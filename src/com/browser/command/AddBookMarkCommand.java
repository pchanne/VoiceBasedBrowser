/**
 *  This command class represents the functionality
 *   to add bookmarks to the browser.
 */

package com.browser.command;

import com.browser.view.TabView;

public class AddBookMarkCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar()
				.getAddBookmarkToModelButton().fire();
	}

}
