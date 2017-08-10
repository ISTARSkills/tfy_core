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
public class SkillService {

	
	public CourseLevelSkill createOrUpdateCourseSkillTree(int courseId)
	{
		DBUTILS util = new  DBUTILS();
		CourseLevelSkill courseLevelSkill = new CourseLevelSkill();
		Course course = new CourseDAO().findById(courseId); 
		if(course!=null)
		{
			
			Integer courseSkillId = courseId;
			String courseLevelSkillName = course.getCourseName().trim().replace("'", "");			
			ArrayList<ModuleLevelSkill> modLevelSkills = new ArrayList<>();			
			Set<Integer>perfectModules = new HashSet();			
			String findModuleSkill ="select * from skill_objective where context = "+courseSkillId+" and skill_level_type='MODULE'";
			List<HashMap<String, Object>> moduleLevelSkillsData = util.executeQuery(findModuleSkill);
			if(moduleLevelSkillsData.size()>0)
			{
				//context is present
				for(HashMap<String, Object> modSkillRow : moduleLevelSkillsData)
				{
					int modSkillId = (int)modSkillRow.get("id");
					String modSkillName = modSkillRow.get("name").toString();
					String creationType = modSkillRow.get("creation_type").toString();
					ModuleLevelSkill modSkill = new ModuleLevelSkill();
					modSkill.setId(modSkillId);
					modSkill.setSkillName(modSkillName);
					modSkill.setCreationType(creationType);	
					ArrayList<Module> modulesInModSkill = new ArrayList<>();
					ArrayList<SessionLevelSkill> sessionSkillsFromAllMod = new ArrayList<>();
					String findMappedModules="select distinct module_skill_objective.module_id from module_skill_objective, module_course where skill_objective_id ="+modSkillId+" " + 
							"and module_course.module_id = module_skill_objective.module_id\r\n" + 
							"and module_course.course_id = "+courseId;
					List<HashMap<String, Object>> mappedMoDules = util.executeQuery(findMappedModules);
					if(mappedMoDules.size()>0)
					{
						for(HashMap<String, Object> mappedModule : mappedMoDules)
						{
							int modId = (int)mappedModule.get("module_id");
							perfectModules.add(modId);
							Module defaulterModule = new ModuleDAO().findById(modId);
							modulesInModSkill.add(defaulterModule);
							ArrayList<SessionLevelSkill> sessionLevelSkills = getSessionLevelSkillForModuleSkill(modSkillId,course,modId);
							if(sessionLevelSkills.size()==0)
							{							
								//this means this module is orphan, there is no session skill for this module
								//create all sessions under this module as session skill.
								
								for(Cmsession cms : defaulterModule.getCmsessions())
								{
									SessionLevelSkill cmsSkill = createSessionLevelSkill(cms, modSkillId, course);
									sessionLevelSkills.add(cmsSkill);
								}	
								
							}
							sessionSkillsFromAllMod.addAll(sessionLevelSkills);
		 					
						}	
						
						//remove duplicate session skill object
						ArrayList<Integer> duplicateSessionSkillId = new ArrayList<>();
						ArrayList<SessionLevelSkill> temp = sessionSkillsFromAllMod;
						sessionSkillsFromAllMod = new ArrayList<>();
						
						for(SessionLevelSkill sk: temp)
						{
							if(!duplicateSessionSkillId.contains(sk.getId()))
							{
								duplicateSessionSkillId.add(sk.getId());
								sessionSkillsFromAllMod.add(sk);
							}	
						}
						//removed duplicate session skill object
						modSkill.setModules(modulesInModSkill);
						modSkill.setSessionLevelSkill(sessionSkillsFromAllMod);
						modLevelSkills.add(modSkill);
					}	
					
				}	
				
			}
				//lets handle orphan modules
				for(Module module : course.getModules())
				{
					if(!perfectModules.contains(module.getId()))
					{
						perfectModules.add(module.getId());
						
						ModuleLevelSkill modSkill = createModuleLevelSkill(module, course);
						createSkillModuleMapping(modSkill,module,course);
						ArrayList<Module> modInSkill = new ArrayList<>();
						modInSkill.add(module);
						modSkill.setModules(modInSkill);
						modLevelSkills.add(modSkill);
					}	
					
				}
				
			courseLevelSkill.setId(courseId);
			courseLevelSkill.setSkillName(courseLevelSkillName);
			courseLevelSkill.setCreationType("AUTO");
			courseLevelSkill.setModuleLevelSkill(modLevelSkills);
			
			
		
		}
		
		
		
		return courseLevelSkill;
	}

