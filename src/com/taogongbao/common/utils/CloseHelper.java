/**
 * @Copyright 2009, 北京新媒传信科技有限公司 L.P. All rights
 * reserved. The information contained herein is confidential and
 * proprietary to 北京新媒传信科技有限公司, and considered a trade secret
 * as defined under civil and criminal statutes. 北京新媒传信科技有限公司
 * shall pursue its civil and criminal remedies in the event of
 * unauthorized use or misappropriation of its trade secrets. Use of
 * this information by anyone other than authorized employees of 北京
 * 新媒传信科技有限公司 is granted only under a written non-disclosure
 * agreement, expressly prescribing the scope and manner of such use.
 */

package com.taogongbao.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 * @createTime: 2011-4-28 下午03:07:45
 * @author: <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime:
 * @updateAuthor: <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
 * @changesSum:
 * 
 */
public class CloseHelper {

	private static Log log = LogFactory.getLog(CloseHelper.class);

	public static void close(SocketChannel channel){
	    try {
            if (channel != null) {
                channel.close();
                channel = null;
            }
        }
        catch (Exception ex) {
            if (log.isErrorEnabled())
                log.error("关闭SocketChannel异常", ex);
        }
	}
	
	public static void close(InputStream in) {
		try {
			if (in != null) {
				in.close();
				in = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭输入流异常", ex);
		}
	}

	public static void close(OutputStream out) {
		try {
			if (out != null) {
				out.close();
				out = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭输入流异常", ex);
		}
	}

	public static void close(BufferedReader in) {
		try {
			if (in != null) {
				in.close();
				in = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭输入流异常", ex);
		}
	}

	public static void close(ServerSocket server) {
		try {
			if (server != null) {
				server.close();
				server = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭Socket异常", ex);
		}
	}

	public static void close(Socket client) {
		try {
			if (client != null) {
				client.close();
				client = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭Socket异常", ex);
		}
	}

	public static void close(Writer out) {
		try {
			if (out != null) {
				out.close();
				out = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭Writer异常", ex);
		}
	}
}
