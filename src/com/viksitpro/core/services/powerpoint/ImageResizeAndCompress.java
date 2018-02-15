/**
 * 
 */
package com.viksitpro.core.services.powerpoint;

import java.io.File;

import com.tinify.Options;
import com.tinify.Source;
import com.tinify.Tinify;

/**
 * @author Mayank
 *
 */
public class ImageResizeAndCompress extends Thread {

	private String imagePath;
	private String key;
	public ImageResizeAndCompress(String imagePath, String key) {
		super();
		this.imagePath = imagePath;
		this.key = key;
	}
	
	public void run()
	{
		if(imagePath!=null && key!=null)
		{
			File file = new File(imagePath);
			if(file.exists())
			{
				try {
					//ViksitLogger.logMSG(this.getClass().getName(),"optimizing "+file.getAbsolutePath());
					Tinify.setKey(key);
					Source source = Tinify.fromFile(file.getAbsolutePath());
					Options options = new Options()
						    .with("method", "scale")
						    .with("width", 1080);
					Source resized = source.resize(options);
					resized.toFile(file.getAbsolutePath());
					
					Source source2 = Tinify.fromFile(file.getAbsolutePath());
					source2.toFile(file.getAbsolutePath());
					
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		}
	}
	
}
