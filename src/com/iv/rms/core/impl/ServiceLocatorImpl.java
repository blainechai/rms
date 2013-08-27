package com.iv.rms.core.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.iv.rms.contact.ContactService;
import com.iv.rms.core.ServiceLocator;
import com.iv.rms.core.PropertyService;
import com.iv.rms.mail.MailService;
import com.iv.rms.notification.NotificationService;
import com.iv.rms.sms.SmsService;
import com.iv.rms.user.UserService;

@Component
public class ServiceLocatorImpl implements ServiceLocator {

    private MailService mailService;

    @Inject
    private PropertyService propertyService;

    @Inject
    private NotificationService notificationService;

    @Inject
    private ContactService contactService;

    @Inject
    private UserService userService;

    @Inject
    private SmsService smsService;

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.IServiceLocator#getUserService()
     */
    @Override
    @Inject
    public UserService getUserService() {
	return userService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iv.rms.core.IServiceLocator#setUserService(com.iv.rms.user.UserService
     * )
     */
    @Override
    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.IServiceLocator#getContactService()
     */
    @Override
    public ContactService getContactService() {
	return contactService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iv.rms.core.IServiceLocator#setContactService(com.iv.rms.contact.
     * ContactService)
     */
    @Override
    public void setContactService(ContactService contactService) {
	this.contactService = contactService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.IServiceLocator#getNotificationService()
     */
    @Override
    public NotificationService getNotificationService() {
	return notificationService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.IServiceLocator#setNotificationService(com.iv.rms.
     * notification.NotificationService)
     */
    @Override
    public void setNotificationService(NotificationService notificationService) {
	this.notificationService = notificationService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.IServiceLocator#getMailService()
     */
    @Override
    public MailService getMailService() {
	return mailService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iv.rms.core.IServiceLocator#setMailService(com.iv.rms.mail.MailService
     * )
     */
    @Override
    public void setMailService(MailService mailService) {
	this.mailService = mailService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.IServiceLocator#getPropertyService()
     */
    @Override
    public PropertyService getPropertyService() {
	return propertyService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.core.IServiceLocator#setPropertyService(com.iv.rms.core.
     * PropertyService)
     */
    @Override
    public void setPropertyService(PropertyService propertyService) {
	this.propertyService = propertyService;
    }

    @Override
    public SmsService getSmsService() {
	return smsService;
    }

    @Override
    public void setSmsService(SmsService smsService) {
	this.smsService = smsService;
    }

}
