package junitTest;

import static org.junit.Assert.*;
import javafx.scene.web.WebView;
import junit.framework.Assert;

import org.junit.Test;

import com.browser.controller.BrowserWindow;
import com.browser.view.History;
import com.browser.view.ToolbarView;

public class BackCommandTest {

	BrowserWindow  browserWindow;
	ToolbarView toolBar;
	WebView view;
	
	
	@Test
	public void test() {
		//Launch();
		//fail("Not yet implemented");
	
/*	browserWindow.getView().getEngine().load("www.google.com");
	
		 String s = browserWindow.getView().getEngine().getLocation();
		//view = new WebView();
		browserWindow.getView().getEngine().load("www.yahoo.com");
		String s1 = browserWindow.getView().getEngine().getLocation();
	
		System.out.println("s1: " );*/
	
	browserWindow = new BrowserWindow("string");
	//History history =  new History();
	
	
	
	browserWindow.getHistory().executeNavigation("www.yahoo.com");
	browserWindow.getHistory().executeNavigation("www.google.com");
	String backString=browserWindow.getHistory().requestNavBack();
	Assert.assertEquals("www.yahoo.com", backString);
	
	
	}
	
	/*@Test
	public reflectURLChange(String s)
	{
		
	}*/

}
