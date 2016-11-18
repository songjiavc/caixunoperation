package com.richinfo.manager.user.model;

/** 
  * @ClassName: RelaUserRole 
  * @Description: 用户关联角色
  * @author songjia@richinfo.cn
  * @date 2016年11月18日 下午2:41:31 
  *  
  */
public class RelaAuthRole {
	
	private Role role;
	
	private Authority user;

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the user
	 */
	public Authority getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Authority user) {
		this.user = user;
	}
	
}
