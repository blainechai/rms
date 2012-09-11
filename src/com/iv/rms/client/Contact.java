package com.iv.rms.client;

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
import com.iv.rms.shared.ApplicationException;

public class Contact implements EntryPoint {
	private static final String SUBJECT = "Subject";
	private static final String YOUR_MESSAGE_GOES_HERE = "Your message goes here";
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";
	
	private final NotificationServiceAsync notificationService = GWT.create(NotificationService.class);

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
		
		final Button sendButtton = new Button("Submit");
		sendButtton.setEnabled(false);
		
		final Label errorLabel = new Label("");
		errorLabel.setStyleName("errorLabel");
		verticalPanel.add(errorLabel);
		final Label lblNewLabel = new Label("Contact us");
		lblNewLabel.setStyleName("contactUsLabel");
		verticalPanel.add(lblNewLabel);
		
		final TextBox subjectText = new TextBox();
		final TextArea messageBox = new TextArea();
		subjectText.setText(SUBJECT);
		subjectText.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				if ( subjectText.getText().equalsIgnoreCase(SUBJECT) ){
					subjectText.setText("");
				}
			}
		});
		subjectText.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				if ( subjectText.getText().equalsIgnoreCase("") ){
					subjectText.setText(SUBJECT);
					sendButtton.setEnabled(false);
				}else if ( !messageBox.getText().equalsIgnoreCase(YOUR_MESSAGE_GOES_HERE)){
					sendButtton.setEnabled(true);
				}
			}
				
		});
		subjectText.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if ( !messageBox.getText().equalsIgnoreCase(YOUR_MESSAGE_GOES_HERE)){
					sendButtton.setEnabled(true);
				}
				
			}
		});
		verticalPanel.add(subjectText);
		
		
		messageBox.setText(YOUR_MESSAGE_GOES_HERE);
		messageBox.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				if ( messageBox.getText().equalsIgnoreCase(YOUR_MESSAGE_GOES_HERE) ){
					messageBox.setText("");
				}
			}
		});
		messageBox.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				if ( messageBox.getText().equalsIgnoreCase("") ){
					messageBox.setText(YOUR_MESSAGE_GOES_HERE);
					sendButtton.setEnabled(false);
				}else if ( !subjectText.getText().equalsIgnoreCase(SUBJECT)){
					sendButtton.setEnabled(true);
				}
			}
				
		});
		messageBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if ( !subjectText.getText().equalsIgnoreCase(SUBJECT)){
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
				notificationService.saveUserContactMessage(subjectText.getText(), messageBox.getText(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						messageBox.removeFromParent();
						subjectText.removeFromParent();
						sendButtton.removeFromParent();
						lblNewLabel.setText("Your message has been sent. Thank you.");
						sendButtton.setEnabled(true);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						sendButtton.setEnabled(true);
						if ( caught instanceof ApplicationException ){
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

