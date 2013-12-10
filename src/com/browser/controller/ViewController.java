/**
 * This is a controller class which handles all the events of a single tab.
 */
package com.browser.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.browser.helper.SmartNotes;
import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;
import com.browser.reader.FileReader;
import com.browser.speech.SpeechRecognitionTask;
import com.browser.view.BrowserTabBarView;
import com.browser.view.SideBarView;
import com.browser.view.TabToolbarView;
import com.browser.view.TabView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class ViewController {

	private TabToolbarView tabToolBar;
	private BrowserWindow browserWindowView;
	private FileReader textReader;
	private SmartNotes smartNoteObj;
	private TagHandler tagHandler;

	public TagHandler getTagHandler() {
		return tagHandler;
	}

	public void setTagHandler(TagHandler tagHandler) {
		this.tagHandler = tagHandler;
	}

	public TabToolbarView getTabToolBar() {
		return tabToolBar;
	}

	public void setTabToolBar(TabToolbarView tabToolBar) {
		this.tabToolBar = tabToolBar;
	}

	public SmartNotes getSmartNoteObj() {
		return smartNoteObj;
	}

	public void setSmartNoteObj(SmartNotes smartNoteObj) {
		this.smartNoteObj = smartNoteObj;
	}

	public FileReader getTextReader() {
		return textReader;
	}

	public void setTextReader(FileReader textReader) {
		this.textReader = textReader;
	}

	public BrowserWindow getBrowserWindowView() {
		return browserWindowView;
	}

	public void setBrowserWindowView(BrowserWindow browserWindowView) {
		this.browserWindowView = browserWindowView;
	}

	public ViewController() {
		// sTask = new SpeechRecognitionTask();
		EventHandler<ActionEvent> backActionEvent = new BackActionEvent();
		EventHandler<ActionEvent> goActionEvent = new GoActionEvent();
		EventHandler<KeyEvent> goActionEventOnEnter = new GoActionEventOnEnter();
		EventHandler<ActionEvent> forwardActionEvent = new ForwardActionEvent();
		EventHandler<ActionEvent> refreshActionEvent = new RefreshActionEvent();
		EventHandler<ActionEvent> bookmarkActionEvent = new BookmarkActionEvent();
		EventHandler<ActionEvent> bookmarkToModelActionEvent = new BookmarkToModelActionEvent();
		EventHandler<ActionEvent> speechActionEvent = new SpeechActionEvent();
		EventHandler<ActionEvent> exitActionEvent = new ExitActionEvent();
		EventHandler<ActionEvent> addTabActionEvent = new AddTabActionEvent();
		EventHandler<ActionEvent> homeActionEvent = new HomeActionEvent();

		textReader = new FileReader();
		smartNoteObj = new SmartNotes();

		this.tabToolBar = new TabToolbarView(backActionEvent, goActionEvent,
				goActionEventOnEnter, forwardActionEvent, refreshActionEvent,
				bookmarkActionEvent, bookmarkToModelActionEvent,
				speechActionEvent, exitActionEvent, addTabActionEvent,
				homeActionEvent);
		this.browserWindowView = new BrowserWindow();

		tagHandler = new TagHandler(browserWindowView.webEngine);

	}

	// Reflects url of a webpage in address bar of currently opened browser when
	// web page is loaded
	public void reflectURLChange() {

		browserWindowView.getView().getEngine().getLoadWorker().stateProperty()
				.addListener(new ChangeListener<State>() {
					public void changed(ObservableValue ov, State oldState,
							State newState) {

						if (newState == State.RUNNING) {
							tabToolBar.getAddressBarField().setText(
									browserWindowView.getView().getEngine()
											.getLocation());
						}
						if (newState == State.SUCCEEDED) {
							// System.out.println("Page "+
							// browserWindowView.getView().getEngine().getLocation()
							// + " loaded");
							browserWindowView.getHistory().executeNav(
									browserWindowView.getView().getEngine()
											.getLocation());
							// System.out.println("items list:"+browserWindowView.getHistory().getItems());
						}
					}
				});

	}

	public void perfomGoAction() {

		tabToolBar.getNavButton().onActionProperty()
				.set(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent actionEvent) {
						try {
							browserWindowView.navTo(tabToolBar
									.getAddressBarField().getText());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
	}

	// Event handler for back button
	public class BackActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			if (browserWindowView.getHistory().canNavBack()) {
				try {
					System.out.println("navigating back......");
					browserWindowView.navTo(browserWindowView.getHistory()
							.requestNavBack());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Event handler for back button
	public class ForwardActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			if (browserWindowView.getHistory().canNavForward()) {
				try {
					browserWindowView.navTo(browserWindowView.getHistory()
							.requestNavForward());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
	}

	// Event handler for Go button
	public class GoActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			try {
				browserWindowView.navTo(tabToolBar.getAddressBarField()
						.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// Event handler for refresh button
	public class RefreshActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			try {
				browserWindowView.navTo(tabToolBar.getAddressBarField()
						.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// Event handler for enter pressed for navigation
	public class GoActionEventOnEnter implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent keyEvent) {
			if (keyEvent.getCode().equals(KeyCode.ENTER)) {
				try {
					browserWindowView.navTo(tabToolBar.getAddressBarField()
							.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	// Event handler for bookmark button
	public class BookmarkActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			tabToolBar.getBookmarkStage().show();

			tabToolBar.setBookmarkTitleText(browserWindowView.getView()
					.getEngine().getTitle());
			tabToolBar.setBookmarkUrlText(tabToolBar.getAddressBarField()
					.getText());

		}
	}

	// Event handler for add button on bookmark dialog
	public class BookmarkToModelActionEvent implements
			EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			Bookmark bookmark = new Bookmark();
			bookmark.setBookmarkName(tabToolBar.getBookmarkTitle());
			bookmark.setBookmarkURL(tabToolBar.getBookmarkURL());

			TabViewController.getBookmarkModel().addBookmark(bookmark);
			setBookmarkItems();
			tabToolBar.getBookmarkStage().close();

		}
	}

	// reflects bookmarked website on menu button of currently openend browser
	// whenever bookmark is added
	public void setBookmarkItems() {
		((MenuButton) tabToolBar.getNavPane().getChildren().get(10)).getItems()
				.remove(0);
		Menu menu = new Menu("Bookmarks");
		for (final Bookmark bookmark : TabViewController.getBookmarkModel()
				.getBookmarkList()) {

			MenuItem bookmarkMenuItem = new MenuItem(bookmark.getBookmarkName());
			bookmarkMenuItem.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent arg0) {
					// page should load url
					try {
						browserWindowView.navTo(bookmark.getBookmarkURL());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			menu.getItems().add(bookmarkMenuItem);
		}
		((MenuButton) tabToolBar.getNavPane().getChildren().get(10)).getItems()
				.add(0, menu);

	}

	// Event handler for speech button
	public class SpeechActionEvent implements EventHandler<ActionEvent> {
		int SpeechCounter = 0;

		@Override
		public void handle(ActionEvent actionEvent) {

			if (TabView.isSpeechMode()) {
				tabToolBar.setSpeechMode(false);
				TabView.setSpeechMode(false);
				tabToolBar.createSpeechButtonHelper("Micro-off-icon",
						"Enable speech mode");
				TabViewController.sTask.cancel();
			} else {
				tabToolBar.setSpeechMode(true);
				TabView.setSpeechMode(true);
				tabToolBar.createSpeechButtonHelper("Micro-icon",
						"Disable speech mode");
				if (SpeechCounter == 0) {
					SpeechCounter++;
					TabViewController.sTask.start();
				} else {
					TabViewController.sTask.restart();
				}
			}
		}

	}

	// Event handler for exit
	public class ExitActionEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {
			System.exit(0);
		}
	}

	// Event handler for adding new tab
	public class AddTabActionEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {
			TabView addTab = new TabView();
			BrowserTabBarView.getBrowserTabHolder().getTabs().add(addTab);
		}
	}

	// Event handler for home button
	public class HomeActionEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {
			try {
				browserWindowView.navTo(TabToolbarView.DEFAULT_HOME);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// adds block around selected view on a web page
	public void drawLine(int startPointX, int startPointY, int endPointX,
			int endPointY) {
		Path path = new Path();
		path.getElements().add(new MoveTo(startPointX, startPointY));
		path.getElements().add(new LineTo(endPointX, endPointY));
		path.setStrokeWidth(10);
		path.setStroke(Color.BLACK);

		if (browserWindowView.getChildren().contains(path)) {
			browserWindowView.getChildren().remove(path);
		}
		browserWindowView.getChildren().add(path);
	}
}
