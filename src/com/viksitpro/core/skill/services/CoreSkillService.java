/**
 * 
 */
package com.viksitpro.core.skill.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.CmsessionDAO;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.LessonDAO;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.ModuleDAO;
import com.viksitpro.core.dao.entities.SkillObjective;
import com.viksitpro.core.skill.pojo.CourseLevelSkill;
import com.viksitpro.core.skill.pojo.LearningObjective;
import com.viksitpro.core.skill.pojo.ModuleLevelSkill;
import com.viksitpro.core.skill.pojo.SessionLevelSkill;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author mayank
 *
 */
public class CoreSkillService {
	
	public CourseLevelSkill  getShellSkillTreeForCourse(int courseId)
	{
		CourseLevelSkill courseLevelSkill = new CourseLevelSkill();
		Course course = new CourseDAO().findById(courseId);
		HashMap<Integer,SkillObjective> totalLoIncourse = new HashMap<>();
		ArrayList<Integer> modulesInCourse = new ArrayList<>();
		ArrayList<Integer> sessionsInCourse = new ArrayList<>();
		ArrayList<Integer> lessonsInCourse = new ArrayList<>();
		
		for(Module module : course.getModules())
		{
			modulesInCourse.add(module.getId());
			for(Cmsession cms: module.getCmsessions())
			{
				sessionsInCourse.add(cms.getId());
				for(Lesson lesson : cms.getLessons())
				{
					lessonsInCourse.add(lesson.getId());
					for(SkillObjective skillObj : lesson.getSkillObjectives())
					{
						if(!totalLoIncourse.containsKey(skillObj.getId()))
						{
							totalLoIncourse.put(skillObj.getId(), skillObj);
						}	
					}	
					
				}	
			}
		}
		
		DBUTILS util = new DBUTILS();
		String loIds =StringUtils.join(totalLoIncourse.keySet(), ",");
		System.out.println(loIds);		
		if(!loIds.isEmpty()) {
			String findCMSessionLevelSkill ="select DISTINCT session_skill.id , session_skill.name,COALESCE(session_skill.creation_type, 'SYSTEM_CREATED') as creation_type,  session_skill_lo_map.learning_obj_id "
					+ "from session_skill_lo_map , skill_objective session_skill "
					+ "where session_skill_lo_map.learning_obj_id in ("+loIds+") and session_skill_lo_map.session_skill_id =  session_skill.id ";
			List<HashMap<String, Object>> sessionSkillIdData = util.executeQuery(findCMSessionLevelSkill);
			HashMap<Integer, SessionLevelSkill> sessionLevelskills = new HashMap<>();
			for(HashMap<String, Object> sessionSkillrow: sessionSkillIdData)
			{
				int sessionSkillId = (int)sessionSkillrow.get("id");
				String skillName = (String)sessionSkillrow.get("name");
				int loId = (int)sessionSkillrow.get("learning_obj_id");
				String creationType=  (String)sessionSkillrow.get("creation_type");
				if(sessionLevelskills.containsKey(sessionSkillId))
				{
					// Add Learning  objective to skill
					SessionLevelSkill sessionSkill = sessionLevelskills.get(sessionSkillId);
					ArrayList<LearningObjective> los = sessionSkill.getLearningObjectives();
					
					
				}
				else
				{
					//create session  level skill
					SessionLevelSkill sesionSkill = new SessionLevelSkill();
					sesionSkill.setCreationType(creationType);
					sesionSkill.setId(sessionSkillId);
					sesionSkill.setSkillName(skillName);
					ArrayList<LearningObjective> los = new ArrayList<>();
					
					SkillObjective lobj = totalLoIncourse.get(loId);
					
					LearningObjective loNew = new LearningObjective();
					loNew.setCreationType(lobj.getCreationType());
					loNew.setId(lobj.getId());
					loNew.setLearningObjectiveName(lobj.getName());
					ArrayList<Lesson> lessons = new ArrayList<>();
					for(Lesson lesson: lobj.getLessons())
					{
						if(lessonsInCourse.contains(lesson.getId()))
						{
							lessons.add(lesson);
						}	
					}
					loNew.setLessons(lessons);
					los.add(loNew);
					
					sesionSkill.setLearningObjectives(los);
					sessionLevelskills.put(sesionSkill.getId(), sesionSkill);
					
				}	
				
				
			}	
			
			
			String findModuleLevelskill ="select DISTINCT module_skill_id, session_skill_id from module_skill_session_skill_map where session_skill_id in (select DISTINCT session_skill_id from session_skill_lo_map where learning_obj_id in ("+loIds+"))";
			
			
		}
		
		return courseLevelSkill;
	}
	
	
	public static void main(String args[])
	{
		CoreSkillService cs = new CoreSkillService();
		cs.getShellSkillTreeForCourse(5);
	}
	
}
