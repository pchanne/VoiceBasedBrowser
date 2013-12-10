package com.browser.command;

import com.browser.view.TabView;

public class HomeCommand implements Command{

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar().getHomeButton().fire();
	}

}
