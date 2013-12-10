/**
 * This class uses the Sphinx-4
 * endpointer, which automatically segments incoming audio
 * into utterances and silences.
 */
package com.browser.speech;

import com.browser.controller.BrowserWindow;
import com.browser.main.VoiceBrowser;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechCommands {

	private static BrowserWindow browserWindow;

	public static String CommandSpoken() {
		ConfigurationManager cm;
		String resultText = null;

		cm = new ConfigurationManager(
				SpeechCommands.class.getResource("speechcommands.config.xml"));

		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();

		// start the microphone or deallocate the resources and log the message
		// if this is not possible
		Microphone microphone = (Microphone) cm.lookup("microphone");
		if (!microphone.startRecording()) {
			VoiceBrowser.logger.error("Cannot start microphone.");
			recognizer.deallocate();
		}
		Result result = recognizer.recognize();

		if (result != null) {
			resultText = result.getBestFinalResultNoFiller();
			VoiceBrowser.logger.info("Yout said:" + resultText);
		} else {
			VoiceBrowser.logger.info("I can't hear what you said.\n");
		}
		return resultText;

	}
}
