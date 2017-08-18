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

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.viksitpro.core.dao.entities.Assessment;
import com.viksitpro.core.dao.entities.AssessmentDAO;
import com.viksitpro.core.dao.entities.AssessmentQuestion;
import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.CmsessionDAO;
import com.viksitpro.core.dao.entities.Context;
import com.viksitpro.core.dao.entities.ContextDAO;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.LessonDAO;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.ModuleDAO;
import com.viksitpro.core.dao.entities.Question;
import com.viksitpro.core.dao.entities.SkillObjective;
import com.viksitpro.core.dao.entities.SkillObjectiveDAO;
import com.viksitpro.core.delivery.pojo.DeliveryCourse;
import com.viksitpro.core.delivery.pojo.DeliveryLesson;
import com.viksitpro.core.delivery.pojo.DeliveryModule;
import com.viksitpro.core.delivery.pojo.DeliverySession;
import com.viksitpro.core.skill.pojo.DeliveryAssessmentTree;
import com.viksitpro.core.skill.pojo.CourseLevelSkill;
import com.viksitpro.core.skill.pojo.DeliveryQuestion;
import com.viksitpro.core.skill.pojo.LearningObjective;
import com.viksitpro.core.skill.pojo.ModuleLevelSkill;
import com.viksitpro.core.skill.pojo.SessionLevelSkill;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author mayank
 *
 */
public class CoreSkillService {
	
	public DeliveryCourse getDeliveryTreeForCourse(int courseId)
	{
		DeliveryCourse courseTree = new DeliveryCourse();
		Course course = new CourseDAO().findById(courseId);
		if(course!=null)
		{
			HashMap<Integer, ArrayList<ModuleLevelSkill>> lessonModSkillMap = new HashMap<>();
			CourseLevelSkill courseSkillTree = getShellSkillTreeForCourse(courseId);
			if(courseSkillTree.getModuleLevelSkill()!=null)
			{
				for(ModuleLevelSkill modskill : courseSkillTree.getModuleLevelSkill())
				{
					for(SessionLevelSkill sessionSkill :modskill.getSessionLevelSkill())
					{
						for(LearningObjective lobj : sessionSkill.getLearningObjectives())
						{
							for(Lesson lesson : lobj.getLessons())
							{
								
								
									if(lessonModSkillMap.containsKey(lesson.getId()))
									{
										ArrayList<ModuleLevelSkill> modSkills = lessonModSkillMap.get(lesson.getId());
										boolean alreadyPresent = false;
										for(ModuleLevelSkill mm : modSkills)
										{
											if(mm.getId()==modskill.getId())
											{
												alreadyPresent=true;
												break;
											}	
										}
										if(!alreadyPresent)
										{
											modSkills.add(modskill);
											lessonModSkillMap.put(lesson.getId(), modSkills);
										}	
									}
									else
									{
										ArrayList<ModuleLevelSkill> modSkills = new ArrayList<>();
										modSkills.add(modskill);
										lessonModSkillMap.put(lesson.getId(), modSkills);
									}
								
									
							}	
						}	
					}	
				}
			}
			
			ArrayList<DeliveryModule> deliveryModules = new ArrayList<>();
			for(Module module : course.getModules())
			{
				DeliveryModule deliveryMod = new DeliveryModule();
				deliveryMod.setId(module.getId());
				deliveryMod.setModuleName(module.getModuleName());
				ArrayList<DeliverySession> deliverySessions = new ArrayList<>();
				boolean isModulePerfect= true;				
				if(module.getCmsessions().size()==0)
				{
					isModulePerfect= false;					
				}
				else
				{
					for(Cmsession cms: module.getCmsessions())
					{
						DeliverySession deliverySession = new DeliverySession();
						deliverySession.setId(cms.getId());
						deliverySession.setSessionName(cms.getTitle());
						ArrayList<DeliveryLesson> deliveryLessons = new ArrayList<>();
						boolean isSessionPerfect= true; 
						if(cms.getLessons().size()==0)
						{
							isSessionPerfect=false;
						}
						else
						{
							for(Lesson lesson : cms.getLessons())
							{
								DeliveryLesson l = new DeliveryLesson();
								l.setId(lesson.getId());
								l.setLessonName(lesson.getTitle());
								l.setType(lesson.getType());
								l.setIsPerfect(true);
								if(lesson.getType().equalsIgnoreCase("ASSESSMENT"))
								{										
									if(lesson.getLessonXml()!=null && !lesson.getLessonXml().isEmpty() && !lesson.getLessonXml().equalsIgnoreCase(""))
									{	
										DeliveryAssessmentTree assessTree =	getDeliveryTreeForAssessment(Integer.parseInt(lesson.getLessonXml()));
										if(!assessTree.isValid())
										{
											l.setIsPerfect(false);
											isSessionPerfect = false;
											isModulePerfect=false;
										}
										
									}else
									{
										l.setIsPerfect(false);
										isSessionPerfect = false;
										isModulePerfect=false;
									}	
								}
								else
								{
									
									if(lessonModSkillMap.containsKey(lesson.getId()))
									{
										l.setMappedModuleLevelSkill(lessonModSkillMap.get(lesson.getId()));
										l.setIsPerfect(true);
									}
									else
									{
										ArrayList<ModuleLevelSkill> modSkills = new ArrayList<>();
										l.setMappedModuleLevelSkill(modSkills);
										l.setIsPerfect(false);
										isSessionPerfect = false;
										isModulePerfect=false;
									}
								}									
								deliveryLessons.add(l);
							}
						}	
						
						deliverySession.setLessons(deliveryLessons);
						deliverySession.setIsPerfect(isSessionPerfect);
						deliverySessions.add(deliverySession);
					}
				}	
				deliveryMod.setSessions(deliverySessions);
				deliveryMod.setIsPerfect(isModulePerfect);
				deliveryModules.add(deliveryMod);	
				
			}
			
			courseTree.setCourseName(course.getCourseName());
			courseTree.setId(courseId);
			courseTree.setModules(deliveryModules);
		}		
		return courseTree;
	}
	
