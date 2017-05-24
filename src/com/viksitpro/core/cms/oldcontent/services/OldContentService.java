package com.viksitpro.core.cms.oldcontent.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.viksitpro.core.cms.oldcontent.CMSLesson;
import com.viksitpro.core.cms.oldcontent.CMSSlide;
import com.viksitpro.core.cms.oldcontent.CMSTextItem;
import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.Course;
import com.viksitpro.core.dao.entities.CourseDAO;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.Module;

public class OldContentService {

		public void createZipForCourse(int courseId)
		{			
			String mediaPath = getMediaPath();
			Course c = new CourseDAO().findById(courseId);
			for(Module m : c.getModules())
			{
				for(Cmsession cms: m.getCmsessions())
				{
					for(Lesson l : cms.getLessons())
					{
						if(l.getCategory().equalsIgnoreCase("ILT") || l.getCategory().equalsIgnoreCase("BOTH")){
							System.err.println("creating folder for lesson "+ l.getId());
							createFolderForLessonInCourse(c.getId(), l.getId());
						}
					}
				}
			}
			String SOURCE_FOLDER = mediaPath+"/courseZIPs/"+c.getId();
			File sourceFile = new File(SOURCE_FOLDER);
			
			String zipName = mediaPath+"/courseZIPs/"+c.getId()+".zip";
			
			ZipFiles zipFiles = new ZipFiles();
	        zipFiles.zipDirectory(sourceFile, zipName);
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

		public void createFolderForLessonInCourse(int courseId, int lessonId)
		{
			
			Set<String> allUrls = new HashSet<String>();
			String mediaPath =getMediaPath();
			String oldMediaPath = getOldMediaPath();
			try {
				JAXBContext  jaxbContext= JAXBContext.newInstance(CMSLesson.class);
				File file = new File(mediaPath+"/lessonXMLs/"+lessonId+".xml");
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				CMSLesson cmsLesson = (CMSLesson) unmarshaller.unmarshal(file);
				for (CMSSlide cmsSlide : cmsLesson.getSlides()) {
					 
					allUrls.add(oldMediaPath+cmsSlide.getImage_BG());
					allUrls.add(oldMediaPath+cmsSlide.getAudioUrl());
					if(cmsSlide.getTitle()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getTitle().getFragmentAudioUrl());
					}
					if(cmsSlide.getTitle2()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getTitle2().getFragmentAudioUrl());
					}
					if(cmsSlide.getParagraph()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getParagraph().getFragmentAudioUrl());
					}
					if(cmsSlide.getList()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getList().getMergedAudioURL());
						for(CMSTextItem item : cmsSlide.getList().getItems())
						{
							allUrls.add(oldMediaPath+item.getFragmentAudioUrl());							
						}
					}
					if(cmsSlide.getImage()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getImage().getFragmentAudioUrl());
					}
					if(cmsSlide.getVideo()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getVideo().getUrl());
					}
					
				}
				
				File courseFolder = new File(mediaPath + "courseZIPs/"+courseId);

				System.out.println(courseFolder.getAbsolutePath());
				if (!courseFolder.exists()) {
					System.out.println("Folder does not exists");
					courseFolder.mkdir();
				}
				
				File lessonFolder = new File(mediaPath + "courseZIPs/"+courseId+"/"+lessonId);

				System.out.println(lessonFolder.getAbsolutePath());
				if (!lessonFolder.exists()) {
					System.out.println("Folder does not exists");
					lessonFolder.mkdir();
				}
				
				for(String str :allUrls)
				{
					System.out.println(str);
					if(!str.contains("null") && !str.contains("none") && !str.equalsIgnoreCase(oldMediaPath))
					{
						str = str.replace("/content/media_upload?getfile=", "").replaceAll("/video/", "");
						String fileName = str.substring(str.lastIndexOf("/")); 						
						File src = new File(str.replace("/content/media_upload?getfile=", "").replaceAll("/video/", ""));
						File dest = new File(mediaPath + "courseZIPs/"+courseId+"/"+lessonId+"/"+fileName);
						System.err.println(src.getAbsolutePath());
						System.err.println(dest.getAbsolutePath());
						FileUtils.copyFile(src, dest);						
					}
				}
				
				File srcLessonXml = new File(mediaPath+"/lessonXMLs/"+lessonId+".xml");
				File destLessonXML = new File(mediaPath + "courseZIPs/"+courseId+"/"+lessonId+"/"+lessonId+".xml");
				FileUtils.copyFile(srcLessonXml, destLessonXML);	
			} catch (Exception e) {
				e.printStackTrace();
			}
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
}
