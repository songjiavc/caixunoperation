package com.richinfo.manager.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.richinfo.manager.user.bean.AuthorityBean;
import com.richinfo.manager.user.model.Authority;

public interface AuthorityMapper {
	
	@Autowired
	List<Authority> getAuthListByParentId(String authParentId);
	
	@Autowired
	void deleteAuthorityById(Map<String,String> paramMap);
	
	@Autowired
	void insertAuthority(AuthorityBean authority);
	
	@Autowired
	void updateAuthority(AuthorityBean authority);
	
	Integer checkValue(AuthorityBean authority);
	
}
