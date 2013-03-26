package com.taogongbao.entity.cms;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsGroupManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CMS_GROUP_MANAGER")
public class CmsGroupManager implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -7605996956019746089L;
	private Integer id;
	private Integer groupId;
	private String manegerId;

	// Constructors

	/** default constructor */
	public CmsGroupManager() {
	}

	/** full constructor */
	public CmsGroupManager(Integer id, Integer groupId, String manegerId) {
		this.id = id;
		this.groupId = groupId;
		this.manegerId = manegerId;
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

	@Column(name = "GROUP_ID", nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "MANEGER_ID", nullable = false, length = 36)
	public String getManegerId() {
		return this.manegerId;
	}

	public void setManegerId(String manegerId) {
		this.manegerId = manegerId;
	}

}