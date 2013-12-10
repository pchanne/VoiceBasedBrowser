package com.browser.command;

import com.browser.view.TabView;

public class AddBookMarkCommand implements Command{

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar().getAddBookmarkToModelButton().fire();
	}

}
