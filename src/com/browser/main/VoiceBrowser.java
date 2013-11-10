package com.browser.main;

import java.util.ArrayList;

import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;
import com.browser.view.MenuBarView;
import com.browser.view.SideBarView;
import com.browser.view.ToolbarView;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class VoiceBrowser extends Application {
    
    SideBarView sideBar;

	private TextField addressBarField = new TextField(); // URL location
	private Scene scene;
	private BrowserWindow browserWindow;
	private BorderPane mainLayout = new BorderPane(); 
	BookmarkModel bookmarkModel;
	
	// layout of the browser application.
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

	// TO DO: add excptions - malformed and unsupported
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
	
		stage.setTitle("Voice Based Browser");
		
		bookmarkModel= new BookmarkModel();
		
		browserWindow = new BrowserWindow();
		setAddressbarField(browserWindow.DEFAULT_HOME);
		addressBarField.setStyle("-fx-font-size: 14;");
		addressBarField.setPromptText("Where do you want to go today?");
		addressBarField.setTooltip(new Tooltip("Enter a location"));				
		
		addressBarField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		      public void handle(KeyEvent keyEvent) {
		        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
		        	getVoiceBrowser().navTo(addressBarField.getText());
		          //System.out.println("in actionlistener");
		        }
		      }
		    });
		

		browserWindow.getView().getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.RUNNING) {
                    addressBarField.setText(browserWindow.getView().getEngine().getLocation());                    
                }
            }
            });
		
		HBox.setHgrow(addressBarField, Priority.ALWAYS);
		
				
		//set up main Layout
		mainLayout.setTop(ToolbarView.CreateNavToolbar(this));
		mainLayout.setCenter(getVoiceBrowser());

		MenuBarView menuBar= new MenuBarView();
		sideBar= new SideBarView();
		
		VBox vbox_for_menubar_toolbar= new VBox();
		vbox_for_menubar_toolbar.getChildren().addAll(menuBar.createMenuBar(),ToolbarView.CreateNavToolbar(this));
		
		mainLayout.setTop(vbox_for_menubar_toolbar);
		mainLayout.setLeft(sideBar.createSideBar());
		
		setBookmarkItems();
				
		//mainLayout.setTop(ToolbarView.CreateNavToolbar(this));

		
		// add an overlay layer over the main layout for effects and status messages.
	    final AnchorPane overlayLayer = new AnchorPane();
	    final StackPane overlaidLayout = new StackPane();
	    overlaidLayout.getChildren().addAll(mainLayout, overlayLayer);
	    overlayLayer.setPickOnBounds(false);
		
	    //create scene from overlaidLayout
		scene = new Scene(overlaidLayout,1020,650);
		
		ToolbarView.getAddBookmarkButton().setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //get link in address bar and set it to url label of bookmark pop window
                
                ToolbarView.getBookmarkStage().show();
                
                
                //System.out.println(browserWindow.getView().getEngine().getTitle());
                
                ToolbarView.setBookmarkTitleText(browserWindow.getView().getEngine().getTitle());
                ToolbarView.setBookmarkUrlText(addressBarField.getText());
                
            }
            
        });
		
		ToolbarView.getAddBookmarkToModelButton().setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //add book mark to the model
                Bookmark bookmark= new Bookmark();
                bookmark.setBookmarkName(ToolbarView.getBookmarkTitle());
                bookmark.setBookmarkURL(ToolbarView.getBookmarkURL());
                
                bookmarkModel.addBookmark(bookmark);
                setBookmarkItems();
                ToolbarView.getBookmarkStage().close();
                
            }
            
        });
		
		
        stage.setScene(scene);        
        stage.show();

	}
	
	public void setBookmarkItems()
    {
        /*Bookmark googleBookmark= new Bookmark();
        googleBookmark.setBookmarkName("google");
        googleBookmark.setBookmarkURL("www.google.co.in");
        
        Bookmark yahooBookmark= new Bookmark();
        yahooBookmark.setBookmarkName("yahoo");
        yahooBookmark.setBookmarkURL("www.yahoo.in");
        
        
        bookmarkModel.addBookmark(googleBookmark);
        bookmarkModel.addBookmark(yahooBookmark);*/
        
        ContextMenu bookmarkContextMenu= sideBar.getBookmarkButton().getContextMenu(); 
        bookmarkContextMenu.getItems().removeAll(bookmarkContextMenu.getItems()); 
               
        ArrayList<Bookmark> bookmarkList= bookmarkModel.getBookmarkList();
        
        for(final Bookmark bookmark: bookmarkList)
        {
            MenuItem bookmarkMenuItem= new MenuItem(bookmark.getBookmarkName());
            
            bookmarkMenuItem.setOnAction(new EventHandler<ActionEvent>(){

                public void handle(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                                  
                    // page should load url
                    
                    getVoiceBrowser().navTo(bookmark.getBookmarkURL());
                    
                }
                
            });
            
            bookmarkContextMenu.getItems().add(bookmarkMenuItem);
            
        }
        
        sideBar.getBookmarkButton().setContextMenu(bookmarkContextMenu);
        
        
    }
	
	public TextField getAddressBarField(){
		return addressBarField;
	}
	
	public BrowserWindow getVoiceBrowser(){
		return browserWindow.getBrowser();
	}
	
	private void setAddressbarField(String loc){
    	addressBarField.setText(loc);
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

}
