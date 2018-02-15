/**
 * 
 */
package com.viksitpro.core.skill.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.SkillObjective;
import com.viksitpro.core.logger.ViksitLogger;
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
		ViksitLogger.logMSG(this.getClass().getName(),loIds);		
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
					
					sessionSkill.setLearningObjectives(los);
					sessionLevelskills.put(sessionSkill.getId(), sessionSkill);
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
			
			
			HashMap<Integer, ModuleLevelSkill> moduleLevelSkills = new HashMap<>();
			
			String findModuleLevelskill ="SELECT DISTINCT module_skill_id, session_skill_id, COALESCE(module_skill.creation_type,'SYSTEM_CREATED') as creation_type, module_skill.name FROM module_skill_session_skill_map, skill_objective module_skill WHERE session_skill_id IN ( SELECT DISTINCT session_skill_id FROM session_skill_lo_map WHERE learning_obj_id IN ("+loIds+") ) and module_skill.id = module_skill_session_skill_map.module_skill_id";
			List<HashMap<String, Object>> moduleLevelSkillsData = util.executeQuery(findModuleLevelskill);  
			for(HashMap<String, Object> moduleRow: moduleLevelSkillsData)
			{
				int moduleSkillId= (int)moduleRow.get("module_skill_id");
				String moduleSkillName = moduleRow.get("name").toString();
				String creationType = moduleRow.get("creation_type").toString();
				int sessionSkillId = (int)moduleRow.get("session_skill_id");
				
				if(moduleLevelSkills.containsKey(moduleSkillId))
				{
					// add session level skill in existing module
					ModuleLevelSkill modSkill = moduleLevelSkills.get(moduleSkillId);
					ArrayList<SessionLevelSkill> sessionLevelSkillPerMod = modSkill.getSessionLevelSkill();
					boolean sessionSkillalreadyPresent = false;
					for(SessionLevelSkill sessionInMod:  sessionLevelSkillPerMod)
					{
						if(sessionInMod.getId()==sessionSkillId)
						{
							sessionSkillalreadyPresent= true;
							break;							
						}	
					}
					
					if(!sessionSkillalreadyPresent)
					{						
						if(sessionLevelskills.containsKey(sessionSkillId))
						{
							sessionLevelSkillPerMod.add(sessionLevelskills.get(sessionSkillId));
						}
					}				
					modSkill.setSessionLevelSkill(sessionLevelSkillPerMod);
					moduleLevelSkills.put(moduleSkillId, modSkill);					
					
				}
				else
				{
					//create new module level skill
					ModuleLevelSkill modSkill = new ModuleLevelSkill();
					modSkill.setId(moduleSkillId);
					modSkill.setCreationType(creationType);
					modSkill.setSkillName(moduleSkillName);
					
					ArrayList<SessionLevelSkill> sessionLevelSkillPerMod = new ArrayList<>();
					if(sessionLevelskills.containsKey(sessionSkillId))
					{
						sessionLevelSkillPerMod.add(sessionLevelskills.get(sessionSkillId));
					}	
					modSkill.setSessionLevelSkill(sessionLevelSkillPerMod);
					moduleLevelSkills.put(moduleSkillId, modSkill);
					
				}	
			}
			
			
			ArrayList<ModuleLevelSkill> moduleLevelSkill = new ArrayList<>();
			for(int modSkillId: moduleLevelSkills.keySet())
			{
				moduleLevelSkill.add(moduleLevelSkills.get(modSkillId));				
			}
			
			courseLevelSkill.setModuleLevelSkill(moduleLevelSkill);			
		}
		
		return courseLevelSkill;
	}
	
	
	public static void main(String args[])
	{
		CoreSkillService cs = new CoreSkillService();
		cs.getShellSkillTreeForCourse(5);
		
	}
	
}
