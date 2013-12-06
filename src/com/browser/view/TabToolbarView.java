package com.browser.view;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import com.browser.helper.GetImagePath;
import com.browser.main.VoiceBrowser;
import com.browser.speech.SpeechRecognitionTask;

public class TabToolbarView {
	
    private  Button backButton;
    private ImageView backGraphic;
	private ColorAdjust backColorAdjust;
	
    private  MenuButton menuButton;
    private static MenuItem saveMenuItem;
	private static MenuItem loadMenuItem;
	private MenuItem newTabMenuItem;
	private static MenuItem exitMenuItem;
	private static MenuItem showStatusBarMenuItem;
	private static Menu showBookmarkMenuItem;
	private static MenuItem showHistoryMenuItem;
	private static MenuItem userManualMenuItem;
	private static MenuItem aboutMenuItem;
	private static ImageView menuGraphic;
	private static ColorAdjust menuColorAdjust;
    
    private Button homeButton;
    private ImageView homeGraphic;
	private ColorAdjust homeColorAdjust;
    
    private Button refreshButton;
    private ImageView refreshGraphic;
	private ColorAdjust refreshColorAdjust;
    
    private Button forwardButton;
    private ImageView forwardGraphic;
	private ColorAdjust forwardColorAdjust;
	
    private TextField addressBarField;
    
    public Button navButton;
    private ImageView navGraphic;
	private ColorAdjust navColorAdjust;
	
    private static GetImagePath getImgObj;
    private static String iconPath;
    
    private Button addTabButton;
    private ImageView addTabGraphic;
	private ColorAdjust addTabColorAdjust;
	
	private static Button sidebarButton;
	private static ImageView sidebarGraphic;
	private static ColorAdjust sidebarColorAdjust;
	private static SideBarView sidebarView;
	
	private Button speechButton;
	private ColorAdjust speechColorAdjust;
	private ImageView speechGraphic;
	private int counter;
	private static boolean isSpeechMode;
	
	private Button addBookmarkButton;
	private Button addBookmarkToModelButton;
	private TextField bookmarkURLTextField;
	private Stage bookmarkStage;
	private TextField bookmarkTitleTextField;
	private ImageView bookmarkGraphic;
	private ColorAdjust bookmarkColorAdjust;
	
	public static String DEFAULT_HOME = "http://www.google.com"; // default home url
    
    public void setAddressBarFieldText(String addressBarFieldText) {
		this.addressBarField.setText(addressBarFieldText);
	}

