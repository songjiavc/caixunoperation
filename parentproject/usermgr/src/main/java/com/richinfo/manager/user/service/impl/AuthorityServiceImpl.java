package com.richinfo.manager.user.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.richinfo.manager.user.bean.AuthorityBean;
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
	public void deleteAuthorityById(String authId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("authId", authId);
		authorityMapper.deleteAuthorityById(paramMap);
		
	}

	@Override
	public String insertOrUpdateAuthority(AuthorityBean authorityBean) {
		//判断是删除还是修改
		String id = authorityBean.getId();
		Authority authority = null;
		if(StringUtils.isEmpty(id)){
			//id为空位添加
			//初始化创建人和创建按时间
			id = UUID.randomUUID().toString();
			authority = new Authority();
			authority.setId(id);
			authority.setAuthCode(authorityBean.getCode());
			authority.setAuthName(authorityBean.getAuthName());
			authority.setAuthImg(authorityBean.getAuthImg());
			authority.setMenuUrl(authorityBean.getMenuUrl());
			authority.setParentAuthId(authorityBean.getParentAuthId());
			authority.setStatus(authorityBean.getStatus());
			authority.setCreater(authorityBean.getCreater());
			authority.setCreateTime(new Timestamp(System.currentTimeMillis()));
			authorityMapper.insertAuthority(authority);
		}else{
			//id不为空位修改
			authority = new Authority();
			authority.setAuthCode(authorityBean.getCode());
			authority.setAuthName(authorityBean.getAuthName());
			authority.setAuthImg(authorityBean.getAuthImg());
			authority.setMenuUrl(authorityBean.getMenuUrl());
			authority.setParentAuthId(authorityBean.getParentAuthId());
			authority.setStatus(authorityBean.getStatus());
			authority.setCreater(authorityBean.getCreater());
			authority.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			//如果是开启
			authorityMapper.updateAuthority(authority);
		}
		return id;
	}

	@Override
	public Authority getAuthorityById(String authId) {
		return authorityMapper.getAuthorityByid(authId);
	}

	@Override
	public List<Authority> getAuthorityListByParentId(String parentAuthId) {
		List<Authority> authList = authorityMapper.getAuthListByParentId(parentAuthId);
		return authList;
	}

	@Override
	public boolean checkValue(AuthorityBean authorityBean) {
		Authority authority = new Authority();
		authority.setAuthCode(authorityBean.getCode());
		authority.setAuthName(authorityBean.getAuthName());
		authority.setStatus('1');
		authority.setId(authorityBean.getId());
		
		Integer count = authorityMapper.checkValue(authority);
		if(count > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public Integer checkChildNum(AuthorityBean authorityBean){
		
		Authority authority = new Authority();
		authority.setStatus(authorityBean.getStatus());
		authority.setParentAuthId(authorityBean.getParentAuthId());
		Integer count = authorityMapper.checkChildNum(authority);
		return count;
		
	}
	
	@Override
	public List<Authority> getIsStatusAuthList(){
		return authorityMapper.getIsStatusAuthList();
	}

}
