package com.browser.view;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import com.browser.main.VoiceBrowser;

public class ToolbarView {
	
	private static Button backButton;
	private static Button forwardButton;
	private static Button navButton;
	
	public static Pane CreateNavToolbar(VoiceBrowser voiceBrowserObj){
		
		// create a back button.
		backButton = new Button("Back");
	    backButton.setTooltip(new Tooltip("Go back or right click for history"));
	    //final ImageView backGraphic = new ImageView(new Image(Util.getResource("239706184.png")));
	    final ColorAdjust backColorAdjust = new ColorAdjust();
	    backColorAdjust.setBrightness(-0.1);
	    backColorAdjust.setContrast(-0.1);
	    /*backGraphic.setEffect(backColorAdjust);
	    backButton.setGraphic(backGraphic);
	    backGraphic.setPreserveRatio(true);
	    backGraphic.setFitHeight(32);*/
	    /*backButton.onActionProperty().set(new EventHandler<ActionEvent>() {
	      @Override
	      public void handle(ActionEvent actionEvent) {
	        if (voiceBrowserObj.getBrowser().getHistory().canNavBack()) {
	        	voiceBrowserObj.getBrowser().navTo(voiceBrowserObj.getBrowser().getHistory().requestNavBack());
	        }
	      }
	    });
	    backButton.setOnMouseReleased(voiceBrowserObj.getBrowser().getHistory().createShowHistoryMouseEvent(backButton));
	    */
	    
	    // create a forward button.
	    forwardButton = new Button("Forward");
	    forwardButton.setTranslateX(-2);
	    //final ImageView forwardGraphic = new ImageView(new Image(Util.getResource("1813406178.png")));
	    final ColorAdjust forwardColorAdjust = new ColorAdjust();
	    forwardColorAdjust.setBrightness(-0.1);
	    forwardColorAdjust.setContrast(-0.1);
	    /*forwardGraphic.setEffect(forwardColorAdjust);
	    forwardGraphic.setPreserveRatio(true);
	    forwardGraphic.setFitHeight(20);
	    forwardButton.setGraphic(forwardGraphic);
	    forwardButton.setTooltip(new Tooltip("Go forward"));
	    forwardButton.onActionProperty().set(new EventHandler<ActionEvent>() {
	      @Override
	      public void handle(ActionEvent actionEvent) {
	        if (chrome.getBrowser().getHistory().canNavForward()) {
	          chrome.getBrowser().navTo(chrome.getBrowser().getHistory().requestNavForward());
	        }
	      }
	    });
	    forwardButton.setOnMouseReleased(chrome.getBrowser().getHistory().createShowHistoryMouseEvent(backButton));*/
	    
	 // create a navigate button.
	    navButton = new Button("Go");
	    navButton.setTooltip(new Tooltip("Go to or rejuvenate the location"));
	    //final ImageView navGraphic = new ImageView(new Image(Util.getResource("Forward Arrow.png")));
	    final ColorAdjust navColorAdjust = new ColorAdjust();
	    navColorAdjust.setContrast(-0.7);
	    /*navGraphic.setEffect(navColorAdjust);
	    navGraphic.setPreserveRatio(true);
	    navGraphic.setFitHeight(14);
	    navButton.setGraphic(navGraphic);
	    navButton.onActionProperty().set(new EventHandler<ActionEvent>() {
	      @Override
	      public void handle(ActionEvent actionEvent) {
	        chrome.getBrowser().navTo(chrome.getBrowser().getLocField().getText());
	      }
	    });*/
	    
	    
	 // align all of the navigation widgets in a horizontal toolbar.
	    final HBox navPane = new HBox();
	    navPane.setAlignment(Pos.CENTER);
	    navPane.getStyleClass().add("toolbar");
	    navPane.setSpacing(5);
	    //navPane.getChildren().addAll(backButton);
	    //navPane.getChildren().addAll(backButton, forwardButton, chrome.getChromeLocField(), chrome.getTabManager().getTabPane(), chrome.getTabManager().getNewTabButton(), navButton);
	    navPane.getChildren().addAll(backButton, forwardButton, voiceBrowserObj.getAddressBarField(), navButton);
	    navPane.setFillHeight(false);
	    Platform.runLater(new Runnable() {
	      public void run() {
	        navPane.setMinHeight(navPane.getHeight());
	      }
	    });

	    final InnerShadow innerShadow = new InnerShadow();
	    innerShadow.setColor(Color.ANTIQUEWHITE);
	    navPane.setEffect(innerShadow);
	    
	    return navPane;
	}
}
