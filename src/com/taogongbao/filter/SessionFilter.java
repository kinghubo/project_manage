package com.taogongbao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taogongbao.common.constant.SessionConstants;

/**
 * 
 *
 * @createTime: May 4, 2011 3:43:06 PM
 * @author: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @changesSum: 
 * 
 */
public class SessionFilter implements Filter{

	private FilterConfig config;
	

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		Boolean loginFlag = (Boolean)req.getSession().getAttribute(SessionConstants.USER_SESSION);
		if(loginFlag == null || !loginFlag){
			// 未登录用户
			resp.sendRedirect("login.jsp");
		}
		filterChain.doFilter(request, response);
	}


	public void destroy() {
		this.config = null;
	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}
	
}
