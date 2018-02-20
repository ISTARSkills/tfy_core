/**
 * 
 */
package com.viksitpro.core.exceptions;

/**
 * @author vaibhav
 *
 */
public class EntityNotFoundException extends Exception {

	public EntityNotFoundException() {
		super();
		
	}

	public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public EntityNotFoundException(String message) {
		super(message);
		
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
