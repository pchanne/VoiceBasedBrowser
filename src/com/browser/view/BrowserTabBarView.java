/**
 * This class represents container for holding all the tabs in a browser
 */
package com.browser.view;

import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

public class BrowserTabBarView {

	private static TabPane browserTabHolder;

	public BrowserTabBarView() {
		setupTabHolder();
	}

	/*
	 * setup the properties of the tab holder
	 */
	private void setupTabHolder() {
		browserTabHolder = new TabPane();
	}

	/*
	 * getter for tabholder
	 */
	public static TabPane getBrowserTabHolder() {
		return browserTabHolder;
	}

	// creates the tab view, adds the tab pane to a horizontal layout for
	// display
	public Parent createTabBarView() {

		HBox tabBarLayout = new HBox();
		tabBarLayout.getChildren().addAll(browserTabHolder);
		return tabBarLayout;
	}

}
