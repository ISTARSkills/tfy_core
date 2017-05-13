package com.viksitpro.core.notification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.IstarNotification;
import com.viksitpro.core.dao.entities.IstarNotificationDAO;

public class IstarNotificationServices {
	
	public IstarNotification createIstarNotification(int senderId, int receiverId, String title, String details, 
			String status, String action, String type, Boolean isEventBased, Integer taskId, String groupCode){
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		IstarNotification istarNotification = new IstarNotification();
		
		istarNotification.setSenderId(senderId);
		istarNotification.setReceiverId(receiverId);
		istarNotification.setTitle(title);
		istarNotification.setDetails(details);
		istarNotification.setStatus(status);
		istarNotification.setAction(action);
		istarNotification.setType(type);
		istarNotification.setIsEventBased(isEventBased);
		istarNotification.setTaskId(taskId);
		istarNotification.setCreatedAt(current);
		istarNotification.setReadByAdmin(false);
		//String groupCode = UUID.randomUUID().toString();
		istarNotification.setGroupCode(groupCode);
		
		istarNotification = saveIstarNotificationToDAO(istarNotification);
		
		return istarNotification;
	}
	
	public void createIstarNotification(int senderId, List<Integer> allReceiverId, String title, String details, 
			String status, String action, String type, Boolean isEventBased, Integer taskId){
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		String groupCode = UUID.randomUUID().toString();
		
		for(Integer receiverId : allReceiverId){
			IstarNotification istarNotification = new IstarNotification();
			
			istarNotification.setSenderId(senderId);
			istarNotification.setReceiverId(receiverId);
			istarNotification.setTitle(title);
			istarNotification.setDetails(details);
			istarNotification.setStatus(status);
			istarNotification.setAction(action);
			istarNotification.setType(type);
			istarNotification.setIsEventBased(isEventBased);
			istarNotification.setTaskId(taskId);
			istarNotification.setCreatedAt(current);
			istarNotification.setGroupCode(groupCode);
			
			saveIstarNotificationToDAO(istarNotification);
		}
	}	
	
	public void updateNotificationStatus(List<Integer> allNotificationId, String status){
			
		for(Integer notificationId : allNotificationId){
			IstarNotification istarNotification = getIstarNotification(notificationId);
			
			if(istarNotification!=null){
				istarNotification.setStatus(status);
				istarNotification = updateIstarNotificationToDAO(istarNotification);
			}
		}	
	}
	
	public void updateNotificationStatus(Integer notificationId, String status){		
			IstarNotification istarNotification = getIstarNotification(notificationId);
			
			if(istarNotification!=null){
				istarNotification.setStatus(status);
				istarNotification = updateIstarNotificationToDAO(istarNotification);
			}
	}
	
	
	public List<IstarNotification> getAllNotificationOfUser(int istarUserId){
		IstarNotificationDAO istarNotificationDAO = new IstarNotificationDAO();
		List<IstarNotification> allIstarNotification; 
		try{
			allIstarNotification= istarNotificationDAO.findByReceiverId(istarUserId);
		}catch(IllegalArgumentException e){
			allIstarNotification = new ArrayList<IstarNotification>();
		}
		return allIstarNotification;
	}
	
	public IstarNotification getIstarNotification(Integer istarNotificationId){
		IstarNotificationDAO istarNotificationDAO = new IstarNotificationDAO();
		IstarNotification istarNotification;
		try{
		istarNotification = istarNotificationDAO.findById(istarNotificationId);
		}catch(IllegalArgumentException e){
			istarNotification = null;
		}
		return istarNotification;
	}

	public IstarNotification saveIstarNotificationToDAO(IstarNotification istarNotification) {

		IstarNotificationDAO istarNotificationDAO = new IstarNotificationDAO();

		Session istarNotificationSession = istarNotificationDAO.getSession();
		Transaction istarNotificationTransaction = null;
		try {
			istarNotificationTransaction = istarNotificationSession.beginTransaction();
			istarNotificationSession.save(istarNotification);
			istarNotificationTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (istarNotificationTransaction != null)
				istarNotificationTransaction.rollback();
		} finally {
			istarNotificationSession.close();
		}
		return istarNotification;
	}
	
	public IstarNotification updateIstarNotificationToDAO(IstarNotification istarNotification) {

		IstarNotificationDAO istarNotificationDAO = new IstarNotificationDAO();

		Session istarNotificationSession = istarNotificationDAO.getSession();
		Transaction istarNotificationTransaction = null;
		try {
			istarNotificationTransaction = istarNotificationSession.beginTransaction();
			istarNotificationSession.update(istarNotification);
			istarNotificationTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (istarNotificationTransaction != null)
				istarNotificationTransaction.rollback();
		} finally {
			istarNotificationSession.close();
		}
		return istarNotification;
	}
}
