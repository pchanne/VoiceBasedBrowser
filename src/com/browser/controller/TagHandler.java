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
    Elements linkTagModelList;
    HashMap tagCountHashMap;
    JSoupHelper jsoupHelper;
    WebEngine webEngine;
    int headerTagPosition;
    int linkTagPosition;
    Element currentLink;
    HeaderTagModel currentHeader;
    
    public HeaderTagModel getCurrentHeader() {
		return currentHeader;
	}

	public void setCurrentHeader(HeaderTagModel currentHeader) {
		this.currentHeader = currentHeader;
	}

	public Element getCurrentLink() {
		return currentLink;
	}

	public void setCurrentLink(Element currentLink) {
		this.currentLink = currentLink;
	}

	public TagHandler(WebEngine webEngine)
    {
        headerTagModelList= new ArrayList<HeaderTagModel>();
        tagCountHashMap= new HashMap();
        jsoupHelper= new JSoupHelper();
        this.webEngine= webEngine;
        headerTagPosition=0;
        linkTagPosition = 0;
        
    }
    
    public void initialise()
    {
        String url= webEngine.getLocation();
        Elements headerTagList= jsoupHelper.getHeaderTags(url);     
        linkTagModelList = jsoupHelper.getLinkTags(url);
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
                            "headerTag[i].style.backgroundColor = 'lightblue';"+
                        "}";
                        
                 webEngine.executeScript(scriptToGetHeaderTag);
            }
            
        }
    
    public void selectNextHeader()
    {
    	//selectAllHeaderTags();
        if(headerTagPosition== headerTagModelList.size())
        {
            headerTagPosition=0;
        }
        currentHeader= headerTagModelList.get(headerTagPosition);
              
        
        headerTagPosition++;
        
        String scriptToGetHeaderTag;
        
        System.out.println(currentHeader.toString());
        scriptToGetHeaderTag="var headerTag = document.getElementsByTagName('"+currentHeader.
                getHeaderTag().tagName()+"');"+
                    "headerTag["+currentHeader.getPosition()+"].style.backgroundColor = 'lightgray';";
                
         webEngine.executeScript(scriptToGetHeaderTag);
    }
    public void selectAllLinkTags() {
        String url = webEngine.getLocation();
        //Elements headerTagList = jsoupHelper.getHeaderTags(url);

        String scriptToGetHeaderTag;


                       scriptToGetHeaderTag = "var headerTag = document.getElementsByTagName('a');" + "for(var i=0;i< headerTag.length;i++)" + "{"
                                                     + "headerTag[i].style.backgroundColor = 'lightyellow';" + "}";

                       webEngine.executeScript(scriptToGetHeaderTag);
       

}

public void selectNextLinkHeader() {
	//selectAllLinkTags();
        if (linkTagPosition == linkTagModelList.size()) {
                       System.out.println("when reset"+linkTagPosition);
                       linkTagPosition = 0;
}

        currentLink=linkTagModelList.get(linkTagPosition);
                                      String scriptToGetHeaderTag;
        scriptToGetHeaderTag = "var headerTag = document.getElementsByTagName('" + currentLink.tagName()+ "');" + "headerTag["
                                      + linkTagPosition + "].style.backgroundColor = 'lightgreen';";
       
//        System.out.println("link text "+currentLink.text());
//        System.out.println("link url "+currentLink.attr("href"));
//       
//        System.out.println("script is "+scriptToGetHeaderTag);
//        System.out.println("size of list "+linkTagModelList.size());
//        System.out.println("linkTagPosition"+linkTagPosition);
        linkTagPosition++;
        webEngine.executeScript(scriptToGetHeaderTag);
}

public void navigateurl()
{
        //System.out.println("Currentlink url is "+currentLink.text());
        //System.out.println("link url "+currentLink.attr("href"));
        webEngine.load(currentLink.attr("href"));                             
       
}
   public void clearSelectedTags(){
	   System.out.println("Clearing all selected tags");
	   String scriptToGetHeaderTag;
       String scriptToGetLinkTag;
       for(int i=1;i<=6;i++)
       {
           scriptToGetHeaderTag="var headerTag = document.getElementsByTagName('h"+i+"');"+
                   "for(var i=0;i< headerTag.length;i++)"+
                   "{"+
                       "headerTag[i].style.backgroundColor = '';"+
                   "}";
//           scriptToGetLinkTag = "var headerTag = document.getElementsByTagName('a');" + "for(var i=0;i< headerTag.length;i++)" + "{"
//                   + "headerTag[i].style.backgroundColor = 'yellow';" + "}";        
            webEngine.executeScript(scriptToGetHeaderTag);
       }
       scriptToGetLinkTag = "var headerTag = document.getElementsByTagName('a');" + "for(var i=0;i< headerTag.length;i++)" + "{"
               + "headerTag[i].style.backgroundColor = '';" + "}";

webEngine.executeScript(scriptToGetLinkTag);
   }
}
