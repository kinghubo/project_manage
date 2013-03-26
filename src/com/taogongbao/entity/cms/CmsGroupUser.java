package com.taogongbao.entity.cms;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsGroupUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_group_user")
public class CmsGroupUser implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1800894382154095107L;
	private Integer id;
	private Integer groupId;
	private String userId;
	private String userName;
	private String creater;
	private Date createTime;

	// Constructors

	/** default constructor */
	public CmsGroupUser() {
	}

	/** minimal constructor */
	public CmsGroupUser(Integer id, Integer groupId, String userId, String userName) {
		this.id = id;
		this.groupId = groupId;
		this.userId = userId;
		this.userName = userName;
	}

	/** full constructor */
	public CmsGroupUser(Integer id, Integer groupId, String userId, String userName,
			String creater, Date createTime) {
		this.id = id;
		this.groupId = groupId;
		this.userId = userId;
		this.userName = userName;
		this.creater = creater;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "group_id", nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "user_id", nullable = false, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "creater", length = 50)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}