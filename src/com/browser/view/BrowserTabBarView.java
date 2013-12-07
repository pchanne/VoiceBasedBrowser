package com.browser.view;

import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

public class BrowserTabBarView {
	
	private static TabPane browserTabHolder;

	public BrowserTabBarView() {
		setupTabHolder();
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 * setup the properties of the tab holder
	 * 
	 */
	private void setupTabHolder() {
		browserTabHolder = new TabPane();
	}

	/*
	 * 
	 * getter for tabholder
	 * 
	 */
	public static TabPane getBrowserTabHolder() {
		return browserTabHolder;
	}
	
	public Parent createTabBarView(){
		
		HBox tabBarLayout = new HBox();
		tabBarLayout.getChildren().addAll(browserTabHolder);
		return tabBarLayout;
	}

}
