package com.browser.main;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class BrowserWindow extends Region{
	
	private static WebView browser;
    private static WebEngine webEngine;
    private final TextField      locField = new TextField();    // the location the browser engine is currently pointing at (or where the user can type in where to go next).
    
    public TextField getLocField() {
		return locField;
	}

	public BrowserWindow(){
    	browser = new WebView();
        webEngine = browser.getEngine();
        initBrowser();
    }
    
    private void initBrowser(){
    	
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load("http://www.google.com");
        //add the web view to the scene
        getChildren().add(browser);
        
        
        //not sure why this is required
        HBox.setHgrow(locField, Priority.ALWAYS);
        locField.setPromptText("Where do you want to go today?");
        locField.setTooltip(new Tooltip("Enter a location or find happiness."));
        locField.setOnKeyReleased(new EventHandler<KeyEvent>() {
          @Override
          public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
              navTo(locField.getText());
            }
          }
        });
        locField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observableValue, Boolean from, Boolean to) {
              if (to) {
                Platform.runLater(new Runnable() {
                  @Override public void run() {
                    locField.selectAll();
                  }
                });
              }
            }
          });
    }
    
    
    
    public BrowserWindow getBrowser(){
    	        
    	return this;
    }
    
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
    
    public void navTo(String loc) {
        // modify the request location, to make it easier on the user for typing.
    // todo we probably don't want this default nav for empty .... work out what to do instead ....
//        if (loc == null || loc.isEmpty()) { // go home if the location field is empty.
//          loc = chrome.homeLocationProperty.get();
//        }
    	System.out.println(loc);
        if (loc == null) loc = "";
        if (loc.startsWith("google")) { // search google
          loc = "http://www.google.com/search?q=" + loc.substring("google".length()).trim().replaceAll(" ", "+");
        } else if (loc.startsWith("bing")) { // search bing
          loc = "http://www.bing.com/search?q=" + loc.substring("bing".length()).trim().replaceAll(" ", "+");
        } else if (loc.startsWith("yahoo")) { // search yahoo
          loc = "http://search.yahoo.com/search?p=" + loc.substring("yahoo".length()).trim().replaceAll(" ", "+");
        } else if (loc.startsWith("wiki")) {
          loc = "http://en.wikipedia.org/w/index.php?search=" + loc.substring("wiki".length()).trim().replaceAll(" ", "+");
        } else if (loc.startsWith("find")) { // search default (google) due to keyword
          loc = "http://www.google.com/search?q=" + loc.substring("find".length()).trim().replaceAll(" ", "+");
        } else if (loc.startsWith("search")) { // search default (google) due to keyword
          loc = "http://www.google.com/search?q=" + loc.substring("search".length()).trim().replaceAll(" ", "+");
        } else if (loc.contains(" ")) { // search default (google) due to space
          loc = "http://www.google.com/search?q=" + loc.trim().replaceAll(" ", "+");
        } else if (!(loc.startsWith("http://") || loc.startsWith("https://")) && !loc.isEmpty()) {
          loc = "http://" + loc;  // default to http
        }

        // ask the webview to navigate to the given location.
        if (!loc.equals(getView().getEngine().getLocation())) {
        	System.out.println(getView().getEngine().getLocation());
          if (!loc.isEmpty()) {
        	  System.out.println("location isn't empty!!: " + getView().getEngine());
        	  System.out.println("new: " + loc);
        	  browser.getEngine().load(loc);
        	  
          } else {
            getView().getEngine().loadContent("");
          }
        } else {
          getView().getEngine().reload();
        }

        // webview will grab the focus if automatically if it has an html input control to display, but we want it
        // to always grab the focus and kill the focus which was on the input bar, so just set ask the platform to focus
        // the web view later (we do it later, because if we did it now, the default focus handling might kick in and override our request).
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            getView().requestFocus();
          }
        });
      }
    
    public WebView getView() {
        return browser;
      }

}
