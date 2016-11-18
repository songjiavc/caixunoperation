package com.richinfo.manager.user.dao.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.richinfo.manager.user.model.User;

public interface LoginMapper {
	
	@Autowired
	User getLoginUser(Map loginInfo);
}
