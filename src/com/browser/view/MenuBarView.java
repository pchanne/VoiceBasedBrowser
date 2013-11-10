package com.browser.view;

import java.awt.event.ActionListener;

import javax.swing.Timer;


//import java.util.Timer;
import java.util.TimerTask;

import quicktime.std.music.TunePlayer;

import com.browser.main.BrowserWindow;
import com.browser.main.VoiceBrowser;
import com.browser.speech.SpeechCommands;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarView {

    private Menu fileMenu;
    private MenuItem saveMenuItem;
    private MenuItem loadMenuItem;
    private MenuItem newTabMenuItem;
    private MenuItem exitMenuItem;
    
    private Menu viewMenu;
    private MenuItem showStatusBarMenuItem;
    private MenuItem showBookmarkMenuItem;
    private MenuItem showHistoryMenuItem;
    
    private Menu helpMenu;
    private MenuItem userManualMenuItem;
    private MenuItem aboutMenuItem;
    
    private Menu speechMenu;
    private CheckMenuItem speechModeEnable;
    private String speechCommandSpoken;
    private Timer timer;
    private Thread t1;
    //private Timer timer;
    
    public MenuBarView(final BrowserWindow browserObj, final VoiceBrowser voiceBrowserObj)
    {
        fileMenu = new Menu("File");
        viewMenu = new Menu("View");
        helpMenu = new Menu("Help");
        speechMenu= new Menu("Speech");
        speechCommandSpoken = null;
        
//        //timer.schedule(new TimerTask() {
//			t1 = new Thread(){
//			@Override
//			public void run() {
//				Platform.runLater(new Runnable() {
//					
//					public void run() {
//						// TODO Auto-generated method stub
//						// TODO Auto-generated method stub
//						speechCommandSpoken =  SpeechCommands.CommandSpoken();
//						System.out.println("Command spoken " +speechCommandSpoken);
//						if(speechCommandSpoken.equalsIgnoreCase("Go"))
//						{
//							System.out.println("Command!!!");
//			          	//browserWindow = new BrowserWindow();
//							voiceBrowserObj.getVoiceBrowser().navTo(voiceBrowserObj.getAddressBarField().getText());
//							//voiceBrowserObj.getAddressBarField().setText(speechCommandSpoken);
//						}
//					}
//				});	
//			}
//		};
        
        timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Browser outside runnable "+browserObj);
				Platform.runLater(new Runnable() {
					public void run() {
						// TODO Auto-generated method stub
						speechCommandSpoken =  SpeechCommands.CommandSpoken();
						System.out.println("Command spoken " +speechCommandSpoken);
						if(speechCommandSpoken.equalsIgnoreCase("Go"))
						{
							System.out.println("Command!!!");

							System.out.println("Browser inside runnable "+browserObj);
			          	//browserWindow = new BrowserWindow();
							//voiceBrowserObj.getVoiceBrowser().navTo(voiceBrowserObj.getAddressBarField().getText());
							//voiceBrowserObj.getAddressBarField().setText("http://www.yahoo.com");
							//browserObj.navTo(voiceBrowserObj.getAddressBarField().getText());
							//browserObj.navTo("www.yahoo.com");
							//browserObj.getLocField().setText(speechCommandSpoken);
							//voiceBrowserObj.getAddressBarField().setText(speechCommandSpoken);
							BrowserWindow.browser.getEngine().load("www.yahoo.com");
							System.out.println("Successfully loaded !!!");
							//voiceBrowserObj.getAddressBarField().setText(speechCommandSpoken);
						}
					}
				});
				
				
			}
		});
    }
    
    
    public Node createMenuBar()
    {     
       
        MenuBar menuBar= new MenuBar();
        
        setfileMenuItems();
        setViewMenuItems();
        setSpeechMenuItems();
        setHelpMenuItems();
        
        menuBar.getMenus().addAll(fileMenu, viewMenu, speechMenu,helpMenu);        
        
        return menuBar;
    }
    
    private void setfileMenuItems()
    {
        saveMenuItem= new MenuItem("Save");
        loadMenuItem= new MenuItem("Load");
        exitMenuItem= new MenuItem("Exit");
        
        fileMenu.getItems().addAll(saveMenuItem, loadMenuItem, exitMenuItem);
    }
    
    private void setViewMenuItems()
    {
        showStatusBarMenuItem= new MenuItem("Show Status Bar");
        showBookmarkMenuItem= new MenuItem("BookMarks");
        showHistoryMenuItem = new MenuItem("History");
        
        viewMenu.getItems().addAll(showStatusBarMenuItem, showBookmarkMenuItem, showHistoryMenuItem);
    }
    
    private void setSpeechMenuItems()
    {
        speechModeEnable= new CheckMenuItem("Enable/Disable Speech Mode");
        
        speechMenu.getItems().addAll(speechModeEnable);
        speechMenu.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				if(speechModeEnable.isSelected()){
				// TODO Auto-generated method stub
					System.out.println("Enabled Speech!!!");
					timer.start();
					//t1.run();
				}
				else
					//t1.stop();
					timer.stop();
			}
		});
    }
      
    private void setHelpMenuItems()
    {
        userManualMenuItem= new MenuItem("Help");
        aboutMenuItem= new MenuItem("About");
        
        helpMenu.getItems().addAll(userManualMenuItem, aboutMenuItem);
    }
    
}
