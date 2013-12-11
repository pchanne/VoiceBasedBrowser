/**
 * This test case is used to check the back functionality
 **/
package com.browser.test;

import javafx.scene.web.WebView;
import junit.framework.Assert;
import org.junit.Test;
import com.browser.controller.BrowserWindow;

public class BackCommandTest {

	BrowserWindow browserWindow;

	WebView view;

	@Test
	public void test() {

		browserWindow = new BrowserWindow("string");
		browserWindow.getHistory().executeNavigation("www.yahoo.com");
		browserWindow.getHistory().executeNavigation("www.google.com");
		String backString = browserWindow.getHistory().requestNavBack();
		Assert.assertEquals("www.yahoo.com", backString);

	}

}
