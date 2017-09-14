[TOC]
# Java数据持久化技术概述
参考代码day1
## 持久化的概念
* 对象在内存中创建后，不能永久存在。将对象永久保存起来，就是持久化的过程
* 思考，哪些对象需要被持久化？（通常只有实体域对象需要持久化，过程域对象和事件域对象一般不需要持久化。广义持久化指增、删、改、差）

## 用户管理业务组件编写
* 企业应用在存在大量的业务组件，业务组件是一个系统中最核心的东西，跟你使用什么框架没关系，设计业务组件的核心是接口
* 用户管理业务组件基本功能：实现用户的添加、修改、删除及id查询用户，按name查询用户，按types查询用户等功能
* 设计一个用户类User，包括用户名、性别、用户类别等基本属性
* 设计一个用户管理的业务接口IUserService

## 案例回顾
### 针对User的持久化方案
1. 用文本文件的方式存到硬盘上
2. 用xml文件保存
3. 存为java对象，使用Serializable接口，使用writeObject写
4. 其他保存方式：保存到远端服务云服务器上，或者保存到email服务器中
5. **用数据库保存：便于查询，便于管理，便于使用**

## 把User对象持久化到数据库中
### 通过JDBC API来实现

```
graph TD
A[DriverManager]-->B[connection]
B --> C[statement]
B --> D[PreparedStatement]
C --> E[Result]
D --> E
```
**JDBC的优缺点**
* 优点：控制程度高
* 缺点：代码复杂

### 通过hibernate来实现
1. 导入包
2. 加入hibernate.cfg.xml,并按照我们的数据做好配置
```
<hibernate-configuration>
    <session-factory name="foo">
    	<!-- hibernate 是否显示生成是sql -->
    	<property name="show_sql">true</property>
    	<!-- 告诉 hibernate使用什么数据库方言 -->
    	<property name="dialect">org.hibernate.dialect.MySQLDialect</property>
    	<!-- 告诉 hibernate 使用什么数据库驱动 -->
    	<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
    	<!-- 告诉hibernate 使用哪个数据库 -->
    	<property name="connection.url">jdbc:mysql:///hibernate</property>
    	<!-- 告诉hibernate 数据库用户名 -->
    	<property name="connection.username">root</property>
    	<!-- 告诉hibernate 数据库密码 -->
    	<property name="connection.password">123456</property>
    	
    	<!-- 告诉hibernate 需要管理的业务对象 -->
    	<mapping resource="cc/wei/hibernate/day1/domain/User.hbm.xml"/>
    </session-factory>
</hibernate-configuration>
```
3. 为domain编写映射文件.将映射文件放到Hibernate配置
```
<hibernate-mapping package="cc.wei.hibernate.day1.domain">
    <class name="User" table="USER" dynamic-update="true">
        <id name="id">	<generator class="native"/></id>	
        <property name="name" column="name" type="java.lang.String"/>
        <property name="password" column="password" type="java.lang.String"/>
        <property name="sex" column="sex" />
        <property name="bornDate" column="borndate"/>
    </class>
</hibernate-mapping>
```
4. 编写代码
5. 测试

# javaEE软件结构体系分析
## 软件程序的分层体系结构
### 双层体系结构

```
graph TD
A[应用程序层]-->B(数据库层)
```
### 三层体系结构

```
graph TD
A[表述层]-->B[业务逻辑层]
B --> C(数据库层)
```
* 表述层：提供与用户交互的GUI（图形用户界面）
* 业务逻辑层：实现各种业务逻辑
* 数据库层：负责存放和管理应用的持久性数据
* 应用程序层：负责生成用户界面的代码和负责业务逻辑的代码混在一起

### 区分物理分层和逻辑分层
* 物理分层：每一层运行在单独的机器上，意味着创建分布式软件系统
* 逻辑分层：在单个的软件模块中完成特定的功能
* 不作特别说明：软件分层指的是逻辑分层

### 软件分层的优点
1. 伸缩性：能否支持更多用户
2. 可维护性：需求变化时，影响一部分，不影响其他部分的代码
3. 可扩展性：增加新功能的难易程度
4. 可重用性：代码没冗余，满足多种需求
5. 可管理性：管理系统的难易程度

### 软件分层的缺点
1. 设计人员要求高
2. 体系结构合理划分，耗时大
3. 调试困难
4. 对于规模较小的应用，软件分层会降低开发效率

