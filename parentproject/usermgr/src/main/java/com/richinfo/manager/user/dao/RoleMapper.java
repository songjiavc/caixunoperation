package com.richinfo.manager.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.richinfo.manager.user.model.Role;

public interface RoleMapper {
	
	
	/** 
	  * @Description: 根据条件查询列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 下午3:03:45
	  * @param role
	  * @return 
	  */
	List<Role> getRoleListByWhere(Role role);
	
	/** 
	  * @Description: 根据id删除对应权限
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午8:59:43 
	  * 
	  * @param paramMap 
	  */
	@Autowired
	void deleteRoleById(Map<String,String> paramMap);
	
	/** 
	  * @Description: 插入权限内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午8:59:56 
	  * 
	  * @param authority 
	  */
	@Autowired
	void insertRole(Role role);
	
	/** 
	  * @Description: 更新角色内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:00:04 
	  * 
	  * @param role 
	  */
	@Autowired
	void updateRole(Role role);
	
	/** 
	  * @Description: 判断code是否重复
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:00:14 
	  * 
	  * @param authority
	  * @return 
	  */
	@Autowired
	Integer checkValue(Role role);
	
	/** 
	  * @Description: 根据id获取权限对应实体内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:34:25 
	  * 
	  * @param authId
	  * @return 
	  */
	@Autowired
	Role getRoleByid(String roleId);
	
	/** 
	  * @Description: 通过角色id级联查询权限列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 下午5:38:07 
	  * @param id
	  * @return 
	  */
	@Autowired
	Role getRoleRelaAuthByRoleId(String id);
	
}
