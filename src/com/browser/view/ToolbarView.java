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
	
	public static Pane CreateNavToolbar(VoiceBrowser voiceBrowserObj){
		
		// create a back button.
		backButton = new Button();
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
	    
	 // align all of the navigation widgets in a horizontal toolbar.
	    final HBox navPane = new HBox();
	    navPane.setAlignment(Pos.CENTER);
	    navPane.getStyleClass().add("toolbar");
	    navPane.setSpacing(5);
	    navPane.getChildren().addAll(backButton);
	    //navPane.getChildren().addAll(sidebarButton, backButton, forwardButton, chrome.getChromeLocField(), chrome.getTabManager().getTabPane(), chrome.getTabManager().getNewTabButton(), navButton);
	    navPane.setFillHeight(false);
	    Platform.runLater(new Runnable() {
	      @Override public void run() {
	        navPane.setMinHeight(navPane.getHeight());
	      }
	    });

	    final InnerShadow innerShadow = new InnerShadow();
	    innerShadow.setColor(Color.ANTIQUEWHITE);
	    navPane.setEffect(innerShadow);
	    
	    return navPane;
	}
}
