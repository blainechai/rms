package com.iv.rms.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

@SuppressWarnings("serial")
public class ChatTest extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse responose) throws ServletException, IOException {
		JID jid = new JID("vasile.iacome@gmail.com");
		String msgBody = "No one has sent you a gift on Example.com. To view: http://example.com/gifts/";
		Message msg = new MessageBuilder().withRecipientJids(jid).withBody(msgBody).build();
		boolean messageSent = false;
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		SendResponse status = xmpp.sendMessage(msg);
		messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);

		if (!messageSent) {
			// Send an email message instead...
			System.out.println("Failed to send the message");
		}
	}

}
