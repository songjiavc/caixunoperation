
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
	
	private String code;
	
	private String name;
	
	private List<User> userList;
	
	private List<Authority> authList;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * @return the authList
	 */
	public List<Authority> getAuthList() {
		return authList;
	}

	/**
	 * @param authList the authList to set
	 */
	public void setAuthList(List<Authority> authList) {
		this.authList = authList;
	}
	
}
