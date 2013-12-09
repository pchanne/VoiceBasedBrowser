package com.browser.view;

import java.io.IOException;

import com.browser.controller.BrowserWindow;
import com.browser.controller.TabViewController;
import com.browser.controller.ViewController;
import com.browser.helper.GetImagePath;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TabView extends Tab {

	private TabToolbarView tabToolbarViewObj;
	private ViewController viewController;
	private static ViewController currentViewController;
	
	//testing
	private TextField searchBar;
    private Button findTagButton;
    private Button selectButton;
    private Pane myTabToolBarPane;
    private BrowserWindow myBrowser;
    private static boolean speechMode = false;
    private Button speechButton;
    private ImageView speechGraphic;
    private ColorAdjust speechColorAdjust;
    private static GetImagePath getImgObj;
    
	public static ViewController getCurrentViewController() {
		return currentViewController;
	}

	public static void setCurrentViewController(ViewController currentViewController) {
		TabView.currentViewController = currentViewController;
	}

	public static boolean isSpeechMode() {
		return speechMode;
	}

	public static void setSpeechMode(boolean speechMode) {
		TabView.speechMode = speechMode;
	}

	public ViewController getViewController() {
		return viewController;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	public TabView() {
		//System.out.println(this);
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
		
		

		
		searchBar= new TextField();
		findTagButton = new Button("Find Tag");
		selectButton= new Button("Select");
		
		HBox selectTagLayout= new HBox();
		
		selectTagLayout.getChildren().addAll(searchBar, findTagButton, selectButton);
		HBox.setHgrow(searchBar, Priority.ALWAYS);
		
		final VBox sideBarContainer= new VBox();
		
		setOnSelectionChanged(new EventHandler<Event>(){
            @Override
            public void handle(Event arg0) {
            	System.out.println("changed");
                if(sideBarContainer.getChildren().contains(SideBarView.getBarDisplay()))
                {
                    sideBarContainer.getChildren().remove(SideBarView.getBarDisplay());
                }
                sideBarContainer.getChildren().add(SideBarView.getBarDisplay());
               
                //Changing speech icon on tab change based on global speechMode status
                getImgObj = new GetImagePath();
                String iconPath = null;
                //System.out.println("VC object: "+TabView.this.viewController);
                //System.out.println("Selected Tab: "+BrowserTabBarView.getBrowserTabHolder().getSelectionModel().getSelectedItem().isSelected());
                
                if(BrowserTabBarView.getBrowserTabHolder().getSelectionModel().getSelectedItem().isSelected())
                {
                	System.out.println("Selected Tab :"+TabView.this.viewController);
                	TabView.setCurrentViewController(TabView.this.viewController);
                	//TabViewController.sTask.setViewController(TabView.this.viewController);
                }
                
                if(TabView.speechMode){
                	iconPath = getImgObj.jarScan("icons.jar", "Micro-icon");
                	speechGraphic = new ImageView(new Image(iconPath));
            		((Button)viewController.getTabToolBar().getNavPane().getChildren().get(8)).setGraphic(speechGraphic);
            		//System.out.println("changing to mic on");
            		
                }else if(!TabView.speechMode){
                	iconPath = getImgObj.jarScan("icons.jar", "Micro-off-icon");
                	speechGraphic = new ImageView(new Image(iconPath));
                	((Button)viewController.getTabToolBar().getNavPane().getChildren().get(8)).setGraphic(speechGraphic);
                	//System.out.println("changing to mic off");
                }
                
            }
            
        });

		tabLayout.setLeft(sideBarContainer);
		setContent(tabLayout);
		
		
	}

}