/**
 * 
 */
package com.viksitpro.core.services.powerpoint;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.viksitpro.core.exceptions.EntityNotFoundException;
import com.viksitpro.core.utilities.AppProperies;


/**
 * @author Mayank
 *
 */
public class ImageOptimizer{

	private String directoryPath;
	public ImageOptimizer(String directoryPath) {
		super();
		this.directoryPath = directoryPath;
	}	

	public void optimize() throws EntityNotFoundException
	{
		if(directoryPath!=null)
		{
			File directory = new File(directoryPath);
		    if (directory.exists()){
		    	//ViksitLogger.logMSG(this.getClass().getName(),"directory exist");
		    	String tinifyKey = null;		
				tinifyKey = AppProperies.getProperty("tinify_key");
				if(tinifyKey==null)
				{
					throw new EntityNotFoundException("tinify_key is not defined in app.properties");
				}
				
				File[] directoryListing = directory.listFiles();
				int i =0;
				if (directoryListing != null && directoryListing.length>0) {
					 ExecutorService excuters = Executors.newFixedThreadPool(directoryListing.length);
					 for(File f : directoryListing)			 
					 {
						// ViksitLogger.logMSG(this.getClass().getName(),i++);
						 //ViksitLogger.logMSG(this.getClass().getName(),f.getAbsolutePath());
						if(!f.getName().toLowerCase().contains("xml"))
						{
							ImageResizeAndCompress resizer = new ImageResizeAndCompress(f.getAbsolutePath(), tinifyKey);
							excuters.execute(resizer);
						}
					 }
				}	 
		    }	
		}
		else
		{
			//ViksitLogger.logMSG(this.getClass().getName(),"directory path is null");
		}	
	}

	
}
