package cc.wei.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
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