	public DeliveryAssessmentTree getDeliveryTreeForAssessment(int assessmentId)
	{
		DeliveryAssessmentTree tree = new DeliveryAssessmentTree();
		Assessment assessment = new AssessmentDAO().findById(assessmentId);
		if(assessment!=null)
		{
			
			CourseLevelSkill assessmentSkillTree = getShellTreeForAssessment(assessmentId);
			HashMap<Integer, ArrayList<ModuleLevelSkill>> queSkillMap = new HashMap<>();
			if(assessmentSkillTree.getModuleLevelSkill()!=null)
			{
				for(ModuleLevelSkill modSkill : assessmentSkillTree.getModuleLevelSkill())
				{
					for(SessionLevelSkill sessionSkill : modSkill.getSessionLevelSkill())
					{
						for(LearningObjective lobj : sessionSkill.getLearningObjectives())
						{
							for(Question que : lobj.getQuestions())
							{
								if(queSkillMap.containsKey(que.getId()))
								{
									 ArrayList<ModuleLevelSkill> modSkills = queSkillMap.get(que.getId());
									 boolean alreadyPresent = false;
									 for(ModuleLevelSkill mSkill : modSkills)
									 {
										 if(modSkill.getId()==mSkill.getId())
										 {
											 alreadyPresent=true;
											 break;
										 } 
									 }
									 if(!alreadyPresent)
									 {
										 modSkills.add(modSkill);
									 } 
									 queSkillMap.put(que.getId(), modSkills);
								}
								else
								{
									ArrayList<ModuleLevelSkill> modSkills = new ArrayList<>();
									modSkills.add(modSkill);
									queSkillMap.put(que.getId(), modSkills);
								}	
							}	
						}	
					}	
				}
			}
			
			tree.setId(assessmentId);
			tree.setAssessmentTitle(assessment.getAssessmenttitle());
			ArrayList<DeliveryQuestion> deliveryQuestions = new ArrayList<>();
			
			
			for(AssessmentQuestion assessque: assessment.getAssessmentQuestions())
			{
				DeliveryQuestion que = new DeliveryQuestion();
				que.setId(assessque.getQuestion().getId());
				que.setQuestion(assessque.getQuestion());
				if(queSkillMap.containsKey(que.getId()))
				{
					que.setModuleLevelSkill(queSkillMap.get(que.getId()));					
				}										
				deliveryQuestions.add(que);
			}
			tree.setQuestions(deliveryQuestions);
			boolean isValid =true;
			for(DeliveryQuestion que : deliveryQuestions)
			{
				if(que.getModuleLevelSkill()==null )
				{
					isValid=false;
					break;
					
				}else if(que.getModuleLevelSkill().size()==0)
				{
					isValid=false;
					break;
				}	
			}
			
			tree.setValid(isValid);			
		}
		return tree;
	}
	
