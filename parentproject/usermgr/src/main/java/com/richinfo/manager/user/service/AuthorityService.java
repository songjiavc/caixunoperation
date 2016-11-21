package com.richinfo.manager.user.service;

import java.util.List;

import com.richinfo.manager.user.model.Authority;


public interface AuthorityService {
	/** 
	  * @Description: 根据父亲节点id获取所有子节点内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月21日 下午1:20:43 
	  * @param authParentId
	  * @return 
	  */
	public List<Authority> getChildrenAuthorityList(String authParentId);
	
	/** 
	  * @Description: 根据权限id删除对应权限，逻辑删除
	  * @author songjia@richinfo.cn
	  * @date 2016年11月21日 下午1:23:21 
	  * 
	  * @param authId 
	  */
	public void deleteAuthorityById(String authId);
	
	/** 
	  * @Description: 添加或者删除
	  * @author songjia@richinfo.cn
	  * @date 2016年11月21日 下午1:25:26 
	  * 
	  * @param authority
	  * @return 
	  */
	public String insertOrUpdateAuthority(Authority authority);
	
}
