package com.college.entity;

import java.io.Serializable;
public class Teacher implements Serializable {

	//alias
	public static final String TABLE_ALIAS = "Teacher";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TEACHERNAME = "teacherName";
	public static final String ALIAS_RESUME = "resume";
	public static final String ALIAS_USERID = "userId";
	public static final String ALIAS_ACADEMICTITLE = "academicTitle";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_IMAGE = "image";
	public static final String ALIAS_LINK = "link";
	public static final String ALIAS_CONTENT = "content";
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
	 * 
	 */
	private String teacherName;
	/**
	 * 
	 */
	private String resume;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private String academicTitle;
	/**
	 * 
	 */
	private Integer type;
	/**
	 * 
	 */
	private String image;
	/**
	 * 
	 */
	private String link;
	/**
	 * 
	 */
	private String content;
	/**
	 * 0: 正常 1:删除
	 */
	private Integer status;
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
	public String getTeacherName() {
		return this.teacherName;
	}
	
	public void setTeacherName(String value) {
		this.teacherName = value;
	}
	public String getResume() {
		return this.resume;
	}
	
	public void setResume(String value) {
		this.resume = value;
	}
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	public String getAcademicTitle() {
		return this.academicTitle;
	}
	
	public void setAcademicTitle(String value) {
		this.academicTitle = value;
	}
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer value) {
		this.type = value;
	}
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String value) {
		this.image = value;
	}
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String value) {
		this.link = value;
	}
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String value) {
		this.content = value;
	}
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
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