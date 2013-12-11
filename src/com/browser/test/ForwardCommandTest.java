/**
 * This test case is used to check the forward functionality
 **/
package com.browser.test;

import javafx.scene.web.WebView;
import junit.framework.Assert;
import org.junit.Test;
import com.browser.controller.BrowserWindow;

public class ForwardCommandTest {

	BrowserWindow browserWindow;
	WebView view;

	@Test
	public void test() {

		browserWindow = new BrowserWindow("string");
		browserWindow.getHistory().executeNavigation("www.yahoo.com");
		browserWindow.getHistory().executeNavigation("www.google.com");
		browserWindow.getHistory().executeNavigation("www.cnn.com");
		browserWindow.getHistory().executeNavigation("www.rediff.com");
		browserWindow.getHistory().requestNavBack();
		String forwardString = browserWindow.getHistory().requestNavForward();
		Assert.assertEquals("www.cnn.com", forwardString);
	}

}
