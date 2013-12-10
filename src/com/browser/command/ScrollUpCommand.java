package com.browser.command;

import com.browser.view.TabView;

public class ScrollUpCommand implements Command{

	@Override
	public void execute() {
		TabView.getCurrentViewController().getBrowserWindowView().webEngine.executeScript("window.scrollBy(0,-500);");
	}

}
