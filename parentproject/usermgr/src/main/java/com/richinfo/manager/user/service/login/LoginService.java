package com.richinfo.manager.user.service.login;

import javax.servlet.http.HttpServletRequest;



/** 
  * @ClassName: LoginService 
  * @Description: 登录请求service
  * @author songjia@richinfo.cn
  * @date 2016年11月19日 下午2:50:19 
  *  
  */
public interface LoginService {
	
	/**
	 * 登录校验
	 * @param request
	 * @return
	 */
	public boolean userLogin(HttpServletRequest request);
	
}
