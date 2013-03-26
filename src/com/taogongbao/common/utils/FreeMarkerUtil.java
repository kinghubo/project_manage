package com.taogongbao.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 
 * @author liyouliang
 *
 */
public class FreeMarkerUtil {
	/**
	 * <pre>
	 * 
	 * 生成静态页面主方法
	 * templatePath模板文件存放路径
	 * templateName 模板文件名称
	 * filename 生成的文件名称
	 * 
	 * </pre>
	 */
	public static void createFileByFtl(String templatePath,
			String templateName, String fileName, Map<?, ?> data) {
		try {
			Configuration config = new Configuration();
			// 设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(new File(templatePath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());

			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			Template template = config.getTemplate(templateName, "UTF-8");
			// 合并数据模型与模板
			FileOutputStream fos = new FileOutputStream(fileName);
			Writer out = new OutputStreamWriter(fos, "UTF-8");
			template.process(data, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 生成静态页面主方法
	 * 
	 * @param context
	 *        ServletContext
	 * @param data
	 *        一个Map的数据结果集
	 * @param templatePath
	 *        ftl模版路径
	 * @param targetHtmlPath
	 *        生成静态页面的路径
	 * </pre>
	 */
	public static void createFileByFtl(ServletContext context,
			Map<String, Object> data, String templatePath, String targetHtmlPath) {
		Configuration freemarkerCfg = new Configuration();
		// 加载模版
		freemarkerCfg.setServletContextForTemplateLoading(context, "/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		try {
			// 指定模版路径
			Template template = freemarkerCfg
					.getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");
			// 静态页面路径
			String htmlPath = context.getRealPath("/html") + "/"
					+ targetHtmlPath;
			File htmlFile = new File(htmlPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}