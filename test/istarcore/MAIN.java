/**
 * 
 */
package istarcore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Assessment;
import com.viksitpro.core.dao.entities.AssessmentDAO;
import com.viksitpro.core.dao.entities.AssessmentQuestion;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.LessonDAO;
import com.viksitpro.core.dao.entities.Question;
import com.viksitpro.core.dao.entities.SkillObjective;
import com.viksitpro.core.dao.entities.SkillObjectiveDAO;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author verma6uc
 *
 */
public class MAIN {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		//createQLO();
		//ViksitLogger.logMSG(this.getClass().getName(),"started");
		////ViksitLogger.logMSG(this.getClass().getName(),);
		//createAssesmentBechmark();
		//executeSQLFromFile();
		//addCourseToAssessment();
		//SS();
		//ll();
		//portCheck();
		checkAssessment();
		
		//ViksitLogger.logMSG(this.getClass().getName(),"exiting");
		System.exit(0);

	}
	
	

	


	private static void checkAssessment() {
		
	}






	private static void portCheck() {
		// TODO Auto-generated method stub
		//ViksitLogger.logMSG(this.getClass().getName(),isLocalPortInUse(4567));
	}


	private static boolean isLocalPortInUse(int port) {
	    try {
	        // ServerSocket try to open a LOCAL port
	        new ServerSocket(port).close();
	        // local port can be opened, it's available
	        return false;
	    } catch(IOException e) {
	        // local port cannot be opened, it's in use
	        return true;
	    }
	}



	private static void ll() {
		// TODO Auto-generated method stub
		DBUTILS util = new DBUTILS();
		for(int i=4; i<=16 ;i++)
		{
			String insertlesson="INSERT INTO lesson (id, type, duration, tags, title, subject, order_id, created_at, is_deleted, description, image_url, lesson_xml) VALUES ((select		COALESCE(max(id),0)+1 from lesson), 'VIDEO', NULL, NULL, 'L"+i+"', NULL, NULL, NULL, 'f', 'JRE Components. The JRE is the software environment in which programs compiled for a typical JVM implementation can run.', '/var/www/html/course_images/7f2ab079-fc2d-4239-98bd-191e93bf9843.png ', '<?xml version=1.0 encoding=UTF-8 standalone=yes?><video_lesson>    <id>11</id>    <video_thumb_url>/var/www/html/course_images/991a5b48-f020-42b2-a18b-55b1d3027bfe.png</video_thumb_url>    <video_url>/var/www/html/video/5dae77c9-3fce-4bef-9188-ec4517174924.mp4</video_url></video_lesson>'); ";
			util.executeUpdate(insertlesson);
		}
	}






	private static void SS() {
		// TODO Auto-generated method stub
		//ViksitLogger.logMSG(this.getClass().getName(),"start");
		DBUTILS util = new DBUTILS();
		for(int i =5 ; i<= 16;i++)
		{
		String skillName = "SK"+i;
		String sql ="INSERT INTO skill_objective (id, type, name,parent_skill, skill_level_type, order_id, context) VALUES ((select		COALESCE(max(id),0)+1 from skill_objective), 'SKILL', '"+skillName+"',		NULL, NULL,NULL,7) returning id;";
		int skillId = util.executeUpdateReturn(sql);
		for(int j=1; j <3; j++)
		{
		String lobname = "LO"+j+skillName;
		String indertLOb= "INSERT INTO skill_objective (id, type, name,		parent_skill, skill_level_type, order_id, context) VALUES ((select		COALESCE(max(id),0)+1 from skill_objective), 'LEARNING_OBJECTIVE',		'"+lobname+"', "+skillId+", NULL,NULL,7);";
		util.executeUpdate(indertLOb);
		}
		}
		//ViksitLogger.logMSG(this.getClass().getName(),"done");
		}






	private static void addCourseToAssessment() throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ISTAR-SERVER-PU-1\\Desktop\\777.txt"))) {
			String line = br.readLine();

			int count = 0;
			int good=0;
			
			while (line != null) {
				try {
					line = br.readLine();
					//ViksitLogger.logMSG(this.getClass().getName(),line);

					String[] word = line.split("#");

					if (word.length > 1) {
						String assessmentId = word[0];
						String courseTitle = word[1];

						if (assessmentId != null && courseTitle != null) {
							AssessmentDAO assessmentDAO = new AssessmentDAO();
							Assessment assessment = assessmentDAO.findById(Integer.parseInt(assessmentId));

							CourseDAO courseDAO = new CourseDAO();
							List<Course> courses = courseDAO.findByCourseName(courseTitle);

							if (assessment != null && courses.size() > 0) {
								good++;
								assessment.setCourse(courses.get(0).getId());
								
								Session assessmentSession = assessmentDAO.getSession();
								Transaction assessmentTransaction = null;
								try {
									assessmentTransaction = assessmentSession.beginTransaction();
									assessmentSession.update(assessment);
									assessmentTransaction.commit();
								} catch (HibernateException e) {
									e.printStackTrace();
									if (assessmentTransaction != null)
										assessmentTransaction.rollback();
									e.printStackTrace();
								} finally {
									assessmentSession.close();
								}																
							} else {
								count++;
							}

						}
					}

					//ViksitLogger.logMSG(this.getClass().getName(),"EXECUTED");
				} catch (Exception e) {
					//ViksitLogger.logMSG(this.getClass().getName(),line);
				}
			}
			//ViksitLogger.logMSG(this.getClass().getName(),"Count :" + count);
			//ViksitLogger.logMSG(this.getClass().getName(),"Good :" + good);
		}

	}






	public static void createAssesmentBechmark() {
		AssessmentDAO dao = new AssessmentDAO();

		for (Assessment asessment : dao.findAll()) {
			int totoalPoints = 0;
			Set<Integer> assessmentSkills = new HashSet<Integer>();

			for (AssessmentQuestion aq : asessment.getAssessmentQuestions()) {
				Question q = aq.getQuestion();
				Set<Integer> parentSkills = new HashSet<Integer>();

				////ViksitLogger.logMSG(this.getClass().getName(),"Q->" + q.getId());
				for (SkillObjective so : q.getSkillObjectives()) {
					////ViksitLogger.logMSG(this.getClass().getName(),"P--->" + so.getParentSkill());
					parentSkills.add(so.getParentSkill());
				}
				totoalPoints = totoalPoints + q.getDifficultyLevel();
				assessmentSkills.addAll(parentSkills);
			}
			for (Integer parentCMSSkill : assessmentSkills) {
				double perSkill = ((double) totoalPoints) / assessmentSkills.size();

				String sql = "INSERT INTO assessment_benchmark (id, assessment_id, skill_objective_id, max_points)"
						+ " VALUES ((SELECT (MAX(ID) + 1) FROM assessment_benchmark), '" + asessment.getId() + "', "
						+ parentCMSSkill + ", '" + perSkill + "');";
				//ViksitLogger.logMSG(this.getClass().getName(),sql);
			}
		}
	}
	
	
	public static void createAssesmentBechmarkForAllSkills() {
		AssessmentDAO dao = new AssessmentDAO();

		List<SkillObjective> allLOs = (new SkillObjectiveDAO()).findByType("LEARNING_OBJECTIVE");
		int countSO = 0;

		for (Assessment asessment : dao.findAll()) {
			int totoalPoints = 0;
			for (AssessmentQuestion aq : asessment.getAssessmentQuestions()) {
				Question q = aq.getQuestion();
				if (q.getSkillObjectives().size() == 0) {
					countSO++;
					// saveQuestion(q, allLOs);
				}
				totoalPoints = totoalPoints + q.getDifficultyLevel();
			}
			String sql = "INSERT INTO assessment_benchmark1 (id, assessment_id, skill_objective_id, max_points)"
					+ " VALUES ((SELECT (MAX(ID) + 1) FROM assessment_benchmark1), '" + asessment.getId() + "', '1', '"
					+ totoalPoints + "');";
			//ViksitLogger.logMSG(this.getClass().getName(),(sql);
		}
		//ViksitLogger.logMSG(this.getClass().getName(),countSO);

	}

	private static void saveQuestion(Question q, List<SkillObjective> allLOs) {
		
		////ViksitLogger.logMSG(this.getClass().getName(),"SAVING-->" + q.getId());
		
		int maxSize = allLOs.size();
		
		int rnd = (new Random()).nextInt(maxSize);
		
		String sql ="INSERT INTO question_skill_objective (learning_objectiveid, questionid) VALUES ("+allLOs.get(rnd).getId()+", "+q.getId()+");";
		
		//ViksitLogger.logMSG(this.getClass().getName(),sql);
	}

	public static void createQLO() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\ISTAR-SERVER-PU-1\\Desktop\\eeeeeeeeeeeee.txt"))) {
			String line = br.readLine();
			int kam=0;
			while (line != null) {
				try {
					line = br.readLine();
					////ViksitLogger.logMSG(this.getClass().getName(),line.split("\t")[1]);
					SkillObjectiveDAO dao = new SkillObjectiveDAO();
					for (SkillObjective skill_objective : dao.findAll()) {
						if(skill_objective.getName().toUpperCase().trim().equalsIgnoreCase(line.split("\t")[1].toUpperCase().trim())) {
							String sql = "INSERT INTO question_skill_objective (learning_objectiveid, questionid) VALUES ("+skill_objective.getId()+","+line.split("\t")[0]+");";
							//ViksitLogger.logMSG(this.getClass().getName(),sql);
							kam++;
						}
					}
				} catch (Exception e) {
					//ViksitLogger.logMSG(this.getClass().getName(),line);
				}
			}
			//ViksitLogger.logMSG(this.getClass().getName(),("kmainis"+kam);
}

	}

	public static void executeSQLFromFile() throws FileNotFoundException, IOException {

		DBUTILS DBUTILS = new DBUTILS();

		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\ISTAR-SERVER-PU-1\\Desktop\\999.txt"))) {
			String line = br.readLine();

			while (line != null) {
				try {
					line = br.readLine();
					//ViksitLogger.logMSG(this.getClass().getName(),line);
					DBUTILS.executeUpdate(line);
					//ViksitLogger.logMSG(this.getClass().getName(),"EXECUTED");
				} catch (Exception e) {
					//ViksitLogger.logMSG(this.getClass().getName(),line);
				}
			}
		}
	}

	public static void learingObjectiveForLesson() throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ISTAR-SERVER-PU-1\\Desktop\\33.txt"))) {
			String line = br.readLine();

			int count = 0;
			int index = 0;
			int good = 0;

			while (line != null) {
				index++;
				// //ViksitLogger.logMSG(this.getClass().getName(),"Line: " + index);
				try {
					line = br.readLine();
					if (line.contains("#")) {

						// //ViksitLogger.logMSG(this.getClass().getName(),line);
						String[] word = line.split("#");

						String learningObjectiveTitle = word[0].trim();
						String lessonTitle = word[1].trim();

						// //ViksitLogger.logMSG(this.getClass().getName(),learningObjectiveTitle);
						// //ViksitLogger.logMSG(this.getClass().getName(),lessonTitle);

						SkillObjectiveDAO soDAO = new SkillObjectiveDAO();
						LessonDAO lessonDAO = new LessonDAO();

						List<SkillObjective> allSO = soDAO.findByName(learningObjectiveTitle);
						List<Lesson> allLesson = lessonDAO.findByTitle(lessonTitle);

						if (allSO.size() > 0 && allLesson.size() > 0) {
							Lesson lesson = allLesson.get(0);

							for (SkillObjective so : allSO) {
								good++;
								String sql = "INSERT INTO lesson_skill_objective (lessonid, learning_objectiveid) VALUES ("
										+ lesson.getId() + "," + so.getId() + ");";
								//ViksitLogger.logMSG(this.getClass().getName(),sql);
							}
						} else {
							count++;
						}
					}
				} catch (Exception e) {
					// //ViksitLogger.logMSG(this.getClass().getName(),"Exceptions");
				}
			}
			//ViksitLogger.logMSG(this.getClass().getName(),"Count: " + count);
			//ViksitLogger.logMSG(this.getClass().getName(),"Good: " + good);
		}
	}

}

