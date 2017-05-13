/**
 * 
 */
package com.viksitpro.core.utilities;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @vauthor Vaibhav
 *
 */
public class IStarBaseServelet extends HttpServlet {
	
	private static final long serialVersionUID = 8222264765449309590L;

	public void printParams(HttpServletRequest request) {
		System.out.println("From IStarBaseServelet:");
		for (Object key : request.getParameterMap().keySet()) {
			String paramName = key.toString();
			Object paramValue = request.getParameter(key.toString());
			System.out.println("Param -> " + paramName + " : Value ->" + paramValue);
		}

	}

	public void printAttrs(HttpServletRequest request) {
		System.out.println("From IStarBaseServelet: ");
		for (Enumeration<String> enumeration = request.getAttributeNames(); enumeration.hasMoreElements();) {
			String attributeName = enumeration.nextElement();
			Object attribute = request.getAttribute(attributeName);
			System.out.println("Attr -> " + attributeName + " : Value ->" + attribute);
		}

	}
	
	public void printAll(HttpServletRequest request) {
		printParams(request);
		printAttrs(request);
	}

	public HashMap<String, String> getReqMap(HttpServletRequest request) {
		HashMap<String, String> data = new HashMap<String, String>();
		for (String iterable_element : request.getParameterMap().keySet()) {
			data.put(iterable_element, request.getParameter(iterable_element));
		}
		
		Enumeration<String> attrs = request.getAttributeNames();
		while (attrs.hasMoreElements()) {
			String name = (String) attrs.nextElement();
			String value = "" + request.getAttribute(name);
			data.put(name, value);
		}
		return data;
	}
}
