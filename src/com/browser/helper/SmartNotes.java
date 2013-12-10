/**
 * This class handles the SmartNotes feature
 */
package com.browser.helper;

import java.util.ArrayList;

import com.browser.main.VoiceBrowser;
import com.browser.view.SideBarView;

public class SmartNotes {
	private ArrayList<String> copyNotes;

	// Default constructor
	public SmartNotes() {
		copyNotes = new ArrayList<String>();
	}

	/*
	 * This method will copy the selected text, add it to the arraylist and
	 * display all the content in the SmartNotes text area.
	 */
	public void copySelectedText(String textForNotes) {
		copyNotes.add(textForNotes);
		for (int i = 0; i < copyNotes.size(); i++) {
			VoiceBrowser.logger.info(copyNotes.get(i) + "\t");
		}
		SideBarView.setTextAreaText(copyNotes);
	}
	
	//Dummy constructor for testing the functionality
	public String copySelectedTextDummy(String textForNotes) {
		copyNotes.add(textForNotes);
		String data = "";
		for (int i = 0; i < copyNotes.size(); i++) {
			data += (copyNotes.get(i) + " ");
		}
		return data;
	}

}
