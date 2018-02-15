/**
 * 
 */
package com.viksitpro.core.logger;

import org.apache.log4j.Logger;

/**
 * @author vaibhav
 *
 */
public class ViksitLogger {
	public static final Logger LOGGER = Logger.getLogger(ViksitLogger.class);
	
	public static void logMSG(String className,Object message) {
		LOGGER.info(className + " --- "+ message.toString() );
	}
	public static void logMSGERROR(String className,Object message) {
		LOGGER.error(className + " --- "+ message.toString() );
	}
}