	public Button getForwardButton() {
		return forwardButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public static final Logger logger = Logger.getLogger(Application.class);
        
    private static CheckMenuItem speechModeEnable;
    
  //Event Handlers
  	public EventHandler<ActionEvent> backAction;
  	public EventHandler<ActionEvent> goAction;
  	public EventHandler<KeyEvent> goActionOnEnter;
  	public EventHandler<ActionEvent> forwardAction;
  	public EventHandler<ActionEvent> refreshAction;
  	public EventHandler<ActionEvent> bookmarkAction;
  	public EventHandler<ActionEvent> bookmarkToModelAction;
  	public EventHandler<ActionEvent> speechAction;
  	public EventHandler<ActionEvent> exitAction;
  	//Constructor
  	public TabToolbarView(EventHandler<ActionEvent> backAction,
  			EventHandler<ActionEvent> goAction,EventHandler<KeyEvent> goActionOnEnter, EventHandler<ActionEvent> forwardAction,
  			EventHandler<ActionEvent> refreshAction, EventHandler<ActionEvent> bookmarkAction, EventHandler<ActionEvent> bookmarkToModelAction,EventHandler<ActionEvent> speechAction,
  			EventHandler<ActionEvent> exitAction

  	) {
  		this.backAction = backAction;
  		this.goAction = goAction;
  		this.goActionOnEnter = goActionOnEnter;
  		this.forwardAction = forwardAction;
  		this.refreshAction = refreshAction;
  		this.bookmarkAction = bookmarkAction;
  		this.bookmarkToModelAction = bookmarkToModelAction;
  		this.speechAction = speechAction;
  		this.exitAction = exitAction;
  	}
    
    public Pane CreateNavToolbar()
    {
        getImgObj = new GetImagePath();
        addressBarField = new TextField();
        sidebarView = new SideBarView();
        HBox.setHgrow(addressBarField, Priority.ALWAYS);
        addressBarField.setStyle("-fx-font-size: 20;");
		addressBarField.setPromptText("Where do you want to go today?");
		addressBarField.setTooltip(new Tooltip("Enter a location"));
		setAddressBarFieldText(DEFAULT_HOME);
		addressBarField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER)) {
					/*try {
						getVoiceBrowser().navTo(addressBarField.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					// System.out.println("in actionlistener");
				}
			}
		});
        
        isSpeechMode = false;
		counter = 0;
		//sTask = new SpeechRecognitionTask(voiceBrowserObj.getVoiceBrowser(), voiceBrowserObj);
        
        //call createBackButton() to create the back button in the tabToolbarview
        createBackButton();
        //call createForwardButton() to create the forward button in the tabToolbarview
        createForwardButton();
        //call createNavigateButton() to create the navigate button in the tabToolbarview
        createNavigateButton();
        //call createAddTabButton() to create a new tab for the user to use for browsing purposes
        createAddTabButton();
        // call createRefreshButton() to reload the current page
        createRefreshButton();
        // call createBookmarkButton() to create the bookmark button
        createBookmarkButton();
        // call createSpeechButton() to create to toggle button for the speech mdoe
        createSpeechButton();
        // call createHomeButton(), by clicking on this button the user will be directed to the homepage
        createHomeButton();
        // call createMenuButton() to create the menu
        createMenuButton();
        //creates the sidebar button, createSideBarButton() -- this houses the smart notes tab and the speech mode tab
        createSideBarButton();           
       
        
     // align all of the navigation widgets in a horizontal toolbar.
       
        final HBox navPane = new HBox();
		navPane.setPadding(new Insets(5, 0, 5, 0));
		navPane.setAlignment(Pos.CENTER);
		navPane.getStyleClass().add("toolbar");
		navPane.setSpacing(6);
		/*navPane.getChildren().addAll(sidebarButton, backButton, forwardButton, refreshButton,
				voiceBrowserObj.getAddressBarField(), navButton,
				addBookmarkButton, addTabButton, homeButton, speechButton, menuButton);*/
		navPane.getChildren().addAll(sidebarButton, backButton, forwardButton, refreshButton, addressBarField, navButton, addBookmarkButton, homeButton, speechButton, addTabButton, menuButton);

		navPane.setFillHeight(false);
		Platform.runLater(new Runnable() {
			public void run() {
				navPane.setMinHeight(navPane.getHeight());
			}
		});
                        
        return navPane;
    }
    
    
     /** 
     *  creates the forward button
     * */
     
    private void createForwardButton() {
		iconPath = getImgObj.jarScan("icons.jar", "Arrows-Forward-icon");
		forwardButton = new Button(null);
		forwardButton.setTranslateX(-2);
		forwardButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		forwardGraphic = new ImageView(new Image(iconPath));
		forwardColorAdjust = new ColorAdjust();
		forwardColorAdjust.setBrightness(-0.1);
		forwardColorAdjust.setContrast(-0.1);
		forwardGraphic.setEffect(forwardColorAdjust);
		forwardGraphic.setPreserveRatio(true);
		forwardGraphic.setFitHeight(24);
		forwardButton.setGraphic(forwardGraphic);
		forwardButton.setTooltip(new Tooltip("Go forward"));
		
		 /** forwardButton.onActionProperty().set(new EventHandler<ActionEvent>()
		 * {
		 * 
		 * @Override public void handle(ActionEvent actionEvent) { if
		 * (chrome.getBrowser().getHistory().canNavForward()) {
		 * chrome.getBrowser
		 * ().navTo(chrome.getBrowser().getHistory().requestNavForward()); } }
		 * });
		 * forwardButton.setOnMouseReleased(chrome.getBrowser().getHistory().
		 * createShowHistoryMouseEvent(backButton));*/
		 

	}
    
    /*
     * 
     *  create the back button to be added into the tabToolbarView
     * */
     
    private void createBackButton() {
		
		/*  
		 * Back button
		 */
		 
		backButton = new Button(null);
		backButton.setTooltip(new Tooltip("Go back"));
		backButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");

		iconPath = getImgObj.jarScan("icons.jar", "Arrows-Back-icon");
		backGraphic = new ImageView(new Image(iconPath));
		backColorAdjust = new ColorAdjust();
		backColorAdjust.setBrightness(-0.1);
		backColorAdjust.setContrast(-0.1);
		backGraphic.setEffect(backColorAdjust);
		backButton.setGraphic(backGraphic);
		backGraphic.setPreserveRatio(true);
		backGraphic.setFitHeight(24);
		backButton.onActionProperty().set(backAction);
	}
    
    
     /* 
     *  createNavigateButton() - create the navigate/Go button in the toolbar
     */ 
     
