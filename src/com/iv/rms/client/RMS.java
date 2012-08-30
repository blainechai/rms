package com.iv.rms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.iv.rms.shared.FieldVerifier;
import com.summatech.gwt.client.HourMinutePicker;
import com.summatech.gwt.client.HourMinutePicker.PickerFormat;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RMS implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("workspace");
		RootPanel.get("sendButtonContainer").add(sendButton, 196, 416);
		
		Label lblWhen = new Label("When");
		rootPanel.add(lblWhen, 134, 126);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setValue(DateTimeFormat.getShortDateFormat().parse("2012-08-30"));
		rootPanel.add(datePicker, 134, 148);
		datePicker.setSize("223px", "181px");
		HourMinutePicker hourMinutePicker = new HourMinutePicker(PickerFormat._24_HOUR);
		hourMinutePicker.setTime("", 00 ,00);
		rootPanel.add(hourMinutePicker, 360, 148);
		
		Label lblHow = new Label("How");
		rootPanel.add(lblHow, 134, 337);
		
		SimpleCheckBox simpleCheckBox = new SimpleCheckBox();
		simpleCheckBox.setName("Mail");
		rootPanel.add(simpleCheckBox, 307, 359);
		
		SimpleCheckBox simpleCheckBox_1 = new SimpleCheckBox();
		rootPanel.add(simpleCheckBox_1, 134, 359);
		
		SimpleCheckBox simpleCheckBox_2 = new SimpleCheckBox();
		rootPanel.add(simpleCheckBox_2, 210, 359);
		
		InlineLabel nlnlblNewInlinelabel = new InlineLabel("Mail");
		rootPanel.add(nlnlblNewInlinelabel, 236, 359);
		
		InlineLabel nlnlblYm = new InlineLabel("YM");
		rootPanel.add(nlnlblYm, 160, 359);
		nlnlblYm.setSize("24px", "16px");
		
		InlineLabel nlnlblSms = new InlineLabel("SMS");
		rootPanel.add(nlnlblSms, 333, 359);
		nlnlblSms.setSize("24px", "16px");
		
		InlineLabel nlnlblWhat = new InlineLabel("What");
		rootPanel.add(nlnlblWhat, 134, 16);
		
		TextArea textArea = new TextArea();
		rootPanel.add(textArea, 134, 38);
		textArea.setSize("215px", "69px");

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
		class MyHandler implements ClickHandler, KeyUpHandler {
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
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = "";
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
	}
}
