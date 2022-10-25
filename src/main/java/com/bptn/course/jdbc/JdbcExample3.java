package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExample3 {

	static String username = "postgres";
	static String password = "postgres";
	static String dbName = "bptn";
	static String port = "5432";
	
	static String dbUrl = "localhost";
	
	public static void main(String[] args) {
		
		//Connection conn = null;
		ResultSet rs = null;
		String conStr = "jdbc:postgresql://" + dbUrl + ":" + port + "/" + dbName;
		//String conStr = "jdbc:postgresql://localhost:5432/bptn";

		/*
		 * Connection is an interface that extends the Autoclosable Interface, that
		 * means that Java can close the connection for us by using try with resources.
		 */
		String sql = "INSERT INTO students (\"studentId\",\"courseId\", \"studentName\"," +
		             "\"studentEmail\", \"studentPhone\") VALUES (?,?,?,?,?)";

		try ( Connection conn = DriverManager.getConnection(conStr,username,password); 
			  PreparedStatement stmt = conn.prepareStatement(sql);) {
			
			stmt.setInt(1,100);
			stmt.setInt(2,1);
			stmt.setString(3,"Josh");
			stmt.setString(4,"Josh@bptn.com");
			stmt.setInt(5,123);         
			
			int num = stmt.executeUpdate(); 
			
            System.out.println("Rows inserted: " + num);			
			
			System.out.println("Insert Executed Successfully!!");
		} catch (SQLException e) {
			
			System.out.println("Error while running query");
			e.printStackTrace();
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
