package com.skillstorm.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.ExpenseDAO;
import com.skillstorm.models.Expense;

@WebServlet(urlPatterns = "/*")
public class ExpenseServlet extends HttpServlet {

	private ExpenseDAO expenseDAO = new ExpenseDAO();

	// Return all expenses
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(req.getPathInfo());
		System.out.println(req.getServletContext());
		System.out.println(req.getServletPath());
		System.out.println(req.getContextPath());
		System.out.println(req.getRequestURI());

		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(expenseDAO.findAll()));
			resp.setContentType("application/json");
		} catch (JsonProcessingException e) {
			System.err.println("JsonProcessingException: " + e);
		} catch (IOException e) {
			System.err.println("IOException: " + e);
		} catch (SQLException e) {
			System.err.println("SQLException: " + e);
		}

	}

	// Create an expense
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			expenseDAO.create(new ObjectMapper().readValue(req.getInputStream(), Expense.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Update an expense
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			System.out.println("doPut works!");

	}

	// Delete an expense
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			System.out.println("doDelete works!");

	}

}
