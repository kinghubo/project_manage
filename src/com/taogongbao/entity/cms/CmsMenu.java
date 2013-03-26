package com.taogongbao.entity.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * CmsMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_menu")
public class CmsMenu implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -6511990160417322334L;
	private Integer id;
	private String menuName;
	private String menuUrl;
	private Integer menuType;
	private Integer fatherId;
	private Integer sort;
	private String creater;
	private Date createTime;

	// Constructors

	/** default constructor */
	public CmsMenu() {
	}

	/** minimal constructor */
	public CmsMenu(Integer id, String menuName, Integer menuType, Integer fatherId,
			Integer sort, String creater, Date createTime) {
		this.id = id;
		this.menuName = menuName;
		this.menuType = menuType;
		this.fatherId = fatherId;
		this.sort = sort;
		this.creater = creater;
		this.createTime = createTime;
	}

	/** full constructor */
	public CmsMenu(Integer id, String menuName, String menuUrl, Integer menuType,
			Integer fatherId, Integer sort, String creater, Date createTime) {
		this.id = id;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuType = menuType;
		this.fatherId = fatherId;
		this.sort = sort;
		this.creater = creater;
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

	@Column(name = "menu_name", nullable = false)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "menu_url")
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "menu_type", nullable = false)
	public Integer getMenuType() {
		return this.menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	@Column(name = "father_id", nullable = false)
	public Integer getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	@Column(name = "sort", nullable = false)
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "creater", nullable = false)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", nullable = false, length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}