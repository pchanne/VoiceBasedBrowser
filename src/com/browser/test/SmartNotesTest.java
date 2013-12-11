/**
 * This test case is used to check the SmartNotes functionality
 **/
package com.browser.test;

import org.junit.Assert;
import org.junit.Test;
import com.browser.helper.SmartNotes;

public class SmartNotesTest {

	SmartNotes notes;

	@Test
	public void test() {

		notes = new SmartNotes();
		String data = "";
		data += "Good Morning! This is test for SmartNotes. VoiceBrowser saved data. ";

		String str = notes.copySelectedTextDummy("Good Morning!");
		str = notes.copySelectedTextDummy("This is test for SmartNotes.");
		str = notes.copySelectedTextDummy("VoiceBrowser saved data.");

		Assert.assertEquals(data, str);

	}

}
