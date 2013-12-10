/**
 *  This command class represents the functionality
 * to open a dialog box for adding a bookmark to the browser.
 */

package com.browser.command;

import com.browser.view.TabView;

public class BookMarkCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar()
				.getAddBookmarkButton().fire();

	}

}
