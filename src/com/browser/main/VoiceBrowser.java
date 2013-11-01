package com.browser.main;

import com.browser.view.ToolbarView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;


public class VoiceBrowser extends Application {

	private TextField addressBarField = new TextField(); // URL location
	private Scene scene;
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
		addressBarField.setStyle("-fx-font-size: 14;");
		addressBarField.setPromptText("Where do you want to go today?");
		addressBarField.setTooltip(new Tooltip("Enter a location"));
		
		/*addressBarField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		      @Override public void handle(KeyEvent keyEvent) {
		        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
		          getBrowser().navTo(addressBarField.getText());
		        }
		      }
		    });*/
		
		HBox.setHgrow(addressBarField, Priority.ALWAYS);
		mainLayout.setTop(ToolbarView.CreateNavToolbar(this));
		
		
		final StackPane overlaidLayout = new StackPane();
		scene = new Scene(new BrowserWindow(),750,500, Color.web("#666970"));
		//scene = new Scene(overlaidLayout,1020,600);
		
        stage.setScene(scene);
        //scene.getStylesheets().add("webviewsample/BrowserToolbar.css");        
        stage.show();

	}

}
