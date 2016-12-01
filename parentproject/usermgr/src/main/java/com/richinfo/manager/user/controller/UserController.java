package com.richinfo.manager.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.common.bean.ResultBeanDataList;
import com.richinfo.manager.common.exception.GlobalExceptionHandler;
import com.richinfo.manager.common.util.DateUtil;
import com.richinfo.manager.common.util.LoginUtils;
import com.richinfo.manager.user.bean.UserBean;
import com.richinfo.manager.user.model.Role;
import com.richinfo.manager.user.model.User;
import com.richinfo.manager.user.service.UserRelaRoleService;
import com.richinfo.manager.user.service.UserService;


/**
  * 
  * @ClassName: RoleController 
  * @Description: 角色管理
  * @author songjia@richinfo.cn
  * @date 2015年10月19日 上午9:12:37 
  *
  */
@Controller
@RequestMapping("/user")
public class UserController extends GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRelaRoleService userRelaRoleService;
	
	
	/**
	 * @param request
	 * @param model
	 * @param httpSession
	 * @desc  初始化权限页面
	 * @return
	 */
	@RequestMapping(value = "/initUser.action")
	public String initRole(HttpServletRequest request,ModelMap model,HttpSession httpSession) {
 		///authority/initAuthority.action
 		return "user/userAccount";
	}
	
	/** 
	  * @Description: 根据角色id获取角色详细内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 上午10:42:34 
	  * @param id
	  * @param model
	  * @param httpSession
	  * @return
	  * @throws Exception 
	  */
	@RequestMapping(value = "/getDetailAccount", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getDetailUser(
			@RequestParam(value="id",required=true) String id,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		UserBean userBean = userService.getUserById(id);
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		rtnMap.put("id", userBean.getId());
		rtnMap.put("userCode", userBean.getUserCode());
		rtnMap.put("userName", userBean.getUserName());
		rtnMap.put("status", userBean.getStatus());
		rtnMap.put("telephone", userBean.getTelephone());
		return rtnMap;
	}
	 
	/**
	  * @Description:获取用户所属角色列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月24日 上午9:56:29
	  */
	 @RequestMapping(value = "/getRoleOfUserId", method = RequestMethod.GET)
	 public @ResponseBody ResultBeanDataList<Role> getAuthListOfRole(
			@RequestParam(value="id",required=true) String id,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		 ResultBeanDataList<Role> resultBeanData = new ResultBeanDataList<Role>();
		 User user = userService.getUserRelaRoleByUserId(id);
		 List<Role> roleList = new ArrayList<Role>();
		 if(user.getRole() != null){
			 roleList.add(user.getRole());
		 }
		 resultBeanData.setRows(roleList);
		 resultBeanData.setTotal(1);
		 return resultBeanData;
	}
	 
	
	/** 
	  * @Description: 保存角色信息
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 上午11:28:37 
	  * @param id
	  * @param code
	  * @param name
	  * @param model
	  * @param httpSession
	  * @return 
	  */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public  @ResponseBody ResultBean saveOrUpdate(
		    @RequestParam(value="id",required=false) String id,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="telephone",required=false) String telephone,
			@RequestParam(value="password",required=false) String password,
			@RequestParam(value="status",required=false) Character status,
			ModelMap model,HttpSession httpSession)
	{
		UserBean userBean = new UserBean();
		userBean.setId(id);
		userBean.setUserCode(code);
		userBean.setUserName(name);
		userBean.setPassword(password);
		userBean.setTelephone(telephone);
		userBean.setStatus(status);
		if(StringUtils.isEmpty(id)){
			userBean.setCreater(LoginUtils.getAuthenticatedUserCode(httpSession));
		}else{
			userBean.setUpdater(LoginUtils.getAuthenticatedUserCode(httpSession));
		}
		return userService.insertOrUpdateUser(userBean);
	}
	 
	/**
	 * 
	 * @Description: 删除角色实体
	 * @author songjia@richinfo.cn
	 * @date 2015年10月19日 下午2:30:04
	 */
	@RequestMapping(value = "/deleteAccountByIds", method = RequestMethod.POST)
	public @ResponseBody ResultBean deleteUser(
			@RequestParam(value="ids",required=false) String ids,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		return userService.deleteUserByIds(ids);
	}
	 
	 /**
	  * 
	 * @Description: 用户角色关联关联（添加删除）
	 * @author songjia@richinfo.cn
	 * @date 2015年10月19日 下午4:29:17
	  */
	@RequestMapping(value = "/manageUserRelaRole", method = RequestMethod.POST)
	public @ResponseBody ResultBean manageRoleAndauth(
			@RequestParam(value="userId",required=false) String userId,
			@RequestParam(value="role",required=false) String roles,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		//服务层的事物保证
		return userRelaRoleService.updateUserRelaRole(userId, roles);
	}
 

	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public  @ResponseBody ResultBeanDataList<UserBean> getUserList(
			@RequestParam(value="page",required=false) int page,
			@RequestParam(value="rows",required=false) int rows,
			@RequestParam(value="status",required=false) Character status,
			@RequestParam(value="userCode",required=false) String userCode,
			@RequestParam(value="userName",required=false) String userName,
			@RequestParam(value="telephone",required=false) String telephone,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		UserBean userBean = new UserBean();
		if(status != null){
			userBean.setStatus(status);
		}
		if(!StringUtils.isEmpty(userCode)){
			userBean.setUserCode(userCode);
		}
		if(!StringUtils.isEmpty(userName)){
			userBean.setUserName(userName);
		}
		ResultBeanDataList<UserBean> rtnDataList = new ResultBeanDataList<UserBean>();
		PageHelper.startPage(page, rows); //
		List<User> userList = userService.getUserListByWhere(userBean);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		if(userList != null && userList.size() > 0){
			for(User user : userList){
				userBean = new UserBean();
				userBean.setId(user.getId());
				userBean.setUserCode(user.getUserCode());
				userBean.setUserName(user.getUserName());
				userBean.setTelephone(user.getTelephone());
				userBean.setStatus(user.getStatus());
				userBean.setCreater(user.getCreater());
				if(user.getRole() != null){
					userBean.setRoleName(user.getRole().getRoleName());
				}
				userBean.setCreateTime(DateUtil.formatDate(user.getCreateTime(), DateUtil.FULL_DATE_FORMAT));
				userBeanList.add(userBean);
			}
		}
		rtnDataList.setRows(userBeanList);
		rtnDataList.setStatus("success");
		rtnDataList.setMessage("");
		rtnDataList.setTotal(pageInfo.getTotal());
		return rtnDataList;
	}
	 
	/**
	 * 
	 * @Description: 校验角色编码是否重复
	 * @author songjia@richinfo.cn
	 * @date 2015年10月20日 下午2:14:25
	 */
	@RequestMapping(value = "/checkValue", method = RequestMethod.GET)
	public  @ResponseBody ResultBean  checkValue(
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="code",required=false) String code,
			ModelMap model,HttpSession httpSession)
	{
		ResultBean resultBean = new ResultBean ();
		UserBean userBean = new UserBean();
		userBean.setId(id);
		userBean.setUserCode(code);
		resultBean.setExist(userService.checkValue(userBean));
		return resultBean;
	}
	
}
