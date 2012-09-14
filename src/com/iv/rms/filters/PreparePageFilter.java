package com.iv.rms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.appengine.api.utils.SystemProperty;
import com.iv.rms.Constants;

public class PreparePageFilter implements Filter {

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
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
