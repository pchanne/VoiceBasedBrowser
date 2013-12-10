/**
 * This class represents toolbar of a tab. It is responsible
 *  for creating all the buttons and add them to toolbar
 *   whenever new browser is created.
 */

package com.browser.view;

import java.net.URL;

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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import org.apache.log4j.Logger;

import com.browser.helper.GetImagePath;
import com.browser.main.VoiceBrowser;

public class TabToolbarView {

	private Button backButton;
	private ImageView backGraphic;
	private ColorAdjust backColorAdjust;

	private MenuButton menuButton;
	private static MenuItem exitMenuItem;
	private static Menu showBookmarkMenuItem;
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
	private boolean isSpeechMode;

	private Button addBookmarkButton;
	private Button addBookmarkToModelButton;
	private TextField bookmarkURLTextField;
	private Stage bookmarkStage;
	private TextField bookmarkTitleTextField;
	private ImageView bookmarkGraphic;
	private ColorAdjust bookmarkColorAdjust;

	private ImageView logoGraphic;

	public static String DEFAULT_HOME = "http://www.google.com";
	public static final Logger logger = Logger.getLogger(Application.class);

	private HBox navPane;

	public HBox getNavPane() {
		return navPane;
	}

	public void setNavPane(HBox navPane) {
		this.navPane = navPane;
	}

	private static CheckMenuItem speechModeEnable;

	public Button getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(Button homeButton) {
		this.homeButton = homeButton;
	}

	public Button getSpeechButton() {
		return speechButton;
	}

	public void setSpeechButton(Button speechButton) {
		this.speechButton = speechButton;
	}

	public void setAddressBarFieldText(String url) {
		this.addressBarField.setText(url);
	}

	public boolean isSpeechMode() {
		return isSpeechMode;
	}

