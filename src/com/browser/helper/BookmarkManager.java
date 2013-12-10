/**
 * This class handles the bookmark actions - adding , removing bookmark 
 * from bookmark model and writing the list to the file.
 */

package com.browser.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.browser.model.BookmarkModel;

public class BookmarkManager {

	static private HashMap<String, String> bookmarkMapping = new HashMap<String, String>();

	public static ArrayList<String> getBookmarks() {
		ArrayList<String> bookmarkList = new ArrayList<String>();
		for (String bookmarkKey : bookmarkMapping.keySet()) {
			bookmarkList.add(bookmarkKey);
		}
		return bookmarkList;
	}

	public static String getBookmarkURL(String bookmarkName) {
		return bookmarkMapping.get(bookmarkName);
	}

	public static boolean addBookmark(String title, String url) {
		if (!bookmarkMapping.containsKey(title)) {
			bookmarkMapping.put(title, url);
			return true;
		} else
			return false;
	}

	public static boolean removeBookmark(String title) {
		if (bookmarkMapping.containsKey(title)) {
			bookmarkMapping.remove(title);
			return true;
		}

		return false;
	}

	/*
	 * This method will edit an existng bookmark
	 */
	public static boolean editBookmark(String title, String url) {
		if (bookmarkMapping.containsKey(title)) {
			bookmarkMapping.remove(title);
			bookmarkMapping.put(title, url);
			return true;
		}

		return false;
	}

	public String getUrl(String title) {
		if (bookmarkMapping.containsKey(title)) {
			return bookmarkMapping.get(title);
		}

		return null;
	}

	private String serialiseBookmarkModel(BookmarkModel bookmarkModel) {
		ObjectMapper objectMapper = new ObjectMapper();

		Writer strWriter = new StringWriter();
		String jsonString = null;

		try {
			objectMapper.writeValue(strWriter, bookmarkModel);

			jsonString = strWriter.toString();

		} catch (JsonGenerationException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return jsonString;
	}

	private BookmarkModel deserialiseBookmarkModel(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		BookmarkModel deserialisedBookmarkModel = null;

		try {
			deserialisedBookmarkModel = objectMapper.readValue(jsonString,
					BookmarkModel.class);

		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return deserialisedBookmarkModel;
	}

	/*
	 * This method will write the bookmarkmodel to a file
	 */
	public void writeToFile(String jsonString) {
		try {
			File jsonFile = new File("bookmark.json");
			FileWriter filewriter = new FileWriter(jsonFile.getAbsoluteFile());
			BufferedWriter bufferwriter = new BufferedWriter(filewriter);
			bufferwriter.write(jsonString);
			bufferwriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method will load the bookmarkmodel from file.
	 */
	public String readFromFile() {
		String jsonString = null;
		try {
			FileReader fileReader = new FileReader("bookmark.json");
			BufferedReader bufferReader = new BufferedReader(fileReader);
			jsonString = bufferReader.readLine();
			bufferReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	public void saveBookmarkModel(BookmarkModel bookmarkModel) {
		String jsonString = serialiseBookmarkModel(bookmarkModel);
		writeToFile(jsonString);
	}

	public BookmarkModel loadBookmarkModel() {
		String jsonString = readFromFile();
		BookmarkModel deserialisedBookmarkModel = deserialiseBookmarkModel(jsonString);

		return deserialisedBookmarkModel;
	}

}
