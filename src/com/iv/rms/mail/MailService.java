package com.iv.rms.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.iv.rms.entity.Notification;
import com.iv.rms.entity.Owner;

public class MailService {

	public void sendMail(Notification notification, Owner owner) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody = notification.getMessage();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("mailremind@gmail.com", "www.mail-remind.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(owner.getEmail(), owner.getName()));
			msg.setSubject("Automatc reminder from www.mail-remind.com");
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
