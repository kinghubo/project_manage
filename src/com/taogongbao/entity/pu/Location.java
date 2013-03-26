package com.taogongbao.entity.pu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PuLocation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pu_location")
public class Location implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -205996897467730072L;
	private Long id;
	private Long parentId;
	private Integer levels;
	private String name;
	private String level1Name;
	private String level2Name;
	private String level3Name;
	private String level4Name;
	private String level5Name;
	private String code;
	private String allcode;
	private String seftid;

	// Constructors

	/** default constructor */
	public Location() {
	}

	/** minimal constructor */
	public Location(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Location(Long id, Long parentId, Integer levels, String name,
			String level1Name, String level2Name, String level3Name,
			String level4Name, String level5Name, String code, String allcode,
			String seftid) {
		this.id = id;
		this.parentId = parentId;
		this.levels = levels;
		this.name = name;
		this.level1Name = level1Name;
		this.level2Name = level2Name;
		this.level3Name = level3Name;
		this.level4Name = level4Name;
		this.level5Name = level5Name;
		this.code = code;
		this.allcode = allcode;
		this.seftid = seftid;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PARENT_ID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "LEVELS")
	public Integer getLevels() {
		return this.levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	@Column(name = "NAME", length = 36)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LEVEL1_NAME", length = 36)
	public String getLevel1Name() {
		return this.level1Name;
	}

	public void setLevel1Name(String level1Name) {
		this.level1Name = level1Name;
	}

	@Column(name = "LEVEL2_NAME", length = 36)
	public String getLevel2Name() {
		return this.level2Name;
	}

	public void setLevel2Name(String level2Name) {
		this.level2Name = level2Name;
	}

	@Column(name = "LEVEL3_NAME", length = 36)
	public String getLevel3Name() {
		return this.level3Name;
	}

	public void setLevel3Name(String level3Name) {
		this.level3Name = level3Name;
	}

	@Column(name = "LEVEL4_NAME", length = 36)
	public String getLevel4Name() {
		return this.level4Name;
	}

	public void setLevel4Name(String level4Name) {
		this.level4Name = level4Name;
	}

	@Column(name = "LEVEL5_NAME", length = 36)
	public String getLevel5Name() {
		return this.level5Name;
	}

	public void setLevel5Name(String level5Name) {
		this.level5Name = level5Name;
	}

	@Column(name = "CODE", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ALLCODE", length = 100)
	public String getAllcode() {
		return this.allcode;
	}

	public void setAllcode(String allcode) {
		this.allcode = allcode;
	}

	@Column(name = "SEFTID", length = 8)
	public String getSeftid() {
		return this.seftid;
	}

	public void setSeftid(String seftid) {
		this.seftid = seftid;
	}

}