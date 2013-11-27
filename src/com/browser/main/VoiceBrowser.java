package com.browser.main;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.DocumentFragment;

import com.browser.controller.ViewController;
import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;
import com.browser.reader.FileReader;
import com.browser.view.BrowserTabBarView;
import com.browser.view.MenuBarView;
import com.browser.view.SideBarView;
import com.browser.view.TabView;
import com.browser.view.ToolbarView;
import com.sun.glass.events.MouseEvent;

import javafx.application.Application;
import javafx.application.Platform;
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

	// private TextField addressBarField = new TextField(); // URL location
	private Scene scene;
	// private BrowserWindow browserWindow;
	public String command = null;
	private BorderPane mainLayout = new BorderPane();
	BookmarkModel bookmarkModel;
	public String website = null;
	public ViewController viewController;
	private static SideBarView sidebarViewObj;

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

		viewController = new ViewController();
		bookmarkModel = new BookmarkModel();
		//sidebarViewObj = new SideBarView();
		stage.setTitle("Voice Based Browser");
		// browserWindow = new BrowserWindow();
		/*
		 * setAddressbarField(viewController.getBrowserWindowView().DEFAULT_HOME)
		 * ; addressBarField.setStyle("-fx-font-size: 20;");
		 * addressBarField.setPromptText("Where do you want to go today?");
		 * addressBarField.setTooltip(new Tooltip("Enter a location"));
		 * 
		 * addressBarField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		 * public void handle(KeyEvent keyEvent) { if
		 * (keyEvent.getCode().equals(KeyCode.ENTER)) { try {
		 * getVoiceBrowser().navTo(addressBarField.getText()); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } // System.out.println("in actionlistener"); }
		 * } });
		 * 
		 * browserWindow.getView().getEngine().getLoadWorker().stateProperty()
		 * .addListener(new ChangeListener<State>() { public void
		 * changed(ObservableValue ov, State oldState, State newState) {
		 * System.out.println("blah blah ++++++++++++++++++++++++++++"); if
		 * (newState == State.RUNNING) {
		 * addressBarField.setText(browserWindow.getView()
		 * .getEngine().getLocation()); }
		 * 
		 * if(newState== State.SUCCEEDED) {
		 * System.out.println("Page "+browserWindow.getView()
		 * .getEngine().getLocation()+" loaded"); }
		 * 
		 * } });
		 */

		viewController.reflectURLChange();

		// HBox.setHgrow(addressBarField, Priority.ALWAYS);

		// set up main Layout
		mainLayout.setTop(viewController.getToolBar().CreateNavToolbar());
		mainLayout.setCenter(viewController.getBrowserWindowView().getBrowser());
		//mainLayout.setLeft(sidebarViewObj.getSideBar());
		
		// initialize the sidebar once and for all!!!
		SideBarView.initSideBar();

		/*
		 * MenuBarView menuBar = new MenuBarView(this.getVoiceBrowser(), this);
		 * 
		 * 
		 * 
		 * VBox vbox_for_menubar_toolbar = new VBox();
		 * vbox_for_menubar_toolbar.getChildren
		 * ().addAll(menuBar.createMenuBar(),
		 * ToolbarView.CreateNavToolbar(this));
		 * 
		 * mainLayout.setTop(vbox_for_menubar_toolbar);
		 */

		// setBookmarkItems();

		// mainLayout.setTop(ToolbarView.CreateNavToolbar(this));

		// SpeechCommands.CommandSpoken(this);
		// add an overlay layer over the main layout for effects and status
		// messages.
		final AnchorPane overlayLayer = new AnchorPane();
		final StackPane overlaidLayout = new StackPane();
		overlaidLayout.getChildren().addAll(mainLayout, overlayLayer);
		overlayLayer.setPickOnBounds(false);

		BrowserTabBarView browserTabPane = new BrowserTabBarView();
		TabView newTab = new TabView();
		//n1.setContent(overlaidLayout);
		browserTabPane.getBrowserTabHolder().getTabs().add(newTab);

		// create scene from overlaidLayout
		scene = new Scene(browserTabPane.getBrowserTabHolder(), 1020, 650);
		
		// create scene from overlaidLayout
		//scene = new Scene(overlaidLayout, 1020, 650);

		/*
		 * ToolbarView.getAddBookmarkButton().setOnAction( new
		 * EventHandler<ActionEvent>() {
		 * 
		 * public void handle(ActionEvent arg0) { // TODO Auto-generated method
		 * stub // get link in address bar and set it to url label of //
		 * bookmark pop window
		 * 
		 * ToolbarView.getBookmarkStage().show();
		 * 
		 * //
		 * System.out.println(browserWindow.getView().getEngine().getTitle());
		 * 
		 * ToolbarView.setBookmarkTitleText(viewController.getBrowserWindowView()
		 * .getView().getEngine().getTitle());
		 * ToolbarView.setBookmarkUrlText(addressBarField .getText());
		 * 
		 * }
		 * 
		 * });
		 * 
		 * ToolbarView.getAddBookmarkToModelButton().setOnAction( new
		 * EventHandler<ActionEvent>() {
		 * 
		 * public void handle(ActionEvent arg0) { // TODO Auto-generated method
		 * stub // add book mark to the model Bookmark bookmark = new
		 * Bookmark(); bookmark.setBookmarkName(ToolbarView.getBookmarkTitle());
		 * bookmark.setBookmarkURL(ToolbarView.getBookmarkURL());
		 * 
		 * bookmarkModel.addBookmark(bookmark); setBookmarkItems();
		 * ToolbarView.getBookmarkStage().close();
		 * 
		 * }
		 * 
		 * });
		 */

		stage.setScene(scene);

		stage.show();

	}

	/*
	 * public void setBookmarkItems() {
	 * 
	 * Bookmark googleBookmark= new Bookmark();
	 * googleBookmark.setBookmarkName("google");
	 * googleBookmark.setBookmarkURL("www.google.co.in");
	 * 
	 * Bookmark yahooBookmark= new Bookmark();
	 * yahooBookmark.setBookmarkName("yahoo");
	 * yahooBookmark.setBookmarkURL("www.yahoo.in");
	 * 
	 * 
	 * bookmarkModel.addBookmark(googleBookmark);
	 * bookmarkModel.addBookmark(yahooBookmark);
	 * 
	 * 
	 * 
	 * ToolbarView.getShowBookmarkMenuItem().getItems().removeAll(
	 * ToolbarView.getShowBookmarkMenuItem().getItems());
	 * 
	 * ArrayList<Bookmark> bookmarkList = bookmarkModel.getBookmarkList();
	 * 
	 * for (final Bookmark bookmark : bookmarkList) { MenuItem bookmarkMenuItem
	 * = new MenuItem(bookmark.getBookmarkName());
	 * 
	 * bookmarkMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	 * 
	 * public void handle(ActionEvent arg0) { // TODO Auto-generated method stub
	 * 
	 * // page should load url
	 * 
	 * try { getVoiceBrowser().navTo(bookmark.getBookmarkURL()); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * }
	 * 
	 * });
	 * 
	 * 
	 * 
	 * ToolbarView.getShowBookmarkMenuItem().getItems().add(bookmarkMenuItem);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

	/*
	 * public TextField getAddressBarField() { return addressBarField; }
	 */

	/*
	 * public BrowserWindow getVoiceBrowser() { return
	 * browserWindow.getBrowser(); }
	 */

	// public void speechTest(final String Command) {
	//
	//
	// System.out.println("command received is:" + Command);
	//
	// Platform.runLater(new Runnable() {
	//
	// @Override
	// public void run() {
	// String selection;
	// FileReader objTemp = new FileReader();
	// selection= (String) BrowserWindow.webEngine
	// .executeScript("window.getSelection().toString()");
	// System.out.println("selected text is :  -----------------------------------------------   "
	// + selection);
	// try {
	// objTemp.ReadTemp(selection);
	// } catch (IOException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// String s2 = BrowserWindow.webEngine.getTitle();
	// System.out.println("title: " + s2);
	// DocumentFragment s1 = (DocumentFragment) BrowserWindow.webEngine
	// .executeScript("window.getSelection().getRangeAt().cloneContents()");
	//
	// System.out.println("text content : " + s1.getChildNodes().getLength());
	// for(int i = 0;i<s1.getChildNodes().getLength();i++)
	// {
	// System.out.println("Tag Name: "+s1.getChildNodes().item(i).getPrefix());
	// System.out.println("Text Content: "+s1.getChildNodes().item(i));
	// }
	// // System.out.println("text content2 : " + s1.getChildNodes().item(0));
	// // System.out.println("text content : " + s1.getChildNodes().item(1));
	//
	//
	// // System.out.println("UI Thread:"+Thread.currentThread().getName());
	// // System.out.println("inside run");
	// // System.out.println("BWObject:"+BrowserWindow.webEngine);
	// website = Command.replace(".dot", ".");
	// website = website.replace(" ", "");
	// System.out.println("Website said : "+website);
	// if(website.equalsIgnoreCase("yahoo.com"))
	// {
	// System.out.println("Website identified and set as the addressbar field ");
	// getAddressBarField().setText(website);
	// }
	// if(Command.equalsIgnoreCase("go")){
	// try {
	// getVoiceBrowser().navTo(getAddressBarField().getText());
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// //BrowserWindow.webEngine.load("www.yahoo.com");
	// //getAddressBarField().setText("www.yahoo.com");
	//
	// //ToolbarView.backButton.setDisable(true);
	// }
	// });
	//
	// //ToolbarView.getAddBookmarkButton().fire();
	// //ToolbarView.getBackButton().fire();
	// //getVoiceBrowser().navTo(Command);
	//
	// }

	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;

	}

	/**
	 * @param scene
	 *            the scene to set
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	/*
	 * private void setAddressbarField(String loc){
	 * addressBarField.setText(loc); }
	 */

}
