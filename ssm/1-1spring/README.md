[TOC]

SSM学习路径分为4个阶段
* 战斗伊始——Spring
* 转战阵地——SpringMVC
* 再下一城——MyBatis
* 终极目标——整合案例

<font size=6><center>**战斗伊始——Spring**</center></font>

# 1-1 Spring入门篇

## 专题一、IOC

### 接口及面向接口编程

**接口**
* 用于沟通的中介物的抽象化
* 实体把自己提供给外界的一种抽象化说明，用以由内部操作分离出外部沟通方法，使其能被修改内部而不影响外界其他实体与其交互的方式
* 对应java，接口即声明，声明了哪些方法是对外公开的
* 在java8中，接口可以拥有方法体

**面向接口编程**
* 结构设计中，分清层次及调用关系，每层只向外（上层）提供一组功能接口，各层间仅依赖接口而非实现类
* 接口实现的变动不影响各层间的调用，这一点在公共服务上中尤为重要
* “面向接口编程” 中的 “接口” 是用于隐藏具体实现和实现多态性的组件

**例子:**  
定义一个接口
```
public interface OneInterface {
	String hello(String str);
}
```
定义一个接口实现类
```
public class OneInterfaceImpl implements OneInterface {
	public String hello(String str) {
		return "word from interface OneInterface:" + str;
	}
}
```
测试类
```
public class App {
	public static void main(String[] args) {
		OneInterface oif = new OneInterfaceImpl();
		System.out.println(oif.hello("world"));
	}
}
```
结果:  
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/ssm/01interface.jpg)


### 什么是IOC
* **IOC:控制反转** 控制权的转移，应用程序本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护
* **DI（依赖注入）是其一种实现方式**
* **目的:** 创建对象并且组装对象之间的关系  

![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/ssm/02container.jpg)
 

### Spring的Bean配置
* 刚才的接口在Spring中的配置方式
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="oneInterface" class="com.spring.ioc.interfaces.OneInterfaceImpl">
    
    </bean>
</beans>

```

### Bean的初始化
* **基础：两个包**  
\- `org.springframework.beans`  
\- `org.springframework.context`  
\- `BeanFactory`提供配置结构和基本功能，加载并初始化Bean  
\- `ApplicationContext`保存了Bean对象并在Spring中被广泛使用
* **方式，ApplicationContext**
\- 本地文件
\- ClassPath
\- Web应用中依赖servlet或Listener
* **文件**
```
FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("F:/workspace/appcontext.xml");
```
* **ClassPath**
```
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
```

* **Web应用**
```
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
    <servlet-name>context</servlet-name>
    <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
```

### Spring的常用注入方式
* **Spring注入是指在启动Spring容器加载bean配置的时候，完成对变量的赋值行为**
* **常用的两种注入方式**  
\- 设值注入  
\- 构造注入  

* **例子**  
```
<!-- 通过设值注入 -->
<bean id="injectionService" class="com.spring.ioc.injection.service.InjectionServiceImpl">
    <property name="injectionDao" ref="injectionDao"/>
</bean>
<!-- 通过构造器注入 -->
<bean id="injectionService" class="com.spring.ioc.injection.service.InjectionServiceImpl">
    <constructor-arg name="injectionDao" ref="injectionDao"/>
</bean>
<bean id="injectionDao" class="com.spring.ioc.injection.dao.InjectionDaoImpl"></bean>
 
```

## 专题二、Bean
### Bean配置项
id,class,scope,constructor-arguments,properties,autowiring-mode,lazy-initialization mode,initialization/destruction method

### Bean的作用域
* singleton:单例，指在一个bean容器中只存在一份
* prototype:每次请求（每次使用）创建新的实例，destroy方式不生效
* request:每次http请求创建一个实例且仅在当前request内有效
* session:同上，每次http请求创建，当前session内有效
* global session:基于portlet的web中有效（portlet定义了global session），如果是在web中，同session
* **例子(singleton与prototype)**   
1. 测试singleton  

```
//定义一个类
package com.spring.bean;

public class BeanScope {
    public void say() {
        System.out.println("BeanScope say: " + this.hashCode());
    }
}

```

```
//配置文件
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="beanScope" class="com.spring.bean.BeanScope" scope="singleton">
    	
    </bean>
