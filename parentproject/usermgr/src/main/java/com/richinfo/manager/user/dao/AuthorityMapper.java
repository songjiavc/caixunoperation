package com.richinfo.manager.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.richinfo.manager.user.model.Authority;

public interface AuthorityMapper {
	
	@Autowired
	List<Authority> getAuthListByParentId(String authParentId);
	
	@Autowired
	void deleteAuthorityById(Map<String,String> paramMap);
	
	@Autowired
	String insertAuthority(Authority authority);
	
	@Autowired
	void updateAuthority(Authority authority);
	
}
