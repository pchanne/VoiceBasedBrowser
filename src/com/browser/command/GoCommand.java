/**
 * This command class represents the functionality
 *  to navigate to the URL specified in the browser's address field.
 */

package com.browser.command;

import java.io.IOException;

import com.browser.view.TabView;

public class GoCommand implements Command {

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
