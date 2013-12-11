/**
 * This is a main class which is an entry point
 *  for Voice-based browser application. It creates
 * stage and scene and makes scene visible in a given pixel size.
 */

package com.browser.main;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.browser.controller.TabViewController;

public class VoiceBrowser extends Application {

	public static final Logger logger = Logger.getLogger(VoiceBrowser.class);
	private Scene scene;
	private TabViewController tabViewController;

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Start of the application");

		launch();
	}

	@Override
	public void start(Stage stage) throws MalformedURLException,
			UnsupportedEncodingException {

		stage.setTitle("Voice Based Browser");
		tabViewController = new TabViewController();

		scene = new Scene(tabViewController.getBrowserTabBarView()
				.getBrowserTabHolder(), 1020, 650);
		stage.setScene(scene);
		stage.show();
	}

}
