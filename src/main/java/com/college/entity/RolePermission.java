package com.college.entity;

import java.io.Serializable;
public class RolePermission implements Serializable {

	//alias
	public static final String TABLE_ALIAS = "RolePermission";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ROLEID = "roleId";
	public static final String ALIAS_PERMISSIONID = "permissionId";
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
	private Integer roleId;
	/**
	 * 
	 */
	private Integer permissionId;
	/**
	 * 0: 正常 1:删除
	 */
	private Boolean status;
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
	public Integer getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Integer value) {
		this.roleId = value;
	}
	public Integer getPermissionId() {
		return this.permissionId;
	}
	
	public void setPermissionId(Integer value) {
		this.permissionId = value;
	}
	public Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(Boolean value) {
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

	@Override
	public String toString() {
		return "RolePermission{" +
				"id=" + id +
				", roleId=" + roleId +
				", permissionId=" + permissionId +
				", status=" + status +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", maxId=" + maxId +
				'}';
	}
}