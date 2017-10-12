[TOC]

# 第一章 课程简介
**映射类型**
* 一对多（one-to-many）
* 多对一（many-to-one）
* 一对一（one-to-one）
* 多对多（many-to-many）

# 第二章 Hibernate中的单向一对多关联
## 2-1 一对多映射简介
如：一个班级对应多个学生
* 在数据库中，可以通过添加主外键的关联，表现一对多的关系
* 通过在一方持有多方的集合实现，即在“一”的一端中使用<set>元素表示持有“多”的一端的现象

## 2-2 hibernate的基础配置
New --> Java Project --> copy hibernate.cfg.xml --> src目录
```
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.url">
        	jdbc:mysql:///hibernate?useUnicode=true&characterEncoding=utf8
        </property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">123456</property>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="show_sql">true</property>
    </session-factory>
</hibernate-configuration>
```

## 2-3 创建HibernateUtil工具类
```
package cc.wlc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
    private static SessionFactory sessionFactory;
    private static Session session;
    
    static {
    	Configuration config = new Configuration().configure();
    	sessionFactory = config.buildSessionFactory();
    }
    
    //获取sessionFactory
    public static SessionFactory geSessionFactory() {
    	return sessionFactory;
    }
    
    //获取session
    public static Session getSession() {
    	session = sessionFactory.openSession();
    	return session;
    }
    
    //关闭session
    public static void closeSession(Session session) {
    	if(session!=null) {
    		session.close();
    	}
    }
}

```

## 2-4 在mysql数据库中建表
```
create table grade
(
	gid int primary key,
	gname varchar(20) not null,
	gdesc varchar(50)
);
create table student
(
	sid int primary key,
	sname varchar(20) not null,
	sex char(2),
	gid int
);
alter table student add constraint fk_student_gid foreign key (gid) references grade(gid);
```

## 2-5 创建持久化和映射文件并配置一对多关系
**Grade.hbm.xml**
```
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<!-- Generated 2017-10-12 10:49:12 by Hibernate Tools 3.5.0.Final -->
<hibernate-mapping>
    <class name="cc.wlc.entity.Grade" table="grade">
        <id name="gid" type="int">
            <column name="gid" />
            <generator class="increment" />
        </id>
        <property name="gname" type="java.lang.String">
            <column name="gname" length="20" not-null="true"/>
        </property>
        <property name="gdesc" type="java.lang.String">
            <column name="gdesc" />
        </property>
        <set name="students" table="student" inverse="false" lazy="true">
            <key>
                <column name="gid" />
            </key>
            <one-to-many class="cc.wlc.entity.Student" />
        </set>
    </class>
</hibernate-mapping>
```
**Student.hbm.xml**
```
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<!-- Generated 2017-10-12 10:49:12 by Hibernate Tools 3.5.0.Final -->
<hibernate-mapping>
    <class name="cc.wlc.entity.Student" table="student">
        <id name="sid" type="int">
            <column name="sid" />
            <generator class="increment" />
        </id>
        <property name="sname" type="java.lang.String">
            <column name="sname" />
        </property>
        <property name="sex" type="java.lang.String">
            <column name="sex" />
        </property>
    </class>
</hibernate-mapping>
```
**在hibernate.cfg.xml中要加上**
```
<mapping resource="cc/wlc/entity/Grade.hbm.xml"/>
<mapping resource="cc/wlc/entity/Student.hbm.xml"/>
```

## 2-6 测试——添加和查询学生信息
```
//将学生添加到班级
public static void add() {
    Grade grade = new Grade("java一班","java软件开发一班");
    Student s1 = new Student("张三","男");
    Student s2 = new Student("莉莉", "女");
    
    //如果希望在学生表中添加对应的班级编号，需要在班级中添加学生，建立关联关系
    grade.getStudents().add(s1);
    grade.getStudents().add(s2);
    
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(grade);
    session.save(s1);
    session.save(s2);
    tx.commit();
    HibernateUtil.closeSession(session);
}

//查询班级中包含的学生
public static void findStudentsByGrade() {
    Session session = HibernateUtil.getSession();
    Grade grade = session.get(Grade.class, 1);
    System.out.println(grade.getGname()+","+grade.getGdesc());
    
    Set<Student> students = grade.getStudents();
    for(Student stu:students) {
    	System.out.println(stu.getSname()+","+stu.getSex());
    }
}
```

## 2-7 测试——修改和删除学生信息
```
//修改学生信息
public static void update() {
    Grade grade = new Grade("java二班","java软件开发二班"); 
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Student stu = session.get(Student.class, 1);
    grade.getStudents().add(stu);
    session.save(grade);
    tx.commit();
    HibernateUtil.closeSession(session);
}

//删除学生信息
public static void delete() {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Student stu = session.get(Student.class, 2);
    session.delete(stu);
    tx.commit();
    HibernateUtil.closeSession(session);
}
```
## 2-8 set元素的常用属性
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/8.jpg)

# 第三章 单向多对一关联

## 3-1 简介
* 多对一的关系和关系数据库的外键参照关系最匹配，即在己方的表中的一个外键参照另一个表的主键
* 通过在多方持有一方的引用实现，需要在“多”的一端使用<many-to-one>配置

## 3-2 单向多对一的配置
**在Student类中添加Grade类的引用**
```
private Grade grade;
```
添加getter/setter方法

**在多方的配置文件Student.hbm.xml中添加配置代码**
```
<!-- 配置多对一关联关系 -->
<many-to-one name="grade" class="cc.wlc.entity.Grade" column="gid"></many-to-one>
```

## 3-3 测试——添加学生信息
```
public static void save() {
    Grade g = new Grade("java一班","java软件开发一班");
    Student stu1 = new Student("李梅","女");
    Student stu2 = new Student("小明","男");
    
    //设置关联关系
    stu1.setGrade(g);
    stu2.setGrade(g);
    
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(g);
    session.save(stu1);
    session.save(stu2);
    tx.commit();
    HibernateUtil.closeSession(session);
}
```
## 3-4 双向多对一测试
```
//设置关联关系
g.getStudents().add(stu1);
g.getStudents().add(stu2);
stu1.setGrade(g);
stu2.setGrade(g);
```

# 第四章 inverse和cascade属性
## 4-1 inverse属性的用法
* <set>节点的inverse属性指定关联关系的控制方向，默认由one方来维护
* 关联关系中，inverse = "false" 则为主动方，由主动方负责维护关联关系
* 在一对多关联中，只能设置one方的inverse为true，这将有助于性能的改善

## 4-2 cascade属性的用法

* 当设置了cascade属性不为none时，Hibernate会自动持久化关联的对象
* cascade属性的设置会带来性能上的变动，需谨慎设置

属性值 | 含义和作用
---|---
all | 对所有操作进行级联操作
save-update | 执行保存和更新操作时进行级联操作
delete | 执行删除操作时进行级联操作
none | 对所有操作不进行级联操作

## 4-3 测试——信息查询
```
//查询学生所在班级的信息
public static void findGradeByStudent() {
    Session session = HibernateUtil.getSession();
    Student stu = session.get(Student.class, 2);
    System.out.println(stu.getSid()+","+stu.getSname()+","+stu.getSex());
    
    Grade g = stu.getGrade();
    System.out.println(g.getGid()+","+g.getGname()+","+g.getGdesc());
    HibernateUtil.closeSession(session);
}
```