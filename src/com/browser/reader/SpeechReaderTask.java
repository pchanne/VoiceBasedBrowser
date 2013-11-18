package com.browser.reader;

import java.io.IOException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import com.browser.controller.ViewController;
import com.browser.helper.SpeechHelper;
import com.browser.main.VoiceBrowser;
import com.browser.speech.SpeechCommands;

public class SpeechReaderTask extends Service<Void>{
	String speechCommand = null; //= "Go";
	String website;
	boolean ReadEnabled = true;
	
	public VoiceBrowser voiceBrowserObj;
	private SpeechHelper speechHelper;
	private ViewController viewController;
	public SpeechReaderTask(ViewController viewController){
		this.viewController = viewController;
	}
	@Override
	protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            
            protected Void call() throws Exception {
                
                
                //while(ReadEnabled){
                	/*if(isCancelled())
                	{
                		break;
                	}*/
                	//System.out.println("Sleeping");
                	//speechCommand = "Go";
                	
                	if(viewController.getBrowserWindowView().getSelectedText().equalsIgnoreCase("") || viewController.getBrowserWindowView().getSelectedText()==null)
		    		{
		    			System.out.println("Reading the whole page ");
		    			try {
							viewController.getTextReader().ReadWholePage(viewController.getToolBar().getAddressBarField().getText());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    		else
		    		{
		    			System.out.println("Reading the selected text in the page ");
		    			try {
							viewController.getTextReader().ReadSelectedText(viewController.getBrowserWindowView().getSelectedText());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
                		
                		//vb.getVoiceBrowser().navTo("www.yahoo.com");
                		//System.out.println();
                		//System.out.println("eventdes: "+ToolbarView.testButton.getEventDispatcher().dispatchEvent(null, null));
                		//BrowserWindow.browser.getEngine().load("www.yahoo.com");
                	//}
                	//Thread.sleep(100);

                //}

                return null;
            }
        };
    }


}
