package com.browser.model;

import org.jsoup.nodes.Element;



public class Tag {

    public Tag(Element tagElement, int position) {
        super();
        this.tagElement = tagElement;
        this.position = position;
    }
    /**
     * @return the tagElement
     */
    public Element getTagElement() {
        return tagElement;
    }
    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }
    Element tagElement;
    int position;
}
