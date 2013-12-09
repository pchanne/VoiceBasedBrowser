package junitTest;

import static org.junit.Assert.*;
import javafx.scene.web.WebView;
import junit.framework.Assert;

import org.junit.Test;

import com.browser.controller.BrowserWindow;
import com.browser.controller.History;

/*
 * This test case is use to check the reflectUrlchange functionality
 */
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
