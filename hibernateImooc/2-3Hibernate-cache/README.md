[TOC]

# 第一章 缓存策略概述
**1. 课程内容**
* 了解缓存
* 掌握hibernate一级缓存的使用
* 掌握hibernate二级缓存的使用
* hibernate一二级缓存的对比和总结

**2. 什么是缓存？**
* 并不是指计算机的内存或者CPU的一二级缓存
* 缓存是指为了降低应用程序对物理数据源访问的频次，从而提高应用程序的运行性能的一种策略

**3. 为什么使用缓存？**
* ORM框架访问数据库的效率直接影响应用程序的运行速度，提升和优化ORM框架的执行效率至关重要
* hibernate的缓存是提升和优化hibernate执行效率的重要手段，所以学会hibernate缓存的使用和配置是优化的关键

# 第二章 hibernate不使用缓存的问题
**观察下面代码发现什么问题?**
```
public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getCurrentSession();
        Employee emp = (Employee) session.get(Employee.class, 1);
        System.out.println(emp.getName());
        
        Session session = HibernateUtil.getCurrentSession();
        emp = (Employee) session.get(Employee.class, 1);
        System.out.println(emp.getName());
        
        session.close();
    }
}
```
**控制台**
```
Hibernate: 
    select
        employee0_.EMPID as EMPID0_0_,
        employee0_.NAME as NAME0_0_,
        employee0_.AGE as AGE0_0_ 
    from
        EMPLOYEE employee0_ 
    where
        employee0_.EMPID=?
jack
jack
```
1. 第二次查询同一个对象时，并没有再次执行数据库查询
2. 在不同的session中多次查询同一对象时，会执行多次数据库查询
3. 一级缓存中，持久化类的每个实例都具有唯一的OID

# 第三章 一级缓存介绍
1. hibernate一级缓存又称为“session缓存”、“会话级缓存”
2. 通过session从数据库查询实体时会把实体在内存中存储起来，下一次查询同一实体时不再从数据库获取，而从内存中获取，这就是缓存
3. 一级缓存的生命周期和session相同；session销毁，它也销毁
4. 一级缓存的数据可适用范围在当前会话之内

**hibernate一级缓存的API**  
一级缓存无法取消，用两个方法管理：
* evict()：用于将某个对象从session的一级缓存中清除
* clear()：用于将一级缓存中的所有对象全部清除

一级缓存有时候会对程序的性能产生影响

# 第四章 二级缓存
**提出问题，如何解决？**

有些常用的数据，在一个session中缓存以后，我们希望在其它session中能够直接使用，而不用再次缓存怎么办？

使用更高级别的二级缓存，每个session公用的缓存

**二级缓存的配置步骤**
1. 添加二级缓存对应jar包
2. 在hibernate配置文件中添加Provider类的描述
3. 添加二级缓存的属性配置文件
4. 在需要被缓存的表所对应的映射文件中添加<cache/>标签

**<cache/>标签的详细介绍**

usage：指定缓存策略，可选的策略包括： transactional, read-write, nonstrict-read-write, read-only

# 第五章 一二级缓存对比及总结
**二级缓存**

* 二级缓存又称为“全局缓存”、“应用级缓存”
* 二级缓存中的数据可适用范围是当前应用的所有会话
* 二级缓存是可插拔式缓存，默认是EHCache，还支持其它二级缓存组件如：Hashtable, OSCache, SwarmCache, JBoss TreeCache等

**在通常情况下会将具有以下特征的数据放入到二级缓存中**

* 很少被修改的数据
* 不是很重要的数据，允许出现偶尔并发的数据
* 不会被并发访问的数据
* 参考数据

**一二级缓存的对比**
![image](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/hibernate/20.jpg)

**总结**
1. hibernate的缓存能提交检索效率
2. hibernate的缓存分为一级缓存和二级缓存。一级缓存是会话级缓存，二级缓存是应用级缓存
3. hibernate的缓存在提高检索的同时，也会增加服务器的消耗，所以要注意缓存的使用策略