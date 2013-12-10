package com.browser.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebHistory;

public class History {
	private static final int MAX_HISTORY_SIZE = 100; // max number of locations we will store in the history.
	private final ObservableList<String> items = FXCollections.observableArrayList();
	private int pointer = 0; // index into the history list for the currently displayed page from the history.
	private Integer navPointer = null; // index into the history list for a new page to be displayed page in the history.
	private final BrowserWindow browser; // browser window (contains WebView) managed by this history.
	private final ContextMenu historyMenu;

	public ObservableList<String> getItems() {
		return items;
	}

	public History(BrowserWindow browser) {
		this.browser = browser;
		this.historyMenu = new ContextMenu();

	}

	public History() {
		this.browser = null;
		this.historyMenu = null;
	}

	public boolean canNavForward() {
		return pointer < items.size() - 1;
	}

	public boolean canNavBack() {
		System.out.println("num : " + items.size());
		return pointer > 0;
	}

	public String requestNav(Integer index) {
		if (index >= 0 && index <= items.size()) {
			this.navPointer = index;
		}

		return items.get(navPointer);
	}

	public String requestNavForward() {
		if (canNavForward()) {
			navPointer = pointer + 1;
		}

		return items.get(navPointer);
	}

	public String requestNavBack() {
		System.out.println("in nav back");
		if (canNavBack()) {
			navPointer = pointer - 1;
		}

		return items.get(navPointer);
	}

	public void executeNav(String newLoc) {
		historyMenu.hide();

		if (navPointer == null) { // standard navPointer.
			if (pointer < items.size() - 1) { // wipe any forward button
												// history.
				items.remove(pointer + 1, items.size());
			}
			items.add(newLoc);

			if (items.size() >= MAX_HISTORY_SIZE) {
				items.remove(0);
			}
			pointer = items.size() - 1;
		} else { // navPointer using history list.
			pointer = navPointer;
			navPointer = null;
		}
	}

	/*
	 * Dummy function to test the behavior of executeNav function
	 */
	public void executeNavigation(String newLoc) {

		System.out.println("inside navigation");
		if (navPointer == null) { // standard navPointer.
			if (pointer < items.size() - 1) { // wipe any forward button
												// history.
				items.remove(pointer + 1, items.size());
			}
			items.add(newLoc);
			if (items.size() >= MAX_HISTORY_SIZE) {
				items.remove(0);
			}
			pointer = items.size() - 1;
		} else { // navPointer using history list.
			pointer = navPointer;
			navPointer = null;
		}
	}

	/**
	 * Show a history menu when the user right clicks.
	 * 
	 * @param displayNode
	 *            the node under which the history menu is to be displayed.
	 * @return a right click mouse button event handler which will show a
	 *         history context menu.
	 */
	public EventHandler<MouseEvent> createShowHistoryMouseEvent(
			final Node displayNode) {
		return new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
					createMenu().show(displayNode, Side.BOTTOM, 0, 0); 
				}
			}
		};
	}

	/**
	 * Show a history menu when the clicks a history button
	 * 
	 * @param displayNode
	 *            the node under which the history menu is to be displayed.
	 * @return an action event handler which will show a history context menu.
	 */
	public EventHandler<ActionEvent> createShowHistoryActionEvent(
			final Node displayNode) {
		return new EventHandler<ActionEvent>() {

			public void handle(ActionEvent actionEvent) {
				createMenu().show(displayNode, Side.BOTTOM, 0, 0); 																	// button).
			}
		};
	}

	/**
	 * @return a new context menu for a range of history items.
	 */
	private ContextMenu createMenu() {
		// clear the existing history menu as we will build a new one from
		// scratch.
		historyMenu.getItems().clear();

		// determine an appropriate subset range of the history list to display.
		int minIdx = Math.max(0, pointer - 6); // min range (inclusive) of
												// history items to show.
		int maxIdx = Math.min(items.size(), pointer + 4); // min range
															// (exclusive) of
															// history items to
															// show.

		// add menu items to the history list.
		for (int i = maxIdx - 1; i >= minIdx; i--) {
			final MenuItem nextMenu = createMenuItem(items.get(i), i);
			historyMenu.getItems().add(nextMenu);
			if (i == pointer) {
				nextMenu.getStyleClass().add("currentMenu");
			}
		}

		return historyMenu;
	}

	private MenuItem createMenuItem(final String loc, final int navPointer) {
		final MenuItem nextMenuItem = new MenuItem(loc);
		nextMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				try {
					browser.navTo(requestNav(navPointer));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return nextMenuItem;
	}
}
