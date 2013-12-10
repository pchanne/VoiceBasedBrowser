/**
 * This class is a controller class responsible
 *  for instantiating BrowserEngine(webView) for selected tab.  
 */

package com.browser.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.w3c.dom.DocumentFragment;

import com.browser.main.VoiceBrowser;
import com.browser.reader.FileReader;

public class BrowserWindow extends Pane {

	public WebView browser;

	public WebEngine webEngine;
	private History history;
	private final TextField locField = new TextField(); // the location the
														// browser engine is
														// currently pointing at
														// (or where the user
														// can type in where to
														// go next).
	public static String DEFAULT_HOME = "http://www.google.com";
	public FileReader titleReader;
	private String selectedText;

	public static double currentWidth;
	public static double currentHeight;

	public String getSelectedText() {
		return selectedText;
	}

	public void setSelectedText(String selectedText) {
		this.selectedText = selectedText;
	}

	public TextField getLocField() {
		return locField;
	}

	public BrowserWindow() {
		history = new History(this);
		browser = new WebView();
		webEngine = browser.getEngine();
		titleReader = new FileReader();
		initBrowser();
	}

	public BrowserWindow(String temp) {
		history = new History();
	}

	// initialized browser with default home
	private void initBrowser() {

		selectedText = new String();
		// apply the styles
		getStyleClass().add("browser");
		// load the web page
		webEngine.load(DEFAULT_HOME);
		// add the web view to the scene
		getChildren().add(browser);

		browser.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

				VoiceBrowser.logger
						.debug("Some text is selected by mouse event ---------------------");
				selectedText = (String) webEngine
						.executeScript("window.getSelection().toString()");
				webEngine
						.executeScript("var d = document.getElementsByTagName('h4'); for (var i = 0; i < d.length; i++) {d[i].style.backgroundColor = 'blue';};");
				{
					DocumentFragment s1 = (DocumentFragment) webEngine
							.executeScript("window.getSelection().getRangeAt().cloneContents()");
				}
			}

		});

		locField.setPromptText("Where do you want to go today?");
		locField.setTooltip(new Tooltip("Enter a location or find happiness."));
		locField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER)) {
					try {
						navTo(locField.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public BrowserWindow getBrowser() {

		return this;
	}

	private Node createSpacer() {
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		return spacer;
	}

	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}

	@Override
	protected double computePrefWidth(double height) {
		return 750;
	}

	@Override
	protected double computePrefHeight(double width) {
		return 500;
	}

	// This function is navigates browser to requested url
	public void navTo(String loc) throws IOException {
		if (loc == null)
			loc = "";
		if (loc.startsWith("google")) { // search google
			loc = "http://www.google.com/search?q="
					+ loc.substring("google".length()).trim()
							.replaceAll(" ", "+");
		} else if (loc.startsWith("wiki")) {
			loc = "http://en.wikipedia.org/w/index.php?search="
					+ loc.substring("wiki".length()).trim()
							.replaceAll(" ", "+");
		} else if (loc.startsWith("find")) { // search default (google) due to//
												// keyword
			loc = "http://www.google.com/search?q="
					+ loc.substring("find".length()).trim()
							.replaceAll(" ", "+");
		} else if (loc.startsWith("search")) { // search default (google) due to
												// keyword
			loc = "http://www.google.com/search?q="
					+ loc.substring("search".length()).trim()
							.replaceAll(" ", "+");
		} else if (loc.contains(" ")) { // search default (google) due to space
			loc = "http://www.google.com/search?q="
					+ loc.trim().replaceAll(" ", "+");
		} else if (!(loc.startsWith("http://") || loc.startsWith("https://"))
				&& !loc.isEmpty()) {
			loc = "http://" + loc; // default to http
		}

		// ask the webview to navigate to the given location.
		if (!loc.equals(getView().getEngine().getLocation())) {
			if (!loc.isEmpty()) {
				browser.getEngine().load(loc);
			} else {
				getView().getEngine().loadContent("");
			}
		} else {
			getView().getEngine().reload();
		}

		// webview will grab the focus if automatically if it has an html input
		// control to display, but we want it
		// to always grab the focus and kill the focus which was on the input
		// bar, so just set ask the platform to focus
		// the web view later (we do it later, because if we did it now, the
		// default focus handling might kick in and override our request).
		Platform.runLater(new Runnable() {
			public void run() {
				getView().requestFocus();
			}
		});
	}

	public History getHistory() {
		return history;
	}

	public WebView getView() {
		return browser;
	}

}
