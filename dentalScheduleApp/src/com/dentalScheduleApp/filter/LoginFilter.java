package com.dentalScheduleApp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/*")
public class LoginFilter implements Filter{

//	private ArrayList<String> urlList;
//	
//	@Override
	@Override
	public void init(FilterConfig config) throws ServletException {
//		String urls = config.getInitParameter("avoid-urls");
//		StringTokenizer token = new StringTokenizer(urls, ",");
//		urlList = new ArrayList<String>();
//		
//		while(token.hasMoreTokens() ) {
//			System.out.println("adding this token: " + token.toString());
//			urlList.add(token.nextToken());
//		}
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String url = request.getServletPath();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        chain.doFilter(request, response);
//		System.out.println("----");
//		System.out.println("Our url is: " + url);
//		
//		
//		HttpSession session = request.getSession(false);
//		String userName = (String) session.getAttribute("username");
//		System.out.println("Our username in doFilter is: " +userName);
//		
//		if(session != null && !session.isNew()) {
//			System.out.println("** Inside of session == null **");
//			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
//		    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		    response.setDateHeader("Expires", 0); // Proxies.
//		
//			chain.doFilter(request, response);
////			response.sendRedirect(request.getContextPath() + "/login");
////			return;
//		} else {
//			System.out.println("Inside of else filter!");
//			
//			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
//		    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		    response.setDateHeader("Expires", 0); // Proxies.
////		    chain.doFilter(request, response);
////			System.out.println("Our contextPath is: "+request.getContextPath());
//			response.sendRedirect(request.getContextPath() + "/login");
//		}	
////		System.out.println("Post login action..");
////		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
////	    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
////	    response.setDateHeader("Expires", 0); // Proxies.
		System.out.println("---");
	}
	
//	@Override
	@Override
	public void destroy() {
		
	}
//	
}
