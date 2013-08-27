package com.iv.rms.user.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserConfigurationEntryPoint implements EntryPoint {

    public void onModuleLoad() {
	// Add the nameField and sendButton to the RootPanel
	// Use RootPanel.get() to get the entire body element
	RootPanel rootPanel = RootPanel.get("workspace");
	rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);

	VerticalPanel verticalPanel = new VerticalPanel();
	rootPanel.add(verticalPanel);

	HorizontalPanel horizontalPanel = new HorizontalPanel();
	verticalPanel.add(horizontalPanel);
	horizontalPanel.setSize("623px", "39px");

	InlineLabel nlnlblNewInlinelabel_1 = new InlineLabel("New InlineLabel");
	horizontalPanel.add(nlnlblNewInlinelabel_1);

	TextBox textBox = new TextBox();
	horizontalPanel.add(textBox);

	HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
	verticalPanel.add(horizontalPanel_1);
	horizontalPanel_1.setSize("623px", "35px");

	InlineLabel nlnlblNewInlinelabel = new InlineLabel("New InlineLabel");
	horizontalPanel_1.add(nlnlblNewInlinelabel);

	TextBox textBox_1 = new TextBox();
	horizontalPanel_1.add(textBox_1);

	DateTimeFormat sdf = DateTimeFormat.getFormat("mm");
	Date date = new Date();

	int realMin = Integer.parseInt(sdf.format(date));
	sdf = DateTimeFormat.getFormat("HH");

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

    }
}
