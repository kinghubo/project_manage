package com.taogongbao.entity.cms;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsEntGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CMS_ENT_GROUP")
public class CmsEntGroup implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 6158447091876100665L;
	private Integer id;
	private Integer entId;
	private Integer groupId;

	// Constructors

	/** default constructor */
	public CmsEntGroup() {
	}

	/** full constructor */
	public CmsEntGroup(Integer id, Integer entId, Integer groupId) {
		this.id = id;
		this.entId = entId;
		this.groupId = groupId;
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

	@Column(name = "ENT_ID", nullable = false)
	public Integer getEntId() {
		return this.entId;
	}

	public void setEntId(Integer entId) {
		this.entId = entId;
	}

	@Column(name = "GROUP_ID", nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}