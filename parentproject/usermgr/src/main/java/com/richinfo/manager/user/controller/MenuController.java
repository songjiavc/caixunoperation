package com.richinfo.manager.user.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.stat.TableStat.Condition;
import com.alibaba.druid.util.StringUtils;
import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.common.bean.TreeBean;
import com.richinfo.manager.common.exception.GlobalExceptionHandler;
import com.richinfo.manager.common.util.Constants;
import com.richinfo.manager.common.util.LoginUtils;
import com.richinfo.manager.user.bean.AuthorityBean;
import com.richinfo.manager.user.bean.MenuBean;
import com.richinfo.manager.user.model.Authority;
import com.richinfo.manager.user.service.AuthorityService;


/** 
  * @ClassName: MenuController 
  * @Description: 目录相关控制层
  * @author songj@sdfcp.com
  * @date 2015年9月23日 下午5:21:54 
  *  
  */
@Controller
@RequestMapping("/menu")
public class MenuController extends GlobalExceptionHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
    @Autowired
	private AuthorityService authorityService;

    
    
    
	/** 
	  * @Description: 根据静态变量名获取变量值
	  * @author songjia@richinfo.cn
	  * @date 2016年11月23日 上午11:06:54 
	  * 
	  * @param httpSession
	  * @param constantName
	  * @param model
	  * @return
	  * @throws Exception 
	  */
	@RequestMapping(value = "/getConstant", method = RequestMethod.GET)
	public @ResponseBody ResultBean getConstant(HttpSession httpSession,
			@RequestParam(value="constantName",required=true) String constantName,
			ModelMap model) throws Exception {
		ResultBean resultBean = new ResultBean ();
		
		if("ORIGIN_AUTH_ID".equals(constantName))//获取权限表的虚拟根节点值
		{
			resultBean.setMessage(Constants.ORIGIN_AUTH_ID);
		}
		
		if("PROVINCE_ALL".equals(constantName))//获取省份全部的code
		{
			resultBean.setMessage(Constants.PROVINCE_ALL);
		}
		
		return resultBean;
		
	}
	
	
	/**
	 * 跳转到错误页
	 * @return
	 */
	@RequestMapping(value = "/error.action", method = RequestMethod.GET)
	public String error()
	{
		String indexPage = "error";
		
		
		return indexPage;
	}
    
	
	/** 
	  * @Description:获取用户对应的权限菜单 
	  * @author songjia@richinfo.cn
	  * @date 2016年11月23日 上午11:07:29 
	  * 
	  * @param model
	  * @param httpSession
	  * @return
	  * @throws Exception 
	  */
	@RequestMapping(value = "/getMenuData", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getMenuData(ModelMap model,HttpSession httpSession) throws Exception {
		
		/**
		 * 动态获取菜单实现
		 * 1.从session中获取当前登录人员的code，根据code获取当前用户对应的角色
		 * 2.便利角色，根据角色获取当前角色对应的权限数据
		 * 3.整理权限数据，将权限数据去重展示
		 * 4.%“baisic”根不动，前台获取也是根据这边name来获取的%
		 */
		//获取session中的登录数据
		
		/*
		String userId = LoginUtils.getAuthenticatedUserId(httpSession);
		User user = userService.getUserById(userId);
		//获取当前登录人员的角色list
		Role role = user.getRole();
		
		Set<Authority> authorities = new HashSet<Authority> ();
		
		List<Authority> authins = role.getAuthList();
		
		for (Authority authority : authins) {
			if(Constants.IS_NOT_DELETED.equals(authority.getStatus()))//Constants.IS_NOT_DELETED:未删除的权限数据
			{
				authorities.add(authority);
			}
			
		}
		
		Map<String,Object> child = new HashMap<String,Object> ();
		List<MenuBean> menubeans = new ArrayList<MenuBean> ();
		child.put("basic", menubeans);
		
		Iterator<Authority> it = authorities.iterator();
		String parentId ;
		while(it.hasNext())
		{
			Authority authin = it.next();
			//如果父节点是根节点的则是菜单的一级菜单
			if(Constants.ORIGIN_AUTH_ID.equals(authin.getParentAuthId()))//Constants.ORIGIN_AUTH_ID:权限树的树根节点写死我“1”
			{
				parentId = authin.getId();
				List<MenuBean> menus = new ArrayList<MenuBean> ();//下级菜单
				MenuBean mb = new MenuBean();
				mb.setMenuid(authin.getId());//权限id
				mb.setIcon(authin.getAuthImg());//权限图片
				mb.setMenuname(authin.getAuthName());//权限名称
				mb.setMenus(menus);
				menubeans.add(mb);
				menus = getChildMenu(parentId, authorities, menus);//获取下级菜单
			}
		}
		*/
		
		Map<String,Object> child = new HashMap<String,Object> ();
		List<MenuBean> menubeans = new ArrayList<MenuBean> ();
		List<MenuBean> menus = new ArrayList<MenuBean> ();
		child.put("basic", menubeans);
		
		MenuBean mb = new MenuBean();
		mb.setMenuid("10");
		mb.setIcon("icon-sys");
		mb.setMenuname("用户管理");
		mb.setMenus(menus);
		menubeans.add(mb);
		
		MenuBean mb5 = new MenuBean();
		mb5.setMenuid("112");
		mb5.setIcon("icon-nav");
		mb5.setMenuname("权限管理");
		mb5.setUrl("/authority/initAuthority.action");
		menus.add(mb5);
		
		MenuBean mb1 = new MenuBean();
		mb1.setMenuid("111");
		mb1.setIcon("icon-nav");
		mb1.setMenuname("角色管理");
		mb1.setUrl("/role/initRole.action");
		menus.add(mb1);
		
		
		return child;
	}
	
	/**
	 * 
	* @Description: TODO(获取下级菜单) 
	* @author songjia@richinfo.cn
	* @date 2015年10月29日 下午2:09:16
	 */
	private List<MenuBean> getChildMenu(String parentAuthId,Set<Authority> authorities,List<MenuBean> menus)
	{
		
		Iterator<Authority> itin = authorities.iterator();
		
		while(itin.hasNext())
		{
			Authority authchild = itin.next();
			if(parentAuthId.equals(authchild.getParentAuthId()))//Constants.ORIGIN_AUTH_ID:权限树的树根节点写死我“1”
			{
				
				MenuBean mb5 = new MenuBean();
				mb5.setMenuid(authchild.getId());
				mb5.setIcon(authchild.getAuthImg());
				mb5.setMenuname(authchild.getAuthName());
				mb5.setUrl(authchild.getMenuUrl());
				
				List<MenuBean> menuChilds = new ArrayList<MenuBean> ();
				menuChilds = getChildMenu(authchild.getId(), authorities, menuChilds);
				if(null != menuChilds &&menuChilds.size()>0)
				{
					mb5.setMenus(menuChilds);
				}
				
				menus.add(mb5);
			}
		}
		
		return menus;
	}
	

}
