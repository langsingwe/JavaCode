[TOC]

SSM学习路径分为4个阶段
* 战斗伊始——Spring
* 转战阵地——SpringMVC
* 再下一城——MyBatis
* 终极目标——整合案例

<font size=6><center>**战斗伊始——Spring**</center></font>

# 1-2 Spring事务管理

## 概念介绍
### 事务回顾
1. 什么是事务  
事务指的是逻辑上的一组操作，这组操作要么全部成功，要么全部失败

2. 事务的特性  
* 原子性：事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生
* 一致性：事务前后数据的完整性必须保持一致
* 隔离性：多个用户并发访问数据库时，一个用户的事务不能被其他用户的事务所干扰，多个并发事务之间数据要相互隔离
* 持久性：一个事务一旦被提交，它对数据中数据的改变就是永久性的，即使shujuk发生故障也不应该对其有任何影响

### 事务的API介绍
#### Spring接口介绍
Spring事务管理高层抽象主要包括三个接口：
1. PlatformTransactionManager 事务管理器
2. TransactionDefinition 事务定义信息（隔离、传播、超时、只读）
3. TransactionStatus 事务具体运行状态

#### 事务管理器 PlatformTransactionManager
* Spring为不同的持久化框架提供了不同PlatformTransactionManager接口实现

<html>
<table>
    <tr>
        <th>事务</th>
        <th>说明</th>
    </tr>
    <tr>
        <td width="420px">org.springframework.jdbc.datasource.DataSourceTransactionManager</td>
        <td>使用Spring JDBC或iBatis进行持久化数据时使用</td>
    </tr>
    <tr>
        <td>org.springframework.orm.hibernate3.HibernateTransactionManager</td>
        <td>使用Hibernate3.0版本进行持久化数据时使用</td>
    </tr>
    <tr>
        <td>org.springframework.orm.jpa.JpaTransactionManager</td>
        <td>使用JPA进行持久化时使用</td>
    </tr>
    <tr>
        <td>org.springframwork.jdo.JdoTransactionManager</td>
        <td>当持久化机制是Jdo时使用</td>
    </tr>
    <tr>
        <td>org.springframwork.transaction.jta.JtaTransactionManager</td>
        <td>使用一个JTA实现来管理事务，在一个事务跨越多个资源时必须使用</td>
    </tr>
</table>
</html>

#### 事务定义信息 TransactionDefinition 
* **如果不考虑隔离性，会引发安全问题如下**
1. **脏读：** 一个事务读取了另一个事务改写但还未提交的数据，如果这些数据被回滚，则读到的数据是无效的
2. **不可重复读：** 在同一个事务中，多次读取同一数据返回的结果有所不同
3. **幻读：** 一个事务读取了几行记录后，另一个事务插入一些记录，幻读就发生了。在后来的查询中，第一个事务就会发现有些原来没有的记录

* **事务隔离级别**

隔离级别 | 含义
---|---
DEFAULT | 使用后端数据库默认的隔离级别（spring中的选择项）
READ_UNCOMMITTED | 允许你读取还未提交的改变了的数据。可能导致脏、幻、不可重复读
READ_COMMITTED | 允许在并发事务已经提交后读取。可防止脏读，但幻读和不可重复读仍可发生
REPEATABLE_READ | 对相同字段的多次读取是一致的，除非数据被事务本身改变。可防止脏、不可重复读，但幻读仍可能发生
SERIALIZABLE | 完全服从ACID的隔离级别，确保不发生脏、幻、不可重复读。这在所有的隔离级别中是最慢的，它的典型的完全锁定在事务中涉及的数据表来完成的

> mysql默认采用REPEATABLE_READ隔离级别  
  Oracle默认采用READ_COMMITTED隔离级别
  
* **事务传播行为**

事务传播行为类型 | 说明
---|---
PROPAGATION_REQUIRED | 支持当前事务，如果不存在，就新建一个
PROPAGATION_SUPPORTS | 支持当前事务，如果不存在，就不使用事务
PROPAGATION_ANDATORY | 支持当前事务，如果不存在，抛出异常
PROPAGATION_REQUIRES_NEW |  如果有事务存在，挂起当前事务，创建一个新的事务
PROPAGATION_NOT_SUPPORTED | 以非事务方式运行，如果有事务存在，挂起当前事务
PROPAGATION_NEVER | 以非事务方式运行，如果有事务存在，抛出异常
PROPAGATION_NESTED | 如果当前事务存在，则嵌套事务执行

### Spring 事务管理
**Spring 支持两种方式事务管理**    
1. 编程式的事务管理
 * 在实际应用中很少使用
 * 通过TransactionTemplate手动管理事务

2. 使用XML配置声明式事务
 * 开发中推荐使用（代码侵入性最小）
 * Spring的声明式事务是通过AOP实现的

