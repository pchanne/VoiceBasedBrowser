/**
 * This command class represents the functionality
 *  to navigate to the default_home page of our browser.
 */

package com.browser.command;

import com.browser.view.TabView;

public class HomeCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar().getHomeButton()
				.fire();
	}

}
