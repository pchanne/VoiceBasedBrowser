package com.browser.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class FileReader {
	
	public void ReadTitle(String URLString) throws IOException
	{
		//Jsoup : to read doc from url
		Document doc = Jsoup.connect(URLString).get();
	
		String title = doc.title();
		System.out.println(title);
		
		new formTTS("You have opened "+title);
	}
	
	public void ReadTemp(String URLString) throws IOException
	{
		//Jsoup : to read doc from url
		//Document doc = Jsoup.connect(URLString).get();
	
		//String title = doc.title();
		//System.out.println(URLString);
		
		new formTTS(URLString);
	}

}
