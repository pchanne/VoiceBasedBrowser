package com.browser.helper;

import java.util.ArrayList;

import com.browser.view.SideBarView;

public class SmartNotes {
	private ArrayList<String> copyNotes;
	
	public SmartNotes(){
		copyNotes = new ArrayList<String>();
	}
	
	public void copySelectedText(String textForNotes){
		copyNotes.add(textForNotes);
		System.out.println("Copied Notes are :\n");
		for(int i = 0;i<copyNotes.size();i++){
			System.out.println(copyNotes.get(i)+"\t");
		}
		SideBarView.setTextAreaText(copyNotes);
	}

}
