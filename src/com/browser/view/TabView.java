package com.browser.view;

import java.io.IOException;

import com.browser.controller.BrowserWindow;
import com.browser.controller.ViewController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TabView extends Tab {

	private TabToolbarView tabToolbarViewObj;
	private ViewController viewController;
	
	//testing
	private TextField searchBar;
    private Button findTagButton;
    private Button selectButton;
    private Pane myTabToolBarPane;
    private BrowserWindow myBrowser;

	public ViewController getViewController() {
		return viewController;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	public TabView() {
		
		viewController = new ViewController();
		myTabToolBarPane = viewController.getTabToolBar().CreateNavToolbar();
		
		final BorderPane tabLayout = new BorderPane();
		
		tabLayout.setTop(myTabToolBarPane);
		tabLayout.setCenter(viewController.getBrowserWindowView());
		viewController.reflectURLChange();

		viewController.getBrowserWindowView().getView().getEngine().titleProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observableValue,
							String oldValue, String newTitle) {
						if (newTitle != null && !"".equals(newTitle)) {
							setText(newTitle);
						}
					}
				});
		
		

		/*viewController.getBrowserWindowView().getView().getEngine().getLoadWorker().stateProperty()
				.addListener(new ChangeListener<State>() {
					public void changed(ObservableValue ov, State oldState,
							State newState) {
						if (newState == State.RUNNING) {

							tabToolbarViewObj.getAddressBarField().setText(
									viewController.getBrowserWindowView().getView().getEngine()
											.getLocation());
						}

						if (newState == State.SUCCEEDED) {
							System.out.println("in tab: " + getText());
							System.out.println("Page "
									+ viewController.getBrowserWindowView().getView().getEngine()
											.getLocation() + " loaded");
						}

					}
				});
*/
		/*tabLayout.getChildren().addAll(tabToolbarViewObj.CreateNavToolbar(),
				browserWindow);*/
		
		searchBar= new TextField();
		findTagButton = new Button("Find Tag");
		selectButton= new Button("Select");
		
		HBox selectTagLayout= new HBox();
		
		selectTagLayout.getChildren().addAll(searchBar, findTagButton, selectButton);
		HBox.setHgrow(searchBar, Priority.ALWAYS);
		
		//VBox testLayout= new VBox();
		
		//testLayout.getChildren().addAll(myTabToolBarPane,selectTagLayout);
		//tabLayout.setTop(testLayout);
		
		//tabLayout.setTop(tabToolbarViewObj.CreateNavToolbar());
		//tabLayout.setCenter(viewController.getBrowserWindowView());
		
		//tabLayout.setLeft(sideBarViewObj.getInstance().getSideBar());
/*
		viewController.getTabToolBar().getNavButton().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				try {
					browserWindow.navTo(viewController.getTabToolBar().getAddressBarField().getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
		
		final VBox sideBarContainer= new VBox();
		
		setOnSelectionChanged(new EventHandler<Event>(){
            @Override
            public void handle(Event arg0) {
                if(sideBarContainer.getChildren().contains(SideBarView.getBarDisplay()))
                {
                    sideBarContainer.getChildren().remove(SideBarView.getBarDisplay());
                }
                sideBarContainer.getChildren().add(SideBarView.getBarDisplay());
                System.out.println(SideBarView.getBarDisplay().toString());
            }
            
        });

		tabLayout.setLeft(sideBarContainer);
		setContent(tabLayout);
	}

}