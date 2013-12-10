/**
 * This command class represents the main webview initially.
 */

package com.browser.command;

import com.browser.view.TabView;

public class ScreenCommand implements Command {

	@Override
	public void execute() {
		TabView.getCurrentViewController()
				.getTagHandler()
				.initialiseViews(
						0,
						0,
						TabView.getCurrentViewController()
								.getBrowserWindowView().browser.getWidth(),
						TabView.getCurrentViewController()
								.getBrowserWindowView().browser.getHeight());
		int startX = 0;
		int startY = 0;
		int endX = (int) TabView.getCurrentViewController()
				.getBrowserWindowView().browser.getWidth();
		int endY = (int) TabView.getCurrentViewController()
				.getBrowserWindowView().browser.getHeight();
		TabView.getCurrentViewController().drawLine(startX, startY, startX,
				endY);
		TabView.getCurrentViewController().drawLine(startX, startY, endX,
				startY);
		TabView.getCurrentViewController().drawLine(endX, endY, endX, startY);
		TabView.getCurrentViewController().drawLine(endX, endY, startX, endY);
	}

}
