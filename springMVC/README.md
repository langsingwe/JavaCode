[TOC]
# 一、回顾Servlet
1. 将前端页面和后端java结合起来
2. servlet做了哪些事情:
 * 将请求的url映射到java类的一个方法上
 * 将前端页面提交的参数传入到java类中
 * 将后端的处理结果回显到前端页面
 * 控制页面的跳转
3. servlet案例
```
@WebServlet("/toUpdate")
public class ToUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        resp.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        BookDao bookDao = new BookDao();
        Book book = bookDao.findById(id);
        List<Category> categorys = new CategoryDao().findAll();
        req.setAttribute("book",book);
        req.setAttribute("categorys",categorys);
        req.getRequestDispatcher("update.jsp").forward(req,resp);
    }
}
```
分析servlet代码可以发现：  
 获取表单的数据比较麻烦，而且都是重复的代码；  
 获取表单的数据需要进行类型转换；  
 servlet的前端视图部分只能是jsp；  
 有些公共的功能需要手动编码实现：如文件上传

4. 为了解决servlet中的问题，出现了一些mvc框架：如struts1，struts2,springMVC,jfinal,nutz等

5. MVC框架做的事情和servlet做的事是一样的

# 二、SpringMVC简介
1. SpringMVC是Spring框架提供的一组解决MVC的轻量级组件
2. SpringMVC的运行图：
![mvc](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/springMVC/mvc.png)
![mvc2](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/springMVC/mvc2.png)
3. 好处：
 自动获取参数   
 自动进行常见类型转换  
 同一个处理器可以处理多个请求  
 支持实现了常见功能：文件上传  
 支持多种视图   
 支持RSETFul风格  
 天生和Spring框架无缝集成  
 开发效率比较高

# 三、搭建SpringMVC第一个案例  
1. 新建一个web项目
2. 导入相关的jar包
```
commons-logging-1.1.1.jar
spring-aop-4.3.9.RELEASE.jar
spring-beans-4.3.9.RELEASE.jar
spring-context-4.3.9.RELEASE.jar
spring-core-4.3.9.RELEASE.jar
spring-expression-4.3.9.RELEASE.jar
spring-web-4.3.9.RELEASE.jar
spring-webmvc-4.3.9.RELEASE.jar
```
3. 配置web.xml
```
<!-- 配置SpringMVC的前端控制器 -->
<servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>mvc</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
<welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
</welcome-file-list>
```
4. 开发springMVC的处理类
```
/*
*实现springMVC的处理器
*/
public class HelloController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","hello springMVC!!!");
        mv.setViewName("hello");
        return mv;
    }
}
```
5. springMVC的配置文件，该文件默认放于WEB-INF下，名称为`DispatcherServlet的名-servlet.xml`
```
<!-- 配置处理器映射器 -->
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
<!-- name为请求的url class为请求处理器 -->
<bean name="hello.do" class="cc.wei.controller.HelloController"/>	
<!-- 配置适配器 -->
<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
<!-- 配置视图解析器 -->
<bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
<!-- 视图解析器的前缀 讲返回的ModelAndView的名前加上前缀 -->
<property name="prefix" value="/WEB-INF/jsp/"/>
<!-- 视图解析器的后缀 如果返回的视图名为hello 那么最终视图为 /WEB-INF/jsp/hello.jsp -->
<property name="suffix" value=".jsp"/>
```
6. 页面
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>springMVC</title>
</head>
<body>
    ${msg }
</body>
</html>
```
7. 测试  

![测试结果](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/springMVC/hellospring.jpg)

# 四、简单流程及配置
1. 简单的流程图
```
sequenceDiagram
浏览器->>服务器: 1发起请求
服务器->>DispathcerServlet: 2根据web.xml映射请求
DispathcerServlet->>HandlerMapping: 3读取springMVC的配置文件，进行url映射
HandlerMapping->>HandlerAdapter: 4根据映射器结果调用处理器适配器
HandlerAdapter->>Handler(Controller): 5调用处理器执行
Handler(Controller)->>ViewResolver: 6将处理给视图解析器，解析为指定的视图
ViewResolver->>服务器: 7将解析结果返回
服务器->>浏览器: 8响应
```
2. 修改springMVC的配置文件名称
```
<servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc.xml</param-value>
    </init-param>
