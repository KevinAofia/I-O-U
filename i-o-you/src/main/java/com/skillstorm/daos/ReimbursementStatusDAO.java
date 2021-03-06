package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skillstorm.models.ReimbursementStatus;

public class ReimbursementStatusDAO {

	private Connection connection;

	public ReimbursementStatusDAO() {
		String url = "jdbc:mysql://localhost:3306/ioyou";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("\n\nReimbursementStatusDAO: " + e + "\n\n");
		}
		try {
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.err.println("\n\nReimbursementStatusDAO: " + e + "\n\n");
		}
	}

	// CRUD
	public ReimbursementStatus create(ReimbursementStatus reimbursementStatus) throws SQLException {
		String sql = "INSERT INTO ReimbursementStatus(Status) values (?);";

		// flag: return generated keys
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, reimbursementStatus.getStatus());
		statement.executeUpdate();

		ResultSet resultSet = statement.getGeneratedKeys(); // grab id...this throws an exception w/o flag
		resultSet.next();
		int generatedId = resultSet.getInt(1);
		reimbursementStatus.setId(generatedId);

		return reimbursementStatus;
	}

	public ReimbursementStatus findDefault() throws SQLException {
		// Default status returned at the end
		String sql = "SELECT ReimbursementStatusId, Status FROM ReimbursementStatus WHERE ReimbursementStatusId = 1;";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		// Ensure resultSet returned a value or return null
		if (resultSet.next()) {
			// Create Java Object and Return with Extracted DB values
			return new ReimbursementStatus(resultSet.getInt("ReimbursementStatusId"), resultSet.getString("Status"));
		} else {
			return null;
		}

	}

	public ReimbursementStatus findById(int id) throws SQLException {
		String sql = "SELECT ReimbursementStatusId, Status FROM ReimbursementStatus WHERE ReimbursementStatusId = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		// Ensure resultSet returned a value or return null
		if (resultSet.next()) {
			// Create Java Object and Return with Extracted DB values
			return new ReimbursementStatus(resultSet.getInt("ReimbursementStatusId"), resultSet.getString("Status"));
		} else {
			return null;
		}
	}

	public List<ReimbursementStatus> findAll() throws SQLException {
		// set of statuses returned at the end
		List<ReimbursementStatus> reimbursementStatuses = new ArrayList<ReimbursementStatus>();
		String sql = "SELECT ReimbursementStatusId, Status FROM ReimbursementStatus;";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		// Create Java Objects from MySQL DB
		while (resultSet.next()) {
			ReimbursementStatus reimbursementStatus = new ReimbursementStatus();

			// Extract values from DB table
			int id = resultSet.getInt("ReimbursementStatusId");
			String status = resultSet.getString("Status");

			// Store it in the Java object
			reimbursementStatus.setId(id);
			reimbursementStatus.setStatus(status);

			reimbursementStatuses.add(reimbursementStatus);
		}
		return reimbursementStatuses;

	}

	public boolean update(ReimbursementStatus reimbursementStatus) throws SQLException {
		String sql = "UPDATE ReimbursementStatus SET Status = ? WHERE ReimbursementStatusId = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, reimbursementStatus.getStatus());
		statement.setInt(2, reimbursementStatus.getId());
		return statement.executeUpdate() == 1;
	}

	public boolean delete(ReimbursementStatus reimbursementStatus) throws SQLException {
		// ADJUST FOR NULLPOINTER EXCEPTION LATER

		String sql = "DELETE FROM ReimbursementStatus WHERE ReimbursementStatusId = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, reimbursementStatus.getId());
		// Return true if one row was affected successfully
		try {
			return statement.executeUpdate() == 1;
		}
		// We'll handle any FK constraint exception here
		catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}
	}
}
