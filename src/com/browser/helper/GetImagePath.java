package com.browser.helper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.browser.view.ToolbarView;

public class GetImagePath {

	public String jarScan(String jarName, String iconName) {
		final List<String> fileNamesImgs = new ArrayList<String>();
		URL urlString = ToolbarView.class.getClassLoader().getResource(jarName);
		String fileName = new String();
		ZipInputStream zip;
		try {
			zip = new ZipInputStream(urlString.openStream());
			ZipEntry ze = null;
			iconName = iconName + ".png";
			while ((ze = zip.getNextEntry()) != null) {
				String entryName = ze.getName();
				//System.out.println(entryName.substring(6));
				//System.out.println("icon name: " + iconName + ".png");
				
				if (entryName.endsWith(".jpg") || (entryName.endsWith(".png"))) {
					String tempStr = entryName.substring(6);
					//System.out.println("tempStr: " + tempStr);
					//System.out.println("iconName: " + iconName);
					if(tempStr.equals(iconName)){
						System.out.println("in here!!");
						fileNamesImgs.add("/" + entryName);
						fileName = "/" + entryName;
					}
					//fileNamesImgs.add("/" + entryName);
				}
			}
		} catch (IOException e1) {
		}
		System.out.println(fileNamesImgs);
		return fileName;
	}

}
