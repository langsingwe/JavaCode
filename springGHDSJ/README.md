[TOC]
# 一、邂逅spring
1. spring：春天，预示着给软件行业带来春天
2. 作者 Rod Johson
3. spring本身就是一个大杂烩，支持各种现有的框架，使得现在框架更加实用。充当桥梁和纽带的作用。
4. 结构体系
![spring结构体系图](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/spring/runtime.jpg)
5. spring本身包含多种设计模式  
    * 单例模式
    * 亨元模式
    * 代理模式
    * 工厂模式
    * 模版模式
6. spring提供的功能  
    * IOC
    * AOP
    * 事务
    * 框架整合

# 二、IOC
## IOC概念
inverse of control控制反转。在传统的开发中对象是由程序员通过new来创建的。使用spring后，对象由spring容器来创建。在需要使用对象的地方，由spring容器来进行设置。这样结果：创建对象的权限由程序员变为spring容器。设置对象由主动设置变为被动接收。由主动式编程变为被动式编程。
## 案例一：  
> 参考代码 01spring_helloworld

1. 新建java项目  
2. 添加spring的jar包  
```
aopalliance.jar
aspectjweaver-1.5.4.jar
commons-logging-1.1.1.jar
spring-aop-4.3.9.RELEASE.jar
spring-aspects-4.3.9.RELEASE.jar
spring-beans-4.3.9.RELEASE.jar
spring-context-4.3.9.RELEASE.jar
spring-core-4.3.9.RELEASE.jar
spring-expression-4.3.9.RELEASE.jar
spring-jdbc-4.3.9.RELEASE.jar
spring-orm-4.3.9.RELEASE.jar
spring-tx-4.3.9.RELEASE.jar
spring-web-4.3.9.RELEASE.jar
spring-webmvc-4.3.9.RELEASE.jar
```
3. 添加vo类
```
package cc.wei.vo;
public class User {
    private int age;
    private String name;
    public int getAge() {
    	return age;
    }
    public void setAge(int age) {
    	this.age = age;
    }
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
	
}

```
4. 添加spring的配置文件  

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="user" class="cc.wei.vo.User">
    	<property name="name" value="张三"/>
    </bean>
</beans>

```
5. 测试
    
```
public class SpringDemo {
    public static void main(String[] args) {
    	//读取配置文件，启动容器
    	ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    	User user = (User) ac.getBean("user");
    	System.out.println(user);
    }
}
```
## 使用spring来创建对象的方式
> 参考代码 02spring_ioc

1. 通过有参构造方法来创建对象   
 ① 通过参数名称来设值
```
<!-- 通过参数名称来创建对象 -->
<bean id="user" class="cc.wei.vo.User">
	<!-- constructor-arg构造方法参数，name表示参数名称，value表示参数值 -->
	<constructor-arg name="name" value="张三"/>
	<constructor-arg name="age" value="26"/>
</bean>
```
 ② 通过参数下标来设值
```
<bean id="user1" class="cc.wei.vo.User">
    <!-- constructor-arg构造方法参数，index表示参数下标，value表示参数值 -->
    <constructor-arg index="0" value="20"/>
    <constructor-arg index="1" value="李四"/>
</bean>
```
 ③ 通过参数的类型来设值
```
<bean id="user2" class="cc.wei.vo.User">
    <!-- constructor-arg构造方法参数，type表示参数类型，value表示参数值
    	 根据参数类型来设值，如果有多个参数类型相同，那么将会根据出现的顺序来指定值
     -->
    <constructor-arg type="int" value="20"/>
    <constructor-arg type="java.lang.String" value="李四"/>
</bean>
```
2. 通过无参构造方法来创建对象
 默认会存在一个无参构造函数

3. 通过静态工厂来创建对象  
 * 静态工厂类：
```
public class UserStaticFactory {
    public static User createUser(int age,String name) {
    	return new User(age,name);
    }
}	
```   
  
* 配置文件：
```
<bean id="user3" class="cc.wei.factory.UserStaticFactory" factory-method="createUser">
    <constructor-arg index="0" value="60"/>
    <constructor-arg index="1" value="王五"/>
</bean>
```
4. 通过动态工厂来创建对象
* 动态工厂类
```
public class UserDynamicFactory {
    public User createUser (int age,String name) {
        return new User(age,name);
    }
}
```
* 配置文件
```
<!-- 配置动态工厂 -->
    <bean id="factory" class="cc.wei.factory.UserDynamicFactory"/>
    <bean id="user4" factory-bean="factory" factory-method="createUser">
    	<constructor-arg index="0" value="30"/>
    	<constructor-arg index="1" value="大头"/>
    </bean>
