package com.iv.rms.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.utils.SystemProperty;
import com.iv.rms.Constants;

public class PreparePageFilter implements Filter {
	
	private	static Map<String, String> mappings = new HashMap<String, String>(); 
	
	static{
		mappings.put("/", "index.jsp");
		mappings.put("/index", "index.jsp");
		mappings.put("/contact", "contact.jsp");
		mappings.put("/whatsthis", "whatsthis.jsp");
	}

	public PreparePageFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if ( SystemProperty.environment.value() == SystemProperty.Environment.Value.Production ){
			request.setAttribute(Constants.GAE_MODE, true);
		}else{
			request.setAttribute(Constants.GAE_MODE, false);
		}
		String target = mappings.get(((HttpServletRequest)request).getRequestURI());
		if ( target != null ){
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.include(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