</beans>
```

```
//测试方法
@Test
public void testSay() {
    BeanScope beanScope = super.getBean("beanScope");
    beanScope.say();
    
    BeanScope beanScope2 = super.getBean("beanScope");
    beanScope2.say();
}
```

```
//测试结果
BeanScope say: 1558712965
BeanScope say: 1558712965
```
2. 测试prototype
```
//修改配置文件.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="beanScope" class="com.spring.bean.BeanScope" scope="prototype">
    	
    </bean>
</beans>
```

```
//执行测试方法结果
BeanScope say: 828441346
BeanScope say: 1899073220
```

### Bean的生命周期
* **生命周期**  
\- 定义  
\- 初始化  
\- 使用  
\- 销毁  
* **初始化**  

\- 实现`org.springframework.beans.factory.InitializingBean`接口，覆盖`afterPropertiesSet`方法  
```
public class ExampleInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        //do something
    }
}
```
\- 配置`init-method`
```
<bean id="exampleInitBean" class="examples.ExampleBean" init-method="init"/>
public class ExampleBean {
    public void init() {
        //do some intialization work
    }
}

```
* **销毁**

\- 实现`org.springframework.beans.factory.DisposableBean`接口,覆盖`destroy`方法
```
public class ExampleInitializingBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        //do something
    }
}
```

\- 配置`destroy-method`
```
<bean id="exampleDestroyBean" class="examples.ExampleBean" init-method="cleanup"/>
public class ExampleBean {
    public void cleanup() {
        //do some destruction work(like releasing pooled connections)
    }
}
```

* **配置全局默认初始化、销毁方法**
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
    default-init-method="init" default-destroy-method="destroy">
    
</beans>
```

* **例子**

> 关于bean初始化和销毁同时使用的注意情况：  
1->默认全局的初始化和销毁方法`default-init-method="init" default-destroy-method="destroy"`  
2->实现接口的初始化和销毁方法`implements InitializingBean,DisposableBean`  
3->配置文件中配置初始化和销毁方法`init-method="init" destroy-method="destroy`  
 这三个方法同时使用时，1默认的则不执行，而23两种都会执行，并且是2实现接口的方式先于配置中3的执行。  
 1默认的全局初始化和销毁方法可以有可以没有，有没有对配置都没有太大影响，当然如果一个bean没有采取23初始化销毁方法，而有1默认的方法的话，这两个方法还是会执行的。即使没有，系统也不会报错。

### Aware
* Spring中提供了一些以Aware结尾的接口，实现了Aware接口的bean在被初始化之后，可以获取相应资源
* 通过Aware接口，可以对Spring相应资源进行操作（一定要慎重）
* 为对Spring进行简单的扩展提供了方便的入口
* **例子**  
com/spring/aware


### Bean的自动装配(Autowiring)
* **NO:** 不做任何操作
* **byName:** 根据属性名自动装配。此选项将检查容器并根据名字查找与属性完全一致的bean，并将其与属性自动装配
* **byType:** 如果容器中存在一个与指定属性类型相同的bean，那么将与该属性自动装配；如果存在多个该类型bean，那么抛出异常，并指出不能使用byType方式进行自动装配；如果没有找到相匹配的bean，则什么事都不发生
* **Constructor:** 与byType方式类似，不同之处在于它应用于构造器参数，如果容器中没有找到与构造器参数类型一致的bean，那么抛出异常
* **例子**  
com/spring/autowiring

### Resources
* 针对于资源文件的统一接口
* Resources
1. UrlResource:URL对应的资源，根据一个URL地址即可构建
2. ClassPathResource:获取类路径下的资源文件
3. FileSystemResource:获取文件系统里面的资源
4. ServletContextResource:ServletContext封装的资源，用于访问ServletContext环境下的资源
5. InputStreamResource:针对于输入流封装的资源
6. ByteArrayResource:针对于字节数组封装的资源
* **例子**
```
//MyResource.java
package com.spring.resource;

public class MyResource implements ApplicationContextAware{

    private ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	this.applicationContext = applicationContext;
    }
    
    public void resource() throws IOException {
        //Resource resource = applicationContext.getResource("classpath:config.txt");
        //Resource resource = applicationContext.getResource("file:F:\\eclipse_workspace\\ssm\\01spring\\src\\main\\resources\\config.txt");
        //Resource resource = applicationContext.getResource("url:https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle");
        Resource resource = applicationContext.getResource("config.txt");
        System.out.println(resource.getFilename());
        System.out.println(resource.contentLength());
	}
}	
```
```
//测试方法
@Test
public void testResource() {
    MyResource resource = super.getBean("myResource");
    try {
    	resource.resource();
    } catch (IOException e) {
    	e.printStackTrace();
    }
}
```

