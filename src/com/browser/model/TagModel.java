/**
 * This is a array list of TagModel used to
 *  retrieve headerTag and current textTag position
 */

package com.browser.model;

import java.util.ArrayList;

public class TagModel {

	// list having all link tags(a)
	private ArrayList<Tag> linkTagList;

	// list having all header tags(h1-h6)
	private ArrayList<Tag> headerTagList;

	// list having all text tags (p,pre)
	private ArrayList<Tag> textTagList;

	// dimensions of TagModel
	private double xOrigin;
	private double yOrigin;
	private double viewWidth;
	private double viewHeight;

	// current selected position of tag element
	private int currentLinkTagPosition;
	private int currentHeaderTagPosition;
	private int currentTextTagPosition;

	public ArrayList<Tag> getLinkTagList() {
		return linkTagList;
	}

	private TagModel() {
		linkTagList = new ArrayList<Tag>();
		headerTagList = new ArrayList<Tag>();
		textTagList = new ArrayList<Tag>();

		currentLinkTagPosition = -1;
		currentHeaderTagPosition = -1;
		currentTextTagPosition = -1;
	}

	public TagModel(double viewWidth, double viewHeight, double xOrigin,
			double yOrigin) {

		this();
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
	}

	public double getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(double viewWidth) {
		this.viewWidth = viewWidth;
	}

	public double getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(double viewHeight) {
		this.viewHeight = viewHeight;
	}

	public double getxOrigin() {
		return xOrigin;
	}

	public double getyOrigin() {
		return yOrigin;
	}

	public void addLinkTag(Tag linkTag) {
		linkTagList.add(linkTag);
	}

	public void addLinkTags(ArrayList<Tag> linkTagList) {
		this.linkTagList.addAll(linkTagList);
	}

	public void addHeaderTags(ArrayList<Tag> headerTagList) {
		this.headerTagList.addAll(headerTagList);
	}

	public void addTextTags(ArrayList<Tag> textTagList) {
		this.textTagList.addAll(textTagList);
	}

	public void addHeaderTag(Tag headerTag) {
		headerTagList.add(headerTag);
	}

	public void addTextTag(Tag textTag) {
		textTagList.add(textTag);
	}

	/*
	 * removes all link tags (a) in Model
	 */
	public void clearLinkTags() {
		linkTagList.removeAll(linkTagList);
	}

	/*
	 * removes all header tags (h1-h6) in Model
	 */
	public void clearHeaderTags() {
		headerTagList.removeAll(headerTagList);
	}

	/*
	 * removes all text tags (p,pre) in Model
	 */
	public void clearTextTags() {
		textTagList.removeAll(textTagList);
	}

	/*
	 * removes all tags in Model
	 */
	public void clearModel() {
		clearLinkTags();
		clearHeaderTags();
		clearTextTags();
	}

	public ArrayList<Tag> getHeaderTagList() {
		return headerTagList;
	}

	public ArrayList<Tag> getTextTagList() {
		return textTagList;
	}

	public int getCurrentLinkTagPosition() {
		return currentLinkTagPosition;
	}

	public void setCurrentLinkTagPosition(int currentLinkTagPosition) {
		this.currentLinkTagPosition = currentLinkTagPosition;
	}

	public int getCurrentHeaderTagPosition() {
		return currentHeaderTagPosition;
	}

	public void setCurrentHeaderTagPosition(int currentHeaderTagPosition) {
		this.currentHeaderTagPosition = currentHeaderTagPosition;
	}

	public int getCurrentTextTagPosition() {
		return currentTextTagPosition;
	}

	public void setCurrentTextTagPosition(int currentTextTagPosition) {
		this.currentTextTagPosition = currentTextTagPosition;
	}
}
