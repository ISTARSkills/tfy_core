package com.viksitpro.core.cms.oldcontent.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.firebase.database.core.Path;
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
						System.err.println("creating folder for lesson " + l.getId());
						createFolderForLessonInCourse(c.getId(), l.getId());
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

		Set<String> allUrls = new HashSet<String>();
		String mediaPath = getMediaPath();
		String oldMediaPath = getOldMediaPath();
		String mediaUrlPath = getMediaUrl();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CMSLesson.class);
			File file = new File(mediaPath + File.separator + "lessonXMLs" + File.separator + lessonId + ".xml");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			CMSLesson cmsLesson = (CMSLesson) unmarshaller.unmarshal(file);

			mediaUrlPath = mediaUrlPath + File.separator + "lessonXMLs" + File.separator + lessonId + File.separator;
			for (CMSSlide cmsSlide : cmsLesson.getSlides()) {
				try {
					allUrls.add(oldMediaPath + cmsSlide.getImage_BG());
					// remove unnecessary path
					if (cmsSlide.getImage_BG() != null && !cmsSlide.getImage_BG().contains("http") && !cmsSlide.getImage_BG().contains("none") && !cmsSlide.getImage_BG().contains("null")) {
						cmsSlide.setImage_BG(mediaUrlPath + cmsSlide.getImage_BG().replace("/video/backgrounds/", ""));
					}
					allUrls.add(oldMediaPath + cmsSlide.getAudioUrl());
					if (cmsSlide.getAudioUrl() != null && !cmsSlide.getAudioUrl().equalsIgnoreCase("none")) {
						cmsSlide.setAudioUrl(mediaUrlPath + cmsSlide.getAudioUrl().replace("/video/", ""));
					}
					if (cmsSlide.getTitle() != null) {
						allUrls.add(oldMediaPath + cmsSlide.getTitle().getFragmentAudioUrl());
						if (cmsSlide.getTitle().getFragmentAudioUrl() != null) {
							cmsSlide.getTitle().setFragmentAudioUrl(mediaUrlPath + cmsSlide.getTitle().getFragmentAudioUrl().replace("/video/", ""));
						}
					}
					if (cmsSlide.getTitle2() != null) {
						allUrls.add(oldMediaPath + cmsSlide.getTitle2().getFragmentAudioUrl());
						if (cmsSlide.getTitle2().getFragmentAudioUrl() != null) {
							cmsSlide.getTitle2().setFragmentAudioUrl(mediaUrlPath + cmsSlide.getTitle2().getFragmentAudioUrl().replace("/video/", ""));
						}
					}
					if (cmsSlide.getParagraph() != null) {
						allUrls.add(oldMediaPath + cmsSlide.getParagraph().getFragmentAudioUrl());
						if (cmsSlide.getParagraph().getFragmentAudioUrl() != null) {
							cmsSlide.getParagraph().setFragmentAudioUrl(mediaUrlPath + cmsSlide.getParagraph().getFragmentAudioUrl().replace("/video/", ""));
						}

					}
					if (cmsSlide.getList() != null) {
						ArrayList<CMSTextItem> newItems = new ArrayList<>();
						allUrls.add(oldMediaPath + cmsSlide.getList().getMergedAudioURL());
						for (CMSTextItem item : cmsSlide.getList().getItems()) {
							allUrls.add(oldMediaPath + item.getFragmentAudioUrl());
							if (item.getFragmentAudioUrl() != null && !item.getFragmentAudioUrl().equalsIgnoreCase("")) {
								item.setFragmentAudioUrl(mediaUrlPath + item.getFragmentAudioUrl().replace("/video/", ""));

							}
							newItems.add(item);
						}
						cmsSlide.getList().setItems(newItems);

						if (cmsSlide.getList().getMergedAudioURL() != null && !cmsSlide.getList().getMergedAudioURL().equalsIgnoreCase("none") && !cmsSlide.getList().getMergedAudioURL().equalsIgnoreCase("null")) {
							cmsSlide.getList().setMergedAudioURL(mediaUrlPath + cmsSlide.getList().getMergedAudioURL().replace("/video/", ""));
						}

					}
					if (cmsSlide.getImage() != null) {

						allUrls.add(oldMediaPath + cmsSlide.getImage().getUrl());
						System.out.println("uodated image url " + cmsSlide.getImage().getUrl().replace("/content/media_upload?getfile=", "").replace("/video/", ""));
						if (cmsSlide.getImage().getUrl() != null && !cmsSlide.getImage().getUrl().contains("http")) {

							CMSImage im = cmsSlide.getImage();
							String updateImageUrl = mediaUrlPath + cmsSlide.getImage().getUrl().replace("/content/media_upload?getfile=", "").replace("/video/", "");
							im.setUrl(updateImageUrl);
							cmsSlide.setImage(im);
						} else {

						}

						allUrls.add(oldMediaPath + cmsSlide.getImage().getFragmentAudioUrl());
						if (cmsSlide.getImage().getFragmentAudioUrl() != null) {
							cmsSlide.getImage().setFragmentAudioUrl(mediaUrlPath + cmsSlide.getImage().getFragmentAudioUrl().replace("/video/", ""));
						}

						System.err.println(">>>>>>>>>>>>>>>" + cmsSlide.getImage().getUrl());

					} else {
						System.err.println("cmsSlide.getImage() is null");
					}
					if (cmsSlide.getVideo() != null) {
						allUrls.add(oldMediaPath + cmsSlide.getVideo().getUrl());
						if (cmsSlide.getVideo().getUrl() != null) {
							cmsSlide.getVideo().setUrl(mediaUrlPath + cmsSlide.getVideo().getUrl().replace("/video/", ""));
						}

					}

				} catch (NullPointerException eeee) {
					eeee.printStackTrace();
				}

			}

			File courseFolder = new File(mediaPath + "courseZIPs" + File.separator + courseId);

			System.out.println(courseFolder.getAbsolutePath());
			if (!courseFolder.exists()) {
				System.out.println("Folder does not exists");
				courseFolder.mkdir();
			}

			File lessonFolder = new File(mediaPath + "courseZIPs" + File.separator + courseId + File.separator + lessonId);

			System.out.println(lessonFolder.getAbsolutePath());
			if (!lessonFolder.exists()) {
				System.out.println("Folder does not exists");
				lessonFolder.mkdir();
			}

			/*
			 * make a folder in lessonXML folder also which will contain
			 * lessonxml, and assessts
			 */
			File lessonFolderInLessonXML = new File(mediaPath + "lessonXMLs" + File.separator + lessonId);

			System.out.println(lessonFolderInLessonXML.getAbsolutePath());
			if (!lessonFolderInLessonXML.exists()) {
				System.out.println("Folder does not exists");
				lessonFolderInLessonXML.mkdir();
			}

			for (String str : allUrls) {
				System.out.println("iterating strsss" + str);
				if (str != null && !str.contains("null") && !str.contains("none") && !str.equalsIgnoreCase(oldMediaPath)) {
					str = str.replace("/content/media_upload?getfile=", "").replaceAll("/video/", "");
					String fileName = str.replace("backgrounds/", "");
					fileName = fileName.replace("C:/Users/mayank/Pictures/Camera Roll/", "");
					File src = new File(str);

					File dest = new File(mediaPath + "courseZIPs" + File.separator + courseId + File.separator + lessonId + File.separator + fileName);
					System.err.println(src.getAbsolutePath());
					System.err.println(dest.getAbsolutePath());
					try {
						FileUtils.copyFile(src, dest);
					} catch (FileNotFoundException ee) {
					}

					// same media file will go in lesson folder in lessonXMLs
					// folder
					File fileInLessonXMLFolder = new File(mediaPath + "lessonXMLs" + File.separator + lessonId + File.separator + fileName);
					try {
						FileUtils.copyFile(src, fileInLessonXMLFolder);
					} catch (FileNotFoundException e) {

					}
				}

			}
			JAXBContext jaxbContext11 = JAXBContext.newInstance(CMSLesson.class);
			Marshaller jaxbMarshaller = jaxbContext11.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			File destLessonXML = new File(mediaPath + "courseZIPs" + File.separator + courseId + File.separator + lessonId + File.separator + lessonId + ".xml");
			System.err.println("|||||||||||||||||||||||" + destLessonXML.getAbsolutePath());
			jaxbMarshaller.marshal(cmsLesson, destLessonXML);

			// copy updatedlessonXML in lessonXMLs folder

			File destLessonXMLInlessonXMLsFolder = new File(mediaPath + "lessonXMLs" + File.separator + lessonId + File.separator + lessonId + ".xml");

			// output pretty printed

			jaxbMarshaller.marshal(cmsLesson, destLessonXMLInlessonXMLsFolder);

			// zip the lesson folder
			String SOURCE_FOLDER = mediaPath + File.separator + "lessonXMLs" + File.separator + lessonId;
			File sourceFile = new File(SOURCE_FOLDER);

			String zipName = mediaPath + File.separator + "lessonXMLs" + File.separator + lessonId + ".zip";

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
		oc.createZipForCourse(1);
		oc.unzip("C:" + File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "courseZIPs" + File.separator + "1.zip", "C:\\Users\\mayank\\Documents\\data\\");
	}

	public void unzip(String zipFile, String location) {
		try {
			FileInputStream fin = new FileInputStream(zipFile);
			ZipInputStream zin = new ZipInputStream(fin);
			ZipEntry ze = null;
			while ((ze = zin.getNextEntry()) != null) {
				System.out.println("Unzipping " + ze.getName() + " location  " + location);

                if (ze.isDirectory()) {
                    dirChecker(ze.getName(),location);
                } else {
				FileOutputStream fout = new FileOutputStream(location + ze.getName().replaceAll(".jpg", ".aaa").replaceAll(".png", ".aaa").replaceAll(".jpeg", ".aaa").replaceAll(".mp4", ".aaa"));

				byte[] buffer = new byte[8192];
				int len;
				while ((len = zin.read(buffer)) != -1) {
					fout.write(buffer, 0, len);
				}
				fout.close();

				zin.closeEntry();

			}}
			zin.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("unzip" + e);
		}

	}

	private void dirChecker(String dir, String location) {
	
		
		try {
			File f = new File(location + dir);
			
			System.err.println("----------------->" + f.getAbsolutePath());
			if (!f.isDirectory()) {
				f.mkdirs();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
