package com.viksitpro.core.skill.controllers;		


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viksitpro.core.utilities.DBUTILS;


@WebServlet("/add_lo_to_question")
public class AddLoToQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddLoToQuestion() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int questionId = Integer.parseInt(request.getParameter("question_id"));
		String [] los = request.getParameterValues("los[]");
		DBUTILS util = new DBUTILS();
		if(los.length!=0)
		{
			String deleteOldLOMap ="delete from question_skill_objective where questionid="+questionId;
			util.executeUpdate(deleteOldLOMap);
			
			String findContextOfLo ="select distinct lo.id , mod_skill.context from skill_objective lo, session_skill_lo_map slp, module_skill_session_skill_map msp,skill_objective mod_skill where lo.id in ("+String.join(",", los)+") and lo.id = slp.learning_obj_id and slp.session_skill_id = msp.session_skill_id and msp.module_skill_id = mod_skill.id  ;";
			List<HashMap<String, Object>> loContextMap = util.executeQuery(findContextOfLo);
			for(HashMap<String, Object> row:  loContextMap)
			{
				int loId = (int)row.get("id");
				int context = (int)row.get("context");
				String insertIntoLessonSkillObjective ="insert into question_skill_objective (questionid, learning_objectiveid, context_id) values("+questionId+","+loId+","+context+");";
				util.executeUpdate(insertIntoLessonSkillObjective);
			}	
		}							
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
