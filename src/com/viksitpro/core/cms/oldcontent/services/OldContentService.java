package com.viksitpro.core.cms.oldcontent.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;

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

	
	
	public void createZipForCourse(int courseId) {
		
		String mediaPath = getMediaPath();
		Course c = new CourseDAO().findById(courseId);
		for (Module m : c.getModules()) {
			for (Cmsession cms : m.getCmsessions()) {
				for (Lesson l : cms.getLessons()) {
					if (l.getCategory().equalsIgnoreCase("ILT") || l.getCategory().equalsIgnoreCase("BOTH")) {
						//System.err.println("creating folder for lesson " + l.getId());
						try {
							createFolderForLessonInCourse(c.getId(), l.getId());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
						}
					}
				}
			}
		}
		String SOURCE_FOLDER = mediaPath + "/courseZIPs/" + c.getId();
		File sourceFile = new File(SOURCE_FOLDER);

		String zipName = mediaPath + "/courseZIPs/" + c.getId() + ".zip";

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

	public void createFolderForLessonInCourse(int courseId, int lessonId) {

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
		
		
		
		
		Set<String> allUrls = new HashSet<String>();
		String mediaPath = getMediaPath();
		String oldMediaPath = getOldMediaPath();
		String mediaUrlPath = getMediaUrl();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CMSLesson.class);
			File file = new File(mediaPath + "/lessonXMLs/" + lessonId + ".xml");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			CMSLesson cmsLesson = (CMSLesson) unmarshaller.unmarshal(file);

			mediaUrlPath = mediaUrlPath + "/lessonXMLs/" + lessonId + "/"+lessonId+"/";
			for (CMSSlide cmsSlide : cmsLesson.getSlides()) {
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
						for (CMSTextItem item : cmsSlide.getList().getItems()) {
							allUrls.add(oldMediaPath + item.getFragmentAudioUrl());
							if (item.getFragmentAudioUrl() != null
									&& !item.getFragmentAudioUrl().equalsIgnoreCase("")) {
								item.setFragmentAudioUrl(
										mediaUrlPath + item.getFragmentAudioUrl().replace("/video/", ""));

							}
							newItems.add(item);
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
						//System.out.println("uodated image url " + cmsSlide.getImage().getUrl()
								//.replace("/content/media_upload?getfile=", "").replace("/video/", ""));
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

						//System.err.println(">>>>>>>>>>>>>>>" + cmsSlide.getImage().getUrl());

					} else {
						//System.err.println("cmsSlide.getImage() is null");
					}
					if (cmsSlide.getVideo() != null) {
						allUrls.add(oldMediaPath + cmsSlide.getVideo().getUrl());
						if (cmsSlide.getVideo().getUrl() != null) {
							//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>video"+oldMediaPath + cmsSlide.getVideo().getUrl());
							cmsSlide.getVideo()
									.setUrl(mediaUrlPath + cmsSlide.getVideo().getUrl().replace("/video/", ""));
						}

					}

				} catch (NullPointerException eeee) {
					eeee.printStackTrace();
				}

			}

			File courseFolder = new File(mediaPath + "courseZIPs/" + courseId);
			
			//System.out.println(courseFolder.getAbsolutePath());
			if (!courseFolder.exists()) {
				//System.out.println("Folder does not exists");
				courseFolder.mkdir();
				//Files.setPosixFilePermissions(Paths.get(courseFolder.getAbsolutePath()), perms);
			}

			File lessonFolder = new File(mediaPath + "courseZIPs/" + courseId + "/" + lessonId);
			
			//System.out.println(lessonFolder.getAbsolutePath());
			if (!lessonFolder.exists()) {
				//System.out.println("Folder does not exists");
				lessonFolder.mkdir();
				//Files.setPosixFilePermissions(Paths.get(lessonFolder.getAbsolutePath()), perms);
			}
			
			File coursefolderInlessonFolder = new File(mediaPath + "courseZIPs/" + courseId + "/" + lessonId+"/"+courseId);
			
			//System.out.println(coursefolderInlessonFolder.getAbsolutePath());
			if (!coursefolderInlessonFolder.exists()) {
				//System.out.println("Folder does not exists");
				coursefolderInlessonFolder.mkdir();
				//Files.setPosixFilePermissions(Paths.get(lessonFolder.getAbsolutePath()), perms);
			}
			
			
			File finalLessonFolderolderInlessonFolder = new File(mediaPath + "courseZIPs/" + courseId + "/" + lessonId+"/"+courseId+"/"+lessonId);
			
			//System.out.println(finalLessonFolderolderInlessonFolder.getAbsolutePath());
			if (!finalLessonFolderolderInlessonFolder.exists()) {
				//System.out.println("Folder does not exists");
				finalLessonFolderolderInlessonFolder.mkdir();
				//Files.setPosixFilePermissions(Paths.get(lessonFolder.getAbsolutePath()), perms);
			}
			
			/*
			 * make a folder in lessonXML folder also which will contain
			 * lessonxml, and assessts
			 */
			File lessonFolderInLessonXML = new File(mediaPath + "lessonXMLs/" + lessonId+"/"+lessonId+"/");

			//System.out.println(lessonFolderInLessonXML.getAbsolutePath());
			if (!lessonFolderInLessonXML.exists()) {
				//System.out.println("Folder does not exists");
				lessonFolderInLessonXML.mkdir();
				//Files.setPosixFilePermissions(Paths.get(lessonFolderInLessonXML.getAbsolutePath()), perms);
			}

			for (String str : allUrls) {
				//System.out.println("iterating strsss!!!!!!!!!!!!" + str);
				if (str != null && !str.contains("null") && !str.contains("none")
						&& !str.equalsIgnoreCase(oldMediaPath)) {
					str = str.replace("/content/media_upload?getfile=", "").replaceAll("/video/", "");
					String fileName = str.replace("backgrounds/", "");
					fileName = fileName.replace(getOldMediaPath(), "");
					File src = new File(str);
					//System.err.println("src file name ->"+ src.getAbsolutePath());
					File dest = new File(mediaPath + "courseZIPs/" + courseId + "/" + lessonId+"/"+courseId+"/"+lessonId+"/"+ fileName);

					try {
						FileUtils.copyFile(src, dest);
						//Files.setPosixFilePermissions(Paths.get(dest.getAbsolutePath()), perms);
					} catch (FileNotFoundException ee) {
					}

					// same media file will go in lesson folder in lessonXMLs
					// folder
					File fileInLessonXMLFolder = new File(mediaPath + "lessonXMLs/" + lessonId + "/" +lessonId+"/"+fileName);
					try {
						FileUtils.copyFile(src, fileInLessonXMLFolder);
						//Files.setPosixFilePermissions(Paths.get(fileInLessonXMLFolder.getAbsolutePath()), perms);
					} catch (FileNotFoundException e) {

					}
				}

			}
			JAXBContext jaxbContext11 = JAXBContext.newInstance(CMSLesson.class);
			Marshaller jaxbMarshaller = jaxbContext11.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			File destLessonXML = new File(
					mediaPath + "courseZIPs/" + courseId + "/" + lessonId+"/"+courseId+"/"+lessonId + "/" + lessonId + ".xml");
			//System.err.println("|||||||||||||||||||||||" + destLessonXML.getAbsolutePath());
			jaxbMarshaller.marshal(cmsLesson, destLessonXML);

			// copy updatedlessonXML in lessonXMLs folder

			File destLessonXMLInlessonXMLsFolder = new File(
					mediaPath + "lessonXMLs/" + lessonId + "/" + lessonId+"/"+lessonId + ".xml");

			// output pretty printed

			jaxbMarshaller.marshal(cmsLesson, destLessonXMLInlessonXMLsFolder);

			// zip the lesson folder
			String SOURCE_FOLDER = mediaPath + "/lessonXMLs/" + lessonId;
			File sourceFile = new File(SOURCE_FOLDER);

			String zipName = mediaPath + "/lessonXMLs/" + lessonId + ".zip";

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

	public static void main(String[] args) {
		OldContentService oc = new OldContentService();
		for(Course c : (List<Course>) new CourseDAO().findAll())
		{
			oc.createZipForCourse(c.getId());
		}
		
	}
	
}
