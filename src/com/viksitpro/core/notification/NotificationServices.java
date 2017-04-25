package com.viksitpro.core.notification;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.IstarNotification;
import com.viksitpro.core.dao.entities.IstarNotificationDAO;

public class NotificationServices {
	
	public IstarNotification createIstarNotification(int senderId, int receiverId, String title, String details, 
			String status, String action, String type, Boolean isEventBased, int taskId, String groupCode){
		
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
		if(groupCode==null){
			groupCode = UUID.randomUUID().toString();
		}
		istarNotification.setGroupCode(groupCode);
		istarNotification.setCreatedAt(current);
		
		istarNotification = saveIstarNotificationToDAO(istarNotification);
		
		return istarNotification;
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
}
