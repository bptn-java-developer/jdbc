package com.bptn.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExample2 {

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
		String sql = "SELECT * FROM students WHERE \"studentId\"= ?";

		try ( Connection conn = DriverManager.getConnection(conStr,username,password); 
			  PreparedStatement stmt = conn.prepareStatement(sql);) {
			
			int id = 10;
			stmt.setInt(1,id);
			
			rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				
				System.out.print("Student Id" + rs.getInt("studentId"));
				System.out.print(", Course Id" + rs.getInt("courseId"));
				System.out.print(", Student Name:" + rs.getString("studentName"));
				System.out.print(", Student Email: " + rs.getString("studentEmail"));
				System.out.println(", Student Phone: " + rs.getInt("studentPhone"));
			}
			
			
			System.out.println("Query Executed Successfully!!");
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
