/**
 * 
 */
package com.viksitpro.cms.benchmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Assessment;
import com.viksitpro.core.dao.entities.AssessmentBenchmark;
import com.viksitpro.core.dao.entities.AssessmentBenchmarkDAO;
import com.viksitpro.core.dao.entities.AssessmentDAO;
import com.viksitpro.core.dao.entities.AssessmentQuestion;
import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.LessonDAO;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.Question;
import com.viksitpro.core.dao.entities.QuestionDAO;
import com.viksitpro.core.dao.entities.SkillObjective;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author mayank
 *
 */
public class BenchmarkUtility {

	public void createSkillBenchMarkForLessonNew(int lessonId, int courseId) 
	{
		Lesson lesson = new LessonDAO().findById(lessonId);
		Set<Cmsession> sessions = new HashSet<>();
		if(lesson.getCmsessions()!=null)
		{
			sessions = lesson.getCmsessions();
		}	
		
		ArrayList<Integer> sessionSkills = new ArrayList<>();
		for(Cmsession cms : sessions)
		{
			boolean validSession = false;
			outerloop : 
			{
				for(Module mod : cms.getModules())
				{
					for(Course c: mod.getCourses())
					{
						if(c.getId()==courseId)
						{
							validSession = true;
							break outerloop;
						}
					}	
				}
			}
			
			if(validSession)
			{
				sessionSkills.add(cms.getId());
			}			
		}
		
		if(sessionSkills.size()>0)
		{
			for(int sSkill : sessionSkills)
			{
				int cmsessionSkillCount=sessionSkills.size();	    	
		    	String perSkillMaxPoints = "cast(:per_lesson_points as float8)/("+cmsessionSkillCount+")";
		    	int itemId = lessonId;
		    	String itemType = "LESSON";
		    	AssessmentBenchmark assessmentBenchMark = new AssessmentBenchmark();
		    	assessmentBenchMark.setItemId(itemId);
		    	assessmentBenchMark.setItemType(itemType);
		    	assessmentBenchMark.setMaxPoints(perSkillMaxPoints);
		    	assessmentBenchMark.setSkillId(sSkill);
		    	assessmentBenchMark.setCourseId(courseId);
		    	AssessmentBenchmarkDAO assessmentBenchMarkDAO = new AssessmentBenchmarkDAO();
		    	Session session = null;
				Transaction tx = null;
				try {
					session = assessmentBenchMarkDAO.getSession();
					tx = session.beginTransaction();
					assessmentBenchMarkDAO.attachDirty(assessmentBenchMark);
					tx.commit();
				} catch (Exception e) {
					e.printStackTrace();
					tx.rollback();
				} finally {
					session.flush();
				}
			}
		}
		
	}

	
	
