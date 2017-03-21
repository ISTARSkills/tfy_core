package com.viksitpro.core.dao.utils.workflow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.StageLog;
import com.viksitpro.core.dao.entities.StageLogDAO;
import com.viksitpro.core.dao.entities.Task;
import com.viksitpro.core.dao.utils.task.TaskServices;
import com.viksitpro.core.dao.utils.user.IstarUserServices;

public class StageLogServices {

	public StageLog createStageLog(Task task, IstarUser istarUser, String stageName, String stageType, String status, String result, 
			String action, String url0, String url1, String actionPassword, Integer actionId, String urlCode){
		
		StageLog stageLog = new StageLog();
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		stageLog.setTask(task);
		stageLog.setIstarUser(istarUser);
		stageLog.setStageName(stageName);
		stageLog.setStageType(stageType);
		stageLog.setStatus(status);
		stageLog.setResult(result);
		stageLog.setAction(action);
		stageLog.setUrl0(url0);
		stageLog.setUrl1(url1);
		stageLog.setActionPassword(actionPassword);
		stageLog.setActionId(actionId);
		stageLog.setCreatedAt(current);
		stageLog.setUpdatedAt(current);
		stageLog.setUrlCode(urlCode);
		
		stageLog = saveStageLogToDAO(stageLog);
		
		return stageLog;
	}
	
	public StageLog createStageLog(Integer taskId, Integer istarUserId, String stageName, String stageType, String status, String result, 
			String action, String url0, String url1, String actionPassword, Integer actionId, String urlCode){
		
		TaskServices taskServices = new TaskServices();
		Task task = taskServices.getTask(taskId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		StageLog stageLog = new StageLog();
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		stageLog.setTask(task);
		stageLog.setIstarUser(istarUser);
		stageLog.setStageName(stageName);
		stageLog.setStageType(stageType);
		stageLog.setStatus(status);
		stageLog.setResult(result);
		stageLog.setAction(action);
		stageLog.setUrl0(url0);
		stageLog.setUrl1(url1);
		stageLog.setActionPassword(actionPassword);
		stageLog.setActionId(actionId);
		stageLog.setCreatedAt(current);
		stageLog.setUpdatedAt(current);
		stageLog.setUrlCode(urlCode);
		
		stageLog = saveStageLogToDAO(stageLog);
		
		return stageLog;
	}
	
	public StageLog updateStageLog(StageLog stageLog, String status, String result, String action, String url0, String url1, 
			String actionPassword, Integer actionId, String urlCode){
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		stageLog.setStatus(status);
		stageLog.setResult(result);
		stageLog.setAction(action);
		stageLog.setUrl0(url0);
		stageLog.setUrl1(url1);
		stageLog.setActionPassword(actionPassword);
		stageLog.setActionId(actionId);
		stageLog.setUpdatedAt(current);
		stageLog.setUrlCode(urlCode);
		
		stageLog = updateStageLogToDAO(stageLog);
		
		return stageLog;
	}
	
	public StageLog updateStatusAndResultStageLog(StageLog stageLog, String status, String result, String action){
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		stageLog.setStatus(status);
		stageLog.setResult(result);
		stageLog.setAction(action);
		stageLog.setUpdatedAt(current);
		
		stageLog = updateStageLogToDAO(stageLog);
		
		return stageLog;
	}
	
	public StageLog updateStageLog(Integer stageLogId, String status, String result, String action, String url0, String url1, 
			String actionPassword, Integer actionId, String urlCode){
		
		StageLog stageLog = getStageLog(stageLogId);
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		stageLog.setStatus(status);
		stageLog.setResult(result);
		stageLog.setAction(action);
		stageLog.setUrl0(url0);
		stageLog.setUrl1(url1);
		stageLog.setActionPassword(actionPassword);
		stageLog.setActionId(actionId);
		stageLog.setUrlCode(urlCode);
		stageLog.setUpdatedAt(current);
		
		stageLog = updateStageLogToDAO(stageLog);
		
		return stageLog;
	}
	
	public List<StageLog> getStageLogOfTaskForStage(Task task, String stageName){
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<StageLog> criteria = builder.createQuery(StageLog.class);
		Root<StageLog> fromStageLog = criteria.from(StageLog.class);

		criteria.where(builder.equal(fromStageLog.get("task"), task));
		criteria.where(builder.equal(fromStageLog.get("stageName"), stageName));

		List<StageLog> allStageLog = session.createQuery(criteria).getResultList();
		
		return allStageLog;
	}
	
	public List<StageLog> getStageLogOfTaskForIstarUser(Task task, IstarUser istarUser){
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<StageLog> criteria = builder.createQuery(StageLog.class);
		Root<StageLog> fromStageLog = criteria.from(StageLog.class);

		criteria.where(builder.equal(fromStageLog.get("task"), task));
		criteria.where(builder.equal(fromStageLog.get("istarUser"), istarUser));

		List<StageLog> allStageLog = session.createQuery(criteria).getResultList();
		
		return allStageLog;
	}
	
	public List<StageLog> getStageLogFromUrlCode(String urlCode){
		StageLogDAO stageLogDAO = new StageLogDAO();
		List<StageLog> stageLogList = new ArrayList<StageLog>();
		
		try{
			stageLogList = stageLogDAO.findByUrlCode(urlCode);
		}catch(IllegalArgumentException e){
			System.out.println("urlCode is null");
		}		
		return stageLogList;
	}
	
	public StageLog getStageLog(Integer stageLogId){
		StageLogDAO stageLogDAO = new StageLogDAO();
		StageLog stageLog;
		
		try{
		stageLog = stageLogDAO.findById(stageLogId);
		}catch(IllegalArgumentException e){
			stageLog = null;
		}		
		return stageLog;
	}
	
	public void removeStageLogs(List<StageLog> allStageLog){		
		for(StageLog stageLog : allStageLog){
			deleteStageLogFromDAO(stageLog);
		}				
		System.out.println("All existing stage logs deleted");
	}
	
	public StageLog saveStageLogToDAO(StageLog stageLog) {

		StageLogDAO stageLogDAO = new StageLogDAO();

		Session stageLogSession = stageLogDAO.getSession();
		Transaction stageLogTransaction = null;
		try {
			stageLogTransaction = stageLogSession.beginTransaction();
			stageLogSession.save(stageLog);
			stageLogTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (stageLogTransaction != null)
				stageLogTransaction.rollback();
			e.printStackTrace();
		} finally {
			stageLogSession.close();
		}
		return stageLog;
	}

	public StageLog updateStageLogToDAO(StageLog stageLog) {

		StageLogDAO stageLogDAO = new StageLogDAO();

		Session stageLogSession = stageLogDAO.getSession();
		Transaction stageLogTransaction = null;
		try {
			stageLogTransaction = stageLogSession.beginTransaction();
			stageLogSession.update(stageLog);
			stageLogTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (stageLogTransaction != null)
				stageLogTransaction.rollback();
			e.printStackTrace();
		} finally {
			stageLogSession.close();
		}
		return stageLog;
	}
	
	public void deleteStageLogFromDAO(StageLog stageLog) {

		StageLogDAO stageLogDAO = new StageLogDAO();

		Session stageLogSession = stageLogDAO.getSession();
		Transaction stageLogTransaction = null;
		try {
			stageLogTransaction = stageLogSession.beginTransaction();
			stageLogSession.delete(stageLog);
			stageLogTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (stageLogTransaction != null)
				stageLogTransaction.rollback();
			e.printStackTrace();
		} finally {
			stageLogSession.close();
		}
	}
}
