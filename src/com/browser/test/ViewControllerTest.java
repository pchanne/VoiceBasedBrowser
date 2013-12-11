/**
 * This test case is use to check the reflectUrlchange functionality
 **/
package com.browser.test;

import javafx.scene.web.WebView;
import junit.framework.Assert;
import org.junit.Test;
import com.browser.controller.BrowserWindow;

public class ViewControllerTest {

	BrowserWindow browserWindow;
	WebView view;

	@Test
	public void test() {

		browserWindow = new BrowserWindow("string");

		browserWindow.getHistory().executeNavigation("www.yahoo.com");
		Assert.assertNotNull(browserWindow.getHistory().getItems());

	}

}
