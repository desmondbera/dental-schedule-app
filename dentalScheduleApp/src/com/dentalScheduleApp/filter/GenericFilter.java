package com.dentalScheduleApp.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GenericFilter implements javax.servlet.Filter{
	public FilterConfig filterConfig;
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain chain) throws java.io.IOException, javax.servlet.ServletException {
		chain.doFilter(request, response);
	}
	@Override
	public void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	@Override
	public void destroy() {
		
	}
}