</servlet>
```
3. controller的几种配置方式  
参考文章：  
http://www.cnblogs.com/ysloong/p/6388962.html  
http://cuisuqiang.iteye.com/blog/2043697

# 五、使用注解开发Controller
1. 新建web项目
2. 导入相关jar包
```
commons-logging-1.1.1.jar
spring-aop-4.3.9.RELEASE.jar
spring-beans-4.3.9.RELEASE.jar
spring-context-4.3.9.RELEASE.jar
spring-core-4.3.9.RELEASE.jar
spring-expression-4.3.9.RELEASE.jar
spring-web-4.3.9.RELEASE.jar
spring-webmvc-4.3.9.RELEASE.jar
```
3. 配置web.xml
```
<servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc.xml</param-value>
    </init-param>
</servlet>
    <servlet-mapping>
        <servlet-name>mvc</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
```
4. 开发controller类
```
/*
 * 使用注解开发controller
 * @controller表示该类是一个处理器，容器扫描该类时会产生相应的对象
 */
@Controller
public class HelloController {
    /*
     * 处理方法
     * @RequestMapping 注解访问该类的url
     */
    @RequestMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "spring mvc annotation");
        return mv;
    }
}
```
5. 配置springmvc的配置文件
```
<!-- 使用注解开发 -->
<mvc:annotation-driven/>
<!-- 扫描注解所在包 -->
<context:component-scan base-package="cc.wei.controller"/>
<!-- 配置视图解析器 -->
<bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
    <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
    <!-- 视图解析器的前缀 讲返回的ModelAndView的名前加上前缀 -->
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <!-- 视图解析器的后缀 如果返回的视图名为hello 那么最终视图为 /WEB-INF/jsp/hello.jsp -->
    <property name="suffix" value=".jsp"/>
</bean>
```
6. 开发Jsp页面
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>annotation</title>
</head>
<body>
${msg }
</body>
</html>
```
7. 测试

# 六、参数绑定
## 基本数据类型的获取：  
 表单中的域名称和处理方法的参数名相同，springMVC会自动将数据绑定到参数上  
 jsp页面  
```
<form action="login.do" method="post">
    username:<input type="text" name="name"><br>
    password:<input type="password" name="pwd"><br>
    <input type="submit" value="登录">
</form>
```
 LoginController  
 ```
@Controller
public class LoginController {
    @RequestMapping("toLogin")
    public ModelAndView toLogin() {
    	return new ModelAndView("login");
    }
    @RequestMapping("/login")
    public ModelAndView login(String name,String pwd) {
        System.out.println("name="+name+"\tpassword="+pwd);
        ModelAndView mv = new ModelAndView("success");
        if("wei".equals(name)&&"123456".equals(pwd)) {
        	mv.setViewName("success");
        	mv.addObject("msg","登陆成功！");
        }else {
        	mv.setViewName("login");
        }
        	return mv;
    }
}
 ```
## 如果表单域名称和参数名不一致时，
需要使用`@RequestParam`来处理  
 jsp页面  
```
<form action="login1.do" method="post">
    username:<input type="text" name="username"><br>
    password:<input type="password" name="pwd"><br>
    <input type="submit" value="登录">
</form>
```
 LoginController  
 ```
@Controller
public class LoginController {
    @RequestMapping("toLogin")
    public ModelAndView toLogin() {
    	return new ModelAndView("login");
    }
    @RequestMapping("/login1")
    public ModelAndView login1(@RequestParam("username")String name,String pwd) {
        System.out.println("name="+name+"\tpassword="+pwd);
        ModelAndView mv = new ModelAndView("success");
        if("wei".equals(name)&&"123456".equals(pwd)) {
        	mv.setViewName("success");
        	mv.addObject("msg","登陆成功！");
        }else {
        	mv.setViewName("login");
        }
        	return mv;
    }
}
 ```
## 如果在处理器中想获取对象
那么**要求表单域中名称和对象的属性名相同**  
jsp
```
<form action="login2.do" method="post">
    username:<input type="text" name="username"><br>
    password:<input type="password" name="pwd"><br>
    <input type="submit" value="登录">
</form>
```
User.java
```
public class User {
    private String name;
    private String pwd;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
```
LoginController  
 ```
@Controller
public class LoginController {
    @RequestMapping("toLogin")
    public ModelAndView toLogin() {
    	return new ModelAndView("login");
    }
    @RequestMapping("/login2")
    public ModelAndView login2(User user) {
        System.out.println("name="+user.getName()+"\tpassword="+user.getPwd());
        ModelAndView mv = new ModelAndView("success");
        if("wei".equals(user.getName())&&"123456".equals(user.getPwd())) {
        	mv.setViewName("success");
        	mv.addObject("msg","登陆成功！");
        }else {
        	mv.setViewName("login");
        }
        	return mv;
    }
}
 ```
 