### Bean管理的注解实现及例子
* ClassPath扫描与组件管理
* 类的自动检测与注册Bean
* <context:annotation-config/>
* @Component,@Respository,@Service,@Controller
* @Required
* @Autowired
* Qualifier
* Resource

#### ClassPath扫描与组件管理
* 从Spring3.0开始，Spring JavaConfig项目提供了很多特性，包括使用java而不是XML定义bean，比如@Configuration,@Bean,@Import,@DependsOn
* @Component是一个通用注解，可用于任何bean
* @Responsitory,@Service,@Controller是更有针对性的注解
1. @Responsitory通常用于注解DAO类，即持久层
2. @Service通常用于注解Service类，即服务层
3. @Controller通常用于注解Controller类，即控制层(MVC)

#### 元注解(Meta-annotations)
* 许多Spring提供的注解可以作为自己的代码，即“元数据注解”，元注解是一个简单的注解，可以应用到另一个注解
```
@Target({ElementType.Type})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component //Spring will see this and treate @Service in the same way as @Component
public @interface Service {
    //....
}
```
* 除了value(),元注解还可以有其它的属性，允许定制
```
@Target({ElementType.Type})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope("session")
public @interface SessionScope {
    ScopedProxyMode proxyMode() default ScopedProxyMode.DEFAULT
}
```

#### 类的自动检测及Bean的注册
* Spring可以自动检测类并注册Bean到ApplicationContext中
```
@Service
public class SimpleMovieLister {
    private MovieFinder movieFinder;
    @Autowired
    public SimpleMovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
```
```
@Respository
public class JpaMovieFinder implements MovieFinder {
    // implemention elided for clarity
}
```
* 为了能够检测这些类并注册相应的Bean，需要下面的内容
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="org.example" />
</beans>
```
* <context:component-scan>包含<context:annotation-config>，通常在使用前者后，不再使用后者
* AutowiredAnnotationBeanPostProcessor和CommonAnnotationBeanPostProcessor也会被包含进来


#### <context:annotation-config>

* 通过在基于XML的Spring配置如下标签（请注意包含上下文命名空间）
* <context:annotation-config/>仅会查找在同一个applicationContext中的bean注解
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config />
</beans>
```

#### 使用过滤器进行自定义扫描
* 默认情况下，类被自动发型并注册bean的条件是：使用@Component,@Repository,@Service,@Controller注解或者使用@Component的自定义注解
* 可以通过过滤器修改上面的行为，如：下面例子的XML配置忽略所有的@Repository"Stub"代替
```
<beans>
    <context:component-scan base-package="org.example">
        <context:include-filter type="regex" expression=".*Stub.*Repository"/>
        <context:exclude-filter type="annotation" expression="org.springframework.stereptype.Repository"/>
    </context:component-scan>
</beans>
```
* 还可使用user-default-filters="false"禁用自动发现与注册

#### 定义Bean
* 扫描过程中组件被自动检测，那么Bean名称是由BeanNameGenerator生成的（@Component，@Repository,@Service,@Controller都会有个name属性用于显示设置Bean Name）
```
@Service("myMovieLister")
public class SimpleMovieLister {
    //...
}
```
```
@Repository
public class MovieFinderImpl implements MovieFinder {
    //...
}
```
* 可自定义bean命名策略，实现BeanNameGenerator接口，并一定要包含一个无参数构造器
```
<beans>
    <context:component-scan base-package="org.example" name-generator="org.example.MyNameGenerator"/>
</beans>
```
#### 作用域(Scope)
* 通常情况下自动查找的Spring组件，其scope是singleton,Spring2.5提供了一个标识scope的注解@Scope
```
@Scope("prototype")
@Repository
public class MovieFinderImpl implements MovieFinder {
    //...
}
```
* 也可以自定义scope策略，实现ScopeMetadataResolver接口并提供一个无参构造器
```
<beans>
    <context:component-scan base-package="org.example" scope-resolver="org.example.MyScopeResolver"/>
</beans>
```
#### 代理方式
* 可以使用scoped-proxy属性指定代理,有三个值可选：no,interfaces,targetClass
```
<beans>
    <context:component-scan base-package="org.example" scoped-proxy="interfaces"/>
</beans>
```

