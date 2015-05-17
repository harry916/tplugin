package com.harry.tplugin.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StoreProceduresUtils {
	private static Connection conn = null;
	private static CallableStatement cs = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/tplugin?characterEncoding=utf8",
							"root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void callLoginStatement(String userName, String password,
			String ipAdress, String powerCode) {
		try {
			CallableStatement cs = conn
					.prepareCall("{call LOGINPROCEDURES(?,?,?,?)}");
			cs.setString(1, userName);
			cs.setString(2, password);
			cs.setString(3, ipAdress);
			cs.setString(4, powerCode);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean callProductStatementInit()
	{
		try {
			cs = conn
					.prepareCall("{call PRODUCTPROCEDURES(?,?,?,?)}");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void callProductStatement(String proId, String proName,
			String proType, String unstandard) {
		try {
			if (null ==proId)
				return;
			System.out.println("@@@mwz  "+proId+" "+proName+" "+proType+" "+unstandard);
			cs.setString(1, proId);
			cs.setString(2, proName);
			cs.setString(3, proType);
			cs.setString(4, unstandard);
			cs.addBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void callProductStatementFinish()
	{
		try {
			cs.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != cs)
			{
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				cs = null;
			}
		}
	}
	
	public static void callSendAllowStatement(String state, String proType, String storeJson) {
		try {
			CallableStatement cs = conn
					.prepareCall("{call SENDALLOWPROCEDURES(?,?,?)}");
			cs.setString(1, state);
			cs.setString(2, proType);
			cs.setString(3, storeJson);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void callSendOrderStatement(String state, String proType, String sendIndexJson) {
		try {
			CallableStatement cs = conn
					.prepareCall("{call SENDORDERPROCEDURES(?,?,?)}");
			cs.setString(1, state);
			cs.setString(2, proType);
			cs.setString(3, sendIndexJson);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callStockStatement(String proId, String storeId, int number, int preNumber) {
		try {
			CallableStatement cs = conn
					.prepareCall("{call STOCKPROCEDURES(?,?,?,?)}");
			cs.setString(1, proId);
			cs.setString(2, storeId);
			cs.setInt(3, number);
			cs.setInt(4, preNumber);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
