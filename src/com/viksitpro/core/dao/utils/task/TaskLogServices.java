package com.viksitpro.core.dao.utils.task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Task;
import com.viksitpro.core.dao.entities.TaskLog;
import com.viksitpro.core.dao.entities.TaskLogDAO;
import com.viksitpro.core.dao.utils.user.IstarUserServices;


@SuppressWarnings("unused")
public class TaskLogServices {	
	
	public TaskLogServices(){}
	
	public static int getPercentageLeft(Timestamp start, Timestamp end) {
		if(start!=null && end!=null)
		{
			long now = System.currentTimeMillis();
	        long s = start.getTime();
	        long e = end.getTime();
	        if (s >= e || now >= e) {
	            return 0;
	        }
	        if (now <= s) {
	            return 100;
	        }
	        return (int) ((e - now) * 100 / (e - s));
		}
		else
		{
			return 0;
		}	
        
    }
   
	public List<Task> getTasks(Integer istarUserId)
	{
		 List<Task> uniqueTasks = new ArrayList<Task>();
		 IstarUserServices userService = new IstarUserServices();
    	 IstarUser user = userService.getIstarUser(istarUserId);
		 Set<Task> logs = new HashSet<Task>();
		 List<Task> userTasksAsActor = new ArrayList<Task>(user.getTasksForActor());
    	 List<Task> userTasksAsOwner = new ArrayList<Task>(user.getTasksForOwner());
    	 List<Task> userTasksAsAssigneeMember = new ArrayList<Task>(user.getTasksForAssigneeMember());
    	  		     		
    		 logs.addAll(userTasksAsActor);
    	 
    		 logs.addAll(userTasksAsOwner);
    	 
    		 logs.addAll(userTasksAsAssigneeMember);
    		 List<Task> t= (List<Task>)new BaseHibernateDAO().GetOrderedListFromSet(logs, "endDate");
    		 if(t!=null)
        	 {
    			 uniqueTasks= t;
        	 }
    		 
    		 return uniqueTasks;    		 
	}
	
	
    public  List<TaskLog> getTaskLogs(Integer istarUserId)
    {
    	 List<TaskLog> uniqueLogs = new ArrayList<TaskLog>();
    	 Set<TaskLog> logs = new HashSet<TaskLog>();
    	 IstarUserServices userService = new IstarUserServices();
    	 IstarUser user = userService.getIstarUser(istarUserId);
    	 List<Task> userTasksAsActor = new ArrayList<Task>(user.getTasksForActor());
    	 List<Task> userTasksAsOwner = new ArrayList<Task>(user.getTasksForOwner());
    	 List<Task> userTasksAsAssigneeMember = new ArrayList<Task>(user.getTasksForAssigneeMember());
    	 for(Task task : userTasksAsActor)
    	 { 		     		
    		 logs.addAll(task.getTaskLogs());
    	 }
    	 
    	 for(Task task : userTasksAsOwner)
    	 {
    		 logs.addAll(task.getTaskLogs());
    	 }
    	 
    	 for(Task task : userTasksAsAssigneeMember)
    	 {
    		 logs.addAll(task.getTaskLogs());
    	 }
    	 if(new BaseHibernateDAO().GetOrderedListFromSet(logs, "id")!=null)
    	 {
    		 uniqueLogs= (List<TaskLog>)new BaseHibernateDAO().GetOrderedListFromSet(logs, "id");
    	 }
    	
    	
    	 //ViksitLogger.logMSG(this.getClass().getName(),"logs size---"+uniqueLogs.size());
    	 return uniqueLogs;
    }
    
    /**
     * This method create TaskLog and returns TaskLog Object corresponding to Input parameter title  body  status  entityType
     *   jsessionId  istarUserId  taskId
     * 
     * @param title  String value of TaskLog title
     * @param body   String value of TaskLog body
     * @param status String value of TaskLog status
     * @param entityType String value of TaskLog entityType
     * @param jsessionId String value of TaskLog jsessionId
     * @param istarUserId Integer value of TaskLog istarUserId
     * @param taskId  Integer value of TaskLog Id
     * @return  TaskLog Object
     */
	public  TaskLog createTaskLog(String title, String body, String status, String entityType, String jsessionId, int istarUserId, int taskId){
		
		TaskServices taskServices = new TaskServices();
		Task task = taskServices.getTask(taskId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		TaskLog taskLog = new TaskLog();
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		taskLog.setTitle(title);
		taskLog.setBody(body);
		taskLog.setStatus(status);
		taskLog.setEntityType(entityType);
		taskLog.setJsessionId(jsessionId);
		taskLog.setIstarUser(istarUser);
		taskLog.setTask(task);
		taskLog.setCreatedAt(current);
		taskLog.setUpdatedAt(current);		
		
		taskLog = saveTaskLogToDAO(taskLog);

		return taskLog;
	}
		
	/**
	 * This method generate TaskLogs entering DataBase by calling createTaskLog method 
	 * Input parameters
	 * @param task  Task instarnce  of TaskLog task
	 * @param logMessageFormat String value of TaskLog logMessageFormat
	 * @param jsessionId String value of TaskLog jsessionId
	 * @return  nothing
	 */
	public  void generateTaskLog(Task task, String logMessageFormat, String jsessionId){
		
			String taskLogTitle = task.getName() + ": " + logMessageFormat;
			String taskLogBody = taskLogTitle;
			createTaskLog(taskLogTitle, taskLogBody, task.getState(), task.getItemType(), jsessionId, 
					task.getIstarUserByActor().getId(), task.getId());			
	}
	
	/**
	 * This method returns TaskLog Object corresponding to Input parameter taskLogId
	 * 
	 * @param taskLogId Integer value of TaskLog Id
	 * @return TaskLog Object
	 */
	public  TaskLog getTaskLog(int taskLogId){
		TaskLogDAO taskLogDAO = new TaskLogDAO();
		TaskLog taskLog = taskLogDAO.findById(taskLogId);
		
		return taskLog;
	}
	
	public  TaskLog saveTaskLogToDAO(TaskLog taskLog) {

		TaskLogDAO taskLogDAO = new TaskLogDAO();

		Session taskLogSession = taskLogDAO.getSession();
		Transaction taskLogTransaction = null;
		try {
			taskLogTransaction = taskLogSession.beginTransaction();
			taskLogSession.save(taskLog);
			taskLogTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskLogTransaction != null)
				taskLogTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskLogSession.close();
		}
		return taskLog;
	}
}
