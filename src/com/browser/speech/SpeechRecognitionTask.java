package com.browser.speech;

import com.browser.main.BrowserWindow;
import com.browser.main.VoiceBrowser;
import com.browser.view.ToolbarView;
import com.sun.glass.ui.Launchable;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class SpeechRecognitionTask extends Service<Void>{

	String speechCommand = "Go";
	boolean SpeechEnabled = true;
	public BrowserWindow bw;
	public VoiceBrowser vb;
	public SpeechRecognitionTask(BrowserWindow bw, VoiceBrowser vb){
		this.bw = bw;
		this.vb = vb;
	}
	@Override
	protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                
                
                while(SpeechEnabled){
                	//System.out.println("Sleeping");
                	speechCommand = "Go";
                	
                	//vb.getAddressBarField().setText(speechCommand);
                	vb.getAddressBarField().setText("Go");
                	Thread.sleep(1000);
                	if(speechCommand == "Go"){
                		//bw.navTo("www.yahoo.com");
                		//System.out.println("inside if");
                		//System.out.println("Browser View:"+BrowserWindow.browser.getEngine().getLocation());
                		//System.out.printlns("vb: "+vb.toString());
                		//speechCommand =  SpeechCommands.CommandSpoken();
						//System.out.println("Command spoken " +speechCommand);
                		System.out.println("Speech Thread:"+Thread.currentThread().getName());
                		vb.speechTest(speechCommand);
                		
                		//vb.getVoiceBrowser().navTo("www.yahoo.com");
                		//System.out.println();
                		//System.out.println("eventdes: "+ToolbarView.testButton.getEventDispatcher().dispatchEvent(null, null));
                		//BrowserWindow.browser.getEngine().load("www.yahoo.com");
                	}
                	Thread.sleep(10000);

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
