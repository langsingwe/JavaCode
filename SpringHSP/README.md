资源共享地址：https://pan.baidu.com/s/1bpGVxgz, 提取密码 **m5fn**<br>
# 一、spring的快速入门案例
1. **spring是什么**  
struts 是 web 框架（jsp/action/actionform）  
hibernate 是 orm 框架，处于持久层  
spring 是容器框架，用于配置bean，并维护bean之间关系的框架  

> **spring 中有一个非常重要的概念：bean（是java中的任何一种对象 javabean/service/action/数据源/dao），IOC（控制反转），DI（依赖注入）**

![spring的层次图](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/springHSP/1.jpg)
# 快速开发
开发一个spring项目
1. 引入spring的开发包(最小配置spring.jar 该包把常用的jar都包括, 还要 写日志包 common-logging.jar  
2. 创建spring的一个核心文件 applicationContext.xml, [hibernate有核心 hibernate.cfg.xml struts核心文件 struts-config.xml], 该文件一般放在src目录下,该文件中引入 xsd文件 ：
可以从给出的案例中拷贝一份.  
3. 配置bean  
```
<!-- 在容器文件中配置bean（service/dao/domai/action/数据源） -->
<!-- bean元素的作用是，当我们的spring框架加载时候，spring就会自动创建一个bean对象，并放入内存
	UserService userService = new UserService();
	userService.setName("春春");
 -->
<bean id="userService" class="com.service.UserService">
	<!-- 这里就体现出注入的概念 -->
	<property name="name">
		<value>春春</value>
	</property>
</bean>
```
4. 在Test.java中。怎么使用  
```
//我们现在用使用spring来完成上面的任务
//1.得到spring的applicationContext对象（容器对象）
ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
UserService us = (UserService) ac.getBean("userService");
us.sayHello();
```

5. **细节讨论**  
**传统的方法和使用spring的方法区别**
* 使用spring ，没有new 对象,我们把创建对象的任务交给spring框架
* **spring的运行原理图**  
![spring的运行原理图](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/springHSP/2.jpg)  

* **我们再看spring**  
**对上面案例总结：**  
spring实际上是一个容器框架，可以配置各种bean，并且可以维护bean与bean的关系，当我们需要使用某个bean的时候，我们可以getBean(id),使用即可.  
**IOC是什么？**  
ioc(inverse of controll ) 控制反转: 所谓控制反转就是把创建对象(bean),和维护对象(bean)的关系的权利从程序中转移到spring的容器(applicationContext.xml),而程序本身不再维护.
**DI是什么**  
di(dependency injection) 依赖注入: 实际上di和ioc是同一个概念，spring设计者认为di更准确表示spring核心技术  

> 学框架，最重要的就是学习各个配置

# 二、开始spring
[点我查看参考代码文件夹](https://github.com/weiliangchun/JavaEE/tree/master/SpringHSP/src/com/hsp/interfaces)  

**spring提倡接口编程，配合DI技术可以达到层与层的解耦**
###### 举例说明
现在我们体验一下spring的DI配合接口编程，完成一个字母大小写转换的案例  
思路：  
1. 创建一个接口 ChangLetter  
2. 两个类实现这个接口  
3. 把对象配置到spring容器中  
4. 使用  

通过上面的案例，我们可以初步体会到DI配合接口编程，的确可以减少层（web层）和业务层的耦合度

# 装配bean
**从ApplicationContex 应用上下文容器中获取bean和从bean工厂容器中获取bean**  
结论：
1. 如果使用ApplicationContext ，则配置的bean如果是 singlton不管你用不用，都被实例化.(好处就是可以预先加载,缺点就是耗内存)
2. 如果是 BeanFactory ,则当你获取beanfacotry时候，配置的bean不会被马上实例化，当你使用的时候，才被实例(好处节约内存,缺点就是速度)
3. 规定: 一般没有特殊要求，应当使用ApplicatioContext完成(90%)

##### bean的scope细节

 作用域 | 描述
---|---
sigleton   | 在每个Spring IoC容器中一个bean定义对应一个对象实例。
prototype | 一个bean定义对应多个对象实例。
request | 在一次HTTP请求中，一个bean定义对应一个实例；即每次HTTP请求将会有各自的bean实例，它们依据某个bean定义创建而成。该作用域仅在基于web的Spring ApplicationContext情形下有效。
session | 在一个HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
global session | 在一个全局的HTTP Session中，一个bean定义对应一个实例。典型情况下，仅在使用portlet context的时候有效。该作用域仅在基于web的Spring ApplicationContext情形下有效。

入门案例：  
```
//获取两个student  
Student s1=(Student) ac.getBean("student");  
Student s2=(Student) ac.getBean("student");  
System.out.println(s1+" "+s2);
```

* request 
* session
* global-session  
是在web开发中才有意义

## 三种获取ApplicationContext对象引用的方法
1. ClassPathXmlApplicationContext -> 通过类路径
2. FileSystemXmlApplicationContext -> 通过文件路径
3. XmlWebApplicationContext  

# 三、bean的生命周期 ==(重要)==
[点我查看参考代码文件夹](https://github.com/weiliangchun/JavaEE/tree/master/SpringHSP/src/com/hsp/beanlife)  
1. 实例化（当我们的程序加载beans.xml文件），把我们的bean（前提是scope=singleton）实例化到内存中
2. 调用set方法设置属性，IOC注入
3. 如果这个bean实现了BeanNameAware接口，会调用它实现的setBeanName(String beanId)方法，此处传递的是Spring配置文件中bean的ID
4. 如果这个bean实现了BeanFactoryAware接口，会调用它实现的setBeanFactory(),传递是Spring工厂本身（可以用这个方法取到其他bean）
5. 如果这个bean实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文，该方式同样可以实现步骤4，但比4更好，因为ApplicationContext是BeanFactory的子接口，有更多的实现方法
6. 如果这个bean关联了BeanPostProcessor接口，将会调用postProcessBeforeInitialization(Object obj,String s)方法，BeanPostProcessor经常被用作是bean内容的更改，并且由于这个是在bean初始化结束时调用After方法，也可用于内存或缓存技术
7. 如果这个bean在spring配置文件中配置了init-method属性，会自动调用其配置的初始化方法
8. 如果这个bean关联了BeanPostProcessor接口，将会调用postAfterInitialization(Object obj,String s)方法
9. 使用bean
10. 关闭bean
11. 如果bean实现了DisposableBean接口，会调用其实现的destroy方法
12. 如果这个bean的spring配置了destroy-method属性，会自动调用其配置的销毁方法  

实际开发中，我们往往没用到这么多的过程，常见的是：  
实例化 => IOC注入 => AOP => 使用bean => 关闭bean 

# 配置bean的细节
## scope的说明：
 作用域 | 描述
---|---
sigleton   | 在每个Spring IoC容器中一个bean定义对应一个对象实例。
prototype | 一个bean定义对应多个对象实例。
request | 在一次HTTP请求中，一个bean定义对应一个实例；即每次HTTP请求将会有各自的bean实例，它们依据某个bean定义创建而成。该作用域仅在基于web的Spring ApplicationContext情形下有效。
session | 在一个HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
global session | 在一个全局的HTTP Session中，一个bean定义对应一个实例。典型情况下，仅在使用portlet context的时候有效。该作用域仅在基于web的Spring ApplicationContext情形下有效。

**尽量使用scope="singleton"，不要使用prototype，因为这样对性能影响较大**

# 四、如何给集合类型注入值
[点我查看参考代码文件夹](https://github.com/weiliangchun/JavaEE/tree/master/SpringHSP/src/com/hsp/collection)  
java中主要的集合有：map set list 数组  
list：有序 可以存储相同的对象
set：无序 不可以存储相同对象 会覆盖  
**Department类**
```
package com.hsp.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Department {
	
	private String name;
	private String[] empName;
	private List<Employee> empList;
	private Set<Employee> empSets;
	private Map<String,Employee> empMaps;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getEmpName() {
		return empName;
	}
	public void setEmpName(String[] empName) {
		this.empName = empName;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	public Set<Employee> getEmpSets() {
		return empSets;
	}
	public void setEmpSets(Set<Employee> empSets) {
		this.empSets = empSets;
	}
	public Map<String, Employee> getEmpMaps() {
		return empMaps;
	}
	public void setEmpMaps(Map<String, Employee> empMaps) {
		this.empMaps = empMaps;
	}
	
}

```

 **Employee类**
```
package com.hsp.collection;
public class Employee {
	private String name;
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
```
**beans.xml配置文件** 
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<bean id="department" class="com.hsp.collection.Department">
	<property name="name" value="财务部"/>
	<!-- 给数组注值 -->
	<property name="empName">
		<list>
			<value>小明</value>
			<value>小小明</value>
			<value>大明</value>
		</list>
	</property>
	<!-- 给List注入值 ,list可以有相同对象-->
	<property name="empList">
		<list>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
		</list>
	</property>
	<!-- 给set注入值 ，set不能有相同对象-->
	<property name="empSets">
		<set>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
			<ref bean="emp1"/>
			<ref bean="emp2"/>
		</set>
	</property>
	<!-- 给Map注入值,只要key不一样，就可以装配value -->
	<property name="empMaps">
		<map>
			<entry key="1" value-ref="emp1"/>
			<entry key="2" value-ref="emp2"/>
		</map>
	</property>
</bean>

<bean id="emp1" class="com.hsp.collection.Employee">
	<property name="name" value="北京"/>
	<property name="id" value="1"/>
</bean>
<bean id="emp2" class="com.hsp.collection.Employee">
	<property name="name" value="天津"/>
	<property name="id" value="2"/>
</bean>
</beans>

```

## 内部bean
```
<bean id="foo" class="...Foo">
    <property name="属性">
        <!-- 第一种方法引用 -->
        <ref bean="bean对象名" />
        <!-- 第二种内部bean -->
        <bean>
            <property name="属性">
                ...
            </property>
        </bean>
    </property>
</bean>
```
# 五、继承配置
[点我查看参考代码文件夹](https://github.com/weiliangchun/JavaEE/tree/master/SpringHSP/src/com/hsp/inherit)  
```
public class Student
public class Graduate extends Student
```
**在beans.xml中配置**
```
<!-- 配置一个学生对象 -->
<bean id="student" class="com.hsp.inherit.Student">
	<property name="name" value="小明"/>
	<property name="age" value="20"/>
</bean>
<!-- 配置Graduate对象 --> 
<bean id="graduate" parent="student" class="com.hsp.inherit.Graduate">
	<!-- 如果自己配置属性name,age,则会替换从父对象继承的数据 -->
	<property name="name" value="大明"/>
	<property name="degree" value="学士"/>
</bean>
```
# 六、通过构造函数注入值   
[点我查看参考代码文件夹](https://github.com/weiliangchun/JavaEE/tree/master/SpringHSP/src/com/hsp/constructor)  
**beans.xml关键代码**
```
<!-- 配置一个雇员对象 -->
<bean id="employee" class="com.hsp.constructor.Employee">
	<!-- 通过构造函数来注入属性值 -->
	<constructor-arg index="0" type="java.lang.String" value="大明"/>
	<constructor-arg index="1" type="int" value="23"/>
</bean>
```

> set注入的缺点是无法清晰表达哪些属性是必须的，哪些是可选的。  
构造注入的优势是通过构造强制依赖关系，不可能实例化不完全的或无法使用的bean

# 七、自动装配bean的属性值 ==（重点）==
[点我查看参考代码文件夹](https://github.com/weiliangchun/JavaEE/tree/master/SpringHSP/src/com/hsp/autowire)  

<html>
 <table>
    <tr>
        <th style="width:105px">模式</th>
        <th>说明</th>
    </tr>
     <tr>
        <td style="text-align:center">no</td>
        <td>不使用自动装配。必须通过ref元素指定依赖，这是默认设置。由于显式指定协作者可以使配置更灵活、更清晰，因此对于较大的部署配置，推荐采用该设置。而且在某种程度上，它也是系统架构的一种文档形式。</td>
    </tr>
    <tr>
        <td style="text-align:center">byName</td>
        <td>不使用自动装配不使用自动装配。必须通过ref元素指定依赖，这是默认设置。由于显式指定协作者可以使配置更灵活、更清晰，因此对于较大的部署配置，推荐采用该设置。而且在某种程度上，它也是系统架构的一种文档形式。</td>
    </tr>
    <tr>
        <td style="text-align:center">byType</td>
        <td>如果容器中存在一个与指定属性类型相同的bean，那么将与该属性自动装配。如果存在多个该类型的bean，那么将会抛出异常，并指出不能使用byType方式进行自动装配。若没有找到相匹配的bean，则什么事都不发生，属性也不会被设置。如果你不希望这样，那么可以通过设置dependency-check="objects"让Spring抛出异常。</td>
    </tr>
    <tr>
        <td style="text-align:center">constructor</td>
        <td>与byType的方式类似，不同之处在于它应用于构造器参数。如果在容器中没有找到与构造器参数类型一致的bean，那么将会抛出异常。</td>
    </tr>
    <tr>
        <td style="text-align:center">autodetect</td>
        <td>通过bean类的自省机制（introspection）来决定是使用constructor还是byType方式进行自动装配。如果发现默认的构造器，那么将使用byType方式。</td>
    </tr>
 </table>
</html>

1. **byName的用法**
```
<!-- 配置一个master对象 -->
<bean id="master" class="com.hsp.autowire.Master" autowire="byName">
	<property name="name">
		<value>小明</value>
	</property>
</bean>
<!-- 配置一个dog对象 -->
<bean id="dog" class="com.hsp.autowire.Dog">
	<property name="name" value="大黄"/>
	<property name="age" value="3"/>
</bean>
```
2. **byType**  
找和属性类型相同的bean,找不到,装不上,找到多个抛异常  

3. **constructor**  
查找和bean的构造参数一致的一个或多个bean，若找不到或找到多个，抛异常。按照参数的类型装配  

4. **autodetect**  
(3)和(2)之间选一个方式。不确定性的处理与(3)和(2)一致。  

5. **defualt**  

==这个需要在<beans defualt-autorwire=“指定” />==  
当你在<beans >指定了 default-atuowrite后， 所有的bean的默认的autowire就是 指定的装配方法;  
如果没有在<beans defualt-autorwire=“指定” /> 没有  defualt-autorwire=“指定” ，则默认是
defualt-autorwire=”no”

> autowire默认值是default。  
default-autowire默认值是no

6. **no:不自动装配**  
使用spring的特殊bean,完成分散配置:

## 使用spring的特殊bean,完成分散配置
**beans.xml**  
> 说明:当通过 context:property-placeholder 引入多个properties文件时，要用逗号隔开

```
<!-- 引入我们的db.properties文件 -->			
<context:property-placeholder location="classpath:com/hsp/dispatch/db.properties,classpath:com/hsp/dispatch/db2.properties"/>  
<!-- 配置-DBUtil对象 -->
<bean id="dbutil1" class="com.hsp.dispatch.DBUtil">
	<property name="name" value="${name}" />
	<property name="driver" value="${driver}" />
	<property name="url" value="${url}" />
	<property name="pwd" value="${pwd}" />
</bean>
<!-- 配置-DBUtil对象 -->
<bean id="dbutil2" class="com.hsp.dispatch.DBUtil">
	<property name="name" value="${db2.name}" />
	<property name="driver" value="${db2.driver}" />
	<property name="url" value="${db2.url}" />
	<property name="pwd" value="${db2.pwd}" />
</bean>
```
**db.properties**
```
name=scott
driver=oracle:jdbc:driver:OraclaDriver
url=jdbc:oracle:@localhost:3306
pwd=123456

```
# 八、AOP编程
[点我查看参考代码文件夹](https://github.com/weiliangchun/JavaEE/tree/master/SpringHSP/src/com/hsp/aop)  
##### aop(aspect oriented programming):面向切面编程，是对所有对象或者是一类对象编程
##### 核心：在==不==增加代码的基础上，==还==增加新的功能

* 汇编(伪机器指令 mov jump) 面向机器  
* c语言(面向过程  )->系统软件(操作系统，数据库, 杀毒软件，防火墙,驱动..)  
```
语句1；
语句2；
...
```
* java语法(面向对象->类-对象)  
```
Class Dog {
    属性;->变量
	行为->函数
}

```
* 面向切面 spring( ->aop) 面向n多对象编程  
aop特别提醒: aop编程，实际上在开发框架本身用的多,在实际项目中，用的不是很多,但是将来会越来越多，这个一个趋势.

## AOP原理 + 案例
##### 步骤
1. 定义接口
2. 编写对象（被代理对象 = 目标对象）
3. 编写通知（前置通知目标方法调用前调用）
4. 在beans.xml文件配置
 * 配置 被代理对象 = 目标对象
 * 配置通知
 * 配置代理对象 是ProxyFactoryBean的对象实例  
   ① \<\!\-\- 代理接口集 \-\-\>  
   ② 织入通知  
   ③ 配置被代理对象  
后面还有好后置通知、环绕通知、异常通知、引入通知

> **spring的aop中，当你通过代理对象去实现aop的时候，获取的ProxyFactoryBean是什么类型？**  
返回的是一个代理对象,如果目标对象实现了接口，则spring使用jdk 动态代理技术,如果目标对象没有实现接口，则spring使用CGLIB技术.



