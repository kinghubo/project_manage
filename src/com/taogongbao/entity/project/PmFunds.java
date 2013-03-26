package com.taogongbao.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PmFunds entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pm_funds")
public class PmFunds implements java.io.Serializable {

	// Fields

	private Integer id;

	private Integer proId;

	private String explanation;

	private Integer businessSum;

	private String createUserId;

	private String createUserName;

	// Constructors

	/** default constructor */
	public PmFunds() {
	}

	/** full constructor */
	public PmFunds(Integer proId, String explanation, Integer businessSum,
			String createUserId, String createUserName) {
		this.proId = proId;
		this.explanation = explanation;
		this.businessSum = businessSum;
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

	@Column(name = "BUSINESS_SUM", precision = 8, scale = 0)
	public Integer getBusinessSum() {
		return this.businessSum;
	}

	public void setBusinessSum(Integer businessSum) {
		this.businessSum = businessSum;
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