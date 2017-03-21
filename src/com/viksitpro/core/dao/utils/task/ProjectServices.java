package com.viksitpro.core.dao.utils.task;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Project;
import com.viksitpro.core.dao.entities.ProjectDAO;
import com.viksitpro.core.dao.utils.user.IstarUserServices;

public class ProjectServices {

	
	/**
	 * 
	 * @param projectId Integer value of Project Id and  instance of Project to be deleted
	 */
	public void deleteProject(Integer projectId)
	{
		ProjectDAO projectDAO = new ProjectDAO();
		Project project = getProject(projectId);
		Session projectSession = projectDAO.getSession();
		Transaction projectTransaction = null;
		try {
			projectTransaction = projectSession.beginTransaction();
			projectSession.delete(project);
			projectTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (projectTransaction != null)
				projectTransaction.rollback();
			e.printStackTrace();
		} finally {
			projectSession.close();
		}
	}
	
	
	/**
	 * This method create Project and returns Project Object corresponding to Input parameter name  istarUserId  deadline
	 * 
	 * @param name  String value of Project name
	 * @param istarUserId  Integer value of Project istarUserId
	 * @param deadline  Timestamp value of Project deadline
	 * @return  Project Object
	 */
	public Project createProject(String name, Integer istarUserId, Timestamp deadline){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		Project project = new Project();
		
		project.setName(name);
		project.setIstarUser(istarUser);
		project.setDeadline(deadline);
		project.setCreatedAt(current);
		project.setUpdatedAt(current);
		
		project = saveProjectToDAO(project);
		
		return project;
	}
	
	/**
	 * This method updates Project and returns Project Object corresponding to Input parameter name  istarUserId  deadline
	 * 
	 * @param projectId Integer value of projectId 
	 * @param name  String value of Project name
	 * @param istarUserId  Integer value of Project istarUserId
	 * @param deadline  Timestamp value of Project deadline
	 * @return  Project Object
	 */
	public Project updateProject(Integer projectId, String name, Integer istarUserId, Timestamp deadline){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		Project project = getProject(projectId);
		
		project.setName(name);
		project.setIstarUser(istarUser);
		project.setDeadline(deadline);
		project.setUpdatedAt(current);
		
		project = updateProjectToDAO(project);
		
		return project;
	}
	
	/**
	 * This method returns Project Object corresponding to Input parameter projectId
	 * 
	 * @param projectId Integer value of Project ID
	 * @return Project Object
	 */
	public Project getProject(Integer projectId){
		ProjectDAO projectDAO = new ProjectDAO();
		Project project;
		
		try{
		project = projectDAO.findById(projectId);
		}catch(IllegalArgumentException e){
			project = null;
		}		
		return project;
	}
	
	public Project saveProjectToDAO(Project project) {

		ProjectDAO projectDAO = new ProjectDAO();

		Session projectSession = projectDAO.getSession();
		Transaction projectTransaction = null;
		try {
			projectTransaction = projectSession.beginTransaction();
			projectSession.save(project);
			projectTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (projectTransaction != null)
				projectTransaction.rollback();
			e.printStackTrace();
		} finally {
			projectSession.close();
		}
		return project;
	}

	public Project updateProjectToDAO(Project project) {

		ProjectDAO projectDAO = new ProjectDAO();

		Session projectSession = projectDAO.getSession();
		Transaction projectTransaction = null;
		try {
			projectTransaction = projectSession.beginTransaction();
			projectSession.update(project);
			projectTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (projectTransaction != null)
				projectTransaction.rollback();
			e.printStackTrace();
		} finally {
			projectSession.close();
		}
		return project;
	}

	/**
	 * This method Mark Project As Completed returns Project Object corresponding to Input parameter projectId  completedOn
	 * 
	 * @param projectId Integer value of Project Id
	 * @param completedOn Timestamp value of date on which project is completed
	 * @return Project Object
	 */
	public Project markProjectAsCompleted(Integer projectId, Timestamp completedOn)
	{
		Project project = getProject(projectId);
		
		project.setCompletedOn(completedOn);
		project.setUpdatedAt(completedOn);
		
		project = updateProjectToDAO(project);
		
		return project;
	}
}