## 包装类  
Role.java
```
public class Role {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```
User.java
```
public class User {
    private String name;
    private String pwd;
    private Role role;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
```
JSP  
```
<form action="login3.do" method="post">
    username:<input type="text" name="name"><br>
    password:<input type="password" name="pwd"><br>
    role:<input type="radio" name="role.name" value="管理员">管理员
    	<input type="radio" name="role.name" value="普通会员">普通会员<br>
        <input type="submit" value="登录">
</form>
```
Controller的处理
```
@Controller
public class LoginController {
    @RequestMapping("toLogin")
    public ModelAndView toLogin() {
    	return new ModelAndView("login");
    }
    @RequestMapping("/login3")
    public ModelAndView login3(User user) {
    	System.out.println("roleName="+user.getRole().getName());
    	ModelAndView mv = new ModelAndView("success");
    	if("wei".equals(user.getName())&&"123456".equals(user.getPwd())) {
    		mv.setViewName("success");
    		mv.addObject("msg","登陆成功！");
    	}else {
    		mv.setViewName("login");
    	}
    		return mv;
    }
}
```
## 数组的绑定
页面提交一组数据时，并且该数据是基本类型和String，可以直接使用数组来接收 
JSP
```
<form action="arr.do" method="post">
爱好：<input type="checkbox" name="hobbies" value="足球">足球
    <input type="checkbox" name="hobbies" value="篮球">篮球
    <input type="checkbox" name="hobbies" value="羽毛球">羽毛球
    <input type="checkbox" name="hobbies" value="乒乓球">乒乓球<br>
    <input type="submit" value="提交">
</form>
```
controller
```
@Controller
public class CollectionController {
    @RequestMapping("/arr")
    public ModelAndView arr(String[] hobbies) {
        for(String str:hobbies) {
        	System.out.println(str);
        }
        return null;
    }
}
```
## List的绑定
需要对list进行包装，并且提供get/set方法   
JSP
```
<form action="arr1.do" method="post">
爱好：<input type="checkbox" name="hobbies[0]" value="足球">足球
    <input type="checkbox" name="hobbies[1]" value="篮球">篮球
    <input type="checkbox" name="hobbies[2]" value="羽毛球">羽毛球
    <input type="checkbox" name="hobbies[3]" value="乒乓球">乒乓球
    <input type="submit" value="提交">
</form>
```
Student.java
```
public class Student {
    private List<String> hobbies;
    public List<String> getHobbies() {
        return hobbies;
    }
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}

```
Controller
```
@RequestMapping("/arr1")
public ModelAndView arr(Student student) {
    for(String str:student.getHobbies()) {
    	System.out.println(str);
    }
    return null;
}
```
list更多用于绑定提交批量对象  
Goods.java
```
/*
 * 商品类
 */
public class Goods {
    private String name;
    private double price;
    private int num;
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public double getPrice() {
    	return price;
    }
    public void setPrice(double price) {
    	this.price = price;
    }
    public int getNum() {
    	return num;
    }
    public void setNum(int num) {
    	this.num = num;
    }
}
```
Cart.java
```
/*
 * 购物车
 */
public class Cart {
    private List<Goods> list;
    public List<Goods> getList() {
        return list;
    }
    public void setList(List<Goods> list) {
        this.list = list;
    }
}
```
JSP
```
<form action="arr2.do" method="post">
    商品名称:<input type="text" name="list[0].name">
    商品价格:<input type="text" name="list[0].price">
    商品数量:<input type="text" name="list[0].num"><br>
    商品名称:<input type="text" name="list[1].name">
    商品价格:<input type="text" name="list[1].price">
    商品数量:<input type="text" name="list[1].num"><br>
    	<input type="submit" value="提交">
</form>
```
controller
```
@RequestMapping("/arr2")
public ModelAndView arr2(Cart cart) {
    for(Goods goods:cart.getList()) {
    	System.out.println(goods.getName()+"\t"+goods.getPrice()+"\t"+goods.getNum());
    }
    return null;
}
```

# 七、数据回显
## 方式  
 1)通过ModelAndView对象将数据回显  
```
@RequestMapping("/mv")
public ModelAndView mv() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("data");
    //通过ModelAndView来添加回显数据 添加的是一条key-value数据
    mv.addObject("name", "张三");
    
    Map<String, Object> map = new HashMap<>();
    map.put("age",22);
    map.put("sex","女");
    //添加一组key-value数据
    mv.addAllObjects(map);
    return mv;
}
```
 2)通过ModelMap来回显数据：需要在处理方法上生命一个ModelMap类型的参数
