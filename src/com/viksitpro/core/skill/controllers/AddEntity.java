package com.viksitpro.core.skill.controllers;		


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viksitpro.core.skill.services.CoreSkillService;
import com.viksitpro.core.utilities.DBUTILS;


@WebServlet("/add_entity")
public class AddEntity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddEntity() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int parent_skill_id = Integer.parseInt(request.getParameter("parent_skill_id"));
		String parent_type = request.getParameter("parent_type");
		String new_child_name = request.getParameter("new_child_name").replace("'", "");
		String sql="";
		DBUTILS util = new DBUTILS();
		if(parent_type.equalsIgnoreCase("CONTEXT"))
		{
			sql="insert into skill_objective (id, name, type, skill_level_type, creation_type, context) "
					+ "values((select COALESCE(max(id),0)+1 from skill_objective),'"+new_child_name+"','SKILL','MODULE','MANUAL',"+parent_skill_id+")";
			util.executeUpdate(sql);
		}
		else if(parent_type.equalsIgnoreCase("MODULE_LEVEL_SKILL"))
		{
			sql="insert into skill_objective (id, name, type, parent_skill, skill_level_type, creation_type) "
					+ "values((select COALESCE(max(id),0)+1 from skill_objective),'"+new_child_name+"','SKILL',"+parent_skill_id+",'CMSESSION','MANUAL') returning id";
			int sessionSkillId = util.executeUpdateReturn(sql);
			
			String addToMap = "insert into module_skill_session_skill_map (module_skill_id, session_skill_id) values("+parent_skill_id+","+sessionSkillId+")";
			util.executeUpdate(addToMap);
			
		}else if(parent_type.equalsIgnoreCase("SESSION_LEVEL_SKILL"))
		{
			sql="insert into skill_objective (id, name, type, parent_skill, skill_level_type, creation_type) "
					+ "values((select COALESCE(max(id),0)+1 from skill_objective),'"+new_child_name+"','LEARNING_OBJECTIVE',"+parent_skill_id+",'LESSON','MANUAL') returning id";
			int lo = util.executeUpdateReturn(sql);
			
			String addToMap = "insert into session_skill_lo_map (learning_obj_id, session_skill_id) values("+lo+","+parent_skill_id+")";
			util.executeUpdate(addToMap);
		}	
		
									
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
