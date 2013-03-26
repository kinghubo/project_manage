package com.taogongbao.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 自定义时间格式转换
 *
 * @createTime: Nov 22, 2010 6:21:30 PM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @changesSum: 
 * 
 */
@SuppressWarnings("unchecked")
public class CustomConvert  extends StrutsTypeConverter {
	
	/**
	 * 字符串的时间格式转换为Date对象
	 */
    
	public Date convertFromString(Map context, String[] values, Class toClass) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	for(String str : values){
    		if(com.taogongbao.common.utils.StringUtils.checkNull(str)){
    			continue;
    		}
    		if(str.contains(":")) {
    			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		}
    		Date date = null;
    		try {
				date = simpleDateFormat.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		return date;
    	}
    	return null;
    }
    
   @Override
   public String convertToString(Map context, Object o) {
	   Date date = (Date) o;
	   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return simpleDateFormat.format(date);
    }    
}   

