/**
 * This class is used to set and retrieve the position of tagElement
 */
package com.browser.model;

import org.jsoup.nodes.Element;

public class Tag {

	private Element tagElement;

	// relative positon of tagElement in webpage with respect to its tagName
	private int position;

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

}
