package com.utilities.controller;		


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ReportExtractController")
public class ReportExtractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReportExtractController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reportId = 0;
		
		if(request.getParameter("key")!= null && request.getParameter("key").equalsIgnoreCase("Download CSV File")) {
			
			reportId = Integer.parseInt(request.getParameter("reportID"));
			
			
			System.err.println("<<<<<<<<<<>>>>>>>>>>>>> "+reportId);
			
		}
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
