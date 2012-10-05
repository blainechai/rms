package com.iv.rms.notification.server;

import com.google.appengine.api.users.UserServiceFactory;
import com.iv.rms.core.AbstractServlet;
import com.iv.rms.notification.client.NotificationServiceServletAdapter;
import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.ApplicationException;

@SuppressWarnings("serial")
public class NotificationServiceServletAdapterImpl extends AbstractServlet implements NotificationServiceServletAdapter{
	
	@Override
	public void saveNotification(SimpleNotification notification) throws ApplicationException{
		if (UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new ApplicationException(getServiceLocator().getPropertyService().getValue(""));
		}else{
			getServiceLocator().getNotificationService().saveNotification(notification, getServiceLocator().getUserService().getCurrentUser());
		}
	}
	
	public Boolean hasUserTimeZone(){
		return getServiceLocator().getNotificationService().hasUserTimeZone( getServiceLocator().getUserService().getCurrentUser());
	}
	
	@Override
	public Long saveTempNotification(SimpleNotification notification) throws ApplicationException {
		return getServiceLocator().getNotificationService().saveTempNotification(notification);
	}

	@Override
	public SimpleNotification getTempNotification(Long id) {
		return getServiceLocator().getNotificationService().getTempNotification(id);
	}

	@Override
	public void processPendingNotification() {
		getServiceLocator().getNotificationService().processPendingNotification();
	}

}