	public void setSpeechMode(boolean isSpeechMode) {
		this.isSpeechMode = isSpeechMode;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public Button getForwardButton() {
		return forwardButton;
	}

	public void setForwardButton(Button forwardButton) {
		this.forwardButton = forwardButton;
	}

	public Button getAddTabButton() {
		return addTabButton;
	}

	public Button getNavButton() {
		return navButton;
	}

	public TextField getAddressBarField() {
		return addressBarField;
	}

	public Button getAddBookmarkButton() {
		return addBookmarkButton;
	}

	public Button getAddBookmarkToModelButton() {
		return addBookmarkToModelButton;
	}

	public void setBookmarkUrlText(String bookmarkURl) {

		bookmarkURLTextField.setText(bookmarkURl);
	}

	public void setBookmarkTitleText(String bookmarkTitle) {
		bookmarkTitleTextField.setText(bookmarkTitle);
	}

	public String getBookmarkTitle() {
		return bookmarkTitleTextField.getText();
	}

	public String getBookmarkURL() {
		return bookmarkURLTextField.getText();
	}

	public Menu getShowBookmarkMenuItem() {
		return showBookmarkMenuItem;
	}

	public Stage getBookmarkStage() {
		return bookmarkStage;
	}

	// Event Handler's
	public EventHandler<ActionEvent> backAction;
	public EventHandler<ActionEvent> goAction;
	public EventHandler<KeyEvent> goActionOnEnter;
	public EventHandler<ActionEvent> forwardAction;
	public EventHandler<ActionEvent> refreshAction;
	public EventHandler<ActionEvent> bookmarkAction;
	public EventHandler<ActionEvent> bookmarkToModelAction;
	public EventHandler<ActionEvent> speechAction;
	public EventHandler<ActionEvent> exitAction;
	public EventHandler<ActionEvent> addTabAction;
	public EventHandler<ActionEvent> homeAction;

	// Constructor
	public TabToolbarView(EventHandler<ActionEvent> backAction,
			EventHandler<ActionEvent> goAction,
			EventHandler<KeyEvent> goActionOnEnter,
			EventHandler<ActionEvent> forwardAction,
			EventHandler<ActionEvent> refreshAction,
			EventHandler<ActionEvent> bookmarkAction,
			EventHandler<ActionEvent> bookmarkToModelAction,
			EventHandler<ActionEvent> speechAction,
			EventHandler<ActionEvent> exitAction,
			EventHandler<ActionEvent> addTabAction,
			EventHandler<ActionEvent> homeAction

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
		this.addTabAction = addTabAction;
		this.homeAction = homeAction;
	}

	public Pane CreateNavToolbar() {
		getImgObj = new GetImagePath();
		addressBarField = new TextField();
		sidebarView = new SideBarView();
		isSpeechMode = false;

		createAddressBarField();
		// call createBackButton() to create the back button in the
		// tabToolbarview
		createBackButton();
		// call createForwardButton() to create the forward button in the
		// tabToolbarview
		createForwardButton();
		// call createNavigateButton() to create the navigate button in the
		// tabToolbarview
		createNavigateButton();
		// call createAddTabButton() to create a new tab for the user to use for
		// browsing purposes
		createAddTabButton();
		// call createRefreshButton() to reload the current page
		createRefreshButton();
		// call createBookmarkButton() to create the bookmark button
		createBookmarkButton();
		// call createSpeechButton() to create to toggle button for the speech
		// mdoe
		createSpeechButton();
		// call createHomeButton(), by clicking on this button the user will be
		// directed to the homepage
		createHomeButton();
		// call createMenuButton() to create the menu
		createMenuButton();
		// creates the sidebar button, createSideBarButton() -- this houses the
		// smart notes tab and the speech mode tab
		createSideBarButton();

		// align all of the navigation widgets in a horizontal toolbar.

		navPane = new HBox();
		navPane.setPadding(new Insets(5, 0, 5, 0));
		navPane.setAlignment(Pos.CENTER);
		navPane.getStyleClass().add("toolbar");
		navPane.setSpacing(6);
		navPane.getChildren().addAll(sidebarButton, backButton, forwardButton,
				refreshButton, addressBarField, navButton, addBookmarkButton,
				homeButton, speechButton, addTabButton, menuButton);

		navPane.setFillHeight(false);
		Platform.runLater(new Runnable() {
			public void run() {
				navPane.setMinHeight(navPane.getHeight());
			}
		});

		return navPane;
	}

	/*
	 * Create addressBar
	 */
	public void createAddressBarField() {

		setAddressBarFieldText(DEFAULT_HOME);
		addressBarField.setStyle("-fx-font-size: 20;");
		addressBarField.setPromptText("Where do you want to go today?");
		addressBarField.setTooltip(new Tooltip("Enter a location"));
		addressBarField.setOnKeyReleased(goActionOnEnter);

		HBox.setHgrow(addressBarField, Priority.ALWAYS);
	}

	/*
	 * creates the forward button
	 */
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
		forwardButton.onActionProperty().set(forwardAction); // sets the
																// forwards
																// button event
																// handler

	}

	/*
	 * create the back button to be added into the tabToolbarView
	 */
	private void createBackButton() {

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
		backButton.onActionProperty().set(backAction); // sets the back button
														// click action
	}

	/*
	 * createNavigateButton() - create the navigate/Go button in the toolbar
	 */

	private void createNavigateButton() {

		iconPath = getImgObj.jarScan("icons.jar", "Go-icon");
		navButton = new Button(null);
		navButton.setTooltip(new Tooltip("Hit it"));
		navButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		navGraphic = new ImageView(new Image(iconPath));
		navColorAdjust = new ColorAdjust();
		navColorAdjust.setContrast(-0.1);
		navGraphic.setEffect(navColorAdjust);
		navGraphic.setPreserveRatio(true);
		navGraphic.setFitHeight(24);
		navButton.setGraphic(navGraphic);
		navButton.onActionProperty().set(goAction); // sets the go button event
													// handler
	}

