package com.college.entity;

import java.io.Serializable;
public class User implements Serializable {

	//alias
	public static final String TABLE_ALIAS = "User";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERNAME = "userName";
	public static final String ALIAS_PASSWORD = "password";
	public static final String ALIAS_SALT = "salt";
	public static final String ALIAS_REALNAME = "realName";
	public static final String ALIAS_IDCARD = "idCard";
	public static final String ALIAS_ADMINISTRATOR = "administrator";
	public static final String ALIAS_STATUS = "status";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_UPDATETIME = "updateTime";
	//maxId
	public static final String ALIAS_MAXID = "maxId";

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 
	 */
	private String salt;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 身份 ID
	 */
	private String idCard;
	/**
	 * 0: 普通  1:管理
	 */
	private boolean administrator;
	/**
	 * 0: 正常 1:删除
	 */
	private boolean status;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	//maxId
	private Integer maxId;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String value) {
		this.userName = value;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	public String getSalt() {
		return this.salt;
	}
	
	public void setSalt(String value) {
		this.salt = value;
	}
	public String getRealName() {
		return this.realName;
	}
	
	public void setRealName(String value) {
		this.realName = value;
	}
	public String getIdCard() {
		return this.idCard;
	}
	
	public void setIdCard(String value) {
		this.idCard = value;
	}
	public boolean getAdministrator() {
		return this.administrator;
	}
	
	public void setAdministrator(boolean value) {
		this.administrator = value;
	}
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean value) {
		this.status = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	public Integer getMaxId() {
		return maxId;
	}

	public void setMaxId(Integer maxId) {
		this.maxId = maxId;
	}
}