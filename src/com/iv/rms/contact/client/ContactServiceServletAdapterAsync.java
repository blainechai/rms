package com.iv.rms.contact.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContactServiceServletAdapterAsync {

    void saveUserContactMessage(String subject, String message, AsyncCallback<Void> callback);

    void test(AsyncCallback<Void> callback);

}
