package com.viksitpro.core.dao.utils.task;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.TaskType;
import com.viksitpro.core.dao.entities.TaskTypeDAO;
import com.viksitpro.core.dao.utils.user.IstarUserServices;

public class TaskTypeServices {

	/**
	 * This method create TaskType and returns TaskType Object corresponding to Input parameter name  category  workflowFile  istarUserId
	 * 
	 * @param name  String value of TaskType name
	 * @param category  String value of TaskType category
	 * @param workflowFile  String value of TaskType workflowFile
	 * @param istarUserId   String value of TaskType istarUserId
	 * @return TaskType Object
	 */
	public TaskType createTaskType(String name, String category, String workflowFile, int istarUserId){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		TaskType taskType = new TaskType();
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		taskType.setName(name);
		taskType.setCategory(category);
		taskType.setWorkflowFile(workflowFile);
		taskType.setIstarUser(istarUser);
		taskType.setCreatedAt(current);
		taskType.setUpdatedAt(current);
		
		taskType = saveTaskTypeToDAO(taskType);
		
		return taskType;
	}
	
	/**
	 * This method updates TaskType and returns TaskType Object corresponding to Input parameter name  category  workflowFile  istarUserId
	 * 
	 * @param name  String value of TaskType name
	 * @param category  String value of TaskType category
	 * @param workflowFile  String value of TaskType workflowFile
	 * @param istarUserId   String value of TaskType istarUserId
	 * @return TaskType Object
	 */
	public TaskType updateTaskType(int taskTypeId, String name, String category, String workflowFile, int istarUserId){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		TaskType taskType = getTaskType(taskTypeId);
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		taskType.setName(name);
		taskType.setCategory(category);
		taskType.setWorkflowFile(workflowFile);
		taskType.setIstarUser(istarUser);
		taskType.setUpdatedAt(current);
		
		taskType = updateTaskTypeToDAO(taskType);
		
		return taskType;
	}
	
	/**
	 * This method returns TaskType Object corresponding to Input parameter taskTypeId
	 * 
	 * @param taskTypeId Integer value of TaskType taskTypeId
	 * @return TaskType Object
	 */
	public TaskType getTaskType(Integer taskTypeId){
		TaskTypeDAO taskTypeDAO = new TaskTypeDAO();
		TaskType taskType;
		try{
		 taskType= taskTypeDAO.findById(taskTypeId);
		}catch(IllegalArgumentException e){
			taskType = null;
		}
		return taskType;
	}
	
	public void deleteTaskType(Integer taskTypeId){
		
		TaskType taskType = getTaskType(taskTypeId);
		deleteTaskTypeFromDAO(taskType);
	}
	
	public TaskType saveTaskTypeToDAO(TaskType taskType) {

		TaskTypeDAO taskTypeDAO = new TaskTypeDAO();

		Session taskTypeSession = taskTypeDAO.getSession();
		Transaction taskTypeTransaction = null;
		try {
			taskTypeTransaction = taskTypeSession.beginTransaction();
			taskTypeSession.save(taskType);
			taskTypeTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskTypeTransaction != null)
				taskTypeTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskTypeSession.close();
		}
		return taskType;
	}

	public TaskType updateTaskTypeToDAO(TaskType taskType) {

		TaskTypeDAO taskTypeDAO = new TaskTypeDAO();

		Session taskTypeSession = taskTypeDAO.getSession();
		Transaction taskTypeTransaction = null;
		try {
			taskTypeTransaction = taskTypeSession.beginTransaction();
			taskTypeSession.update(taskType);
			taskTypeTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskTypeTransaction != null)
				taskTypeTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskTypeSession.close();
		}
		return taskType;
	}
	
	public TaskType deleteTaskTypeFromDAO(TaskType taskType) {

		TaskTypeDAO taskTypeDAO = new TaskTypeDAO();

		Session taskTypeSession = taskTypeDAO.getSession();
		Transaction taskTypeTransaction = null;
		try {
			taskTypeTransaction = taskTypeSession.beginTransaction();
			taskTypeSession.delete(taskType);
			taskTypeTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskTypeTransaction != null)
				taskTypeTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskTypeSession.close();
		}
		return taskType;
	}
}


