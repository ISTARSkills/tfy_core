package com.viksitpro.core.skill.controllers;		


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viksitpro.core.skill.services.CoreSkillService;


@WebServlet("/skill_tree_creator")
public class SkillTreeCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SkillTreeCreator() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String referrer= request.getHeader("referer");
		String creationType =request.getParameter("creation_type");
		
		switch (creationType) {
		case "COURSE_TREE":
			//create entire course tree for course.
			int courseId = Integer.parseInt(request.getParameter("course_id"));
			CoreSkillService service = new CoreSkillService();
			//service.createOrUpdateCourseSkillTree(courseId);
			break;
		default:
			break;
		}
				
		
		response.sendRedirect(referrer);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
