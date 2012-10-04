package com.iv.rms.notification.server;

import com.iv.rms.core.AbstractServlet;
import com.iv.rms.notification.client.NotificationServiceServletAdapter;
import com.iv.rms.notification.client.SimpleNotification;
import com.iv.rms.notification.shared.ApplicationException;

@SuppressWarnings("serial")
public class NotificationServiceServletAdapterImpl extends AbstractServlet implements NotificationServiceServletAdapter{
	
	@Override
	public void saveNotification(SimpleNotification notification) throws ApplicationException{
		getServiceLocator().getNotificationService().saveNotification(notification);
	}
	
	public Boolean hasUserTimeZone(){
		return getServiceLocator().getNotificationService().hasUserTimeZone();
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
