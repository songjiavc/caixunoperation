package com.richinfo.manager.user.service;

import java.util.List;

import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.user.bean.UserBean;
import com.richinfo.manager.user.model.User;

public interface UserService {

	/** 
	  * @Description: 根据userid获取user实体
	  * @author songjia@richinfo.cn
	  * @date 2016年11月30日 上午9:08:35 
	  * @param id
	  * @return 
	  */
	public UserBean getUserById(String id);

	/** 
	  * @Description: 根据userid获取用户关联角色
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 下午5:36:27 
	  * 
	  * @param id
	  * @return 
	  */
	public User getUserRelaRoleByUserId(String id);
	
	/** 
	  * @Description: 根据条件查询用户列表
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 上午10:35:07 
	  * 
	  * @param role
	  * @return 
	  */
	public List<User> getUserListByWhere(UserBean userBean);

	/** 
	  * @Description: 获取符合条件的user总数
	  * @author songjia@richinfo.cn
	  * @date 2016年11月30日 下午4:32:15 
	  * 
	  * @param userBean
	  * @return 
	  */
	public Integer getTotalByWhere(UserBean userBean);
	/** 
	  * @Description: 添加或者更新用户信息
	  * @author songjia@richinfo.cn
	  * @date 2016年11月21日 下午1:25:26 
	  * 
	  * @param authority
	  * @return 
	  */
	public ResultBean insertOrUpdateUser(UserBean userBean);
	
	/** 
	  * @Description: 根据角色ids删除角色，注意与角色关联的用户和权限注意提示内容
	  * @author songjia@richinfo.cn
	  * @date 2016年11月28日 上午10:56:49 
	  * @param ids
	  * @return 
	  */
	public ResultBean deleteUserByIds(String ids);
}
