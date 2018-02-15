package com.viksitpro.core.dao.utils.workflow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.viksitpro.core.dao.entities.PmWorkflow;

public class WorkflowUtility {

	public String createPmWorkflowXML(PmWorkflow workflow) {
		
		//ViksitLogger.logMSG(this.getClass().getName(),"Generating XML");
		String absoluteFileName=null;
		UUID fileName = UUID.randomUUID();	
		JAXBContext contextObj;
		try {			
		contextObj = JAXBContext.newInstance(PmWorkflow.class);
	    Marshaller marshallerObj = contextObj.createMarshaller();  
	    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	      
		Properties properties = new Properties();
		String propertyFileName = "app.properties";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		
			properties.load(inputStream);
			String workflowPath = properties.getProperty("mediaPath") + File.separator + "workflows/";
			
			File uploadFolder = new File(workflowPath);
			
			if(!uploadFolder.exists()){
				uploadFolder.mkdir();
			}
			
			File workflowXMLFile = new File(uploadFolder, fileName+".xml");
			
			marshallerObj.marshal(workflow, new FileOutputStream(workflowXMLFile)); 
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(NullPointerException e){
			//ViksitLogger.logMSG(this.getClass().getName(),"InputStream is null");
		}
		return fileName+".xml";
	}
	
	public String updatePmWorkflowXML(String workflowFileName ,PmWorkflow workflow){
		
		File workflowFile = new File(workflowFileName);
		
		workflowFile.delete();
		
		String newWorkflowFileName = createPmWorkflowXML(workflow);
		
		return newWorkflowFileName;
	}
}
