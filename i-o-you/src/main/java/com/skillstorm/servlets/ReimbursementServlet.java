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
import com.skillstorm.daos.ReimbursementStatusDAO;

@WebServlet(urlPatterns = "/reimbursementstatuses")
public class ReimbursementServlet extends HttpServlet {

	private ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(reimbursementStatusDAO.findAll()));
			resp.setContentType("application/json");
		} catch (JsonProcessingException e) {
			System.err.println("JsonProcessingException: " + e);
		} catch (IOException e) {
			System.err.println("IOException: " + e);
		} catch (SQLException e) {
			System.err.println("SQLException: " + e);
		}

	}
}