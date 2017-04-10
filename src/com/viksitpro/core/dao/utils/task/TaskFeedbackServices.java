package com.viksitpro.core.dao.utils.task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Task;
import com.viksitpro.core.dao.entities.TaskFeedback;
import com.viksitpro.core.dao.entities.TaskFeedbackDAO;
import com.viksitpro.core.dao.utils.user.IstarUserServices;

public class TaskFeedbackServices {

	/**
	 * This method create TaskFeedback and returns TaskFeedback Object corresponding to Input parameter status  feedback
	 *   taskId  taskId  istarUserId
	 * 
	 * @param status  String value of Status
	 * @param feedback  String value of FeedBack
	 * @param taskId  Integer value of Task Id
	 * @param istarUserId  Integer value of istarUser Id
	 * @return TaskFeedback Object
	 */
	public TaskFeedback createTaskFeedback(String status,String stage, String feedback, Integer taskId, Integer istarUserId, Integer rating){
		
		TaskServices taskServices = new TaskServices();
		Task task = taskServices.getTask(taskId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		TaskFeedback taskFeedback = createTaskFeedback(status, stage, feedback, task, istarUser, rating);
		
		return taskFeedback;
	}
	
	public TaskFeedback createTaskFeedback(String feedback, Integer taskId, Integer istarUserId, Integer rating){
		
		TaskServices taskServices = new TaskServices();
		Task task = taskServices.getTask(taskId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		TaskFeedback taskFeedback = createTaskFeedback(task.getState(), task.getState(), feedback, task, istarUser, rating);
		
		return taskFeedback;
	}
	
	/**
	 * This method create TaskFeedback and returns TaskFeedback Object corresponding to Input parameter status  feedback
	 *   taskId  taskId  istarUserId
	 *   
	 * @param status  String value of Status
	 * @param feedback  String value of FeedBack
	 * @param task  Integer value of Task Id
	 * @param istarUser  IstarUser instance of istarUser
	 * @return TaskFeedback Object
	 */
	public TaskFeedback createTaskFeedback(String status, String stage, String feedback, Task task, IstarUser istarUser, Integer rating){
		
		TaskFeedback taskFeedback = new TaskFeedback();
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		taskFeedback.setStatus(status);
		taskFeedback.setFeedback(feedback);
		taskFeedback.setStage(stage);
		taskFeedback.setTask(task);
		taskFeedback.setIstarUser(istarUser);
		taskFeedback.setRating(rating);
		taskFeedback.setCreatedAt(current);
		taskFeedback.setUpdatedAt(current);

		
		taskFeedback = saveTaskFeedbackToDAO(taskFeedback);
		
		return taskFeedback;
	}
	
	/**
	 * This method returns TaskFeedback Object corresponding to Input parameter taskFeedbackId
	 * 
	 * @param taskFeedbackId Integer value of TaskFeedback Id
	 * @return  TaskFeedback Object
	 */
	public TaskFeedback getTaskFeedback(int taskFeedbackId) {
		TaskFeedbackDAO taskFeedbackDAO = new TaskFeedbackDAO();
		TaskFeedback taskFeedback = taskFeedbackDAO.findById(taskFeedbackId);

		return taskFeedback;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskFeedback> getAllTaskFeedback(int taskId){
		
		String sql = "from TaskFeedback taskFeedback where task= :taskId";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		
		Query query = session.createQuery(sql);
		query.setParameter("taskId",taskId);
		
		List<TaskFeedback> allTaskFeedback = new ArrayList<TaskFeedback>();
		try{
			allTaskFeedback = query.list();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		return allTaskFeedback;
	}

	public TaskFeedback saveTaskFeedbackToDAO(TaskFeedback taskFeedback) {

		TaskFeedbackDAO taskFeedbackDAO = new TaskFeedbackDAO();

		Session taskFeedbackSession = taskFeedbackDAO.getSession();
		Transaction taskFeedbackTransaction = null;
		try {
			taskFeedbackTransaction = taskFeedbackSession.beginTransaction();
			taskFeedbackSession.save(taskFeedback);
			taskFeedbackTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskFeedbackTransaction != null)
				taskFeedbackTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskFeedbackSession.close();
		}
		return taskFeedback;
	}

	public TaskFeedback updateTaskFeedbackToDAO(TaskFeedback taskFeedback) {

		TaskFeedbackDAO taskFeedbackDAO = new TaskFeedbackDAO();

		Session taskFeedbackSession = taskFeedbackDAO.getSession();
		Transaction taskFeedbackTransaction = null;
		try {
			taskFeedbackTransaction = taskFeedbackSession.beginTransaction();
			taskFeedbackSession.update(taskFeedback);
			taskFeedbackTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskFeedbackTransaction != null)
				taskFeedbackTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskFeedbackSession.close();
		}
		return taskFeedback;
	}
}
