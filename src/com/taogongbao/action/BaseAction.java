package com.taogongbao.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.feinno.baigong.common.tools.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.taogongbao.common.constant.SessionConstants;
import com.taogongbao.common.utils.FileUtil;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.entity.cms.CmsUserLog;
import com.taogongbao.manager.userpopedom.ICmsUserLogManager;

/**
 * 
 * @createTime: Mar 17, 2011 10:22:27 AM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * 
 */
public class BaseAction  extends ActionSupport implements Preparable {

	private File pic;//上传图片
	private int start;
	private int limit;
	private Date sTime;
	private Date eTime;
	private String content;//内容
	private String picFileName;
	private ICmsUserLogManager cmsUserLogManager;
	private static final long serialVersionUID = 5926134931393176313L;

	/**
	 * 获得请求(HttpServletRequest对象）
	 * @return 当前请求
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得响应（HttpServletRequest对象）
	 * @return 当前响应current response
	 */
	protected HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		return response;
	}

	/**
	 * 输出Json数据
	 */
	protected void print(String str) {
		try {
			this.getResponse().getWriter().print(str);
		} catch (IOException e) {
			// TODO XXXX 这里记录日志
		}
	}
	
	/**
	 * 获得所给url对应的文件的内容
	 * @return
	 * @throws Exception
	 */
	public String getContent(){
		//得到url
		String url=this.getRequest().getParameter("url");
		//获得url对应的内容
		StringBuffer sb=FileUtil.getContentByUrl(url);
		//返回给客户端
		print(sb.toString());
		return NONE;
	}
	
	/**
	 * 更新url对应的文件的内容
	 */
	public String updateContent(){
		//得到要更新的url
		String url=this.getRequest().getParameter("url");
		//更新url对应的内容
		FileUtil.updateContentByUrl(url, this.content);
		return NONE;
	}
	
	/**
	 * 更新url对应的图片的内容
	 */
	public String updateImage()throws Exception{
		//得到要更新的url
		String url=this.getRequest().getParameter("url");
		//更新url对应的内容
		FileUtil.updateImageByUrl(url, this.pic);
		return NONE;
	}

	public CmsUser getSessionCmsUser(){
		return (CmsUser)this.getRequest().getSession().getAttribute(SessionConstants.CMS_USER);
	}
	/**
	 * 处理ids,增加ID之间的''
	 */
	public String disposeIds(String ids){
		if(StringUtil.isEmpty(ids)){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		String[] idStrs = ids.split(",");
		for(int i=0;i<idStrs.length;i++){
			if(idStrs[i].startsWith("'") && idStrs[i].endsWith("'")){
				sb.append("," + idStrs[i]);
			}else{
				sb.append(",'" + idStrs[i]+"'");
			}
		}
		return sb.toString().substring(1);
	}
	
	protected void addCmsUserLog(String menuName,String content){
		
		CmsUser cmsUser = getSessionCmsUser();
		if(null != cmsUser && !StringUtil.isEmpty(cmsUser.getId()) 
				&& !StringUtil.isEmpty(menuName) && !StringUtil.isEmpty(content)){
			CmsUserLog cmsUserLog = new CmsUserLog();
			cmsUserLog.setUserId(cmsUser.getId());
			cmsUserLog.setMenuName(menuName);
			cmsUserLog.setContent(cmsUser.getUsername() + "，" + content);
			cmsUserLogManager.addCmsUserLog(cmsUserLog);
		}
		
	}
	
	protected void addCmsUserLog(String menuName,String content,Response<String> res){
		if(res.getCode() != -1) addCmsUserLog(menuName,content);
	}
	
	
	/**
	 * 验证方法
	 */
	public void prepare() {
		
	}

	
	public void setContent(String content) {
		this.content = content;
	}
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
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
	public Date getsTime() {
		return sTime;
	}
	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}
	public Date geteTime() {
		return eTime;
	}
	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public ICmsUserLogManager getCmsUserLogManager() {
		return cmsUserLogManager;
	}
	public void setCmsUserLogManager(ICmsUserLogManager cmsUserLogManager) {
		this.cmsUserLogManager = cmsUserLogManager;
	}
}
