package com.browser.command;

import com.browser.view.TabView;

public class BookMarkCommand implements Command{

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTabToolBar().getAddBookmarkButton().fire();
		
	}

}
