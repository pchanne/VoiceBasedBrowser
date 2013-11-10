package com.browser.view;

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
    
    public MenuBarView()
    {
        fileMenu = new Menu("File");
        viewMenu = new Menu("View");
        helpMenu = new Menu("Help");
        speechMenu= new Menu("Speech");
        
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
    }
      
    private void setHelpMenuItems()
    {
        userManualMenuItem= new MenuItem("Help");
        aboutMenuItem= new MenuItem("About");
        
        helpMenu.getItems().addAll(userManualMenuItem, aboutMenuItem);
    }
    
}
