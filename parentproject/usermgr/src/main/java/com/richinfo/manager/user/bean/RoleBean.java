package com.richinfo.manager.user.bean;

import com.richinfo.manager.common.util.BaseBean;



/** 
  * @ClassName: RoleBean 
  * @Description: 作为前台传入参数和后台返回参数的中间实体
  * @author songjia@richinfo.cn
  * @date 2016年11月28日 上午10:38:40 
  *  
  */
public class RoleBean extends BaseBean{
	

	private String id;
	
	private String roleCode;
	
	private String roleName;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
