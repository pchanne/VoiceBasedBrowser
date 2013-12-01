package com.browser.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.scene.web.WebEngine;

import com.browser.helper.JSoupHelper;
import com.browser.model.HeaderTagModel;
import com.sun.jndi.toolkit.ctx.HeadTail;

public class TagHandler {

    ArrayList<HeaderTagModel> headerTagModelList;
    HashMap tagCountHashMap;
    JSoupHelper jsoupHelper;
    WebEngine webEngine;
    int headerTagPosition;
    
    public TagHandler(WebEngine webEngine)
    {
        headerTagModelList= new ArrayList<HeaderTagModel>();
        tagCountHashMap= new HashMap();
        jsoupHelper= new JSoupHelper();
        this.webEngine= webEngine;
        headerTagPosition=0;
        
    }
    
    public void initialise()
    {
        String url= webEngine.getLocation();
        Elements headerTagList= jsoupHelper.getHeaderTags(url);     
        
        headerTagModelList= new ArrayList<HeaderTagModel>();
        tagCountHashMap= new HashMap();
        
        for(int i=0;i< headerTagList.size();i++)
        {
            Element currentHeaderElement= headerTagList.get(i);
            int position_of_current_header_element;            
            if(tagCountHashMap.containsKey(currentHeaderElement.tagName()))
            {
                int count_of_currentHeaderTag= (Integer) tagCountHashMap.get(
                        currentHeaderElement.tagName());
                position_of_current_header_element= count_of_currentHeaderTag+1;
                
                tagCountHashMap.remove(currentHeaderElement.tagName());
                tagCountHashMap.put(currentHeaderElement.tagName(),
                        count_of_currentHeaderTag+1);
            }
            else
            {
                tagCountHashMap.put(currentHeaderElement.tagName(), 0);
                position_of_current_header_element=0;
            }
            
            HeaderTagModel currentHeaderTag= new HeaderTagModel(currentHeaderElement
                    , position_of_current_header_element);
            headerTagModelList.add(currentHeaderTag);  
        }
    }
    
      
            
    public void selectAllHeaderTags()
        {          
            
            String scriptToGetHeaderTag;
            
            for(int i=1;i<=6;i++)
            {
                scriptToGetHeaderTag="var headerTag = document.getElementsByTagName('h"+i+"');"+
                        "for(var i=0;i< headerTag.length;i++)"+
                        "{"+
                            "headerTag[i].style.backgroundColor = 'blue';"+
                        "}";
                        
                 webEngine.executeScript(scriptToGetHeaderTag);
            }
            
        }
    
    public void selectNextHeader()
    {
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
                    "headerTag["+currentHeader.getPosition()+"].style.backgroundColor = 'red';";
                
         webEngine.executeScript(scriptToGetHeaderTag);
    }
    
    
}
