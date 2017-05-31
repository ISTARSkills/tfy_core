package istarcore;

import java.io.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.*;
import com.viksitpro.core.dao.utils.HibernateSessionFactory;



/**
 * 
 */

/**
 * @author vaibhav verma
 *
 */
public class ImportCourseSkillTreeNewSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Read csv
		 String csvFile = "C:\\Users\\vaibhav verma\\Downloads\\Asset management - Skill mapping - DRAFT (1).csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {
	                String[] country = line.split(cvsSplitBy);
	                int courseID = Integer.parseInt(country[0]);
	                String moduleLevelSkill = country[1];
	                int cmsessionID = Integer.parseInt(country[2]);
	                String sessionLevelSkill = country[3];
	                String lo = country[4];
	                int courseLevelSkill = Integer.parseInt(country[0]);
	                // 2673 - Parent Skill Name
	                //int courseSkill = createCourseLevelSkill(courseID);
	                int moduleID = createMOduleLevelSkill(moduleLevelSkill,courseLevelSkill);
	                int cmsessionSkillID = createSessionLevelSkill(sessionLevelSkill,moduleID,courseLevelSkill);
	                int loID = createLO(lo,cmsessionSkillID,courseLevelSkill);
	                //associateLoWithSkill(loID,cmsessionSkillID);
	                associateLessonWillLO(loID,cmsessionID);
	             //   associateQuestionWithLO(loID,cmsessionID);
	                associateSkillWithCMSESSION(cmsessionID,sessionLevelSkill);
	                
	            
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        System.exit(0);
	}

	private static void associateSkillWithCMSESSION(int cmsessionID, String sessionLevelSkill) {
		CmsessionDAO cmsessionDAO = new CmsessionDAO();
		Cmsession cms = cmsessionDAO.findById(cmsessionID);
		boolean ifexistes = false;
		for (SkillObjective iterable_element : cms.getSkillObjectives()) {
			if(iterable_element.getName().equalsIgnoreCase(sessionLevelSkill)){
				ifexistes = true;
				return;
			}
		}
		if(!ifexistes) {
		SkillObjective sss= new SkillObjectiveDAO().findByName(sessionLevelSkill).get(0);
		cms.getSkillObjectives().add(sss);
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			cmsessionDAO.attachDirty(cms);
			tx.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			//tx.rollback();
		} finally {
			//session.flush();
		}
		}
	}

	private static int createMOduleLevelSkill(String moduleLevelSkill, int parentSkill) {
		SkillObjectiveDAO dao = new SkillObjectiveDAO();
		if(dao.findByName(moduleLevelSkill).size()==0) {
			SkillObjective transientInstance = new SkillObjective();
			transientInstance.setType("MODULE_LEVEL_SKILL");
			transientInstance.setName(moduleLevelSkill);
			transientInstance.setParentSkill(parentSkill);
			transientInstance.setContext(parentSkill);
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateSessionFactory.getSession();
				tx = session.beginTransaction();
				dao.save(transientInstance);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.flush();
			}
			return transientInstance.getId();
			
			
		} else {
			return  dao.findByName(moduleLevelSkill).get(0).getId();

		}
		
	
		
	}
	
	
	private static int createSessionLevelSkill(String sessionLevelSkill, int moduleID, int courseLevelSkill) {
		SkillObjectiveDAO dao = new SkillObjectiveDAO();
		if(dao.findByName(sessionLevelSkill).size()==0) {
			SkillObjective transientInstance = new SkillObjective();
			transientInstance.setType("CMSESSION_LEVEL_SKILL");
			transientInstance.setName(sessionLevelSkill);
			transientInstance.setParentSkill(moduleID);
			transientInstance.setContext(courseLevelSkill);
			
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateSessionFactory.getSession();
				tx = session.beginTransaction();
				dao.save(transientInstance);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.flush();
			}
			return transientInstance.getId();
		} else {
			return  dao.findByName(sessionLevelSkill).get(0).getId();
		}
	}

	
	private static int createLO(String lo, int cmsessionSkillID, int moduleLevelSkill) {
		SkillObjectiveDAO dao = new SkillObjectiveDAO();
		if(dao.findByName(lo).size()==0) {
			SkillObjective transientInstance = new SkillObjective();
			transientInstance.setType("LEARNING_OBJECTIVE");
			transientInstance.setName(lo);
			transientInstance.setParentSkill(cmsessionSkillID);
			transientInstance.setContext(moduleLevelSkill);
			
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateSessionFactory.getSession();
				tx = session.beginTransaction();
				dao.save(transientInstance);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.flush();
			}
			
			return transientInstance.getId();
		} else {
			return dao.findByName(lo).get(0).getId();
		}
		
	}
	
	
	private static void associateLessonWillLO(int loID, int cmsessionID) {
		CmsessionDAO cmsessionDAO = new CmsessionDAO();
		
		
		for (Lesson lesson : cmsessionDAO.findById(cmsessionID).getLessons()) {
			lesson.getSkillObjectives().add((new SkillObjectiveDAO()).findById(loID));
			LessonDAO dao  = new LessonDAO();
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateSessionFactory.getSession();
				tx = session.beginTransaction();
				dao.attachDirty(lesson);
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
