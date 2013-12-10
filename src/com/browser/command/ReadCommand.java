/**
 *  This command class represents the functionality
 *   to read the title within the selected view.
 */

package com.browser.command;

import com.browser.helper.SpeechHelper;

public class ReadCommand implements Command {

	@Override
	public void execute() {
		if (SpeechHelper.readCounter == 0) {
			SpeechHelper.readCounter++;
			SpeechHelper.speechReaderTask.start(); // starts the reader task.
		} else {
			System.out.println("restarted");
			SpeechHelper.speechReaderTask.restart(); // restarts the reader
														// task.
		}

	}

}
