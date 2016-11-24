package com.richinfo.manager.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
  * @ClassName: resultBean 
  * @Description: TODO(返回数据bean) 
  * @author songjia@richinfo.cn
  * @date 2015年10月9日 下午4:46:57 
  *
 */
public class ResultBeanDataList<T>
{
	private String message;//返回提示信息
	
	private String status;//返回状态（success of fail）
	
	private List<T> rows = new ArrayList<T>();
	
	private long total;
	
	
	
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

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
}