#### Bean的定义及作用域
* **例子**
```
//定义类添加注解
package com.spring.beanannotation;
//@Component("bean")
@Scope
@Component
public class BeanAnnotation {
	public void say(String str) {
		System.out.println("BeanAnnotation:"+str);
	}
	
	public void myHashCode() {
		System.out.println("BeanAnnotation:"+this.hashCode());
	}
}
```
```
//配置文件
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">
    
    <context:component-scan base-package="com.spring.beanannotation"></context:component-scan>
</beans>
```
```
//测试方法
@Test
public void testSay() {
    BeanAnnotation bean = super.getBean("beanAnnotation");
    bean.say("this is test");
}

@Test
public void testScope() {
    BeanAnnotation bean = super.getBean("beanAnnotation");
    bean.myHashCode();
    
    bean = super.getBean("beanAnnotation");
    bean.myHashCode();
}
```
#### @Required
* @Required注解适用于bean属性的setter方法
* 这个注解仅仅表示，受影响的bean属性必须在配置时被填充，通过在bean定义或通过自动装配一个明确的属性值
```
public class SimpleMovieLister {
    private MovieFinder movieFinder;
    @Required
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
    //...
}
```
#### Autowired
* 可以将@Autowired注解为“传统”的setter方法
```
private MovieFinder movieFinder;
@Autowired
public void setMovieFinder(MovieFinder movieFinder) {
    this.movieFinder = movieFinder;
}
```
* 可用于构造器或成员变量
```
@Autowired
private MovieCatalog movieCatalog;

private CustomerPreferenceDao customerPreferenceDao;

@Autowired
public MovieRecommender(CustomerPreferenceDao customerPreferenceDao) {
    this.customerPreferenceDao = customerPreferenceDao;
}
```
* 默认情况下，如果因找不到合适的bean将会导致autowiring失败抛出异常，可以通过下面的方式避免
```
public class SimpleMovieLister {
    private MovieFinder movieFinder;
    @Autowired(required=false)
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
```
* 每个类只能有一个构造器被标记为required=true
* @Autowired的必要属性，建议使用@Required注解
* **例子**
```
//DAO接口
package com.spring.beanannotation.injection.dao;

public interface InjectionDao {
	public void save(String str);
}
```

```
//DAO接口实现类
package com.spring.beanannotation.injection.dao;

import org.springframework.stereotype.Repository;

@Repository
public class InjectionDaoImpl implements InjectionDao {

	public void save(String str) {
		//模拟数据库保存操作
		System.out.println("保存数据:" + str);
	}
}
```

```
//Service接口
package com.spring.beanannotation.injection.service;

public interface InjectionService {
	public void save(String str);
}
```

```
//ServiceJi接口实现类
package com.spring.beanannotation.injection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.beanannotation.injection.dao.InjectionDao;


@Service
public class InjectionServiceImpl implements InjectionService {
	@Autowired
	private InjectionDao injectionDao;
	
	@Autowired
	public InjectionServiceImpl(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}
	
//	@Autowired
	public void setInjectionDao(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}

	public void save(String str) {
		//模拟业务操作
		System.out.println("Service接收参数:" + str);
		str = str + ":" + this.hashCode();
		injectionDao.save(str);
	}
}

```

```
//配置文件spring-beanannotation.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">
    
    <context:component-scan base-package="com.spring.beanannotation"></context:component-scan>
</beans>
```

