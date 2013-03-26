package com.taogongbao.common.constant;

import java.io.File;

/**
 * *****************************************************************************
 * ** 模块名 : com.taogongbao.er.common 文件名 : Contacts.java 文件实现功能 : 静态常量类 作者 :
 * o.zhibin 版本 : JDK 1.6 备注 : <其它说明> 创建时间: 2010-6-26 下午04:17:23
 * --------------------------------- 修改记录 : ---------------------------------- 日
 * 期 版本 修改人 修改内容 YYYY/MM/DD X.Y <作者或修改者名> <修改内容>
 * 
 * *****************************************************************************
 * **
 */
public class Constants {

	/** 文件分隔符 */
	public final static String FILE_SEPARATOR = File.pathSeparator;
	// Socket配置
    public static final String SOCKET_CONFIG_PATH = "/properties/socket.properties";
	
	public class HqlSign {
        public final static String EQUAL = "="; // 默认值: 等于  
        public final static String LESS_EQUAL = "<="; // 小于等于
        public final static String THAN_EQUAL = ">="; // 大于等于
        public final static String NOT_EQUAL = "<>"; // 不等于
        public final static String LIKE = "like"; // 模糊查询LIKE
        public final static String IN = "in"; // IN
        public final static String LESS = "<"; //小于
        public final static String THAN = ">"; //大于
        public static final String IS = "is";

        public final static String ASC = "asc"; //升序
        public final static String DESC = "desc"; //降序

        public final static String AND = "and";
        public final static String OR = "or";
    }
	//CMS上传图片properties配置文档路径
	public final static String CMS_UPLOAD_PROPERTIES ="/properties/uploadfile.properties";
	public final static String CMS_UPLOAD_URLPATH_NAME="url.path";
	public final static String CMS_UPLOAD_UPLOADPATH_NAME="upload.path";
}
