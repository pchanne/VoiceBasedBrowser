package com.browser.reader;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class formTTS {

	VoiceManager voiceMgr; 

	formTTS(String input)
	{
		voiceMgr = VoiceManager.getInstance();
		Voice voice = voiceMgr.getVoice("kevin16");
		voice.allocate();
		voice.speak(input);
		
	}
	
}



