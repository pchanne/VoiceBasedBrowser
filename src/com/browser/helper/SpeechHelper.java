package com.browser.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import org.w3c.dom.DocumentFragment;

import com.browser.controller.BrowserWindow;
import com.browser.controller.ViewController;
import com.browser.main.VoiceBrowser;
import com.browser.reader.FileReader;
import com.browser.reader.SpeechReaderTask;
import com.browser.view.SideBarView;

public class SpeechHelper {

	private String website;
	private ViewController viewController;
	private SpeechReaderTask speechReaderTask;
	private int ReadCounter;
	
	
	public SpeechHelper(ViewController viewController){
		ReadCounter = 0;
		this.viewController = viewController;
		speechReaderTask = new SpeechReaderTask(viewController);
		website = null;
	}
	
	public void speechTest(final String Command) {
		
		System.out.println("command received is:" + Command);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
								if (Command != null) {
					website = Command.replace(".dot", ".");
					website = website.replace(" ", "");
					System.out.println("Website said : " + website);
					if (website != null) {
						if (website.equalsIgnoreCase("yahoo.com")) {
							System.out
									.println("Website identified and set as the addressbar field ");
							viewController.getToolBar()
									.getAddressBarField().setText(website);
						}
						if (website.equalsIgnoreCase("bing.com")) {
							viewController.getToolBar().getAddressBarField().setText(website);
						}
						if(website.equalsIgnoreCase("cnn.com")){
							viewController.getToolBar().getAddressBarField().setText(website);
						}
						if (Command.equalsIgnoreCase("go")) {
							try {
								viewController.getBrowserWindowView().navTo(viewController.getToolBar().getAddressBarField().getText());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (Command.equalsIgnoreCase("back")) {
							
							viewController.getToolBar().getBackButton().fire();
						}
						if (Command.equalsIgnoreCase("forward")) {
							viewController.getToolBar().getForwardButton().fire();
						}
						if (Command.equalsIgnoreCase("refresh")) {
							try {
								viewController.getBrowserWindowView().navTo(viewController.getToolBar()
										.getAddressBarField().getText());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
//						if (Command.equalsIgnoreCase("exit")) {
//							System.exit(0);
//						}
						if (Command.equalsIgnoreCase("read")) {
						if(ReadCounter == 0)	
							{	
								ReadCounter++;
								speechReaderTask.start();
							}
						else{
							System.out.println("restarted");
								speechReaderTask.restart();
						}
							
						}
						if(Command.equalsIgnoreCase("smart notes")&& !(viewController.getBrowserWindowView().getSelectedText().equalsIgnoreCase(""))){
							
							viewController.getSmartNoteObj().copySelectedText(viewController.getBrowserWindowView().getSelectedText());
						}
						if(Command.equalsIgnoreCase("save") && !(SideBarView.getTextArea().getText().equalsIgnoreCase(""))){
							System.out.println("Saving smart notes: ");
							ObservableList<CharSequence> paragraph = SideBarView.getTextArea().getParagraphs();
						    Iterator<CharSequence>  iter = paragraph.iterator();
						    try
						    {
						        BufferedWriter bf = new BufferedWriter(new FileWriter(new File("smartnotes.txt")));
						        while(iter.hasNext())
						        {
						            CharSequence seq = iter.next();
						            bf.append(seq);
						            bf.newLine();
						        }
						        bf.flush();
						        bf.close();
						    }
						    catch (IOException e)
						    {
						        e.printStackTrace();
						    }
						}
					}
				}
			}
		});

	}
}
