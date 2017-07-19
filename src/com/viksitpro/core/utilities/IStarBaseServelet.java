package com.viksitpro.core.utilities;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;



public class IStarBaseServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IStarBaseServelet() {
		super();

	}
	
	public void printParams(HttpServletRequest request) {
		//System.out.println("Printing parameters of request:");
		for (String iterable_element : request.getParameterMap().keySet()) {
			//System.out.println(iterable_element+" : "+request.getParameter(iterable_element));
		}
	}
	

}