### 转账案例
* 创建数据表account
```
CREATE TABLE account(
    -> id INT(11) NOT NULL AUTO_INCREMENT,
    -> name VARCHAR(20) NOT NULL,
    -> money DOUBLE DEFAULT NULL,
    -> PRIMARY KEY(id)
    -> )ENGINE=
InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO account VALUES('1','aaa','1000');
INSERT INTO account VALUES('2','bbb','1000');
INSERT INTO account VALUES('3','ccc','1000');
```
* 创建maven工程
* 添加依赖
* 在src/main/resources目录编写配置：applicationContext.xml log4j.properties jdbc.properties
* 编写DAO注入 注入JdbcTemplate
```
package com.spring.demo1.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/*
 * 转账案例的DAO层的实现类
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    /**
     * @param out    : 转出账号
     * @param money  ：转账金额
     */
    @Override
    public void outMoney(String out, Double money) {
        String sql = "update account set money = money - ? where name = ?";
        this.getJdbcTemplate().update(sql,money,out);
    }
    /**
     * @param in     ： 转入账号
     * @param money  ： 转账金额
     */
    @Override
    public void inMoney(String in, Double money) {
        String sql = "update account set money = money + ? where name = ?";
        this.getJdbcTemplate().update(sql,money,in);
    }
}
```
* 编写service注入DAO
```
package com.spring.demo1.service;

import com.spring.demo1.dao.AccountDao;
/*
 * 转账案例的业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    //注入转账的DAO
    private AccountDao accountDao;
    
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    /**
     * @param out ： 转出账号
     * @param in ： 转入账号
     * @param money ： 转账金额
     */
    @Override
    public void transfer(String out, String in, Double money) {
        //aaa向bbb转账200元
        accountDao.outMoney("aaa", 200);
        int i = 1 / 0;
        accountDao.inMoney("bbb", 200);
    }
}
```
* 配置applicationContext.xml
```
<!-- 引入外部属性文件 -->
<context:property-placeholder location="classpath:jdbc.properties"/>

<!-- 配置c3p0连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="${jdbc.driverClass}"/>
    <property name="jdbcUrl" value="${jdbc.url}"/>
    <property name="user" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<!-- 配置业务层的类 -->
<bean id="accountService" class="com.spring.demo1.service.AccountServiceImpl">
    <property name="accountDao" ref="accountDao"/>
</bean>

<!-- 配置DAO的类 -->
<bean id="accountDao" class="com.spring.demo1.dao.AccountDaoImpl">
    <property name="dataSource" ref="dataSource"/>
</bean>
```
### 编程式事务管理
* 在AccountService中使用TransactionTemplate
* TransactionTemplate依赖DataSourceTransactionManager
* DataSourceTransactionManager依赖DataSource构造
```
<bean id="accountService" class="cn.java.service.AccountService">
    <property name="accountDAO" ref="accountDAO"/>
</bean>
<bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
    <property name="transactionManager" ref="transactionManager"/>
</bean>
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
```
```
package com.spring.demo1.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.spring.demo1.dao.AccountDao;
/*
 * 转账案例的业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    //注入转账的DAO
    private AccountDao accountDao;
    
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    //注入事务管理模版
    private TransactionTemplate transactionTemplate;
    
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    /**
     * @param out ： 转出账号
     * @param in ： 转入账号
     * @param money ： 转账金额
     */
	@Override
    public void transfer(final String out, final String in, final Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.outMoney(out, money);
                int i = 1 / 0;
                accountDao.inMoney(in, money);
            }
        });
    }
}

```
### 声明式事务管理
```
<!-- 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<!-- 配置业务层的代理 -->
<bean id="accountServiceProxy" class="org.springframework.transaction.interceptor.TransactionProxyFactoryBean">
    <!-- 配置目标对象 -->
    <property name="target" ref="accountService"/>
    <!-- 注入事务管理器 -->
    <property name="transactionManager" ref="transactionManager"/>
    <!-- 注入事务属性 -->
    <property name="transactionAttributes">
        <props>
            <!--
             prop的格式 
            	* PROPAGATION : 事务的传播行为
            	* ISOLATION   : 事务的隔离级别
            	* readOnly    : 只读(不可以进行修改，插入，删除)
            	* -Exception  : 发生哪些异常回滚事务
            	* +Exception  : 发生哪些异常事务不回滚
            -->
            <prop key="transfer">PROPAGATION_REQUIRED</prop>
        </props>
    </property>
</bean>
```
```
@Resource(name="accountServiceProxy")
private AccountService accountService;
@Test
/*
 * 转账案例
 */
public void test1() {
    accountService.transfer("aaa", "bbb", 200d);
}
```
### 使用XML配置声明式事务 基于tx/aop
* 引入aop和tx命名空间
```
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd">
```
```
<!-- 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<!-- 配置事务的通知(事务的增强) -->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
    	<tx:method name="transfer" propagation="REQUIRED" />
    </tx:attributes>
</tx:advice>

<!-- 配置切面 -->
<aop:config>
    <!-- 配置切入点 -->
    <aop:pointcut expression="execution(* com.spring.demo3.service.AccountService+.*(..))" id="pointcut1"/>
    <!-- 配置切面 -->
    <aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut1"/>
</aop:config>
```
### 使用注解配置声明式事务
```
<!-- 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<!-- 开启注解事务 -->
<tx:annotation-driven transaction-manager="transactionManager"/>
```
```
//需要声明事务的类上加注解
@Transactional
public class AccountServiceImpl implements AccountService {
    ...
}
```

## 总结
**Spring将事务管理分成了两类：**
* 编程式事务管理  
手动编写代码进行事务管理（很少使用）

* 声明式事务管理  
1.基于TransactionProxyFactoryBean的方式（很少使用）需要为每个进行事务管理的类，配置一个TransactionProxyFactoryBean进行增强  
2.基于AspectJ的XML方式（常用）一旦配置好，类上不需要添加任何东西  
3.基于注解方式在（常用）配置简单。需要在业务层类上添加`@Transactionanl`的注解