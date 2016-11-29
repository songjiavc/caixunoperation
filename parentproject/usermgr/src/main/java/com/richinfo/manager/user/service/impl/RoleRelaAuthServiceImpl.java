package com.richinfo.manager.user.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richinfo.manager.common.bean.ResultBean;
import com.richinfo.manager.user.dao.RoleRelaAuthMapper;
import com.richinfo.manager.user.service.RoleRelaAuthService;

@Service("roleRelaAuthService")
public class RoleRelaAuthServiceImpl implements RoleRelaAuthService{

	@Autowired
	RoleRelaAuthMapper roleRelaAuthMapper;
	
	@Override
	public void deleteRelaByRoleId(String roleId) {
		roleRelaAuthMapper.deleteRelaByRoleId(roleId);
	}

	@Override
	public void deleteRelaByAuthId(String authId) {
		roleRelaAuthMapper.deleteRelaByAuthId(authId);
	}

	@Override
	public void insertRela(Map<String, String> paramMap) {
		roleRelaAuthMapper.insertRela(paramMap);
	}

	@Override
	public ResultBean updateRoleRelaAuth(String roleId, String auths) {
		ResultBean resultBean = new ResultBean();
		//删除前对应关系
		roleRelaAuthMapper.deleteRelaByRoleId(roleId);
		//保存现在存在关系
		Map<String,String> paramMap = null;
		String[] authArr = auths.split(",");
		for(int i = 0;i < authArr.length;i++){
			paramMap = new HashMap<String,String>();
			paramMap.put("id", UUID.randomUUID().toString());
			paramMap.put("roleId", roleId);
			paramMap.put("authId", authArr[i]);
			roleRelaAuthMapper.insertRela(paramMap);
		}
		resultBean.setStatus("success");
		resultBean.setMessage("角色设置权限成功!");
		return resultBean;
	}
	
}
