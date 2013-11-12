package com.browser.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.ImageIcon;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import com.browser.helper.GetImagePath;

import com.browser.main.VoiceBrowser;
import com.browser.speech.SpeechRecognitionTask;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.WindowEvent;

public class ToolbarView {

	private static Button backButton;
	private static MenuButton menuButton;
	private static Button homeButton;
	private static Button speechButton;
	private static Button refreshButton;
	private static Button forwardButton;
	public static Button navButton;
	private static GetImagePath getImgObj;
	private static String iconPath;
	private static ContextMenu menuItems;

	private static MenuItem saveMenuItem;
	private static MenuItem loadMenuItem;
	private MenuItem newTabMenuItem;
	private static MenuItem exitMenuItem;

	private static MenuItem showStatusBarMenuItem;
	private static Menu showBookmarkMenuItem;
	private static MenuItem showHistoryMenuItem;

	private static MenuItem userManualMenuItem;
	private static MenuItem aboutMenuItem;

	private static CheckMenuItem speechModeEnable;
	private static Button addBookmarkButton;
	private static Button addBookmarkToModelButton;
	private static TextField bookmarkURLTextField;
	private static Stage bookmarkStage;
	private static TextField bookmarkTitleTextField;

	private static ImageView speechGraphic;
	private static ColorAdjust speechColorAdjust;

	private static ImageView backGraphic;
	private static ColorAdjust backColorAdjust;
	private static ImageView forwardGraphic;
	private static ColorAdjust forwardColorAdjust;
	private static ImageView refreshGraphic;
	private static ColorAdjust refreshColorAdjust;
	private static ImageView navGraphic;
	private static ColorAdjust navColorAdjust;
	private static ImageView homeGraphic;
	private static ColorAdjust homeColorAdjust;
	private static ImageView menuGraphic;
	private static ColorAdjust menuColorAdjust;
	private static ImageView bookmarkGraphic;
	private static ColorAdjust bookmarkColorAdjust;

	private static boolean isSpeechMode;
	private static SpeechRecognitionTask sTask;

	public static Button getBackButton() {
		return backButton;
	}

	public static void setBackButton(Button backButton) {
		ToolbarView.backButton = backButton;
	}

	public static Button getNavButton() {
		return navButton;
	}

	public static void setNavButton(Button navButton) {
		ToolbarView.navButton = navButton;
	}

	/**
	 * @return the bookmarkStage
	 */
	public static Stage getBookmarkStage() {
		return bookmarkStage;
	}

	public static Pane CreateNavToolbar(final VoiceBrowser voiceBrowserObj) {

		getImgObj = new GetImagePath();

		isSpeechMode = false;
		sTask = new SpeechRecognitionTask(voiceBrowserObj.getVoiceBrowser(), voiceBrowserObj);

		// backButton.setOnMouseReleased(voiceBrowserObj.getVoiceBrowser().getHistory().createShowHistoryMouseEvent(backButton));
		createBackButton();

		createForwardButton();

		createNavigateButton(voiceBrowserObj);

		createHomeButton();

		createRefreshButton();

		createSpeechButton();
		//createSpeechButtonHelper();

		createBookmarkButton();

		createMenuButton(voiceBrowserObj);

		// align all of the navigation widgets in a horizontal toolbar.
		final HBox navPane = new HBox();
		navPane.setPadding(new Insets(5, 0, 5, 0));
		navPane.setAlignment(Pos.CENTER);
		navPane.getStyleClass().add("toolbar");
		navPane.setSpacing(6);
		// navPane.setSp
		// navPane.getChildren().addAll(backButton);
		// navPane.getChildren().addAll(backButton, forwardButton,
		// chrome.getChromeLocField(), chrome.getTabManager().getTabPane(),
		// chrome.getTabManager().getNewTabButton(), navButton);
		navPane.getChildren().addAll(backButton, forwardButton, refreshButton,
				voiceBrowserObj.getAddressBarField(), navButton,
				addBookmarkButton, homeButton, speechButton, menuButton);

		navPane.setFillHeight(false);
		Platform.runLater(new Runnable() {
			public void run() {
				navPane.setMinHeight(navPane.getHeight());
			}
		});

		final InnerShadow innerShadow = new InnerShadow();
		innerShadow.setColor(Color.ANTIQUEWHITE);
		navPane.setEffect(innerShadow);

		return navPane;
	}

	public static Parent getAddBookmarkPopupScene() {
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

		HBox hbox = new HBox(10);
		hbox.getChildren().add(addBookmarkToModelButton);
		hbox.setAlignment(Pos.BASELINE_RIGHT);
		grid.add(hbox, 1, 3);

		return grid;

	}


