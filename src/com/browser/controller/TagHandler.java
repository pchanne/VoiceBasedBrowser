/**
 * This class is responsible for dividing current
 *  view of web page into 2 further views 
 * and retrieving text, link and header tags in current viewport of user
 */

package com.browser.controller;

import java.util.ArrayList;
import javafx.scene.web.WebEngine;
import com.browser.helper.JSoupHelper;
import com.browser.main.VoiceBrowser;
import com.browser.model.Tag;
import com.browser.model.TagModel;

public class TagHandler {

	private JSoupHelper jsoupHelper;
	private WebEngine webEngine;

	// model for storing tags in different subviews of current view
	private TagModel topViewTagModel;
	private TagModel bottomViewTagModel;
	private TagModel currentViewTagModel;

	// dimensions of current view
	double currentViewXOrigin;
	double currentViewYOrigin;
	double currentViewWidth;
	double currentViewHeight;

	public TagHandler(WebEngine webEngine) {
		jsoupHelper = new JSoupHelper();
		this.webEngine = webEngine;
	}

	/**
	 * @return the currentViewXOrigin
	 */
	public double getCurrentViewXOrigin() {
		return currentViewXOrigin;
	}

	/**
	 * @return the currentViewYOrigin
	 */
	public double getCurrentViewYOrigin() {
		return currentViewYOrigin;
	}

	/**
	 * @return the currentViewWidth
	 */
	public double getCurrentViewWidth() {
		return currentViewWidth;
	}

	/**
	 * @return the currentViewHeight
	 */
	public double getCurrentViewHeight() {
		return currentViewHeight;
	}

	public double getTopViewXOrigin() {
		return topViewTagModel.getxOrigin();
	}

	public double getTopViewYOrigin() {
		return topViewTagModel.getyOrigin();
	}

	public double getBottomViewXOrigin() {
		return bottomViewTagModel.getxOrigin();
	}

	public double getBottomViewYOrigin() {
		return bottomViewTagModel.getyOrigin();
	}

	public double getTopViewWidth() {
		return topViewTagModel.getViewWidth();
	}

	public double getTopViewHeight() {
		return topViewTagModel.getViewHeight();
	}

	public double getBottomViewWidth() {
		return bottomViewTagModel.getViewWidth();
	}

	public double getBottomViewHeight() {
		return bottomViewTagModel.getViewHeight();
	}

	/*
	 * initialises current view to the user and sets dimenstions of subviews
	 * topview and bottomview in it.
	 */
	public void initialiseViews(double xOrigin, double yOrigin,
			double viewWidth, double viewHeight) {
		currentViewXOrigin = xOrigin;
		currentViewYOrigin = yOrigin;
		currentViewWidth = viewWidth;
		currentViewHeight = viewHeight;

		if (currentViewTagModel != null) {
			deselectAllLinksinCurrentView();
		}

		currentViewTagModel = new TagModel(viewWidth, viewHeight, xOrigin,
				yOrigin);
		topViewTagModel = new TagModel(viewWidth, viewHeight / 2, xOrigin,
				yOrigin);
		bottomViewTagModel = new TagModel(viewWidth, viewHeight / 2, xOrigin,
				yOrigin + viewHeight / 2);

		fillCurrentViewModel();
	}