	private void createSkillModuleMapping(ModuleLevelSkill modSkill, Module module, Course course) {
		DBUTILS util = new DBUTILS();
		String mapping ="insert into module_skill_objective (module_id, skill_objective_id) values("+module.getId()+","+modSkill.getId()+")";
		util.executeUpdate(mapping);
		
	}

	private ArrayList<SessionLevelSkill> getSessionLevelSkillForModuleSkill(int modSkillId, Course course, int modId) {
		DBUTILS util = new DBUTILS();
		
		Module m = new ModuleDAO().findById(modId);
		
		String findCMSSkill ="select * from skill_objective where context = "+course.getId()+" and skill_level_type='CMSESSION' and parent_skill="+modSkillId;
		List<HashMap<String, Object>> cmsLevelSkillsData = util.executeQuery(findCMSSkill);
		ArrayList<SessionLevelSkill> sessionSkills = new ArrayList<>();
		ArrayList<Integer> perfectCMSession = new ArrayList<Integer>();
		ArrayList<LearningObjective> losFromAllLesson = new ArrayList<>();
		if(cmsLevelSkillsData.size()>0)
		{
			
			for(HashMap<String, Object> cmsSkillRow : cmsLevelSkillsData)
			{
				int cmsSkillId = (int)cmsSkillRow.get("id");
				String cmsSkillName = cmsSkillRow.get("name").toString();
				String creationType = cmsSkillRow.get("creation_type").toString();
				SessionLevelSkill cmsSkill = new SessionLevelSkill();
				cmsSkill.setId(cmsSkillId);
				cmsSkill.setSkillName(cmsSkillName);
				cmsSkill.setCreationType(creationType);
				
				String findmappedCMSession = "select DISTINCT cmsession_skill_objective.cmsession_id from cmsession_skill_objective, cmsession_module , module_course\r\n" + 
						"where skill_objective_id ="+cmsSkillId+ 
						"and cmsession_module.cmsession_id = cmsession_skill_objective.cmsession_id\r\n" + 
						"and module_course.module_id = cmsession_module.module_id\r\n" + 
						"and cmsession_module.module_id = "+modId + 
						"and module_course.course_id = "+course.getId();
				
				List<HashMap<String, Object>> mappedCMSData = util.executeQuery(findmappedCMSession);
				if(mappedCMSData.size()>0)
				{
					for(HashMap<String, Object> mappedCMSRow: mappedCMSData)
					{
						int cmsId = (int) mappedCMSRow.get("cmsession_id");
						perfectCMSession.add(cmsId);
						ArrayList<LearningObjective> los = getLoForSessionLevelskill(cmsSkillId,course, cmsId);
						if(los.size()==0)
						{
							Cmsession cms = new CmsessionDAO().findById(cmsId);
							for(Lesson l : cms.getLessons())
							{
								LearningObjective lo = createLO(cmsSkillId, l, course);
								los.add(lo);
							}	
						}
						losFromAllLesson.addAll(los);
					}
					
					//remove duplicate los
					ArrayList<Integer> duplicateLO = new ArrayList<>();
					ArrayList<LearningObjective> temp = losFromAllLesson;
					losFromAllLesson = new ArrayList<>();
					
					for(LearningObjective sk: temp)
					{
						if(!duplicateLO.contains(sk.getId()))
						{
							duplicateLO.add(sk.getId());
							losFromAllLesson.add(sk);
						}	
					}
					//removed duplocate los
					cmsSkill.setLearningObjectives(losFromAllLesson);
					sessionSkills.add(cmsSkill);
				}	
				
				
				
				
				
				
				
			}	
			
		}
		
		
		for(Cmsession cms : m.getCmsessions())
		{
			if(!perfectCMSession.contains(cms.getId()))
			{
				SessionLevelSkill sessionLevelSkill =  createSessionLevelSkill(cms, modSkillId, course);
				createSkillSessionMapping(sessionLevelSkill, cms, course);
				sessionSkills.add(sessionLevelSkill);
			}
		}
		
		return sessionSkills;
	}

