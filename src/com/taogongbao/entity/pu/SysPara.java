package com.taogongbao.entity.pu;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PuSysPara entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pu_sys_para")
public class SysPara implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -8083377111118948331L;
	private Integer id;
	private String code;
	private String value;
	private String description;
	private Integer state;

	// Constructors

	/** default constructor */
	public SysPara() {
	}

	/** minimal constructor */
	public SysPara(Integer id, String code, String description, Integer state) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.state = state;
	}

	/** full constructor */
	public SysPara(Integer id, String code, String value, String description,
			Integer state) {
		this.id = id;
		this.code = code;
		this.value = value;
		this.description = description;
		this.state = state;
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

	@Column(name = "CODE", nullable = false, length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "value", length = 100)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "description", nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}