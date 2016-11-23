package com.richinfo.manager.common.util;

import javax.servlet.http.HttpSession;

import com.richinfo.manager.user.model.User;



public class LoginUtils {
	
	
	 /** 
	  * @Description:根据登录session 获取登录用户code
	  * @author songjia@richinfo.cn
	  * @date 2016年11月23日 上午9:59:02 
	  * 
	  * @param session
	  * @return 
	  */
	public static String getAuthenticatedUserCode(HttpSession session){
		 String userId = null; 
		
		 User userBean = (User)session.getAttribute("currentUser");
		 
		 userId = userBean.getCode();
		 
		 return userId;
	 }
	 
	 
	 /** 
	  * @Description: 根据session获取用户名称 
	  * @author songjia@richinfo.cn
	  * @date 2016年11月23日 上午9:59:42 
	  * 
	  * @param session
	  * @return 
	  */
	public static String getAuthenticatedUserName(HttpSession session){
		 String name = null; 
		
		 User userBean = (User)session.getAttribute("userBean");
		 
		 name = userBean.getName();
		 
		 return name;
	 }
	 
	 /** 
	  * @Description: 根据  session 获取用户对应id
	  * @author songjia@richinfo.cn
	  * @date 2016年11月23日 上午10:00:13 
	  * 
	  * @param session
	  * @return 
	  */
	public static String getAuthenticatedUserId(HttpSession session){
		 String userId = null; 
		
		 User userBean = (User)session.getAttribute("userBean");
		 
		 userId = userBean.getId();
		 
		 return userId;
	 }
	 
}
