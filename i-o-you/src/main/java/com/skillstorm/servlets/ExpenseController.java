package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.models.Expense;

@WebServlet(urlPatterns = "/expenses")
public class ExpenseController extends HttpServlet {

	// JDBC code
	
	//INIT
	
	//SERVICE
	
	//DESTROY

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet works!");
		resp.getWriter().append("doGet works!");

		// JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString("test value");
		resp.getWriter().println(json);
		resp.setContentType("application/json");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HTTP request body
		System.out.println("doPost works!");
		System.out.println(req.getParameter("in1"));
//		resp.sendRedirect("index.html"); // direct to another page
		req.getRequestDispatcher("index.html").forward(req, resp); // forward req,resp to another page - server-side
		
		// JSON
		InputStream requestBody = req.getInputStream();
		ObjectMapper objectMapper = new ObjectMapper();
		Expense expense = objectMapper.readValue(requestBody, Expense.class);
		String json = objectMapper.writeValueAsString(expense);
		resp.getWriter().println(json);
		
	}

}
