/**
 * This is a Task (background thread) which starts on enabling 
speech mode. Once enabled, it continously listens to speech commands till
 * it is disabled.
 */
package com.browser.speech;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import com.browser.helper.SpeechHelper;
import com.browser.main.VoiceBrowser;

public class SpeechRecognitionTask extends Service<Void> {

	String speechCommand = null;
	String website;
	boolean SpeechEnabled = true;
	private SpeechHelper speechHelper;

	public SpeechRecognitionTask() {
		speechHelper = new SpeechHelper();
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				while (SpeechEnabled) {
					if (isCancelled()) {
						break;
					}
					speechCommand = SpeechCommands.CommandSpoken();
					VoiceBrowser.logger.info("Speech Command received:"
							+ speechCommand);
					speechHelper.speechTest(speechCommand);

					Thread.sleep(100);

				}

				return null;
			}
		};
	}

}
