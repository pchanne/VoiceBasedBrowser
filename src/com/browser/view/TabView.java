/**
 * This class represents single tab in a browser.
 * this class extends the tab class from javafx
 * using the tab class we build each individual tab that would be added
 * to the tab pane in the browser window
 */
package com.browser.view;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import com.browser.controller.TabViewController;
import com.browser.controller.ViewController;
import com.browser.helper.GetImagePath;
import com.browser.model.Bookmark;

public class TabView extends Tab {

	private ViewController viewController;
	private static ViewController currentViewController;

	private TextField searchBar;

	private Button findTagButton;
	private Button selectButton;
	private Pane myTabToolBarPane;
	private static boolean speechMode = false;
	private ImageView speechGraphic;
	private static GetImagePath getImgObj;

	public static ViewController getCurrentViewController() {
		return currentViewController;
	}

	public static void setCurrentViewController(
			ViewController currentViewController) {
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

	// Constructor
	/*
	 * initializes a new tab, it sets the layout of the tab as borderpane the
	 * top part contains the toolbar the left will contain the sidebar and the
	 * center contains the browser window
	 */
	public TabView() {

		viewController = new ViewController();
		myTabToolBarPane = viewController.getTabToolBar().CreateNavToolbar();

		final BorderPane tabLayout = new BorderPane();

		tabLayout.setTop(myTabToolBarPane);
		tabLayout.setCenter(viewController.getBrowserWindowView());
		viewController.reflectURLChange();

		viewController.getBrowserWindowView().getView().getEngine()
				.titleProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observableValue,
							String oldValue, String newTitle) {
						if (newTitle != null && !"".equals(newTitle)) {
							setText(newTitle);
						}
					}
				});

		searchBar = new TextField();
		findTagButton = new Button("Find Tag");
		selectButton = new Button("Select");

		HBox selectTagLayout = new HBox();

		selectTagLayout.getChildren().addAll(searchBar, findTagButton,
				selectButton);
		HBox.setHgrow(searchBar, Priority.ALWAYS);

		final VBox sideBarContainer = new VBox();

		setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// Changing speech icon on tab change based on global speechMode
				// status
				getImgObj = new GetImagePath();
				reflectSiderBar(sideBarContainer);

				// set up currently selected tab as global tab
				if (BrowserTabBarView.getBrowserTabHolder().getSelectionModel()
						.getSelectedItem().isSelected()) {
					TabView.setCurrentViewController(TabView.this.viewController);
					speechButtonHandler();

				}
				reflectBookmarks();
			}
		});

		tabLayout.setLeft(sideBarContainer);
		setContent(tabLayout);
	}

	/*
	 * Changes speechButton status across all the tabs when speech mode is
	 * changed in one of the tabs.
	 */
	public void speechButtonHandler() {

		String iconPath = null;
		if (TabView.speechMode) {
			iconPath = getImgObj.jarScan("icons.jar", "Micro-icon");
			speechGraphic = new ImageView(new Image(iconPath));
			((Button) viewController.getTabToolBar().getNavPane().getChildren()
					.get(8)).setGraphic(speechGraphic);

		} else if (!TabView.speechMode) {
			iconPath = getImgObj.jarScan("icons.jar", "Micro-off-icon");
			speechGraphic = new ImageView(new Image(iconPath));
			((Button) viewController.getTabToolBar().getNavPane().getChildren()
					.get(8)).setGraphic(speechGraphic);
		}

	}

	/*
	 * Reflects side bar changes made by on tab on all the tabs on tabswitch
	 */
	public void reflectSiderBar(VBox sideBarContainer) {

		if (sideBarContainer.getChildren()
				.contains(SideBarView.getBarDisplay())) {
			sideBarContainer.getChildren().remove(SideBarView.getBarDisplay());
		}
		sideBarContainer.getChildren().add(SideBarView.getBarDisplay());

	}

	/*
	 * Reflect bookmarks added by on one of the tab into menuButton of all the
	 * tabs.
	 */
	public void reflectBookmarks() {

		((MenuButton) viewController.getTabToolBar().getNavPane().getChildren()
				.get(10)).getItems().remove(0);

		Menu menu = new Menu("Bookmarks");
		for (final Bookmark bookmark : TabViewController.getBookmarkModel()
				.getBookmarkList()) {

			MenuItem bookmarkMenuItem = new MenuItem(bookmark.getBookmarkName());
			bookmarkMenuItem.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent arg0) {
					try {
						viewController.getBrowserWindowView().navTo(
								bookmark.getBookmarkURL());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			menu.getItems().add(bookmarkMenuItem);
		}
		((MenuButton) viewController.getTabToolBar().getNavPane().getChildren()
				.get(10)).getItems().add(0, menu);

	}

}