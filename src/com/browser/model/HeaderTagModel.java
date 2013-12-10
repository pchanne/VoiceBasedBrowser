/**
 *This class is used to store the store and retrieve the header tags and its respective positions.
 *  This is required since there are multiple headertags such as H1,H2,H3,H4,H5 and H6.
 *  Additionally there can be nesting of headertags as well.
 */

package com.browser.model;

import org.jsoup.nodes.Element;

public class HeaderTagModel {

    private Element headerTag;
    private int position;
    
    public HeaderTagModel(Element headerTag, int position) {        
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