	/*
	 * This function gets tagelements in viewport having origin at
	 * (xOrigin,yOrigin) and width = viewWidth and height= viewHeight
	 */
	private ArrayList<Tag> getTagElements(ArrayList<Tag> tagElementList,
			double xOrigin, double yOrigin, double viewWidth, double viewHeight) {
		ArrayList<Tag> elementsInViewList = new ArrayList<Tag>();

		for (Tag currentTagElement : tagElementList) {

			String executeScriptToGetTagElement;
			executeScriptToGetTagElement = "function isElementInViewport(el)"
					+ " {var rect = el.getBoundingClientRect();"
					+ "return rect.left >" + xOrigin + " && rect.top > "
					+ yOrigin + " && " + "rect.right < "
					+ (xOrigin + viewWidth) + " && rect.bottom < "
					+ (yOrigin + viewHeight) + ";} "
					+ "var d = document.getElementsByTagName('"
					+ currentTagElement.getTagElement().tagName() + "')" + "["
					+ currentTagElement.getPosition() + "]; "
					+ "isElementInViewport(d);";

			// System.out.println(executeScriptToGetTagElement);

			boolean isTagElementInView = (Boolean) webEngine
					.executeScript(executeScriptToGetTagElement);

			if (isTagElementInView) {
				elementsInViewList.add(currentTagElement);
			}
		}

		return elementsInViewList;
	}

	/*
	 * This function will fill tagModel with all tags which lie in area defined
	 * by its dimensions
	 */
	private void fillViewModel(TagModel tagModel) {
		double viewWidth = tagModel.getViewWidth();
		double viewHeight = tagModel.getViewHeight();
		double xOrigin = tagModel.getxOrigin();
		double yOrigin = tagModel.getyOrigin();

		// get all link tags
		ArrayList<Tag> linkElements = jsoupHelper.getLinkTags(webEngine
				.getLocation());
		tagModel.addLinkTags(getTagElements(linkElements, xOrigin, yOrigin,
				viewWidth, viewHeight));

		// get all header Tags
		ArrayList<Tag> headerElements = jsoupHelper.getHeaderTags(webEngine
				.getLocation());
		tagModel.addHeaderTags(getTagElements(headerElements, xOrigin, yOrigin,
				viewWidth, viewHeight));

		// get all p, pre, span tags
		ArrayList<Tag> textElements = jsoupHelper.getTextTags(webEngine
				.getLocation());
		tagModel.addTextTags(getTagElements(textElements, xOrigin, yOrigin,
				viewWidth, viewHeight));
	}

	/*
	 * This function will highlight all tags in tagElementlist with particular
	 * color
	 */
	private void highlightParticularTagsinView(ArrayList<Tag> tagElementList,
			String color) {
		for (Tag tagElement : tagElementList) {
			highlightTagElement(tagElement.getTagElement().tagName(),
					tagElement.getPosition(), color);
		}
	}

	/*
	 * this fucntion will highlight all links(a tag) in area defined by
	 * dimensions of selectedTagModel with particular color
	 */
	private void highlightAllLinksinView(TagModel selectedTagModel, String color) {
		highlightParticularTagsinView(selectedTagModel.getLinkTagList(), color);
	}

	/*
	 * this fucntion will highlight all links(h1 to h6 tags) in area defined by
	 * dimensions of selectedTagModel with particular color
	 */
	private void highlightAllHeadersinView(TagModel selectedTagModel,
			String color) {
		highlightParticularTagsinView(selectedTagModel.getHeaderTagList(),
				color);
	}

	/*
	 * this fucntion will highlight all links(p, pre tags) in area defined by
	 * dimensions of selectedTagModel with particular color
	 */
	private void highlightAllTextinView(TagModel selectedTagModel, String color) {
		highlightParticularTagsinView(selectedTagModel.getTextTagList(), color);
	}

	/*
	 * this function highlights all links in area having dimensions of
	 * topViewTagModel
	 */
	public void highlightAllLinksinTopView() {
		deselectAllLinks();

		String highlightAllLinksinTopViewColor = "red";
		highlightAllLinksinView(topViewTagModel,
				highlightAllLinksinTopViewColor);
	}

	/*
	 * this function highlights all links in area having dimensions of
	 * bottomViewTagModel
	 */

	public void highlightAllLinksinBottomView() {
		deselectAllLinks();

		String highlightAllLinksinBottomViewColor = "red";
		highlightAllLinksinView(bottomViewTagModel,
				highlightAllLinksinBottomViewColor);
	}

