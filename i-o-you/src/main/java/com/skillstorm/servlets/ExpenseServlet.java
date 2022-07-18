package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.ExpenseDAO;
import com.skillstorm.models.Expense;

@WebServlet(urlPatterns = "/expenses")
public class ExpenseServlet extends HttpServlet {

	private ExpenseDAO expenseDAO = new ExpenseDAO();

	@Override
	public void init() throws ServletException {
		System.out.println("init() ...opening connection");	
	}
//
	@Override
	public void destroy() {
		System.out.println("destroy() ...closing connection");
	}

	// GET to /expenses calls doGet
	// returns all expenses
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.err.println("\n\nmessage\n\n");
		try {
			System.out.println(expenseDAO.findAll());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.getWriter().append("doGET: found all");
	}

	// POST to /expenses calls doPost
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost works!");

		// HTTP request body
//		System.out.println(req.getParameter("in1"));
//		resp.sendRedirect("index.html"); // direct to another page
//		req.getRequestDispatcher("index.html").forward(req, resp); // forward req,resp to another page - server-side

		// JSON
		InputStream requestBody = req.getInputStream();
		ObjectMapper objectMapper = new ObjectMapper();
		Expense expense = objectMapper.readValue(requestBody, Expense.class);
		String json = objectMapper.writeValueAsString(expense);
		resp.getWriter().println(json);

	}

	// UPDATE to /expenses calls doPost
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			System.out.println("doPut works!");

	}

	// DELETE to /expenses calls doPost
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			System.out.println("doDelete works!");

	}

}
