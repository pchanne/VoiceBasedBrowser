/**
 * Sidebar is a static sidebarView which displays
 * smartnotes taken from multiple tabs.
 */
package com.browser.view;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.VBox;

public class SideBarView {

	private static VBox sideBar;
	private static TextArea textArea;
	private static TabPane sideBarTabPane;
	private static Tab smartNotesTab;
	private static Tab speechTab;

	public static TextArea getTextArea() {
		return textArea;
	}

	public static Node getSideBar() {
		return sideBar;
	}

	public static void setSideBar(VBox sideBar) {
		SideBarView.sideBar = sideBar;
	}

	// sets the content of the TextArea --> Smart Notes
	public static void setTextAreaText(ArrayList<String> noteText) {
		textArea.clear();
		for (int i = 0; i < noteText.size(); i++) {
			textArea.appendText(noteText.get(i) + "\n");
		}

	}

	/*
	 * this method would be called once, where sidebar will be created and this
	 * is stored in sidebar which later can be assigned to any javafx element
	 */
	public static void initSideBar() {
		sideBar = new VBox();
		sideBar.setStyle("-fx-background-color: WHITE; "
				+ "-fx-border-color: BLACK; -fx-border-width: 0 0.5 0 0;");
		sideBar.setPrefWidth(350.00);
		TabPane tabs = new TabPane();
		textArea = TextAreaBuilder.create().prefWidth(400).prefHeight(1600)
				.wrapText(true).build();
		TabPane sideBarTabPane = new TabPane();
		Tab smartNotesTab = new Tab("Smart Notes");
		Tab speechTab = new Tab("Speech");

		speechTab.setClosable(false);
		smartNotesTab.setClosable(false);
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setContent(textArea);
		sideBarTabPane.setPrefHeight(600.00);
		smartNotesTab.setContent(scrollpane);
		sideBarTabPane.getTabs().add(smartNotesTab);
		sideBarTabPane.getTabs().add(speechTab);
		sideBar.getChildren().add(sideBarTabPane);

	}

	public static VBox getBarDisplay() {
		return sideBar;
	}

	/*
	 * creates static side bar
	 */
	public static Node createSideBar() {

		smartNotesTab.setClosable(false);
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setContent(textArea);
		sideBarTabPane.setPrefHeight(600.00);
		smartNotesTab.setContent(scrollpane);
		sideBarTabPane.getTabs().add(smartNotesTab);
		sideBarTabPane.getTabs().add(speechTab);
		sideBar.getChildren().add(sideBarTabPane);

		return sideBar;
	}

}