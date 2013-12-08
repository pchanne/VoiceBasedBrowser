package junitTest;

import static org.junit.Assert.*;

import junit.framework.Assert;

import org.junit.Test;

import com.browser.controller.BrowserWindow;
import com.browser.helper.SmartNotes;
import com.browser.view.History;


/*
 * This test case is used to check the SmartNotes functionality
 */
public class SmartNotesTest {

	SmartNotes notes;

	@Test
	public void test() {

		notes = new SmartNotes();
		String data = "";
		data += "Good Morning! This is test for SmartNotes. VoiceBrowser saved data. ";

		String str = notes.copySelectedTextDumy("Good Morning!");
		str = notes.copySelectedTextDumy("This is test for SmartNotes.");
		str = notes.copySelectedTextDumy("VoiceBrowser saved data.");
		System.out.println(str);

		Assert.assertEquals(data, str);

	}

}
