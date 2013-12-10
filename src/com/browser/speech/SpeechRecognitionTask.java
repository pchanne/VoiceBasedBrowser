package com.browser.speech;

import com.browser.controller.BrowserWindow;
import com.browser.controller.ViewController;
import com.browser.helper.SpeechHelper;
import com.browser.main.VoiceBrowser;
import com.browser.view.TabView;
import com.sun.glass.ui.Launchable;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class SpeechRecognitionTask extends Service<Void>{

	String speechCommand = null; 
	String website;
	boolean SpeechEnabled = true;
	private SpeechHelper speechHelper;
	
	public SpeechRecognitionTask(){
		//this.viewController = TabView.getCurrentViewController();
		speechHelper = new SpeechHelper();
	}
	@Override
	protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                
                
                while(SpeechEnabled){
                	if(isCancelled())
                	{
                		break;
                	}
                	//System.out.println("Sleeping");
                	//speechCommand = "Go";
                	speechCommand =  SpeechCommands.CommandSpoken();
                	//vb.getAddressBarField().setText(speechCommand);
                	//vb.getAddressBarField().setText(speechCommand);
                	//Thread.sleep(60);
                	System.out.println("Website said :"+speechCommand);
                	
                	//if(speechCommand != null){
                		//bw.navTo("www.yahoo.com");
                		//System.out.println("inside if");
                		//System.out.println("Browser View:"+BrowserWindow.browser.getEngine().getLocation());
                		//System.out.printlns("vb: "+vb.toString());
                		//speechCommand =  SpeechCommands.CommandSpoken();
						//System.out.println("Command spoken " +speechCommand);
                		System.out.println("Speech Thread:"+Thread.currentThread().getName());
                		speechHelper.speechTest(speechCommand);
                		
                		//vb.getVoiceBrowser().navTo("www.yahoo.com");
                		//System.out.println();
                		//System.out.println("eventdes: "+ToolbarView.testButton.getEventDispatcher().dispatchEvent(null, null));
                		//BrowserWindow.browser.getEngine().load("www.yahoo.com");
                	//}
                	Thread.sleep(100);

                }

                return null;
            }
        };
    }

	/*@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	Task<String> commandTask = new Task<String>(){

		@Override
		protected String call() throws Exception {
			// TODO Auto-generated method stub
			
			System.out.println("Background task started");
			
			return "Go";
		}
		
	};
		Thread th = new Thread(commandTask);
		th.setDaemon(true);
		System.out.println("Starting Background task");
		th.start();
	}
	
	public static void main(String[] args){
		
		launch(args);
		
	}*/
	

}
