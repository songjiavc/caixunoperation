package com.richinfo.manager.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.richinfo.manager.user.model.Authority;
import com.richinfo.manager.user.service.AuthorityService;

/** 
  * @ClassName: AuthController 
  * @Description: 权限管理控制层
  * @author songjia@richinfo.cn
  * @date 2016年11月21日 上午11:24:41 
  *  
  */
@Controller
@RequestMapping("/authority")
public class AuthController extends BaseController{
	
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * @param request
	 * @param model
	 * @param httpSession
	 * @desc  初始化权限页面
	 * @return
	 */
	@RequestMapping(value = "/initAuthority.action")
	public String initAuthority(HttpServletRequest request,ModelMap model,HttpSession httpSession) {
 		///authority/initAuthority.action
 		return "user/authority";
	}
	
	/** 
	  * @Description: 获取权限下级列表 
	  * @author songjia@richinfo.cn
	  * @date 2016年11月21日 下午3:44:57 
	  * 
	  * @param request
	  * @param model
	  * @param httpSession
	  * @return 
	  */
	@RequestMapping(value = "/getChildrenAuthorityList.action")
	public @ResponseBody List<Authority> getChildrenAuthorityList(HttpServletRequest request,ModelMap model,HttpSession httpSession) {
 		///authority/initAuthority.action
 		String authParentId = request.getParameter("authParentId");
		List<Authority> authList = authorityService.getChildrenAuthorityList(authParentId);
		return authList;
	}
	
	/** 
	  * @Description: 保存或者更新url
	  * @author songjia@richinfo.cn
	  * @date 2016年11月21日 下午3:49:22 
	  * @param request
	  * @param model
	  * @param httpSession
	  * @return 
	  */
	@RequestMapping(value = "/saveOrUpdateAuthority.action")
	public @ResponseBody String saveOrUpdateAuthority(HttpServletRequest request,ModelMap model,HttpSession httpSession){
		Authority authority = new Authority();
		String authId = authorityService.insertOrUpdateAuthority(authority);
		return this.sendSuccessMsg(authId);
	}
}
