/**
 * This command class represents the functionality
 *  to navigate to the link specified within the selected view.
 */

package com.browser.command;

import java.io.IOException;

import com.browser.view.TabView;

public class NavigateCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTagHandler().deselectAllLinks();
		TabView.getCurrentViewController().getTagHandler().deselectAllHeaders();
		TabView.getCurrentViewController().getTagHandler().deselectAllText();
		try {
			TabView.getCurrentViewController()
					.getBrowserWindowView()
					.navTo(TabView.getCurrentViewController().getTagHandler()
							.getUrlFromCurrentLinkTaginCurrentView());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
