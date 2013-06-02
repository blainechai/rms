package com.iv.rms.chat;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class ChatService {

    public void sendMessage(String id, String message) {
	JID jid = new JID(id);
	Message msg = new MessageBuilder().withRecipientJids(jid).withBody(message).build();
	boolean messageSent = false;
	XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	SendResponse status = xmpp.sendMessage(msg);
	messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);
	if (!messageSent) {
	    System.out.println("Failed to send the message to the user");
	}
    }

}
