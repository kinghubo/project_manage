package com.taogongbao.common.utils.parse.json.impl;

import java.util.Set;

import net.sf.json.processors.JsonBeanProcessorMatcher;

import org.apache.log4j.Logger;

/**
 * 
 * @createTime: 2010-11-23 上午11:32:57
 * @author: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @changesSum:
 */
public class HibernateJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher {
	private static Logger log = Logger .getLogger(HibernateJsonBeanProcessorMatcher.class);

	@SuppressWarnings("unchecked")
	public Object getMatch(Class target, Set set) {

		if (target.getName().contains("$$EnhancerByCGLIB$$")) {
			log.warn("Found Lazy-References in Hibernate object "
					+ target.getName());
			return org.hibernate.proxy.HibernateProxy.class;
		}
		return DEFAULT.getMatch(target, set);
	}

}
