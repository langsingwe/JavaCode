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


## 3-3 Query接口应用编程示例——代码实现
```
package cc.wlc.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.wlc.util.HibernateSessionFactory;

public class SellerTest {

    private Session session = null;
    
    @Before
    public void setUp() throws Exception {
    	session = HibernateSessionFactory.getCurrentSession();
    }
    
    @After
    public void tearDown() throws Exception {
    	session.close();
    }
    
    @Test
    public void testSeller() {
        String hql = "from Seller";
        Query query = session.createQuery(hql);
        List<Seller> sellers = query.list();
        for (Seller seller : sellers) {
        	System.out.println(seller);
        }
    }

}

```

# 第四章 检索对象——from子句
## 4-1 内容简介
1. from子句
2. from子句中持久化类的引用
3. 别名的使用

## 4-2 from子句简介及简单案例实现（上）
1. HQL语句最简形式
2. from指定了HQL语句查询主体——持久化类及其属性
```
package cc.wlc.model;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cc.wlc.util.HibernateSessionFactory;

public class CustomerTest {

    private Session session = null;
    
    @Test
    public void testCustomer() {
        String hql = "from Customer";
        Query query = session.createQuery(hql);
        List<Customer> customers = query.list();
        for(Customer customer:customers) {
            System.out.println("name:"+customer.getName());
        }
    }
    
    @Before
    public void setUp() throws Exception{
    	session = HibernateSessionFactory.getCurrentSession();
    }
    
    @After
    public void tearDown() throws Exception {
    	session.close();
    }

}

```

## 4-3 from子句简单案例实现（下）
```
package cc.wlc.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.wlc.util.HibernateSessionFactory;

public class CommodityTest {

    private Session session = null;
    
    @Test
    public void testFromClause() {
    	String hql = "from Commodity";
    	Query query = session.createQuery(hql);
    	List<Commodity> commodities = query.list();
    	for(Commodity commodity:commodities) {
            System.out.println("name:"+commodity.getName());
//          System.out.println("seller's name:"+commodity.getSeller().getName());
    	}
    }
    
    @Before
    public void setUp() throws Exception{
    	session = HibernateSessionFactory.getCurrentSession();
    }
    
    @After
    public void tearDown() throws Exception {
    	session.close();
    }

}

```

## 4-4 from子句中持久化类的引用
1. 不需要引入持久化类的全限定名，直接引入类名
2. auto-import（自动引入）缺省情况

## 4-5from子句中别名的使用
1. 为被查询的类指定别名
2. 在HQL语句其它部分通过别名引用该类
3. 别名命名习惯

# 第五章 选择——select子句
## 5-1 内容简介
1. 以Object[]形式返回选择的属性
2. 以List形式返回选择的属性
3. 以map形式返回选择的属性
4. 以自定义类型返回选择的属性
5. 获取独特的结果——distinct关键字

## 5-2 通过Object[]返回查询结果
1. select子句中未指定返回数据类型，默认为Object[]
2. 如果要查询的数据只有一个`select s.name from Seller s`,返回类型为对象Object
```
@Test
public void testSelectClauseObjectArray() {
    String hql = "select s.name,s.tel,s.address,s.star from Seller s";
    Query query = session.createQuery(hql);
    List<Object[]> list = query.list();
    
    for (Object[] objs : list) {
        System.out.println("===========");
        System.out.println("name:" + objs[0]);
        System.out.println("tel:" + objs[1]);
        System.out.println("address:" + objs[2]);
        System.out.println("star:" + objs[3]);
    }
}
```

## 5-3 通过List返回查询结果
1. select子句中使用 new list指定
```
@Test
public void testSelectClauseList() {
    String hql = "select new list(s.name,s.tel,s.address) from Seller s";
    Query query = session.createQuery(hql);
    List<List> lists = query.list();
    for(List list:lists) {
        System.out.println("=================");
        System.out.println("name:"+list.get(0));
        System.out.println("tel:"+list.get(1));
        System.out.println("address"+list.get(2));
    }
}
```
## 5-4 通过Map返回查询结果
1. select子句中使用 new map 指定
2. key值为索引值，字符串类型
```
@Test
public void testSelectClauseMap() {
    String hql = "select new map(s.name,s.tel,s.address) from Seller s";
    Query query = session.createQuery(hql);
    List<Map> maps = query.list();
    for(Map map:maps) {
        System.out.println("=================");
        System.out.println("name:"+map.get("0"));
        System.out.println("tel:"+map.get("1"));
        System.out.println("address:"+map.get("2"));
    }
}
```
**可以通过给要查询的数据指定别名**
```
@Test
public void testSelectClauseMap() {
    String hql = "select new map(s.name as name,s.tel as tel,s.address as address) from Seller s";
    Query query = session.createQuery(hql);
    List<Map> maps = query.list();
    for(Map map:maps) {
        System.out.println("=================");
        System.out.println("name:"+map.get("name"));
        System.out.println("tel:"+map.get("tel"));
        System.out.println("address:"+map.get("address"));
    }
}
```

