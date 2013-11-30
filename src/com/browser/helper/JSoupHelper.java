package com.browser.helper;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JSoupHelper {
    
    
    public void getHeaderTags()
    {
        String html="<h1>H1:1</h1><h2>H2:1</h2><h2>H2:2</h2><h1>H1:2</h1>";
        Document doc= Jsoup.parse(html);
        
        Elements hTags = doc.select("h1, h2, h3, h4, h5, h6");
        
        for(int i=0; i< hTags.size(); i++)
        {
            Element currentHTag= hTags.get(i);
            System.out.println(currentHTag.tagName());
        }
        
    }
    
    public String getTag(String url, String textToFind)
    {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements allElements;//= doc.select("p");
            
            //String textToFind="Para3";        
            Elements linkList= doc.select(":contains("+textToFind+")");
            
            Element currentElement= linkList.get(linkList.size()-1);
            
            allElements= doc.select(currentElement.tagName());
            
            System.out.println("Index is:"+ allElements.indexOf(currentElement));
                        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        
        
        return null;
    }

    public int getPosition(String url, String textToFind)
    {
        
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements allElements;//= doc.select("p");
            
            //String textToFind="Para3";        
            Elements linkList= doc.select(":contains("+textToFind+")");
            
            Element currentElement= linkList.get(linkList.size()-1);
            
            allElements= doc.select(currentElement.tagName());
            
            System.out.println("Index is:"+ allElements.indexOf(currentElement));
            
            return allElements.indexOf(currentElement);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        
        return -1;
        
        
    }
   
}
