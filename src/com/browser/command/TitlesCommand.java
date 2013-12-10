package com.browser.command;

import com.browser.view.TabView;

public class TitlesCommand implements Command{

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTagHandler().deselectAllLinks();
		TabView.getCurrentViewController().getTagHandler().deselectAllHeaders();
		TabView.getCurrentViewController().getTagHandler().deselectAllText();
		TabView.getCurrentViewController().getTagHandler().highlightAllHeadersinCurrentView();
	}

}
