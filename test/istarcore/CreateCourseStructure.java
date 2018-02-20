package istarcore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.CmsessionDAO;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.LessonDAO;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.ModuleDAO;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * 
 */

/**
 * @author Aditya
 *
 */
public class CreateCourseStructure {

	private static final String FILE_NAME = "C:\\Users\\vaibhav\\Downloads\\vaibhav.xlsx";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
		Workbook workbook = new XSSFWorkbook(excelFile);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			System.err.println(workbook.getSheetName(i));
		}

		Sheet datatypeSheet = workbook.getSheetAt(0);
		System.err.println(datatypeSheet.getSheetName());
		Iterator<Row> iterator = datatypeSheet.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			try {
				Row currentRow = iterator.next();
				String courseName = currentRow.getCell(0).getStringCellValue().trim();
				String moduleName = currentRow.getCell(2).getStringCellValue().trim();
				String sessionName = currentRow.getCell(3).getStringCellValue().trim();
				String lessonName = currentRow.getCell(4).getStringCellValue().trim();
				if (!courseName.equalsIgnoreCase("") && !moduleName.equalsIgnoreCase("")
						&& !moduleName.equalsIgnoreCase("")) {
					Course c = createCourse(courseName);
					Module m = addModule(courseName + "----" + moduleName, c);
					updateCourseMod(c.getId(), m.getId());
					Cmsession cmsession = createCMSESSION(courseName + "----" + moduleName + "------" + sessionName, m);
					updatecmsMod(cmsession.getId(), m.getId());
					// createLesson();
					// http://talentify.in/content/content_creator/presentation.jsp?lesson_id=10132
					if (currentRow.getCell(10).getStringCellValue().trim().contains("presentation.jsp")) {// talentify.in
						String lessonID = currentRow.getCell(10).getStringCellValue().trim().replaceAll(
								"http://talentify.in/content/content_creator/presentation.jsp\\?lesson_id=", "");
						System.out.println("Lesson from ELT "+lessonID);

						linkLesson(cmsession.getId(), Integer.parseInt(lessonID));
					} else if (currentRow.getCell(10).getStringCellValue().trim().contains("preview_desktop.jsp")) { // http://api.talentify.in/content/lesson/preview_desktop.jsp?ppt_id=1288
						String lessonID = currentRow.getCell(10).getStringCellValue().trim()
								.replaceAll("http://api.talentify.in/content/lesson/preview_desktop.jsp\\?ppt_id=", "");
						System.out.println("Lesson from api "+lessonID);
						lessonID = getAPILessonID(lessonID);
						linkLesson(cmsession.getId(), Integer.parseInt(lessonID));
					} else {
						//System.out.println("New Lesson ");

						Lesson lesson = createLesson(lessonName, courseName);
						linkLesson(cmsession.getId(), (lesson.getId()));
					}
					// Lesson lesson = createLesson(lessonName,courseName);
					// linkLesson(cmsession.getId(),(lesson.getId()));

					// System.out.println(currentRow.getCell(10).getStringCellValue().trim());
				}
				// Cmsession cmsession = createCMSESSION(sessionName,m);
			} catch (Exception e) {
				// System.err.println("Exception in loop "+i);
			}
			i = i + 1;

		}

	
		
		
		String lessonID = getAPILessonID("1288");
		System.out.println(lessonID);
		System.exit(0);
	}

	static String getAPILessonID(String lessonID) {
		String DB_URL = "jdbc:postgresql://35.200.218.45:5432/api";
		String USER = "postgres";
		String PASS = "cx6ac54nmgGtLD1y";
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select lesson.id as id from lesson, presentaion where presentaion.lesson_id = lesson.id and presentaion.id="+lessonID;
			
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");

				//System.out.println(", Last: " + id);
				return "" + id;
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return null;
	}

	private static Lesson createLesson(String lessonName, String courseName) {
		Lesson lesson = new Lesson();
		LessonDAO dao = new LessonDAO();
		Session professionalProfileSession = dao.getSession();
		// 10090 PRESENTATION 60 Aptitude/GDPI 1 f Aptitude/GDPI 1 / BOTH t
		lesson.setTitle(lessonName);
		lesson.setType("PRESENTATION");
		lesson.setDuration(60);
		lesson.setSubject(courseName);
		Timestamp createdAt = new Timestamp(System.currentTimeMillis());
		lesson.setCreatedAt(createdAt);
		lesson.setDescription(lessonName);
		lesson.setIsDeleted(false);
		lesson.setCategory("BOTH");
		lesson.setIsPublished(true);
		lesson.setImage_url("/course_images/l_6646.png");
		Transaction professionalProfileTransaction = null;
		try {
			professionalProfileTransaction = professionalProfileSession.beginTransaction();

			professionalProfileSession.save(lesson);
			professionalProfileTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (professionalProfileTransaction != null)
				professionalProfileTransaction.rollback();
		} finally {
			professionalProfileSession.close();
		}

		return lesson;
	}

	private static void updateCourseMod(Integer cmID, Integer modID) {
		try {
			String sql = "INSERT INTO \"public\".\"module_course\" (\"module_id\", \"course_id\") VALUES ('" + modID
					+ "', '" + cmID + "');" + "";
			System.out.println(sql);
			new DBUTILS().executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	private static void linkLesson(Integer cmsID, int lessonID) {
		// cmsession.getId(),Integer.parseInt(lessonID)
		try {
			String sql = "INSERT INTO  lesson_cmsession  (lesson_id, cmsession_id) VALUES ('" + lessonID + "', '"
					+ cmsID + "');";
			//System.out.println(sql);
			new DBUTILS().executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	private static void updatecmsMod(Integer cmID, Integer modID) {
		try {
			String sql = "INSERT INTO  cmsession_module  (\"cmsession_id\", \"module_id\") VALUES ('" + cmID + "', '"
					+ modID + "');";
			// System.out.println(sql);
			new DBUTILS().executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	private static Cmsession createCMSESSION(String sessionName, Module m) {
		CmsessionDAO dao = new CmsessionDAO();
		if (dao.findByTitle(sessionName).size() == 0) {

			Session professionalProfileSession = dao.getSession();
			Cmsession cmsession = new Cmsession(sessionName);
			cmsession.setDescription(sessionName);
			cmsession.setImage_url("/course_images/58.png");
			Transaction professionalProfileTransaction = null;
			try {
				professionalProfileTransaction = professionalProfileSession.beginTransaction();
				// cmsession.getModules().add(m);
				professionalProfileSession.save(cmsession);
				// professionalProfileSession.merge(m);
				professionalProfileTransaction.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (professionalProfileTransaction != null)
					professionalProfileTransaction.rollback();
			} finally {
				professionalProfileSession.close();
			}
			return dao.findByTitle(sessionName).get(0);
		} else {
			Cmsession cmsession = dao.findByTitle(sessionName).get(0);

			return cmsession;
		}
	}

	private static Module addModule(String courseName, Course c) {
		ModuleDAO dao = new ModuleDAO();
		if (dao.findByModuleName(courseName).size() == 0) {

			Session professionalProfileSession = dao.getSession();
			Module module = new Module(courseName);
			module.setModule_description(courseName);
			Timestamp createdAt = new Timestamp(System.currentTimeMillis());
			module.setImage_url("/course_images/m_171.png");
			Transaction professionalProfileTransaction = null;
			try {
				professionalProfileTransaction = professionalProfileSession.beginTransaction();
				// module.getCourses().add(c);

				professionalProfileSession.save(module);
				professionalProfileTransaction.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (professionalProfileTransaction != null)
					professionalProfileTransaction.rollback();
			} finally {
				// professionalProfileSession.close();
			}
			return dao.findByModuleName(courseName).get(0);
		} else {
			Module module = dao.findByModuleName(courseName).get(0);

			return module;
		}
	}

	private static Course createCourse(String courseName) {
		CourseDAO dao = new CourseDAO();
		if (dao.findByCourseName(courseName).size() == 0) {

			Session professionalProfileSession = dao.getSession();
			Course course = new Course(courseName);
			course.setCourseDescription(courseName);
			course.setCategory("DDUGKY");
			course.setTags(courseName);
			Timestamp createdAt = new Timestamp(System.currentTimeMillis());
			course.setCreatedAt(createdAt);
			course.setImage_url("/course_images/9.png");
			Transaction professionalProfileTransaction = null;
			try {
				professionalProfileTransaction = professionalProfileSession.beginTransaction();

				professionalProfileSession.save(course);
				professionalProfileTransaction.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (professionalProfileTransaction != null)
					professionalProfileTransaction.rollback();
			} finally {
				professionalProfileSession.close();
			}

			return course;
		} else {
			return dao.findByCourseName(courseName).get(0);
		}

	}
}
