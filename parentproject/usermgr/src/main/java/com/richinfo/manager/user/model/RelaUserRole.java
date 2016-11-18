/**   
* @Title: RelaUserRole.java 
* @Package com.richinfo.manager.user.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author songjia@richinfo.cn
* @date 2016年11月18日 下午2:25:09 
* @version V1.0   
*/
package com.richinfo.manager.user.model;

/** 
  * @ClassName: RelaUserRole 
  * @Description: 用户角色关联关系实体
  * @author songjia@richinfo.cn
  * @date 2016年11月18日 下午2:25:09 
  *  
  */
public class RelaUserRole {
	
	private Role role;
	
	private User user;

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
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}
