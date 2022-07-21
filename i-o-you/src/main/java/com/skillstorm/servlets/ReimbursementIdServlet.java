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

@WebServlet(urlPatterns = "/reimbursementstatuses/*")
public class ReimbursementIdServlet extends HttpServlet {

	private ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAO();

	// Returns an expense
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = getReimbursementStatusId(req);
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(reimbursementStatusDAO.findById(id)));
		} catch (JsonProcessingException e) {
			System.err.println("JsonProcessingException: " + e);
		} catch (IOException e) {
			System.err.println("IOException: " + e);
		} catch (SQLException e) {
			System.err.println("SQLException: " + e);
		}
		resp.setContentType("application/json");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = getReimbursementStatusId(req);

		try {
			reimbursementStatusDAO.delete(reimbursementStatusDAO.findById(id));
			resp.getWriter().println(new ObjectMapper().writeValueAsString(reimbursementStatusDAO.findAll()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getReimbursementStatusId(HttpServletRequest req) {
		String[] path = req.getPathInfo().split("/"); // string array containing trailing strings at splits
		return Integer.parseInt(path[1]);
	}

}