package com.taogongbao.entity.pu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PuDictionaryPosition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pu_dictionary_position")
public class DictionaryPosition implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1710388471954436152L;
	private String id;
	private String name;
	private String industryId;
	private String industryName;
	private String pinyin;

	// Constructors

	/** default constructor */
	public DictionaryPosition() {
	}

	/** minimal constructor */
	public DictionaryPosition(String id) {
		this.id = id;
	}

	/** full constructor */
	public DictionaryPosition(String id, String name, String industryId,
			String industryName, String pinyin) {
		this.id = id;
		this.name = name;
		this.industryId = industryId;
		this.industryName = industryName;
		this.pinyin = pinyin;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "industry_id", length = 10)
	public String getIndustryId() {
		return this.industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	@Column(name = "industry_name", length = 128)
	public String getIndustryName() {
		return this.industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	@Column(name = "pinyin", length = 50)
	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}