/**
 * 
 */
package com.viksitpro.core.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;



/**
 * @author ISTAR-SERVER-PU-1
 *
 */
public class AppProperies {
	static Properties properties = new Properties();
	static{
		try {
			String propertyFileName = "app.properties";
			InputStream inputStream = AppProperies.class.getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		
		// read XML
	}
	
	public static String getProperty(String property) {
		String deploymentType = "prod";
		deploymentType = properties.getProperty(property);
		return deploymentType;
	}
	
	public static  String generatePopOver(String elementName) {
		try {
			DBUTILS db = new DBUTILS();
			HashMap<String, Object> item = db.executeQuery("select * from helper where element_name='"+elementName+"'").get(0);
			
			return "<i class='pull-right-margin-left fa fa-question-circle' aria-hidden='true' data-toggle='popover' "
					+ " data-trigger='hover' title='"+item.get("header")+"' data-content='"+item.get("body")+"'  data-placement='"+item.get("position")+"'></i>";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
}
