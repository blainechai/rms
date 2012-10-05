package com.iv.rms.notification.server;

import com.google.appengine.api.users.UserServiceFactory;
import com.iv.rms.core.GWTAbstractRemoteServiceServlet;
import com.iv.rms.notification.client.NotificationServiceServletAdapter;
import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.NotificationException;

@SuppressWarnings("serial")
public class NotificationServiceServletAdapterImpl extends GWTAbstractRemoteServiceServlet implements NotificationServiceServletAdapter{
	
	@Override
	public void saveNotification(SimpleNotification notification) throws NotificationException{
		if (UserServiceFactory.getUserService().getCurrentUser() == null ){
			throw new NotificationException(getServiceLocator().getPropertyService().getValue(""));
		}else{
			getServiceLocator().getNotificationService().saveNotification(notification, getServiceLocator().getUserService().getCurrentUser());
		}
	}
	
	public Boolean hasUserTimeZone(){
		return getServiceLocator().getNotificationService().hasUserTimeZone( getServiceLocator().getUserService().getCurrentUser());
	}
	
	@Override
	public Long saveTempNotification(SimpleNotification notification) throws NotificationException {
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
