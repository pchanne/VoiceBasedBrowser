package junitTest;

import static org.junit.Assert.*;
import javafx.scene.web.WebView;
import junit.framework.Assert;

import org.junit.Test;

import com.browser.controller.BrowserWindow;
import com.browser.view.History;
//import com.browser.view.ToolbarView;
/*
 * This test case is used to check the forward functionality
 */
public class ForwardCommandTest {

	BrowserWindow  browserWindow;
	//ToolbarView toolBar;
	WebView view;
	
	
	@Test
	public void test() {
		
	browserWindow = new BrowserWindow("string");
	browserWindow.getHistory().executeNavigation("www.yahoo.com");
	browserWindow.getHistory().executeNavigation("www.google.com");
	browserWindow.getHistory().executeNavigation("www.cnn.com");
	browserWindow.getHistory().executeNavigation("www.rediff.com");
	String backString=browserWindow.getHistory().requestNavBack();
	String forwardString = browserWindow.getHistory().requestNavForward();
	Assert.assertEquals("www.rediff.com", forwardString);
	}
	
}