	public void createSkillBenchMarkforAssessmentNew(Integer assessmentId, int courseId) {		
		HashSet<Integer> sessionSkillInAssessment = new HashSet<>();
		HashMap<Integer, HashSet<Integer>>sessionSkillInQuestion = new HashMap<>();		
		Assessment assessment = new AssessmentDAO().findById(assessmentId);		
		
		HashMap<Integer, ArrayList<Integer>> loSessionSkillMAp = new HashMap<>();
		if(assessment!=null && assessment.getAssessmentQuestions()!=null)
		{
			Course course = new CourseDAO().findById(courseId);
			for(Module module : course.getModules())
			{
				for(Cmsession session : module.getCmsessions())
				{
					for(Lesson lesson : session.getLessons())
					{
						if(lesson.getType()!=null && !lesson.getType().equalsIgnoreCase("ASSESSMENT") && lesson.getSkillObjectives()!=null)
						{
							for(SkillObjective lo : lesson.getSkillObjectives())
							{
								if(loSessionSkillMAp.containsKey(lo.getId()))
								{
									ArrayList<Integer> sessionIds = loSessionSkillMAp.get(lo.getId());
									if(!sessionIds.contains(session.getId()))
									{
										sessionIds.add(session.getId());
									}
									loSessionSkillMAp.put(lo.getId(), sessionIds);
								}
								else
								{
									ArrayList<Integer> sessionIds = new ArrayList<>();
									sessionIds.add(session.getId());									
									loSessionSkillMAp.put(lo.getId(), sessionIds);
								}	
							}
						}
					}
				}
			}
			

			if(loSessionSkillMAp.size()>0)
			{
				Set<AssessmentQuestion> questions = assessment.getAssessmentQuestions();
				for(AssessmentQuestion assque : questions)
				{
					Question que  = assque.getQuestion();
					if(que.getSkillObjectives()!=null)
					{
						HashSet<Integer> sessionSkillIds = new HashSet<>();
						for(SkillObjective lo : que.getSkillObjectives())
						{
							if(loSessionSkillMAp.containsKey(lo.getId()))
							{
								ArrayList<Integer> ss = loSessionSkillMAp.get(lo.getId());
								for(int s : ss)
								{
									sessionSkillIds.add(s);
								}	
							}
						}
						sessionSkillInAssessment.addAll(sessionSkillIds);
						sessionSkillInQuestion.put(que.getId(), sessionSkillIds);
					}	
				}
			}
			
			if(sessionSkillInAssessment.size()>0)
			{
				DBUTILS util = new DBUTILS(); 
				String deleteOldAssessmentBenchMark ="delete from assessment_benchmark where item_id="+assessmentId+" and item_type='ASSESSMENT' and course_id="+courseId+";";
				util.executeUpdate(deleteOldAssessmentBenchMark);
				//first insert benchmark point for assessment
				
				for(int sessionSkillId : sessionSkillInAssessment)
				{
			    	int cmsessionSkillCount=sessionSkillInAssessment.size();
			    	//int totalAssessmentPoint = Integer.parseInt(per_assessment_points);
			    	String perSkillMaxPoints = "cast(:per_assessment_points as float8)/("+cmsessionSkillCount+")";
			    	int itemId = assessmentId;
			    	String itemType = "ASSESSMENT";
			    	
			    	AssessmentBenchmark assessmentBenchMark = new AssessmentBenchmark();
			    	assessmentBenchMark.setItemId(itemId);
			    	assessmentBenchMark.setItemType(itemType);
			    	assessmentBenchMark.setMaxPoints(perSkillMaxPoints);
			    	assessmentBenchMark.setSkillId(sessionSkillId);
			    	assessmentBenchMark.setCourseId(courseId);
			    	AssessmentBenchmarkDAO assessmentBenchMarkDAO = new AssessmentBenchmarkDAO();
			    	Session session = null;
					Transaction tx = null;
					try {
						session = assessmentBenchMarkDAO.getSession();
						tx = session.beginTransaction();
						assessmentBenchMarkDAO.attachDirty(assessmentBenchMark);
						tx.commit();
					} catch (Exception e) {
						e.printStackTrace();
						tx.rollback();
					} finally {
						session.flush();
					}	
				}	
			}
			if(sessionSkillInQuestion.size()>0)
			{
				for(int queId : sessionSkillInQuestion.keySet())
				{
					//first delete old data related to that question
					DBUTILS util = new DBUTILS(); 
		    		String deleteOldQuestionBenchMark ="delete from assessment_benchmark where item_id="+queId+" and item_type='QUESTION';";
					util.executeUpdate(deleteOldQuestionBenchMark);
		    		
		    		//find cmsession skill related to question.
					
					if(sessionSkillInQuestion.get(queId)!=null && sessionSkillInQuestion.get(queId).size()>0)
					{
						Question que = new QuestionDAO().findById(queId);
						HashSet<Integer>skills = sessionSkillInQuestion.get(queId);
						for(int sessionSkillId : skills)
						{
							int cmsSkillId = sessionSkillId;
			    			int totalCMSessionSkillCount = skills.size();
			    			//int totalQuestionPoint = Integer.parseInt(per_question_points);
					    	String perSkillMaxPoints = "cast((:per_question_points * "+que.getDifficultyLevel()+") as float8)/("+totalCMSessionSkillCount+")";
					    	int itemId = que.getId();
					    	String itemType = "QUESTION";
					    	
					    	AssessmentBenchmark assessmentBenchMark = new AssessmentBenchmark();
					    	assessmentBenchMark.setItemId(itemId);
					    	assessmentBenchMark.setItemType(itemType);
					    	assessmentBenchMark.setMaxPoints(perSkillMaxPoints);
					    	assessmentBenchMark.setSkillId(sessionSkillId);
					    	assessmentBenchMark.setCourseId(courseId);
					    	AssessmentBenchmarkDAO assessmentBenchMarkDAO = new AssessmentBenchmarkDAO();
					    	Session session = null;
							Transaction tx = null;
							try {
								session = assessmentBenchMarkDAO.getSession();
								tx = session.beginTransaction();
								assessmentBenchMarkDAO.attachDirty(assessmentBenchMark);
								tx.commit();
							} catch (Exception e) {
								e.printStackTrace();
								tx.rollback();
							} finally {
								session.flush();
							}
						}	
					}	
				}
			}
			
		}	
	}
}

