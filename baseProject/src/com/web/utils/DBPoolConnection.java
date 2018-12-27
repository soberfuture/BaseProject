package com.web.utils;

import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DBPoolConnection {
	private static DBPoolConnection dbPoolConnection = null;
	private static DruidDataSource druidDataSource = null;

	static {
		System.out.println("加载jdbc.properties...");
		Properties properties = CommonUtils.loadPropertiesFile("classes/jdbc.properties");
		try {
			druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties); // DruidDataSrouce工厂模式
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据库连接池单例
	 * 
	 * @return
	 */
	public static synchronized DBPoolConnection getInstance() {
		if (null == dbPoolConnection) {
			dbPoolConnection = new DBPoolConnection();
		}
		return dbPoolConnection;
	}

	/**
	 * 返回druid数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public DruidPooledConnection getConnection() throws SQLException {
		return druidDataSource.getConnection();
	}

}
