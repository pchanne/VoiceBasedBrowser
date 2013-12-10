/**
 * This class is used to create the bookmark as a combination of name and url
 */
package com.browser.model;

public class Bookmark {

	private String bookmarkName;
	private String bookmarkURL;

	// Do-nothing Constructor
	public void Bookmark() {
	}

	public String getBookmarkName() {
		return bookmarkName;
	}

	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}

	public String getBookmarkURL() {
		return bookmarkURL;
	}

	/**
	 * @param bookmarkURL
	 *            the bookmarkURL to set
	 */
	public void setBookmarkURL(String bookmarkURL) {
		this.bookmarkURL = bookmarkURL;
	}

	@Override
	public String toString() {
		return "Bookmark [bookmarkName=" + bookmarkName + ", bookmarkURL="
				+ bookmarkURL + "]";
	}
}
