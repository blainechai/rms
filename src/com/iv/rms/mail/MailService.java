package com.iv.rms.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.iv.rms.entity.Notification;

public class MailService {

	public void sendMail(Notification notification) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody = notification.getMessage();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("vasile.iacome@gmail.com", "RMS - Reminder"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("iv_eleven@yahoo.com", "Vasile Iacome"));
			msg.setSubject("Reminder from RMS");
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
