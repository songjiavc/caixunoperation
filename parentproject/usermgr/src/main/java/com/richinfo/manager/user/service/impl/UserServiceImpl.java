package com.richinfo.manager.user.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.common.util.MD5Util;
import com.richinfo.manager.user.bean.UserBean;
import com.richinfo.manager.user.dao.UserMapper;
import com.richinfo.manager.user.dao.UserRelaRoleMapper;
import com.richinfo.manager.user.model.User;
import com.richinfo.manager.user.service.UserService;

import oracle.net.aso.MD5;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserRelaRoleMapper userRelaRoleMapper;
	
	@Override
	public UserBean getUserById(String id) {
		User user = userMapper.getUserById(id);
		UserBean roleBean = new UserBean();
		roleBean.setId(user.getId());
		roleBean.setUserCode(user.getUserCode());
		roleBean.setUserName(user.getUserName());
		roleBean.setTelephone(user.getTelephone());
		return roleBean;
	}

	@Override
	public List<User> getUserListByWhere(UserBean userBean){
		User user = new User();
		user.setUserCode(userBean.getUserCode());
		user.setUserName(userBean.getUserName());
		user.setTelephone(userBean.getTelephone());
		user.setStatus(userBean.getStatus());
		return userMapper.getUserListByWhere(user);
	}
	
	@Override
	public Integer getTotalByWhere(UserBean userBean){
		User user = new User();
		user.setUserCode(userBean.getUserCode());
		user.setUserName(userBean.getUserName());
		user.setTelephone(userBean.getTelephone());
		user.setStatus(userBean.getStatus());
		return userMapper.getTotalByWhere(user);
	}

	@SuppressWarnings("finally")
	@Override
	public ResultBean insertOrUpdateUser(UserBean userBean) {
		//判断是删除还是修改
		ResultBean resultBean = new ResultBean();
		User user = new User();
		try{
			String id = userBean.getId();
			if(StringUtils.isEmpty(id)){
				//id为空位添加
				//初始化创建人和创建按时间
				id = UUID.randomUUID().toString();
				user.setId(id);
				user.setUserCode(userBean.getUserCode());
				user.setUserName(userBean.getUserName());
				user.setTelephone(userBean.getTelephone());
				user.setPassword(MD5Util.MD5(userBean.getPassword()));  //密码用MD5加密
				user.setCreater(userBean.getCreater());
				user.setCreateTime(new Timestamp(System.currentTimeMillis()));
				user.setStatus('1');
				userMapper.insertUser(user);
				resultBean.setStatus("success");
				resultBean.setMessage("用户添加成功！");
			}else{
				//id不为空位修改
				user.setId(id);
				user.setUserCode(userBean.getUserCode());
				user.setUserName(userBean.getUserName());
				user.setUpdater(userBean.getUpdater());
				user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				user.setStatus('1');
				//如果是开启
				userMapper.updateUser(user);
				resultBean.setStatus("success");
				resultBean.setMessage("用户修改成功！");
			}
		}catch(Exception ex){
			resultBean.setStatus("failure");
			resultBean.setMessage("用户保存失败！");
			logger.error(resultBean.getMessage() + ex.getMessage());
		}finally{
			return resultBean;
		}
	}

	@Override
	public ResultBean deleteUserByIds(String ids) {
		//删除角色则将角色关联的权限内容一并删除
		ResultBean resultBean = new ResultBean();
		String[] idsArr = ids.split(",");
		List<String> idList = Arrays.asList(idsArr);
		//先删除对应关系
		userRelaRoleMapper.batchRemoveByUserIds(idList);
		//删除实体内容
		userMapper.batchRemoveByIds(idList);
		resultBean.setStatus("success");
		resultBean.setMessage("删除成功！");
		return resultBean;
	}

	@Override
	public User getUserRelaRoleByUserId(String id) {
		return userMapper.getUserRelaRoleByUserId(id);
	}

}
