[TOC]
# 第一章 Hibernate初识
## 1-1 课程介绍
1. 什么是ORM
2. Hibernate简介
3. 编写第一个示例

## 1-2 什么是ORM
* ORM(Object/Relationship Mapping)：对象/关系映射

 利用面向对象思想编写的数据库应用程序最终都是把对象信息保存在关系型数据库中，于是要编写很多和底层数据库相关的SQL语句

* 写SQL语句有什么不好吗？

1. 不同的数据库使用的SQL语法不同，比如：PL/SQL与T/SQL
2. 同样的功能在不同的数据库中有不同的实现方式。比如分页SQL
3. 程序过分依赖SQL对程序的移植以及扩展、维护带来很大的麻烦

* 有没有办法让程序员彻底抛弃书写SQL的思想，完全的使用面向对象思想开发软件呢？

## 1-3 Hibnerate简介
Hibnerate是Java领域的一款开源的ORM框架技术

Hibnerate对JDBC进行了非常轻量级的对象封装

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/1.jpg)

**其他主流的ORM框架技术**
1. MyBatis
2. Toplink
3. EJB

## 1-4 开发前的准备
* 开发工具：Eclipse
* Hibernate Tools for Eclipse Plugins
* Hibernate Tools是由JBoss推出的一个eclipse综合开发工具插件，该插件可以简化ORM框架Hibernate,以及JBoss Seam,EJB3等的开发工作

最新版本eclipse oxygen在Help --> Eclipse Marketplace --> 搜索JBoss Tool --> 安装

其他版本去：http://tools.jboss.org/downloads/jbosstools/oxygen/4.5.0.Final.html#marketplace  下载对应版本的ZIP包，然后选择安装

## 1-5 编写第一个Hibernate例子
* 创建Hibernate的配置文件
* 创建持久化类
* 创建对象-关系映射文件
* 通过Hibernate API编写访问数据库的代码

***

* 导入Hibernate必须的jar包  
 hibernate-release-5.2.11.Final/lib/required
* 导入Mysqk的JDBC驱动  
 mysql-connector-java-5.1.16-bin.jar
* 导入JUnit4的jar包  
 junit-4.10.jar

## 1-6 创建hibernate工程

* File --> new Java Project --> Hibernate_001
* Windows --> Preferences --> Java --> Build Path --> User Libraries --> New
1. hibernate-core 引入 hibernate-release-5.2.11.Final/lib/required 中的所有jar包
2. mysql-jdbc 引入  mysql-connector-java-5.1.16-bin.jar
3. junit4 引入 junit-4.10.jar  
* 右键项目 --> Build Path --> Add Library --> User Libraries --> 勾选hibernate-core,junit4,mysql-jdbc

## 1-7 hibernate配置文档
* 创建hibernate的配置文件  
 hibernate-release-5.2.11.Final\project\hibernate-core\src\main\resources\org\hibernate\hibernate-configuration-3.0.dtd

```
<property name="connection.username">root</property>
<property name="connection.password">123456</property>
<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
<property name="connection.url">jdbc:mysql:///hibernate?useUnicode=true&amp;characterEncoding=UTF-8</property>
<property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
```
> 这里因为我是MySQL版本是5以上的，所以在最后一行的方言属性配置必须为`org.hibernate.dialect.MySQL5Dialect`,5以下的版本为`org.hibernate.dialect.MySQLDialect`。

## 1-8 创建持久化类
**Students类**
```
package cc.wlc.vo;

import java.util.Date;

/*
 * 学生类
 */
public class Students {

    // 1. 公有的类
    // 2. 提供公有的不带参数的默认构造方法
    // 3. 属性私有
    // 4. 属性setter/getter封装
    
    private int sid;// 学号
    private String sname;// 姓名
    private String gender;// 性别
    private Date birthday;// 出生日期
    private String address;// 地址
    
    public Students() {
    
    }
    
    public Students(int sid, String sname, String gender, Date birthday, String address) {
    	// super();
    	this.sid = sid;
    	this.sname = sname;
    	this.gender = gender;
    	this.birthday = birthday;
    	this.address = address;
    }
    
    public int getSid() {
    	return sid;
    }
    
    public void setSid(int sid) {
    	this.sid = sid;
    }
    
    public String getSname() {
    	return sname;
    }
    
    public void setSname(String sname) {
    	this.sname = sname;
    }
    
    public String getGender() {
    	return gender;
    }
    
    public void setGender(String gender) {
    	this.gender = gender;
    }
    
    public Date getBirthday() {
    	return birthday;
    }
    
    public void setBirthday(Date birthday) {
    	this.birthday = birthday;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    @Override
    public String toString() {
    	return "Students [sid=" + sid + ", sname=" + sname + ", gender=" + gender + ", birthday=" + birthday
                         + ", address=" + address + "]";
    }

}

```

## 1-9 创建对象-关系映射文件和数据库
1. new --> Hibernate XML Mapping File --> 选择类Students --> Finish
2. 在hibernate.cfg.xml中添加`<mapping resource="Students.hbm.xml"/>`
3. 创建数据库 hibernate

