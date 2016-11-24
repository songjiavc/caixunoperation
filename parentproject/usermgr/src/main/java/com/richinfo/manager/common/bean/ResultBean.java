package com.richinfo.manager.common.bean;


/** 
  * @ClassName: ResultBean 
  * @Description: 返回值类型bean
  * @author songjia@richinfo.cn
  * @date 2016年11月24日 上午11:43:36 
  *  
  */
public class ResultBean
{
	private String message;//返回提示信息
	
	private String status;//返回状态（success of fail）
	
	private boolean isExist;//当前值是否存在
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	
}
