package com.richinfo.manager.user.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.user.dao.UserRelaRoleMapper;
import com.richinfo.manager.user.service.UserRelaRoleService;

@Service("userRelaRoleService")
public class UserRelaRoleServiceImpl implements UserRelaRoleService{

	@Autowired
	UserRelaRoleMapper userRelaRoleMapper;
	
	@Override
	public void deleteRelaByUserId(String userId) {
		userRelaRoleMapper.deleteRelaByUserId(userId);
	}

	@Override
	public void deleteRelaByRoleId(String roleId) {
		userRelaRoleMapper.deleteRelaByRoleId(roleId);
	}

	@Override
	public void insertRela(Map<String, String> paramMap) {
		userRelaRoleMapper.insertRela(paramMap);
	}

	@Override
	public ResultBean updateUserRelaRole(String userId, String roles) {
		
		ResultBean resultBean = new ResultBean();
		//删除前对应关系
		userRelaRoleMapper.deleteRelaByUserId(userId);
		//保存现在存在关系
		Map<String,String> paramMap = null;
		String[] roleArr = roles.split(",");
		for(int i = 0;i < roleArr.length;i++){
			paramMap = new HashMap<String,String>();
			paramMap.put("id", UUID.randomUUID().toString());
			paramMap.put("userId", userId);
			paramMap.put("roleId", roleArr[i]);
			userRelaRoleMapper.insertRela(paramMap);
		}
		resultBean.setStatus("success");
		resultBean.setMessage("角色设置权限成功!");
		return resultBean;
		
	}
	
}
