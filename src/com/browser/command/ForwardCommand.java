/**
 * This command class represents the functionality to
 *  navigate to the next webpage from the history.
 */

package com.browser.command;

import com.browser.view.TabView;

public class ForwardCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar().getForwardButton()
				.fire();

	}

}
