package com.browser.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.browser.controller.TabViewController;
import com.browser.controller.ViewController;
import com.browser.model.BookmarkModel;
import com.browser.view.BrowserTabBarView;
import com.browser.view.SideBarView;
import com.browser.view.TabView;

public class VoiceBrowser extends Application {

	private Scene scene;
	private TabViewController tabViewController;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch();
	}

	// TO DO: add excptions - malformed and unsupported
	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Voice Based Browser");
		tabViewController = new TabViewController();
		
		scene = new Scene(tabViewController.getBrowserTabBarView().getBrowserTabHolder(), 1020, 650);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * @param scene
	 *            the scene to set
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}


}
