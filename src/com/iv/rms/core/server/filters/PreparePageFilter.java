package com.iv.rms.core.server.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.iv.rms.core.ApplicationServiceLocatorProvider;
import com.iv.rms.core.Constants;
import com.iv.rms.core.ServiceLocator;
import com.iv.rms.core.impl.WebApplicationServiceLocatorProvider;

public class PreparePageFilter implements Filter {

    private ApplicationServiceLocatorProvider serviceLocatorProvider;

    private static final String DOMAIN = "domain";
    private static Map<String, String> mappings = new HashMap<String, String>();

    static {
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
	ServiceLocator serviceLocator = getServiceLocator(((HttpServletRequest) request).getSession().getServletContext());
	if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
	    // check if it's production domain or dev domain
	    if (request.getServerName().equals(serviceLocator.getPropertyService().getValue(DOMAIN))) {
		request.setAttribute(Constants.GAE_MODE, true);
	    } else {
		request.setAttribute(Constants.GAE_MODE, false);
	    }
	} else {
	    request.setAttribute(Constants.GAE_MODE, false);
	}
	User user = UserServiceFactory.getUserService().getCurrentUser();
	request.setAttribute("user", user);
	if (user == null) {
	    request.setAttribute("loginURL", UserServiceFactory.getUserService().createLoginURL(((HttpServletRequest) request).getRequestURI()));
	} else {
	    request.setAttribute("email", user.getEmail());
	    request.setAttribute("logoutURL", UserServiceFactory.getUserService().createLogoutURL(((HttpServletRequest) request).getRequestURI()));
	}
	String target = mappings.get(((HttpServletRequest) request).getRequestURI());
	if (target != null) {
	    RequestDispatcher rd = request.getRequestDispatcher(target);
	    rd.include(request, response);
	} else {
	    chain.doFilter(request, response);
	}

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public ServiceLocator getServiceLocator(ServletContext servletContext) {
	serviceLocatorProvider = new WebApplicationServiceLocatorProvider(servletContext);
	return serviceLocatorProvider.getServiceLocator();
    }

}
