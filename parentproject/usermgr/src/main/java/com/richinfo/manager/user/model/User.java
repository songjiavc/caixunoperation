package com.richinfo.manager.user.model;

import com.richinfo.manager.common.util.BaseModel;


/** 
  * @ClassName: User 
  * @Description: mybaitis 对应实体文件
  * @author songjia@richinfo.cn
  * @date 2016年11月18日 下午2:32:45 
  *  
  */
public class User extends BaseModel{
	
	private String id;
	
	private String userCode;
	
	private String userName;
	
	private String password;
	
	private String telephone;
	
	private Role role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
