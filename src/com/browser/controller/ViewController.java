package com.browser.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.browser.helper.SmartNotes;
import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;
import com.browser.reader.FileReader;
import com.browser.speech.SpeechRecognitionTask;
import com.browser.view.SideBarView;
import com.browser.view.ToolbarView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ViewController {

	private ToolbarView toolBar;
	private SideBarView sideBar;
	private BrowserWindow browserWindowView;
	private BookmarkController bookMarkController;
	private BookmarkModel bookmarkModel;
	private SpeechRecognitionTask sTask;
	private FileReader textReader;
	private SmartNotes smartNoteObj;
	
	
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

	public ToolbarView getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolbarView toolBar) {
		this.toolBar = toolBar;
	}

	public SideBarView getSideBar() {
		return sideBar;
	}

	public void setSideBar(SideBarView sideBar) {
		this.sideBar = sideBar;
	}

	public BrowserWindow getBrowserWindowView() {
		return browserWindowView;
	}

	public void setBrowserWindowView(BrowserWindow browserWindowView) {
		this.browserWindowView = browserWindowView;
	}

	public BookmarkController getBookMarkController() {
		return bookMarkController;
	}

	public void setBookMarkController(BookmarkController bookMarkController) {
		this.bookMarkController = bookMarkController;
	}

	public ViewController() {
		
		sTask = new SpeechRecognitionTask(this);
		EventHandler<ActionEvent> backActionEvent = new BackActionEvent();
		EventHandler<ActionEvent> goActionEvent = new GoActionEvent();
		EventHandler<KeyEvent> goActionEventOnEnter = new GoActionEventOnEnter();
		EventHandler<ActionEvent> forwardActionEvent = new ForwardActionEvent();
		EventHandler<ActionEvent> refreshActionEvent = new RefreshActionEvent();
		EventHandler<ActionEvent> bookmarkActionEvent = new BookmarkActionEvent();
		EventHandler<ActionEvent> bookmarkToModelActionEvent = new BookmarkToModelActionEvent();
		EventHandler<ActionEvent> speechActionEvent = new SpeechActionEvent(); 
		EventHandler<ActionEvent> exitActionEvent = new ExitActionEvent();
		bookmarkModel = new BookmarkModel();
		textReader = new FileReader();
		smartNoteObj = new SmartNotes();
		
		this.toolBar = new ToolbarView(backActionEvent, goActionEvent, goActionEventOnEnter,forwardActionEvent, refreshActionEvent, bookmarkActionEvent,bookmarkToModelActionEvent,speechActionEvent, exitActionEvent);
		this.sideBar = new SideBarView();
		this.browserWindowView = new BrowserWindow();
		//this.bookMarkController = new BookmarkController(toolBar,	browserWindowView);

	}

	public void reflectURLChange() {

		browserWindowView.getView().getEngine().getLoadWorker().stateProperty()
				.addListener(new ChangeListener<State>() {
					public void changed(ObservableValue ov, State oldState,
							State newState) {
						System.out
								.println("blah blah ++++++++++++++++++++++++++++");
						if (newState == State.RUNNING) {
							toolBar.getAddressBarField().setText(
									browserWindowView.getView().getEngine()
											.getLocation());
						}

						if (newState == State.SUCCEEDED) {
							System.out.println("Page "
									+ browserWindowView.getView().getEngine()
											.getLocation() + " loaded");
						}

					}
				});

	}

	public void perfomGoAction() {

		toolBar.getNavButton().onActionProperty()
				.set(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent actionEvent) {
						try {
							browserWindowView.navTo(toolBar
									.getAddressBarField().getText());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	}

	
	public class BackActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("Back Clicked!");
			if(browserWindowView.getHistory().canNavBack()){
				try {
					System.out.println("navigating back......");
					browserWindowView.navTo(browserWindowView.getHistory().requestNavBack());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public class ForwardActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("forward Clicked!");

		}
	}
	
	public class GoActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("Go Clicked!");
			try {
				browserWindowView.navTo(toolBar
						.getAddressBarField().getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public class RefreshActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("Refresh Clicked!");
			try {
				browserWindowView.navTo(toolBar
						.getAddressBarField().getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public class GoActionEventOnEnter implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent keyEvent) {
			// TODO Auto-generated method stub
			System.out.println("Enter Pressed : Go");
			if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			try {
				browserWindowView.navTo(toolBar
						.getAddressBarField().getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		}
	}
	
	public class BookmarkActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("bookmark clicked");
			toolBar.getBookmarkStage().show();

			toolBar.setBookmarkTitleText(browserWindowView.getView().getEngine().getTitle());
			toolBar.setBookmarkUrlText(toolBar.getAddressBarField().getText());

		

		}
	}
	public class BookmarkToModelActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("bm in action");
			Bookmark bookmark = new Bookmark();
			bookmark.setBookmarkName(toolBar.getBookmarkTitle());
			bookmark.setBookmarkURL(toolBar.getBookmarkURL());

			bookmarkModel.addBookmark(bookmark);
			setBookmarkItems();
			toolBar.getBookmarkStage().close();


		}
	}
	
	public void setBookmarkItems() {
		
	toolBar.getShowBookmarkMenuItem().getItems().removeAll(toolBar.getShowBookmarkMenuItem().getItems());

		ArrayList<Bookmark> bookmarkList = bookmarkModel.getBookmarkList();
		
		for (final Bookmark bookmark : bookmarkList) {
			MenuItem bookmarkMenuItem = new MenuItem(bookmark.getBookmarkName());

			bookmarkMenuItem.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					// page should load url
					try {
						browserWindowView.navTo(bookmark.getBookmarkURL());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});		
			toolBar.getShowBookmarkMenuItem().getItems().add(bookmarkMenuItem);
		}

	}
	
	
	
	public class SpeechActionEvent implements EventHandler<ActionEvent> {
		int SpeechCounter = 0 ;
		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("Speech enabled/disabled clicked");
			if (toolBar.isSpeechMode()) {
				toolBar.setSpeechMode(false);
				toolBar.createSpeechButtonHelper("Micro-off-icon",
						"Enable speech mode");
				sTask.cancel();
			} else {
				toolBar.setSpeechMode(true);
				toolBar.createSpeechButtonHelper("Micro-icon",
						"Disable speech mode");
				if (SpeechCounter == 0) {
					SpeechCounter++;
					sTask.start();
				} else {
					sTask.restart();
				}
			}
		}
		

		}
	
	
	public class ExitActionEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.exit(0);

		}
		}
	
}
