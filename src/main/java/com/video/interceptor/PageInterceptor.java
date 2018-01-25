package com.video.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.video.util.MapUtil;

@Component
public class PageInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// request.getParameter("参数名"); //根据参数名获取参数值（注意，只能获取一个值的参数）
		// request.getParameterValue("参数名"); //根据参数名获取参数值（可以获取多个值的参数）
		// request.getParameterNames(); //获取所有参数名称列表

		String cpage = request.getParameter("cpage");
		String pagesize = request.getParameter("pagesize");
		String method = request.getMethod();
		switch (method == null ? "" : method) {
		case "POST":
			if (cpage == null || cpage == "" || pagesize == null || pagesize == "") {
				response.getWriter().print(MapUtil.errorMap("need  method cpage and pagesize"));
				return false;
			} else {
				return true;
			}

		case "GET":
			
			return true;
		case "DELETE":
			
			return true;
		case "PUT":
			
			return true;
		default:
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
