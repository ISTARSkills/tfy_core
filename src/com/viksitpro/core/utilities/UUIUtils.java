/**
 * 
 */
package com.viksitpro.core.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.apache.log4j.Logger;



/**
 * @author vaibhav
 *
 */
public class UUIUtils {
	static Logger log = Logger.getLogger(UUIUtils.class.getName());

	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static void printlog(Object message) {
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
		Date date = new Date();
		String timeStampString = format.format(date);
		log.error(timeStampString + " " + message.toString());
		//System.err.println(message.toString());
	}

	public static void printlog(Object cname, Object message) {
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
		Date date = new Date();
		String timeStampString = format.format(date);
		log.error(timeStampString + " - " + cname + " - " + message.toString());
		//System.err.println(message.toString());
	}
}
