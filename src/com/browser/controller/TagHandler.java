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

    ArrayList<HeaderTagModel> headerTagModelList;
    HashMap tagCountHashMap;
    JSoupHelper jsoupHelper;
    WebEngine webEngine;
    int headerTagPosition;
    int linkTagPosition;
    int textTagPosition;
    
    TagModel topViewTagModel;
    TagModel bottomViewTagModel;
        
    ArrayList<Tag> linksInTopView;        
    
    
    public TagHandler(WebEngine webEngine)
    {
        
        headerTagModelList= new ArrayList<HeaderTagModel>();
        tagCountHashMap= new HashMap();
        jsoupHelper= new JSoupHelper();
        this.webEngine= webEngine;
        
        headerTagPosition=0;
        linkTagPosition=0;
        textTagPosition=0;
        
        linksInTopView= new ArrayList<Tag>();
        
    }
        
    
    public void initialiseViews(double xOrigin, double yOrigin, double viewWidth, double viewHeight)
    {
        topViewTagModel= new TagModel(viewWidth, viewHeight/2,xOrigin,yOrigin);
        bottomViewTagModel= new TagModel(viewWidth, viewHeight/2, xOrigin,yOrigin+ viewHeight/2);
        fillTopViewModel();
        fillBottomViewModel();
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
    
    
    
    
    public void highlightAllLinksinTopView()
    {
        deselectAllLinks();
        System.out.println("Higlighting top links");
        
        for(Tag linkElement: topViewTagModel.getLinkTagList())
        {
            String scriptToHighlightLinks="var d = document.getElementsByTagName('" +
                    linkElement.getTagElement().tagName()  +
                    "')["+linkElement.getPosition()+"];" +
                    "d.style.backgroundColor='red'; ";
            
            webEngine.executeScript(scriptToHighlightLinks);
        }
    }
    
    public void highlightAllLinksinBottomView()
    {
        deselectAllLinks();
        System.out.println("Higlighting bottom links");
        for(Tag linkElement: bottomViewTagModel.getLinkTagList())
        {
            String scriptToHighlightLinks="var d = document.getElementsByTagName('" +
                    linkElement.getTagElement().tagName()  +
                    "')["+linkElement.getPosition()+"];" +
                    "d.style.backgroundColor='red'; ";
            
            System.out.println(scriptToHighlightLinks);
            webEngine.executeScript(scriptToHighlightLinks);
        }
    }
    
    public void highlightAllHeadersinTopView()
    {
        deselectAllHeaders();
        for(Tag headerElement: topViewTagModel.getHeaderTagList())
        {
            String scriptToHighlightLinks="var d = document.getElementsByTagName('" +
                    headerElement.getTagElement().tagName()  +
                    "')["+headerElement.getPosition()+"];" +
                    "d.style.backgroundColor='red'; ";
            
            webEngine.executeScript(scriptToHighlightLinks);
        }
    }
    
    public void highlightAllTextinTopView()
    {
        
        for(Tag textElement: topViewTagModel.getTextTagList())
        {
            String scriptToHighlightLinks="var d = document.getElementsByTagName('" +
                    textElement.getTagElement().tagName()  +
                    "')["+textElement.getPosition()+"];" +
                    "d.style.backgroundColor='red'; ";
            
            webEngine.executeScript(scriptToHighlightLinks);
        }
    }
    
    
    public void highlightAllHeadersinBottomView() {
        // TODO Auto-generated method stub
        
        deselectAllHeaders();
        for(Tag headerElement: bottomViewTagModel.getHeaderTagList())
        {
            String scriptToHighlightLinks="var d = document.getElementsByTagName('" +
                    headerElement.getTagElement().tagName()  +
                    "')["+headerElement.getPosition()+"];" +
                    "d.style.backgroundColor='red'; ";
            
            webEngine.executeScript(scriptToHighlightLinks);
        }        
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
            
    public void selectNextHeader()
    {
        //selectAllHeaderTags();
        if(headerTagPosition== headerTagModelList.size())
        {
            headerTagPosition=0;
        }
        HeaderTagModel currentHeader= headerTagModelList.get(headerTagPosition);
              
        
        headerTagPosition++;
        
        String scriptToGetHeaderTag;
        
        System.out.println(currentHeader.toString());
        scriptToGetHeaderTag="var headerTag = document.getElementsByTagName('"+currentHeader.
                getHeaderTag().tagName()+"');"+
                    "headerTag["+currentHeader.getPosition()+"].style.backgroundColor = 'red';"+
                "headerTag["+currentHeader.getPosition()+"].scrollIntoView();"+
                        "var pos= $(headerTag["+currentHeader.getPosition()+"]).getClientRects();"+
                        "alert(pos.left);";
                
         webEngine.executeScript(scriptToGetHeaderTag);
        
        
    }
    
    public void selectNextLinkElementinTopView()
    {
        
    }
    
    public void deselectAllLinks()
    {
        String scriptToDeselectLinks="var linkTag = document.getElementsByTagName('a');"+
                "for(var i=0;i< linkTag.length;i++)"+
                "{"+
                    "linkTag[i].style.backgroundColor = '';"+
                "}";
        webEngine.executeScript(scriptToDeselectLinks);
    }
    
    public void deselectAllHeaders()
    {
        for(int i=1;i<=6;i++)
        {
            String scriptToDeselectHeaders="var linkTag = document.getElementsByTagName('h"+i+"');"+
                    "for(var i=0;i< linkTag.length;i++)"+
                    "{"+
                        "linkTag[i].style.backgroundColor = '';"+
                    "}";
            webEngine.executeScript(scriptToDeselectHeaders);
        }
        
        
    }

           
}
