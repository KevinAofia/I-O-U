package com.skillstorm.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.ExpenseDAO;
import com.skillstorm.models.Expense;

@WebServlet(urlPatterns = "/expenses")
public class ExpenseServlet extends HttpServlet implements Filter {

	private ExpenseDAO expenseDAO = new ExpenseDAO();

	// Return all expenses
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpServletResponse response = (HttpServletResponse) resp;
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Headers", "*");
		if (((HttpServletRequest) req).getMethod().equals("OPTIONS")) {
			response.setStatus(202);
		}

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

	// Creates an expense
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			expenseDAO.create(new ObjectMapper().readValue(req.getInputStream(), Expense.class));
			resp.getWriter().println(new ObjectMapper().writeValueAsString(expenseDAO.findAll()));
			resp.setContentType("application/json");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CORSFilter processing request..");
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Methods", "*");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Headers", "*");
		if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
			resp.setStatus(202);
		}
		chain.doFilter(request, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("CORSFilter init()");
	}

	@Override
	public void destroy() {
		System.out.println("CORSFilter destroy()");
	}

}
