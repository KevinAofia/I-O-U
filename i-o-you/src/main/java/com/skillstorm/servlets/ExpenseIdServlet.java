package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.ExpenseDAO;
import com.skillstorm.daos.ReimbursementStatusDAO;
import com.skillstorm.models.Expense;

@WebServlet(urlPatterns = "/expenses/*")
public class ExpenseIdServlet extends HttpServlet {

	private ExpenseDAO expenseIdDAO = new ExpenseDAO();

	// return an expense
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = getExpenseId(req);
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(expenseIdDAO.findById(id)));
		} catch (JsonProcessingException e) {
			System.err.println("JsonProcessingException: " + e);
		} catch (IOException e) {
			System.err.println("IOException: " + e);
		} catch (SQLException e) {
			System.err.println("SQLException: " + e);
		}
		resp.setContentType("application/json");
	}

	// updates an expense
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = getExpenseId(req); // strict update to this id only

		ObjectMapper mapper = new ObjectMapper();
		InputStream requestBody = req.getInputStream();
		Expense expense = mapper.readValue(requestBody, Expense.class); // map JSON to POJO

		expense.setId(id); // strict update using this id only
		try {
			// strict update using FK constraint and mapped DB values
			expense.setStatus(new ReimbursementStatusDAO().findById(expense.getStatus().getId()));

			expenseIdDAO.update(expense);

			resp.getWriter().print(mapper.writeValueAsString(expenseIdDAO.findById(id)));
			resp.setContentType("application/json");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	// delete an expense
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = getExpenseId(req);

		try {
			expenseIdDAO.delete(expenseIdDAO.findById(id));
			resp.getWriter().println(new ObjectMapper().writeValueAsString(expenseIdDAO.findAll()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getExpenseId(HttpServletRequest req) {
		String[] path = req.getPathInfo().split("/"); // string array containing trailing strings at splits
		return Integer.parseInt(path[1]);
	}

}
