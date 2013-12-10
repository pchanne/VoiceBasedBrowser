/**
 * This command class represents the functionality
 *  to add the selected paragraph from the webpage to the sidebar view.
 */

package com.browser.command;

import com.browser.view.TabView;

public class SmartNotesCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController()
				.getSmartNoteObj()
				.copySelectedText(
						TabView.getCurrentViewController().getTagHandler()
								.getTextFromCurrentTextinCurrentView());

	}

}
