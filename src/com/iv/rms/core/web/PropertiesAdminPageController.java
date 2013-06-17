package com.iv.rms.core.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iv.rms.core.ServiceLocator;

@Controller
public class PropertiesAdminPageController {
    
    @Inject
    private ServiceLocator serviceLocator;

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @RequestMapping(value = "/properties")
    public ModelAndView home() {
	System.out.println("PropertiesAdminPageController: Passing through...");
	System.out.println(getServiceLocator());
	ModelAndView view = new ModelAndView("properties");
	view.addObject("adminEmail", getServiceLocator().getPropertyService().getValue("adminEmail"));
	return view;
    }

   

}
