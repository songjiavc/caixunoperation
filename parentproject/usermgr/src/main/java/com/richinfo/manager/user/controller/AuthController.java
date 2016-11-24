package com.richinfo.manager.user.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.richinfo.manager.common.util.LoginUtils;
import com.richinfo.manager.user.bean.AuthorityBean;
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
	 * 
	* @Description: 保存或者修改权限 
	* @author songjia@richinfo.cn
	* @date 2015年10月9日 下午2:38:35
	 */
	@RequestMapping(value = "/saveOrUpdate.action", method = RequestMethod.GET)
	public @ResponseBody ResultBean saveOrUpdate(
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="authName",required=false) String authName,
			@RequestParam(value="parentAuthId",required=false) String parentAuthId,
			@RequestParam(value="url",required=false) String url,
			@RequestParam(value="authImg",required=false) String authImg,
			@RequestParam(value="status",required=false) Character status,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		ResultBean resultBean = new ResultBean();
		AuthorityBean authorityBean = new AuthorityBean();
		authorityBean.setId(id);
		authorityBean.setCode(code);
		authorityBean.setAuthName(authName);
		authorityBean.setParentAuthId(parentAuthId);
		authorityBean.setMenuUrl(url);
		authorityBean.setAuthImg(authImg);
		authorityBean.setStatus(status);
		if(StringUtils.isEmpty(id)){
			authorityBean.setCreater(LoginUtils.getAuthenticatedUserCode(httpSession));
			authorityBean.setCreateTime(new Timestamp(System.currentTimeMillis()));
		}else{
			authorityBean.setUpdater(LoginUtils.getAuthenticatedUserCode(httpSession));
			authorityBean.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		}
		authorityService.insertOrUpdateAuthority(authorityBean);
		resultBean.setStatus("success");
		resultBean.setMessage("权限保存成功!");
		return resultBean;
	}
	

	/**
	 * 
	* @Description: TODO(根据code获取权限的详细信息（根据唯一条件获取数据）) 
	* @author songjia@richinfo.cn
	* @date 2015年10月10日 上午10:16:35
	 */
	@RequestMapping(value = "/getDetailAuth.action", method = RequestMethod.GET)
	public @ResponseBody Authority getDetailAuth(
			@RequestParam(value="code",required=false) String authId,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		Authority authority = new Authority();
		
		authority = authorityService.getAuthorityById(authId);
		
		return authority;
	}
	
	/**
	 * 
	* @Description: TODO(查询权限数据带分页) 
	* @author songjia@richinfo.cn
	* @date 2015年10月14日 上午8:58:45
	 */
	@RequestMapping(value = "/getAuthList", method = RequestMethod.GET)
	public @ResponseBody ResultBeanDataList<Authority> getAuthList(
			@RequestParam(value="page",required=false) int page,
			@RequestParam(value="rows",required=false) int rows,
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="parentCode",required=false) String parentCode,
			ModelMap model,HttpSession httpSession) throws Exception
	{
		ResultBeanDataList<Authority> rtnDataList = new ResultBeanDataList<Authority>();
		PageHelper.startPage(page, rows); //
		List<Authority> authList = authorityService.getAuthorityListByParentId(parentCode);
		PageInfo<Authority> pageInfo = new PageInfo<Authority>(authList);
		rtnDataList.setRows(authList);
		rtnDataList.setStatus("success");
		rtnDataList.setMessage("");
		rtnDataList.setTotal(pageInfo.getTotal());
		return rtnDataList;
	}
	
	
	/** 
	  * @Description: 权限输入值校验，用来校验code唯一性和authname唯一性
	  * @author songjia@richinfo.cn
	  * @date 2016年11月24日 上午10:32:10 
	  * 
	  * @param id
	  * @param code
	  * @param authname
	  * @param parentAuth
	  * @param status
	  * @param model
	  * @param httpSession
	  * @return
	  * @throws Exception 
	  */
	@RequestMapping(value = "/checkValue", method = RequestMethod.POST)
	public @ResponseBody ResultBean  checkValue(
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="authname",required=false) String authName,
			@RequestParam(value="status",required=false) String status,
			ModelMap model,HttpSession httpSession) throws Exception {
		AuthorityBean authorityBean = new AuthorityBean();
		authorityBean.setCode(code);
		authorityBean.setAuthName(authName);
		authorityBean.setStatus('1');
		authorityBean.setId(id);
		ResultBean resultBean = new ResultBean();
		resultBean.setExist(authorityService.checkValue(authorityBean));
		return resultBean;
	}
	
	
	
}
