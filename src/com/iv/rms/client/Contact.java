package com.iv.rms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Contact implements EntryPoint {
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
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 10, 10);
		verticalPanel.setSize("430px", "199px");
		
		TextBox txtbxFrom = new TextBox();
		txtbxFrom.setText("From");
		verticalPanel.add(txtbxFrom);
		
		TextArea textArea = new TextArea();
		verticalPanel.add(textArea);
		textArea.setSize("304px", "144px");
		rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
	}
}

