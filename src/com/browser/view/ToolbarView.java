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
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import com.browser.helper.GetImagePath;
import com.browser.main.VoiceBrowser;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.WindowEvent;

public class ToolbarView {
	
	private static Button backButton;
	private static MenuButton menuButton;
	private static Button homeButton;
	private static Button bookmarkButton;
	private static Button refreshButton;
	private static Button forwardButton;
	private static Button navButton;
	private static GetImagePath getImgObj;
	private static String iconPath;
	private static ContextMenu menuItems;
	
	private static MenuItem saveMenuItem;
    private static MenuItem loadMenuItem;
    private MenuItem newTabMenuItem;
    private static MenuItem exitMenuItem;

    private static MenuItem showStatusBarMenuItem;
    private static MenuItem showBookmarkMenuItem;
    private static MenuItem showHistoryMenuItem;
    
    private static MenuItem userManualMenuItem;
    private static MenuItem aboutMenuItem;

    private static CheckMenuItem speechModeEnable;
	
	public static Pane CreateNavToolbar(final VoiceBrowser voiceBrowserObj){
		
		getImgObj = new GetImagePath();
		
		// create a back button.
		
		backButton = new Button(null);
	    backButton.setTooltip(new Tooltip("Go back"));
	    backButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
	    
	    iconPath = getImgObj.jarScan("icons.jar", "Arrows-Back-icon");
	    ImageView backGraphic = new ImageView(new Image(iconPath));
	    //Image image = new Image("..\\VoiceBasedBrowser\\icons\\Arrow-Back-icon.png");
	    //backGraphic.setImage(img);
	    final ColorAdjust backColorAdjust = new ColorAdjust();
	    backColorAdjust.setBrightness(-0.1);
	    backColorAdjust.setContrast(-0.1);
	    backGraphic.setEffect(backColorAdjust);
	    backButton.setGraphic(backGraphic);
	    backGraphic.setPreserveRatio(true);
	    backGraphic.setFitHeight(24);
	    backButton.onActionProperty().set(new EventHandler<ActionEvent>() {
	      public void handle(ActionEvent actionEvent) {
	        if (voiceBrowserObj.getVoiceBrowser().getHistory().canNavBack()) {
	        	voiceBrowserObj.getVoiceBrowser().navTo(voiceBrowserObj.getVoiceBrowser().getHistory().requestNavBack());
	        }
	      }
	    });
	    backButton.setOnMouseReleased(voiceBrowserObj.getVoiceBrowser().getHistory().createShowHistoryMouseEvent(backButton));
	    
	    
	    // create a forward button.
	    iconPath = getImgObj.jarScan("icons.jar", "Arrows-Forward-icon");
	    forwardButton = new Button(null);
	    forwardButton.setTranslateX(-2);
	    forwardButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
	    final ImageView forwardGraphic = new ImageView(new Image(iconPath));
	    final ColorAdjust forwardColorAdjust = new ColorAdjust();
	    forwardColorAdjust.setBrightness(-0.1);
	    forwardColorAdjust.setContrast(-0.1);
	    forwardGraphic.setEffect(forwardColorAdjust);
	    forwardGraphic.setPreserveRatio(true);
	    forwardGraphic.setFitHeight(24);
	    forwardButton.setGraphic(forwardGraphic);
	    forwardButton.setTooltip(new Tooltip("Go forward"));
	    /*
	    forwardButton.onActionProperty().set(new EventHandler<ActionEvent>() {
	      @Override
	      public void handle(ActionEvent actionEvent) {
	        if (chrome.getBrowser().getHistory().canNavForward()) {
	          chrome.getBrowser().navTo(chrome.getBrowser().getHistory().requestNavForward());
	        }
	      }
	    });
	    forwardButton.setOnMouseReleased(chrome.getBrowser().getHistory().createShowHistoryMouseEvent(backButton));*/
	    
	 // create a navigate button.
	    iconPath = getImgObj.jarScan("icons.jar", "Go-icon");
	    navButton = new Button(null);
	    navButton.setTooltip(new Tooltip("Hit it"));
	    navButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
	    //navButton.setStyle("-fx-border-color: RED");
	    final ImageView navGraphic = new ImageView(new Image(iconPath));
	    final ColorAdjust navColorAdjust = new ColorAdjust();
	    navColorAdjust.setContrast(-0.1);
	    navGraphic.setEffect(navColorAdjust);
	    navGraphic.setPreserveRatio(true);
	    navGraphic.setFitHeight(24);
	    navButton.setGraphic(navGraphic);
	    navButton.onActionProperty().set(new EventHandler<ActionEvent>() {
	      public void handle(ActionEvent actionEvent) {
	    	  voiceBrowserObj.getVoiceBrowser().navTo(voiceBrowserObj.getAddressBarField().getText());
	      }
	    });
	    
	    /*
	     * 
	     * Home button
	     * 
	     */
	    iconPath = getImgObj.jarScan("icons.jar", "Basic-Home-icon");
	    homeButton = new Button(null);
	    homeButton.setTranslateX(-2);
	    homeButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
	    final ImageView homeGraphic = new ImageView(new Image(iconPath));
	    final ColorAdjust homeColorAdjust = new ColorAdjust();
	    homeColorAdjust.setBrightness(-0.1);
	    homeColorAdjust.setContrast(-0.1);
	    homeGraphic.setEffect(forwardColorAdjust);
	    homeGraphic.setPreserveRatio(true);
	    homeGraphic.setFitHeight(24);
	    homeButton.setGraphic(homeGraphic);
	    homeButton.setTooltip(new Tooltip("Take me home"));
	    
	    /*
	     * 
	     * Refresh page button
	     * 
	     */
	    iconPath = getImgObj.jarScan("icons.jar", "Basic-Reload-icon");
	    refreshButton = new Button(null);
	    refreshButton.setTranslateX(-2);
	    refreshButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
	    final ImageView refreshGraphic = new ImageView(new Image(iconPath));
	    final ColorAdjust refreshColorAdjust = new ColorAdjust();
	    refreshColorAdjust.setBrightness(-0.1);
	    refreshColorAdjust.setContrast(-0.1);
	    refreshGraphic.setEffect(refreshColorAdjust);
	    refreshGraphic.setPreserveRatio(true);
	    refreshGraphic.setFitHeight(24);
	    refreshButton.setGraphic(refreshGraphic);
	    refreshButton.setTooltip(new Tooltip("Refresh"));
	    
	    /*
	     * 
	     * Bookmark page button
	     * 
	     */
	    iconPath = getImgObj.jarScan("icons.jar", "Bookmarks2-icon");
	    bookmarkButton = new Button(null);
	    bookmarkButton.setTranslateX(-2);
	    bookmarkButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
	    final ImageView bookmarkGraphic = new ImageView(new Image(iconPath));
	    final ColorAdjust bookmarkColorAdjust = new ColorAdjust();
	    refreshColorAdjust.setBrightness(-0.1);
	    refreshColorAdjust.setContrast(-0.1);
	    bookmarkGraphic.setEffect(bookmarkColorAdjust);
	    bookmarkGraphic.setPreserveRatio(true);
	    bookmarkGraphic.setFitHeight(24);
	    bookmarkButton.setGraphic(bookmarkGraphic);
	    bookmarkButton.setTooltip(new Tooltip("Bookmark me"));
	    
	    
	    /*
	     * 
	     * Menu button
	     * 
	     */
	    iconPath = getImgObj.jarScan("icons.jar", "Menu-icon");
	    menuButton = new MenuButton(null);
	    menuButton.setTranslateX(-2);
	    menuButton.setStyle("-fx-background-color: WHITE; -fx-border-color: WHITE; -fx-border-width: 0;");
	    final ImageView menuGraphic = new ImageView(new Image(iconPath));
	    final ColorAdjust menuColorAdjust = new ColorAdjust();
	    menuColorAdjust.setBrightness(-0.1);
	    menuColorAdjust.setContrast(-0.1);
	    menuGraphic.setEffect(menuColorAdjust);
	    menuGraphic.setPreserveRatio(true);
	    menuGraphic.setFitHeight(24);
	    menuButton.setGraphic(menuGraphic);
	    menuButton.setTooltip(new Tooltip("Explore more"));
	    /*
	     * 
	     *  creating menu items
	     *  save
	     *  load
	     *  exit
	     *  help
	     *  about
	     *  show status bar
	     *  bookmarks
	     *  history
	     *  enable/disable speech
	     * 
	     */
	    saveMenuItem= new MenuItem("Save");
        loadMenuItem= new MenuItem("Load");
        exitMenuItem= new MenuItem("Close");
        showStatusBarMenuItem= new MenuItem("Show Status Bar");
        showBookmarkMenuItem= new MenuItem("BookMarks");
        showHistoryMenuItem = new MenuItem("History");
        speechModeEnable= new CheckMenuItem("Enable/Disable Speech Mode");
        userManualMenuItem= new MenuItem("Help");
        aboutMenuItem= new MenuItem("About");
	    
	    exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent e) {
	        	voiceBrowserObj.closeBrowser();
	        }
	    });
	    menuButton.getItems().addAll(saveMenuItem, loadMenuItem, showStatusBarMenuItem, showBookmarkMenuItem, showHistoryMenuItem, speechModeEnable, userManualMenuItem, aboutMenuItem, exitMenuItem);
	    
	    
	    
	 // align all of the navigation widgets in a horizontal toolbar.
	    final HBox navPane = new HBox();
	    navPane.setPadding(new Insets(5, 0, 5, 0));
	    navPane.setAlignment(Pos.CENTER);
	    navPane.getStyleClass().add("toolbar");
	    navPane.setSpacing(6);
	    //navPane.setSp
	    //navPane.getChildren().addAll(backButton);
	    //navPane.getChildren().addAll(backButton, forwardButton, chrome.getChromeLocField(), chrome.getTabManager().getTabPane(), chrome.getTabManager().getNewTabButton(), navButton);
	    navPane.getChildren().addAll(backButton, forwardButton, refreshButton, voiceBrowserObj.getAddressBarField(), navButton, bookmarkButton, homeButton, menuButton);
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
	
	
}
