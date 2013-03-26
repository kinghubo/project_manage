package com.taogongbao.entity.pu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PuBroadcastGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pu_broadcast_group")
public class BroadcastGroup implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -4338088640335508L;
	private Integer id;
	private Long groupId;
	private Integer parentId;
	private Integer level;
	private String groupName;
	private String seftId;
	private Integer state;

	// Constructors

	/** default constructor */
	public BroadcastGroup() {
	}

	/** minimal constructor */
	public BroadcastGroup(Integer id, Long groupId, Integer parentId,
			String groupName) {
		this.id = id;
		this.groupId = groupId;
		this.parentId = parentId;
		this.groupName = groupName;
	}

	/** full constructor */
	public BroadcastGroup(Integer id, Long groupId, Integer parentId,
			Integer level, String groupName, String seftId, Integer state) {
		this.id = id;
		this.groupId = groupId;
		this.parentId = parentId;
		this.level = level;
		this.groupName = groupName;
		this.seftId = seftId;
		this.state = state;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "group_id", nullable = false)
	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "parent_id", nullable = false)
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "level_")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "group_name", nullable = false, length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "seft_id", length = 8)
	public String getSeftId() {
		return this.seftId;
	}

	public void setSeftId(String seftId) {
		this.seftId = seftId;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}