```
//测试方法
@Test
public void testAutowired() {
    InjectionService service = super.getBean("injectionServiceImpl");
    service.save("This is autowired.");
}
```
```
//测试结果
Service接收参数:This is autowired.
保存数据:This is autowired.:928466577
```
* 可以使用@Autowired注解那些众所周知的解析依赖性接口，比如：
BeanFactory,ApplicationContext,Environment,ResourceLoader,ApplicationEventPublisher,and MessageSource
```
public class MovieRecommender {
    @Autowired
    private ApplicationContext context;
    public  MovieRecommender() {
        
    }
    //...
}
```
* 可以通过添加注解给需要该类型的数组的字段或方法，以提供ApplicationContext中的所有特定类型的bean
```
private Set<MovieCatalog> movieCatalogs;
@Autowired
public void setMovieCatalogs(Set<MovieCatalog> movieCatalogs) {
    this.movieCatalogs = movieCatalogs;
}
```
* 可以用于装配key为String的Map
```
private Map<String,MovieCatalog> movieCatalogs;
@Autowired
public void setMovieCatalogs(Map<String,MovieCatalog> movieCatalogs) {
    this.movieCatalogs = movieCatalogs;
}
```
* 如果希望数组有序，可以让bean实现`org.springframework.core.Ordered`接口或使用`@Order`注解
* `@Autowired`是由`Spring BeanPostProcessor`处理的，所以不能在自己的`BeanPostProcessor`或`BeanFactoryPostProcessor`类型应用这些注解，这些类型必须通过XML或者Spring的`@Bean`注解加载

#### 数组及Map的自动注入
* **例子**
```
//定义接口
package com.spring.beanannotation.multibean;

public interface BeanInterface {

}
```
```
//接口的一个实现类
package com.spring.beanannotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class BeanImplOne implements BeanInterface {

}
```
```
//接口的另一个实现类
package com.spring.beanannotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class BeanImplTwo implements BeanInterface {

}
```
```
//调用的类
package com.spring.beanannotation.multibean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanInvoker {
    @Autowired
    private List<BeanInterface> list;
    
    @Autowired
    private Map<String,BeanInterface> map;
    
    public void say() {
        if(list!=null&&list.size()!=0) {
            System.out.println("---------------list---------------");
            for(BeanInterface bean : list) {
            	System.out.println(bean.getClass().getName());
            }
        } else {
            System.out.println("List<BeanInterface> list is null !!!!!");
        }
        
        if(map!=null&&map.size()!=0) {
            System.out.println("---------------map---------------");
            for(Map.Entry<String, BeanInterface> entry : map.entrySet()) {
                System.out.println(entry.getKey()+"   "+entry.getValue().getClass().getName());
        	}
        }else {
            System.out.println("Map<String,BeanInterface> map is null !!!!!");
        }
    }
}
```
```
//测试方法
@Test
public void testMultiBean() {
    BeanInvoker invoker = super.getBean("beanInvoker");
    invoker.say();
}
```
```
//结果
---------------list---------------
com.spring.beanannotation.multibean.BeanImplTwo
com.spring.beanannotation.multibean.BeanImplOne
---------------map---------------
beanImplOne   com.spring.beanannotation.multibean.BeanImplOne
beanImplTwo   com.spring.beanannotation.multibean.BeanImplTwo
```

#### @Qualifier
* 按类型自动装配可能多个bean实例的情况，可以使用Spring的@Qualifier注解缩小范围(或指定唯一)，也可以用于指定单独的构造器参数或方法参数
* 可用于注解集合类型变量
* 如果通过名字进行注解注入，主要使用的不是@Autowired(即使在技术上能够通过@Qulifier指定bean的名字),替代方式是使用JSR-250@Resource注解，它是通过其独特的名称来定义来识别特定的目标（这是一个与所声明的类型是无关的匹配过程）
* 因语义差异，集合或Map类型的bean无法通过@Autowired来注入，因为没有类型匹配到这样的bean，为这些使用@Resource注解，通过唯一名称引用集合或Map的bean
* @Autowired适用于fields,constructors,multi-argument methods这些允许在参数级别使用@Qualifier注解缩小范围的情况
* @Resource适用于成员变量，只有一个参数的setter方法，所以在目标是构造器或一个多参数方法时，最好的方式是使用qulifier
* 定义自己的qulifier注解并使用
```
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Genre {
    String value();
}
```
```
public class MovieRecommender {
    @Autowired
    @Genre("Action")
    private MovieCatalog actionCatalog;
    private MovieCatalog comedyCatalog;
    
    @Autowired
    public void setComedyCatalog(@Genre("Comedy") MovieCatalog comedyCatalog) {
        this.comedyCatalog = comedyCatalog;
    }
    //...
}
```
* **例子**

