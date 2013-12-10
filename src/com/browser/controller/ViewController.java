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
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewController {

	private TabToolbarView tabToolBar;
	private BrowserWindow browserWindowView;
	private BookmarkModel bookmarkModel;
	//private SpeechRecognitionTask sTask;
	private FileReader textReader;
	private SmartNotes smartNoteObj;
	private TagHandler tagHandler;
//	public static double currentWidth;
//	public static double currentHeight;

	
	
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
		//sTask = new SpeechRecognitionTask();
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
		
		bookmarkModel = new BookmarkModel();
		textReader = new FileReader();
		smartNoteObj = new SmartNotes();
		
		this.tabToolBar = new TabToolbarView(backActionEvent, goActionEvent, goActionEventOnEnter,forwardActionEvent, refreshActionEvent, bookmarkActionEvent,bookmarkToModelActionEvent,speechActionEvent, exitActionEvent, addTabActionEvent, homeActionEvent);
		this.browserWindowView = new BrowserWindow();

//		currentWidth = this.browserWindowView.getWidth();
//		currentHeight = this.browserWindowView.getHeight();
		tagHandler = new TagHandler(browserWindowView.webEngine);
		
	}

	public void reflectURLChange() {

		browserWindowView.getView().getEngine().getLoadWorker().stateProperty()
				.addListener(new ChangeListener<State>() {
					public void changed(ObservableValue ov, State oldState,
							State newState) {
						
						if (newState == State.RUNNING) {
							tabToolBar.getAddressBarField().setText(browserWindowView.getView().getEngine().getLocation());
						}
						if (newState == State.SUCCEEDED) {
							//System.out.println("Page "+ browserWindowView.getView().getEngine().getLocation() + " loaded");
							//System.out.println("Curr :"+browserWindowView.getView().getEngine().getLocation());
							browserWindowView.getHistory().executeNav(browserWindowView.getView().getEngine().getLocation());
							//System.out.println("items list:"+browserWindowView.getHistory().getItems());
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

	
	public class BackActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			System.out.println("Back Clicked!");
			if(browserWindowView.getHistory().canNavBack()){
				try {
					System.out.println("navigating back......");
					browserWindowView.navTo(browserWindowView.getHistory().requestNavBack());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class ForwardActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			System.out.println("forward Clicked!");
			if(browserWindowView.getHistory().canNavForward()){
				System.out.println("history "+browserWindowView.getHistory());
				try {
					browserWindowView.navTo(browserWindowView.getHistory().requestNavForward());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		

		}
	}
	
	public class GoActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("Go Clicked!");
			try {
				browserWindowView.navTo(tabToolBar.getAddressBarField().getText());
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
				browserWindowView.navTo(tabToolBar.getAddressBarField().getText());
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
			if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			try {
				browserWindowView.navTo(tabToolBar.getAddressBarField().getText());
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
			tabToolBar.getBookmarkStage().show();

			tabToolBar.setBookmarkTitleText(browserWindowView.getView().getEngine().getTitle());
			tabToolBar.setBookmarkUrlText(tabToolBar.getAddressBarField().getText());

		

		}
	}
	public class BookmarkToModelActionEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			System.out.println("bm in action");
			Bookmark bookmark = new Bookmark();
			bookmark.setBookmarkName(tabToolBar.getBookmarkTitle());
			bookmark.setBookmarkURL(tabToolBar.getBookmarkURL());

			bookmarkModel.addBookmark(bookmark);
			setBookmarkItems();
			tabToolBar.getBookmarkStage().close();


		}
	}
	
	public void setBookmarkItems() {
		
		tabToolBar.getShowBookmarkMenuItem().getItems().removeAll(tabToolBar.getShowBookmarkMenuItem().getItems());
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
			tabToolBar.getShowBookmarkMenuItem().getItems().add(bookmarkMenuItem);
		}

	}
	
	
	
	public class SpeechActionEvent implements EventHandler<ActionEvent> {
		int SpeechCounter = 0 ;
		@Override
		public void handle(ActionEvent actionEvent) {
			
			if (TabView.isSpeechMode()) {
				//System.out.println("disabling");
				tabToolBar.setSpeechMode(false);
				TabView.setSpeechMode(false);
				tabToolBar.createSpeechButtonHelper("Micro-off-icon","Enable speech mode");
				TabViewController.sTask.cancel();
			} else {
				//System.out.println("enabling");
				tabToolBar.setSpeechMode(true);
				TabView.setSpeechMode(true);
				tabToolBar.createSpeechButtonHelper("Micro-icon","Disable speech mode");
				if (SpeechCounter == 0) {
					SpeechCounter++;
					TabViewController.sTask.start();
				} else {
					TabViewController.sTask.restart();
				}
			}
		}
		

		}
	
	
	public class ExitActionEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {
			System.exit(0);
		}
		}
	
	public class AddTabActionEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent actionEvent) {
			TabView addTab = new TabView();
            BrowserTabBarView.getBrowserTabHolder().getTabs().add(addTab);
		}
	}
	
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
}
