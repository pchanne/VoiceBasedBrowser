package com.browser.main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.browser.helper.JSoupHelper;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        String html = "<p id=\"para1\">An <a href='http://example.com/'><b>the hello example</b></a> link.</p>";
        
        html="<p>Para1 content</p><p>Para2 content</p><p>Para3 content</p>";
        
        JSoupHelper helper= new JSoupHelper();
        helper.getHeaderTags();
        //helper.getPosition(html, "Para3");
        
        /*
        Document doc= Jsoup.parse(html);
        
        
        Element link = doc.select("p").first();
        
        if(!link.hasAttr("id"))
        link.attr("id", "link1");
        
        System.out.println("ID of "+link.tagName()+" :"+link.id());
               
        
        String linkText= link.text();
        
        //System.out.println(linkText);
        
        String textToFind="An the hello example";
        
        Elements linkList= doc.select(":contains("+textToFind+")");  
        
                
        System.out.println("Tags having given text:");
        for(int i=0;i<linkList.size();i++)
        {
            System.out.println(linkList.get(i).tagName());
        }
        
        Element currentElement= linkList.get(linkList.size()-1);
        System.out.println("Current Tag name: "+currentElement.tagName()+ 
        		"\nEntire Text: "+currentElement.text());
        
        
        Elements linkParent= linkList.parents();
        
        Elements linkChildren= linkList.get(linkList.size()-1).getAllElements();        
        //linkList= linkParent.select("#para1");
        
                
        System.out.println("\nParents:");
        for(int i=0;i< linkParent.size();i++)
        {            
            System.out.println(linkParent.get(i).tagName());
        }
        
        System.out.println("\nChildren:");
        for(int i=1;i< linkChildren.size();i++)
        {            
            System.out.println(linkChildren.get(i).tagName());
        }
        */
        
        
    }

}
