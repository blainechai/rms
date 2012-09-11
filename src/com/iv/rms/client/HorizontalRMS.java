package com.iv.rms.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.iv.rms.shared.ApplicationException;
import com.iv.rms.shared.FieldVerifier;
import com.summatech.gwt.client.HourMinutePicker;
import com.summatech.gwt.client.HourMinutePicker.PickerFormat;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HorizontalRMS implements EntryPoint {
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

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("workspace");
		rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
						
						VerticalPanel verticalPanel = new VerticalPanel();
						verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
						rootPanel.add(verticalPanel);
								final Label errorLabel = new Label();
								verticalPanel.add(errorLabel);
						
								HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
								verticalPanel.add(horizontalPanel_1);
								horizontalPanel_1.setSpacing(20);
								horizontalPanel_1.setSize("481px", "400px");
								horizontalPanel_1.getElement().getStyle().setPosition(Position.STATIC);
								
										final VerticalPanel whenPanel = new VerticalPanel();
										whenPanel.setSpacing(20);
										horizontalPanel_1.add(whenPanel);
										whenPanel.setHeight("341px");
										
												Label lblWhen = new Label("When");
												lblWhen.setStyleName("whenLbl");
												whenPanel.add(lblWhen);
												
														final DatePicker datePicker = new DatePicker();
														whenPanel.add(datePicker);
														datePicker.setValue(new Date());
														datePicker.setSize("262px", "182px");
														final HourMinutePicker hourMinutePicker = new HourMinutePicker(PickerFormat._24_HOUR);
														whenPanel.add(hourMinutePicker);
														
														
																hourMinutePicker.setTime("", 00, 00);
																
																		VerticalPanel whatPanel = new VerticalPanel();
																		whatPanel.setSpacing(20);
																		horizontalPanel_1.add(whatPanel);
																		
																				InlineLabel nlnlblWhat = new InlineLabel("What");
																				nlnlblWhat.setStyleName("whatLbl");
																				whatPanel.add(nlnlblWhat);
																				
																						final TextArea messageBox = new TextArea();
																						whatPanel.add(messageBox);
																						messageBox.setSize("262px", "222px");
																						
																						
																								VerticalPanel howPanel = new VerticalPanel();
																								howPanel.setSpacing(20);
																								horizontalPanel_1.add(howPanel);
																								howPanel.setSize("50px", "50px");
																								
																										Label lblHow = new Label("How");
																										lblHow.setStyleName("howLbl");
																										howPanel.add(lblHow);
																										lblHow.setWidth("50px");
																										
																												HorizontalPanel horizontalPanel = new HorizontalPanel();
																												howPanel.add(horizontalPanel);
																												horizontalPanel.setSpacing(5);
																												//horizontalPanel.setWidth("297px");
																												
																														final CheckBox mailCheckBox = new CheckBox("Mail");
																														mailCheckBox.setEnabled(false);
																														mailCheckBox.setChecked(true);
																														mailCheckBox.setStyleName("howCheckbox");
																														horizontalPanel.add(mailCheckBox);
																														
																																final Button sendButton = new Button("Send");
																																verticalPanel.add(sendButton);
																																
																																		// We can add style names to widgets
																																		sendButton.addStyleName("sendButton");
																																		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class NewNotificationHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				if (!FieldVerifier.isValidName(messageBox.getText())) {
					errorLabel.setText("Message is too short");
					errorLabel.setStyleName("errorLabel");
					return;
				}
				
				// Then, we send the input to the server.
				SimpleNotification sn = new SimpleNotification();
				sn.setMessage(messageBox.getText());
				sn.setMinutes(hourMinutePicker.getMinutes());
				sn.setDate(datePicker.getValue());
				sn.setTimeZone("" + callGetClientTimeZone());
				List<NotificationViews> selectedViews = new ArrayList<NotificationViews>();
				if (mailCheckBox.getValue()) {
					selectedViews.add(NotificationViews.MAIL);
				}
				sn.setViews(selectedViews);
				sendButton.setEnabled(false);
				notificationService.saveNotification(sn, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						sendButton.setEnabled(true);
						DateTimeFormat sdf = DateTimeFormat.getFormat("dd MMM yyyy HH:mm");
						Date d = datePicker.getValue();
						d.setHours(hourMinutePicker.getHour());
						d.setMinutes(hourMinutePicker.getMinute());
						errorLabel.setText("Done. You will receive a notification email on " + sdf.format(d) );
						errorLabel.setStyleName("");
					}

					@Override
					public void onFailure(Throwable caught) {
						sendButton.setEnabled(true);
						if ( caught  instanceof ApplicationException){
							errorLabel.setText(caught.getMessage());
						}else{
							dialogBox.setText("Operation failed");
							serverResponseLabel.addStyleName("serverResponseLabelError");
							serverResponseLabel.setHTML(SERVER_ERROR);
							dialogBox.center();
							closeButton.setFocus(true);
						}
					}
				});
			}
		}

		// Add a handler to send the name to the server
		NewNotificationHandler handler = new NewNotificationHandler();
		sendButton.addClickHandler(handler);
		
	}
	
	 private native int callGetClientTimeZone() /*-{
     	return $wnd.getClientTimeZone();
   	}-*/;
	
}
