package com.iv.rms.core;

import javax.inject.Inject;

import com.iv.rms.contact.ContactService;
import com.iv.rms.mail.MailService;
import com.iv.rms.notification.NotificationService;
import com.iv.rms.sms.SmsService;
import com.iv.rms.user.UserService;

public interface ServiceLocator {

    @Inject
    public UserService getUserService();

    public void setUserService(UserService userService);

    public ContactService getContactService();

    public void setContactService(ContactService contactService);

    public NotificationService getNotificationService();

    public void setNotificationService(NotificationService notificationService);

    public MailService getMailService();

    public void setMailService(MailService mailService);

    public PropertyService getPropertyService();

    public void setPropertyService(PropertyService propertyService);

    public SmsService getSmsService();

    public void setSmsService(SmsService smsService);

}