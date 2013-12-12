/**
 * This class with locate the relative path of the requested image icon.
 */
package com.browser.helper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.browser.view.TabToolbarView;

public class GetImagePath {

	public String jarScan(String jarName, String iconName) {
		final List<String> fileNamesImgs = new ArrayList<String>();
		URL urlString = TabToolbarView.class.getClassLoader().getResource(
				jarName);
		String fileName = new String();
		ZipInputStream zip;
		try {
			zip = new ZipInputStream(urlString.openStream());
			ZipEntry ze = null;
			iconName = iconName + ".png";
			while ((ze = zip.getNextEntry()) != null) {
				String entryName = ze.getName();

				if (entryName.endsWith(".jpg") || (entryName.endsWith(".png"))) {
					String tempStr = entryName.substring(6);

					if (tempStr.equals(iconName)) {

						fileName = entryName;
						
					}

				}
			}
		} catch (IOException e1) {
		}

		return fileName;
	}

}
