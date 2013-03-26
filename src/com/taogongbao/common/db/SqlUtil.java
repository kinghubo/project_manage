package com.taogongbao.common.db;

public class SqlUtil {
	/**
	 * 数据库从MySql变成Oracle时，分页SQL转换方法。
	 * 
	 * @param sql
	 * @param start 开始位置
	 * @param limit 限制长度
	 * @return
	 */
	public static String convertPagingSql(String sql, int start, int limit) {
		StringBuilder temp = new StringBuilder(sql);
		temp.insert(0, "select * from (select row_.*, rownum rownum_ from ( ");
		temp.append(" ) row_ where rownum <= ").append(start + limit);
		temp.append(" ) where rownum_ > ").append(start);
		return temp.toString();
	}
}
