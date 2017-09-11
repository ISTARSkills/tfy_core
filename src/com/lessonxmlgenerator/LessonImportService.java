/**
 * 
 */
package com.lessonxmlgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.viksitpro.core.cms.oldcontent.CMSImage;
import com.viksitpro.core.cms.oldcontent.CMSLesson;
import com.viksitpro.core.cms.oldcontent.CMSList;
import com.viksitpro.core.cms.oldcontent.CMSParagraph;
import com.viksitpro.core.cms.oldcontent.CMSSlide;
import com.viksitpro.core.cms.oldcontent.CMSTextItem;
import com.viksitpro.core.cms.oldcontent.CMSVideo;
import com.viksitpro.core.utilities.AppProperies;
import com.viksitpro.core.utilities.DBUTILS;



/**
 * @author ISTAR-SERVER-PU-1
 *
 */
public class LessonImportService {

	/**
	 * @param args
	 */
	static FileWriter bw;
	public Connection getConnection()
	{
		try{
			
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://api.talentify.in:5432/postgres","postgres", "X3m2p1z0!@#");
			return connection;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public Connection getTalentifyConnection()
	{
		try{
			
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://cdn.talentify.in:5432/talentify","postgres", "4a626021-e55a");
			return connection;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	
	 public void importLessonForCourse(int courseId) throws SQLException {
		// TODO Auto-generated method stub
		String query="select lesson.id  from lesson, cmsession, module, course where lesson.session_id = cmsession.id and cmsession.module_id = module.id and course.id = module.course_id and course.id = "+courseId;
		Connection con = getConnection();
		try {
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				
				int lessonId = rs.getInt("id");
				createXMLForLesson(lessonId);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	
	
	public void createXMLForAllLesson()
	{
		Connection con = getConnection();
		String getLessonPptDetails  = "select lesson.id  as lesson_id , presentaion.id  as ppt_id from lesson , presentaion, slide where lesson.id = presentaion.lesson_id and presentaion.id = slide.presentation_id and lesson_type != 'ASSESSMENT' group by lesson.id , presentaion.id having (count(slide.id) >1)";
		try {
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(getLessonPptDetails);
			while (rs.next()) {
				
				int lessonId = rs.getInt("lesson_id");
				int pptId = rs.getInt("ppt_id");
				String cmsLessonInString = getCMSLessonString(pptId, lessonId);
				createlessonXMLFile(cmsLessonInString, lessonId);
			}
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
	}

	
	public String getCMSLessonString(int pptID, int lessonId) {
		java.io.StringWriter buffer = new StringWriter();
		Connection con = getConnection();
		DBUTILS dbutils = new DBUTILS();
		CMSLesson cmsLesson = new CMSLesson();
		ArrayList<CMSSlide> cmSslides = new ArrayList<>();
		String sql = "select * from slide where presentation_id=" + pptID + " order by order_id";
		
		Statement statement = null;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.isBeforeFirst()){
				while (rs.next()) {
					CMSSlide cmsSlide = new CMSSlide();
					String slide_xml = (String) rs.getString("slide_text");
					try {
						JAXBContext context = JAXBContext.newInstance(CMSSlide.class);
						String slide_text = slide_xml.replaceAll("<br />", " ").replaceAll("<br>", " ")
								.replaceAll("&nbsp;", " ").replaceAll("&lt;br&gt;", " ").replaceAll("&lt;br /&gt;", " ")
								.replaceAll("[^\\x00-\\x7F]", "");
						// ////System.err.println(slide_text);
						InputStream in = IOUtils.toInputStream(slide_text, "UTF-8");
						Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
						cmsSlide = (CMSSlide) jaxbUnmarshaller.unmarshal(in);
						cmsSlide.setId(rs.getInt("id"));
						cmsSlide.setOrder_id(rs.getInt("order_id"));
						cmsSlide = convertParaToList(cmsSlide, cmsSlide.getTemplateName());
						
						cmsSlide = cleanListSlide(cmsSlide);
						// hack for TN
						cmsSlide = massageCMSLIDE(cmsSlide);
						cmsSlide = saveMediaRelatedToLesson(cmsSlide, lessonId);
						cmsSlide.setTeacherNotes(rs.getString("teacher_notes").toString());

					} catch (JAXBException e) {
						// TODO: handle exception
					} catch (IOException e) {
						// TODO: handle exception
					}
					cmSslides.add(cmsSlide);
				}
				
				cmsLesson.setSlides(cmSslides);
			}
			
			
			sql = "select * from lesson where id = (select p.lesson_id from presentaion  as p where p.id = " + pptID + ")";
			//List<HashMap<String, Object>> lesson = dbutils.executeQuery(sql);
			Statement statement2 = con.createStatement();
			ResultSet rs2 = statement2.executeQuery(sql);
			while (rs2.next()){
				cmsLesson.setLessonTitle(rs2.getString("title"));
				cmsLesson.setLessonDescription("NA");
				cmsLesson.setStudentNotes("NA");
				cmsLesson.setTeacherNotes("NA");
				cmsLesson.setType("PRESENTATION");
				try {
					JAXBContext context = JAXBContext.newInstance(CMSLesson.class);
					javax.xml.bind.Marshaller marshaller = context.createMarshaller();
					marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
					marshaller.marshal(cmsLesson, buffer);
				} catch (JAXBException e) {
					// TODO: handle exception
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
		return buffer.toString();
	
	
	}
	private CMSSlide convertParaToList(CMSSlide cmsSlide, String templateVMFileName) {
		if (templateVMFileName.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH")) {

			CMSParagraph cmsphara = new CMSParagraph();

			if (!cmsSlide.getParagraph().getText().contains("<table")) {

				cmsSlide.setTemplateName("ONLY_TITLE_LIST");
				CMSList list = new CMSList();
				list.setList_type("SIMPLE_LIST_NO_BULLETS");
				
				ArrayList<CMSTextItem> textitemArray = new ArrayList();
				String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");

				for (String w : words) {

					String[] result1 = w.split("\\<li.*?>");
					for (String w1 : result1) {

						CMSTextItem textitem = new CMSTextItem();
						textitem.setText(
								w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
										.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
						textitemArray.add(textitem);
					}

				}
				list.setItems(textitemArray);
				cmsSlide.setList(list);

				cmsphara.setText("");
				cmsSlide.setParagraph(cmsphara);

			} else {
				//System.err.println("has table");
			}

		}

		if (templateVMFileName.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH_IMAGE")) {

			CMSParagraph cmsphara = new CMSParagraph();

			if (!cmsSlide.getParagraph().getText().contains("<table")) {

				cmsSlide.setTemplateName("ONLY_TITLE_PARAGRAPH_IMAGE_LIST");
				CMSList list = new CMSList();
				list.setList_type("SIMPLE_LIST_NO_BULLETS");
				ArrayList<CMSTextItem> textitemArray = new ArrayList();
				String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
				for (String w : words) {

					String[] result1 = w.split("\\<li.*?>");
					for (String w1 : result1) {

						CMSTextItem textitem = new CMSTextItem();
						textitem.setText(
								w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
										.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
						textitemArray.add(textitem);
					}

				}
				list.setItems(textitemArray);
				cmsSlide.setList(list);

				cmsphara.setText("");
				cmsSlide.setParagraph(cmsphara);

			} else {
				//System.err.println("has table");
			}

		}

		if (templateVMFileName.equalsIgnoreCase("ONLY_PARAGRAPH")) {

			CMSParagraph cmsphara = new CMSParagraph();

			if (!cmsSlide.getParagraph().getText().contains("<table")) {

				cmsSlide.setTemplateName("ONLY_LIST");
				CMSList list = new CMSList();
				list.setList_type("SIMPLE_LIST_NO_BULLETS");
				ArrayList<CMSTextItem> textitemArray = new ArrayList();
				String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
				for (String w : words) {

					String[] result1 = w.split("\\<li.*?>");
					for (String w1 : result1) {

						CMSTextItem textitem = new CMSTextItem();
						textitem.setText(
								w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
										.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
						textitemArray.add(textitem);
					}

				}
				list.setItems(textitemArray);
				cmsSlide.setList(list);

				cmsphara.setText("");
				cmsSlide.setParagraph(cmsphara);

			} else {
				//System.err.println("has table");
			}

		}

		if (templateVMFileName.equalsIgnoreCase("ONLY_PARAGRAPH_IMAGE")) {

			CMSParagraph cmsphara = new CMSParagraph();

			if (!cmsSlide.getParagraph().getText().contains("<table")) {

				cmsSlide.setTemplateName("ONLY_PARAGRAPH_IMAGE_LIST");
				CMSList list = new CMSList();
				list.setList_type("SIMPLE_LIST_NO_BULLETS");
				ArrayList<CMSTextItem> textitemArray = new ArrayList();
				String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
				for (String w : words) {

					String[] result1 = w.split("\\<li.*?>");
					for (String w1 : result1) {

						CMSTextItem textitem = new CMSTextItem();
						textitem.setText(
								w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
										.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
						textitemArray.add(textitem);
					}

				}
				list.setItems(textitemArray);
				cmsSlide.setList(list);

				cmsphara.setText("");
				cmsSlide.setParagraph(cmsphara);

			} else {
				//System.err.println("has table");
			}

		}

		if (templateVMFileName.equalsIgnoreCase("ONLY_PARAGRAPH_TITLE")) {

			CMSParagraph cmsphara = new CMSParagraph();

			if (!cmsSlide.getParagraph().getText().contains("<table")) {

				cmsSlide.setTemplateName("ONLY_PARAGRAPH_TITLE_LIST");
				CMSList list = new CMSList();
				list.setList_type("SIMPLE_LIST_NO_BULLETS");
				ArrayList<CMSTextItem> textitemArray = new ArrayList();
				String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
				for (String w : words) {

					String[] result1 = w.split("\\<li.*?>");
					for (String w1 : result1) {

						CMSTextItem textitem = new CMSTextItem();
						textitem.setText(
								w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
										.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
						textitemArray.add(textitem);
					}

				}
				
				list.setItems(textitemArray);
				cmsSlide.setList(list);

				cmsphara.setText("");
				cmsSlide.setParagraph(cmsphara);

			} else {
				//System.err.println("has table");
			}

		}
		return cmsSlide;
	}



	private CMSSlide cleanListSlide(CMSSlide cmsSlide) {
		
	//	if(cmsSlide.getTemplateName().toUpperCase().contains("LIST")){
		
		CMSList list = new CMSList();
		ArrayList<CMSTextItem> items = new ArrayList<>();
		if(cmsSlide.getList()!=null && cmsSlide.getList().getItems()!=null)
		{
			for (CMSTextItem item : cmsSlide.getList().getItems()) {
				if(item!=null && !item.getText().equalsIgnoreCase(""))
				{
					items.add(item);
				}
			}
			list.setList_type(cmsSlide.getList().getList_type());
			list.setItems(items);
			cmsSlide.setList(list);
		}	
		return cmsSlide;
	}



	private String getMediaPath() {
		String mediaPath = null;
		try {
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
				mediaPath = properties.getProperty("mediaPath");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaPath;
	}

	private String getMediaUrl() {
		String mediaPath = null;
		try {
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
				mediaPath = properties.getProperty("media_url_path");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaPath;
	}
	
	private CMSSlide saveMediaRelatedToLesson(CMSSlide cmsSlide, int lessonId) {
		String mediaPath = getMediaPath();
		String oldMediaPath = getOldMediaPath();
		String mediaUrlPath = getMediaUrl();
		mediaUrlPath = mediaUrlPath+"/lessonXMLs/"+lessonId+"/"+lessonId+"/";
		Set<String> allUrls = new HashSet<String>();
		try {
			allUrls.add(oldMediaPath + cmsSlide.getImage_BG());
			try {
				allUrls.add(oldMediaPath + cmsSlide.getImage_BG().replaceAll(".png", "_desktop.png"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			// remove unnecessary path
			if (cmsSlide.getImage_BG() != null && !cmsSlide.getImage_BG().contains("http")
					&& !cmsSlide.getImage_BG().contains("none") && !cmsSlide.getImage_BG().contains("null")) {
				cmsSlide.setImage_BG(mediaUrlPath + cmsSlide.getImage_BG().replace("/video/backgrounds/", ""));
			}
			allUrls.add(oldMediaPath + cmsSlide.getAudioUrl());
			if (cmsSlide.getAudioUrl() != null && !cmsSlide.getAudioUrl().equalsIgnoreCase("none")) {
				cmsSlide.setAudioUrl(mediaUrlPath + cmsSlide.getAudioUrl().replace("/video/", ""));
			}
			if (cmsSlide.getTitle() != null) {
				allUrls.add(oldMediaPath + cmsSlide.getTitle().getFragmentAudioUrl());
				if (cmsSlide.getTitle().getFragmentAudioUrl() != null) {
					cmsSlide.getTitle().setFragmentAudioUrl(
							mediaUrlPath + cmsSlide.getTitle().getFragmentAudioUrl().replace("/video/", ""));
				}
			}
			if (cmsSlide.getTitle2() != null) {
				allUrls.add(oldMediaPath + cmsSlide.getTitle2().getFragmentAudioUrl());
				if (cmsSlide.getTitle2().getFragmentAudioUrl() != null) {
					cmsSlide.getTitle2().setFragmentAudioUrl(
							mediaUrlPath + cmsSlide.getTitle2().getFragmentAudioUrl().replace("/video/", ""));
				}
			}
			if (cmsSlide.getParagraph() != null) {
				allUrls.add(oldMediaPath + cmsSlide.getParagraph().getFragmentAudioUrl());
				if (cmsSlide.getParagraph().getFragmentAudioUrl() != null) {
					cmsSlide.getParagraph().setFragmentAudioUrl(mediaUrlPath
							+ cmsSlide.getParagraph().getFragmentAudioUrl().replace("/video/", ""));
				}

			}
			if (cmsSlide.getList() != null) {
				ArrayList<CMSTextItem> newItems = new ArrayList<>();
				allUrls.add(oldMediaPath + cmsSlide.getList().getMergedAudioURL());
				if(cmsSlide.getList().getItems()!=null)
				{
					for (CMSTextItem item : cmsSlide.getList().getItems()) {
						allUrls.add(oldMediaPath + item.getFragmentAudioUrl());
						if (item.getFragmentAudioUrl() != null
								&& !item.getFragmentAudioUrl().equalsIgnoreCase("") && !item.getFragmentAudioUrl().contains("none")) {
							item.setFragmentAudioUrl(
									mediaUrlPath + item.getFragmentAudioUrl().replace("/video/", ""));

						}
						newItems.add(item);
					}
				}
				cmsSlide.getList().setItems(newItems);

				if (cmsSlide.getList().getMergedAudioURL() != null
						&& !cmsSlide.getList().getMergedAudioURL().equalsIgnoreCase("none")
						&& !cmsSlide.getList().getMergedAudioURL().equalsIgnoreCase("null")) {
					cmsSlide.getList().setMergedAudioURL(
							mediaUrlPath + cmsSlide.getList().getMergedAudioURL().replace("/video/", ""));
				}

			}
			if (cmsSlide.getImage() != null) {

				allUrls.add(oldMediaPath + cmsSlide.getImage().getUrl());
				/////System.out.println("uodated image url " + cmsSlide.getImage().getUrl()
					//	.replace("/content/media_upload?getfile=", "").replace("/video/", ""));
				if (cmsSlide.getImage().getUrl() != null && !cmsSlide.getImage().getUrl().contains("http")) {

					CMSImage im = cmsSlide.getImage();
					String updateImageUrl = mediaUrlPath + cmsSlide.getImage().getUrl()
							.replace("/content/media_upload?getfile=", "").replace("/video/", "");
					im.setUrl(updateImageUrl);
					cmsSlide.setImage(im);
				} else {

				}
				
				allUrls.add(oldMediaPath + cmsSlide.getImage().getFragmentAudioUrl());
				if (cmsSlide.getImage().getFragmentAudioUrl() != null) {
					cmsSlide.getImage().setFragmentAudioUrl(
							mediaUrlPath + cmsSlide.getImage().getFragmentAudioUrl().replace("/video/", ""));
				}

				////System.err.println(">>>>>>>>>>>>>>>" + cmsSlide.getImage().getUrl());

			} else {
				////System.err.println("cmsSlide.getImage() is null");
			}
			if (cmsSlide.getVideo() != null) {
				allUrls.add(oldMediaPath + cmsSlide.getVideo().getUrl());
				if (cmsSlide.getVideo().getUrl() != null && !cmsSlide.getVideo().getUrl().contains("http")) {
					CMSVideo vid = new CMSVideo();
					////System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>video"+oldMediaPath + cmsSlide.getVideo().getUrl());
					String updatedUrl = mediaUrlPath + cmsSlide.getVideo().getUrl().replace("/content/media_upload?getfile=", "").replace("/video/", "").replaceAll(" ", "_");
					vid.setUrl(updatedUrl);
					
					cmsSlide.setVideo(vid);
				}
			}
		} catch (NullPointerException eeee) {
			eeee.printStackTrace();
		}
		

		
		savecollectedMediaInLessonFolder(allUrls, lessonId);
		
		
		
		
		return cmsSlide;
	}


	private void savecollectedMediaInLessonFolder(Set<String> allUrls, int lessonID) {
		
		String mediaPath = getMediaPath();
		String oldMediaPath = getOldMediaPath();
		String mediaUrlPath = getMediaUrl();
		String serverType = getServerType();
		
		Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
		// add owners permission
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);
		// add group permissions
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);
		// add others permissions
		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);
		
		//create structure like below
				//lessonXMLs//lessonId//lessonId//lessonId.xml
					File outerLessonFolder = new File(mediaPath + "lessonXMLs/" + lessonID);			
					////System.out.println(outerLessonFolder.getAbsolutePath());
					if (!outerLessonFolder.exists()) {
						////System.out.println("Folder does not exists");
						outerLessonFolder.mkdir();
						if(serverType.equalsIgnoreCase("linux"))
						{	
							try {
								Files.setPosixFilePermissions(Paths.get(outerLessonFolder.getAbsolutePath()), perms);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}	
					}	
					
					
					File innerLessonFolder = new File(mediaPath + "lessonXMLs/" + lessonID+"/"+lessonID);			
					////System.out.println(innerLessonFolder.getAbsolutePath());
					if (!innerLessonFolder.exists()) {
						////System.out.println("Folder does not exists");
						innerLessonFolder.mkdir();
						if(serverType.equalsIgnoreCase("linux"))
						{	
							try {
								Files.setPosixFilePermissions(Paths.get(innerLessonFolder.getAbsolutePath()), perms);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}	
					}	
					
		
		
		for (String str : allUrls) {
		//	//System.out.println("iterating strsss!!!!!!!!!!!!" + str);
			if (str != null && !str.contains("null") && !str.contains("none")
					&& !str.equalsIgnoreCase(oldMediaPath)) {
				str = str.replace("/content/media_upload?getfile=", "").replaceAll("/video/", "");
				String fileName = str.replace("backgrounds/", "");
				fileName = fileName.replace(getOldMediaPath(), "");
				File src = new File(str);
				////System.err.println("src file name ->"+ src.getAbsolutePath());
				
				File fileInLessonXMLFolder = new File(mediaPath + "lessonXMLs/" + lessonID + "/" +lessonID+"/"+fileName);
				try {
					////System.err.println("src file "+src.getAbsolutePath());
					FileUtils.copyFile(src, fileInLessonXMLFolder);
					if(serverType.equalsIgnoreCase("linux")){
						Files.setPosixFilePermissions(Paths.get(fileInLessonXMLFolder.getAbsolutePath()), perms);
					}
				} catch (IOException e) {

				}
			}

		}
		
		
		
		
	}


	private String getServerType() {
		String mediaPath = null;
		try {
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
				mediaPath = properties.getProperty("server_type");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaPath;
	}


	private CMSSlide massageCMSLIDE(CMSSlide cmsSlide) {
		try
		{
		switch (cmsSlide.getTemplateName()) {
		case "ONLY_TITLE":
			cmsSlide.setFragmentCount(0);
			
			break;
		case "NO_CONTENT":
			cmsSlide.setFragmentCount(0);
			break;
		case "ONLY_TITLE_IMAGE":
			cmsSlide.setFragmentCount(1);
			break;
		case "ONLY_IMAGE":
			cmsSlide.setFragmentCount(0);
			break;
		case "ONLY_VIDEO":
			cmsSlide.setFragmentCount(0);
			break;
		case "ONLY_2TITLE":
			cmsSlide.setFragmentCount(1);
			break;
		case "ONLY_2TITLE_IMAGE":
			cmsSlide.setFragmentCount(2);
			break;

		case "ONLY_LIST":
			cmsSlide.setFragmentCount(cmsSlide.getList().getItems().size() - 1);
			break;
		case "ONLY_TITLE_LIST":
			cmsSlide.setFragmentCount(cmsSlide.getList().getItems().size());
			break;
		case "ONLY_TITLE_LIST_NUMBERED":
			cmsSlide.setFragmentCount(cmsSlide.getList().getItems().size());
			break;
		case "ONLY_LIST_NUMBERED":
			cmsSlide.setFragmentCount(cmsSlide.getList().getItems().size() - 1);
			break;

		case "ONLY_PARAGRAPH":

			ArrayList<String> arrayList = new ArrayList<>();
			if (cmsSlide.getParagraph().getText().contains("<table")) {

				if (cmsSlide.getParagraph().getText().contains("<p")) {

					for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
						arrayList.add(retval);
					}

				} else {
					arrayList.add(cmsSlide.getParagraph().getText());
				}

			} else {
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}
			}

			cmsSlide.setFragmentCount(arrayList.size() - 1);
			break;
		case "ONLY_PARAGRAPH_LIST":

			arrayList = new ArrayList<>();

			for (CMSTextItem ss : cmsSlide.getList().getItems()) {
				if (!ss.getText().isEmpty()) {

					arrayList.add(ss.getText());
				}

			}

			cmsSlide.setFragmentCount(arrayList.size() - 1);
			break;

		case "ONLY_PARAGRAPH_IMAGE":

			arrayList = new ArrayList<>();
			if (cmsSlide.getParagraph().getText().contains("<table")) {

				if (cmsSlide.getParagraph().getText().contains("<p")) {

					for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
						arrayList.add(retval);
					}

				} else {
					arrayList.add(cmsSlide.getParagraph().getText());
				}

			} else {
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}
			}
			cmsSlide.setFragmentCount(arrayList.size());

			break;

		case "ONLY_PARAGRAPH_IMAGE_LIST":
			arrayList = new ArrayList<>();

			for (CMSTextItem ss : cmsSlide.getList().getItems()) {
				if (!ss.getText().isEmpty()) {

					arrayList.add(ss.getText());
				}

			}

			cmsSlide.setFragmentCount(arrayList.size());
			break;
		case "ONLY_TITLE_PARAGRAPH_LIST":
			arrayList = new ArrayList<>();

			for (CMSTextItem ss : cmsSlide.getList().getItems()) {
				if (!ss.getText().isEmpty()) {

					arrayList.add(ss.getText());
				}

			}

			cmsSlide.setFragmentCount(arrayList.size());
			break;

		case "ONLY_TITLE_PARAGRAPH":
			arrayList = new ArrayList<>();

			if (cmsSlide.getParagraph().getText().contains("<table")) {

				if (cmsSlide.getParagraph().getText().contains("<p")) {

					for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
						arrayList.add(retval);
					}

				} else {
					arrayList.add(cmsSlide.getParagraph().getText());
				}

			} else {
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}
			}

			cmsSlide.setFragmentCount(arrayList.size());
			break;

		case "ONLY_PARAGRAPH_TITLE_LIST":
			arrayList = new ArrayList<>();

			for (CMSTextItem ss : cmsSlide.getList().getItems()) {
				if (!ss.getText().isEmpty()) {

					arrayList.add(ss.getText());
				}

			}

			cmsSlide.setFragmentCount(arrayList.size());
			break;

		case "ONLY_PARAGRAPH_TITLE":
			arrayList = new ArrayList<>();
			if (cmsSlide.getParagraph().getText().contains("<table")) {

				if (cmsSlide.getParagraph().getText().contains("<p")) {

					for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
						arrayList.add(retval);
					}

				} else {
					arrayList.add(cmsSlide.getParagraph().getText());
				}

			} else {
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}
			}
			cmsSlide.setFragmentCount(arrayList.size());
			break;
		case "ONLY_TITLE_PARAGRAPH_IMAGE":

			arrayList = new ArrayList<>();
			if (cmsSlide.getParagraph().getText().contains("<table")) {

				if (cmsSlide.getParagraph().getText().contains("<p")) {

					for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
						arrayList.add(retval);
					}

				} else {
					arrayList.add(cmsSlide.getParagraph().getText());
				}

			} else {
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}
			}
			cmsSlide.setFragmentCount(arrayList.size() + 1);

			break;

		case "ONLY_TITLE_PARAGRAPH_IMAGE_LIST":

			arrayList = new ArrayList<>();

			for (CMSTextItem ss : cmsSlide.getList().getItems()) {
				if (!ss.getText().isEmpty()) {

					arrayList.add(ss.getText());
				}

			}

			cmsSlide.setFragmentCount(arrayList.size() + 1);

			break;

		case "ONLY_TITLE_TREE":

			arrayList = new ArrayList<>();

			for (CMSTextItem ss : cmsSlide.getList().getItems()) {
				if (!ss.getText().isEmpty()) {

					arrayList.add(ss.getText());

					for (CMSTextItem sl : ss.getList().getItems()) {

						arrayList.add(sl.getText());
					}

				}

			}

			cmsSlide.setFragmentCount(arrayList.size());

			break;

		default:
			break;

		case "ONLY_2BOX":

			arrayList = new ArrayList<>();

			for (CMSTextItem ss : cmsSlide.getList().getItems()) {
				if (!ss.getText().isEmpty()) {

					arrayList.add(ss.getText());
				}

			}

			cmsSlide.setFragmentCount(arrayList.size() + 1);

			break;
		}

		}catch(Exception ex)
		{
			
		}
		// TODO Auto-generated method stub
		return cmsSlide;
		
	}
	
	private String getOldMediaPath() {
		String mediaPath = null;
		try {
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
				mediaPath = properties.getProperty("old_media_path");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaPath;
	}
	
	
	public boolean createlessonXMLFile(String cmsLessonInString, int lessonID) throws IOException{
		boolean success = false;
		Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
		// add owners permission
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);
		// add group permissions
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);
		// add others permissions
		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);
		
		if(!cmsLessonInString.trim().equalsIgnoreCase("")){
			String mediaPath ="";
			String serverType = "windows";
			try {
				Properties properties = new Properties();
				String propertyFileName = "app.properties";
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
				if (inputStream != null) {
					properties.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
				}
				mediaPath = properties.getProperty("mediaPath");
				serverType = properties.getProperty("server_type");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		//create structure like below
		//lessonXMLs//lessonId//lessonId//lessonId.xml
			File outerLessonFolder = new File(mediaPath + "lessonXMLs/" + lessonID);			
			//System.out.println(outerLessonFolder.getAbsolutePath());
			if (!outerLessonFolder.exists()) {
				//System.out.println("Folder does not exists");
				outerLessonFolder.mkdir();
				if(serverType.equalsIgnoreCase("linux"))
				{	
					Files.setPosixFilePermissions(Paths.get(outerLessonFolder.getAbsolutePath()), perms);
				}	
			}	
			
			
			File innerLessonFolder = new File(mediaPath + "lessonXMLs/" + lessonID+"/"+lessonID);			
			//System.out.println(innerLessonFolder.getAbsolutePath());
			if (!innerLessonFolder.exists()) {
				//System.out.println("Folder does not exists");
				innerLessonFolder.mkdir();
				if(serverType.equalsIgnoreCase("linux"))
				{	
					Files.setPosixFilePermissions(Paths.get(innerLessonFolder.getAbsolutePath()), perms);
				}	
			}	
			

			String lessonXMLPath=mediaPath + "lessonXMLs/" + lessonID+"/"+lessonID+"/"+lessonID+".xml";
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(lessonXMLPath), "UTF-8"));
			try {				
					//////System.err.println(lessonXML);
			    out.write(cmsLessonInString.replaceAll("[^\\x00-\\x7F]",""));				
			} finally {
			    out.close();
			}			
			//File file = new File(path);		
	}	
		return success;
	}



	public void createXMLForLesson(int lessonId) {
		Connection con = getConnection();
		String getLessonPptDetails  = "select lesson.id  as lesson_id , presentaion.id  as ppt_id from lesson , presentaion, slide where lesson.id = presentaion.lesson_id and presentaion.id = slide.presentation_id and lesson_type != 'ASSESSMENT' and presentaion.lesson_id="+lessonId+" group by lesson.id , presentaion.id having (count(slide.id) >1)";
		try {
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(getLessonPptDetails);
			while (rs.next()) {
				
				
				int pptId = rs.getInt("ppt_id");
				String cmsLessonInString = getCMSLessonString(pptId, lessonId);
				createlessonXMLFile(cmsLessonInString, lessonId);
			}
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
	}

	public void importAssessmentForCourse(int courseId) {
		// TODO Auto-generated method stub
		String query="select assessment.id, lesson.id as lesson_id,lesson.title as lesson_title, cmsession.id as session_id,module.id as module_id   from lesson, cmsession, module, course, assessment where lesson.session_id = cmsession.id and cmsession.module_id = module.id and course.id = module.course_id and course.id = "+courseId+" and lesson.dtype = 'ASSESSMENT' and assessment.lesson_id = lesson.id ";
		System.out.println(query);
		Connection con = getConnection();
		try {
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				
				int assessmentId = rs.getInt("id");
				int lessonId = rs.getInt("lesson_id");
				int sessionId = rs.getInt("session_id");
				int moduleId = rs.getInt("module_id");
				String lessontitle =  rs.getString("lesson_title");
				//boolean treeExist = importLesson(lessonId, sessionId, moduleId, lessontitle, assessmentId);
				//if(treeExist) {
					importAssessment(assessmentId, courseId);
				//}
			}
			
			//con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private boolean importLesson(int lessonId, int sessionId, int moduleId, String lessonTitle, int assessmentID) {
		boolean treeExist = true;
		
		
		try {
			Statement statement = getLocalConnection().createStatement();
			String getlesson = "select * from lesson where id ="+lessonId;
			ResultSet rs = statement.executeQuery(getlesson);
			if(rs.next()) {
				bw=new FileWriter(AppProperies.getProperty("mediaLessonPath")+"/assessments/lesson_"+lessonId+"_assessment_lesson.sql");
				String insertQuery ="update lesson set lesson_xml='"+assessmentID+"' where id ="+lessonId+"";
				bw.write(insertQuery);
				bw.write(System.lineSeparator());
				bw.close();	
			}
			else
			{
				//insert lessons
				bw=new FileWriter(AppProperies.getProperty("mediaLessonPath")+"/assessments/lesson_"+lessonId+"_assessment_lesson.sql");
				String insertQuery ="INSERT INTO lesson (id, type, duration, tags, title, subject, order_id, created_at, is_deleted, description, lesson_xml, category, is_published, org_id) "
						+ "VALUES ("+lessonId+", 'ASSESSMENT', '60', NULL, '"+lessonTitle.replace("'", "").trim()+"', 'NONE', NULL, '2017-07-26 14:30:26.639738', 'f', NULL, '"+assessmentID+"', 'BOTH', 't', NULL);";
				bw.write(insertQuery);
				bw.write(System.lineSeparator());
				bw.close();
			}			
		
			
			
		}catch(Exception ex)
		{
			
		}
		
		return treeExist;
	}

	private void importAssessment(int assessmentId, int courseId) {

		
		
		
		try {
			Statement statement = getConnection().createStatement();
			String getAssessmentDetails = "select * from assessment where id ="+assessmentId;
			System.out.println(getAssessmentDetails);
			ResultSet rs = statement.executeQuery(getAssessmentDetails);
			while (rs.next()) {
				
			try{
				bw=new FileWriter(AppProperies.getProperty("mediaLessonPath")+"/assessments/"+assessmentId+"_assessment.sql");

				 Statement checkAssesState = getLocalConnection().createStatement();
				 String checkIfAssessmentExist ="select * from assessment where id="+assessmentId;
				 ResultSet chekAssess = checkAssesState.executeQuery(checkIfAssessmentExist);
				 if(!chekAssess.next())
				 {
					System.out.println("inserting");
					String insertQuery="INSERT INTO public.assessment (id, assessment_type, created_at, number_of_questions, assessmentdurationhours, assessmentdurationminutes, assessmenttitle, retry_able, category, description, is_published, course_id) "
							+ "VALUES ("+rs.getInt("id")+", '"+rs.getString("assessment_type")+"', now(), "+rs.getInt("number_of_questions")+", "+rs.getInt("assessmentdurationhours")+", "+rs.getInt("assessmentdurationminutes")+", '"+rs.getString("assessmenttitle").toString().trim().replace("'", "")+"', '"+rs.getBoolean("retry_able")+"', '"+rs.getString("category")+"', 'Not Available', 't',"+courseId+");";						
					
					bw.write(insertQuery);
					bw.write(System.lineSeparator());
					
				 }
					String findAssessmentQuestsion = "select * from assessment_question where assessmentid = "+rs.getInt("id");
					
					Statement statement2 = getConnection().createStatement();
					
					ResultSet rs2 = statement2.executeQuery(findAssessmentQuestsion);
					
					while (rs2.next()) {
						
						
						String findQuestionDetails = "select * from question where id ="+rs2.getInt("questionid");
						Statement statement3 = getConnection().createStatement();
						
						ResultSet rs3 = statement3.executeQuery(findQuestionDetails);
						
						while(rs3.next())
						{
						
							Statement checkQueState = getLocalConnection().createStatement();
							 String checkIfQueExist ="select * from question where id="+rs2.getInt("questionid");
							 System.out.println(checkIfQueExist);
							 ResultSet chekQue = checkQueState.executeQuery(checkIfQueExist);
							 if(!chekQue.next())
							 {
								 String passage="";
								 if(rs3.getString("comprehensive_passage_text")!=null)
								 {
									 passage= rs3.getString("comprehensive_passage_text").trim().replace("'", "");
								 } 
								 String insertIntoQuestion = "INSERT INTO question (id, question_text, question_type, created_at, difficulty_level, specifier, duration_in_sec, explanation, comprehensive_passage_text, points, context_id) "
									+ "VALUES ("+rs3.getInt("id")+", '"+rs3.getString("question_text").trim().replace("'", "")+"', "+rs3.getInt("question_type")+", now(), "+rs3.getInt("difficulty_level")+", "+rs3.getInt("specifier")+", "+rs3.getInt("duration_in_sec")+", "
											+ "'"+rs3.getString("explanation").trim().replace("'", "")+"', "
													+ "'"+passage+"', '1', "+courseId+");";							
								 bw.write(insertIntoQuestion);
								 bw.write(System.lineSeparator());
								 
							 }
							String findOptionRelatedToQuestion = "select * from assessment_option where question_id = "+rs2.getInt("questionid");
							Statement statement4 = getConnection().createStatement();
							
							ResultSet rs4 = statement4.executeQuery(findOptionRelatedToQuestion);
							while(rs4.next())
							{
								Statement checkOptionState = getLocalConnection().createStatement();
								 String checkIfOptinExist ="select * from assessment_option where id="+rs4.getInt("id");
								 ResultSet chekOption = checkOptionState.executeQuery(checkIfOptinExist);
								if(!chekOption.next())
								{
									String insertIntoAssessmentOption ="INSERT INTO assessment_option (id, text, question_id, marking_scheme) "
										+ "VALUES ("+rs4.getInt("id")+", '"+rs4.getString("text").trim().replace("'", "")+"', "+rs4.getInt("question_id")+", "+rs4.getInt("marking_scheme")+");";
								
									bw.write(insertIntoAssessmentOption);		
									bw.write(System.lineSeparator());
									
								}
							}
							
						}
						
						Statement checkAssessmentQueueState = getLocalConnection().createStatement();
						String checkIfAssessmentQueueExist ="select * from assessment_question where assessmentid="+rs2.getInt("assessmentid")+" and questionid="+rs2.getInt("questionid");
						ResultSet chekAssessmentQueue = checkAssessmentQueueState.executeQuery(checkIfAssessmentQueueExist);
						if(!chekAssessmentQueue.next())
						{
							String insertIntoAssessmentQuestion = "INSERT INTO assessment_question (assessmentid, questionid, created_at, order_id, id) "
									+ "VALUES ("+rs2.getInt("assessmentid")+", "+rs2.getInt("questionid")+", now(), "+rs2.getInt("order_id")+", "+rs2.getInt("id")+");";
							bw.write(insertIntoAssessmentQuestion);
							bw.write(System.lineSeparator());
							
						}	
						
						
					}
					
					bw.close();
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
					// no need to close it.
					//bw.close();

					//System.out.println("Done");

				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	private Connection getLocalConnection() {
		try{
			
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://localhost:5432/talentify","postgres", "4a626021-e55a");
			return connection;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	private Connection getBusinessConnection() {
		try{
			
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://business.talentify.in:5432/talentify","postgres", "4a626021-e55a");
			return connection;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public void importSkillForCourse(int courseId) {
		
		try {
			Statement statement = getConnection().createStatement();
			String getModuleLevelSkill ="select * from skill where parent_skill = (select parent_skill from course where id = "+courseId+")";
			ResultSet modSkills = statement.executeQuery(getModuleLevelSkill);
			while (modSkills.next()) {
				String skillTitle = modSkills.getString("skill_title"); 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void importModuleCMSessionLesson(int courseId) {
		
		//select 'INSERT INTO public.course (id, course_name, course_description,  created_at, image_url) 		VALUES ('||id||', '''||course_name||''', '''||course_description||''', now(), ''/course_images/'||id||'.png'');' from course where id in (58,59,60,62,63,64,65,66);
		//select 'INSERT INTO module (id, module_name, is_deleted, module_description, image_url) 	VALUES ('||id||', '''||module_name||''', ''f'', '''||module_name||''', ''/course_images/m_'||id||'.png'');' from module where course_id in (58,59,60,62,63,64,65,66);
		//select 'INSERT INTO module_course (module_id, course_id) VALUES ('||id||', '||course_id||');' from  module where course_id in (58,59,60,62,63,64,65,66);
		//select 'INSERT INTO cmsession (id, title, description, is_deleted, created_at, image_url) VALUES ('||id||', '''||title||''', '''||description||''', ''f'', now(), ''/course_images/'||(select course_id from module where id = module_id)||'.png'');' from cmsession where module_id in (select id from module where course_id in (58,59,60,62,63,64,65,66) );
		//select 'INSERT INTO cmsession_module (cmsession_id, module_id) VALUES ('||id||', '||module_id||');' from cmsession where module_id in (select id from module where course_id in (58,59,60,62,63,64,65,66));

		
		try {
			bw=new FileWriter(AppProperies.getProperty("mediaLessonPath")+"/courses/"+courseId+"_course.sql");
			
			String sql1 ="select 'INSERT INTO public.course (id, course_name, course_description,  created_at, image_url) 		VALUES ('||id||', '''||course_name||''', '''||course_description||''', now(), ''/course_images/'||id||'.png'');' as q from course where id in ("+courseId+")";
			Statement statement1 = getConnection().createStatement();
			
			ResultSet rs1 = statement1.executeQuery(sql1);
			while (rs1.next()) {
				bw.write(rs1.getString("q"));
				bw.write(System.lineSeparator());
			//System.out.println();
			}
			
			
			String sql2 ="select 'INSERT INTO module (id, module_name, is_deleted, module_description, image_url) 	VALUES ('||id||', '''||module_name||''', ''f'', '''||module_name||''', ''/course_images/m_'||id||'.png'');' as q from module where course_id in ("+courseId+");";
			Statement statement2 = getConnection().createStatement();
			
			ResultSet rs2 = statement2.executeQuery(sql2);
			while (rs2.next()) {
				bw.write(rs2.getString("q"));
				bw.write(System.lineSeparator());
			//System.out.println();
			}
			
			
			String sql3 ="SELECT	'INSERT INTO module_course (module_id, course_id) VALUES (' || ID || ', ' || course_id || ');' as q FROM	MODULE WHERE	course_id IN ("+courseId+");";
			Statement statement3 = getConnection().createStatement();
			
			ResultSet rs3 = statement3.executeQuery(sql3);
			while (rs3.next()) {
				bw.write(rs3.getString("q"));
				bw.write(System.lineSeparator());
			//System.out.println();
			}
			
			
			String sql4 ="select 'INSERT INTO cmsession (id, title, description, is_deleted, created_at, image_url) VALUES ('||id||', '''||title||''', '''||description||''', ''f'', now(), ''/course_images/'||(select course_id from module where id = module_id)||'.png'');' as q from cmsession where module_id in (select id from module where course_id in ("+courseId+") )";
			Statement statement4 = getConnection().createStatement();
			
			ResultSet rs4 = statement4.executeQuery(sql4);
			while (rs4.next()) {
				bw.write(rs4.getString("q"));
				bw.write(System.lineSeparator());
			//System.out.println();
			}
			
			
			String sql5 ="select 'INSERT INTO cmsession_module (cmsession_id, module_id) VALUES ('||id||', '||module_id||');'  as q from cmsession where module_id in (select id from module where course_id in ("+courseId+"))";
			Statement statement5 = getConnection().createStatement();
			
			ResultSet rs5 = statement5.executeQuery(sql5);
			while (rs5.next()) {
				bw.write(rs5.getString("q"));
				bw.write(System.lineSeparator());
			//System.out.println();
			}
			
			
			
			
			
			String findLessonInCourse="select lesson.*, cmsession.id  as session_id from lesson, cmsession, module, course where lesson.session_id = cmsession.id and cmsession.module_id = module.id and course.id = module.course_id and course.id = "+courseId;
			Statement statement = getConnection().createStatement();
			
			ResultSet lessons = statement.executeQuery(findLessonInCourse);
			while (lessons.next()) {
				
				
				String type="";
				
				if(lessons.getString("dtype")=="sds")
				{
					type = "PRESENTATION";
				}
				else
				{
					type = lessons.getString("dtype");
					
				}	
				String insertIntoLesson="INSERT INTO lesson (id, type, duration, title, subject,  created_at, is_deleted, image_url, lesson_xml, category, is_published) "
						+ "VALUES ("+lessons.getInt("id")+", '"+type+"', "+lessons.getInt("duration")+", '"+lessons.getString("title")+"', '"+lessons.getString("subject")+"', now(), 'f', '/course_images/l_"+lessons.getInt("id")+".png', '', 'BOTH', 't');";
				bw.write(insertIntoLesson);
				bw.write(System.lineSeparator());
				
				String insertIntoLessonCMsession = "insert into lesson_cmsession (lesson_id, cmsession_id) values ("+lessons.getInt("id")+","+lessons.getInt("session_id")+");";
				bw.write(insertIntoLessonCMsession);
				bw.write(System.lineSeparator());
				
				if(lessons.getString("dtype")=="ASSESSMENT")
				{
					String ss="select 'update lesson set lesson_xml='''||id||''' where id ='||lesson_id||';' from assessment where lesson_id = "+lessons.getInt("id");
					bw.write(ss);
					bw.write(System.lineSeparator());
				}
				
			}
			bw.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String getModules ="";
		
	}

	

	public void createImportScriptForLesson(int lessonId) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		Connection local = getLocalConnection();
		String getLessonPptDetails  = "select * from lesson where id ="+lessonId;
		try {
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(getLessonPptDetails);
			while (rs.next()) {
				
				String check="select * from lesson where id="+lessonId;
				Statement checkStatement = local.createStatement();
				ResultSet checkResult = checkStatement.executeQuery(check);
				if(checkResult.next())
				{
					String insertQuery="INSERT INTO lesson (id, type, duration, title, order_id, created_at, is_deleted, lesson_xml, category, is_published, org_id) "
							+ "VALUES ("+lessonId+", 'PRESENTATION', 10,  '"+rs.getString("title").trim().replace("'", "")+"', NULL, now(), 'f', '', 'BOTH', 'f', NULL);";
					String mapping="insert into lesson_cmsession (lesson_id, cmsession_id) values("+lessonId+","+rs.getInt("session_id")+")";
					try {
						bw=new FileWriter("C:\\Users\\ISTAR-SKILL\\Documents\\lessonScripts\\"+lessonId+"_lesson.sql");

						
						bw.write(insertQuery);
						bw.write(System.lineSeparator());
						
						bw.write(mapping);
						bw.write(System.lineSeparator());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException  e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
}
