
package com.browser.view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.VBox;

public class SideBarView {

	private static VBox sideBar;
	private static TabPane tabs;
	private static TextArea textArea;
	private static TabPane sideBarTabPane;
	private static Tab smartNotesTab;
	private static Tab speechTab;

	
	public static void setTextAreaText(ArrayList<String> noteText)
	{
		for(int i = 0;i<noteText.size();i++)
		{
			textArea.setText(noteText.get(i)+"\n");
		}
		
	}
	
	//private static SideBarView instance=null;
	
	/*
	 * 
	 * this method would be called once, where sidebar will be created and this is stored
	 * in sidebar which later can be assigned to any javafx element  
	 * 
	 */
	public static void initSideBar()
	{
	    //this method will be called once
	    //where sidebar will be created and this stored in sidebar which later can
	    //be assigned to any javafx element
	    
	    sideBar = new VBox();
        sideBar.setStyle("-fx-background-color: WHITE; " +
        		"-fx-border-color: BLACK; -fx-border-width: 0 0.5 0 0;");
        // sideBar.setSpacing(1);
        sideBar.setPrefWidth(350.00);
        // sideBar.setPadding(new Insets(0, 20, 10, 20));
        TabPane tabs = new TabPane();
        TextArea textArea = TextAreaBuilder.create().prefWidth(400)
                .prefHeight(1600).wrapText(true).build();
        TabPane sideBarTabPane = new TabPane();
        Tab smartNotesTab = new Tab("Smart Notes");
        Tab speechTab = new Tab("Speech");
        
        speechTab.setClosable(false);
        smartNotesTab.setClosable(false);
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(textArea);
        sideBarTabPane.setPrefHeight(600.00);
        smartNotesTab.setContent(scrollpane);
        sideBarTabPane.getTabs().add(smartNotesTab);
        sideBarTabPane.getTabs().add(speechTab);
        sideBar.getChildren().add(sideBarTabPane);
	    
	}
	
	
	/*public SideBarView() {
		
		sideBar = new VBox();
		sideBar.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; -fx-border-width: 0 0.5 0 0;");
		// sideBar.setSpacing(1);
		sideBar.setPrefWidth(350.00);
		// sideBar.setPadding(new Insets(0, 20, 10, 20));
		tabs = new TabPane();
		textArea = TextAreaBuilder.create().prefWidth(400)
				.prefHeight(1600).wrapText(true).build();
		sideBarTabPane = new TabPane();
		smartNotesTab = new Tab("Smart Notes");
		speechTab = new Tab("Speech");
		createSideBar();
		
	}*/
	
	
	public static VBox getBarDisplay() {
		return sideBar;
	}

	public static Node createSideBar() {
		/*TabPane tabs = new TabPane();
		TextArea textArea = TextAreaBuilder.create().prefWidth(400)
				.prefHeight(1600).wrapText(true).build();
		TabPane sideBarTabPane = new TabPane();
		Tab smartNotesTab = new Tab("Smart Notes");
		Tab speechTab = new Tab("Speech");*/
		speechTab.setClosable(false);
		smartNotesTab.setClosable(false);
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setContent(textArea);
		sideBarTabPane.setPrefHeight(600.00);
		smartNotesTab.setContent(scrollpane);
		sideBarTabPane.getTabs().add(smartNotesTab);
		sideBarTabPane.getTabs().add(speechTab);
		sideBar.getChildren().add(sideBarTabPane);

		return sideBar;
	}

	public static Node getSideBar() {
		System.out.println("sidebar contents: " + sideBar.getChildren().get(0));
		System.out.println("sidebar contents2: " + sideBar.toString());
		return sideBar;
	}

	public static void setSideBar(VBox sideBar) {
		SideBarView.sideBar = sideBar;
	}

}