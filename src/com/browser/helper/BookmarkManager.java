package com.browser.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.browser.model.Bookmark;
import com.browser.model.BookmarkModel;

public class BookmarkManager {
    
    static private HashMap<String, String> bookmarkMapping= new HashMap<String, String>();
    
    
    public static boolean addBookmark(String title, String url)
    {
        if(!bookmarkMapping.containsKey(title))
        {
            bookmarkMapping.put(title, url);
            return true;
        }
        else
            return false;
    }
    
    public static boolean removeBookmark(String title)
    {
        if(bookmarkMapping.containsKey(title))
        {
            bookmarkMapping.remove(title);
            return true;
        }        
        
        return false;
    }
    
    public static boolean editBookmark(String title, String url)
    {
        if(bookmarkMapping.containsKey(title))
        {
            bookmarkMapping.remove(title);
            bookmarkMapping.put(title, url);
            return true;
        }
        
        return false;
    }
    
    private void loadBookmarkMapping(BookmarkModel bookmarkModel)
    {
        for(Bookmark bookmark: bookmarkModel.getBookmarkList())
        {
            bookmarkMapping.put(bookmark.getBookmarkName(),bookmark.getBookmarkURL());
        }
    }
    
    private BookmarkModel saveBookmarkMapping()
    {
        BookmarkModel bookmarkModel = new BookmarkModel();
        
        for(Entry<String, String> bookmarkMappingEntry: bookmarkMapping.entrySet())
        {
            Bookmark bookmark = new Bookmark();
            bookmark.setBookmarkName(bookmarkMappingEntry.getKey());
            bookmark.setBookmarkURL(bookmarkMappingEntry.getValue());
            
            bookmarkModel.addBookmark(bookmark);
        }
                
        return bookmarkModel;
    }
        
    public String getUrl(String title)
    {
        if(bookmarkMapping.containsKey(title))
        {
            return bookmarkMapping.get(title);
        }
        
        return null;
    }
    
    private String serialiseBookmarkModel(BookmarkModel bookmarkModel)
    {
        ObjectMapper objectMapper= new ObjectMapper();
        
        Writer strWriter = new StringWriter();
        String jsonString=null;
        
        try {
            objectMapper.writeValue(strWriter, bookmarkModel);
            System.out.println(bookmarkModel.toString());
            jsonString= strWriter.toString();
                                                         
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return jsonString;
    }
    
    private BookmarkModel deserialiseBookmarkModel(String jsonString)
    {
        ObjectMapper objectMapper= new ObjectMapper();
        BookmarkModel deserialisedBookmarkModel=null;
        
        try {
            deserialisedBookmarkModel= objectMapper.readValue(jsonString, BookmarkModel.class);
            System.out.println(deserialisedBookmarkModel.toString());
            
            
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return deserialisedBookmarkModel;
    }
    
    public void writeToFile(String jsonString)
    {
        try {
            File jsonFile = new File("breakout.json");
            FileWriter filewriter = new FileWriter(jsonFile.getAbsoluteFile());
            BufferedWriter bufferwriter = new BufferedWriter(filewriter);
            bufferwriter.write(jsonString);
            bufferwriter.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String readFromFile()
    {
        String jsonString=null;
        try{
            FileReader fileReader = new FileReader("breakout.json");
            BufferedReader bufferReader = new BufferedReader(fileReader);
            jsonString=bufferReader.readLine();
            bufferReader.close();
           
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return jsonString;
    }
    
    

    public void saveBookmarkModel(BookmarkModel bookmarkModel)
    {
        String jsonString = serialiseBookmarkModel(bookmarkModel);
        writeToFile(jsonString);
    }
    
    public BookmarkModel loadBookmarkModel()
    {
        String jsonString = readFromFile();
        BookmarkModel deserialisedBookmarkModel = deserialiseBookmarkModel(jsonString);
        
        return deserialisedBookmarkModel;
    }
    
}
