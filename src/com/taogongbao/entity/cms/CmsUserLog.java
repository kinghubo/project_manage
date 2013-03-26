package com.taogongbao.entity.cms;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsUserLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_user_log")
public class CmsUserLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String menuName;
	private String content;
	private Timestamp createTime;
	private static final long serialVersionUID = -8292686404993773016L;

	// Constructors

	/** default constructor */
	public CmsUserLog() {
	}

	/** full constructor */
	public CmsUserLog(Integer id, String userId, String menuName, String content,
			Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.menuName = menuName;
		this.content = content;
		this.createTime = createTime;
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

	@Column(name = "user_id", nullable = false, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "menu_name", nullable = false, length = 36)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}