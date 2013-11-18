package com.browser.view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class SideBarView {

    private VBox sideBar;   
    
    private Button bookmarkButton;
    ArrayList<MenuItem> bookmarkMenuItemList;
    private Button historyButton;
    
    
    
    public SideBarView()
    {
        sideBar= new VBox();
        
        bookmarkButton= new Button("Bookmark");
        bookmarkMenuItemList= new ArrayList<MenuItem>();
        
        historyButton= new Button("History");
    }
    
    public Node createSideBar()
    {
                                
        sideBar.getChildren().addAll(bookmarkButton,historyButton);
        
        final ContextMenu bookmarkContextMenu = new ContextMenu();                          
        
        bookmarkButton.setContextMenu(bookmarkContextMenu);        
        bookmarkButton.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                              
                bookmarkContextMenu.show(bookmarkButton, Side.RIGHT, 0, 0);
            }
            
        });
                      
        
        
        return sideBar;
    }

    /**
     * @return the bookmarkMenuItemList
     */
    public ArrayList<MenuItem> getBookmarkMenuItemList() {
        return bookmarkMenuItemList;
    }

    /**
     * @param bookmarkMenuItemList the bookmarkMenuItemList to set
     */
    public void setBookmarkMenuItemList(ArrayList<MenuItem> bookmarkMenuItemList) {
        this.bookmarkMenuItemList = bookmarkMenuItemList;
    }

    /**
     * @return the bookmarkButton
     */
    public Button getBookmarkButton() {
        return bookmarkButton;
    }

    /**
     * @param bookmarkButton the bookmarkButton to set
     */
    public void setBookmarkButton(Button bookmarkButton) {
        this.bookmarkButton = bookmarkButton;
    }
    
    
    
    
}
