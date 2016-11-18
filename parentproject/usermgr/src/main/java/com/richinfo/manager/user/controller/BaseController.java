package com.richinfo.manager.user.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

public class BaseController {
	
    /** 
      * @Description: 执行成功后返回 
      * @author songj@sdfcp.com
      * @date 2014年11月24日 下午3:15:34 
      * 
      * @param data
      * @return 
      */
    public String sendSuccessMsg(Object data){
	    Map<String,Object> returnMap = new HashMap<String,Object>();
	    returnMap.put("success", true);
	    returnMap.put("data", data);
	    return JSONArray.toJSONString(returnMap);
	};
	
	/** 
	  * @Description: 返回结果失败执行
	  * @author songj@sdfcp.com
	  * @date 2014年11月24日 下午3:16:22 
	  * 
	  * @param data
	  * @return 
	  */
	public String sendFailureMsg(Object data){
	    Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("success", false);
        returnMap.put("data", data);
        return JSONArray.toJSONString(returnMap);
	}
}
