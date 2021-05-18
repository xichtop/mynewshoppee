package f8.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityAdminInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("admin") == null){
			System.out.print("Yêu cầu đăng nhập!!!");
			response.sendRedirect(request.getContextPath() + "/home/dangnhap.htm");
			return false;
		}
		return true;
	}
}
