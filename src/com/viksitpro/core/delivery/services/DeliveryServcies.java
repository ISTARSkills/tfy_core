/**
 * 
 */
package com.viksitpro.core.delivery.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.CmsessionDAO;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.LessonDAO;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.ModuleDAO;
import com.viksitpro.core.dao.entities.SkillObjective;
import com.viksitpro.core.delivery.pojo.DeliveryCourse;
import com.viksitpro.core.delivery.pojo.DeliveryLesson;
import com.viksitpro.core.delivery.pojo.DeliveryModule;
import com.viksitpro.core.delivery.pojo.DeliverySession;
import com.viksitpro.core.skill.pojo.LearningObjective;
import com.viksitpro.core.skill.pojo.ModuleLevelSkill;
import com.viksitpro.core.skill.pojo.SessionLevelSkill;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author mayank
 *
 */
public class DeliveryServcies {
/*
	public DeliveryCourse getDeliveryCourseTree(int courseId)
	{
		DeliveryCourse dCourse = new DeliveryCourse();		
		
		Course course = new CourseDAO().findById(courseId);	
		ArrayList<DeliveryModule> modules = getDeliveryModulesForCourse(courseId);				
		dCourse.setModules(modules);
		dCourse.setCourseName(course.getCourseName());
		dCourse.setId(course.getId());
		ArrayList<ModuleLevelSkill> moduleLevelSkills = getModuleLevelSkillsForCourse(courseId);		
		dCourse.setModuleLevelSkill(moduleLevelSkills);
		
		ArrayList<Integer> mappedModules = new ArrayList<>();
		for(ModuleLevelSkill modSkill : moduleLevelSkills)
		{
			for(Module mod : modSkill.getModules())
			{
				if(!mappedModules.contains(mod.getId()))
				{
					mappedModules.add(mod.getId());
				}	
			}	
		}
		dCourse.setMappedModules(mappedModules);				
		
		//lets check if for a course all the modules are mapped to module skill, cmsesssion are mapped to cmsesison skill and lesson is mapped to lo.
		if(mappedModules.size()==modules.size())
		{
			louterloop:
			for(DeliveryModule m: modules)
			{
				if(!m.getIsPerfect())
				{
					dCourse.setIsPerfect(false);
					break louterloop;
				}
				else
				{
					dCourse.setIsPerfect(true);	
				}	
			}
			
		}
		else
		{
			dCourse.setIsPerfect(false);
		}			
		return dCourse;
	}

	
	private ArrayList<DeliveryModule> getDeliveryModulesForCourse(int courseId) {
		ArrayList<DeliveryModule> deliveryModules = new ArrayList<>();
		Course c = new CourseDAO().findById(courseId);
		for(Module module : c.getModules())
		{
			DeliveryModule mod = new DeliveryModule();
			mod.setId(module.getId());
			mod.setModuleName(module.getModuleName());
			ArrayList<DeliverySession> sessions = getDeliverySessionForModule(module.getId(),courseId);
			mod.setSessions(sessions);
			
			ArrayList<ModuleLevelSkill>moduleLevelSkills = getModuleLevelSkillForModule(module.getId(),courseId);
			mod.setModuleLevelSkills(moduleLevelSkills);
			
			ArrayList<SessionLevelSkill>sesionLevelSkills = new ArrayList<>(); 
			ArrayList<Integer>sessionSkillCounter = new ArrayList<>();
			for(ModuleLevelSkill ms : moduleLevelSkills)
			{
				
				for(Module m : ms.getModules())
				{
					if(m.getId()==module.getId())
					{
						for(SessionLevelSkill sss: ms.getSessionLevelSkill())
						{
							if(!sessionSkillCounter.contains(sss.getId()))
							{
								sessionSkillCounter.add(sss.getId());
								sesionLevelSkills.add(sss);
							}	
						}	
						
					}	
				}	
			}			
			mod.setSessionLevelSkills(sesionLevelSkills);
			
			ArrayList<Integer> mappedSessions = new ArrayList<>();
			for(SessionLevelSkill sessionSkill : sesionLevelSkills)
			{
				if(sessionSkill.getSessions()!=null) {
					for(Cmsession cms : sessionSkill.getSessions())
					{
							
						if(!mappedSessions.contains(cms.getId()))
						{
							for(Module mm : cms.getModules())
							{
								if(mm.getId()==module.getId())
								{
									mappedSessions.add(cms.getId());
								}	
							}
							
						}	
					}
				}
			}			
			mod.setMappedSessions(mappedSessions);
			
			if(mappedSessions.size()==sessions.size())
			{
				
				for(DeliverySession ds : sessions)
				{
					if(ds.getIsPerfect())
					{
						mod.setIsPerfect(true);
					}
					else
					{
						mod.setIsPerfect(false);
						break ;
					}	
				}	
			}
			else
			{
				mod.setIsPerfect(false);
			}	
			deliveryModules.add(mod);
		}	
		return deliveryModules;
	}


	private ArrayList<ModuleLevelSkill> getModuleLevelSkillForModule(Integer moduleId, int courseId) {
		ArrayList<ModuleLevelSkill> modSkills = new ArrayList<>();
		for(ModuleLevelSkill modSkill : getModuleLevelSkillsForCourse(courseId))
		{
			for(Module m : modSkill.getModules())
			{
				if(m.getId()==moduleId)
				{
					modSkills.add(modSkill);
					break;
				}
			}
		}
		return modSkills;
	}


	


	private ArrayList<DeliverySession> getDeliverySessionForModule(Integer moduleId, Integer courseId) {
		ArrayList<DeliverySession> sessions = new ArrayList<>();
		Module module = new ModuleDAO().findById(moduleId);
		for(Cmsession cms : module.getCmsessions())
		{
			DeliverySession session = new DeliverySession();
			session.setId(cms.getId());
			session.setSessionName(cms.getTitle());
			ArrayList<DeliveryLesson> lessons = getDeliveryLessonForSession(cms.getId(), courseId);
			session.setLessons(lessons);
			
			ArrayList<SessionLevelSkill>sessionLevelSkills = getSessionLevelSkillForCMS(cms.getId(), module.getId(), courseId);
			session.setSessionSkills(sessionLevelSkills);
			
			ArrayList<LearningObjective> losForSession = new ArrayList<>();
			for(SessionLevelSkill sk : sessionLevelSkills)
			{
				for(Cmsession cm: sk.getSessions())
				{
					if(cm.getId()==cms.getId())
					{
						losForSession.addAll(sk.getLearningObjectives());;
					}	
				}
			}
			session.setLos(losForSession);
			
			ArrayList<Integer>mappedLessons = new ArrayList<>();
			for(LearningObjective lo : losForSession)
			{
				for(Lesson l : lo.getLessons())
				{
					if(!mappedLessons.contains(l.getId()))
					{
						mappedLessons.add(l.getId());
					}	
				}	
			}
			session.setMappedLessons(mappedLessons);
			if(lessons.size()==mappedLessons.size())
			{
				for(DeliveryLesson l: lessons)
				{
					if(!l.getIsPerfect())
					{
						session.setIsPerfect(false);
						break;
					}
					else
					{
						session.setIsPerfect(true);
					}	
				}	
			}
			else
			{
				session.setIsPerfect(false);
			}	
			sessions.add(session);
		}	
		return sessions ;
	}
	


	private ArrayList<SessionLevelSkill> getSessionLevelSkillForCMS(Integer sessionId, Integer moduleId, Integer courseId) {
		ArrayList<SessionLevelSkill> sesisonSkills = new ArrayList<>();
		
		String getCMSSkill ="select distinct  cms_skill.* from cmsession_skill_objective , skill_objective cms_skill, skill_objective mod_skill, module_skill_objective\r\n" + 
				"where \r\n" + 
				"cmsession_skill_objective.cmsession_id = "+sessionId+"\r\n" + 
				"and cmsession_skill_objective.skill_objective_id =   cms_skill.id \r\n" + 
				"and cms_skill.parent_skill = mod_skill.id \r\n" + 
				"and cms_skill.skill_level_type='CMSESSION'\r\n" + 
				"and mod_skill.id = module_skill_objective.skill_objective_id\r\n" + 
				"and module_skill_objective.module_id = "+moduleId+"\r\n" + 
				"and mod_skill.skill_level_type='MODULE'\r\n" + 
				"and cms_skill.context = "+courseId+"\r\n" + 
				"and mod_skill.context = "+courseId+"";
		DBUTILS util = new DBUTILS();
		List<HashMap<String, Object>> cmsSkillsData = util.executeQuery(getCMSSkill);
		for(HashMap<String, Object> row: cmsSkillsData)
		{
			int id = (int)row.get("id");
			String name =row.get("name").toString();
			String creationtype =row.get("creation_type").toString();
			SessionLevelSkill cmsSkill = new SessionLevelSkill();
			cmsSkill.setId(id);
			cmsSkill.setSkillName(name);
			cmsSkill.setCreationType(creationtype);
			ArrayList<LearningObjective> losFromAllLesson = new ArrayList<>();
			String findmappedCMSession = "select DISTINCT cmsession_skill_objective.cmsession_id from cmsession_skill_objective, cmsession_module , module_course\r\n" + 
					"where skill_objective_id ="+id+ 
					"and cmsession_module.cmsession_id = cmsession_skill_objective.cmsession_id\r\n" + 
					"and module_course.module_id = cmsession_module.module_id\r\n" + 
					"and cmsession_module.module_id = "+moduleId + 
					"and module_course.course_id = "+courseId;
			
			List<HashMap<String, Object>> mappedCMSData = util.executeQuery(findmappedCMSession);
			if(mappedCMSData.size()>0)
			{
				ArrayList<Cmsession> sessions = new ArrayList<>();
				for(HashMap<String, Object> mappedCMSRow: mappedCMSData)
				{
					int cmsId = (int) mappedCMSRow.get("cmsession_id");
					Cmsession cms  =  new CmsessionDAO().findById(cmsId);
					sessions.add(cms);
					ArrayList<LearningObjective> los = getLoForSessionLevelskill(id,courseId, cmsId);
					losFromAllLesson.addAll(los);
				}
				
				cmsSkill.setSessions(sessions);
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
				sesisonSkills.add(cmsSkill);
			}
		}	
		
		return sesisonSkills;
	}

	private ArrayList<LearningObjective> getLoForSessionLevelskill(int cmsSkillId, int courseId, int cmsId) {
		
		ArrayList<LearningObjective> los = new ArrayList<>();
		
		DBUTILS util = new DBUTILS();
		String findlo ="select * from skill_objective where context = "+courseId+" and skill_level_type='LESSON' and type='LEARNING_OBJECTIVE' and parent_skill="+cmsSkillId;
		List<HashMap<String, Object>> loData = util.executeQuery(findlo);
		
		if(loData.size()>0)
		{
			ArrayList<Integer> perfectLesson = new ArrayList<>();
			
			for(HashMap<String, Object> loRow : loData)
			{
				int loId = (int)loRow.get("id");
				String loTitle =loRow.get("name").toString();
				String creationType = loRow.get("creation_type").toString();
				LearningObjective lo = new LearningObjective();
				lo.setId(loId);
				lo.setLearningObjectiveName(loTitle);
				lo.setCreationType(creationType);
				ArrayList<Lesson> loLessons = new ArrayList<>();
				String getMappedLesson="select DISTINCT lesson_skill_objective.lessonid from  cmsession_module , module_course, lesson_skill_objective, lesson_cmsession "
						+ "where learning_objectiveid = "+loId+" and lesson_skill_objective.lessonid = lesson_cmsession.lesson_id "
						+ "and cmsession_module.cmsession_id = lesson_cmsession.cmsession_id and module_course.module_id = cmsession_module.module_id "
						+ "and lesson_cmsession.cmsession_id = "+cmsId+" and module_course.course_id = "+courseId;
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
			
		}	
			
		return los;
	}
	

	private ArrayList<DeliveryLesson> getDeliveryLessonForSession(Integer sessionId, Integer courseId) {
		ArrayList<DeliveryLesson>lessons = new ArrayList<>();
		Cmsession cms = new CmsessionDAO().findById(sessionId);
		for(Lesson l : cms.getLessons())
		{
			DeliveryLesson lesson = new DeliveryLesson();
			lesson.setId(l.getId());
			lesson.setLessonName(l.getTitle());
			ArrayList<LearningObjective> learningObjectivesForLesson = getlearningObjectivesForLesson(l.getId(), courseId);
			lesson.setMappedLO(learningObjectivesForLesson);
			if(learningObjectivesForLesson.size()>0)
			{
				lesson.setIsPerfect(true);
			}
			else
			{
				lesson.setIsPerfect(false);
			}	
			lessons.add(lesson);
		}	
		return lessons;
	}


	


	private ArrayList<LearningObjective> getlearningObjectivesForLesson(Integer lessonId, Integer courseId) {
		ArrayList<LearningObjective> los = new ArrayList<>();
		Lesson l = new LessonDAO().findById(lessonId);		
		for(SkillObjective lo : l.getSkillObjectives())
		{
			if(lo.getContext().intValue()==courseId.intValue())
			{
				LearningObjective lobj = new LearningObjective();
				lobj.setCreationType(lo.getCreationType());
				lobj.setId(lo.getId());
				lobj.setLearningObjectiveName(lo.getName());
				ArrayList<Lesson> lessonsInLo= new ArrayList<>();
				for(Lesson lesson : lo.getLessons())
				{
					boolean lessonBelongToCurrentCourse = false;
					for (Cmsession cms : lesson.getCmsessions())
					{
						for(Module m : cms.getModules())
						{
							for(Course c : m.getCourses())
							{
								if(c.getId()==courseId)
								{
									lessonBelongToCurrentCourse = true;
								}	
							}	
						}	
					}
					if(lessonBelongToCurrentCourse)
					{
						lessonsInLo.add(lesson);
					}	
				}
				lobj.setLessons(lessonsInLo);
				los.add(lobj);
			}	
		}	
		return los;
	}


	private ArrayList<ModuleLevelSkill> getModuleLevelSkillsForCourse(int courseId) {
		ArrayList<ModuleLevelSkill> moduleLevelSkills = new ArrayList<>(); 
		DBUTILS util = new DBUTILS();
		String findModuleSkill ="select * from skill_objective where context = "+courseId+" and skill_level_type='MODULE'";
		List<HashMap<String, Object>> moduleLevelSkillsData = util.executeQuery(findModuleSkill);
		if(moduleLevelSkillsData.size()>0)
		{
			for(HashMap<String, Object> modSkillRow : moduleLevelSkillsData)
			{
				int modSkillId = (int)modSkillRow.get("id");
				String modSkillName = modSkillRow.get("name").toString();
				String creationType = modSkillRow.get("creation_type").toString();
				ModuleLevelSkill modSkill = new ModuleLevelSkill();
				modSkill.setId(modSkillId);
				modSkill.setSkillName(modSkillName);
				modSkill.setCreationType(creationType);
				ArrayList<SessionLevelSkill> sessionLevelSkillInAllMod = new ArrayList<>();
				ArrayList<Module> modulesInModSkill = new ArrayList<>();
				String findMappedModules="select distinct module_skill_objective.module_id from module_skill_objective, module_course where skill_objective_id ="+modSkillId+" " + 
						"and module_course.module_id = module_skill_objective.module_id\r\n" + 
						"and module_course.course_id = "+courseId;	
				List<HashMap<String, Object>> mappedMoDules = util.executeQuery(findMappedModules);
				for(HashMap<String, Object> mappedModule : mappedMoDules)
				{
					int modId = (int)mappedModule.get("module_id");
					Module m = new ModuleDAO().findById(modId);
					modulesInModSkill.add(m);
					ArrayList<SessionLevelSkill> sessionLevelSkill = getSessionLevelSkillForModuleSkill(modSkillId, courseId, modId);
					sessionLevelSkillInAllMod.addAll(sessionLevelSkill);
				}	
				modSkill.setModules(modulesInModSkill);
				
				modSkill.setSessionLevelSkill(sessionLevelSkillInAllMod);
				
				moduleLevelSkills.add(modSkill);
			}	
		}
		
		return moduleLevelSkills;
	}
	
	
	private ArrayList<SessionLevelSkill> getSessionLevelSkillForModuleSkill(int modSkillId, Integer courseId, int modId) {
		DBUTILS util = new DBUTILS();
		String findCMSSkill ="select * from skill_objective where context = "+courseId+" and skill_level_type='CMSESSION' and parent_skill="+modSkillId;
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
				ArrayList<Cmsession> mappedSessions  = new ArrayList<>();
				String findmappedCMSession = "select DISTINCT cmsession_skill_objective.cmsession_id from cmsession_skill_objective, cmsession_module , module_course\r\n" + 
						"where skill_objective_id ="+cmsSkillId+ 
						"and cmsession_module.cmsession_id = cmsession_skill_objective.cmsession_id\r\n" + 
						"and module_course.module_id = cmsession_module.module_id\r\n" + 
						"and cmsession_module.module_id = "+modId + 
						"and module_course.course_id = "+courseId;
				
				List<HashMap<String, Object>> mappedCMSData = util.executeQuery(findmappedCMSession);
				if(mappedCMSData.size()>0)
				{
					for(HashMap<String, Object> mappedCMSRow: mappedCMSData)
					{
						int cmsId = (int) mappedCMSRow.get("cmsession_id");
						if(!perfectCMSession.contains(cmsId))
						{
							perfectCMSession.add(cmsId);
							mappedSessions.add(new CmsessionDAO().findById(cmsId));
						}
						
						ArrayList<LearningObjective> los = getLoForSessionLevelskill(cmsSkillId,courseId, cmsId);
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
					cmsSkill.setSessions(mappedSessions);
					sessionSkills.add(cmsSkill);
				}	
				
				
			}	
			
		}				
		return sessionSkills;
	}*/
}