```

# 三、配置文件讲解
1. bean
```
<!-- 在配置文件中，一个bean表示一个对象，容器会根据bean配置信息生成对应的对象
     id表示bean的标识符，根据标识符可以从容器中获取对象,id不能重复。如果没有配置标识符，可以根据对象
     类型来获取该对象，如果没有配置标识符，那么该类型的对象在容器中只能有一个。name指定对象的别名
     如果没有id,那么name作为标识符。可以指定多个别名。多个别名之间可以使用空格，分号，逗号来分隔。
     class指定bean的完全限定名 
 -->
    <bean id="user" class="cc.wei.vo.User">
    	<!-- property表示bean对象的属性,name表示bean对象中属性所对应的set方法,value表示属性值,只能是基本数据类型和字符串,如果是对象，使用ref来设置
    	 -->
    	<property name="name" value="张三"/>
    	<property name="age" value="20"/>
    	<property name="role" ref="role"></property>
    </bean>
    <bean id="role" class="cc.wei.vo.Role">
    	<property name="name" value="admin"/>
    </bean>
```
2. alias
```
<!-- 设置别名 -->
    <alias name="user" alias="u5"/>
```
3. import
```
<!-- 用于团队开发，引入别的配置文件 -->
<import resource="" />
```
# 四、依赖注入DI
1. DI：dependency injection依赖注入

2. 特点  
* 对象的创建依赖于容器
* 对象属性的设值依赖容器

3. 注入：指对象属性值的注入过程

4. 依赖注入和控制反转是同一个事物从不同角度看待

4. spring中有两种注入方式  
* 构造器注入
* setter注入

5. 常见的属性注入类型
* 基本类型---直接使用value来指定即可
```
<bean id="user" class="cc.wei.vo.User">
    <property name="age" value="30"/>
    <property name="name" value="mike"/>
</bean>
```
* 引用类型---被引用对象由容器来创建，通过ref来进行引用
```
<bean id="user" class="cc.wei.vo.User">
    <property name="age" value="30"/>
    <property name="name" value="mike"/>
    <property name="role" ref="role"/>
</bean>
<bean id="role" class="cc.wei.vo.Role">
    <property name="name" value="lucy"/>
</bean>
```
* 数组---可以使用array或者list来设置
```
<property name="hobbies">
    <array>
        <value>足球</value>
        <value>乒乓球</value>
        <value>篮球</value>
        <value>网球</value>
    </array>
</property>
```
* list---设值和数组一样，有序的
* set ---设置语法结构相同，但是set设值相同会覆盖，set是无序的
```
<property name="vips">
    <array>
        <value>华联</value>
        <value>物美</value>
        <value>大润发</value>
        <value>华联</value>
    </array>
</property>
```
* map
```
<property name="banks">
    <map>
        <entry key="ICBC" value="工商银行"/>
        <entry key="ABC" value="农业银行"/>
    </map>
</property>
```
* properties
```
<property name="body">
    <props>
    	<prop key="身高">170</prop>
    	<prop key="体重">55kg</prop>
    	<prop key="三围">60,60,60</prop>
    </props>
</property>
```
* Null的设值
```
<property name="name"><null/></property>
```
* p命名空间设值  
 ① 导入命名空间
 ```
 <beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
 ```
 ② 配置
 ```
 <bean id="role" class="cc.wei.vo.Role" p:name="admin"/>
 ```
 
# 五、scope和autowiring
1. scope：表示sprign容器创建对象的范围  
 singleton：单例  
 prototype：每次获取对象都会创建一个新的对象,在整合struts2时，action一定要设置为prototype
 request：表示请求范围  
 session：表示web的会话范围  
 application：表示servletContext

2. autowiring 自动装配 减少spring的配置文件  
* No 默认不使用自动装配，需要进行全部的设置
```
<bean id="userDao" class="cc.wei.dao.impl.UserDaoImpl"/>
<bean id="userService" class="cc.wei.service.impl.UserServiceImpl">
    <property name="userDao" ref="userDao"/>
</bean>
```
* byName 根据set方法名称到容器中查询是否有相同名称的对象存在，如果有那么进行自动装配

# 六、静态代理
1. 代理：就是中介
2. 代理中有哪些对象：比如租房  
 ① 房东  
 ② 中介
 ③ 房客
 中介和房东具有相同功能
3. 使用代码来完成这个功能，可以抽象出以下几个对象：  
 ① 真实对象  
 ② 代理对象  
 ③ 抽象接口  
 ④ 调用接口  
4. 代码实现  
* 抽象接口
```
/*
 * 租房接口
 */
public interface Rent {
    public void rent();
}
```
* 真实对象
```
/*
 * 房东
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("出租房屋");
    }
}

```
* 代理对象
```
/*
 * 中介
 */
public class Proxy implements Rent {
    private Host host;
    @Override
    public void rent() {
        fare();
        host.rent();
    }
    public void fare() {
        System.out.println("收取中介费");
    }
    public void setHost(Host host) {
        this.host = host;
    }
}
```
* 客户端
```
public class Client {
    public static void main(String[] args) {
        Host host = new Host();
        Proxy proxy = new Proxy();
        proxy.setHost(host);
        //租房
        proxy.rent();
    }
}
```
代理描述了在不改变真实对象的情况下，增加了新的功能