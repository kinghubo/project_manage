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
 * CmsPopedomGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_popedom_group")
public class CmsPopedomGroup implements java.io.Serializable {

	private static final long serialVersionUID = 8233454422020337025L;
	private Integer id;
	private String groupName;
	private String groupPopedom;
	private String creater;
	private Date createTime;

	// Constructors

	/** default constructor */
	public CmsPopedomGroup() {
	}

	/** minimal constructor */
	public CmsPopedomGroup(Integer id, String groupName, String groupPopedom) {
		this.id = id;
		this.groupName = groupName;
		this.groupPopedom = groupPopedom;
	}

	/** full constructor */
	public CmsPopedomGroup(Integer id, String groupName, String groupPopedom,
			String creater, Date createTime) {
		this.id = id;
		this.groupName = groupName;
		this.groupPopedom = groupPopedom;
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

	@Column(name = "group_name", nullable = false)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "group_popedom")
	public String getGroupPopedom() {
		return this.groupPopedom;
	}

	public void setGroupPopedom(String groupPopedom) {
		this.groupPopedom = groupPopedom;
	}

	@Column(name = "creater")
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