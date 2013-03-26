package com.taogongbao.entity.project;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PmProjectInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pm_project_info")
public class PmProjectInfo implements java.io.Serializable {

	// Fields

	private Integer id;

	private String name;

	private String explanation;

	private String level;

	private String type;

	private Timestamp createDate;

	private String lastUpdate;

	private String createUserId;

	private String createUserName;

	// Constructors

	/** default constructor */
	public PmProjectInfo() {
	}

	/** full constructor */
	public PmProjectInfo(String name, String explanation, String level,
			String type, Timestamp createDate, String lastUpdate,
			String createUserId, String createUserName) {
		this.name = name;
		this.explanation = explanation;
		this.level = level;
		this.type = type;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
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

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EXPLANATION", length = 100)
	public String getExplanation() {
		return this.explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column(name = "LEVEL", length = 100)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "TYPE", length = 100)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "LAST_UPDATE", length = 100)
	public String getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name = "CREATE_USER_ID", length = 100)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "CREATE_USER_NAME", length = 100)
	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

}