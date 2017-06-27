/**
 * 
 */
package com.viksitpro.core.utilities;

import java.io.IOException;
import java.io.InputStream;
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
	}
	
	public static String getProperty(String property) {
		String deploymentType = "prod";
		deploymentType = properties.getProperty(property);
		return deploymentType;
	}
}
