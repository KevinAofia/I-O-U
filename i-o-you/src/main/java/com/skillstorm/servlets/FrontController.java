package com.skillstorm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// FRONT CONTROLLER NOT IMPLEMENTED
//@WebServlet(urlPatterns = "/*") // EAGER loading
public class FrontController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init()");
	}

	@Override
	public void destroy() {
		System.out.println("destroy()");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get- DELETE THIS LINE");

		StringBuilder baseURL = new StringBuilder(req.getRequestURI());
		System.out.println(baseURL);
		
		int firstSlashIndex = baseURL.indexOf("/");
		baseURL.replace(firstSlashIndex, firstSlashIndex + 1, "");

		int secondSlashIndex = baseURL.indexOf("/");
		baseURL.replace(0, secondSlashIndex + 1, "");
		
		if (baseURL.indexOf("/") != -1) {
			int thirdSlashIndex = baseURL.indexOf("/");
			baseURL.replace(thirdSlashIndex, baseURL.length(), "");

		}
		// CONTINUE HERE
	}

}
