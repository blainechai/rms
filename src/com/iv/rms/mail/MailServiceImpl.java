package com.iv.rms.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.iv.rms.core.AbstractService;
import com.iv.rms.notification.Notification;
import com.iv.rms.user.Owner;

@Component
public class MailServiceImpl extends AbstractService implements MailService {

    private static final String ADMIN_EMAIL = "adminEmail";
    private static final String DEFAULT_NOTIFICATION_SUBJECT = "defaultNotificationSubject";
    private static final String SENDER_EMAIL_NAME = "senderEmailName";
    private static final String SENDER_EMAIL_ADDRESS = "senderEmailAddress";

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.mail.MailService#sendMail(com.iv.rms.entity.Notification,
     * com.iv.rms.entity.Owner)
     */
    @Override
    public void sendMail(Notification notification, Owner owner) {
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props, null);
	String msgBody = notification.getMessage();
	try {
	    Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(getProperty(SENDER_EMAIL_ADDRESS), getProperty(SENDER_EMAIL_NAME)));
	    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(owner.getEmail(), owner.getName()));
	    msg.setSubject(getProperty(DEFAULT_NOTIFICATION_SUBJECT));
	    msg.setText(msgBody);
	    Transport.send(msg);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iv.rms.mail.MailService#sendAdminMail(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void sendAdminMail(String subject, String messsage) {
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props, null);
	try {
	    Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(getProperty(SENDER_EMAIL_ADDRESS), getProperty(SENDER_EMAIL_NAME)));
	    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(getProperty(ADMIN_EMAIL), ""));
	    msg.setSubject(subject);
	    msg.setText(messsage);
	    Transport.send(msg);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
