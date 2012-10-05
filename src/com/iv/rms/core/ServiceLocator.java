package com.iv.rms.core;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.iv.rms.contact.ContactService;
import com.iv.rms.mail.MailService;
import com.iv.rms.notification.NotificationService;
import com.iv.rms.user.UserService;

@Component
public class ServiceLocator {
	
	
	private MailService mailService ;
	
	@Inject
	private PropertyService propertyService;
	
	@Inject
	private NotificationService notificationService;
	
	@Inject
	private ContactService contactService;
	
	@Inject
	private UserService userService;

	@Inject
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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
