package com.dentalScheduleApp.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorldFilter extends GenericFilter {
	private FilterConfig filterConfig;
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain chain) throws java.io.IOException, javax.servlet.ServletException {
		System.out.println("Entering filter");
		request.setAttribute("hello", "Hello World!");
		chain.doFilter(request, response);
		System.out.println("Exiting HelloWorldFilter");
	}
	
}