#### 基于java的容器注解
* @Bean标识一个用于配置和初始化一个由SpringIOC容器管理的新对象的方法，类似于XML配置文件的<bean/>
* 可以在Spring的@Component注解的类中使用@Bean注解任何方法（仅仅是可以）
* 上一点中，通常使用的是@Configuration
```
@Configurtion
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```
#### @Bean
* 自定义Bean name
```
@Configuration
public class AppConfig {
    @Bean(name="myFoo")
    public Foo foo() {
        return new Foo();
    }
}
```
* init-method
* destroy-method
```
public class Foo {
    public void init() {
        // initialization logic
    }
}
public class Bar {
    public void cleanup() {
        // destruction logic
    }
}

@Configuration
public class AppConfig {
    @Bean(initMethod="init")
    public Foo foo() {
        return new Foo();
    }
    @Bean(destroyMethod="cleanup")
    public Bar bar() {
        return new Bar();
    }
}
```
#### @ImportResource和@Value
* **例子**
```
//config.properties
url=127.0.0.1:3306
password=123456
jdbc.username=root
```
```
<!--config.xml-->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">
	
    <context:property-placeholder location="classpath:/config.properties"/>
</beans>
```
```
//MyDriverManager.java
package com.spring.beanannotation.javabased;

public class MyDriverManager {
    
    public MyDriverManager(String url,String username,String password) {
        System.out.println("url:"+url);
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
```
```
StoreConfig.java
package com.spring.beanannotation.javabased;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:config.xml")
public class StoreConfig {
	
    @Value("${url}")
    private String url;
    
    @Value("${jdbc.username}")
    private String username;
    
    @Value("${password}")
    private String password;
    
    @Bean
    public MyDriverManager myDriverManager() {
    	return new MyDriverManager(url,username,password);
    }
}
```
```
//测试方法
@Test
public void testMyDriverManager() {
    MyDriverManager manager = super.getBean("myDriverManager");
    System.out.println(manager.getClass().getName());
}
```
```
//结果
url:127.0.0.1:3306
username:root
password:123456
```
> 注意：这里config.properties里的username要写成jdbc.username，不然会默认username的值为当前计算机的用户名，如Administrator

#### @Bean and @Scope
* 默认@Bean是单例的

## 专题三、AOP
### 什么是AOP
* AOP:Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术
* 主要的功能是：日志记录，性能统计，安全控制，事务处理，异常处理等

### 切面
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/ssm/03qiemian.jpg)

### AOP实现方式
* **预编译**  
 \- AspectJ
* **运行期动态代理（JDK动态代理、CGLib动态代理）**  
 \- SpringAOP、JbossAOP

### AOP几个相关概念

名称 | 说明
---|---
切面(Aspect) | 一个关注点的模块化，这个关注点可能会横切多个对象
连接点(Joinpoint) | 程序执行过程中的某个特定的点
通知(Advice) | 在切面的某个特定的连接点上执行的动作
切入点(Pointcut) | 匹配连接点的断言，在AOP中通知和一个切入点表达式关联
引入(Intorduction) | 在不修改类代码的前提下，为类添加新的方法和属性
目标对象(Target Object) | 被一个或者多个切面所通知的对象
AOP代理(AOP Proxy) | AOP框架创建的对象，用来实现切面契约(aspect contract)(包括通知方法执行等功能)
织入(Weaving) | 把切面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象，分为：编译时织入、类加载时织入、执行时织入 

### Advice的类型
名称 | 说明
---|---
前置通知(Before Advice) | 在某连接点(Joinpoint)之前执行的通知，但不能阻止连接点前的执行(除非它抛出一个异常)
返回后通知(After returning advice) | 在连接点(Joinpoint)正常完成后执行的通知
抛出异常后通知(After throwing advice) | 在方法抛出异常退出时执行的通知
后通知(After(finally)advice) | 当某连接点退出的时候执行的通知(不论是正常返回还是异常退出)
环绕通知(Around Advice) | 包围一个连接点(Joinpoint)的通知

### Spring框架中AOP的用途
* 提供了声明式的企业服务，特别是EJB的替代服务的声明
* 允许用户定制自己的方面，以完成OOP与AOP的互补使用

