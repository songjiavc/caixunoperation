package com.richinfo.manager.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.richinfo.manager.user.model.Role;
import com.richinfo.manager.user.model.User;

public interface UserMapper {
	
	
	/** 
	  * @Description: 根据条件查询列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 下午3:03:45
	  * @param role
	  * @return 
	  */
	List<User> getUserListByWhere(User user);
	
	/** 
	  * @Description: 获取符合条件的用户总数
	  * @author songjia@richinfo.cn
	  * @date 2016年11月30日 下午4:30:58 
	  * 
	  * @param user
	  * @return 
	  */
	Integer getTotalByWhere(User user);
	
	/** 
	  * @Description: 根据id删除对应权限
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午8:59:43 
	  * 
	  * @param paramMap 
	  */
	@Autowired
	void deleteUserById(Map<String,String> paramMap);
	
	/** 
	  * @Description: 批量通过id删除
	  * @author songjia@richinfo.cn
	  * @date 2016年11月29日 下午1:29:37 
	  * 
	  * @param items 
	  */
	@Autowired
	void batchRemoveByIds(List<String> items);
	/** 
	  * @Description: 插入权限内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午8:59:56 
	  * 
	  * @param authority 
	  */
	@Autowired
	void insertUser(User user);
	
	/** 
	  * @Description: 更新角色内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:00:04 
	  * 
	  * @param role 
	  */
	@Autowired
	void updateUser(User user);
	
	/** 
	  * @Description: 判断code是否重复
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:00:14 
	  * 
	  * @param authority
	  * @return 
	  */
	@Autowired
	Integer checkValue(User user);
	
	/** 
	  * @Description: 根据id获取权限对应实体内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月25日 上午9:34:25 
	  * 
	  * @param authId
	  * @return 
	  */
	@Autowired
	User getUserById(String userId);
	
	/** 
	  * @Description: 通过角色id级联查询权限列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 下午5:38:07 
	  * @param id
	  * @return 
	  */
	@Autowired
	User getUserRelaRoleByUserId(String id);
	
}
