/**
 * 
 */
package com.viksitpro.core.customtask;

import java.net.URL;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import com.viksitpro.core.dao.entities.Task;
import com.viksitpro.core.dao.entities.TaskDAO;

/**
 * @author istar
 *
 */
@XmlRootElement
public class TaskLibrary {
	HashMap<Integer, TaskTemplate> templates = new HashMap<>();

	public TaskLibrary() {
		super();
	}

	public HashMap<Integer, TaskTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(HashMap<Integer, TaskTemplate> templates) {
		this.templates = templates;
	}
	
	public Task getTaskTemplate(int taskID) {
		Task t = new TaskDAO().findById(taskID);		
		TaskTemplate tp =  getTaskFromLibrary(t);		
		t.taskTemplate =  tp;
		return t;
		
	}

	public TaskTemplate getTaskFromLibrary(Task t) {
		try {

			URL url = getClass().getClassLoader().getResource("task_libraray.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(TaskLibrary.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TaskLibrary library = (TaskLibrary) jaxbUnmarshaller.unmarshal(url);
			/*for(int i :library.getTemplates().keySet())
			{
				//ViksitLogger.logMSG(this.getClass().getName(),library.getTemplates().get(i).getId());
			}
			*/
			return library.getTemplates().get(t.getItemId());
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return null;
	}
	
	public void setTaskFromLibrary(Task t) {
		try {

			URL url = getClass().getClassLoader().getResource("/task_libraray.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(TaskLibrary.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TaskLibrary library = (TaskLibrary) jaxbUnmarshaller.unmarshal(url);
			//ViksitLogger.logMSG(this.getClass().getName(),library);
			
			
			
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		
	}
	
}
