[TOC]

# 第一章 类级别注解

## 1-1 本章简介

### 一、Hibernate注解简介

使用注解的目的：为了简化繁琐的ORM映射文件（*.hbm.xml）的配置

### 二、JPA与Hibernate的关系

**1. 什么是JPA**

全称Java Persistence API。JPA注解是javaEE的规范和标准

**2. 关系**

JPA是标准接口，Hibernate是实现，但是其功能是JPA的超集

**3. Hibernate如何实现与JPA的关系**

通过hibernate-annotation、hibernate-entitymanager和hibernate-core三个组件来实现

**4. 一般在实际开发中，优先考虑使用JPA注解，这样更有利于程序的移植和扩展**

### 三、Hibernate注解的分类
1. **类级别注解**
* @Entity
* @Table
* Embeddable
2. **属性级别注解**
3. **映射关系注解**

## 1-2 准备工作
**hibernate.cfg.xml**
```
<session-factory>
    <property name="connection.username">root</property>
    <property name="connection.password">123456</property>
    <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="connection.url">jdbc:mysql:///hibernate?useUnicode=true&amp;characterEncoding=utf8</property>
    <property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
    
    <property name="show_sql">true</property>
    <property name="format_sql">true</property>
    <property name="hbm2ddl.auto">update</property>
    <property name="hibernate.current_session_context_class">thread</property>
</session-factory>
```

## 1-3 @Entity注解
* @Entity： 映射实体类
* @Entity(name="tableName") name可选，对应数据库中的一个表。若表名与实体类名相同，则可以省略

注意：使用@Entity时必须指定实体类的主键属性

```
@Id
public int getSid() {
	return sid;
}
```
```
@Test
public void testSchemaExport() {
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
    
    Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
    
    SchemaExport schemaExport = new SchemaExport();
    
    schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
}
```

## 1-4 @Table注解
* @Table(name="",catalog="",schema="")
* 和@Entity配合使用，只能标注在实体的class定义处，表示实体对应的数据库表的信息
* name:可选，映射表的名称，默认表名和实体名称一致，只有在不一致的情况下才需要指定表名
* catalog：可选，表示Catalog名称，默认为Catalog("")。
* schema：可选，表示Schema名称，默认为Schema("")。

**Schema与Catalog**

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/21.jpg)

* 从实现角度来看，各种数据库系统对Catalog和Schema的支持和实现方式千差万别的

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/22.jpg)

## 1-5 @Embeddable
* @Embeddable表示一个非Entity类可以嵌入到另一个Entity类中作为属性而存在
```
/*
 * 地址类
 */
@Embeddable  //表示是一个嵌入类，这个类的对象在另一个实体类中充当属性
public class Address {
	private String postCode;// 邮编
	private String address;// 地址
	private String phone;// 电话
	
	...
	
}
```
```
/*
 * 学生实体类
 */
@Entity
@Table(name = "t_students", schema = "hibernate")
public class Students {
    ...
    private Address add;
    ...
}
```

# 第二章 属性级别注解
## 2-1 内容简介
**添加方式：**
1. 写在字段上面
2. 写在属性的get访问器的上面
***
* **@Id**
* @SequenceGenerator
* **@GeneratedValue**
* **@Column**
* **@Embedded**
* **@EmbeddedId**
* @Lob
* @Version
* @Basic
* **@Transient**

## 2-2 @Id
* @Id：必须，定义了映射到数据库表的主键的属性，一个实体类可以有一个或者多个属性被映射为主键，可置于主键属性或者getXxx()前。
* 注意：如果有多个属性定义为主键属性，该实体类必须实现serializable接口

## 2-3 @GeneratedValue
```
@GeneratedValue(strategy=GenerationType)
```
* strategy表示主键生成策略，取值有：
1. GenerationType.AUTO：主键由程序控制(也是默认的,在指定主键时，如果不指定主键生成策略，默认为AUTO)
2. GenerationType.IDENTITY：主键由数据库自动生成（主要是自动增长型）
3. GenerationType.SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
4. GenerationType.TABLE：使用一个特定的数据库表格来保存主键。

如：  
```
@Id
@TableGenerator(name="tab_cat_gen",allocationSize=1)
@GeneratedValue(Strategy=GenerationType.Table)
```
**Generator - 表示主键生成器的名称，这个属性通常和ORM框架相关，例如：Hibernate可以指定uuid等主键生成方式**