### Sprign的AOP实现
* 纯java实现，无需特殊的编译，不需要控制类加载器层次
* 目前只支持方法执行连接点（通知Spring Bean的方法执行）
* 不是为了提供最完整的AOP实现（尽管它非常强大）；而是侧重于一种AOP实现和Spring IOC容器之间的整合，用于帮助企业应用中的常见问题
* Spring AOP不会与AspectJ竞争，从而提供综合全面的AOP解决方案

### 有接口和无接口的Spring AOP实现区别
* Spring AOP默认使用标准的javaSE动态代理作为AOP代理，这使得任何接口（或者接口集）都可以被代理
* Spring AOP中也可以使用CGLIB代理（如果一个业务对象并没有实现一个接口）

### Scheme —— based AOP
Spring所有的切面和通知器都必须放在一个`<aop:config>`内（可以配置包含多个`<aop:config>`元素），每一个`<aop:config>`可以包含pointcut,advisor和aspect元素(它们必须按照这个顺序进行声明)

`<aop:config>`风格的配置大量使用了Spring的自动代理机制

#### aspect
* <aop:config>
* <aop:aspect>
```
<aop:config>
    <aop:aspect id="myAspect" ref="aBean">
        ...
    </aop:aspect>   
</aop:config>
<bean id="aBean" class="..."> 
    ...
</bean>
```
#### pointcut
* `execution(public * *(..))` 切入点为执行所有public方法时
* `execution(* set*(..))` 切入点为执行所有set开始的方法时
* `execution(* com.xyz.service.AccountService.*(..))` 切入点为执行AccountService类中的所有方法时
* `execution(* com.xyz.service..(..))` 切入点为执行com.xyz.service包下的所有方法时
* `execution(* com.xyz.service...(..))` 切入点为执行com.xyz.service包及其子包下的所有方法时
* `within(com.xyz.service.*)`(only in Srping AOP)
* `within(com.xyz.service..*)`(only in Spring AOP) within用于匹配指定类型内的方法执行
* `this(com.xyz.service.AccountService)`(only in Spring AOP) this 用于匹配当前AOP代理对象类型的执行方法
* `target(com.xyz.service.AccountService)` (only in Spring AOP) target用于匹配当前目标对象类型的执行方法
* `args(java.io.Serializable)` (only in Spring AOP)
* `@target(org.springframework.transaction.annotation.Transactional)` (only in Spring AOP)
* `@within(org.springframework.transaction.annotation.Transactional)` (only in Spring AOP)
* `@annotation(org.springframework.transaction.annotation.Transactional)` (only in Spring AOP)
* `@args(com.xyz.security.Classified)` (only in Spring AOP)
* `bean(tradeService)` (only in Spring AOP)
* `bean(*Service)` (only in Spring AOP)

