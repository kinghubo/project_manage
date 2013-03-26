package com.taogongbao.common.utils;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring工具类，提供取得Spring配置文件中定义的Bean的方法<br>
 * TODO XXXX GXG 此处有待改善，尝试使用ApplicationContext试试。
 * 
 * @createTime: Feb 28, 2011 3:53:26 PM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime:
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @changesSum:
 * 
 */

public class SpringUtil<T> {
    /** 唯一实例 */
    /** Spring工厂接口 */
    private static BeanFactory beanFactory = null;
    /** Spring配置文件 */
    private static final String SPRING_CFG = "classpath:/config/applicationContext*.xml";

    /** 构造器 */
    public SpringUtil() {
        if (beanFactory == null) {
            beanFactory = new ClassPathXmlApplicationContext(SPRING_CFG);

        }
    }

    /**
     * 通过在Spring配置文件中定义的bean名称，从IOC容器中取得实例
     * 
     * @param beanName bean名称
     * @return bean名称对应实例Object，使用时需要强制类型转换
     */
    @SuppressWarnings("unchecked")
    public T getBean(String beanName) throws NullPointerException {
        if (beanName == null) {
            throw new NullPointerException("beanName不能为空!");
        }
        return (T) beanFactory.getBean(beanName);
    }
}
