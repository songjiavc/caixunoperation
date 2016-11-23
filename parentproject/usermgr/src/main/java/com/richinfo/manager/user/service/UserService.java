package com.richinfo.manager.user.service;

import java.awt.print.Pageable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.richinfo.manager.common.util.QueryResult;
import com.richinfo.manager.user.model.Role;
import com.richinfo.manager.user.model.User;



/** 
  * @ClassName: UserService 
  * @Description:  
  * @author songj@sdfcp.com
  * @date 2015年10月12日 上午11:00:15 
  *  
  */
@Service("userService")
public interface UserService {
	
	/** 
	  * @Description: 获取权限表所有数据
	  * @author songj@sdfcp.com
	  * @date 2015年9月25日 上午8:58:51 
	  * @return 
	  */
	public List<User> findAll();

	/** 
	  * @Description: 保存更新用户信息
	  * @author songj@sdfcp.com
	  * @date 2015年10月12日 上午11:16:13 
	  * @param user 
	  */
	public void saveOrUpdate(User accountBean,String userId) throws BizException;

	/** 
	  * @Description: TODO(这里用一句话描述这个类的作用)
	  * @author songj@sdfcp.com
	  * @date 2015年10月15日 上午10:39:51 
	  * @param id
	  * @return 
	  */
	public User getUserById(String id) throws BizException;
	
	/** 
	  * @Description: 判断code是否有重复记录
	  * @author songj@sdfcp.com
	  * @date 2015年10月15日 下午1:16:03 
	  * @param code
	  * @return 
	  */
	public User getUserByCode(String code);

	/** 
	  * @Description:分页查询
	  * @author songj@sdfcp.com
	  * @date 2015年10月13日 下午1:25:25 
	  * @param whereJpql
	  * @param queryParams
	  * @param orderby
	  * @param pageable
	  * @return 
	  */
	public Map<String,Object>  getScrollDataByJpql(Class<User> entityClass,String whereJpql, Object[] queryParams,LinkedHashMap<String, String> orderby, Pageable pageable);
	
	/** 
	  * @Description: 删除帐号
	  * @author songj@sdfcp.com
	  * @date 2015年10月20日 下午2:20:46 
	  * @param ids
	  * @throws BizException 
	  */
	public void deleteAccountByIds(String[] ids,String userId) throws BizException;
	


	
	/** 
	  * @Description: 修改保存方法
	  * @author songj@sdfcp.com
	  * @date 2015年11月18日 下午2:05:03 
	  * @param newPassword
	  * @param userCode 
	  */
	public void savePassword(String newPassword,String userCode);
	
	
	/** 
	  * @Description: 根据userId获取角色列表
	  * @author songj@sdfcp.com
	  * @date 2015年12月7日 下午3:30:31 
	  * @param userId
	  * @return 
	  */
	public List<Role> findRolesByUserId(String userId);
	
	/** 
	  * @Description: 业务层查询用户列表
	  * @author songj@sdfcp.com
	  * @date 2015年12月2日 下午2:08:51 
	  * @param entityClass
	  * @param whereJpql
	  * @param queryParams
	  * @param orderby
	  * @param pageable
	  * @return 
	  */
	public QueryResult<User> getUserList(Class<User> entityClass, String whereJpql, Object[] queryParams, 
			LinkedHashMap<String, String> orderby, Pageable pageable);
	
	/** 
	  * @Description: 业务层查询用户列表
	  * @author songj@sdfcp.com
	  * @date 2015年12月2日 下午2:08:51 
	  * @param entityClass
	  * @param whereJpql
	  * @param queryParams
	  * @param orderby
	  * @param pageable
	  * @return 
	  */
	public QueryResult<User> getAgentList(Class<User> entityClass, String whereJpql, Object[] queryParams, 
			LinkedHashMap<String, String> orderby, Pageable pageable);
	
}