## 5-5 以自定义类型返回查询结果
1. 持久化类定义对应的构造器
2. select子句中调用定义的构造器
**Seller类添加构造方法**
```
public Seller(String name, String tel, String address) {
    this.name = name;
    this.tel = tel;
    this.address = address;
}
```
```
@Test
public void testSlectClauseSelf() {
    String hql = "select new Seller(s.name,s.tel,s.address) from Seller s";
    Query query = session.createQuery(hql);
    List<Seller> sellers = query.list();
    for(Seller seller : sellers) {
        System.out.println("=================");
        System.out.println("name:"+seller.getName());
        System.out.println("tel:"+seller.getTel());
        System.out.println("address:"+seller.getAddress());
    }
}
```

## 5-6 持久化类中无参构造方法的必要性
没有指定构造器Hibernate会指定一个默认的构造器，当程序运行时发现没有默认的构造器程序会报错

Hibernate没有指定的查询的放回集合时候，Hibernate会自动去找默认构造器，如果不存在，则会出现异常

## 5-7 通过distinct返回不重复的查询结果
1. 使用distinct关键字去除查询结果中的重复元素
```
@Test
public void testDistinct() {
    String hql = "select distinct c.sex from Customer c";
    Query query = session.createQuery(hql);
    List<Object> list = query.list();
    for(Object obj:list) {
        System.out.println(obj);
    }
}
```
# 第六章 限制——where子句
## 6-1 内容简介
1. 比较运算
2. 范围运算
3. 字符串模式匹配
4. 逻辑运算
5. 集合运算
6. 在HQL中使用+-*/
7. 查询单个对象（uniqueResult方法）

## 6-2 比较运算
持久化类的属性 VS 给定的查询条件
1. = 、<>（不等）、<、>、>=、<=
2. null值的判断——is [not] null
```
@Test
public void testWhere1() {
    String hql = "from Commodity c where c.price>400";
    Query query = session.createQuery(hql);
    List<Commodity> commodities = query.list();
    for(Commodity c:commodities) {
        System.out.println("name:"+c.getName());
        System.out.println("price:"+c.getPrice());
    }
}
```

## 6-3 null值判断运算
```
@Test
public void testNull() {
    String hql = "from Commodity c where c.description is null";
    Query query = session.createQuery(hql);
    List<Commodity> commodities = query.list();
    for(Commodity c:commodities) {
        System.out.println("name:"+c.getName());
        System.out.println("description:"+c.getDescription());
    }
}
```

## 6-4 范围运算
1. [not] in (列表)
2. [not] between value1 and value2
```
@Test
public void testWhere2() {
    String hql = "from Customer c where c.age in (20,40)";
    Query query = session.createQuery(hql);
    List<Customer> customers = query.list();
    for(Customer c:customers) {
        System.out.println("name:"+c.getName());
        System.out.println("age:"+c.getAge());
    }
}
```

## 6-5 字符串模式匹配
1. like关键字
2. 通配符 %: 任意个。 _ ：一个字符。
```
@Test
public void testWhere3() {
    String hql = "from Customer c where c.address like '%北京%'";
    Query query = session.createQuery(hql);
    List<Customer> customers = query.list();
    for(Customer c:customers) {
        System.out.println("name:"+c.getName());
        System.out.println("address:"+c.getAddress());
    }
}
```

## 6-6 逻辑运算
1. and（逻辑与）、or（逻辑或）
2. not（逻辑非）
```
@Test
public void testWhere2() {
    String hql = "from Commodity c where c.price between 100 and 5000 and c.category like '%电脑%'";
    Query query = session.createQuery(hql);
    List<Commodity> commodities = query.list();
    for(Commodity c:commodities) {
        System.out.println("name:"+c.getName());
        System.out.println("category"+c.getCategory());
        System.out.println("price:"+c.getPrice());
    }
}
```

## 6-7 集合运算
1. is [not] empty： 集合[不]为空，不包含任何元素
2. member of：元素属于集合
```
@Test
public void testWhere1() {
    String hql = "from Order o where o.orderItems is not empty";
    Query query = session.createQuery(hql);
    List<Order> orders = query.list();
    for(Order order:orders) {
        System.out.println(order.getCustomer().getName());
        System.out.println(order.getAmount());
        System.out.println(order.getTradeDate());
    }
}
```

## 6-8 四则运算
1. HQL语句中也可以使用 + - * / 四则运算
2. 四则运算可以在where子句和select子句中使用
```
@Test
public void testWhere4() {
    String hql = "from Commodity c where c.price*5>3000";
    Query query = session.createQuery(hql);
    List<Commodity> commodities = query.list();
    for(Commodity c:commodities) {
        System.out.println("name:"+c.getName());
        System.out.println("price:"+c.getPrice()*5);
    }
}
```

## 6-9 查询单个对象
1. Query接口的uniqueResult方法
2. where子句条件的设置
```
@Test
public void testWhere4() {
    String hql = "from Customer c where c.name='张三'";
    Query query = session.createQuery(hql);
    Customer c = (Customer) query.uniqueResult();
    System.out.println(c.getName());
}
```

# 第七章 排序——order by子句
使用order by子句对查询结果排序
1. 升序排序 asc （默认）
2. 降序排序 desc
```
@Test
public void testOrderBy() {
    String hql = "from Commodity order by seller.id asc,price desc,name asc";
    Query query = session.createQuery(hql);
    List<Commodity> commodities = query.list();
    for(Commodity c:commodities) {
        System.out.println("name:"+c.getName());
        System.out.println("sellerId:"+c.getSeller().getId());
        System.out.println("sellerName:"+c.getSeller().getName());
        System.out.println("price:"+c.getPrice());
    }
}
```