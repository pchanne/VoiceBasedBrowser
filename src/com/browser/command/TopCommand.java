/**
 * This command class represents the top
 *  view of the selected area or the webview.
 */

package com.browser.command;

import com.browser.view.TabView;

public class TopCommand implements Command {

	@Override
	public void execute() {
		int startX = (int) TabView.getCurrentViewController().getTagHandler()
				.getTopViewXOrigin();
		int startY = (int) TabView.getCurrentViewController().getTagHandler()
				.getTopViewYOrigin();
		int endX = (int) (TabView.getCurrentViewController().getTagHandler()
				.getTopViewXOrigin() + TabView.getCurrentViewController()
				.getTagHandler().getTopViewWidth());
		int endY = (int) (TabView.getCurrentViewController().getTagHandler()
				.getTopViewYOrigin() + TabView.getCurrentViewController()
				.getTagHandler().getTopViewHeight());
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
								.getTopViewXOrigin(),
						TabView.getCurrentViewController().getTagHandler()
								.getTopViewYOrigin(),
						TabView.getCurrentViewController().getTagHandler()
								.getTopViewWidth(),
						TabView.getCurrentViewController().getTagHandler()
								.getTopViewHeight());

	}

}
