package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	public static void main(String[] args) throws SQLException {
		System.out.println("Project one working!");

		// 1. establish a Connection to the database
		String url = "jdbc:mysql://localhost:3306/ioyou";
		String username = "root";
		String password = "root";

		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println(!conn.isClosed());

		// hard coded - works
//		String sql = "INSERT INTO Expense(FirstName,LastName,Reason,ReimbursementStatusId) values (\"Kevin\",\"Aofia\",\"another lunch reason\",1)";
//		Statement statement = conn.createStatement();
//		statement.executeUpdate(sql);

		// dynamic - works
//		String userInput1 = "Kevin";
//		String userInput2 = "Aofia";
//		String userInput3 = "ppreason";
//		int userInput4 = 1;
//		String sql = "INSERT INTO Expense(FirstName,LastName,Reason,ReimbursementStatusId) values (?,?,?,?)";
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setString(1, userInput1);
//		statement.setString(2, userInput2);
//		statement.setString(3, userInput3);
//		statement.setInt(4, userInput4);
//		statement.executeUpdate();

		// 3. close the connection
		conn.close();
	}
}