	/*
	 * create the add tab button on clickign this a new tab would be create; ie.
	 * a new instance of the browser window would be created
	 */
	private void createAddTabButton() {
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
		addTabButton.setOnAction(addTabAction); // sets the add tab button event
												// handler for button press
	}

	/*
	 * creates the refresh button - used to reload the current page in the same
	 * tab
	 */
	private void createRefreshButton() {

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
		refreshButton.setOnAction(refreshAction); // sets the refresh button
													// event handler
	}

	/*
	 * creates the bookmark button using which the user can bookmark pages
	 */
	private void createBookmarkButton() {

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

		addBookmarkButton.setOnAction(bookmarkAction); // sets the bookmark
														// button event handler
	}

	/*
	 * speech button helper method
	 */
	public void createSpeechButtonHelper(String imgName, String toolTipValue) {
		iconPath = getImgObj.jarScan("icons.jar", imgName);
		speechGraphic = new ImageView(new Image(iconPath));
		speechButton.setGraphic(speechGraphic);
		speechButton.setTooltip(new Tooltip(toolTipValue));
	}

	/*
	 * creates the speech toggle button to allow the user to switch between
	 * normal usage fo a browser and to use speech to control most of its
	 * functionalities
	 */
	private void createSpeechButton() {
		iconPath = getImgObj.jarScan("icons.jar", "Micro-off-icon");
		speechButton = new Button(null);
		speechButton.setTranslateX(-2);
		speechButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
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
		speechButton.onActionProperty().set(speechAction); // sets the speech
															// button event
															// handler
	}

	/*
	 * creates the home button
	 */
	private void createHomeButton() {
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
		homeButton.setOnAction(homeAction); // sets the home button event
											// handler
	}

	/*
	 * create the menu button with all children menu items
	 */

