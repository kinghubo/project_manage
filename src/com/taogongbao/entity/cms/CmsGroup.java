package com.taogongbao.entity.cms;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CMS_GROUP")
public class CmsGroup implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -6237119254932287213L;
	private Integer id;
	private Integer pid;
	private String groupName;
	private Integer isleaf;

	// Constructors

	/** default constructor */
	public CmsGroup() {
	}

	/** full constructor */
	public CmsGroup(Integer id, Integer pid, String groupName, Integer isleaf) {
		this.id = id;
		this.pid = pid;
		this.groupName = groupName;
		this.isleaf = isleaf;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PID", nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "GROUP_NAME", nullable = false, length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "ISLEAF", nullable = false)
	public Integer getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

}