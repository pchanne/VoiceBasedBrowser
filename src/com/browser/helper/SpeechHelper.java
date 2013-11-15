package com.browser.helper;

import java.io.IOException;

import javafx.application.Platform;

import org.w3c.dom.DocumentFragment;

import com.browser.main.BrowserWindow;
import com.browser.main.VoiceBrowser;
import com.browser.reader.FileReader;

public class SpeechHelper {

	private String website;
	
public void speechTest(final String Command, final VoiceBrowser voiceBrowserObj) {
		
		website = null;
		
		System.out.println("command received is:" + Command);
		
		Platform.runLater(new Runnable() {

		    @Override
		    public void run() {
//	    	String selection = null;; 
//	    	FileReader objTemp = new FileReader();
//		    	 	 	selection= (String) BrowserWindow.webEngine
//		                 .executeScript("window.getSelection().toString()");
//		    	System.out.println("selected text is :  -----------------------------------------------   " + selection);
//		    	if(selection != null)
//		    	{
//		    	try {
//					objTemp.ReadTemp(selection);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
////		    	String s2 = BrowserWindow.webEngine.getTitle();
////		    	System.out.println("title: " + s2);
//		    	DocumentFragment s1 = (DocumentFragment) BrowserWindow.webEngine
//		                 .executeScript("window.getSelection().getRangeAt().cloneContents()");
//		    	
//		    	System.out.println("text content : " + s1.getChildNodes().getLength());
//		    	for(int i = 0;i<s1.getChildNodes().getLength();i++)
//		    	{
//		    		System.out.println("Tag Name: "+s1.getChildNodes().item(i).getPrefix());
//		    		System.out.println("Text Content: "+s1.getChildNodes().item(i));
//		    	}
////		    	System.out.println("text content2 : " + s1.getChildNodes().item(0));
////		    	System.out.println("text content : " + s1.getChildNodes().item(1));
//		    	}
		    	
//		    	System.out.println("UI Thread:"+Thread.currentThread().getName());
//		    	System.out.println("inside run");
//		    	System.out.println("BWObject:"+BrowserWindow.webEngine);
		    	if(Command!=null){
		    	website = Command.replace(".dot", ".");
            	website = website.replace(" ", "");
            	System.out.println("Website said : "+website);
            	if(website!=null){
		    	if(website.equalsIgnoreCase("yahoo.com"))
		    	{
		    		System.out.println("Website identified and set as the addressbar field ");
		    		voiceBrowserObj.getAddressBarField().setText(website);
		    	}
		    	if(Command.equalsIgnoreCase("go")){
		    		try {
						voiceBrowserObj.getVoiceBrowser().navTo(voiceBrowserObj.getAddressBarField().getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
            	}
		    	}
		    	//BrowserWindow.webEngine.load("www.yahoo.com");
		    	//getAddressBarField().setText("www.yahoo.com");
		    	
		    	//ToolbarView.backButton.setDisable(true);
		    }
		});
		
		//ToolbarView.getAddBookmarkButton().fire();
		//ToolbarView.getBackButton().fire();
		//getVoiceBrowser().navTo(Command);
		
	}
}
