package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashSet;
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

		// Expense statuses default to the ReimbursementStatusDAO default
		expense.setStatus(new ReimbursementStatusDAO().findDefault());

		// FK references ReimbursementStatus - grab the id from the correct table
		statement.setInt(5, expense.getStatus().getId());

		statement.executeUpdate();

		// set objects id to generated auto incremented
		ResultSet resultSet = statement.getGeneratedKeys(); // throws exception w/o flag
		resultSet.next();
		int generatedId = resultSet.getInt(1);
		expense.setId(generatedId);
		return expense;
	}

	public Expense findById(int id) throws SQLException {
		String sql = "SELECT ExpenseId,FirstName,LastName,Date,Reason,ReimbursementStatusId FROM Expense WHERE ExpenseId = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		// Ensure resultSet returned a value or return null
		if (resultSet.next()) {

			// Create Java Object and Return with extracted DB values
			Expense expense = new Expense(resultSet.getString("FirstName"), resultSet.getString("LastName"),
					resultSet.getString("Date"), resultSet.getString("Reason"));

			expense.setId(resultSet.getInt("ExpenseId"));

			// Expense status comes from ReimbursementStatusDAO
			expense.setStatus(new ReimbursementStatusDAO().findById(resultSet.getInt("ReimbursementStatusId")));

			return expense;
		} else {
			return null;
		}
	}

	public Set<Expense> findByFirstNameLike(String like) throws SQLException {
		// set of statuses returned at the end
		Set<Expense> expenses = new HashSet<Expense>();

		String sql = "SELECT Expense.Expenseid, Expense.FirstName,Expense.LastName, Expense.Date, Expense.Reason,Expense.ReimbursementStatusId, ReimbursementStatus.Status FROM Expense INNER JOIN ReimbursementStatus ON Expense.ReimbursementStatusId = ReimbursementStatus.ReimbursementStatusId WHERE Expense.FirstName LIKE ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, like);
		ResultSet resultSet = statement.executeQuery();

		// Create Java Objects from MySQL DB
		while (resultSet.next()) {

			// Create Java Object and Return with extracted DB values
			Expense expense = new Expense(resultSet.getString("FirstName"), resultSet.getString("LastName"),
					resultSet.getString("Date"), resultSet.getString("Reason"));

			expense.setId(resultSet.getInt("ExpenseId"));

			// Expense status comes from ReimbursementStatusDAO
			expense.setStatus(new ReimbursementStatusDAO().findById(resultSet.getInt("ReimbursementStatusId")));

			// Create Java Object with extracted DB values and add to set
			expenses.add(expense);
		}
		return expenses;

	}

	public Set<Expense> findByLastNameLike(String like) throws SQLException {
		// set of statuses returned at the end
		Set<Expense> expenses = new HashSet<Expense>();

		String sql = "SELECT Expense.Expenseid, Expense.FirstName,Expense.LastName, Expense.Date, Expense.Reason,Expense.ReimbursementStatusId, ReimbursementStatus.Status FROM Expense INNER JOIN ReimbursementStatus ON Expense.ReimbursementStatusId = ReimbursementStatus.ReimbursementStatusId WHERE Expense.LastName LIKE ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, like);
		ResultSet resultSet = statement.executeQuery();

		// Create Java Objects from MySQL DB
		while (resultSet.next()) {

			// Create Java Object and Return with extracted DB values
			Expense expense = new Expense(resultSet.getString("FirstName"), resultSet.getString("LastName"),
					resultSet.getString("Date"), resultSet.getString("Reason"));

			expense.setId(resultSet.getInt("ExpenseId"));

			// Expense status comes from ReimbursementStatusDAO
			expense.setStatus(new ReimbursementStatusDAO().findById(resultSet.getInt("ReimbursementStatusId")));

			// Create Java Object with extracted DB values and add to set
			expenses.add(expense);
		}
		return expenses;

	}

	public Set<Expense> findByReasonLike(String like) throws SQLException {
		// set of statuses returned at the end
		Set<Expense> expenses = new HashSet<Expense>();

		String sql = "SELECT Expense.Expenseid, Expense.FirstName,Expense.LastName, Expense.Date, Expense.Reason,Expense.ReimbursementStatusId, ReimbursementStatus.Status FROM Expense INNER JOIN ReimbursementStatus ON Expense.ReimbursementStatusId = ReimbursementStatus.ReimbursementStatusId WHERE Expense.Reason LIKE ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, like);
		ResultSet resultSet = statement.executeQuery();

		// Create Java Objects from MySQL DB
		while (resultSet.next()) {

			// Create Java Object and Return with extracted DB values
			Expense expense = new Expense(resultSet.getString("FirstName"), resultSet.getString("LastName"),
					resultSet.getString("Date"), resultSet.getString("Reason"));

			expense.setId(resultSet.getInt("ExpenseId"));

			// Expense status comes from ReimbursementStatusDAO
			expense.setStatus(new ReimbursementStatusDAO().findById(resultSet.getInt("ReimbursementStatusId")));

			// Create Java Object with extracted DB values and add to set
			expenses.add(expense);
		}
		return expenses;
	}

	public Set<Expense> findByStatusId(int id) throws SQLException {
		// set of statuses returned at the end
		Set<Expense> expenses = new HashSet<Expense>();

		String sql = "SELECT Expense.Expenseid, Expense.FirstName,Expense.LastName, Expense.Date, Expense.Reason,Expense.ReimbursementStatusId, ReimbursementStatus.Status FROM Expense INNER JOIN ReimbursementStatus ON Expense.ReimbursementStatusId = ReimbursementStatus.ReimbursementStatusId WHERE Expense.ReimbursementStatusId =  ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		// Create Java Objects from MySQL DB
		while (resultSet.next()) {

			// Create Java Object and Return with extracted DB values
			Expense expense = new Expense(resultSet.getString("FirstName"), resultSet.getString("LastName"),
					resultSet.getString("Date"), resultSet.getString("Reason"));

			expense.setId(resultSet.getInt("ExpenseId"));

			// Expense status comes from ReimbursementStatusDAO
			expense.setStatus(new ReimbursementStatusDAO().findById(resultSet.getInt("ReimbursementStatusId")));

			// Create Java Object with extracted DB values and add to set
			expenses.add(expense);
		}
		return expenses;
	}

	public Set<Expense> findByStatusLike(String like) throws SQLException {
		// set of statuses returned at the end
		Set<Expense> expenses = new HashSet<Expense>();

		String sql = "SELECT Expense.Expenseid, Expense.FirstName,Expense.LastName, Expense.Date, Expense.Reason,Expense.ReimbursementStatusId, ReimbursementStatus.Status FROM Expense INNER JOIN ReimbursementStatus ON Expense.ReimbursementStatusId = ReimbursementStatus.ReimbursementStatusId WHERE ReimbursementStatus.Status LIKE ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, like);
		ResultSet resultSet = statement.executeQuery();

		// Create Java Objects from MySQL DB
		while (resultSet.next()) {

			// Create Java Object and Return with extracted DB values
			Expense expense = new Expense(resultSet.getString("FirstName"), resultSet.getString("LastName"),
					resultSet.getString("Date"), resultSet.getString("Reason"));

			expense.setId(resultSet.getInt("ExpenseId"));

			// Expense status comes from ReimbursementStatusDAO
			expense.setStatus(new ReimbursementStatusDAO().findById(resultSet.getInt("ReimbursementStatusId")));

			// Create Java Object with extracted DB values and add to set
			expenses.add(expense);
		}
		return expenses;
	}

	public Set<Expense> findAll() throws SQLException {
		// set of statuses returned at the end
		Set<Expense> expenses = new HashSet<Expense>();

		String sql = "SELECT ExpenseId,FirstName,LastName,Date,Reason,ReimbursementStatusId FROM Expense;";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		// Create Java Objects from MySQL DB
		while (resultSet.next()) {

			// Create Java Object and Return with extracted DB values
			Expense expense = new Expense(resultSet.getString("FirstName"), resultSet.getString("LastName"),
					resultSet.getString("Date"), resultSet.getString("Reason"));

			expense.setId(resultSet.getInt("ExpenseId"));

			// Expense status comes from ReimbursementStatusDAO
			expense.setStatus(new ReimbursementStatusDAO().findById(resultSet.getInt("ReimbursementStatusId")));

			expenses.add(expense);
		}
		return expenses;
	}

	public boolean update(Expense expense) throws SQLException {
		String sql = "UPDATE Expense SET FirstName =?, LastName = ?, Date = ?, Reason = ?, ReimbursementStatusId = ? WHERE ExpenseId = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, expense.getFirstName());
		statement.setString(2, expense.getLastName());
		statement.setString(3, expense.getDate());
		statement.setString(4, expense.getReason());
		statement.setInt(5, expense.getStatus().getId());
		statement.setInt(6, expense.getId());
		return statement.executeUpdate() == 1;
	}

	public boolean delete(Expense expense) throws SQLException {
		// ADJUST FOR NULLPOINTER EXCEPTION LATER

		String sql = "DELETE FROM Expense WHERE ExpenseId = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, expense.getId());
		// Return true if one row was affected successfully
		try {
			return statement.executeUpdate() == 1;
		}
		// We'll handle any FK constraint exception here
		catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}

	}
	
