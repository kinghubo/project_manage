package com.taogongbao.entity.cms;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_user")
public class CmsUser implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4360273327567883439L;
	private String id;
	private String username;
	private String password;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public CmsUser() {
	}

	/** full constructor */
	public CmsUser(String id, String username, String password,
			Timestamp createtime) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.createtime = createtime;
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

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}