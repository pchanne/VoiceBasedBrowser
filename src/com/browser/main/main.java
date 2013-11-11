package com.browser.main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;
import com.browser.view.MenuBarView;
import com.browser.view.SideBarView;

public class main extends Application{

    SideBarView sidebar;
    
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        VBox vbox= new VBox();

  
        //MenuBarView menuBar= new MenuBarView();
        sidebar= new SideBarView();
        
        //vbox.getChildren().add(menuBar.createMenuBar());

        
        BorderPane borderLayout= new BorderPane();
                       
        
        borderLayout.setTop(vbox);                        
        borderLayout.setLeft(sidebar.createSideBar());
        
        setBookmarkItems();        
        
        Scene scene = new Scene(borderLayout, 400, 400);
        
        stage.setScene(scene);
        stage.show();
                
    }
    
    public void setBookmarkItems()
    {
        Bookmark googleBookmark= new Bookmark();
        googleBookmark.setBookmarkName("google");
        googleBookmark.setBookmarkURL("www.google.co.in");
        
        Bookmark yahooBookmark= new Bookmark();
        yahooBookmark.setBookmarkName("yahoo");
        yahooBookmark.setBookmarkURL("www.yahoo.in");
        
        BookmarkModel bookmarkModel= new BookmarkModel();
        bookmarkModel.addBookmark(googleBookmark);
        bookmarkModel.addBookmark(yahooBookmark);
        
        ContextMenu bookmarkContextMenu= sidebar.getBookmarkButton().getContextMenu();       
               
        ArrayList<Bookmark> bookmarkList= bookmarkModel.getBookmarkList();
        
        for(final Bookmark bookmark: bookmarkList)
        {
            MenuItem bookmarkMenuItem= new MenuItem(bookmark.getBookmarkName());
            
            bookmarkMenuItem.setOnAction(new EventHandler<ActionEvent>(){

                public void handle(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                                  
                    // page should load url
                    System.out.println("Open Page: "+bookmark.getBookmarkURL());
                }
                
            });
            
            bookmarkContextMenu.getItems().add(bookmarkMenuItem);
            
        }
        
        sidebar.getBookmarkButton().setContextMenu(bookmarkContextMenu);
        
        
    }
    
}
