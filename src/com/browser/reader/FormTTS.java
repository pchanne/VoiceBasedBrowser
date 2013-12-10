/**
 * This class is used for Read functionality. It enables detection
 *  by instantiating VoiceManager of freeTTS library. 
 */
package com.browser.reader;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FormTTS {

	VoiceManager voiceMgr;

	FormTTS(String input) {
		voiceMgr = VoiceManager.getInstance();
		Voice voice = voiceMgr.getVoice("kevin16");
		voice.allocate();
		voice.speak(input);

	}

}
