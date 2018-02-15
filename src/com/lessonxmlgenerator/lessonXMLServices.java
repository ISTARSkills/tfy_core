package com.lessonxmlgenerator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class lessonXMLServices {

	public String LessonHTML(int lessonID) {
		String LessonHTML = "";
		LessonHTML += (new SlideUtils()).getLessonHTML(lessonID);
		return LessonHTML;
	}
	
	public String lessonXML(int lessonID){
		int pptID=626;
		String sql = "select * from presentaion where lesson_id = "+lessonID;
		for(HashMap<String, Object> presentation : (new com.viksitpro.core.utilities.DBUTILS()).executeQuery(sql)){
			pptID = Integer.parseInt(presentation.get("id").toString());
		}
		String lessonXML="";
		lessonXML+=(new SlideUtils()).getLessonXML(pptID);
		return lessonXML;
	}
	
	public boolean createlessonXMLFile(String lessonXML, int lessonID) throws IOException{
		boolean success = false;
	////////
		if(!lessonXML.trim().equalsIgnoreCase("")){
			String path ="";
			try {
				Properties properties = new Properties();
				String propertyFileName = "app.properties";
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
				if (inputStream != null) {
					properties.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
				}
				path = properties.getProperty("mediaLessonPath1");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			

		path+=""+lessonID+".xml";
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(path), "UTF-8"));
			try {
				
					////ViksitLogger.logMSG(this.getClass().getName(),(lessonXML);
			    out.write(lessonXML.replaceAll("[^\\x00-\\x7F]",""));
				
			} finally {
			    out.close();
			}
			
			//File file = new File(path);
		
	}	
		return success;
	}
	
	//to write xml files to local file system
	public void generateAllLessonXMLFiles() throws NumberFormatException, IOException{
		String sql = "select lesson_id from presentaion ORDER BY lesson_id";
		List<HashMap<String, Object>> lessons = (new com.viksitpro.core.utilities.DBUTILS()).executeQuery(sql);
		lessonXMLServices xmlServices = new lessonXMLServices();
		////ViksitLogger.logMSG(this.getClass().getName(),slideUtils.getLessonXML(626));
		for(HashMap<String, Object> lesson : lessons){
			//ViksitLogger.logMSG(this.getClass().getName(),(createlessonXMLFile(xmlServices.lessonXML(Integer.parseInt(lesson.get("lesson_id").toString())), 
					//Integer.parseInt(lesson.get("lesson_id").toString())));
		}
		
	}
}
