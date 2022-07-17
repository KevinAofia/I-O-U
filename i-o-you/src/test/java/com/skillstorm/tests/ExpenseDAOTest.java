package com.skillstorm.tests;

import java.sql.SQLException;


import com.skillstorm.daos.ExpenseDAO;

public class ExpenseDAOTest {

	static ExpenseDAO dao;
	
	public static void setup() {
		try {
			dao = new ExpenseDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		System.out.println();
	}

	public void create() {
		System.out.println();

	}

	public void findById() {
		System.out.println();

	}

	public void findByFirstName() {
		System.out.println();

	}

	public void findByLastName() {
		System.out.println();

	}

	public void findByReasonLike() {
		System.out.println();

	}

	public void findByStatus() {
		System.out.println();

	}

	public void findAll() {
		System.out.println();

	}

	public void update() {
		System.out.println();

	}

	public void delete() {
		System.out.println();

	}

}
