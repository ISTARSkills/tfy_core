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
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.viksitpro.core.cms.oldcontent.CMSImage;
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

		public void createFolderForLessonInCourse(int courseId, int lessonId)
		{
			
			Set<String> allUrls = new HashSet<String>();
			String mediaPath =getMediaPath();
			String oldMediaPath = getOldMediaPath();
			String mediaUrlPath= getMediaUrl();
			try {
				JAXBContext  jaxbContext= JAXBContext.newInstance(CMSLesson.class);
				File file = new File(mediaPath+"/lessonXMLs/"+lessonId+".xml");
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				CMSLesson cmsLesson = (CMSLesson) unmarshaller.unmarshal(file);
				
				mediaUrlPath = mediaUrlPath+"/lessonXMLs/"+lessonId+"/";
				for (CMSSlide cmsSlide : cmsLesson.getSlides()) {
					 try{
					allUrls.add(oldMediaPath+cmsSlide.getImage_BG());
					//remove unnecessary path
					if(cmsSlide.getImage_BG()!=null)
					{
						cmsSlide.setImage_BG(mediaUrlPath+cmsSlide.getImage_BG().replace("/video/backgrounds/", ""));
					}
					allUrls.add(oldMediaPath+cmsSlide.getAudioUrl());
					if(cmsSlide.getAudioUrl()!=null)
					{
						cmsSlide.setAudioUrl(mediaUrlPath+cmsSlide.getAudioUrl().replace("/video/",""));
					}
					
					
					if(cmsSlide.getTitle()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getTitle().getFragmentAudioUrl());
						if(cmsSlide.getTitle().getFragmentAudioUrl()!=null)
						{
							cmsSlide.getTitle().setFragmentAudioUrl(mediaUrlPath+cmsSlide.getTitle().getFragmentAudioUrl().replace("/video/", ""));
						}
						
					}
					if(cmsSlide.getTitle2()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getTitle2().getFragmentAudioUrl());
						if(cmsSlide.getTitle2().getFragmentAudioUrl()!=null)
						{
							cmsSlide.getTitle2().setFragmentAudioUrl(mediaUrlPath+cmsSlide.getTitle2().getFragmentAudioUrl().replace("/video/", ""));
						}
						
					}
					if(cmsSlide.getParagraph()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getParagraph().getFragmentAudioUrl());
						if(cmsSlide.getParagraph().getFragmentAudioUrl()!=null)
						{
							cmsSlide.getParagraph().setFragmentAudioUrl(mediaUrlPath+cmsSlide.getParagraph().getFragmentAudioUrl().replace("/video/", ""));
						}
						
					}
					if(cmsSlide.getList()!=null)
					{
						ArrayList<CMSTextItem> newItems = new ArrayList<>();
						allUrls.add(oldMediaPath+cmsSlide.getList().getMergedAudioURL());
						for(CMSTextItem item : cmsSlide.getList().getItems())
						{
							allUrls.add(oldMediaPath+item.getFragmentAudioUrl());	
							if(item.getFragmentAudioUrl()!=null)
							{
								item.setFragmentAudioUrl(mediaUrlPath+item.getFragmentAudioUrl().replace("/video/", ""));
								
							}
							newItems.add(item);
						}
						cmsSlide.getList().setItems(newItems);
						
						if(cmsSlide.getList().getMergedAudioURL()!=null)
						{
							cmsSlide.getList().setMergedAudioURL(mediaUrlPath+cmsSlide.getList().getMergedAudioURL().replace("/video/", ""));
						}
						
					}
					if(cmsSlide.getImage()!=null)
					{
						
						allUrls.add(oldMediaPath+cmsSlide.getImage().getUrl());
						System.out.println("uodated image url "+cmsSlide.getImage().getUrl().replace("/content/media_upload?getfile=", "").replace("/video/", ""));
						if(cmsSlide.getImage().getUrl()!=null)
						{
							CMSImage im = cmsSlide.getImage();
							String updateImageUrl = mediaUrlPath+cmsSlide.getImage().getUrl().replace("/content/media_upload?getfile=", "").replace("/video/", "");
							im.setUrl(updateImageUrl);
							cmsSlide.setImage(im);
						}
											
						allUrls.add(oldMediaPath+cmsSlide.getImage().getFragmentAudioUrl());
						if(cmsSlide.getImage().getFragmentAudioUrl()!=null)
						{
							cmsSlide.getImage().setFragmentAudioUrl(mediaUrlPath+cmsSlide.getImage().getFragmentAudioUrl().replace("/video/", ""));
						}
						
						System.err.println(">>>>>>>>>>>>>>>"+cmsSlide.getImage().getUrl());
						
					}
					else
					{
						System.err.println("cmsSlide.getImage() is null");
					}	
					if(cmsSlide.getVideo()!=null)
					{
						allUrls.add(oldMediaPath+cmsSlide.getVideo().getUrl());
						if(cmsSlide.getVideo().getUrl()!=null)
						{
							cmsSlide.getVideo().setUrl(mediaUrlPath+cmsSlide.getVideo().getUrl().replace("/video/", ""));
						}
						
					}
					
					 }catch(NullPointerException eeee)
					 {
						 eeee.printStackTrace();
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
				
				/* make a folder in lessonXML folder also which will contain lessonxml, and assessts*/
				File lessonFolderInLessonXML = new File(mediaPath + "lessonXMLs/"+lessonId);

				System.out.println(lessonFolderInLessonXML.getAbsolutePath());
				if (!lessonFolderInLessonXML.exists()) {
					System.out.println("Folder does not exists");
					lessonFolderInLessonXML.mkdir();
				}
				
				for(String str :allUrls)
				{
					System.out.println(str);
					if(!str.contains("null") && !str.contains("none") && !str.equalsIgnoreCase(oldMediaPath))
					{
						str = str.replace("/content/media_upload?getfile=", "").replaceAll("/video/", "");
						String fileName = str.substring(str.lastIndexOf("/")); 						
						File src = new File(str);
						File dest = new File(mediaPath + "courseZIPs/"+courseId+"/"+lessonId+"/"+fileName);
						System.err.println(src.getAbsolutePath());
						System.err.println(dest.getAbsolutePath());
						try{
						FileUtils.copyFile(src, dest);	
						}
						catch(FileNotFoundException ee)
						{
							}
						
						
						//same media file will go in lesson folder in lessonXMLs folder
						File fileInLessonXMLFolder = new File(mediaPath + "lessonXMLs/"+lessonId+"/"+fileName);
						try{
						FileUtils.copyFile(src, fileInLessonXMLFolder);		
						}catch (FileNotFoundException e)
						{
							
						}
					}
				}
				JAXBContext jaxbContext11 = JAXBContext.newInstance(CMSLesson.class);
				Marshaller jaxbMarshaller = jaxbContext11.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				
				File destLessonXML = new File(mediaPath + "courseZIPs/"+courseId+"/"+lessonId+"/"+lessonId+".xml");
				System.err.println("|||||||||||||||||||||||"+destLessonXML.getAbsolutePath());
				jaxbMarshaller.marshal(cmsLesson, destLessonXML);
				
				
				//copy updatedlessonXML in lessonXMLs folder
				
				
				
				File destLessonXMLInlessonXMLsFolder = new File(mediaPath + "lessonXMLs/"+lessonId+"/"+lessonId+".xml");
				
				

				// output pretty printed
				

				jaxbMarshaller.marshal(cmsLesson, destLessonXMLInlessonXMLsFolder);
				
				
				//zip the lesson folder
				String SOURCE_FOLDER = mediaPath+"/lessonXMLs/"+lessonId;
				File sourceFile = new File(SOURCE_FOLDER);
				
				String zipName = mediaPath+"/lessonXMLs/"+lessonId+".zip";
				
				ZipFiles zipFiles = new ZipFiles();
		        zipFiles.zipDirectory(sourceFile, zipName);
				
				
				
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
