package com.richinfo.manager.user.service;

import java.util.List;

import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.user.bean.AuthorityBean;
import com.richinfo.manager.user.bean.RoleBean;
import com.richinfo.manager.user.model.Role;

public interface RoleService {

	/**
	 * 
	* @Description: TODO(根据id获取角色详情) 
	* @author songjia@richinfo.cn
	* @date 2015年10月19日 下午2:07:38
	 */
	public RoleBean getRoleById(String id);

	/** 
	  * @Description: 根据角色id获取权限列表内容 
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 下午5:36:27 
	  * 
	  * @param id
	  * @return 
	  */
	public Role getRoleRelaAuthByRoleId(String id);
	
	/** 
	  * @Description: 根据角色传入条件获取角色列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 上午10:35:07 
	  * 
	  * @param role
	  * @return 
	  */
	public List<Role> getRoleListByWhere(RoleBean roleBean);

	/** 
	  * @Description: 添加或者删除
	  * @author songjia@richinfo.cn
	  * @date 2016年11月21日 下午1:25:26 
	  * 
	  * @param authority
	  * @return 
	  */
	public ResultBean insertOrUpdateRole(RoleBean roleBean);
	

	
	/** 
	  * @Description: 根据角色ids删除角色，注意与角色关联的用户和权限注意提示内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 上午10:56:49 
	  * @param ids
	  * @return 
	  */
	public ResultBean deleteRoleByIds(String ids);
	
	/** 
	  * @Description: 角色管理权限内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 上午11:01:43 
	  * @param id
	  * @param auths
	  * @return 
	  */
	public ResultBean manageRoleAndAuth(String id,String[] auths);
}
