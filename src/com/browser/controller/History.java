/**
 * This class is responsible maintaining history for current browsing session.
 */

package com.browser.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;

public class History {
	private static final int MAX_HISTORY_SIZE = 100; // max number of locations
														// we will store in the
														// history.
	private final ObservableList<String> items = FXCollections
			.observableArrayList();
	private int pointer = 0; // index into the history list for the currently
								// displayed page from the history.
	private Integer navPointer = null; // index into the history list for a new
										// page to be displayed page in the
										// history.
	private final BrowserWindow browser; // browser window (contains WebView)
											// managed by this history.
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
		if (canNavBack()) {
			navPointer = pointer - 1;
		}

		return items.get(navPointer);
	}

	/*
	 * Performs navigation to appropriate website according to browser history
	 * on click of back and forward buttons
	 */
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

}
