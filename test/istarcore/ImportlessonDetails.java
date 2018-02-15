/*package istarcore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.viksitpro.core.cms.oldcontent.CMSLesson;
import com.viksitpro.core.cms.oldcontent.CMSSlide;
import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.ModuleDAO;
import com.viksitpro.core.dao.entities.StudentPlaylist;
import com.viksitpro.core.dao.entities.StudentPlaylistDAO;
import com.viksitpro.core.dao.entities.UserProfile;
import com.viksitpro.core.dao.utils.user.IstarUserServices;

public class ImportlessonDetails {

	public static void main(String[] args) {

		try {
			//test();
			fixLessons();
			
			//for (Course course : (new CourseDAO()).findAll()) {
				//for (Module module : (new ModuleDAO()).findAll()) {
				//	String sq = "update module set image_url='/course_images/m_"+module.getId()+".png' where id="+module.getId()+" ;";
				//	//ViksitLogger.logMSG(this.getClass().getName(),(sq);
					
				//}
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void fixLessons() throws FileNotFoundException, IOException {
		 String file = "C:\\Users\\ISTAR-SERVER-PU-1\\Downloads\\Duration & lessons to be deleted.xlsx";
			XSSFWorkbook fs = new XSSFWorkbook(new FileInputStream(file));
			    XSSFSheet wb = fs.getSheetAt(0);
			    int lastROW = wb.getLastRowNum();

			   for (int i = 0; i < lastROW; i++) {
				   try {
					int lessonID = (int) wb.getRow(i).getCell(1).getNumericCellValue();
					   String lessonTitle = wb.getRow(i).getCell(2).getStringCellValue();
					  // String lessonTitle = wb.getRow(i).getCell(4).getStringCellValue();
					   int duration = (int) wb.getRow(i).getCell(7).getNumericCellValue();
					   String toBeDeleted =   wb.getRow(i).getCell(8).getStringCellValue();
					   boolean idDeleted = false;
					   if(toBeDeleted.trim().equalsIgnoreCase("To be deleted")) {
						   idDeleted = true;
					   }
					   if(!idDeleted) {
						   //ViksitLogger.logMSG(this.getClass().getName(),("update lesson set  duration="+duration+",  title='"+lessonTitle.replaceAll("_", " ")+"'  "
						   		+ " , is_deleted='t'   where id='"+lessonID+"' ;");
					   } 
						   
					  

				   } catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		
	}

	public static void test() throws FileNotFoundException, IOException {
		 String file = "C:\\Users\\vaibhav verma\\Downloads\\Digital Marketing.xlsx";
		XSSFWorkbook fs = new XSSFWorkbook(new FileInputStream(file));
		    XSSFSheet wb = fs.getSheetAt(0);
		    int lastROW = wb.getLastRowNum();

		   for (int i = 0; i < lastROW; i++) {
			   try {
				int lessonID = (int) wb.getRow(i).getCell(2).getNumericCellValue();
				   int slideID =  Integer.parseInt(wb.getRow(i).getCell(3).getStringCellValue().split("#")[1]);
				   String tnotes = wb.getRow(i).getCell(4).getStringCellValue();
				   //ViksitLogger.logMSG(this.getClass().getName(),("lesson id ->"+ lessonID+" slideID -> "+slideID+ " coment "+tnotes.trim());
				   updateSlideTN(lessonID, slideID, tnotes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		  
	}

	static void updateSlideTN(int lessonID, int slideID, String Tnotes) {
		try {
			File file = new File("C:\\Users\\vaibhav verma\\Downloads\\xmls\\" + lessonID + ".xml");
			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(CMSLesson.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			CMSLesson cmsLesson = (CMSLesson) jaxbUnmarshaller.unmarshal(file);

			//ViksitLogger.logMSG(this.getClass().getName()," CMS Lesson " + cmsLesson.getLessonTitle());

			for (CMSSlide cmsSlide : cmsLesson.getSlides()) {
				if(cmsSlide.getId() == slideID){
					cmsSlide.setTeacherNotes(Tnotes);
				}
			}

			jaxbMarshaller.marshal(cmsLesson, file);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}*/