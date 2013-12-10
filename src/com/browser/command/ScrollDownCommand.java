/**
 * This command class represents the functionality
 *  to scroll down the current webpage.
 */

package com.browser.command;

import com.browser.view.TabView;

public class ScrollDownCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController().getBrowserWindowView().webEngine
				.executeScript("window.scrollBy(0,500);");
	}

}
