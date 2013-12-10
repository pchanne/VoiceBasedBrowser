package com.browser.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.web.WebEngine;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.browser.helper.JSoupHelper;
import com.browser.model.HeaderTagModel;
import com.browser.model.Tag;
import com.browser.model.TagModel;

public class TagHandler {
    
    JSoupHelper jsoupHelper;
    WebEngine webEngine;
        
    TagModel topViewTagModel;
    TagModel bottomViewTagModel;
    TagModel currentViewTagModel;
    
    double currentViewXOrigin;
    double currentViewYOrigin;
    double currentViewWidth;
    double currentViewHeight;
                         
    public TagHandler(WebEngine webEngine)
    {                     
        jsoupHelper= new JSoupHelper();
        this.webEngine= webEngine;                               
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

    public double getTopViewXOrigin()
    {
        return topViewTagModel.getxOrigin();
    }
    
    public double getTopViewYOrigin()
    {
        return topViewTagModel.getyOrigin();
    }
    
    public double getBottomViewXOrigin()
    {
        return bottomViewTagModel.getxOrigin();
    }
    
    public double getBottomViewYOrigin()
    {
        return bottomViewTagModel.getyOrigin();
    }

    public double getTopViewWidth()
    {
        return topViewTagModel.getViewWidth();
    }
    
    public double getTopViewHeight()
    {
        return topViewTagModel.getViewHeight();
    }
    
    public double getBottomViewWidth()
    {
        return bottomViewTagModel.getViewWidth();
    }
    
    public double getBottomViewHeight()
    {
        return bottomViewTagModel.getViewHeight();
    }
    
    public void initialiseViews(double xOrigin, double yOrigin, double viewWidth, double viewHeight)
    {
        currentViewXOrigin= xOrigin;
        currentViewYOrigin= yOrigin;
        currentViewWidth= viewWidth;
        currentViewHeight= viewHeight;
        
        /*if(topViewTagModel!=null)
        {
            
            deselectAllHeadersinTopView();
            deselectAllLinksinTopView();
            deselectAllTextinTopView();
            
        }
        
        if(bottomViewTagModel!=null)
        {
            deselectAllHeadersinBottomView();
            deselectAllLinksinBottomView();
            deselectAllTextinBottomView();
        }*/
        
        if(currentViewTagModel!=null)
        {
            deselectAllLinksinCurrentView();
        }
        
        currentViewTagModel= new TagModel(viewWidth, viewHeight, xOrigin, yOrigin);
        topViewTagModel= new TagModel(viewWidth, viewHeight/2,xOrigin,yOrigin);
        bottomViewTagModel= new TagModel(viewWidth, viewHeight/2, xOrigin,yOrigin+ viewHeight/2);
        
       
        
        //fillTopViewModel();
        //fillBottomViewModel();
        fillCurrentViewModel();
    }
                                      
    private ArrayList<Tag> getTagElements(ArrayList<Tag> tagElementList,
            double xOrigin, double yOrigin,
            double viewWidth, double viewHeight)
    {
       ArrayList<Tag> elementsInViewList= new ArrayList<Tag>();
        
       for(Tag currentTagElement: tagElementList)
       {
           
           String executeScriptToGetTagElement;
           executeScriptToGetTagElement="function isElementInViewport(el)" +
                   " {var rect = el.getBoundingClientRect();" +
                   "return rect.left >"+ xOrigin+" && rect.top > "+ yOrigin+" && " +
                   "rect.right < " + (xOrigin+viewWidth)+
                   " && rect.bottom < "+(yOrigin+viewHeight)+";} " +
                   "var d = document.getElementsByTagName('"+currentTagElement.getTagElement()
                   .tagName()+"')" +
                   		"["+currentTagElement.getPosition()+"]; " +
                   "isElementInViewport(d);";    
           
           //System.out.println(executeScriptToGetTagElement);
           
           boolean isTagElementInView=(Boolean) webEngine.
                   executeScript(executeScriptToGetTagElement);
           
           if(isTagElementInView)
           {              
               elementsInViewList.add(currentTagElement);
           }
       }
                
        return elementsInViewList;
    }
    
    private void fillViewModel(TagModel tagModel)
    {
        double viewWidth= tagModel.getViewWidth();
        double viewHeight= tagModel.getViewHeight();
        double xOrigin= tagModel.getxOrigin();
        double yOrigin= tagModel.getyOrigin();
        
        
        //get all link tags
        ArrayList<Tag> linkElements= jsoupHelper.getLinkTags(webEngine.getLocation());
        tagModel.addLinkTags(getTagElements(linkElements,xOrigin,yOrigin
                ,viewWidth, viewHeight));
        
        //get all header Tags
        ArrayList<Tag> headerElements= jsoupHelper.getHeaderTags(webEngine.getLocation());
        tagModel.addHeaderTags(getTagElements(headerElements, xOrigin, yOrigin, 
                viewWidth, viewHeight));
        
        //get all p, pre, span tags
        ArrayList<Tag> textElements= jsoupHelper.getTextTags(webEngine.getLocation());
        tagModel.addTextTags(getTagElements(textElements, xOrigin, yOrigin,
                viewWidth, viewHeight));                           
    }
           
    
    private void highlightParticularTagsinView(ArrayList<Tag> tagElementList, String color)
    {
        for(Tag tagElement: tagElementList)
        {
            highlightTagElement(tagElement.getTagElement().tagName(), tagElement.getPosition(), color);
        }
    }
       
    
    private void highlightAllLinksinView(TagModel selectedTagModel, String color)
    {
        highlightParticularTagsinView(selectedTagModel.getLinkTagList(), color);       
    }
    
    private void highlightAllHeadersinView(TagModel selectedTagModel, String color)
    {
        highlightParticularTagsinView(selectedTagModel.getHeaderTagList(),color);       
    }
    
    private void highlightAllTextinView(TagModel selectedTagModel, String color)
    {
        highlightParticularTagsinView(selectedTagModel.getTextTagList(), color);       
    }
    
    
    public void highlightAllLinksinTopView()
    {
        deselectAllLinks();
        
        String highlightAllLinksinTopViewColor="red";
        highlightAllLinksinView(topViewTagModel, highlightAllLinksinTopViewColor);                       
    }
    
    public void highlightAllLinksinBottomView()
    {
        deselectAllLinks();
        
        String highlightAllLinksinBottomViewColor="red";
        highlightAllLinksinView(bottomViewTagModel, highlightAllLinksinBottomViewColor);        
    }
    
    public void highlightAllLinksinCurrentView()
    {                
        String highlightAllLinksinCurrentViewColor="red";
        highlightAllLinksinView(currentViewTagModel, highlightAllLinksinCurrentViewColor);
    }
    
    public void highlightAllHeadersinTopView()
    {
        deselectAllHeaders();
        
        String highlightAllHeadersinTopViewColor="red";
        highlightAllHeadersinView(topViewTagModel, highlightAllHeadersinTopViewColor);               
    }
    
    public void highlightAllHeadersinBottomView() 
    {        
        deselectAllHeaders();
        
        String highlightAllHeadersinBottomViewColor="red";
        highlightAllHeadersinView(bottomViewTagModel, highlightAllHeadersinBottomViewColor);
    }
    
    public void highlightAllHeadersinCurrentView()
    {
                
        String highlightAllHeadersinCurrentViewColor="red";
        highlightAllHeadersinView(currentViewTagModel, highlightAllHeadersinCurrentViewColor);
    }
        
    public void highlightAllTextinTopView()
    {
        //deselect all text elements
        deselectAllText();
        
        String highlightAllTextinTopViewColor="red";
        highlightAllTextinView(topViewTagModel, highlightAllTextinTopViewColor);
                
    }
    
    public void highlightAllTextinBottomView()
    {
        //deselect all text elements
        deselectAllText();
        String highlightAllHeadersinBottomViewColor="red";
        highlightAllTextinView(bottomViewTagModel, highlightAllHeadersinBottomViewColor);
                
    }
    
    public void highlightAllTextinCurrentView()
    {
        deselectAllTextinCurrentView();
        
        String highlightAllTextinCurrentViewColor="red";
        highlightAllTextinView(currentViewTagModel, highlightAllTextinCurrentViewColor);
    }
    
    public void fillCurrentViewModel()
    {
        currentViewTagModel.clearModel();
        fillViewModel(currentViewTagModel);
    }
    
    public void fillTopViewModel()
    {                
        topViewTagModel.clearModel();                       
        fillViewModel(topViewTagModel);                                                  
    }
    
    public void fillBottomViewModel()
    {       
        bottomViewTagModel.clearModel();        
        fillViewModel(bottomViewTagModel); 
        
    }
    
    private void highlightTagElement(String tagName, int relativePosition, String color)
    {
        String scriptToHighlightTag="var nextTag= " +
                "document.getElementsByTagName('"+tagName+"')" +
                        "["+relativePosition+"];" +
                                "nextTag.style.backgroundColor = '"+color+"';";
        
        //System.out.println(scriptToHighlightTag);
        webEngine.executeScript(scriptToHighlightTag);
    }
    
    private void selectLinkTagElement(TagModel selectedTagModel)
    {
        deselectAllLinksinCurrentView();
        
        if(selectedTagModel.getCurrentLinkTagPosition()<selectedTagModel.getLinkTagList().size()-1)
        {
            selectedTagModel.setCurrentLinkTagPosition(
                    selectedTagModel.getCurrentLinkTagPosition()+1);
        }
        else
        {
            selectedTagModel.setCurrentLinkTagPosition(0);
        }
        
        highlightTagElement(selectedTagModel.getLinkTagList().
                get(selectedTagModel.getCurrentLinkTagPosition()).getTagElement().tagName(),
                selectedTagModel.getLinkTagList().
                get(selectedTagModel.getCurrentLinkTagPosition()).getPosition(), "blue");                
        
    }
    
    private void selectHeaderTagElement(TagModel selectedTagModel)
    {
        deselectAllHeadersinCurrentView();
        
        if(selectedTagModel.getCurrentHeaderTagPosition()<selectedTagModel.getHeaderTagList().size()-1)
        {
            selectedTagModel.setCurrentHeaderTagPosition(
                    selectedTagModel.getCurrentHeaderTagPosition()+1);
        }
        else
        {
            selectedTagModel.setCurrentHeaderTagPosition(0);
        }
        
        highlightTagElement(selectedTagModel.getHeaderTagList().
                get(selectedTagModel.getCurrentHeaderTagPosition()).getTagElement().tagName(),
                selectedTagModel.getHeaderTagList().
                get(selectedTagModel.getCurrentHeaderTagPosition()).getPosition(), "blue");   
    }
    
    private void selectTextElement(TagModel selectedTagModel)
    {
        deselectAllTextinCurrentView();
        
        if(selectedTagModel.getCurrentTextTagPosition()<selectedTagModel.getTextTagList().size()-1)
        {
            selectedTagModel.setCurrentTextTagPosition(
                    selectedTagModel.getCurrentTextTagPosition()+1);
        }
        else
        {
            selectedTagModel.setCurrentTextTagPosition(0);
        }
        
        highlightTagElement(selectedTagModel.getTextTagList().
                get(selectedTagModel.getCurrentTextTagPosition()).getTagElement().tagName(),
                selectedTagModel.getTextTagList().
                get(selectedTagModel.getCurrentTextTagPosition()).getPosition(), "blue");
    }
      
    public void selectNextLinkElementinTopView()
    {
        selectLinkTagElement(topViewTagModel);               
    }
    
    public void selectNextLinkElementinBottomView()
    {
        selectLinkTagElement(bottomViewTagModel);
    }
    
    public void selectNextLinkElementinCurrentView()
    {
        selectLinkTagElement(currentViewTagModel);
    }
    
    public void selectNextHeaderElementinTopView()
    {
        selectHeaderTagElement(topViewTagModel);               
    }
    
    public void selectNextHeaderElementinBottomView()
    {
        selectHeaderTagElement(bottomViewTagModel);
    }
    
    public void selectNextHeaderElementinCurrentView()
    {
        selectHeaderTagElement(currentViewTagModel);
    }
    
    public void selectNextTextElementinTopView()
    {
        selectTextElement(topViewTagModel);
    }
    
    public void selectNextTextElementinBottomView()
    {
        selectTextElement(bottomViewTagModel);
    }
    
    public void selectNextTextElementinCurrentView()
    {
        selectTextElement(currentViewTagModel);
    }
    
    public void deselectAllLinksinCurrentView()
    {
        String deselectColor="";
        highlightAllLinksinView(currentViewTagModel, deselectColor);
    }
    
    public void deselectAllLinksinTopView()
    {
        String deselectColor="";
        highlightAllLinksinView(topViewTagModel, deselectColor);
        
    }
    
    public void deselectAllLinksinBottomView()
    {
        String deselectColor="";
        highlightAllLinksinView(bottomViewTagModel, deselectColor);
    }
    
    public void deselectAllHeadersinTopView()
    {
        String deselectColor="";
        highlightAllHeadersinView(topViewTagModel, deselectColor);
    }
    
    public void deselectAllHeadersinBottomView()
    {
        String deselectColor="";
        highlightAllHeadersinView(bottomViewTagModel, deselectColor);
    }
    
    public void deselectAllHeadersinCurrentView()
    {
        String deselectColor="";
        highlightAllHeadersinView(currentViewTagModel, deselectColor);
    }
    
    public void deselectAllLinks()
    {
        deselectAllLinksinTopView();
        deselectAllLinksinBottomView();
    }
    
    public void deselectAllHeaders()
    {
        deselectAllHeadersinTopView();
        deselectAllHeadersinBottomView();
    }
    
    private void deselectAllTextinTopView()
    {
        String deselectColor="";
        highlightAllTextinView(topViewTagModel, deselectColor);
    }
    
    private void deselectAllTextinCurrentView()
    {
        String deselectColor="";
        highlightAllTextinView(currentViewTagModel, deselectColor);                
    }
    
    private void deselectAllTextinBottomView()
    {
        String deselectColor="";
        highlightAllTextinView(bottomViewTagModel, deselectColor);
    }
    
    public void deselectAllText()
    {
        deselectAllTextinTopView();
        deselectAllTextinBottomView();
    }
    
    private String getUrlContentFromCurrentTag(TagModel selectedModel)
    {
        System.out.println(selectedModel.getCurrentLinkTagPosition());
        return selectedModel.getLinkTagList().get(selectedModel.getCurrentLinkTagPosition()).
                getTagElement().attr("href").toString();
        
    }
    
    public String getUrlFromCurrentLinkTaginTopView()
    {
        return getUrlContentFromCurrentTag(topViewTagModel);
    }
    
    public String getUrlFromCurrentLinkTaginBottomView()
    {
        return getUrlContentFromCurrentTag(bottomViewTagModel);
    }
    
    public String getUrlFromCurrentLinkTaginCurrentView()
    {
        return getUrlContentFromCurrentTag(currentViewTagModel);
    }
    
    private String getTextFromCurrentHeaderTag(TagModel selectedModel)
    {
        return selectedModel.getHeaderTagList().get(selectedModel.
                getCurrentHeaderTagPosition()).getTagElement().ownText().toString();
    }
    
    public String getTextFromCurrentHeaderinTopView()
    {
        return getTextFromCurrentHeaderTag(topViewTagModel);
    }

    public String getTextFromCurrentHeaderinBottomView()
    {
        return getTextFromCurrentHeaderTag(bottomViewTagModel);
    }
    
    public String getTextFromCurrentHeaderinCurrentView()
    {
        return getTextFromCurrentHeaderTag(currentViewTagModel);
    }
        
    private String getTextFromCurrentTextTag(TagModel selectedModel)
    {
        return selectedModel.getTextTagList().get(selectedModel.
                getCurrentTextTagPosition()).getTagElement().ownText().toString();
    }
    
    public String getTextFromCurrentTextinCurrentView()
    {
        return getTextFromCurrentTextTag(currentViewTagModel);
    }
}

