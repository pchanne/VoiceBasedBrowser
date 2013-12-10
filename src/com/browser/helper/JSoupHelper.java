/**
 * This class is basically used to parse DOM tree of html page using Jsoup library
 */
package com.browser.helper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.browser.main.VoiceBrowser;
import com.browser.model.Tag;

public class JSoupHelper {

	private Elements allElements;
	private Element currentHTag;

	public JSoupHelper() {

	}

	public JSoupHelper(Document documentUrl) {
		super();

	}

	/*
     * This method will select all the header tags (h1-h6) tags and store postion 
     * in webpage with respect to its tag name
     */
	public ArrayList<Tag> getHeaderTags(String url) {

		ArrayList<Tag> headerTagList;
		Document doc;
		try {
			headerTagList = new ArrayList<Tag>();
			doc = Jsoup.connect(url).get();
			Elements hTags = doc.select("h1, h2, h3, h4, h5, h6");

			int h1Index = 0;
			int h2Index = 0;
			int h3Index = 0;
			int h4Index = 0;
			int h5Index = 0;
			int h6Index = 0;

			for (Element currentHTag : hTags) {
				if (currentHTag.tagName().equals("h1")) {
					headerTagList.add(new Tag(currentHTag, h1Index++));
				}
				if (currentHTag.tagName().equals("h2")) {
					headerTagList.add(new Tag(currentHTag, h2Index++));
				}
				if (currentHTag.tagName().equals("h3")) {
					headerTagList.add(new Tag(currentHTag, h3Index++));
				}
				if (currentHTag.tagName().equals("h4")) {
					headerTagList.add(new Tag(currentHTag, h4Index++));
				}
				if (currentHTag.tagName().equals("h5")) {
					headerTagList.add(new Tag(currentHTag, h5Index++));
				}
				if (currentHTag.tagName().equals("h6")) {
					headerTagList.add(new Tag(currentHTag, h6Index++));
				}
			}

			return headerTagList;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 
     * This method will select all the link tags (a) tags and store postion 
     * in webpage with respect to its tag name     
	 */
	public ArrayList<Tag> getLinkTags(String url) {
		Document doc;
		ArrayList<Tag> linkTags;
		try {
			linkTags = new ArrayList<Tag>();
			doc = Jsoup.connect(url).get();
			Elements aTags = doc.select("a");

			for (Element currentElement : aTags) {
				linkTags.add(new Tag(currentElement, aTags
						.indexOf(currentElement)));
			}

			return linkTags;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	/*
	 * This method will select all the <p> tags
	 */
	public void getPTags() {
		String html = "<p>Para1</p><pre>Pre1</pre><p>Para2</p><pre>Pre2</pre>";
		Document doc = Jsoup.parse(html);

		Elements pTags = doc.select("p,pre,span, ul,li,td,tr,th,table,ol");

		for (int i = 0; i < pTags.size(); i++) {
			currentHTag = pTags.get(i);

		}
	}

	/*
     * This method will select all the text tags (p,pre) tags and store postion 
     * in webpage with respect to its tag name
     */
	public ArrayList<Tag> getTextTags(String url) {

		Document doc;
		ArrayList<Tag> textTagList;
		try {
			doc = Jsoup.connect(url).get();
			Elements textTags = doc.select("p,pre");

			textTagList = new ArrayList<Tag>();

			int pTagIndex = 0;
			int preTagIndex = 0;

			for (Element currentElement : textTags) {
				if (currentElement.tagName().equals("p")) {
					textTagList.add(new Tag(currentElement, pTagIndex++));
				}
				if (currentElement.tagName().equals("pre")) {
					textTagList.add(new Tag(currentElement, preTagIndex++));
				}
			}

			return textTagList;

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	/*
	 * This method will select all the requested tags within the received area
	 */
	public String getTag(String url, String textToFind)
    {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
                    
            Elements linkList= doc.select(":contains("+textToFind+")");
            
            Element currentElement= linkList.get(linkList.size()-1);
            
            allElements= doc.select(currentElement.tagName());
            VoiceBrowser.logger.info("Inside JsoupHelper get Tag method");
            VoiceBrowser.logger.debug("Index is:"+ allElements.indexOf(currentElement));
                        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return null;
    }

	/*
	 * This method will give the position of requested text in the
	 */
	public int getPosition(String url, String textToFind) {

		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements allElements;
			Elements linkList = doc.select(":contains(" + textToFind + ")");

			Element currentElement = linkList.get(linkList.size() - 1);

			allElements = doc.select(currentElement.tagName());

			return allElements.indexOf(currentElement);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return -1;

	}

}

