package com.richinfo.login.service;

import javax.servlet.http.HttpServletRequest;

/** 
  * @ClassName: LoginService 
  * @Description: TODO(这里用一句话描述这个类的作用) 
  * @author songjia@richinfo.cn
  * @date 2016年11月18日 上午10:26:10 
  *  
  */
public interface LoginService {
	
	
	
	/**   
	  * @Description: TODO(用一句话描述该文件做什么) 
	  * @author songjia@richinfo.cn
	  * @date 2016年11月18日 上午10:26:03 
	  * @version V1.0   
	  */
	public boolean userLogin(HttpServletRequest request);
	
}
