package com.iv.rms.mail;

import com.iv.rms.core.Service;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.Owner;

public interface MailService extends Service{

	public void sendMail(Notification notification, Owner owner);

	public void sendAdminMail(String subject, String messsage);

}