package com.richinfo.manager.user.service.logs.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.richinfo.manager.user.dao.logs.LogMapper;

/** 
  * @ClassName: LogServiceImpl 
  * @Description: 保存操作日志
  * @author songjia@richinfo.cn
  * @date 2016年12月1日 下午2:20:07 
  *  
  */
public class LogServiceImpl {

	@Autowired
	LogMapper logMapper;
	
	void LogSave(Map<String,Object> paramMap){
		
		logMapper.saveLog(paramMap);
	}
	
}