## 1-10 使用JUnit进行测试
* @Test: 测试方法
* @Before: 初始化方法
* @After: 释放资源
* 右键项目 --> New --> Source Folder (test) --> 创建类 StudentsTest
```
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * 测试类
 */
public class StudentsTest {
    
    @Before
    public void init() {
    	
    }
    
    @After
    public void destroy() {
    	
    }
    
    @Test
    public void testSaveStudents() {
    	
    }
}
```

## 1-11 通过hibernate API编写访问数据库的代码

**hibernte4以下的代码**
```
//创建配置对象
Configuration config = new Configuration().configure();
//创建服务注册对象
ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//创建会话工厂对象
sessionFactory = config.buildSessionFactory(serviceRegistry);
//打开会话
session = sessionFactory.openSession();
//打开事务
transaction = session.beginTransaction();
```
**hibernate4以上的代码**
```
//获得配置对象
Configuration config = new Configuration().configure();
//创建服务注册对象
ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//创建会话工厂对象
SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
//打开会话
Session session = sessionFactory.openSession();
//打开事务
transaction = session.beginTransaction();
```
或
```
//创建配置对象
Configuration config = new Configuration().configure();
//创建会话工厂
sessionFactory = config.buildSessionFactory();
//打开会话
session = sessionFactory.getCurrentSession();
//打开事务
transaction = session.beginTransaction();
```

# 第二章 Hibernate进阶
## 2-1 课程简介
1. hibernate.cfg.xml常用配置
2. session简介
3. transaction简介
4. session详解
5. 对象关系映射常用配置

## 2-2 hibernate.cfg.xml常用配置
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/2.jpg)

hibernate前缀可以省略。即：hibernate.dialect等同于dialect

## 2-3 session简介
* hibernate的执行流程

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/3.jpg)

不建议直接使用JDBC的connection操作数据库，而是通过使用session操作数据库

session可以理解为操作数据库的对象

session与connection，是多对一关系，每个session都有一个与之对应的connection，一个connection不同时刻可以供多个session使用

把对象保存在关系数据库中需要调用session的各种方法，如:save(),update(),delete(),createQuery()等。

## 2-4 transaction简介
* hibernate对数据的操作都是封装在事务当中，并且默认是非自动提交的方式，所以用session保存对象时，如果不开启事务，并且sho手动提交事务，对象并不会真正保存在数据库中
* 如果想让hibernate像jdbc那样自动提交事务，必须调用session对象的doWork()方法，获得jdbc的connection后，设置其为自动提交事务（注意：通常不推荐这样做）

## 2-6 sesssion详解（上）
* 如何获得session对象？
1. openSession
2. getCurrentSession

> 如果使用getCurrentSession需要在hibernate.cfg.xml文件中进行配置：  
如果是本地事务（jdbc事务）  
`<property name="hibernate.current_session_context_class">thread</property>`  
如果是全局事务（jta事务）  
`<property name="hibernate.current_session_context_class">jta</property>`  

## 2-7 session详解（下）
* **openSession与getCurrentSession的区别**
1. getCurrentSession在事务提交或者回滚之后会自动关闭，而openSession需要你手动关闭，如果使用openSession而没有手动关闭，多次之后会导致连接池溢出
2. openSession每次创建新的session对象。getCurrentSession使用现有的session对象

## 2-8 对象关系映射常用配置
```
<hibernate-mapping
    schema="schemaName"
    catalog="catalogName"
    default-cascade="cascade_style" //级联风格
    default-access="field|property|ClassName"   //访问策略
    default-lazy="true|false"   //加载策略
    package="packagename"
/>
    
<class
    name="ClassName"
    table="tableName"
    batch-size="N"
    where="condition"
    entity-name="EntityName"
/>

<id name="propertyName" type="typename"> 
    column="column_name"
    length="length"
    <generator class="generatorClass" />
</id>
```
**主键生成策略**  
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/4.jpg)

# 第三章 Hibernate单表操作
## 3-1 课程简介
1. 单一主键
2. 基本类型
3. 对象类型
4. 组件属性
5. 单表操作CRUD实例

## 3-2 单一主键
* assigned 由java应用程序负责生成（手工赋值）
* native 由底层数据库自动生成标识符，如果是MySQL就是increment，如果是Oracle就是sequence...

## 3-3 基本类型

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/5.jpg)

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/6.jpg)

## 3-4 对象类型

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/7.jpg)

* MySQL不支持SQL的CLOB类型，在MySQL中，用TEXT,MEDIUMTEXT及LONGTEXT类型来表示长度超过255的长文本数据

## 3-4 组件属性
* 实体类中的某个属性属于用户自定义的类的对象
```
<component name="address" class="Address">
    <property name="postcode" column="POSTCODE"/>
    <property name="phone" column="PHONE"/>
    <property name="address" column="ADDRESS"/>
</component>
```