	/*
	 * this function highlights all links in area having dimensions of
	 * currentViewTagModel
	 */

	public void highlightAllLinksinCurrentView() {
		String highlightAllLinksinCurrentViewColor = "red";
		highlightAllLinksinView(currentViewTagModel,
				highlightAllLinksinCurrentViewColor);
	}

	/*
	 * this function highlights all header(h1-h6) in area having dimensions of
	 * topViewTagModel
	 */

	public void highlightAllHeadersinTopView() {
		deselectAllHeaders();

		String highlightAllHeadersinTopViewColor = "red";
		highlightAllHeadersinView(topViewTagModel,
				highlightAllHeadersinTopViewColor);
	}

	/*
	 * this function highlights all headers (h1-h6 tags) in area having
	 * dimensions of bottomViewTagModel
	 */
	public void highlightAllHeadersinBottomView() {
		deselectAllHeaders();

		String highlightAllHeadersinBottomViewColor = "red";
		highlightAllHeadersinView(bottomViewTagModel,
				highlightAllHeadersinBottomViewColor);
	}

	/*
	 * this function highlights all headers (h1-h6 tags) in area having
	 * dimensions of currentViewTagModel
	 */
	public void highlightAllHeadersinCurrentView() {

		String highlightAllHeadersinCurrentViewColor = "red";
		highlightAllHeadersinView(currentViewTagModel,
				highlightAllHeadersinCurrentViewColor);
	}

	/*
	 * this function highlights all text (p, pre) tags in area having dimensions
	 * of topViewTagModel
	 */
	public void highlightAllTextinTopView() {
		// deselect all text elements
		deselectAllText();

		String highlightAllTextinTopViewColor = "red";
		highlightAllTextinView(topViewTagModel, highlightAllTextinTopViewColor);
	}

	/*
	 * this function highlights all text (p, pre) tags in area having dimensions
	 * of bottomViewTagModel
	 */
	public void highlightAllTextinBottomView() {
		// deselect all text elements
		deselectAllText();
		String highlightAllHeadersinBottomViewColor = "red";
		highlightAllTextinView(bottomViewTagModel,
				highlightAllHeadersinBottomViewColor);

	}

	/*
	 * this function highlights all text (p, pre) tags in area having dimensions
	 * of textViewTagModel
	 */
	public void highlightAllTextinCurrentView() {
		deselectAllTextinCurrentView();

		String highlightAllTextinCurrentViewColor = "red";
		highlightAllTextinView(currentViewTagModel,
				highlightAllTextinCurrentViewColor);
	}

	/*
	 * this function will fill all tags having dimensions of currentViewModel in
	 * currentViewModel
	 */
	public void fillCurrentViewModel() {
		currentViewTagModel.clearModel();
		fillViewModel(currentViewTagModel);
	}

	/*
	 * this function will fill all tags having dimensions of topViewModel in
	 * topViewModel
	 */
	public void fillTopViewModel() {
		topViewTagModel.clearModel();
		fillViewModel(topViewTagModel);
	}

	/*
	 * this function will fill all tags having dimensions of bottomViewModel in
	 * bottomViewModel
	 */
	public void fillBottomViewModel() {
		bottomViewTagModel.clearModel();
		fillViewModel(bottomViewTagModel);

	}

	/*
	 * this function will highlight tag at position relativePosition in webpage
	 * with particular color
	 */
	private void highlightTagElement(String tagName, int relativePosition,
			String color) {
		String scriptToHighlightTag = "var nextTag= "
				+ "document.getElementsByTagName('" + tagName + "')" + "["
				+ relativePosition + "];" + "nextTag.style.backgroundColor = '"
				+ color + "';";

		VoiceBrowser.logger.info(scriptToHighlightTag);
		webEngine.executeScript(scriptToHighlightTag);
	}

