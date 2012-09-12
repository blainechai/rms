package com.iv.rms.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.iv.rms.chat.ChatService;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.Owner;
import com.iv.rms.server.PropertyService;

public class MailService {

	private static final String DEFAULT_NOTIFICATION_SUBJECT = "defaultNotificationSubject";
	private static final String SENDER_EMAIL_NAME = "senderEmailName";
	private static final String SENDER_EMAIL_ADDRESS = "senderEmailAddress";
	
	private ChatService chatService = new ChatService();

	public void sendMail(Notification notification, Owner owner) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody = notification.getMessage();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(PropertyService.getInstance().getValue(SENDER_EMAIL_ADDRESS), PropertyService.getInstance().getValue(SENDER_EMAIL_NAME)));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(owner.getEmail(), owner.getName()));
			msg.setSubject(PropertyService.getInstance().getValue(DEFAULT_NOTIFICATION_SUBJECT));
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		chatService.sendMessage(owner.getEmail(), msgBody);
	}

}
