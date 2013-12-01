package com.browser.model;

import org.jsoup.nodes.Element;

public class HeaderTagModel {

    private Element headerTag;
    private int position;
    
    public HeaderTagModel(Element headerTag, int position) {
        super();
        this.headerTag = headerTag;
        this.position = position;
    }

    /**
     * @return the headerTag
     */
    public Element getHeaderTag() {
        return headerTag;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HeaderTagModel [headerTag=" + headerTag + ", position="
                + position + "]";
    }
    
    
    
    
}
