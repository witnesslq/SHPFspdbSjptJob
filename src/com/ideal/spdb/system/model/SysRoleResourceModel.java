package com.ideal.spdb.system.model;

import com.ideal.spdb.utils.BaseModel;

/**
 * 
 * <p>Title:  浦发五星项目</p>
 * <p>Description:角色资源表实体</p>
 * <p>Company: 上海理想信息产业(集团)有限公司</p>
 * @author wls
 * create date: 2011-8-31
 * @version 1.0
 */
public class SysRoleResourceModel extends BaseModel {
	private static final long serialVersionUID = 1L;
	private String roleId;//角色ID
	private String resourceId;//资源ID
	private String column4;// 备份字段

	public SysRoleResourceModel() {
	}

	public SysRoleResourceModel(String roleId,
			String resourceId, String column4) {
		this.roleId = roleId;
		this.resourceId = resourceId;
		this.column4 = column4;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
}