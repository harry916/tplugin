package com.harry.tplugin.util;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class StoreProceduresUtils {
	private static Connection conn = null;
	private static CallableStatement cs = null;
	private static String driverClassName = "";
	private static String dbUrl = "";
	private static String username = "";
	private static String password = "";
	
	static {
		try {
			dbInitParamsParse();
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(dbUrl + "?characterEncoding=utf8", username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void dbInitParamsParse() {
		try {
			String path = StoreProceduresUtils.class.getResource("/").getPath();
			String websiteUrl = path.replace("/classes", "")
					.replace("%20", " ") + "dbConfig.properties";
			Logger4j.getLogger(StoreProceduresUtils.class).debug(
					"@harry websiteUrl=" + websiteUrl);
			setParameter(websiteUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setParameter(String path) throws Exception {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(path);
			properties.load(fileInputStream);
			driverClassName = properties
					.getProperty("database.driverClassName");
			dbUrl = properties.getProperty("database.url");
			password = properties.getProperty("database.password");
			username = properties.getProperty("database.username");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fileInputStream) {
				fileInputStream.close();
				fileInputStream = null;
			}
		}
	}

	public static void callLoginStatementInit()
	{
		try {
			cs = conn
					.prepareCall("{call LOGINPROCEDURES(?,?,?,?)}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void callLoginStatement(String userName, String password,  String powerCode,
			String ipAdress) {
		try {
			if (null == userName || null == password)
				return;
			cs.setString(1, userName);
			cs.setString(2, password);
			cs.setString(3, ipAdress);
			cs.setString(4, powerCode);
			cs.addBatch();System.out.println("@mwzccccccccccccccc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callProductStatementInit()
	{
		try {
			cs = conn
					.prepareCall("{call PRODUCTPROCEDURES(?,?,?,?)}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void callProductStatement(String proId, String proName,
			String proType, String unstandard) {
		try {
			if (null ==proId)
				return;
			cs.setString(1, proId);
			cs.setString(2, proName);
			cs.setString(3, proType);
			cs.setString(4, unstandard);
			cs.addBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void callStatementFinish()
	{
		try {
			if (null != cs)
				cs.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != cs)
			{
				try {
					cs.close();System.out.println("@mwzdddddddddddddd");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				cs = null;
			}
		}
	}
	
	public static void callSendAllowStatementInit()
	{
		try {
			cs = conn
					.prepareCall("{call SENDALLOWPROCEDURES(?,?,?)}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void callSendAllowStatement(String state, String proType, String storeJson) {
		try {
			if (null == state || null == proType)
				return;
			cs.setString(1, state);
			cs.setString(2, proType);
			cs.setString(3, storeJson);
			cs.addBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callSendOrderStatementInit()
	{
		try {
			cs = conn
					.prepareCall("{call SENDORDERPROCEDURES(?,?,?)}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void callSendOrderStatement(String state, String proType, String sendIndexJson) {
		try {
			if (null == state || null == proType)
				return;
			cs.setString(1, state);
			cs.setString(2, proType);
			cs.setString(3, sendIndexJson);
			cs.addBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callStockStatementInit()
	{
		try {
			cs = conn
					.prepareCall("{call STOCKPROCEDURES(?,?,?,?)}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void callStockStatement(String proId, String storeId, int number, int preNumber) {
		try {
			if (null == proId || null == storeId)
				return;
			cs.setString(1, proId);
			cs.setString(2, storeId);
			cs.setInt(3, number);
			cs.setInt(4, preNumber);
			cs.addBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