	/*
	 * this function will highlight current link tag of selectedTagModel in
	 * webpage
	 */
	private void selectLinkTagElement(TagModel selectedTagModel) {
		deselectAllLinksinCurrentView();

		if (selectedTagModel.getCurrentLinkTagPosition() < selectedTagModel
				.getLinkTagList().size() - 1) {
			selectedTagModel.setCurrentLinkTagPosition(selectedTagModel
					.getCurrentLinkTagPosition() + 1);
		} else {
			selectedTagModel.setCurrentLinkTagPosition(0);
		}

		highlightTagElement(
				selectedTagModel.getLinkTagList()
						.get(selectedTagModel.getCurrentLinkTagPosition())
						.getTagElement().tagName(),
				selectedTagModel.getLinkTagList()
						.get(selectedTagModel.getCurrentLinkTagPosition())
						.getPosition(), "blue");

	}

	/*
	 * this function will highlight current header tag of selectedTagModel in
	 * webpage
	 */
	private void selectHeaderTagElement(TagModel selectedTagModel) {
		deselectAllHeadersinCurrentView();

		if (selectedTagModel.getCurrentHeaderTagPosition() < selectedTagModel
				.getHeaderTagList().size() - 1) {
			selectedTagModel.setCurrentHeaderTagPosition(selectedTagModel
					.getCurrentHeaderTagPosition() + 1);
		} else {
			selectedTagModel.setCurrentHeaderTagPosition(0);
		}

		highlightTagElement(
				selectedTagModel.getHeaderTagList()
						.get(selectedTagModel.getCurrentHeaderTagPosition())
						.getTagElement().tagName(),
				selectedTagModel.getHeaderTagList()
						.get(selectedTagModel.getCurrentHeaderTagPosition())
						.getPosition(), "blue");
	}

	/*
	 * this function will highlight current text tag of selectedTagModel in
	 * webpage
	 */
	private void selectTextElement(TagModel selectedTagModel) {
		deselectAllTextinCurrentView();

		if (selectedTagModel.getCurrentTextTagPosition() < selectedTagModel
				.getTextTagList().size() - 1) {
			selectedTagModel.setCurrentTextTagPosition(selectedTagModel
					.getCurrentTextTagPosition() + 1);
		} else {
			selectedTagModel.setCurrentTextTagPosition(0);
		}

		highlightTagElement(
				selectedTagModel.getTextTagList()
						.get(selectedTagModel.getCurrentTextTagPosition())
						.getTagElement().tagName(),
				selectedTagModel.getTextTagList()
						.get(selectedTagModel.getCurrentTextTagPosition())
						.getPosition(), "blue");
	}

	public void selectNextLinkElementinTopView() {
		selectLinkTagElement(topViewTagModel);
	}

	public void selectNextLinkElementinBottomView() {
		selectLinkTagElement(bottomViewTagModel);
	}

	public void selectNextLinkElementinCurrentView() {
		selectLinkTagElement(currentViewTagModel);
	}

	public void selectNextHeaderElementinTopView() {
		selectHeaderTagElement(topViewTagModel);
	}

	public void selectNextHeaderElementinBottomView() {
		selectHeaderTagElement(bottomViewTagModel);
	}

	public void selectNextHeaderElementinCurrentView() {
		selectHeaderTagElement(currentViewTagModel);
	}

	public void selectNextTextElementinTopView() {
		selectTextElement(topViewTagModel);
	}

	public void selectNextTextElementinBottomView() {
		selectTextElement(bottomViewTagModel);
	}

	public void selectNextTextElementinCurrentView() {
		selectTextElement(currentViewTagModel);
	}

	public void deselectAllLinksinCurrentView() {
		String deselectColor = "";
		highlightAllLinksinView(currentViewTagModel, deselectColor);
	}

	public void deselectAllLinksinTopView() {
		String deselectColor = "";
		highlightAllLinksinView(topViewTagModel, deselectColor);

	}

