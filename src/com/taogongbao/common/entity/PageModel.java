package com.taogongbao.common.entity;

import java.util.List;

import com.taogongbao.common.utils.PropertiesUtils;

/**
 * 分页实体类,包含分页的基本信息
 * 
 * @createTime: Mar 29, 2011 4:12:32 PM
 * @author: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @changesSum: 
 * 
 */
public class PageModel<T> {
	private static final String PAGESIZE_PROPERTIES = "/properties/default.properties";
	
	/**
	 * 查询总页码
	 */
	private int totalPages;
	
	/**
	 * 总条数
	 */
	private int totalRows;
	
	/**
	 * 当前页码（以1开始）
	 */
	private int currentPage;
	
	/**
	 * 每页大小
	 */
	public static final int PAGE_SIZE = Integer.parseInt(PropertiesUtils.getInstance(PAGESIZE_PROPERTIES).get("default.pagesize"));
	
	/**
	 * 起始条数
	 */
	private int start;
	
	/**
	 * 前台自定义每页大小
	 */
	private int limit;
	
	/**
	 * 当前页面数据列表
	 */
	private List<T> dataList;
	
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	/**
	 * 获取当前页码（以1开始）
	 */
	public int getCurrentpage() {
		return currentPage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentPage = currentpage;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		totalPages = totalRows / PageModel.PAGE_SIZE + (totalRows % PageModel.PAGE_SIZE > 0 ? 1 : 0);
		this.totalRows = totalRows;
	}
}