	private static void createSpeechButton() {
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
		speechButton.onActionProperty().set(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				if (isSpeechMode) {
					isSpeechMode = false;
					createSpeechButtonHelper("Micro-off-icon", "Enable speech mode");
				} else {
					isSpeechMode = true;
					createSpeechButtonHelper("Micro-icon", "Disable speech mode");
					sTask.start();
				}
			}
		});
	}

	private static void createSpeechButtonHelper(String imgName, String toolTipValue) {
		iconPath = getImgObj.jarScan("icons.jar", imgName);
		speechGraphic = new ImageView(new Image(iconPath));
		speechButton.setGraphic(speechGraphic);
		speechButton.setTooltip(new Tooltip(toolTipValue));
	}

	private static void createBackButton() {
		/*
		 * 
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
		backButton.onActionProperty().set(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				System.out.println("Clicked");
				/*
				 * if
				 * (voiceBrowserObj.getVoiceBrowser().getHistory().canNavBack())
				 * { //System.out.println("inside navback");
				 * //voiceBrowserObj.getVoiceBrowser
				 * ().navTo(voiceBrowserObj.getVoiceBrowser
				 * ().getHistory().requestNavBack());
				 * 
				 * }
				 */
			}
		});
	}

	private static void createForwardButton() {
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
		/*
		 * forwardButton.onActionProperty().set(new EventHandler<ActionEvent>()
		 * {
		 * 
		 * @Override public void handle(ActionEvent actionEvent) { if
		 * (chrome.getBrowser().getHistory().canNavForward()) {
		 * chrome.getBrowser
		 * ().navTo(chrome.getBrowser().getHistory().requestNavForward()); } }
		 * });
		 * forwardButton.setOnMouseReleased(chrome.getBrowser().getHistory().
		 * createShowHistoryMouseEvent(backButton));
		 */

	}

	private static void createRefreshButton() {
		/*
		 * 
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
	}

	private static void createNavigateButton(final VoiceBrowser voiceBrowserObj) {
		/*
		 * 
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
		navButton.onActionProperty().set(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				try {
					voiceBrowserObj.getVoiceBrowser().navTo(
							voiceBrowserObj.getAddressBarField().getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private static void createHomeButton() {
		/*
		 * 
		 * Home button
		 */
		iconPath = getImgObj.jarScan("icons.jar", "Basic-Home-icon");
		homeButton = new Button(null);
		homeButton.setTranslateX(-2);
		homeButton
				.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
		homeGraphic = new ImageView(new Image(iconPath));
		homeColorAdjust = new ColorAdjust();
		homeColorAdjust.setBrightness(-0.1);
		homeColorAdjust.setContrast(-0.1);
		homeGraphic.setEffect(forwardColorAdjust);
		homeGraphic.setPreserveRatio(true);
		homeGraphic.setFitHeight(24);
		homeButton.setGraphic(homeGraphic);
		homeButton.setTooltip(new Tooltip("Take me home"));
	}

	private static void createMenuButton(final VoiceBrowser voiceBrowserObj) {
		/*
		 * 
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
		/*
		 * 
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
				voiceBrowserObj.closeBrowser();
			}
		});
		menuButton.getItems().addAll(saveMenuItem, loadMenuItem,
				showStatusBarMenuItem, showBookmarkMenuItem,
				showHistoryMenuItem, speechModeEnable, userManualMenuItem,
				aboutMenuItem, exitMenuItem);

	}

	private static void createBookmarkButton() {
		/*
		 * 
		 * Bookmark page button
		 */

		addBookmarkButton = new Button("Add Bookmark");

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

		addBookmarkButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {

				bookmarkStage.show();
			}

		});
	}

    /**
     * @return the addBookmarkButton
     */
    public static Button getAddBookmarkButton() {
        return addBookmarkButton;
    }

    /**
     * @return the addBookmarkToModelButton
     */
    public static Button getAddBookmarkToModelButton() {
        return addBookmarkToModelButton;
    }
    
    public static void setBookmarkUrlText(String bookmarkURl)
    {
        
        bookmarkURLTextField.setText(bookmarkURl);
    }
    
    public static void setBookmarkTitleText(String bookmarkTitle)
    {
        bookmarkTitleTextField.setText(bookmarkTitle);
    }
    
    public static String getBookmarkTitle()
    {
        return bookmarkTitleTextField.getText();
    }
    
    public static String getBookmarkURL()
    {
        return bookmarkURLTextField.getText();
    }

    /**
     * @return the showBookmarkMenuItem
     */
    public static Menu getShowBookmarkMenuItem() {
        return showBookmarkMenuItem;
    }

}
