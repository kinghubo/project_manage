package com.taogongbao.entity.pu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PuOptions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pu_options")
public class Options implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 838028999390597368L;
	private String id;
	private String name;
	private String value;
	private String typeId;
	private String typeName;
	private String description;
	private Integer type;
	private String code;

	// Constructors

	/** default constructor */
	public Options() {
	}

	/** minimal constructor */
	public Options(String id, Integer type) {
		this.id = id;
		this.type = type;
	}

	/** full constructor */
	public Options(String id, String name, String value, String typeId,
			String typeName, String description, Integer type, String code) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.typeId = typeId;
		this.typeName = typeName;
		this.description = description;
		this.type = type;
		this.code = code;
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

	@Column(name = "value", length = 300)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "type_id", length = 300)
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Column(name = "type_name", length = 100)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "code", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}