/**
 This class is used to store and retrieve all the bookmarks throughout the browser 
 */
package com.browser.model;

import java.util.ArrayList;

public class BookmarkModel {

	@Override
	public String toString() {
		return "BookmarkModel [bookmarkList=" + bookmarkList + "]";
	}

	// This the array list used to store the bookmarks throughout
	static ArrayList<Bookmark> bookmarkList;

	public BookmarkModel() {
		bookmarkList = new ArrayList<Bookmark>();
	}

	public ArrayList<Bookmark> getBookmarkList() {
		return bookmarkList;
	}

	public void setBookmarkList(ArrayList<Bookmark> bookmarkList) {
		this.bookmarkList = bookmarkList;
	}

	public void addBookmark(Bookmark bookmark) {
		bookmarkList.add(bookmark);
	}
}
