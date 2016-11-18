package com.richinfo.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richinfo.login.service.LoginService;
import com.richinfo.manager.user.dao.login.LoginMapper;


@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginMapper loginMapper;

	public boolean userLogin(HttpServletRequest request) {
		String username = (String) request.getParameter("username");
		if ("".equals(username)) {
			request.setAttribute("loginFail", "noUsername");
			return false;
		}
		if (username == null) {
			return false;
		}
		Map<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put("code",request.getParameter("username"));
		loginInfo.put("password",request.getParameter("password"));
		return true;
		
	}

}