/*
 * 
 * public static void ain(){ try (BufferedReader br = new BufferedReader( new
 * FileReader("C:\\Users\\ISTAR-SERVER-PU-1\\Desktop\\22222.txt"))) {
 * StringBuilder sb = new StringBuilder(); String line = br.readLine();
 * 
 * int count = 0; int index = 0;
 * 
 * while (line != null) { index++; ////ViksitLogger.logMSG(this.getClass().getName(),"Line: " + index); if
 * (line.contains("#")) {
 * 
 * sb.append(line); sb.append(System.lineSeparator()); line = br.readLine();
 * ////ViksitLogger.logMSG(this.getClass().getName(),line);
 * 
 * String[] word = line.split("#");
 * 
 * if(word.length > 1){ String skillObjectiveTitle = word[0]; String questionId
 * = word[1].trim();
 * 
 * ////ViksitLogger.logMSG(this.getClass().getName(),skillObjectiveTitle);
 * ////ViksitLogger.logMSG(this.getClass().getName(),cmsessionTitle);
 * 
 * SkillObjectiveDAO soDAO = new SkillObjectiveDAO(); QuestionDAO cmsDAO = new
 * QuestionDAO();
 * 
 * List<SkillObjective> allSO = soDAO.findByName(skillObjectiveTitle); Question
 * q = cmsDAO.findById(Integer.parseInt(questionId));
 * 
 * 
 * if (allSO.size() > 0 && q!=null) { for(SkillObjective so : allSO){
 * //ViksitLogger.logMSG(this.getClass().getName(),
 * "insert into question_skill_objective (learning_objectiveid, questionid) values("
 * +so.getId()+","+q.getId()+");"); } }else{
 * 
 * } } }
 * 
 * }
 * 
 * } }
 */

