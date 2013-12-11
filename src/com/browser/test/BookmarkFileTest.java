/**
 * This test case is used to test the 
 * functionality of creating the Bookmark model
 */

package com.browser.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.browser.helper.BookmarkManager;
import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;

public class BookmarkFileTest {

	BookmarkModel bookmarkModel;

	private void createBookmarkModel() {
		bookmarkModel = new BookmarkModel();

		Bookmark testBookmark = new Bookmark();
		testBookmark.setBookmarkName("google");
		testBookmark.setBookmarkURL("www.google.com");

		Bookmark testBookmark1 = new Bookmark();
		testBookmark1.setBookmarkName("yahoo");
		testBookmark1.setBookmarkURL("www.yahoo.in");

		bookmarkModel.addBookmark(testBookmark);
		bookmarkModel.addBookmark(testBookmark1);
	}

	@Test
	public void test() {

		createBookmarkModel();

		BookmarkManager bookmarkManager = new BookmarkManager();

		bookmarkManager.saveBookmarkModel(bookmarkModel);

		BookmarkModel loadedBookmarkModel = bookmarkManager.loadBookmarkModel();

		assertEquals(bookmarkModel.toString(), loadedBookmarkModel.toString());
	}

}
