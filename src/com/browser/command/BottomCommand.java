/**
 * This command class represents the bottom
 * view of the selected area or the webview.
 */

package com.browser.command;

import com.browser.view.TabView;

public class BottomCommand implements Command {

	@Override
	public void execute() {
		int startX = (int) TabView.getCurrentViewController().getTagHandler()
				.getBottomViewXOrigin();
		int startY = (int) TabView.getCurrentViewController().getTagHandler()
				.getBottomViewYOrigin();
		int endX = (int) (TabView.getCurrentViewController().getTagHandler()
				.getBottomViewXOrigin() + TabView.getCurrentViewController()
				.getTagHandler().getBottomViewWidth());
		int endY = (int) (TabView.getCurrentViewController().getTagHandler()
				.getBottomViewYOrigin() + TabView.getCurrentViewController()
				.getTagHandler().getBottomViewHeight());
		TabView.getCurrentViewController().drawLine(startX, startY, startX,
				endY);
		TabView.getCurrentViewController().drawLine(startX, startY, endX,
				startY);
		TabView.getCurrentViewController().drawLine(endX, endY, endX, startY);
		TabView.getCurrentViewController().drawLine(endX, endY, startX, endY);
		TabView.getCurrentViewController()
				.getTagHandler()
				.initialiseViews(
						TabView.getCurrentViewController().getTagHandler()
								.getBottomViewXOrigin(),
						TabView.getCurrentViewController().getTagHandler()
								.getBottomViewYOrigin(),
						TabView.getCurrentViewController().getTagHandler()
								.getBottomViewWidth(),
						TabView.getCurrentViewController().getTagHandler()
								.getBottomViewHeight());

	}

}
