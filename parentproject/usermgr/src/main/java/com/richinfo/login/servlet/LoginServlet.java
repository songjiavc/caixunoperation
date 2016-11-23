package com.richinfo.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.richinfo.manager.user.service.login.LoginService;

/** 
  * @ClassName: LoginServlet 
  * @Description: TODO(这里用一句话描述这个类的作用) 
  * @author songjia@richinfo.cn
  * @date 2016年11月18日 上午10:43:44 
  *  
  */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/* (非 Javadoc) 
	 * <p>Title: doPost</p> 
	 * <p>Description: </p> 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
	 */
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext appContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		LoginService bs = (LoginService) appContext.getBean("loginService");
		boolean loginResult = bs.userLogin(request);
		if (loginResult) {
			//返回到动态加载页面
			request.setAttribute("message", "success");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}
	}
}