```
@RequestMapping("/mm")
public ModelAndView mm(ModelMap modelMap) {
    //添加一条key-value数据
    modelMap.addAttribute("name", "王二");
    //添加一组key-value数据
    Map<String, Object> map = new HashMap<>();
    map.put("age",22);
    map.put("sex","女");
    //添加一组key-value数据
    modelMap.addAllAttributes(map);
    return new ModelAndView("data");
}
```
 3)通过ServletAPI来回显,在处理方法的参数上可以声明ServletAPI对象，该对象由框架来进行设置 
 ```
 @RequestMapping("/api")
public ModelAndView api(HttpServletRequest req,HttpServletResponse resp,HttpSession session) {
    req.setAttribute("name", "大黄");
    session.setAttribute("username", "小明");
    return new ModelAndView("data");
}
 ```
## ModelAndView和ModelMap的区别 
* 共同点：都能回显结果数据
* 不同点：ModelAndView可以指定跳转的视图，而ModelMap只能回显数据

# 八、结果返回类型
1. 结果类型为ModelAndView---最终通过视图解析器来解析  
* 返回null时：不返回任何结果
```
@RequestMapping("/hello")
public ModelAndView hello() {
	return null;
}
```
* 返回ModelAndView，但是不指定视图名称时：将会以请求请求名作为视图名进行返回
```
@RequestMapping("/hello1")
public ModelAndView hello1() {
	return new ModelAndView();
}
```
结果：  
![结果](https://raw.githubusercontent.com/weiliangchun/MarkdownPic/master/springMVC/hello1.jpg)

* 返回null时，在处理方法中可以通过servletAPI来进行视图展示，不通过视图解析器
```
@RequestMapping("/hello2")
public ModelAndView hello2(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
	return new ModelAndView();
}
```
2. 返回void类型
* 返回类型为void，并且没有使用servletAPI进行返回，那么会以请求名为视图名进行视图解析
* 返回类型为void，通过servletAPI进行返回，能够正确的返回

3. 返回类型为String
* 返回类型为String，会将返回值作为视图名，进行视图解析
* 返回类型为String，返回值为null时，请求名将作为视图名称进行返回
* 返回值为String，为返回结果加前缀`"foword"`或者是`"rediract"`分别表示转发和重定向，并且不会进行视图解析

# 九、文件上传
1. 新建web项目
2. 导入`commons-fileupload,commons-io`包
3. 编写jsp页面
```
<form action="upload.do" method="post" enctype="multipart/form-data">
    文件：<input type="file" name="file">
        <input type="submit" value="上传">
</form>
```
4. 编写controller
```
@RequestMapping("upload")
public String upload(@RequestParam("file")CommonsMultipartFile file,HttpServletRequest req) throws IOException {
    //获取上传路径
    String path = req.getServletContext().getRealPath("/upload");
    //获取文件名
    String fileName = file.getOriginalFilename();
    InputStream inputStream = file.getInputStream();
    OutputStream outputStream = new FileOutputStream(new File(path,fileName));
    int len = 0;
    byte[] buffer = new byte[200];
    while((len=inputStream.read(buffer))!=-1) {
    	outputStream.write(buffer, 0, len);
    }
    outputStream.close();
    inputStream.close();
    return "redirect:success.jsp";
}
```
5. 配置文件上传解析器
```
<!-- 配置文件上传解析器 -->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!-- 设置文件上传大小 -->
    <property name="maxUploadSize" value="100000"/>
</bean>
```
6. 测试

# 十、AJAX
1. 在springMVC中依然可以使用servletAPI使用ajax的功能
```
@RequestMapping("/checkName")
public void checkName(String username,HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html;charset=utf-8");
    if("wei".equals(username)) {
        resp.getWriter().print("1");
    }else {
        resp.getWriter().print("0");
}
```
2. 在springMVC中可以使用集成功能来完成ajax  
a->导入jackson的三个jar包  
b->在配置文件中使用`<mvc:annotation-driven/>`或者使用类型转换器  
```
<bean id="stringConverter" class="org.springframework.http.converter.StringHttpMessageConverter">
    <property name="supportedMediaTypes">
        <list>
            <value>text/plain; charset=UTF-8</value>
            <value>application/json;charset=UTF-8</value>
        </list>
    </property>
</bean>
<bean id="jsonConverter" class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
</bean>
<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
    <property name="messageConverters">
        <list>
           <ref bean="stringConverter">
           <ref bean="jsonConverter">
        </list>
    </property>
</bean>
```
 c->在处理方法上使用`@ResponseBody`来进行注释  
 controller
```
@RequestMapping("/list")
@ResponseBody
public List<User> list(){
    List<User> list = new ArrayList<>();
    list.add(new User(1,"张三",25));
    list.add(new User(2,"王五",22));
    list.add(new User(3,"李四",44));
    return list;
}
 ```
 jsp
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $(function(){
        $('#btn').click(function(){
            $.post('list.do',function(data){
                var html = "";
                for(var i=0;i<data.length;i++){
                	html+="<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].age+"</td></tr>";
                }
                $('#content').html(html);
            });
        });
    });
    </script>
    </head>
    <body>
    <input type='button' id='btn' value='获取数据'>
    <table width='80%' align='center'>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
    </tr>
    <tbody id='content'>
    </tbody>
</table>
</body>
</html>
```
# 十一、RESTFul和窄化
1. 窄化：
```
@Controller
@RequestMapping("/user")
public class UserController {
    //请求add方法是 url = /user/add
    @RequestMapping("/add")
    public String add(User user) {
        System.out.println("-- add --");
        return "add";
    }
}
```
2. RESTFul
传统的url：localhost:8080/usersys/delelte.do?id=1001  
RESTFul风格：localhost:8080/usersys/1001/delete.do 更安全，更高效，传递的信息更多  

```
/*
*controller
*/
@RequestMapping("/{id}/delete")
public String delete(@PathVariable("id")int id) {
    System.out.println("id====="+id);
    return "delete";
}
```
请求url：`http://localhost:8080/restful/user/1001/delete.do`

3. 静态资源处理
```
<!-- 静态资源的处理 **表示当前路径及其子路径资源-->
<mvc:resources location="/js/" mapping="/js/**"></mvc:resources>
<mvc:resources location="/css/" mapping="/css/**"></mvc:resources>
```
更多处理方法参考：http://www.cnblogs.com/vevy/p/7560432.html

# 十二、拦截器
1. 拦截器和过滤器相似，过滤器过滤所有请求，拦截器只拦截controller的请求
2. 在springMVC中，拦截器的实现有2种方式：
* 实现HandlerInterceptor接口
* 继承HandlerInterceptorAdapter类
3.拦截器的实现步骤
a -> 自定义拦截器
```
/*
 * 自定义拦截器
 */
public class MyInterceptor implements HandlerInterceptor{
    /*
     * 在处理器的处理方法执行之前执行preHandle方法，一般可以用于初始化，实例化
     * 做一些业务处理的准备工作，该方法返回true继续执行处理器的业务方法，返回false，那么将不会继续执行 
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        System.out.println("preHandler-------");
        return true;
    }
    /*
     * 在处理器处理方法执行之后，在渲染视图之前执行
     * 一般用于处理业务方法执行后的一些收尾工作，如释放资源
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
    		throws Exception {
        System.out.println("postHandler------");
    }
    /*
     * 在渲染页面之后执行
     * 清理，释放
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
    		throws Exception {
        System.out.println("afterCompletioner-------");
}
}
```
b -> 要对自定义拦截器进行配置
```
<!-- 配置拦截器 -->
<mvc:interceptors>
    <!-- 配置多个拦截器 -->
    <mvc:interceptor>
        <!-- 指定被该拦截器拦截的url /**表示拦截器所有请求 /表示跟路径 -->
        <mvc:mapping path="/**"/>
        <!-- 指定拦截器的全路径 -->
        <bean class="cc.wei.interceptor.MyInterceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
```
c -> 测试
4. 登录拦截器的实现
```
/*
 * 登录拦截器的实现
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        /*
         * 如果session中可以找到 那么继续执行
         * 如果session中找不到 查看是否是登录请求 toLogin login
         */
        HttpSession session = request.getSession();
        if(session.getAttribute("username")!=null) {
        	return true;
        }
        String uri = request.getRequestURI();
        if(uri.endsWith("login.do")||uri.endsWith("toLogin.to")) {
        	return true;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }
}
```

以上就是学习spring MVC入门的全部笔记  
附：原视频教程地址：https://ke.qq.com/course/204686  
源代码地址(自己敲的，亲测可以运行无报错)：https://github.com/weiliangchun/JavaCode/tree/master/springMVC