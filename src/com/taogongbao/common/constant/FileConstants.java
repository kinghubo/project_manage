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
}