#### advice
* Before advice
```
<aop:aspect id="beforeExample" ref="aBean">
    <aop:before pointcut-ref="dataAccessOperation" method="doAccessCheck" />
    ...
</aop:aspect>
```
```
<aop:aspect id="beforeExample" ref="aBean">
    <aop:before pointcut="execution(* com.xyz.myapp.dao..(..))" method="doAccessCheck" />
    ...
</aop:aspect>
```
* After returning advice
```
<aop:aspect id="afterReturningExample" ref="aBean">
    <aop:after-returning pointcut-ref="dataAccessOperation" method="doAccessCheck" />
    ...
</aop:aspect>
```
```
<aop:aspect id="afterReturningExample" ref="aBean">
    <aop:after-returning pointcut-ref="dataAccessOperation" returning="retVal" method="doAccessCheck" />
    ...
</aop:aspect>
```
* After throwing advice
```
<aop:aspect id="afterThrowi" ref="aBean">
    <aop:after-throwing pointcut-ref="dataAccessOperation" method="doAccessCheck" />
    ...
</aop:aspect>
```
* 使用throwing属性来指定可被传递的异常的参数名称
```
<aop:aspect id="afterReturningExample" ref="aBean">
    <aop:after-throwing pointcut-ref="dataAccessOperation" method="doAccessCheck" />
    ...
</aop:aspect>
```
* Around advice
* 通知方法的第一个参数必须是ProceedingJoinPoint类型
```
<aop:aspect id="aroundExample" ref="aBean">
    <aop:around pointcut-ref="businessService" method="doBasicProfiling"/>
    ...
</aop:aspect>
```
```
public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
    //start stopwatch
    Object retVal = pjp.proceed();
    //stop stopwatch
    return retVal;
}
```
* Advice parameters
```
public interface FooService {
    Foo getFoo(String fooName, int age);
}
public class DefaultFooService implements FooService {
    public Foo getFoo(String name, int age) {
        return new Foo(name, age);
    }
}
```
```
public class SimpleProfiler {
    public Obje ct profile(ProceedingJoinPoint call, String name, int age) thorws Throwable {
        StopWatch clock = new StopWatch("Profiling for "+name+" and"+age+"" );
        try {
            clock.start(call.toShortString());
            return call.proceed();
        } finally {
            clock.stop();
            System.out.println(clock.prettyPrint());
        }
    }
}
```
```
<!-- this is the object that will be proxied by Spring's AOP infrastructure -->
<bean id="fooService" class="x.y.service.DefaultFooService"/>
<aop:config>
    <aop:aspect ref="profiler">
        <aop:pointcut id="theExecutionOfSomeFooServiceMethod" expression="execution(* x.y.service.FooService.getFoo(String ,int)) and args(name, age)"/>
        <aop:around pointcut-ref="theExecutionOfSomeFooServiceMethod" method="profile"/>
    </aop:aspect>
</aop:config>
```
#### Introductions
* 简介：允许一个切面声明一个实现指定接口的通知对象，并且提供了一个接口实现类来代表这些对象
* 由<aop:aspect>中的<aop:declare-parents>元素声明该元素用于声明所匹配的类型拥有一个新的parent(因此得名)
```
<aop:aspect id="usageTrackerAspect" ref="usageTracking">
    <aop:declare-parents types-matching="com.xyz.myapp.service.*+" implement-interface="com.xyz.myapp.service.tracking.UsageTracked" default-impl="com.xyz.myapp.service.tracking.DefaultUsageTracked"/>
    <aop:before pointcut="com.xyz.myapp.SystemArchitecture.businessService() and this(usageTracked)" method="recordUsage"/>
    
</aop:aspect>
```
```
public void recordUsage(UsageTracked usageTracked) {
    usageTracked.incrementUseCount();
}
```
```
UsageTracked usageTracked = (UsageTracked) context.getBean("myService");
```
### Spring AOP API

#### ProxyFactoryBean
* 创建Spring AOP代理的基本方法是使用`org.springframework.aop.framework.ProxyFactoryBean`
* 这可以完全控制切入点和通知(advice)以及它们的顺序
* 使用ProxyFactoryBean或者其他IOC相关类来创建AOP代理的最重要好处是通知和切入点也可以由IOC来管理
* 被代理类没有实现任何切入点也可以由IOC来管理
* 通过设置proxyTargetClass为true，可强制使用CGLIB
* 如果目标类实现了一个（或者多个）接口，那么创建代理的类型将依赖ProxyFactoryBean的配置
* 如果ProxyFactoryBean的proxyInterfaces属性被设置为一个或者多个全限定接口名，基于JDK的代理将被创建
* 如果ProxyFactoryBean的proxyInterfaces属性没有被设置，但是目标类实现了一个（或者多个）接口，那么ProxyFactoryBean将自动检测到这个目标类已经实现了至少一个接口，创建一个基于JDK的代理

### AspectJ
* @AspectJ的风格类似纯java注解的普通java类
* Spring可以使用AspectJ来做切入点解析
* AOP的运行时仍旧是纯的Spring AOP,对AspectJ的编译器或者织入无依赖性

#### Spring中配置@AspectJ
* 对@AspectJ支持可以使用XML或java风格的配置
* 确保AspectJ的aspectjweaver.jar库包含在应用程序中（版本1.6.8或更高版本）的classpath中
```
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    
}
```
```
<aop:aspectj-autoproxy/>
```
#### aspect
* @AspectJ切面使用@Aspect注解配置，拥有@Aspect的任何bean将被Spring自动识别并应用
* 用@Aspect注解的类可以有方法和字段，他们也可能包括切入点（pointcut）,通知（Advice）和引入（introduction）声明
* @Aspect注解是不能够通过类路径自动检测发现的，所以需要配合使用@Component注解或者在XML配置bean

