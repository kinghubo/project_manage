package com.taogongbao.common;

import java.util.Map;

import com.taogongbao.common.constant.Constants;
import com.taogongbao.common.utils.PropertiesUtils;

/**
 * 类的概要：配置文件读取
 * 
 * Company:新媒传信科技有限公司
 * 
 * @since JDK 1.6
 * @author o.zhibin Create on 2010-6-8 下午04:45:01
 */
public class Configure {

	// Socket 配置Map
	private static Map<String, String> socketMap;

	private static Configure instance;

	private Configure() {

	}

	/**
	 * 获取实例。
	 * 
	 * @param configFile
	 * @return
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a> Mar 18, 2011
	 *         10:47:43 AM
	 */
	public synchronized static Configure getInstance() {
		if (null == instance) {
			instance = new Configure();
			// 加载系统配置
			instance.loadSysConfig();
		}
		return instance;
	}

	/**
	 * 函数名称: update 函数功能描述: 加载配置文件
	 * 
	 * @return boolean true成功，false失败
	 */

	private synchronized boolean loadSysConfig() {

		socketMap = PropertiesUtils.getInstance(Constants.SOCKET_CONFIG_PATH)
				.getMessage();

		return true;
	}

	/**
	 * Socket配置信息
	 * 
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">Hanpei Li</a> Mar 20, 2011
	 *         15:12:43 AM
	 * 
	 */
	public static String getSocketMap(String key) {
		if (null == socketMap) {
			getInstance();
		}
		return socketMap.get(key);
	}

}
