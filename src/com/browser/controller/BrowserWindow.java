package com.browser.controller;

import java.io.IOException;

import netscape.javascript.JSObject;

import org.w3c.dom.DocumentFragment;

import com.browser.helper.SmartNotes;
import com.browser.reader.FileReader;
import com.browser.speech.SpeechCommands;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class BrowserWindow extends Region{
	
	public WebView browser;
    public WebEngine webEngine;
    private History history;
    private final TextField locField = new TextField();    // the location the browser engine is currently pointing at (or where the user can type in where to go next).
    public static String DEFAULT_HOME = "http://www.soic.indiana.edu/graduate/courses/index.php"; 
    private String speechCommandSpoken;
    public FileReader titleReader;
    private SmartNotes smartNoteObj;
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

	public BrowserWindow(){
		System.out.println("Browser Window");
		history = new History(this);
    	browser = new WebView();
        webEngine = browser.getEngine();
        speechCommandSpoken = null;
        titleReader = new FileReader();
        initBrowser();
    }

	public BrowserWindow(String temp)
	{
		history = new History();
	}
	
	//initialized browser with default home
    private void initBrowser(){
    	
    	smartNoteObj =  new SmartNotes();
    	selectedText = new String();

        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load(DEFAULT_HOME);
        
        //add the web view to the scene
        getChildren().add(browser);
        
        
        
       // browser.onMouseClickedProperty().addListener(new temo);
        
        browser.setOnMouseClicked(new EventHandler<MouseEvent>(){
        	 
            @Override
            public void handle(MouseEvent arg0) {
               
                System.out.println("Some text is selected by mouse event ---------------------");
                //String selection = null;
    	    
    		    	 	 	selectedText= (String) webEngine
    		                 .executeScript("window.getSelection().toString()");
    		    	System.out.println("selected text is :  -----------------------------------------------   " + selectedText);
    		    	
    		    	webEngine.executeScript("var d = document.getElementsByTagName('h4'); for (var i = 0; i < d.length; i++) {d[i].style.backgroundColor = 'blue';};");
    	        	//BrowserWindow.webEngine.executeScript("var sel = window.getSelection();var range = sel.getRangeAt(0);var newTextNode = document.createTextNode('hello world!!!!');range.insertNode(newTextNode);range.setStartAfter(newTextNode);sel.removeAllRanges();sel.addRange(range);");
    		    	//BrowserWindow.webEngine.executeScript("if (document.selection) {var range = document.body.createTextRange();range.moveToElementText(document.getElementById('div'));range.select();} else if (window.getSelection()) {var range = document.createRange();range.selectNode(document.getElementById('div'));window.getSelection().removeAllRanges();window.getSelection().addRange(range);}");
    		    	//BrowserWindow.webEngine.executeScript("var range = document.body.createTextRange();range.moveToElementText(document.getElementById('post_message_4959224'));range.select();");
    		    	//  System.out.println("dom2===============================================================================" + dom2.toString());
    		    	if(!selectedText.equalsIgnoreCase(""))
    		    	{
    		    	/*try {
    					objTemp.ReadTemp(selectedText);
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}*/
//    		    	String s2 = BrowserWindow.webEngine.getTitle();
//    		    	System.out.println("title: " + s2);
    		    	DocumentFragment s1 = (DocumentFragment) webEngine
    		                 .executeScript("window.getSelection().getRangeAt().cloneContents()");
    		    	//BrowserWindow.webEngine.executeScript("if (window.getSelection) { var sel = window.getSelection();sel.removeAllRanges();var range = document.createRange();range.selectNodeContents(element);sel.addRange(range);} else if (document.selection) {var textRange = document.body.createTextRange();textRange.moveToElementText(element);textRange.select();}");
    		    	
    		    	System.out.println("text content : " + s1.getChildNodes().getLength());
    		    	for(int i = 0;i<s1.getChildNodes().getLength();i++)
    		    	{
    		    		System.out.println("Tag Name: "+s1.getChildNodes().item(i).getPrefix());
    		    		System.out.println("Text Content: "+s1.getChildNodes().item(i));
    		    	}
    		    	
    		    	//smartNoteObj.copySelectedText(selectedText);
//    		    	System.out.println("text content2 : " + s1.getChildNodes().item(0));
//    		    	System.out.println("text content : " + s1.getChildNodes().item(1));
    		    	}
    		    	else{
    		    		System.out.println("Selection else ---------------------------------");
    		    	}
            }
   
        });
        
     // monitor the web view for when it's location changes, so we can update the history lists and other items correctly.
        //final WebEngine engine = getView().getEngine();
        webEngine.locationProperty().addListener(new ChangeListener<String>() { 
          public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
            //getHistory().executeNav(newLoc); 
            /*System.out.println("items List:"+getHistory().getItems());// update the history lists.
            System.out.println("webengine history:"+webEngine.getHistory().getEntries());
            System.out.println("Current Location:"+(browser.getEngine().getLocation()));*/
            
            //history.getEntries().get(history.getCurrentIndex()).getUrl(
            //getLocField().setText(newLoc);   // update the location field.
           // favicon.set(favIconHandler.fetchFavIcon(newLoc));
          }
        });
        
        
        //not sure why this is required
        HBox.setHgrow(locField, Priority.ALWAYS);
        locField.setPromptText("Where do you want to go today?");
        locField.setTooltip(new Tooltip("Enter a location or find happiness."));
        locField.setOnKeyReleased(new EventHandler<KeyEvent>() {
          public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
              try {
				navTo(locField.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              //System.out.println("from browserWindow key released");
            }
          }
        });
      //not sure why this is required
        /*locField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean from, Boolean to) {
              if (to) {
                Platform.runLater(new Runnable() {
                  public void run() {
                    locField.selectAll();
                  }
                });
              }
            }
          });*/
                       
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
    
    public void navTo(String loc) throws IOException {
        // modify the request location, to make it easier on the user for typing.
    // todo we probably don't want this default nav for empty .... work out what to do instead ....
//        if (loc == null || loc.isEmpty()) { // go home if the location field is empty.
//          loc = chrome.homeLocationProperty.get();
//        }
    	//System.out.println(loc);
        if (loc == null) loc = "";
        if (loc.startsWith("google")) { // search google
          loc = "http://www.google.com/search?q=" + loc.substring("google".length()).trim().replaceAll(" ", "+");
        } /*else if (loc.startsWith("bing")) { // search bing
          loc = "http://www.bing.com/search?q=" + loc.substring("bing".length()).trim().replaceAll(" ", "+");
        } else if (loc.startsWith("yahoo")) { // search yahoo
          loc = "http://search.yahoo.com/search?p=" + loc.substring("yahoo".length()).trim().replaceAll(" ", "+");
        }*/ else if (loc.startsWith("wiki")) {
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
        	//System.out.println(getView().getEngine().getLocation());
          if (!loc.isEmpty()) {
        	  //System.out.println("location isn't empty!!: " + getView().getEngine());
        	  //System.out.println("new: " + loc);
        	  //System.out.println("Browser "+browser);
        	  browser.getEngine().load(loc);
        	
        	  //Read the loaded page....
        	  //titleReader.ReadTitle(loc);
        	  //System.out.println("webEngine history: " + browser.getEngine().getHistory().getEntries());
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