//	public static void main(String[] args) throws SQLException {
//		// FOR FUN TEST AREA
//		ExpenseDAO expenseDAO = new ExpenseDAO();
//		System.out.println(!expenseDAO.getConnection().isClosed());
//		Expense expense = new Expense(25, "new", "person", "07-01-2022", "just");
//		System.out.println(expenseDAO.create(expense));
//		ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAO();
//		System.out.println(!reimbursementStatusDAO.getConnection().isClosed());
//		Expense newExpense = expenseDAO.create(new Expense("Koo", "Ala", "tomorrow", "please approve"));
//		System.out.println(newExpense);
//		System.out.println(expenseDAO.findByStatusId(3));
//		System.out.println(expenseDAO.findByStatusLike("%pend%"));
//		System.out.println(expenseDAO.findByReasonLike("%br%"));
//		System.out.println(expenseDAO.findByFirstNameLike("%k%"));
//		System.out.println(expenseDAO.findByLastNameLike("%ly%"));
//		Expense testExpense = expenseDAO.findById(19);
//		Expense newExpense = expenseDAO.findById(20);
//		System.out.println(testExpense);
//		System.out.println("-----------------------------");
//		System.out.println("-----------------------------");
//		System.out.println(expenseDAO.findAll());
//		newExpense.setStatus(new ReimbursementStatusDAO().findById(3));
//		testExpense.setFirstName("XXXX");
//		testExpense.setLastName("YYYY");
//		testExpense.setStatus(new ReimbursementStatusDAO().findById(5));
//		System.out.println(expenseDAO.update(testExpense));
//		System.out.println(expenseDAO.update(newExpense));
//		System.out.println("-----------------------------");
//		System.out.println("-----------------------------");
//		System.out.println(testExpense);
//		System.out.println(newExpense);
//		System.out.println(expenseDAO.delete(testExpense));
//		System.out.println(expenseDAO.delete(newExpense));
//		System.out.println(reimbursementStatusDAO.delete(testExpense));
//		System.out.println(reimbursementStatusDAO.findAll());
//		System.out.println(reimbursementStatusDAO.findDefault());
//		System.out.println(reimbursementStatusDAO.create(new ReimbursementStatus("Some New status")));
//		ReimbursementStatus testUpdateDelete = reimbursementStatusDAO.findById(5);
//		testUpdateDelete.setStatus("Change this status");
//		System.out.println(reimbursementStatusDAO.update(testUpdateDelete));
//		System.out.println(reimbursementStatusDAO.delete(testUpdateDelete));
//		System.out.println(reimbursementStatusDAO.findByStatusLike("%d"));
//	}
	

}
