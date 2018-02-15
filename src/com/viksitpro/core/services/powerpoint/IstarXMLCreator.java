/**
 * 
 */
package com.viksitpro.core.services.powerpoint;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.viksitpro.core.cms.oldcontent.CMSLesson;
import com.viksitpro.core.cms.oldcontent.CMSSlide;
import com.viksitpro.core.cms.oldcontent.services.ZipFiles;
import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.LessonDAO;
import com.viksitpro.core.exceptions.EntityNotFoundException;
import com.viksitpro.core.logger.ViksitLogger;
import com.viksitpro.core.utilities.AppProperies;
import com.viksitpro.core.utilities.DBUTILS;



/**
 * @author Mayank
 *
 */
public class IstarXMLCreator implements Callable {

	private ArrayList<Integer> slideIds;
	private Integer lessonId;

	public IstarXMLCreator(ArrayList<Integer> slideIds, Integer lessonId) {
		this.slideIds = slideIds;
		this.lessonId = lessonId;
	}

	@Override
	public Object call() throws EntityNotFoundException {
		Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
        //add owners permission
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        //add group permissions
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        //add others permissions
        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_WRITE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);
		String apiKey = null;
		String apachePath = null;
		String mediaUrlPath = null;
		apiKey = AppProperies.getProperty("zamzar_key");
		if (apiKey == null) {
			throw new EntityNotFoundException("zamzar_key not defined in app.properties");
		}
		apachePath = AppProperies.getProperty("apache_path");
		if (apachePath == null) {
			throw new EntityNotFoundException("apache_path not defined in app.properties");
		}
		mediaUrlPath = AppProperies.getProperty("media_url_path");
		if (mediaUrlPath == null) {
			throw new EntityNotFoundException("old_media_path not defined in app.properties");
		}

