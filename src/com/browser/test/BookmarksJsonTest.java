/**
 * This test class is used to test the serialization
 *  and deserialization of the bookmark Model while saving it as JSON
 */
package com.browser.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;

public class BookmarksJsonTest {

	String userDataJSON;

	BookmarkModel bookmarkModel;
	BookmarkModel deserialisedBookmarkModel;

	private void serialise() {
		bookmarkModel = new BookmarkModel();

		Bookmark testBookmark = new Bookmark();

		testBookmark.setBookmarkName("google");
		testBookmark.setBookmarkURL("www.google.com");

		Bookmark testBookmark1 = new Bookmark();

		testBookmark1.setBookmarkName("yahoo");
		testBookmark1.setBookmarkURL("www.yahoo.in");

		bookmarkModel.addBookmark(testBookmark);
		bookmarkModel.addBookmark(testBookmark1);

		ObjectMapper objectMapper = new ObjectMapper();

		Writer strWriter = new StringWriter();

		try {
			objectMapper.writeValue(strWriter, bookmarkModel);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		userDataJSON = strWriter.toString();

	}

	private void deserialise() {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			deserialisedBookmarkModel = objectMapper.readValue(userDataJSON,
					BookmarkModel.class);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test() {

		serialise();
		deserialise();

		assertEquals(bookmarkModel.toString(),
				deserialisedBookmarkModel.toString());

	}

}