## 2-4 @Column
* @Column - 可将属性映射到列，使用该注解来覆盖默认值，@Column描述了数据库表中该字段的详细定义，这对于根据JPA注解生成数据库表结构的工具非常有作用

常用属性：
* **name**：可选，表示数据库表中该字段的名称，默认情形属性名称一致
* **nullable**：可选，表示该字段是否允许为null，默认为true
* **unique**：可选，表示该字段是否是唯一标识，默认为false
* **length**：可选，表示该字段的大小，仅对String类型的字段有效，默认值255（如果是主键不能使用默认值）
* **insertable**：可选，表示在ORM框架执行插入操作时，该字段是否应出现INSERT语句中，默认为true
* **updateable**：可选，表示在ORM框架执行更新操作时，该字段是否应该出现在UPDATE语句中，默认为true。对于一经创建就不可以更改的字段，该属性非常有用，如对于birthday字段

## 2-5 @Embedded
* @Embedded是注释属性的，表示该属性的类是嵌入类
* 注意：同时嵌入类也必须标注@Embeddable注解

## 2-6 @EmbeddedId
* @EmbeddedId使用嵌入式主键类实现复合主键
* 注意：嵌入式主键类必须实现Serializable接口、必须有默认的public无参数的构造方法、必须覆盖equals和hashCode方法

## 2-7 @Transient
* 可选，表示该属性并非一个到数据库表的字段的映射，ORM框架将忽略该属性，如果一个属性并非数据库表的字段映射，就务必将标识为@Transient，否则ORM框架默认其注解为@Basic

## 第三章 关系映射级别注解
## 3-1 内容简介
1. 一对一单向外键
2. 一对一双向外键关联
3. 一对一单向外键联合主键
4. 多对一单向外键关联
5. 一对多单向外键关联
6. 一对多双向外键关联
7. 多对多单向外键关联
8. 多对多双向外键关联

## 3-2 实体之间的映射关系
* 一对一：一个公民对应一个身份证号码
* 一对多（多对一）：一个公民有多个银行账号
* 多对多：一个学生有多个老师，一个老师有多个学生

## 3-3 一对一单向外键关联
* @OneToOne(cascade=CascadeType.ALL)
* @JoinColumn(name="pid",unique=true)
* 注意：保存时应该先保存外键对象，再保存主表对象

## 3-4 一对一双向外键关联
* 主控方的配置同一对一单向外键关联
* @OneToOne(mappedBy="card") //被控方
* *双相关联，必须设置mappedBy属性，因为双向关联只能交给一方去控制，不可能在双方都设置外键保存关联关系，否则双防都无法保存**

## 3-5 一对一双向外键联合主键
* 创建主键类
* 主键类必须实现Serializable接口，重写hashCode()和equals()方法

```
//主键类
@Embeddable
//实体类
@EmbeddedId
```

## 3-6 多对一单向外键
* 多方持有一方的引用，比如：多个学生对应一个班级（多对一）

@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
@JoinColumn(name="pid",referencedColumnName="CID")

## 3-7 多对多单向外键
* 学生和教室构成多对多的关联关系
* 其中一个多方持有另一个多方的集合对象（学生持有教室的集合）
* 创建中间表
```
//学生类
@ManyToMany
@JoinTable(name="teachers_students",joinColumns={@JoinColumn(name="sid")},inverseJoinColumns={@JoinColumn(name="tid")})
```

# 第四章 课程总结
1. 类级别注解 @Entity @Table @Embeddable
2. 属性级别注解 @Id @GeneratedValue @Column @Embedded @Embeddable @Transient
3. 映射关系注解
* 一对一单向外键 @OneToOne
* 一对一双向外键关联 @OneToOne(mappedBy="xxx")
* 一对一单向外键联合主键 @Embeddable @EmbeddedId
* 多对一单向外键关联 @ManyToOne @JoinColumn
* 一对多单向外键关联 @OneToMany @JoinColumn
* 一对多双向外键关联 @ManyToOne @OneToMany @JoinColumn
* 多对多单向外键关联 @ManyToMany @JoinTable
* 多对多双向外键关联 @ManyToMany(mappedBy="xxx") @JoinTable