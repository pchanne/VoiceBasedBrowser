package com.browser.reader;

import java.io.IOException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import com.browser.controller.ViewController;
import com.browser.helper.SpeechHelper;
import com.browser.main.VoiceBrowser;
import com.browser.speech.SpeechCommands;
import com.browser.view.TabView;

public class SpeechReaderTask extends Service<Void>{
	String speechCommand = null; 
	String website;
	boolean ReadEnabled = true;
	
	public VoiceBrowser voiceBrowserObj;
	public SpeechReaderTask(){
	}
	@Override
	protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            
            protected Void call() throws Exception {
		    			System.out.println("Reading the selected text in the page ");			
		    			TabView.getCurrentViewController().getTextReader().ReadSelectedText(TabView.getCurrentViewController().getTagHandler().getTextFromCurrentHeaderinCurrentView());

                return null;
            }
        };
    }


}
