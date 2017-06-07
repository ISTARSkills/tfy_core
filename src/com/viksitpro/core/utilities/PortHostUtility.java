/**
 * 
 */
package com.viksitpro.core.utilities;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author ISTAR-SERVER-PU-1
 *
 */
public class PortHostUtility {

	public  boolean isLocalPortInUse(int port) {
	    try {
	        // ServerSocket try to open a LOCAL port
	        new ServerSocket(port).close();
	        // local port can be opened, it's available
	        return false;
	    } catch(IOException e) {
	        // local port cannot be opened, it's in use
	        return true;
	    }
	}
}
