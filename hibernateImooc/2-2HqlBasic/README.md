[TOC]

# 第一章 课程介绍
## 1-1 课程内容
1. 了解HQL
2. 准备查询
3. 查询子句
* 检索对象——from子句
* 选择——select子句
* 限制——where子句
* 排序——order by子句

## 1-2 课程目标
1. 了解HQL语句定义以及HQL语句形式
2. 掌握Query对象的使用
3. 能够编写出符合数据查询要求的HQL语句

# 第二章 了解HQL
## 2-1 内容简介
1. HQL定义
2. HQL语句形式
3. 初学HQL注意的问题

## 2-2 HQL定义
1. Hibernate Query Language,Hibernate查询语言
2. HQL是面向对象的查询语言
3. HQL提供了丰富灵活的查询特性，Hibernate官方推荐的查询方式

## 2-3 HQL语句形式
* select...
* **from...**
* where...
* group by...
* having...
* order by...

## 2-5 初学HQL要注意的问题
1. HQL是面向对象的查询语言，对java类与属性大小写敏感
2. HQL对关键字不区分大小写

# 第三章 准备查询
## 3-1 内容简介
1. org.hibernate.Query接口
2. Query实例的创建
3. 执行查询

## 3-2 Query接口简介
**org.hibernate.Query**
1. Query接口定义有执行查询的方法
2. Query接口支持方法链编程风格，使得程序代码更为简洁

**Query实例的创建**
1. Session的createQuery()方法创建Query实例
2. createQuery方法包含一个HQL语句参数，createQuery(hql)

**Query执行查询**
1. Query接口的list()方法执行HQL查询
2. list()方法返回结果数据类型为java.util.List, List集合中存放符合查询条件的持久化对象

## 3-3 Query接口应用编程示例——数据库分析
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/9.jpg)

<font size=6>**表结构**</font>

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/10.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/11.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/12.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/13.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/14.jpg)

<font size=6>**表数据**</font>

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/15.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/16.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/17.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/18.jpg)
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/19.jpg)