    private void createNavigateButton() {
		
		/* 
		 * create navigate button
		 */
		 
		iconPath = getImgObj.jarScan("icons.jar", "Go-icon");
		navButton = new Button(null);
		navButton.setTooltip(new Tooltip("Hit it"));
		navButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		// navButton.setStyle("-fx-border-color: RED");
		navGraphic = new ImageView(new Image(iconPath));
		navColorAdjust = new ColorAdjust();
		navColorAdjust.setContrast(-0.1);
		navGraphic.setEffect(navColorAdjust);
		navGraphic.setPreserveRatio(true);
		navGraphic.setFitHeight(24);
		navButton.setGraphic(navGraphic);
		navButton.onActionProperty().set(goAction);
	}
    
    
	 /* 
	 *  create the add tab button
	 *  on clickign this a new tab would be create; ie. a new instance of 
	 *  the browser window would be created
	 */ 
	 
	private void createAddTabButton(){
		iconPath = getImgObj.jarScan("icons.jar", "Plus-icon");
		addTabButton = new Button(null);
		addTabButton.setTranslateX(-2);
		addTabButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		addTabGraphic = new ImageView(new Image(iconPath));
		addTabColorAdjust = new ColorAdjust();
		addTabColorAdjust.setBrightness(-0.1);
		addTabColorAdjust.setContrast(-0.1);
		addTabGraphic.setEffect(addTabColorAdjust);
		addTabGraphic.setPreserveRatio(true);
		addTabGraphic.setFitHeight(24);
		addTabButton.setGraphic(addTabGraphic);
		addTabButton.setTooltip(new Tooltip("Open a new tab"));
		addTabButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                TabView addTab = new TabView();
                BrowserTabBarView.getBrowserTabHolder().getTabs().add(addTab);
            }
            
        });
	}
	
	
	 /* 
	 *  creates the refresh button - used to reload the current page in the same 
	 *  tab
	 */ 
	 
	private void createRefreshButton() {
		
		 /** 
		 * Refresh page button
		 */
		iconPath = getImgObj.jarScan("icons.jar", "Basic-Reload-icon");
		refreshButton = new Button(null);
		refreshButton.setTranslateX(-2);
		refreshButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		refreshGraphic = new ImageView(new Image(iconPath));
		refreshColorAdjust = new ColorAdjust();
		refreshColorAdjust.setBrightness(-0.1);
		refreshColorAdjust.setContrast(-0.1);
		refreshGraphic.setEffect(refreshColorAdjust);
		refreshGraphic.setPreserveRatio(true);
		refreshGraphic.setFitHeight(24);
		refreshButton.setGraphic(refreshGraphic);
		refreshButton.setTooltip(new Tooltip("Refresh"));
		refreshButton.onActionProperty().set(refreshAction);
	}
	
	
	 /** 
	 * creates the bookmark button using which the user can bookmark pages
	 * */
	 
	private void createBookmarkButton() {
		
		 /** 
		 * Bookmark page button
		 */

		bookmarkStage = new Stage();
		bookmarkStage.initStyle(StageStyle.DECORATED);
		Scene scene = new Scene(getAddBookmarkPopupScene(), 300, 150);
		bookmarkStage.setScene(scene);

		iconPath = getImgObj.jarScan("icons.jar", "Bookmarks2-icon");

		addBookmarkButton = new Button(null);
		addBookmarkButton.setTranslateX(-2);
		addBookmarkButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		bookmarkGraphic = new ImageView(new Image(iconPath));
		bookmarkColorAdjust = new ColorAdjust();
		refreshColorAdjust.setBrightness(-0.1);
		refreshColorAdjust.setContrast(-0.1);
		bookmarkGraphic.setEffect(bookmarkColorAdjust);
		bookmarkGraphic.setPreserveRatio(true);
		bookmarkGraphic.setFitHeight(24);
		addBookmarkButton.setGraphic(bookmarkGraphic);
		addBookmarkButton.setTooltip(new Tooltip("Bookmark me"));

		bookmarkStage = new Stage();
		bookmarkStage.initStyle(StageStyle.DECORATED);
		Scene bookmarkScene = new Scene(getAddBookmarkPopupScene(), 300, 150);
		bookmarkStage.setScene(bookmarkScene);

		addBookmarkButton.setOnAction(bookmarkAction);
	}
	
	
	 /** 
	 *  speech button helper method
	 **/ 
	 
	public void createSpeechButtonHelper(String imgName, String toolTipValue) {
		iconPath = getImgObj.jarScan("icons.jar", imgName);
		speechGraphic = new ImageView(new Image(iconPath));
		speechButton.setGraphic(speechGraphic);
		speechButton.setTooltip(new Tooltip(toolTipValue));
	}
	
	
	 /** 
	 *  creates the speech toggle button to allow the user to switch
	 *  between normal usage fo a browser and to use speech to control
	 *  most of its functionalities
	 * */
	 
	private void createSpeechButton() {
		iconPath = getImgObj.jarScan("icons.jar", "Micro-off-icon");
		speechButton = new Button(null);
		speechButton.setTranslateX(-2);
		speechButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		speechGraphic = new ImageView(new Image(iconPath));
		speechColorAdjust = new ColorAdjust();
		speechColorAdjust.setBrightness(-0.1);
		speechColorAdjust.setContrast(-0.1);
		speechGraphic.setEffect(speechColorAdjust);
		speechGraphic.setPreserveRatio(true);
		speechGraphic.setFitHeight(24);
		speechButton.setGraphic(speechGraphic);
		speechButton.setTooltip(new Tooltip("Enable speech mode"));
		// speechButton.set
		speechButton.onActionProperty().set(speechAction);
	}
	
	
	 /** 
	 *  create the home button
	 * */
	 
	private void createHomeButton() {
		
		 /** 
		 * Home button*/
		 
		iconPath = getImgObj.jarScan("icons.jar", "Basic-Home-icon");
		homeButton = new Button(null);
		homeButton.setTranslateX(-2);
		homeButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		homeGraphic = new ImageView(new Image(iconPath));
		homeColorAdjust = new ColorAdjust();
		homeColorAdjust.setBrightness(-0.1);
		homeColorAdjust.setContrast(-0.1);
		homeGraphic.setEffect(homeColorAdjust);
		homeGraphic.setPreserveRatio(true);
		homeGraphic.setFitHeight(24);
		homeButton.setGraphic(homeGraphic);
		homeButton.setTooltip(new Tooltip("Take me home"));
	}
	
	
	 /** 
	 *  create the menu button with all children menu items
	 * */
	 
	private void createMenuButton() {
		
		 /** 
		 * Menu button
		 */
		iconPath = getImgObj.jarScan("icons.jar", "Menu-icon");
		menuButton = new MenuButton(null);
		menuButton.setTranslateX(-2);
		menuButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		menuGraphic = new ImageView(new Image(iconPath));
		menuColorAdjust = new ColorAdjust();
		menuColorAdjust.setBrightness(-0.1);
		menuColorAdjust.setContrast(-0.1);
		menuGraphic.setEffect(menuColorAdjust);
		menuGraphic.setPreserveRatio(true);
		menuGraphic.setFitHeight(24);
		menuButton.setGraphic(menuGraphic);
		
		menuButton.setTooltip(new Tooltip("Explore more"));
		
		 /** 
		 * creating menu items save load exit help about show status bar
		 * bookmarks history enable/disable speech
		 */
		saveMenuItem = new MenuItem("Save");
		loadMenuItem = new MenuItem("Load");
		exitMenuItem = new MenuItem("Close");
		showStatusBarMenuItem = new MenuItem("Show Status Bar");
		showBookmarkMenuItem = new Menu("BookMarks");
		showHistoryMenuItem = new MenuItem("History");
		speechModeEnable = new CheckMenuItem("Enable/Disable Speech Mode");
		userManualMenuItem = new MenuItem("Help");
		aboutMenuItem = new MenuItem("About");

		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//voiceBrowserObj.closeBrowser();
			}
		});
		menuButton.getItems().addAll(saveMenuItem, loadMenuItem,
				showStatusBarMenuItem, showBookmarkMenuItem,
				showHistoryMenuItem, speechModeEnable, userManualMenuItem,
				aboutMenuItem, exitMenuItem);

	}
	
	
	/* * 
	 *  create a button to open the sidebar, the smart notes will be displayed here
	 **/ 
	 
	private static void createSideBarButton(){
		iconPath = getImgObj.jarScan("icons.jar", "Down-Arrow-icon");
		sidebarButton = new Button(null);
		sidebarButton.setTranslateX(-2);
		sidebarButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		sidebarGraphic = new ImageView(new Image(iconPath));
		sidebarColorAdjust = new ColorAdjust();
		sidebarColorAdjust.setBrightness(-0.1);
		sidebarColorAdjust.setContrast(-0.1);
		sidebarGraphic.setEffect(sidebarColorAdjust);
		sidebarGraphic.setPreserveRatio(true);
		sidebarGraphic.setFitHeight(24);
		sidebarButton.setGraphic(sidebarGraphic);
		sidebarButton.setTooltip(new Tooltip("Show me the cool stuff"));
	   
	    sidebarButton.setOnAction(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent actionEvent) {
	        // hide sidebar.
	    	  System.out.println("sidebar button pressed!!!");
	        final double startWidth = getSidebarDisplay().getWidth();
	        System.out.println("startwidth ----------------------------------------------------------------- " + startWidth);
	        final Animation hideSidebar = new Transition() {
	          { setCycleDuration(Duration.millis(250)); }
	          protected void interpolate(double frac) {
	        	  System.out.println("start width: = " + startWidth);
	        	  System.out.println("frac = " + frac);
	            final double curWidth = startWidth * (1.0 - frac);
	            System.out.println("current width is: " + curWidth);
	            getSidebarDisplay().setPrefWidth(curWidth);
	            getSidebarDisplay().setTranslateX(-startWidth + curWidth);
	          }
	        };
	        hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent actionEvent) {
	        	  System.out.println("in on finished property");
	        	  getSidebarDisplay().setVisible(false);
	          }
	        });

	        // show sidebar.
	        final Animation showSidebar = new Transition() {
	          { setCycleDuration(Duration.millis(250)); }
	          protected void interpolate(double frac) {
	        	  getSidebarDisplay().setVisible(true);
	            final double curWidth = startWidth * frac;
	            System.out.println("show sidebar current width: " + curWidth);
	            getSidebarDisplay().setPrefWidth(350.00);
	            getSidebarDisplay().setTranslateX(-startWidth + curWidth);
	          }
	        };

	        if (showSidebar.statusProperty().get().equals(Animation.Status.STOPPED) && hideSidebar.statusProperty().get().equals(Animation.Status.STOPPED)) {
	          if (getSidebarDisplay().isVisible()) {
	            hideSidebar.play();
	          } else {
	            showSidebar.play();
	          }
	        }
	      }
	    });
	
	}
	
	public static VBox getSidebarDisplay() {
	    return sidebarView.getBarDisplay();
	  }
	
	
	public Parent getAddBookmarkPopupScene() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		addBookmarkToModelButton = new Button("Add");

		// Text text = new Text("Welcome");

		Label titleLabel = new Label();
		bookmarkTitleTextField = new TextField();

		titleLabel.setText("Title:");

		Label urlLabel = new Label("URL");
		bookmarkURLTextField = new TextField();

		// grid.add(text, 0, 0, 2, 1);
		grid.add(titleLabel, 1, 0);
		grid.add(bookmarkTitleTextField, 2, 0);
		grid.add(urlLabel, 1, 1);
		grid.add(bookmarkURLTextField, 2, 1);
		// grid.add(button, 0, 2);
		addBookmarkToModelButton.setOnAction(bookmarkToModelAction);
		HBox hbox = new HBox(10);
		hbox.getChildren().add(addBookmarkToModelButton);
		hbox.setAlignment(Pos.BASELINE_RIGHT);
		grid.add(hbox, 1, 3);

		return grid;

	}
	
	
	
    public Stage getBookmarkStage() {
		return bookmarkStage;
	}

	public static boolean isSpeechMode() {
		return isSpeechMode;
	}

	public static void setSpeechMode(boolean isSpeechMode) {
		TabToolbarView.isSpeechMode = isSpeechMode;
	}

	/**
     * @return the addTabButton
     */
    public Button getAddTabButton() {
        return addTabButton;
    }
    /**
     * @return the navButton
     */
    public Button getNavButton() {
        return navButton;
    }
    /**
     * @return the addressBarField
     */
    public TextField getAddressBarField() {
        return addressBarField;
    }
    
    /**
     * @return the addBookmarkButton
     */
    public Button getAddBookmarkButton() {
        return addBookmarkButton;
    }

    /**
     * @return the addBookmarkToModelButton
     */
    public Button getAddBookmarkToModelButton() {
        return addBookmarkToModelButton;
    }
    
    public void setBookmarkUrlText(String bookmarkURl)
    {
        
        bookmarkURLTextField.setText(bookmarkURl);
    }
    
    public void setBookmarkTitleText(String bookmarkTitle)
    {
        bookmarkTitleTextField.setText(bookmarkTitle);
    }
    
    public String getBookmarkTitle()
    {
        return bookmarkTitleTextField.getText();
    }
    
    public String getBookmarkURL()
    {
        return bookmarkURLTextField.getText();
    }

    /**
     * @return the showBookmarkMenuItem
     */
    public Menu getShowBookmarkMenuItem() {
        return showBookmarkMenuItem;
    }
    
}