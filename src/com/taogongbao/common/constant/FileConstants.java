package com.taogongbao.common.constant;

import java.util.HashMap;

import com.taogongbao.common.utils.PropertiesUtils;

/**
 * 保存文件路径
 * @author xiexiaozhou
 *
 */
public class FileConstants {
	private static HashMap<String, String> messagemap;
	static{
		 PropertiesUtils conf = PropertiesUtils.getInstance(Constants.CMS_UPLOAD_PROPERTIES);
		 messagemap = conf.getMessage();
	}
	//文件服务器地址
	public final static String FILE_SAVE_VIRTUAL_PATH=messagemap.get(Constants.CMS_UPLOAD_URLPATH_NAME);
	//文件服务器实际存储地址
	public final static String FILE_SAVE_REAL_PATH=messagemap.get(Constants.CMS_UPLOAD_UPLOADPATH_NAME);
	
	//成功案例图片保存文件夹
	 public final static String  SUCCESS_CASE_IMG_DIR_PATH="/upload/images/successCase/";
	 //成功案例内容图片保存文件夹
	 public final static String  SUCCESS_CASECONTENT_IMG_DIR_PATH="/upload/contentImages/successCase/";
	//成功案例html保存文件夹
	 public final static String  SUCCESS_CASE_HTML_DIR_PATH="/upload/html/successCase/";
	 //名企招聘广告图片保存文件夹
	 public final static String  FamousRecruitAd_IMG_DIR_PATH="/upload/images/famousRecruitAd/";
	 //首页轮换广告图片保存文件夹
	 public final static String  RotateAd_IMG_DIR_PATH="/upload/images/rotateAd/";
	 //网站公告HTML保存文件夹
	 public final static String  SysNotice_HTML_DIR_PATH="/upload/html/sysNotice/";
	 //网站公告图片保存文件夹
	 public final static String  SysNotice_IMG_DIR_PATH="/upload/images/sysNotice/";
	//彩信头帧图片保存文件夹
	 public final static String  MmsHeadFrame_IMG_DIR_PATH="/upload/images/mmsHeadFrame/";
}
