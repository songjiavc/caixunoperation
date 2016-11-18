package com.richinfo.manager.user.model;

import java.util.List;

import com.richinfo.manager.common.util.BaseModel;


/** 
  * @ClassName: Authority 
  * @Description: 权限表对应实体  T_YWPT_AUTHORITY
  * @author songjia@richinfo.cn
  * @date 2016年11月18日 下午2:40:13 
  *  
  */
public class Authority extends BaseModel{
	
	private String id;
	
	private String code;
	
	private String authName;
	
	private String parentAuthId;
	
	private String menuUrl;
	
	private String authImg;

	private List<Role> roleList;
	
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
	 * @return the authName
	 */
	public String getAuthName() {
		return authName;
	}

	/**
	 * @param authName the authName to set
	 */
	public void setAuthName(String authName) {
		this.authName = authName;
	}

	/**
	 * @return the parentAuthId
	 */
	public String getParentAuthId() {
		return parentAuthId;
	}

	/**
	 * @param parentAuthId the parentAuthId to set
	 */
	public void setParentAuthId(String parentAuthId) {
		this.parentAuthId = parentAuthId;
	}

	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * @return the authImg
	 */
	public String getAuthImg() {
		return authImg;
	}

	/**
	 * @param authImg the authImg to set
	 */
	public void setAuthImg(String authImg) {
		this.authImg = authImg;
	}

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
