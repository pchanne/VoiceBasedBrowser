package com.browser.main;

import com.browser.view.MenuBarView;
import com.browser.view.ToolbarView;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
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
import javafx.event.EventHandler;


public class VoiceBrowser extends Application {

	private TextField addressBarField = new TextField(); // URL location
	private Scene scene;
	private BrowserWindow browserWindow;
	private BorderPane mainLayout = new BorderPane();        // layout of the browser application.
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	// TO DO: add excptions - malformed and unsupported
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
	
		stage.setTitle("Voice Based Browser");
		
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
		
		VBox vbox_for_menubar_toolbar= new VBox();
		vbox_for_menubar_toolbar.getChildren().addAll(menuBar.createMenuBar(),ToolbarView.CreateNavToolbar(this));
		
		mainLayout.setTop(vbox_for_menubar_toolbar);
		//mainLayout.setTop(ToolbarView.CreateNavToolbar(this));

		
		// add an overlay layer over the main layout for effects and status messages.
	    final AnchorPane overlayLayer = new AnchorPane();
	    final StackPane overlaidLayout = new StackPane();
	    overlaidLayout.getChildren().addAll(mainLayout, overlayLayer);
	    overlayLayer.setPickOnBounds(false);
		
	    //create scene from overlaidLayout
		scene = new Scene(overlaidLayout,1020,800);
		
		
        stage.setScene(scene);        
        stage.show();

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

}
