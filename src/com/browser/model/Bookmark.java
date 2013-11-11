package com.browser.model;

public class Bookmark {
//private Bookmark bookmark;
private String bookmarkName;
private String bookmarkURL;

public void Bookmark()
{
    
}

/**
 * @return the bookmarkName
 */
public String getBookmarkName() {
    return bookmarkName;
}

/**
 * @param bookmarkName the bookmarkName to set
 */
public void setBookmarkName(String bookmarkName) {
    this.bookmarkName = bookmarkName;
}

/**
 * @return the bookmarkURL
 */
public String getBookmarkURL() {
    return bookmarkURL;
}

/**
 * @param bookmarkURL the bookmarkURL to set
 */
public void setBookmarkURL(String bookmarkURL) {
    this.bookmarkURL = bookmarkURL;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
    return "Bookmark [bookmarkName=" + bookmarkName + ", bookmarkURL="
            + bookmarkURL + "]";
}




}
