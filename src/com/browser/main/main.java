package com.browser.main;

import com.browser.view.MenuBarView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        VBox vbox= new VBox();
        MenuBarView menuBar= new MenuBarView();
        vbox.getChildren().add(menuBar.createMenuBar());
        
        Scene scene = new Scene(vbox, 400, 400);
        
        stage.setScene(scene);
        stage.show();
                
    }
}