		if (lessonId != null) {
			Lesson l = new LessonDAO().findById(lessonId);
			String directoryName= apachePath + "/lessonXMLs/" + lessonId;
			File directory = new File(directoryName);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
		    else
		    {
		    	try {
					delete(directory);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
		    	directory.mkdir();
		    }	
		    directoryName = apachePath + "/lessonXMLs/" + lessonId+"/"+lessonId;
		    directory = new File(directoryName);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
		    else
		    {
		    	directory.delete();
		    	directory.mkdir();
		    }
			
			
			if (l != null) {
				CMSLesson lesson = new CMSLesson();
				lesson.setLessonDescription(l.getDescription());
				lesson.setLessonTitle(l.getTitle());
				lesson.setType("PRESENTATION");
				ArrayList<CMSSlide> slides = new ArrayList<>();
				int slideCounter =0;
				if (slideIds != null) {
					for (int i : slideIds) {
						String imageName = UUID.randomUUID().toString() + System.currentTimeMillis();
						imageName = imageName + "-" + i;
						String endpoint = "https://sandbox.zamzar.com/v1/files/" + i + "/content";
						String desktopFilename = apachePath + "/lessonXMLs/" + lessonId + "/" + lessonId + "/" + imageName+"_desktop.png";
						String localFilename = apachePath + "/lessonXMLs/" + lessonId + "/" + lessonId + "/" + imageName+".png";
						CMSSlide slide = new CMSSlide();

						slide.setId(((lessonId*1000)+i));
						slide.setImage_BG(
								mediaUrlPath + "/lessonXMLs/" + lessonId + "/" + lessonId + "/" + imageName + ".png");
						slide.setOrder_id(i);
						slide.setTemplateName("NO_CONTENT");
						slides.add(slide);
						CloseableHttpClient httpClient = getHttpClient(apiKey);
						HttpGet request = new HttpGet(endpoint);
						try {
							CloseableHttpResponse response = httpClient.execute(request);
							HttpEntity responseContent = response.getEntity();
							BufferedInputStream bis = new BufferedInputStream(responseContent.getContent());
							BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(localFilename));
							BufferedOutputStream bosDesktop = new BufferedOutputStream(new FileOutputStream(desktopFilename));
							int inByte;
							while ((inByte = bis.read()) != -1) {
								bos.write(inByte);
								bosDesktop.write(inByte);
							}
							
							
							ViksitLogger.logMSG(this.getClass().getName(),"File downloaded");
							
							
							response.close();
							httpClient.close();
							bos.close();
							bosDesktop.close();
							bis.close();
							
						} catch (UnsupportedOperationException | IOException e) {
							e.printStackTrace();
						}
						if(slideCounter==0)
						{
							DBUTILS util = new DBUTILS();
							String sql = "update lesson set image_url ='"+ "/lessonXMLs/" + lessonId + "/" + lessonId + "/" + imageName + ".png"+"' where id ="+lessonId;
							util.executeUpdate(sql);
							slideCounter++;
						}	
						
					}
					
					ImageOptimizer optimizer = new ImageOptimizer(apachePath + "/lessonXMLs/" + lessonId + "/" + lessonId);
					optimizer.optimize();					
				}
				
				lesson.setSlides(slides);				
				try {
					File file = new File(directory.getAbsolutePath()+"/"+lessonId+".xml");
					if(file.createNewFile())
					{
						JAXBContext jaxbContext = JAXBContext.newInstance(CMSLesson.class);
						Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
						jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						jaxbMarshaller.marshal(lesson, file);
					}
					
					
					
				} catch (JAXBException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 
			        
			        
				String SOURCE_FOLDER =apachePath + "/lessonXMLs/" + lessonId;
				File sourceFile = new File(SOURCE_FOLDER);

				String zipName =SOURCE_FOLDER+ ".zip";
				ViksitLogger.logMSG(this.getClass().getName(),"creating "+zipName);
				ZipFiles zipFiles = new ZipFiles();
				zipFiles.zipDirectory(sourceFile, zipName);
				
				 /*Runtime rt = Runtime.getRuntime();
				    Process proc;
				    int exitVal = -1;
				    try {
				        proc =  rt.exec("chmod -R 777 /var/www/html/*");
				        
				        exitVal = proc.waitFor();
				        InputStream error = proc.getErrorStream();
				         for (int i = 0; i < error.available(); i++) {
				            ViksitLogger.logMSG(this.getClass().getName(),"" + error.read());
				         }
				        
				         // wait for 10 seconds and then destroy the process
				         Thread.sleep(10000);
				         proc.destroy();
				    } catch (Exception e) {
				    	e.printStackTrace();
				    }*/
				
				if(AppProperies.getProperty("server_type").equalsIgnoreCase("linux"))
		        {	
			        try {
			        	Files.setPosixFilePermissions(Paths.get(apachePath + "/lessonXMLs/" + lessonId), perms);
			        	Files.setPosixFilePermissions(Paths.get(apachePath + "/lessonXMLs/" + lessonId+"/"+lessonId), perms);
			        	File[] directoryListing = directory.listFiles();
			        	if(directoryListing!=null && directoryListing.length>0)
			        	{
			        		 for(File f : directoryListing)			 
							 {
			        			 Files.setPosixFilePermissions(Paths.get(f.getAbsolutePath()), perms); 
							 }
			        	}	
			        	
						Files.setPosixFilePermissions(Paths.get(zipName), perms);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
		        }
				
			}
		}
		else
		{
			ViksitLogger.logMSG(this.getClass().getName(),"lesso  id is null");
		}	

		return null;
	}

	private static CloseableHttpClient getHttpClient(String apiKey) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(apiKey, ""));

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider)
				.build();

		return httpClient;
	}
	
	 public void delete(File file)
		    	throws IOException{

		    	if(file.isDirectory()){

		    		//directory is empty, then delete it
		    		if(file.list().length==0){

		    		   file.delete();
		    		 //  ViksitLogger.logMSG(this.getClass().getName(),"Directory is deleted : "
		                                                

		    		}else{

		    		   //list all the directory contents
		        	   String files[] = file.list();

		        	   for (String temp : files) {
		        	      //construct the file structure
		        	      File fileDelete = new File(file, temp);

		        	      //recursive delete
		        	     delete(fileDelete);
		        	   }

		        	   //check the directory again, if empty then delete it
		        	   if(file.list().length==0){
		           	     file.delete();
		        	    
		        	   }
		    		}

		    	}else{
		    		//if file, then delete it
		    		file.delete();
		    		
		    	}
		    }
}