	private void createMenuButton() {

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
		menuButton.setGraphic(menuGraphic); // sets the menu button event
											// handler

		menuButton.setTooltip(new Tooltip("Explore more"));

		/**
		 * creating menu items save load exit help about show status bar
		 * bookmarks history enable/disable speech
		 */
		exitMenuItem = new MenuItem("Close");
		showBookmarkMenuItem = new Menu("BookMarks");
		userManualMenuItem = new MenuItem("Help");
		aboutMenuItem = new MenuItem("About");

		userManualMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				TabView tab = new TabView();
				BrowserTabBarView.getBrowserTabHolder().getTabs().add(tab);
				tab.setText("Help Manual");

				URL urlString = VoiceBrowser.class.getClassLoader()
						.getResource("Help.html");

				tab.getViewController().getBrowserWindowView().browser
						.getEngine().load(urlString.toString());
			}
		});

		aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				Stage stage = new Stage();
				Scene dialog = new Scene(getAboutDialog(), 650, 350);
				stage.setScene(dialog);
				stage.setTitle("About Voice Based Browser");
				stage.setResizable(false);
				stage.show();
			}

		});

		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});

		menuButton.getItems().addAll(showBookmarkMenuItem, userManualMenuItem,
				aboutMenuItem, exitMenuItem);

	}

	/* *
	 * create a button to open the sidebar, the smart notes will be displayed
	 * here
	 */

	private static void createSideBarButton() {
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

		// handles the close and open animation of the sidebar in the browser
		sidebarButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				final double startWidth = getSidebarDisplay().getWidth();
				final Animation hideSidebar = new Transition() {
					{
						setCycleDuration(Duration.millis(250));
					}

					protected void interpolate(double frac) {
						final double curWidth = startWidth * (1.0 - frac);
						getSidebarDisplay().setPrefWidth(curWidth);
						getSidebarDisplay().setTranslateX(
								-startWidth + curWidth);
					}
				};
				hideSidebar.onFinishedProperty().set(
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								getSidebarDisplay().setVisible(false);
							}
						});

				// show sidebar.
				final Animation showSidebar = new Transition() {
					{
						setCycleDuration(Duration.millis(250));
					}

					protected void interpolate(double frac) {
						getSidebarDisplay().setVisible(true);
						final double curWidth = startWidth * frac;
						getSidebarDisplay().setPrefWidth(350.00);
						getSidebarDisplay().setTranslateX(
								-startWidth + curWidth);
					}
				};

				if (showSidebar.statusProperty().get()
						.equals(Animation.Status.STOPPED)
						&& hideSidebar.statusProperty().get()
								.equals(Animation.Status.STOPPED)) {
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

	// returns the grid pane that builds the dialog box for adding bookmarks
	// return type is GridPane
	public Parent getAddBookmarkPopupScene() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		addBookmarkToModelButton = new Button("Add");

		Label titleLabel = new Label();
		bookmarkTitleTextField = new TextField();

		titleLabel.setText("Title:");

		Label urlLabel = new Label("URL");
		bookmarkURLTextField = new TextField();

		grid.add(titleLabel, 1, 0);
		grid.add(bookmarkTitleTextField, 2, 0);
		grid.add(urlLabel, 1, 1);
		grid.add(bookmarkURLTextField, 2, 1);
		addBookmarkToModelButton.setOnAction(bookmarkToModelAction);
		HBox hbox = new HBox(10);
		hbox.getChildren().add(addBookmarkToModelButton);
		hbox.setAlignment(Pos.BASELINE_RIGHT);
		grid.add(hbox, 1, 3);

		return grid;

	}

	// creates and returns the about dialog box
	// displays information about the Voice Based Browser
	// return type is GridPane
	private Parent getAboutDialog() {

		URL urlString = TabToolbarView.class.getClassLoader().getResource(
				"browser-logo.png");

		logoGraphic = new ImageView(new Image(urlString.toString()));
		logoGraphic.setFitWidth(200);
		logoGraphic.setFitHeight(200);
		Label titleLabel = new Label("  " + "Voice Based Browser");
		titleLabel.setStyle("-fx-font-size: 20pt;");
		titleLabel.setAlignment(Pos.BASELINE_CENTER);
		Label version = new Label("Version: ");
		Label authors = new Label("Developers: ");
		authors.setPrefWidth(60);
		Label versionNo = new Label("1.0");
		Label authorNames = new Label(
				"Akshay Pawaskar, Gaurav Pandey, Pankaj Channe, Shailaja Kapoor, Sneha Shekatkar, Tarun Gulati");
		authorNames.setWrapText(true);
		Label text = new Label(
				"Voice Based Browser is an attempt to make browsing easier by using voice to operate the browser commands.");
		text.setWrapText(true);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(25);
		grid.setVgap(15);

		GridPane innerGrid = new GridPane();
		innerGrid.setHgap(5);
		innerGrid.setVgap(5);

		HBox hbox1 = new HBox(10);
		hbox1.getChildren().addAll(version, versionNo);
		hbox1.setAlignment(Pos.BASELINE_LEFT);

		HBox hbox2 = new HBox(10);
		hbox2.getChildren().addAll(authors, authorNames);
		hbox2.setAlignment(Pos.BASELINE_LEFT);

		HBox hbox3 = new HBox(10);
		hbox3.getChildren().add(text);
		hbox3.setAlignment(Pos.BASELINE_LEFT);

		innerGrid.add(version, 1, 0);
		innerGrid.add(versionNo, 2, 0);
		innerGrid.add(authors, 1, 1);
		innerGrid.add(authorNames, 2, 1);

		innerGrid.setAlignment(Pos.BASELINE_LEFT);

		VBox vbox = new VBox(20);
		vbox.setAlignment(Pos.BASELINE_CENTER);
		vbox.getChildren().addAll(logoGraphic, titleLabel);

		grid.add(vbox, 1, 0);
		grid.add(innerGrid, 1, 1);
		grid.add(hbox3, 1, 2);

		return grid;

	}

}