[TOC]

# 第一章 多对多的应用场景

<font size=4>**案例**</font>  

**企业项目开发过程中**
* 一个项目可由多个员工参与开发
* 一个员工可同时参与开发多个项目

**多对多关联（many-to-many）**
* 多对多关联也是常见的一种关联关系，如项目和员工之间就是典型的多对多关系
* 多对多关联关系一般采用中间表的形式来实现，即新增一张包含关联双方主键的关联表
* 多对多关联可以使用<set>元素和<many-to-many>元素进行配置

# 第二章 多对多的映射配置案例
## 2-1 创建项目和表
```
create table project
(
	proid int primary key,
	proname varchar(20) not null
);
create table employee
(
	empid int primary key,
	empname varchar(20)
);
create table proemp
(
	rproid int,
	rempid int
);
alter table proemp add constraint fk_rproid
	foreign key (rproid) references project(proid);
alter table proemp add constraint fk_rempid
	foreign key (rempid) references employee(empid);
```

## 2-2 创建持久化类和映射文件
**Project类**
```
public class Project {
    private int proid;
    private String proname;
    // 添加一个员工的集合
    private Set<Employee> employees = new HashSet<Employee>();
    
    ...
    
}
```
**Employee类**
```
public class Employee {
    private int empid;
    private String empname;
    // 添加一个项目的集合
    private Set<Project> projects = new HashSet<Project>();
    
    ...
    
}
```

## 2-3 配置映射文件
**Project.hbm.xml**
```
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<!-- Generated 2017-10-12 15:32:10 by Hibernate Tools 3.5.0.Final -->
<hibernate-mapping>
    <class name="cc.wlc.entity.Project" table="project">
    	<id name="proid" column="proid" type="java.lang.Integer">
    		<generator class="assigned"/>
    	</id>
    	<property name="proname" type="java.lang.String">
    		<column name="proname" length="20" not-null="true"/>
    	</property>
    	<!-- 配置多对多关联关系 -->
    	<set name="employees" table="proemp" cascade="all">
    		<key column="rproid"></key>
    		<many-to-many class="cc.wlc.entity.Employee" column="rempid"></many-to-many>
    	</set>
    </class>
</hibernate-mapping>

```
**Employee.hbm.xml**
```
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<!-- Generated 2017-10-12 15:32:10 by Hibernate Tools 3.5.0.Final -->
<hibernate-mapping>
    <class name="cc.wlc.entity.Employee" table="employee">
        <id name="empid" type="int">
            <column name="empid" />
            <generator class="assigned" />
        </id>
        <property name="empname" type="java.lang.String">
            <column name="empname" lenght="20" not-null="true"/>
        </property>
        <set name="projects" table="proemp" inverse="true" lazy="true">
            <key>
                <column name="rempid" />
            </key>
            <many-to-many class="cc.wlc.entity.Project" column="rproid"/>
        </set>
    </class>
</hibernate-mapping>

```

## 2-4 测试
```
package cc.wlc.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cc.wlc.entity.Employee;
import cc.wlc.entity.Project;
import cc.wlc.util.HibernateUtil;

/*
 * 多对多关联关系的配置
 * 同时建立了Project到Employee之间的双向多对多的关系
 * 关联关系的维护交由Project方来处理,并且在保存Project对象时会一并保存Employee对象
 */
public class Test {
    public static void main(String[] args){
        Project project1 = new Project(1001,"项目一");
        Project project2 = new Project(1002,"项目二");
        Employee employee1 = new Employee(1,"小明");
        Employee employee2 = new Employee(2,"小红");
        
        //参加项目一的员工有：小明、小红
        project1.getEmployees().add(employee1);
        project1.getEmployees().add(employee2);
        //参加项目二的员工有：小明
        project2.getEmployees().add(employee1);
        
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(project1);
        session.save(project2);
        tx.commit();
        HibernateUtil.closeSession(session);
    }
}

```
# 第三章 总结
* 实现多对多关联关系
* 在数据库底层通过添加中间表来指定关联关系
1. 在双方的实体中添加一个保存对方的集合
2. 在双方的映射文件中使用<set>元素和<many-to-many>元素进行关联关系的配置