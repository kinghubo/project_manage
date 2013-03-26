package com.taogongbao.entity.project;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PmLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pm_log")
public class PmLog implements java.io.Serializable {

	// Fields

	private Integer id;

	private Integer proId;

	private String explanation;

	private String stage;

	private Timestamp createTime;

	private String createUserId;

	private String createUserName;

	// Constructors

	/** default constructor */
	public PmLog() {
	}

	/** full constructor */
	public PmLog(Integer proId, String explanation, String stage,
			Timestamp createTime, String createUserId, String createUserName) {
		this.proId = proId;
		this.explanation = explanation;
		this.stage = stage;
		this.createTime = createTime;
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

	@Column(name = "PRO_ID")
	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	@Column(name = "EXPLANATION", length = 100)
	public String getExplanation() {
		return this.explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column(name = "STAGE", length = 100)
	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Column(name = "CREATE_TIME", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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