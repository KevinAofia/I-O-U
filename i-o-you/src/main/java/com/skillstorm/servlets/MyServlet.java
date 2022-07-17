package com.skillstorm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; //import javax servlet API to use Servlets
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/my-servlet")
public class MyServlet extends HttpServlet {

	// GET to /i-o-you calls doGet
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet works!");
		resp.getWriter().append("doGet works!");
	}

	// POST to /i-o-you calls doPost
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost works!");
		System.out.println(req.getParameter("in1"));
//		resp.sendRedirect("index.html"); // direct to another page
		req.getRequestDispatcher("index.html").forward(req, resp); // forward req,resp to another page - server-side
	}

	// UPDATE to /i-o-you calls doPost
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("doPut works!");

	}

	// DELETE to /i-o-you calls doPost
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("doDelete works!");

	}

}
