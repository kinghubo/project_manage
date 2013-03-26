package com.taogongbao.entity.pu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PuDictionaryIndustry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pu_dictionary_industry")
public class DictionaryIndustry implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1028489574710431124L;
	private String id;
	private String name;

	// Constructors

	/** default constructor */
	public DictionaryIndustry() {
	}

	/** minimal constructor */
	public DictionaryIndustry(String id) {
		this.id = id;
	}

	/** full constructor */
	public DictionaryIndustry(String id, String name) {
		this.id = id;
		this.name = name;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}