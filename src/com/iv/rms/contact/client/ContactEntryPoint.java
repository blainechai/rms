package com.iv.rms.contact.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iv.rms.contact.shared.ContactException;

public class ContactEntryPoint implements EntryPoint {

    private static final String YOUR_MESSAGE_HAS_BEEN_SENT_THANK_YOU = "Your message has been sent. Thank you.";

    private static final String SUBMIT = "Submit";

    private static final String CONTACT_US = "Contact us";

    private static final String SUBJECT = "Subject";

    private static final String YOUR_MESSAGE_GOES_HERE = "Your message goes here";

    private final ContactServiceServletAdapterAsync contactService = GWT.create(ContactServiceServletAdapter.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

	RootPanel rootPanel = RootPanel.get("workspace");
	rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);

	VerticalPanel verticalPanel = new VerticalPanel();
	verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	verticalPanel.getElement().getStyle().setPosition(Position.RELATIVE);
	rootPanel.add(verticalPanel);
	verticalPanel.setSize("430px", "199px");

	final Button sendButtton = new Button(SUBMIT);
	sendButtton.setEnabled(false);

	final Label errorLabel = new Label("");
	errorLabel.setStyleName("errorLabel");
	verticalPanel.add(errorLabel);
	final Label lblNewLabel = new Label(CONTACT_US);
	lblNewLabel.setStyleName("contactUsLabel");
	verticalPanel.add(lblNewLabel);

	final TextBox subjectText = new TextBox();
	final TextArea messageBox = new TextArea();
	subjectText.setText(SUBJECT);
	subjectText.addFocusHandler(new FocusHandler() {

	    @Override
	    public void onFocus(FocusEvent event) {
		if (subjectText.getText().equalsIgnoreCase(SUBJECT)) {
		    subjectText.setText("");
		}
	    }
	});
	subjectText.addBlurHandler(new BlurHandler() {

	    @Override
	    public void onBlur(BlurEvent event) {
		if (subjectText.getText().equalsIgnoreCase("")) {
		    subjectText.setText(SUBJECT);
		    sendButtton.setEnabled(false);
		} else if (!messageBox.getText().equalsIgnoreCase(YOUR_MESSAGE_GOES_HERE)) {
		    sendButtton.setEnabled(true);
		}
	    }

	});
	subjectText.addKeyUpHandler(new KeyUpHandler() {

	    @Override
	    public void onKeyUp(KeyUpEvent event) {
		if (!messageBox.getText().equalsIgnoreCase(YOUR_MESSAGE_GOES_HERE)) {
		    sendButtton.setEnabled(true);
		}

	    }
	});
	verticalPanel.add(subjectText);

	messageBox.setText(YOUR_MESSAGE_GOES_HERE);
	messageBox.addFocusHandler(new FocusHandler() {

	    @Override
	    public void onFocus(FocusEvent event) {
		if (messageBox.getText().equalsIgnoreCase(YOUR_MESSAGE_GOES_HERE)) {
		    messageBox.setText("");
		}
	    }
	});
	messageBox.addBlurHandler(new BlurHandler() {

	    @Override
	    public void onBlur(BlurEvent event) {
		if (messageBox.getText().equalsIgnoreCase("")) {
		    messageBox.setText(YOUR_MESSAGE_GOES_HERE);
		    sendButtton.setEnabled(false);
		} else if (!subjectText.getText().equalsIgnoreCase(SUBJECT)) {
		    sendButtton.setEnabled(true);
		}
	    }

	});
	messageBox.addKeyUpHandler(new KeyUpHandler() {

	    @Override
	    public void onKeyUp(KeyUpEvent event) {
		if (!subjectText.getText().equalsIgnoreCase(SUBJECT)) {
		    sendButtton.setEnabled(true);
		}

	    }
	});
	verticalPanel.add(messageBox);
	messageBox.setSize("304px", "144px");

	sendButtton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		errorLabel.setText("");
		sendButtton.setEnabled(false);
		contactService.saveUserContactMessage(subjectText.getText(), messageBox.getText(), new AsyncCallback<Void>() {

		    @Override
		    public void onSuccess(Void result) {
			messageBox.removeFromParent();
			subjectText.removeFromParent();
			sendButtton.removeFromParent();
			lblNewLabel.setText(YOUR_MESSAGE_HAS_BEEN_SENT_THANK_YOU);
			sendButtton.setEnabled(true);
		    }

		    @Override
		    public void onFailure(Throwable caught) {
			sendButtton.setEnabled(true);
			if (caught instanceof ContactException) {
			    errorLabel.setText(caught.getMessage());
			}
		    }
		});

	    }
	});
	rootPanel.add(sendButtton);
	rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
    }
}
