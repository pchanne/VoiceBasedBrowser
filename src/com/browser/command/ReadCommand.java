package com.browser.command;

import com.browser.helper.SpeechHelper;

public class ReadCommand implements Command{

	@Override
	public void execute() {
		if(SpeechHelper.readCounter == 0)	
		{	
			SpeechHelper.readCounter++;
			SpeechHelper.speechReaderTask.start();
		}
	else{
		System.out.println("restarted");
			SpeechHelper.speechReaderTask.restart();
	}
		
	}

}
