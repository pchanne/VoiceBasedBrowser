package com.browser.model;

import java.util.ArrayList;

public class BookmarkModel {

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    
    @Override
    public String toString() {
        return "BookmarkModel [bookmarkList=" + bookmarkList + "]";
    }

    static ArrayList<Bookmark> bookmarkList;
    
    public BookmarkModel()
    {
        bookmarkList= new ArrayList<Bookmark>();
    }

    /**
     * @return the bookmarkList
     */
    
    
    public ArrayList<Bookmark> getBookmarkList() {
        return bookmarkList;
    }

    /**
     * @param bookmarkList the bookmarkList to set
     */
    public void setBookmarkList(ArrayList<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }
    
    
    public void addBookmark(Bookmark bookmark)
    {
        bookmarkList.add(bookmark);
    }
    
}
