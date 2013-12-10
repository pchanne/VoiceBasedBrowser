package com.browser.command;

import com.browser.view.TabView;

public class SmartNotesCommand implements Command{

	@Override
	public void execute() {
		TabView.getCurrentViewController().getSmartNoteObj().copySelectedText(TabView.getCurrentViewController().getTagHandler().getTextFromCurrentTextinCurrentView());
		
	}

}