	public CourseLevelSkill getShellTreeForAssessment(int assessmentId)
	{
		CourseLevelSkill courseLevelSkill = new CourseLevelSkill();
		Assessment assessment = new AssessmentDAO().findById(assessmentId);
		if(assessment!=null)
		{
			HashMap<Integer,SkillObjective> totalLoInAssessment = new HashMap<>();
			ArrayList<Integer>questionsInAssessment  = new ArrayList<>();
			for(AssessmentQuestion assesque : assessment.getAssessmentQuestions())
			{
				if(!questionsInAssessment.contains(assesque.getQuestion().getId()))
				{
					questionsInAssessment.add(assesque.getQuestion().getId());
					for(SkillObjective skillObj : assesque.getQuestion().getSkillObjectives())
					{
						if(!totalLoInAssessment.containsKey(skillObj.getId()))
						{
							totalLoInAssessment.put(skillObj.getId(), skillObj);
						}	
					}
				}	
				
			}
			String loIds =StringUtils.join(totalLoInAssessment.keySet(), ",");
			DBUTILS util = new DBUTILS();
			if(!loIds.isEmpty())
			{
				String findCMSessionLevelSkill ="select DISTINCT session_skill.id , session_skill.name,COALESCE(session_skill.creation_type, 'SYSTEM_CREATED') as creation_type,  session_skill_lo_map.learning_obj_id "
						+ "from session_skill_lo_map , skill_objective session_skill "
						+ "where session_skill_lo_map.learning_obj_id in ("+loIds+") and session_skill_lo_map.session_skill_id =  session_skill.id ";
				System.out.println(findCMSessionLevelSkill);
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
						
						SkillObjective lobj = totalLoInAssessment.get(loId);					
						LearningObjective loNew = new LearningObjective();
						loNew.setCreationType(lobj.getCreationType());
						loNew.setId(lobj.getId());
						loNew.setLearningObjectiveName(lobj.getName());
						ArrayList<Question> questions = new ArrayList<>();
						for(Question lques: lobj.getQuestions())
						{
							if(questionsInAssessment.contains(lques.getId()))
							{
								questions.add(lques);
							}	
						}
						loNew.setQuestions(questions);
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
						
						SkillObjective lobj = totalLoInAssessment.get(loId);
						
						LearningObjective loNew = new LearningObjective();
						loNew.setCreationType(lobj.getCreationType());
						loNew.setId(lobj.getId());
						loNew.setLearningObjectiveName(lobj.getName());
						ArrayList<Question> questions = new ArrayList<>();
						for(Question que: lobj.getQuestions())
						{
							if(questionsInAssessment.contains(que.getId()))
							{
								questions.add(que);
							}	
						}
						loNew.setQuestions(questions);
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
			courseLevelSkill.setId(assessmentId);
			courseLevelSkill.setSkillName(assessment.getAssessmenttitle());
			
		}			
		return courseLevelSkill;
	}
	public CourseLevelSkill getShellTreeForContext(int contextId)
	{
		CourseLevelSkill courseLevelSkill = new CourseLevelSkill();
		
		Context context = new ContextDAO().findById(contextId);
		if(context!=null)
		{
			String findModuleLevelSkillUnderContext ="select id, name, COALESCE(creation_type,'SYSTEM_CREATED') as creation_type from skill_objective where skill_level_type ='MODULE' and context ="+contextId;
			DBUTILS util =new DBUTILS();
			List<HashMap<String, Object>> moduleSkillData = util.executeQuery(findModuleLevelSkillUnderContext);				
			HashMap<Integer, ModuleLevelSkill> moduleSkills = new HashMap<>();			
			
			for(HashMap<String, Object> row: moduleSkillData)
			{
				int moduleSkillId = (int)row.get("id");
				String moduleSkillName = row.get("name").toString();
				String creationType = row.get("creation_type").toString();
				
				ModuleLevelSkill modskill = new ModuleLevelSkill();
				modskill.setCreationType(creationType);
				modskill.setId(moduleSkillId);
				modskill.setSkillName(moduleSkillName);
				ArrayList<SessionLevelSkill>sessionSkill = new ArrayList<>();
				modskill.setSessionLevelSkill(sessionSkill);			
				moduleSkills.put(moduleSkillId, modskill);
			}
			
			
			
			String findCMSessionSkill = "select session_skill.id , session_skill.name, COALESCE(session_skill.creation_type,'SYSTEM_CREATED') as creation_type, module_skill.id as module_skill_id from module_skill_session_skill_map , skill_objective session_skill , skill_objective module_skill where module_skill_session_skill_map.session_skill_id = session_skill.id and module_skill_session_skill_map.module_skill_id = module_skill.id and module_skill.context ="+contextId;
			List<HashMap<String, Object>> sessionSkillData = util.executeQuery(findCMSessionSkill);
			for(HashMap<String, Object> row: sessionSkillData)
			{
				int sessionSkillId = (int)row.get("id");
				String sessionSkillName = row.get("name").toString();
				String creationType = row.get("creation_type").toString();
				int moduleSkillId = (int)row.get("module_skill_id");			
				if(moduleSkills.containsKey(moduleSkillId))
				{
					ModuleLevelSkill modSkill =  moduleSkills.get(moduleSkillId);
					ArrayList<SessionLevelSkill> sessionInModSkill = modSkill.getSessionLevelSkill();
					boolean alreadyPresent = false;
					for(SessionLevelSkill sessionSkill : sessionInModSkill)
					{
						if(sessionSkill.getId()==sessionSkillId)
						{
							alreadyPresent=true;
							break;
						}	
					}
					
					if(!alreadyPresent)
					{
						SessionLevelSkill sessionLevelSkill = new SessionLevelSkill();
						sessionLevelSkill.setCreationType(creationType);
						sessionLevelSkill.setId(sessionSkillId);
						sessionLevelSkill.setSkillName(sessionSkillName);
						ArrayList<LearningObjective> lobj = new ArrayList<>();
						sessionLevelSkill.setLearningObjectives(lobj);
						sessionInModSkill.add(sessionLevelSkill);					
					}
					
					modSkill.setSessionLevelSkill(sessionInModSkill);
					moduleSkills.put(moduleSkillId, modSkill);
				}			
			}
			
			
			
			
			String lessonInThisContext ="select DISTINCT lessonid from lesson_skill_objective where context_id = "+contextId;
			List<HashMap<String, Object>> lessonData = util.executeQuery(lessonInThisContext);
			ArrayList<Integer>lessonsInContext = new ArrayList<>(); 
			for(HashMap<String, Object> row: lessonData)
			{
				int lessonId = (int)row.get("lessonid");
				if(!lessonsInContext.contains(lessonId))
				{
					lessonsInContext.add(lessonId);
				}	
			}
			
			
			SkillObjectiveDAO skillObjDao = new SkillObjectiveDAO();
			String findLearningObj = "select distinct lobj.id as lo_id ,  module_skill.id as module_skill_id , session_skill.id as session_skill_id from module_skill_session_skill_map , skill_objective session_skill , skill_objective module_skill, skill_objective lobj, session_skill_lo_map where module_skill_session_skill_map.session_skill_id = session_skill.id and module_skill_session_skill_map.module_skill_id = module_skill.id and lobj.id = session_skill_lo_map.learning_obj_id and session_skill_lo_map.session_skill_id = session_skill.id and module_skill.context = "+contextId+";";
			List<HashMap<String, Object>> loData = util.executeQuery(findLearningObj);
			for(HashMap<String, Object> row: loData)
			{
				int loId = (int)row.get("lo_id");
				int moduleSkillId = (int)row.get("module_skill_id");
				int sessionSkillId = (int)row.get("session_skill_id");
				SkillObjective lobj = skillObjDao.findById(loId);
				
				if(moduleSkills.containsKey(moduleSkillId))
				{
					ModuleLevelSkill modskill = moduleSkills.get(moduleSkillId);
					ArrayList<SessionLevelSkill> sessionLevelSkills = modskill.getSessionLevelSkill();
					ArrayList<SessionLevelSkill> updateSessionLevelSkills = new ArrayList<>();
					for(SessionLevelSkill sessionSkill : sessionLevelSkills)
					{
						if(sessionSkill.getId()==sessionSkillId)
						{												
							boolean alreadyPresent = false;
							ArrayList<LearningObjective> loInSessionSkill = sessionSkill.getLearningObjectives();
							for(LearningObjective lo : loInSessionSkill)
							{
								if(lo.getId()==loId)
								{
									alreadyPresent=true;
									break;
								}
							}
							
							if(!alreadyPresent)
							{
								LearningObjective lobjNew = new LearningObjective();
								if(lobj.getCreationType()!=null)
								{
									lobjNew.setCreationType(lobj.getCreationType());
								}
								else
								{
									lobjNew.setCreationType("SYSTEM_CREATED");
								}	
								
								lobjNew.setId(lobj.getId());
								lobjNew.setLearningObjectiveName(lobj.getName());
								ArrayList<Lesson> lessonsInLo = new ArrayList<>();
								for(Lesson lesson : lobj.getLessons())
								{
									if(lessonsInContext.contains(lesson.getId()))
									{
										lessonsInLo.add(lesson);;
									}									
								}	
								lobjNew.setLessons(lessonsInLo);
								loInSessionSkill.add(lobjNew);
								sessionSkill.setLearningObjectives(loInSessionSkill);	
							}
							
							updateSessionLevelSkills.add(sessionSkill);												
						}
						else
						{
							updateSessionLevelSkills.add(sessionSkill);		
						}	
					}
					modskill.setSessionLevelSkill(updateSessionLevelSkills);
					moduleSkills.put(moduleSkillId, modskill);
				}	
				 
			}
			
			
			
			ArrayList<ModuleLevelSkill> modLevelSkill = new ArrayList<>();
			for(Integer modSkillId : moduleSkills.keySet())
			{
				modLevelSkill.add(moduleSkills.get(modSkillId));
			}
			courseLevelSkill.setModuleLevelSkill(modLevelSkill);
			courseLevelSkill.setId(context.getId());
			courseLevelSkill.setCreationType("MANUAL");
			courseLevelSkill.setSkillName(context.getTitle());
		}		
		
		return courseLevelSkill;
		
	}
	public CourseLevelSkill  getShellSkillTreeForCourse(int courseId)
	{
		CourseLevelSkill courseLevelSkill = new CourseLevelSkill();
		Course course = new CourseDAO().findById(courseId);
		courseLevelSkill.setCreationType("MANUAL");
		courseLevelSkill.setId(courseId);
		courseLevelSkill.setSkillName(course.getCourseName());
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
		//System.out.println(loIds);		
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
		System.out.println("start");
		CoreSkillService cs = new CoreSkillService();
		//cs.getShellSkillTreeForCourse(5);
		cs.getShellTreeForContext(111);
		
		System.out.println("end");
	}
	
}
