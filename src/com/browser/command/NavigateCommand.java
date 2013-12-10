package com.browser.command;

import java.io.IOException;

import com.browser.view.TabView;

public class NavigateCommand implements Command{

	@Override
	public void execute() {
		TabView.getCurrentViewController().getTagHandler().deselectAllLinks();
		TabView.getCurrentViewController().getTagHandler().deselectAllHeaders();
		TabView.getCurrentViewController().getTagHandler().deselectAllText();
		System.out.println("current link "+TabView.getCurrentViewController().getTagHandler().getUrlFromCurrentLinkTaginCurrentView());
		try {
			TabView.getCurrentViewController().getBrowserWindowView().navTo(TabView.getCurrentViewController().getTagHandler().getUrlFromCurrentLinkTaginCurrentView());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
