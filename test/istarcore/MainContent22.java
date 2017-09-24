package istarcore;

import java.io.IOException;
import java.util.List;

import com.viksitpro.cms.benchmark.BenchmarkUtility;
import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.Module;

public class MainContent22 {

	public static void main(String[] args) throws IOException {

		 System.out.println("start");
		 
		 //xx();
		 pulishLesson();
		 publishAssessment();
		System.exit(0);
	}
	

	private static void publishAssessment() {
		BenchmarkUtility utility = new BenchmarkUtility();
		List<Course> courses = new CourseDAO().findAll();
		for (Course course : courses) {
			List<Module> modules = course.getModules();
			for (Module module : modules) {
				List<Cmsession> sessions = module.getCmsessions();
				for (Cmsession cmsession : sessions) {
					List<Lesson> lessons = cmsession.getLessons();
					for (Lesson lesson : lessons) {
						System.err.println("Lesson Id----> " + lesson.getId());
						if(lesson.getType()!=null && lesson.getType().equalsIgnoreCase("ASSESSMENT") && lesson.getLessonXml()!=null && !lesson.getLessonXml().equalsIgnoreCase(""))
						{
							utility.createSkillBenchMarkforAssessmentNew(Integer.parseInt(lesson.getLessonXml()), course.getId());
						}	
					}
				}
			}
		}
		

	}

	private static void pulishLesson() {
		// TODO Auto-generated method stub
		BenchmarkUtility utility = new BenchmarkUtility();
		List<Course> courses = new CourseDAO().findAll();
		for (Course course : courses) {
			List<Module> modules = course.getModules();
			for (Module module : modules) {
				List<Cmsession> sessions = module.getCmsessions();
				for (Cmsession cmsession : sessions) {
					List<Lesson> lessons = cmsession.getLessons();
					for (Lesson lesson : lessons) {
						System.err.println("Lesson Id----> " + lesson.getId());
						utility.createSkillBenchMarkForLessonNew(lesson.getId(), course.getId());
					}
				}
			}
		}
		/*
		 * for(int i=11;i<27;i++) { utility.createSkillBenchMarkForLesson(i, 7);
		 * }
		 */
	}

}
