package com.richinfo.manager.user.dao;

import java.util.List;
import java.util.Map;

public interface UserRelaRoleMapper {
	
	/** 
	  * @Description : 根据角色id删除角色权限关联关系
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 上午10:07:09 
	  * @param roleId 
	  */
	void deleteRelaByUserId(String userId);
	
	/** 
	  * @Description: 根据角色id批量删除对应关系
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 下午1:45:33 
	  * 
	  * @param items 
	  */
	void batchRemoveByUserIds(List<String> items);
	
	/** 
	  * @Description: 根据权限 id 删除对应的角色关联关系
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 上午10:07:42 
	  * 
	  * @param authId 
	  */
	void deleteRelaByRoleId(String roleId);
	
	/** 
	  * @Description: 插入权限与角色的关联（权限与角色是多对多的关系）
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 上午10:08:10 
	  * @param paramMap 
	  */
	void insertRela(Map<String,String> paramMap);
	
	
}
