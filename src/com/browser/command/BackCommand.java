/**
 *  This command class represents the functionality
 * to navigate to the previous webpage from the history.
 */

package com.browser.command;

import com.browser.view.TabView;

public class BackCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar().getBackButton()
				.fire();

	}

}
