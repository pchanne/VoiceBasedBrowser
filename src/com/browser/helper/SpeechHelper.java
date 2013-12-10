package com.browser.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import org.w3c.dom.DocumentFragment;

import com.browser.command.AddBookMarkCommand;
import com.browser.command.BackCommand;
import com.browser.command.BookMarkCommand;
import com.browser.command.ForwardCommand;
import com.browser.command.GoCommand;
import com.browser.command.HomeCommand;
import com.browser.command.Invoker;
import com.browser.command.ReadCommand;
import com.browser.command.RefreshCommand;
import com.browser.command.ScrollDownCommand;
import com.browser.command.ScrollUpCommand;
import com.browser.controller.BrowserWindow;
import com.browser.controller.TabViewController;
import com.browser.controller.ViewController;
import com.browser.main.VoiceBrowser;
import com.browser.reader.FileReader;
import com.browser.reader.SpeechReaderTask;
import com.browser.view.SideBarView;
import com.browser.view.TabView;

public class SpeechHelper {

	private String website;
	private ViewController viewController;
	public static SpeechReaderTask speechReaderTask;
	public static int readCounter;
	public static int viewCounter;
	private double currentX;
	private double currentY;
	private double currentWidth;
	private double currentHeight;
	private boolean bookmarkFlag;
	private Invoker invokerObj;
	
	public SpeechHelper(){
		readCounter = 0;
		viewCounter = 0;
		currentX =0;
		currentY = 0;
		speechReaderTask = new SpeechReaderTask();
		website = null;
		bookmarkFlag = false;
		invokerObj = new Invoker();
	}
	
	public void speechTest(final String Command) {
		
		System.out.println("command received is:" + Command);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				SpeechHelper.this.viewController = TabView.getCurrentViewController();
								if (Command != null) {
					website = Command.replace(".dot", ".");
					website = website.replace(" ", "");
					System.out.println("Website said : " + website);
					if (website != null) {
						if (website.equalsIgnoreCase("yahoo.com")) {
							System.out
									.println("Website identified and set as the addressbar field ");
							viewController.getTabToolBar()
									.getAddressBarField().setText(website);
						}
						if (website.equalsIgnoreCase("bing.com")) {
							viewController.getTabToolBar().getAddressBarField().setText(website);
						}
						if(website.equalsIgnoreCase("cnn.com")){
							viewController.getTabToolBar().getAddressBarField().setText(website);
						}
						if (Command.equalsIgnoreCase("go")) {
							invokerObj.setCommand(new GoCommand());
							
						}
						if (Command.equalsIgnoreCase("back")) {
							invokerObj.setCommand(new BackCommand());
							
						}
						if (Command.equalsIgnoreCase("forward")) {
							invokerObj.setCommand(new ForwardCommand());
						}
						if (Command.equalsIgnoreCase("refresh")) {
							invokerObj.setCommand(new RefreshCommand());
						}
						if (Command.equalsIgnoreCase("home")) {
							invokerObj.setCommand(new HomeCommand());
						}
						if (Command.equalsIgnoreCase("book mark")) {
							bookmarkFlag = true;
							invokerObj.setCommand(new BookMarkCommand());
							
						}
						if (Command.equalsIgnoreCase("Add") && bookmarkFlag) {
							invokerObj.setCommand(new AddBookMarkCommand());
							bookmarkFlag = false;
						}
						if (Command.equalsIgnoreCase("read")) {
							invokerObj.setCommand(new ReadCommand());
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
						if (Command.equalsIgnoreCase("top")){
							if(viewCounter == 0) {
								currentWidth = viewController.getBrowserWindowView().browser.getWidth();
								currentHeight = viewController.getBrowserWindowView().browser.getHeight();
								viewCounter ++;
							}
							viewController.getTagHandler().initialiseViews(currentX, currentY, currentWidth, currentHeight);
							//viewController.getTagHandler().highlightAllTextinTopView();
						
							viewController.getTagHandler().highlightAllLinksinTopView();
							currentHeight = currentHeight/2;
						}
						if (Command.equalsIgnoreCase("bottom")){
							if(viewCounter == 0){
								currentWidth = viewController.getBrowserWindowView().browser.getWidth();
								currentHeight = viewController.getBrowserWindowView().browser.getHeight();
								viewCounter ++;
							}
							currentY = currentY + currentHeight/2;
							viewController.getTagHandler().initialiseViews(currentX, currentY, currentWidth, currentHeight);
							//viewController.getTagHandler().highlightAllHeadersinBottomView();
							viewController.getTagHandler().highlightAllLinksinBottomView();
							currentHeight = currentHeight/2;
						}
//						if (Command.equalsIgnoreCase("Titles")) {
//							viewController.getTagHandler().clearSelectedTags();
//							viewController.getTagHandler()
//									.selectAllHeaderTags();
//						}
//						if (Command.equalsIgnoreCase("Next Title")) {
//							viewController.getTagHandler().clearSelectedTags();
//							viewController.getTagHandler().selectNextHeader();
//						}
//						if (Command.equalsIgnoreCase("Links")) {
//							viewController.getTagHandler().clearSelectedTags();
//							viewController.getTagHandler().selectAllLinkTags();
//							;
//						}
//						if (Command.equalsIgnoreCase("Next Link")) {
//							viewController.getTagHandler().clearSelectedTags();
//							viewController.getTagHandler()
//									.selectNextLinkHeader();
//						}
//						if (Command.equalsIgnoreCase("navigate")) {
//							viewController.getTagHandler().navigateurl();
//						}
						if (Command.equalsIgnoreCase("scroll down")) {
							invokerObj.setCommand(new ScrollDownCommand());
						}
						if (Command.equalsIgnoreCase("scroll up")) {
							invokerObj.setCommand(new ScrollUpCommand());
						}
						if(invokerObj.getCommand()!=null)
							invokerObj.invoke();
					}
				}
			}
		});

	}
}
