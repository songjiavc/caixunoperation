package com.richinfo.manager.user.service;

import java.util.Map;

import com.richinfo.manager.common.bean.ResultBean;


public interface RoleRelaAuthService {
	/** 
	  * @Description : 根据角色id删除角色权限关联关系
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 上午10:07:09 
	  * @param roleId 
	  */
	public void deleteRelaByRoleId(String roleId);
	
	/** 
	  * @Description: 根据权限 id 删除对应的角色关联关系
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 上午10:07:42 
	  * 
	  * @param authId 
	  */
	public void deleteRelaByAuthId(String authId);
	
	/** 
	  * @Description: 插入权限与角色的关联（权限与角色是多对多的关系）
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 上午10:08:10 
	  * @param paramMap 
	  */
	public void insertRela(Map<String,String> paramMap);
	
	/** 
	  * @Description: 服务层事物控制保证在一个事物中
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 上午10:17:31 
	  * @param roleId
	  * @param auths
	  * @return 
	  */
	public ResultBean updateRoleRelaAuth(String roleId,String auths);
}
