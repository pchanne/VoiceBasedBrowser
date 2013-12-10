/**
 * This command class represents the functionality
 *  to refresh the web page currently opened.
 */

package com.browser.command;

import java.io.IOException;

import com.browser.view.TabView;

public class RefreshCommand implements Command {

	@Override
	public void execute() {
		try {
			TabView.getCurrentViewController()
					.getBrowserWindowView()
					.navTo(TabView.getCurrentViewController().getTabToolBar()
							.getAddressBarField().getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
