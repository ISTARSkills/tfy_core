/**
 * 
 */
package com.viksitpro.core.exceptions;

import java.util.Arrays;
import java.util.List;


/**
 * @author ComplexObject
 *
 */
public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	
	 String message;
	 String code;
	 
	 List<String> errors;
	
	
	
	private static final long serialVersionUID = 650431822779448401L;

	

	 @Override
	 public String getMessage(){
	        return message;
	    }

	

	public CustomException(String message, String code, List<String> errors)  {
		super();
		this.message = message;
		this.code = code;
		this.errors = errors;
		
		
	}
	
	public CustomException(String message, String code, String error)  {
		super();
		this.message = message;
		this.code = code;
		this.errors = Arrays.asList(error);
		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	 
	 
	 
}
