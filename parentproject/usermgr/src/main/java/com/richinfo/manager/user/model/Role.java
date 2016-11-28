
package com.richinfo.manager.user.model;

import java.util.List;

import com.richinfo.manager.common.util.BaseModel;

/** 
  * @ClassName: Role 
  * @Description: T_YWPT_ROLE 表对应实体
  * @author songjia@richinfo.cn
  * @date 2016年11月18日 下午2:33:25 
  *  
  */
public class Role extends BaseModel{

	private String id;
	
	private String roleCode;
	
	private String roleName;
	
	private List<User> userList;
	
	private List<Authority> authList;

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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Authority> getAuthList() {
		return authList;
	}

	public void setAuthList(List<Authority> authList) {
		this.authList = authList;
	}

}