/*
 * AssessmentQuestionDAO dao = new AssessmentQuestionDAO();
 * 
 * 
 * for(AssessmentQuestion aq: dao.findAll()){ double totoalPoints = 0; Question
 * q = aq.getQuestion();
 * 
 * String sql =
 * "SELECT DISTINCT parent_skill FROM skill_objective WHERE skill_objective. ID IN (SELECT learning_objectiveid FROM question_skill_objective WHERE questionid ="
 * +q.getId()+")";
 * 
 * BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO(); Session session =
 * baseHibernateDAO.getSession();
 * 
 * SQLQuery query = session.createSQLQuery(sql);
 * 
 * List<Integer> all = query.list();
 * 
 * for(Integer qso : all){ totoalPoints = totoalPoints + q.getDifficultyLevel()
 * ;//((double) q.getDifficultyLevel()/all.size());
 * 
 * String sql1 =
 * "INSERT INTO assessment_benchmark1 (id, assessment_id, skill_objective_id, max_points)"
 * + " VALUES ((SELECT (MAX(ID) + 1) FROM assessment_benchmark1),"+aq.
 * getAssessment().getId()+","+qso+", "+totoalPoints+");";
 * 
 * 
 * } }
 */

/*
 * AssessmentDAO dao = new AssessmentDAO(); //Assessment asessment =
 * dao.findById(10295); for (Assessment asessment : dao.findAll()) { int
 * totoalPoints = 0; int numberOfSkillObjetcives=0;
 * 
 * for (AssessmentQuestion aq : asessment.getAssessmentQuestions()) { Question q
 * = aq.getQuestion();
 * 
 * 
 * if(q.getSkillObjectives().size()==0){ //ViksitLogger.logMSG(this.getClass().getName(),q.getId()); }else{
 * ////ViksitLogger.logMSG(this.getClass().getName(),q.getId() + "--HAS-->" +q.getSkillObjectives().size());
 * }
 * 
 * 
 * 
 * totoalPoints = totoalPoints + q.getDifficultyLevel();// ((double) for
 * (SkillObjective qso : q.getSkillObjectives()) { //
 * q.getDifficultyLevel()/q.getSkillObjectives().size());
 * numberOfSkillObjetcives = numberOfSkillObjetcives+1; String sql =
 * "INSERT INTO assessment_benchmark1 (id, assessment_id, skill_objective_id, max_points)"
 * + " VALUES ((SELECT (MAX(ID) + 1) FROM assessment_benchmark1)," +
 * asessment.getId() + "," + qso.getParentSkill() + ", " + totoalPoints + ");";
 * } } } System.exit(0);
 */

