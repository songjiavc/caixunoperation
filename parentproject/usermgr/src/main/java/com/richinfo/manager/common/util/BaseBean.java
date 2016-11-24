package com.richinfo.manager.common.util;

import java.util.Date;

public class BaseBean {

	private String creater;
	
	private Date createTime;
	
	private String updater;
	
	private Date updateTime;
	
	private Character status;

	/**
	 * @return the creater
	 */
	public String getCreater() {
		return creater;
	}

	/**
	 * @param creater the creater to set
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updater
	 */
	public String getUpdater() {
		return updater;
	}

	/**
	 * @param updater the updater to set
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the status
	 */
	public Character getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Character status) {
		this.status = status;
	}
  
}
