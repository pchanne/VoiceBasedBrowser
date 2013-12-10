/**
 * Reader class is responsible for reading selected content on a web page.
 */
package com.browser.reader;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileReader {

	public void ReadTitle(String URLString) throws IOException {
		Document doc = Jsoup.connect(URLString).get();
		String title = doc.title();
		new FormTTS("You have opened " + title);
	}

	public void ReadSelectedText(String URLString) throws IOException {
		new FormTTS(URLString);
	}

	public void ReadWholePage(String URLString) throws IOException {
		Document doc = Jsoup.connect(URLString).get();
		new FormTTS(doc.text());
	}
}
