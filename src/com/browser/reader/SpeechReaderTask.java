/**
 * This class represents Reader task which starts on issue of Read command.
 */
package com.browser.reader;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import com.browser.view.TabView;

public class SpeechReaderTask extends Service<Void>{
	String speechCommand = null; 
	String website;
	boolean ReadEnabled = true;
	
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
