package com.taogongbao.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 ******************************************************************************** 
 * 模块名 : com.taogongbao.er.common 文件名 : Configuration.java 文件实现功能 : 读取常量属性文件的操作类
 * 作者 : o.zhibin 版本 : JDK 1.6 备注 : <其它说明> 创建时间: 2010-6-27 上午11:12:22
 * --------------------------------- 修改记录 : ---------------------------------- 日
 * 期 版本 修改人 修改内容 YYYY/MM/DD X.Y <作者或修改者名> <修改内容>
 * 
 ******************************************************************************** 
 */

public class PropertiesUtils {
    private static HashMap<String, PropertiesUtils> configuration;

    private final String encoding = "UTF-8";

    // 中文说明语句对象
    private HashMap<String, String> message;

    /**
     * 函数名称：getMessage 函数功能描述： 返回:message
     * 
     * @return message
     */
    public HashMap<String, String> getMessage() {
        return message;
    }

    /**
     * 构造函数:
     * 
     * @param configFile
     *            文件路径
     */
    private PropertiesUtils(String configFile) {
        init(configFile);
    }

    /**
     * 函数名称: getInstance 函数功能描述: 获取实例信息
     * 
     * @param configFile
     * @return Configuration 对象
     */
    public static synchronized PropertiesUtils getInstance(String configFile) {
        PropertiesUtils conf = null;
        if (configuration == null) {
            configuration = new HashMap<String, PropertiesUtils>();
            conf = new PropertiesUtils(configFile);
            configuration.put(configFile, conf);
        } else {
            PropertiesUtils configrue = configuration.get(configFile);
            if (configrue == null) {
                conf = new PropertiesUtils(configFile);
                configuration.put(configFile, conf);
            } else {
                conf = configrue;
            }
        }
        return conf;
    }

    /**
     * 函数名称: init 函数功能描述:初始化，读取配置文件
     * 
     * @param configFile
     *            配置文件路径
     */
    private synchronized void init(String configFile) {
        InputStream in = null;
        Properties propertie = new Properties();

        try {
            InputStream inputFile = new FileInputStream("." + configFile);
            propertie.load(inputFile);
        } catch (Exception e1) {
            in = this.getClass().getResourceAsStream(configFile);
            try {
                propertie.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setPropertiseValue(propertie);
    }

    public String get(String key) {
        return this.message.get(key);
    }

    /**
     * 函数名称: setPropertiseValue 函数功能描述: 设置属性文件的所有信息
     */
    private void setPropertiseValue(Properties propertie) {
        this.message = new HashMap<String, String>();
        Set<Object> keyValue = propertie.keySet();
        for (Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
            String key = (String) it.next();
            String value;
            try {
                value = new String(getValue(key, propertie).getBytes("ISO-8859-1"), this.encoding);
            } catch (Exception e) {
                value = "";
                e.printStackTrace();
            }
            this.message.put(key, value);
        }
    }

    /**
     * 函数名称: getValue 函数功能描述: 根据属性文件的键，获取值
     * 
     * @param key
     *            键
     * @return 对应的值
     */
    private String getValue(String key, Properties propertie) {
        String value = null;
        if (propertie.containsKey(key)) {
            value = propertie.getProperty(key);
        } else {
            value = "";
        }
        return value;
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
//        PropertiesUtils conf = PropertiesUtils.getInstance(Constants.SPRING_CONFIG_FILEPATH);
//        System.out.println(conf.get("SPRING_CONFIG"));
        //HashMap<String, String> messagemap = conf.getMessage();

    }
}
