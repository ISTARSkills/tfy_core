/**
 * 
 */
package com.viksitpro.core.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author ComplexObject
 *
 */
public class SMSUtils {
	
	
	
	public void  sendSMS(String mobile, String message )
	{
		try{
			String url = "https://mobtexting.com/app/index.php/api?method=sms.normal&api_key=0c9ee1130f2a27302bbef3f39360a9eba5f7e48a&sender=TLNTFY&to="
					+ URLEncoder.encode(mobile, "UTF-8") + "&message="
					+ URLEncoder.encode(message, "UTF-8");
			//ViksitLogger.logMSG(this.getClass().getName(),url);
			URL url2 = new URL(url);
			InputStream is = url2.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );	
		    String line = null;
		    while( ( line = reader.readLine() ) != null )  {
		       //ViksitLogger.logMSG(this.getClass().getName(),line);
		    }
	    	reader.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
