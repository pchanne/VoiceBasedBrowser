package com.browser.model;

import java.util.ArrayList;

public class TagModel {
    
    ArrayList<Tag> linkTagList;
    /**
     * @return the linkTagList
     */
    public ArrayList<Tag> getLinkTagList() {
        return linkTagList;
    }

    ArrayList<Tag> headerTagList;
    ArrayList<Tag> textTagList;
    
    double xOrigin;
    double yOrigin;
    
    double viewWidth;
    double viewHeight;
    
    int currentLinkTagPosition;
    int currentHeaderTagPosition;
    int currentTextTagPosition;
    
    private TagModel()
    {
        linkTagList= new ArrayList<Tag>();
        headerTagList= new ArrayList<Tag>();
        textTagList= new ArrayList<Tag>();
        
        currentLinkTagPosition=-1;
        currentHeaderTagPosition=-1;
        currentTextTagPosition=-1;
                
    }
    
    public TagModel(double viewWidth, double viewHeight, double xOrigin, double yOrigin) {
        
        this();
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.xOrigin= xOrigin;
        this.yOrigin= yOrigin;
    }

    /**
     * @return the viewWidth
     */
    public double getViewWidth() {
        return viewWidth;
    }

    /**
     * @param viewWidth the viewWidth to set
     */
    public void setViewWidth(double viewWidth) {
        this.viewWidth = viewWidth;
    }

    /**
     * @return the viewHeight
     */
    public double getViewHeight() {
        return viewHeight;
    }

    /**
     * @param viewHeight the viewHeight to set
     */
    public void setViewHeight(double viewHeight) {
        this.viewHeight = viewHeight;
    }

    /**
     * @return the xOrigin
     */
    public double getxOrigin() {
        return xOrigin;
    }

    /**
     * @return the yOrigin
     */
    public double getyOrigin() {
        return yOrigin;
    }

    public void addLinkTag(Tag linkTag)
    {
        linkTagList.add(linkTag);
    }
    
    public void addLinkTags(ArrayList<Tag> linkTagList)
    {
        this.linkTagList.addAll(linkTagList);
    }
    
    public void addHeaderTags(ArrayList<Tag> headerTagList)
    {
        this.headerTagList.addAll(headerTagList);
    }
    
    public void addTextTags(ArrayList<Tag> textTagList)
    {
        this.textTagList.addAll(textTagList);
    }
    
    public void addHeaderTag(Tag headerTag)
    {
        headerTagList.add(headerTag);
    }
    
    public void addTextTag(Tag textTag)
    {
        textTagList.add(textTag);
    }
    
    public  void clearLinkTags()
    {
     linkTagList.removeAll(linkTagList);   
    }
    
    public void clearHeaderTags()
    {
        headerTagList.removeAll(headerTagList);
    }
    
    public void clearTextTags()
    {
        textTagList.removeAll(textTagList);
    }
    
    public void clearModel()
    {
        clearLinkTags();
        clearHeaderTags();
        clearTextTags();
    }

    /**
     * @return the headerTagList
     */
    public ArrayList<Tag> getHeaderTagList() {
        return headerTagList;
    }

    /**
     * @return the textTagList
     */
    public ArrayList<Tag> getTextTagList() {
        return textTagList;
    }

    /**
     * @return the currentLinkTagPosition
     */
    public int getCurrentLinkTagPosition() {
        return currentLinkTagPosition;
    }

    /**
     * @param currentLinkTagPosition the currentLinkTagPosition to set
     */
    public void setCurrentLinkTagPosition(int currentLinkTagPosition) {
        this.currentLinkTagPosition = currentLinkTagPosition;
    }

    /**
     * @return the currentHeaderTagPosition
     */
    public int getCurrentHeaderTagPosition() {
        return currentHeaderTagPosition;
    }

    /**
     * @param currentHeaderTagPosition the currentHeaderTagPosition to set
     */
    public void setCurrentHeaderTagPosition(int currentHeaderTagPosition) {
        this.currentHeaderTagPosition = currentHeaderTagPosition;
    }

    /**
     * @return the currentTextTagPosition
     */
    public int getCurrentTextTagPosition() {
        return currentTextTagPosition;
    }

    /**
     * @param currentTextTagPosition the currentTextTagPosition to set
     */
    public void setCurrentTextTagPosition(int currentTextTagPosition) {
        this.currentTextTagPosition = currentTextTagPosition;
    }

   

}