	private ArrayList<LearningObjective> getLoForSessionLevelskill(int cmsSkillId, Course course, int cmsId) {
		Cmsession cms = new CmsessionDAO().findById(cmsId);
		ArrayList<LearningObjective> los = new ArrayList<>();
		
		DBUTILS util = new DBUTILS();
		String findlo ="select * from skill_objective where context = "+course.getId()+" and skill_level_type='LESSON' and type='LEARNING_OBJECTIVE' and parent_skill="+cmsSkillId;
		List<HashMap<String, Object>> loData = util.executeQuery(findlo);
		
		if(loData.size()>0)
		{
			ArrayList<Integer> perfectLesson = new ArrayList<>();
			
			for(HashMap<String, Object> loRow : loData)
			{
				int loId = (int)loRow.get("id");
				String loTitle =loRow.get("name").toString();
				LearningObjective lo = new LearningObjective();
				lo.setId(loId);
				lo.setLearningObjectiveName(loTitle);
				lo.setCreationType("SYSTEM_CREATED");
				ArrayList<Lesson> loLessons = new ArrayList<>();
				String getMappedLesson="select DISTINCT lesson_skill_objective.lessonid from  cmsession_module , module_course, lesson_skill_objective, lesson_cmsession "
						+ "where learning_objectiveid = "+loId+" and lesson_skill_objective.lessonid = lesson_cmsession.lesson_id "
						+ "and cmsession_module.cmsession_id = lesson_cmsession.cmsession_id and module_course.module_id = cmsession_module.module_id "
						+ "and lesson_cmsession.cmsession_id = "+cmsId+" and module_course.course_id = "+course.getId();
				List<HashMap<String, Object>> mappedLEsosnData = util.executeQuery(getMappedLesson);
				if(mappedLEsosnData.size()>0)
				{
					for(HashMap<String, Object> lrow: mappedLEsosnData)
					{
						int lid = (int)lrow.get("lessonid");
						perfectLesson.add(lid);
						
						Lesson l = new LessonDAO().findById(lid);
						loLessons.add(l);
					}
					lo.setLessons(loLessons);
					los.add(lo);
				}
				
			}
			
			for(Lesson lesson : cms.getLessons())
			{
				if(!perfectLesson.contains(lesson.getId()))
				{
					LearningObjective lo =  createLO(cmsSkillId, lesson, course);
					createLOLessonMapping(lo.getId(), lesson.getId(), course.getId());
					los.add(lo);
				}
			}	
			
			
		}	
			
		return los;
	}

