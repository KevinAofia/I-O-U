package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import com.skillstorm.models.Expense;

public class ExpenseDAO {

	private Connection connection;

	public ExpenseDAO() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/ioyou";
		String username = "root";
		String password = "root";
		this.connection = DriverManager.getConnection(url, username, password);
	}

	public Connection getConnection() {
		return connection;
	}

	// CRUD
	public Expense create(Expense expense) {
//		String sql = "insert into Artist(Name) values (?)";				// flag: please return my keys
//		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//		statement.setString(1, artist.getName());
//		statement.executeUpdate();
//		
//		// grab the id - ResultSet = statement.getGeneratedKeys()
//		ResultSet rs = statement.getGeneratedKeys(); // without the flag, this throws an exception
//		rs.next();
//		int generatedId = rs.getInt(1);
//		artist.setId(generatedId);
//		
//		return artist;
		return null;
	}

	public Expense findById() {
		return null;
	}

	public Set<Expense> findByFirstName() {
		return null;
	}

	public Set<Expense> findByLastName() {
		return null;
	}

	public Set<Expense> findByReasonLike() {
		return null;
	}

	public Set<Expense> findByStatus() {
		return null;
	}

	public Set<Expense> findAll() {
		return null;
	}

	public boolean update() {
		return false;
	}

	public boolean delete() {
		return false;

	}

}