// //ViksitLogger.logMSG(this.getClass().getName(),(asessment.getAssessmentQuestions().size()+"---"+
// totoalPoints + "Number of Skills- >"+numberOfSkillObjetcives );

/// }

/*
 * for (Assessment asessment : dao.findAll()) { int totoalPoints = 0; for
 * (AssessmentQuestion aq : asessment.getAssessmentQuestions()) { totoalPoints =
 * totoalPoints + aq.getQuestion().getDifficultyLevel();
 * 
 * } String sql =
 * "INSERT INTO assessment_benchmark (id, assessment_id, skill_objective_id, max_points)"
 * + " VALUES ((SELECT (MAX(ID) + 1) FROM assessment_benchmark), '" +
 * asessment.getId() + "', '1', '" + totoalPoints + "');";
 * //ViksitLogger.logMSG(this.getClass().getName(),(sql); }
 */

/*				for(SkillObjective so : q.getSkillObjectives()){
totoalPoints = ((double) q.getDifficultyLevel())/q.getSkillObjectives().size();

String sql = "INSERT INTO assessment_benchmark (id, assessment_id, skill_objective_id, max_points)"
		+ " VALUES ((SELECT (MAX(ID) + 1) FROM assessment_benchmark), '" + asessment.getId() + "', '1', '"
		+ totoalPoints + "');";
//ViksitLogger.logMSG(this.getClass().getName(),(sql);
}
*/



/**
 * 
 *//*
package istarcore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.Campaign;
import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.Context;
import com.viksitpro.core.dao.entities.ContextDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.IstarUserDAO;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.ModuleDAO;
import com.viksitpro.core.dao.utils.HibernateSessionFactory;
import com.viksitpro.core.dao.utils.user.IstarUserServices;

*//**
 * @author verma6uc
 *
 *//*
public class MAIN {

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		Module m = (new ModuleDAO().findById(25));
		for (Cmsession cms : m.getCmsessions()) {
			//ViksitLogger.logMSG(this.getClass().getName(),cms.getId());
		}
		//TestingContextAdd();
		TestingContextFind();
	}

	private static void TestingContextFind() {
		// TODO Auto-generated method stub
		Context context = (new ContextDAO()).findById(1);
		//ViksitLogger.logMSG(this.getClass().getName(),(context.getTitle());
	}

	private static void TestingContextAdd() {
		// TODO Auto-generated method stub
		Context context = new Context();
		ContextDAO contextDAO = new ContextDAO();
		context.setTitle("Champoa");
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			// some action
			contextDAO.attachDirty(context);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			session.flush();
		}
		//ViksitLogger.logMSG(this.getClass().getName(),(context.getId());
	}

}
*/