	public void deselectAllLinksinBottomView() {
		String deselectColor = "";
		highlightAllLinksinView(bottomViewTagModel, deselectColor);
	}

	public void deselectAllHeadersinTopView() {
		String deselectColor = "";
		highlightAllHeadersinView(topViewTagModel, deselectColor);
	}

	public void deselectAllHeadersinBottomView() {
		String deselectColor = "";
		highlightAllHeadersinView(bottomViewTagModel, deselectColor);
	}

	public void deselectAllHeadersinCurrentView() {
		String deselectColor = "";
		highlightAllHeadersinView(currentViewTagModel, deselectColor);
	}

	public void deselectAllLinks() {
		deselectAllLinksinTopView();
		deselectAllLinksinBottomView();
	}

	public void deselectAllHeaders() {
		deselectAllHeadersinTopView();
		deselectAllHeadersinBottomView();
	}

	private void deselectAllTextinTopView() {
		String deselectColor = "";
		highlightAllTextinView(topViewTagModel, deselectColor);
	}

	private void deselectAllTextinCurrentView() {
		String deselectColor = "";
		highlightAllTextinView(currentViewTagModel, deselectColor);
	}

	private void deselectAllTextinBottomView() {
		String deselectColor = "";
		highlightAllTextinView(bottomViewTagModel, deselectColor);
	}

	public void deselectAllText() {
		deselectAllTextinTopView();
		deselectAllTextinBottomView();
	}

	/*
	 * this function will get url from current link in selectedModel
	 */
	private String getUrlContentFromCurrentTag(TagModel selectedModel) {

		VoiceBrowser.logger.info(selectedModel.getCurrentLinkTagPosition());
		return selectedModel.getLinkTagList()
				.get(selectedModel.getCurrentLinkTagPosition()).getTagElement()
				.attr("href").toString();

	}

	/*
	 * this function will get url from current link in topViewModel
	 */
	public String getUrlFromCurrentLinkTaginTopView() {
		return getUrlContentFromCurrentTag(topViewTagModel);
	}

	/*
	 * this function will get url from current link in bottomViewModel
	 */

	public String getUrlFromCurrentLinkTaginBottomView() {
		return getUrlContentFromCurrentTag(bottomViewTagModel);
	}

	/*
	 * this function will get url from current link in currentViewModel
	 */

	public String getUrlFromCurrentLinkTaginCurrentView() {
		return getUrlContentFromCurrentTag(currentViewTagModel);
	}

	/*
	 * this function will get text from current header in selectedModel
	 */

	private String getTextFromCurrentHeaderTag(TagModel selectedModel) {
		return selectedModel.getHeaderTagList()
				.get(selectedModel.getCurrentHeaderTagPosition())
				.getTagElement().text();
	}

	/*
	 * this function will get text from current header in topViewModel
	 */

	public String getTextFromCurrentHeaderinTopView() {
		return getTextFromCurrentHeaderTag(topViewTagModel);
	}

	/*
	 * this function will get text from current header in bottomViewModel
	 */
	public String getTextFromCurrentHeaderinBottomView() {
		return getTextFromCurrentHeaderTag(bottomViewTagModel);
	}

	/*
	 * this function will get text from current header in currentViewModel
	 */
	public String getTextFromCurrentHeaderinCurrentView() {
		return getTextFromCurrentHeaderTag(currentViewTagModel);
	}

	/*
	 * this function will get text from current Text tag(p or pre) in
	 * selectedModel
	 */
	private String getTextFromCurrentTextTag(TagModel selectedModel) {
		return selectedModel.getTextTagList()
				.get(selectedModel.getCurrentTextTagPosition()).getTagElement()
				.text();
	}

	/*
	 * this function will get text from current Text tag(p or pre) in
	 * currentViewModel
	 */
	public String getTextFromCurrentTextinCurrentView() {
		return getTextFromCurrentTextTag(currentViewTagModel);
	}
}