	private ModuleLevelSkill createModuleLevelSkill(Module module, Course course) {
		String createModuleSkillForThisCourse = "INSERT INTO skill_objective (id, type, name, parent_skill, skill_level_type, context,creation_type) \r\n" + 
				"VALUES ((select COALESCE(max(id),0)+1 from skill_objective), 'SKILL', '"+module.getModuleName().trim()+"', NULL, 'MODULE',"+course.getId()+",'SYSTEM_CREATED') returning id;";
		System.out.println(createModuleSkillForThisCourse);
		DBUTILS util = new DBUTILS();
		ModuleLevelSkill modSkill = new  ModuleLevelSkill();
		int moduleSkillId = util.executeUpdateReturn(createModuleSkillForThisCourse);
		modSkill.setId(moduleSkillId);
		modSkill.setSkillName(module.getModuleName().trim());
		modSkill.setCreationType("SYSTEM_CREATED");
		ArrayList<SessionLevelSkill> sessionSkills = new ArrayList<>();
		for(Cmsession cms : module.getCmsessions())
		{
			SessionLevelSkill sessionLevelSkill = createSessionLevelSkill(cms, moduleSkillId, course);
			createSkillSessionMapping(sessionLevelSkill, cms, course);
			sessionSkills.add(sessionLevelSkill);
		}
		modSkill.setSessionLevelSkill(sessionSkills);
		return modSkill;
	}

	private void createSkillSessionMapping(SessionLevelSkill sessionLevelSkill, Cmsession cms, Course course) {
		
		DBUTILS util = new DBUTILS();
		String mapping ="insert into cmsession_skill_objective (cmsession_id, skill_objective_id) values("+cms.getId()+","+sessionLevelSkill.getId()+")";
		util.executeUpdate(mapping);
	}

	private SessionLevelSkill createSessionLevelSkill(Cmsession cms, Integer moduleSkillId, Course course) {
		String createSessionSkillForThisCourse = "INSERT INTO skill_objective (id, type, name, parent_skill, skill_level_type, context,creation_type) \r\n" + 
				"VALUES ((select COALESCE(max(id),0)+1 from skill_objective), 'SKILL', '"+cms.getTitle().trim()+"',"+moduleSkillId+" , 'CMSESSION',"+course.getId()+",'SYSTEM_CREATED') returning id;";
		System.out.println(createSessionSkillForThisCourse);
		DBUTILS util = new DBUTILS();
		SessionLevelSkill sessionSkill = new  SessionLevelSkill();
		int sessionSkillId = util.executeUpdateReturn(createSessionSkillForThisCourse);
		sessionSkill.setId(sessionSkillId);
		sessionSkill.setSkillName(cms.getTitle().trim());
		sessionSkill.setCreationType("SYSTEM_CREATED");
		
		ArrayList<LearningObjective> los = new ArrayList<>();
		for(Lesson l : cms.getLessons())
		{
			LearningObjective lo = createLO(sessionSkillId,l, course);
			createLOLessonMapping(lo.getId(),l.getId(),course.getId());
			ArrayList<Lesson> lessonsOfLO = new ArrayList<>();
			lessonsOfLO.add(l);
			lo.setLessons(lessonsOfLO);
			los.add(lo);
		}
		sessionSkill.setLearningObjectives(los);
		return sessionSkill;
	}

	private void createLOLessonMapping(Integer loId, Integer lessonId,Integer courseId) {
		DBUTILS util = new DBUTILS();
		String mapping ="insert into lesson_skill_objective (lessonid, learning_objectiveid, context_id) values("+lessonId+","+loId+", "+courseId+")";
		util.executeUpdate(mapping);
	}

	private LearningObjective createLO(int sessionSkillId, Lesson l, Course course) {
		String createLOForSession = "INSERT INTO skill_objective (id, type, name, parent_skill, skill_level_type, context,creation_type) \r\n" + 
				"VALUES ((select COALESCE(max(id),0)+1 from skill_objective), 'LEARNING_OBJECTIVE', '"+l.getTitle().trim()+"',"+sessionSkillId+" , 'LESSON',"+course.getId()+",'SYSTEM_CREATED') returning id;";
		System.out.println(createLOForSession);
		DBUTILS util = new DBUTILS();
		LearningObjective lo = new  LearningObjective();
		int loId = util.executeUpdateReturn(createLOForSession);
		lo.setId(loId);
		lo.setLearningObjectiveName(l.getTitle());;;
		lo.setCreationType("SYSTEM_CREATED");
		return lo;
	}
	
}
