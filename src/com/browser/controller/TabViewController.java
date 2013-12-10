/**
 * This is a controller class which controls all the browsers tabs.
 */
package com.browser.controller;

import com.browser.model.BookmarkModel;
import com.browser.speech.SpeechRecognitionTask;
import com.browser.view.BrowserTabBarView;
import com.browser.view.SideBarView;
import com.browser.view.TabToolbarView;
import com.browser.view.TabView;

public class TabViewController {
	private BrowserTabBarView browserTabBarView;
	private TabView tab;
	private static BookmarkModel bookmarkModel;
	public static SpeechRecognitionTask sTask;

	public static BookmarkModel getBookmarkModel() {
		return bookmarkModel;
	}

	public static void setBookmarkModel(BookmarkModel bookmarkModel) {
		TabViewController.bookmarkModel = bookmarkModel;
	}

	public BrowserTabBarView getBrowserTabBarView() {
		return browserTabBarView;
	}

	public void setBrowserTabBarView(BrowserTabBarView browserTabBarView) {
		this.browserTabBarView = browserTabBarView;
	}

	public TabViewController() {

		bookmarkModel = new BookmarkModel();
		sTask = new SpeechRecognitionTask();
		tab = new TabView(); // creating first tab when browser opens.
		browserTabBarView = new BrowserTabBarView(); // container for holding
														// all the tabs.
		SideBarView.initSideBar(); // static sidebar that is shared among all
									// the tabs
		browserTabBarView.getBrowserTabHolder().getTabs().add(tab); // adding
																	// first tab
																	// to
																	// container

	}
}
