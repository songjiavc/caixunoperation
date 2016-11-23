package com.richinfo.manager.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richinfo.manager.user.dao.AuthorityMapper;
import com.richinfo.manager.user.model.Authority;
import com.richinfo.manager.user.service.AuthorityService;

/** 
  * @ClassName: AuthorityServiceImpl 
  * @Description: 权限服务层
  * @author songjia@richinfo.cn
  * @date 2016年11月21日 下午1:04:41 
  *  
  */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	
	
	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public List<Authority> getChildrenAuthorityList(String authParentId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("authParentId", authParentId);
		List<Authority> authList = authorityMapper.getAuthListByParentId(paramMap);
		return authList;
	}

	@Override
	public void deleteAuthorityById(String authId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("authId", authId);
		authorityMapper.deleteAuthorityById(paramMap);
		
	}

	@Override
	public String insertOrUpdateAuthority(Authority authority) {
		//判断是删除还是修改
		String id = authority.getId();
		if(authority.getId() == null){
			//id为空位添加
			id = authorityMapper.insertAuthority(authority);
		}else{
			//id不为空位修改
			authorityMapper.updateAuthority(authority);
		}
		return id;
	}

	@Override
	public Authority getAuthorityById(String authId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
