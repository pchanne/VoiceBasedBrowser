package com.browser.reader;

import java.io.InputStream;

//import org.htmlparser.Parser;
//import org.htmlparser.filters.CssSelectorNodeFilter;
//import org.htmlparser.util.NodeList;
//import org.htmlparser.util.ParserException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class formTTS {
	VoiceManager voiceMgr; 
	
	
	
	formTTS(String input)
	{
		
		voiceMgr = VoiceManager.getInstance();
		System.out.println("Voice Manager "+voiceMgr);
		Voice voice = voiceMgr.getVoice("kevin16");
		System.out.println("Voice "+voice);
		voice.allocate();
		voice.speak(input);
		
	}

	
}



