package com.richinfo.manager.user.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.richinfo.manager.user.bean.RoleBean;
import com.richinfo.manager.user.model.Authority;
import com.richinfo.manager.user.model.Role;
import com.richinfo.manager.user.service.AuthorityService;
import com.richinfo.manager.user.service.RoleRelaAuthService;
import com.richinfo.manager.user.service.RoleService;


/**
 * 
  * @ClassName: RoleController 
  * @Description: 角色管理
  * @author songjia@richinfo.cn
  * @date 2015年10月19日 上午9:12:37 
  *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	 
	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private RoleRelaAuthService roleRelaAuthService;
	
	
	/**
	 * @param request
	 * @param model
	 * @param httpSession
	 * @desc  初始化权限页面
	 * @return
	 */
	@RequestMapping(value = "/initRole.action")
	public String initRole(HttpServletRequest request,ModelMap model,HttpSession httpSession) {
 		///authority/initAuthority.action
 		return "user/roleManage";
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
	@RequestMapping(value = "/getDetailRole", method = RequestMethod.GET)
	 public @ResponseBody RoleBean getDetailRole(
			@RequestParam(value="id",required=true) String id,
			ModelMap model,HttpSession httpSession) throws Exception
	 {
		return roleService.getRoleById(id);
	 }
	 
	/**
	  * @Description:获取指定角色对应的权限列表数据
	  * @author songjia@richinfo.cn
	  * @date 2016年11月24日 上午9:56:29
	  */
	 @RequestMapping(value = "/getAuthListOfRole", method = RequestMethod.GET)
	 public @ResponseBody List<Authority> getAuthListOfRole(
			@RequestParam(value="id",required=true) String id,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		 Role role = roleService.getRoleRelaAuthByRoleId(id);
		 return role.getAuthList();
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
			ModelMap model,HttpSession httpSession)
	{
		RoleBean roleBean = new RoleBean();
		roleBean.setId(id);
		roleBean.setRoleCode(code);
		roleBean.setRoleName(name);
		if(StringUtils.isEmpty(id)){
			roleBean.setCreater(LoginUtils.getAuthenticatedUserCode(httpSession));
		}else{
			roleBean.setUpdater(LoginUtils.getAuthenticatedUserCode(httpSession));
		}
		return roleService.insertOrUpdateRole(roleBean);
	}
	 
	/**
	 * 
	 * @Description: 删除角色实体
	 * @author songjia@richinfo.cn
	 * @date 2015年10月19日 下午2:30:04
	 */
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	public @ResponseBody ResultBean deleteRole(
			@RequestParam(value="ids",required=false) String ids,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		
		return roleService.deleteRoleByIds(ids);
	}
	 
	 /**
	  * 
	 * @Description: 权限角色关联关联（添加删除）
	 * @author songjia@richinfo.cn
	 * @date 2015年10月19日 下午4:29:17
	  */
	@RequestMapping(value = "/manageRoleRelaAuth", method = RequestMethod.POST)
	public @ResponseBody ResultBean manageRoleAndauth(
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="authes",required=false) String authes,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		//服务层的事物保证
		return roleRelaAuthService.updateRoleRelaAuth(id, authes);
	}
 

	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	public  @ResponseBody ResultBeanDataList<RoleBean> getRoleList(
			@RequestParam(value="page",required=false) int page,
			@RequestParam(value="rows",required=false) int rows,
			@RequestParam(value="status",required=false) Character status,
			@RequestParam(value="roleCode",required=false) String roleCode,
			@RequestParam(value="roleName",required=false) String roleName,
//				@RequestParam(value="status",required=false) String status,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		RoleBean roleBean = new RoleBean();
		if(status != null){
			roleBean.setStatus(status);
		}
		if(!StringUtils.isEmpty(roleCode)){
			roleBean.setRoleCode(roleCode);
		}
		if(!StringUtils.isEmpty(roleName)){
			roleBean.setRoleName(roleName);
		}
		ResultBeanDataList<RoleBean> rtnDataList = new ResultBeanDataList<RoleBean>();
		PageHelper.startPage(page, rows); //
		List<Role> roleList = roleService.getRoleListByWhere(roleBean);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
		List<RoleBean> roleBeanList = new ArrayList<RoleBean>();
		if(roleList != null && roleList.size() > 0){
			for(Role role : roleList){
				roleBean = new RoleBean();
				roleBean.setId(role.getId());
				roleBean.setRoleCode(role.getRoleCode());
				roleBean.setRoleName(role.getRoleName());
				roleBean.setCreater(role.getCreater());
				roleBean.setCreateTime(DateUtil.formatDate(role.getCreateTime(), DateUtil.FULL_DATE_FORMAT));
				roleBeanList.add(roleBean);
			}
		}
		rtnDataList.setRows(roleBeanList);
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
	@RequestMapping(value = "/checkRoleValue", method = RequestMethod.POST)
	public  @ResponseBody ResultBean  checkRoleValue(
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="code",required=false) String code,
			ModelMap model,HttpSession httpSession) 
	{
		ResultBean resultBean = new ResultBean ();
		resultBean.setExist(false);
		return resultBean;
	}
	
}