### java应用的持久化层
```
graph TD
A[表述层]-->B[业务逻辑层]
B --> C[持久化层]
C --> D(数据库层)
```
## Hibernate 中间件特性
```
graph TD
A[应用1业务逻辑层]-->B[持久化层Hibernate]
C[应用2业务逻辑层]-->B
D[应用3业务逻辑层]-->B
B --> E(数据库1)
B --> F(数据库2)
B --> G(数据库3)
```
持久化层封装了数据访问的细节，为业务逻辑层提供了面向对象的API，完善的持久化层应该达到的目标：
1. 代码重用性高，可完成所有的数据访问操作
2. 如果需要的话，能够支持多种数据库平台
3. 具有相对独立性，当持久化层变化时，不会影响上层实现

# Hibernate入门
## Hibernate是什么
官网：http://www.hibernate.org/
面向java及.net等环境的对象-关系数据库映射工具
1. 开源的持久层框架
2. ORM映射工具，建立面向对象的域模型和关系数据模型之间的映射
3. 连接java或.net应用和数据库的中间件
4. 对JDBC进行封装，负责java对象的持久化
5. 在分层结构中处于持久化层，封装对数据库的访问细节，使业务逻辑层更专注于实现业务逻辑

## ORM简介
概念：对象-关系映射，指单个组件中负责所有实体域对象的持久化，封装数访问细节

## 对象-关系映射的概念

面向对象概念 | 面向关系概念
---|---
类 | 表
类的实例 | 表的行（记录）
属性，对象粒度 | 表的列（字段）

## Hibernate作用
* hibernate是java应用和关系数据库之间的桥梁，它负责java对象和关系数据之间的映射
* hibernate内部封装了通过JDBC访问数据库的操作，向上层应用提供了面向对象的数据访问API

# Hibernate API简介
1. 提供了访问数据库操作（SessionFactory,session,transaction,query）
2. 配置hibernate接口（configuration）

> Hibernate对JDBC做了轻量级的封装，所谓轻量级是指hibernate并没有完全封装JDBC，java既可以通过hibernate API来访问数据库，还可以直接通过JDBC API访问数据库

## hibernate核心接口
1. Configuration  
    配置hibernate,启动hibernate,创建SessionFactory对象
2. SessionFactory接口  
    负责创建Session对象，可以通过Configuration来创建SessionFactory对象。  
    SessionFactory对象中保存了当前的数据库配置信息和所有映射关系以及预定义的SQL语句。同时，SessionFactory还负责维护Hibernate的二级缓存  
    SessionFactory对象的创建会有较大的开销，而且SessionFactory对象采取了线程安全的设计方式，因此在实际中SessionFactory对象可以尽量的共享，在大多数情况下，一个应用中针对一个数据库共享一个SessionFactory实例
```
Configuration conf = new Configuration().configure(String path);
SessionFactory factory = config.buildSessionFactory();
```
3. Session接口  
    使用最广泛、也被称为持久化管理器，它提供和持久化相关的操作，CRUD等，不是线程安全的，因此，一个Session对象只可以由一个线程使用。避免多个线程共享。轻量级的，创建和销毁不需要消耗太多资源。Session中有一个缓存，称为一级缓存，存放当前工作单元加载的对象
```
Configuration conf = new Configuration().configure(String path);
SessionFactory factory = config.buildSessionFactory();
Session session = factory.openSession();
```

4. Transaction hibernate数据库事务接口  
将应用代码从底层的事务中抽象出来--这可能是一个JDBC事务，一个JTA用户事务或者甚至是一个公共对象请求代理结构（CORBA）--允许应用通过一组一致的API控制事务边界  
使用hibernate进行操作时（增、删、改）必须显示的调用Transaction（默认：autoCommit=false）
```
Transaction tx = session.beginTransaction();
```
5. Query和Criteria接口  
都是查询接口，query实例包装了HQL查询语句，HQL是面向对象的，它引用类名及类的属性名，而不是表名和字段名  
Criteria接口完全封装了基于字符串形式的查询语句，比query接口更面向对象，它擅长执行动态查询

## Hibernate运行过程
1. 应用程序调用Configuration类，该类读取hibernate配置文件及映射文件中的信息
2. 并用这些信息生成一个SessionFactory对象
3. 然后从SessionFatory对象生成一个Session对象
4. 并用Session对象生成Transaction类  
A、可通过Session对象的get(),load(),save(),update(),delete()和saveOrUpdate()等方法对PO进行加载、保存、更新、删除等操作  
B、在查询的情况下，可通过Session对象生成一个Query对象，然后利用Query对象执行查询操作，如果没有异常。Transaction对象将提交这些操作到数据库中

# 对象-关系映射基础