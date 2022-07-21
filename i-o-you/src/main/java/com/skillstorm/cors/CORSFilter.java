package com.skillstorm.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class CORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("CORSFilter processing request..");
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
//		System.out.println("CORSFilter initialized");
	}

	@Override
	public void destroy() {
//		System.out.println("CORSFilter destroyed");
	}

}