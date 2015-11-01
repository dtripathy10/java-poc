package edu.poc.javaee.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class SampleFilter
 */
@WebFilter("/*")
public class SampleFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SampleFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String token = request.getParameter("token");
		if(token == null) {
			System.err.println("request would not be processed!!");
			return;
		}
		System.out.println("request before process!!");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		System.out.println("request after process!!");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.err.println("filter is initialized");
	}

}
