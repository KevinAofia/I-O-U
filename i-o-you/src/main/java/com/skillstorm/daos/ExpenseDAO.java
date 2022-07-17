package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.skillstorm.models.Expense;
import com.skillstorm.models.ReimbursementStatus;

public class ExpenseDAO {

	private Connection connection;

	public ExpenseDAO() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/ioyou";
		String username = "root";
		String password = "root";
		this.connection = DriverManager.getConnection(url, username, password);
	}
	
	// PUT IN CONFIG FILE LATER
	public Connection getConnection() {
		return connection;
	}

	// CRUD
	
	public Expense create(Expense expense) throws SQLException {
		String sql = "INSERT INTO Expense(FirstName, LastName, Date, Reason, ReimbursementStatusId) values (?,?,?,?,?)";
		// flag: return generated keys
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, expense.getFirstName());
		statement.setString(2, expense.getLastName());
		statement.setString(3, expense.getDate());
		statement.setString(4, expense.getReason());
		statement.setInt(5, 1); // make dynamic
		statement.executeUpdate();
		
		// assign object to auto incremented id
		ResultSet resultSet = statement.getGeneratedKeys(); // grab id...this throws an exception w/o flag
		resultSet.next();
		int generatedId = resultSet.getInt(1);
		expense.setId(generatedId);

		return expense;
	}

	public static void main(String[] args) throws SQLException {
		// testing connection
		ExpenseDAO expenseDAO = new ExpenseDAO();
		System.out.println(!expenseDAO.getConnection().isClosed());

		ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAO();
		System.out.println(!reimbursementStatusDAO.getConnection().isClosed());

		// testing create
//		Expense expense = new Expense(25, "new", "person", "07-01-2022", "just");
//		System.out.println(expenseDAO.create(expense));

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
