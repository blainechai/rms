package com.iv.rms.core;

import com.iv.rms.contact.ContactService;
import com.iv.rms.mail.MailService;
import com.iv.rms.notification.NotificationService;

public class ServiceLocator {
	
	private MailService mailService ;
	
	private PropertyService propertyService;
	
	private NotificationService notificationService;
	
	private ContactService contactService;

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public PropertyService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

}
