package com.akwolf.util;

import org.apache.commons.dbutils.DbUtils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBManager {
	//private MysqlDataSource dataSource;
	
	public static MysqlDataSource getDataSource(){
		DbUtils.loadDriver("com.mysql.jdbc.Driver");
		MysqlDataSource dataSource=new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/dbutil");
		dataSource.setUser("zhangh");
		dataSource.setPassword("123456");
		return dataSource ;
	}
}
