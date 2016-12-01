package com.richinfo.manager.user.dao.logs;

import java.util.Map;

public interface LogMapper {
	
	/** 
	  * @Description: 保存日志内容
	  * @author songjia@richinfo.cn
	  * @date 2016年12月1日 下午2:16:40 
	  * @param paramMap 
	  */
	void saveLog(Map<String,Object> paramMap);
}
