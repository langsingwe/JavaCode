package cc.wei.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
