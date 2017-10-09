package com.viksitpro.core.dao.utils.task;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;*/

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Project;
import com.viksitpro.core.dao.entities.Task;
import com.viksitpro.core.dao.entities.TaskDAO;
import com.viksitpro.core.dao.entities.TaskType;
import com.viksitpro.core.dao.entities.Team;
import com.viksitpro.core.dao.utils.user.IstarUserServices;
import com.viksitpro.core.dao.utils.user.TeamServices;
import com.viksitpro.core.utilities.DBUTILS;

public class TaskServices {
	
	public Integer createTodaysTask(String name, String description, String owner, String actor, String itemId, String itemType)
	{
		DBUTILS util = new DBUTILS();
		Integer taskId = null;
		/*String checkIfTaskExist ="select id from task where actor= "+actor+" and item_id="+itemId+" and item_type='"+itemType+"' and cast (start_date  as date) = cast (now() as date)";
		List<HashMap<String, Object>> alreadyAvailbleTask = util.executeQuery(checkIfTaskExist);
		if(alreadyAvailbleTask.size()>0)
		{
			taskId = (Integer)alreadyAvailbleTask.get(0).get("id");
		}
		else
		{*/
			String sql ="INSERT INTO task (id, name, description, owner, actor, state,  start_date, end_date, is_active,  created_at, updated_at, item_id, item_type) "
					+ "VALUES ((select COALESCE(max(id),0) +1 from task), '"+name+"', '"+description+"', "+owner+", "+actor+", 'SCHEDULED', now(),now(), 't', now(), now(), "+itemId+", '"+itemType+"') returning id;";
				//System.out.println(">>>"+sql);
			taskId = util.executeUpdateReturn(sql);
		//}
		
			 
		return taskId;
	}
	
	/**
	 * This method create Task and returns Task Object corresponding to Input parameter name  description  state  tags
	 * itemType  jsessionId  itemId  istarUserByOwnerId  istarUserByAssigneeMemberId  taskTypeId  parentTaskId    teamId
	 * priority  followupDurationInDays  durationInHours   isActive   isRepeatative  isTimedTask  startDate   endDate  followupDate
	 * 
	 * @param name String value of Task name
	 * @param description  String value of Task description
	 * @param state  String value of Task state
	 * @param tags   String value of Task tags
	 * @param itemType  String value of Task itemType
	 * @param jsessionId  String value of Task jsessionId
	 * @param itemId   Integer value of Task itemId
	 * @param istarUserByOwnerId  Integer value of Task istarUserByOwnerId
	 * @param istarUserByAssigneeMemberId  Integer value of Task istarUserByAssigneeMemberId
	 * @param taskTypeId  Integer value of Task taskTypeId
	 * @param parentTaskId  Integer value of Task parentTaskId
	 * @param projectId  Integer value of Task projectId
	 * @param teamId   Integer value of Task teamId
	 * @param priority  Integer value of Task priority
	 * @param followupDurationInDays  Integer value of Task followupDurationInDays
	 * @param durationInHours  Double value of Task durationInHours
	 * @param isActive  Boolean value of Task isActive
	 * @param isRepeatative  Boolean value of Task isRepeatative
	 * @param isTimedTask  Boolean value of Task isTimedTask
	 * @param startDate  Timestamp value of Task startDate
	 * @param endDate  Timestamp value of Task endDate
	 * @param followupDate  Timestamp value of Task followupDate
	 * @return Task Object
	 */
	public Task createTask(String name, String description, String state, String tags, String itemType,String jsessionId, Integer itemId, Integer istarUserByOwnerId, 
			Integer istarUserByAssigneeMemberId, Integer taskTypeId, Integer parentTaskId, Integer projectId, Integer teamId, Integer priority, 
			Integer followupDurationInDays, Float durationInHours, Boolean isActive, Boolean isRepeatative, Boolean isTimedTask, Timestamp startDate,
			Timestamp endDate, Timestamp followupDate){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		
		IstarUser istarUserByOwner = istarUserServices.getIstarUser(istarUserByOwnerId);
		IstarUser istarUserByAssigneeMember = istarUserServices.getIstarUser(istarUserByAssigneeMemberId);
		
		IstarUser istarUserByActor = null;
		if(istarUserByAssigneeMember!=null){
			istarUserByActor = istarUserByAssigneeMember;
		}else{
			istarUserByActor = istarUserByOwner;
		}
		
		Task parentTask = getTask(parentTaskId);
		
		TaskTypeServices taskTypeServices = new TaskTypeServices();
		TaskType taskType = taskTypeServices.getTaskType(taskTypeId);
		
		ProjectServices projectServices = new ProjectServices();
		Project project = projectServices.getProject(projectId);
		
		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);
		
		Task task = new Task();
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setName(name);
		task.setDescription(description);
		task.setState(state);
		task.setTags(tags);
		task.setItemType(itemType);
		task.setItemId(itemId);
		task.setIstarUserByOwner(istarUserByOwner);
		task.setIstarUserByAssigneeMember(istarUserByAssigneeMember);
		task.setIstarUserByActor(istarUserByActor);
		task.setTaskType(taskType);
		task.setTask(parentTask);
		task.setProject(project);
		task.setTeam(team);
		task.setPriority(priority);
		task.setFollowUpDurationInDays(followupDurationInDays);
		task.setDurationInHours(durationInHours);
		task.setIsActive(isActive);
		task.setIsRepeatative(isRepeatative);
		task.setIsTimedTask(isTimedTask);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setFollowupDate(followupDate);
		task.setCreatedAt(current);
		task.setUpdatedAt(current);

		task = saveTaskToDAO(task);		
				
		new TaskLogServices().generateTaskLog(task, "created", jsessionId);
			
		return task;
	}
	
	public Task updateTask(Integer taskId, String name, String description, String state, String tags, String itemType,String jsessionId, Integer itemId, Integer istarUserByOwnerId, 
			Integer istarUserByAssigneeMemberId, Integer taskTypeId, Integer parentTaskId, Integer projectId, Integer teamId, Integer priority, 
			Integer followupDurationInDays, Float durationInHours, Boolean isActive, Boolean isRepeatative, Boolean isTimedTask, Timestamp startDate,
			Timestamp endDate, Timestamp followupDate){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		
		IstarUser istarUserByOwner = istarUserServices.getIstarUser(istarUserByOwnerId);
		IstarUser istarUserByAssigneeMember = istarUserServices.getIstarUser(istarUserByAssigneeMemberId);
		
		IstarUser istarUserByActor = null;
		if(istarUserByAssigneeMember!=null){
			istarUserByActor = istarUserByAssigneeMember;
		}else{
			istarUserByActor = istarUserByOwner;
		}
		
		Task parentTask = getTask(parentTaskId);
		
		TaskTypeServices taskTypeServices = new TaskTypeServices();
		TaskType taskType = taskTypeServices.getTaskType(taskTypeId);
		
		ProjectServices projectServices = new ProjectServices();
		Project project = projectServices.getProject(projectId);
		
		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);
		
		Task task = getTask(taskId);
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setName(name);
		task.setDescription(description);
		task.setState(state);
		task.setTags(tags);
		task.setItemType(itemType);
		task.setItemId(itemId);
		task.setIstarUserByOwner(istarUserByOwner);
		task.setIstarUserByAssigneeMember(istarUserByAssigneeMember);
		task.setIstarUserByActor(istarUserByActor);
		task.setTaskType(taskType);
		task.setTask(parentTask);
		task.setProject(project);
		task.setTeam(team);
		task.setPriority(priority);
		task.setFollowUpDurationInDays(followupDurationInDays);
		task.setDurationInHours(durationInHours);
		task.setIsActive(isActive);
		task.setIsRepeatative(isRepeatative);
		task.setIsTimedTask(isTimedTask);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setFollowupDate(followupDate);
		task.setCreatedAt(current);
		task.setUpdatedAt(current);

		task = updateTaskToDAO(task);		
				
		new TaskLogServices().generateTaskLog(task, "updated", jsessionId);
			
		return task;
	}

	/**
	 * This method update TaskState and returns Task Object corresponding to Input parameter newStage  jsessionId   taskId
	 * 
	 * @param newStage  String value of TaskState newStage
	 * @param jsessionId  String value of TaskState jsessionId
	 * @param taskId  Integer value of TaskState taskId
	 * @return  Task Object
	 */
	public Task updateTaskState(String newStage, String jsessionId, Integer taskId){
		
		Task task = getTask(taskId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setState(newStage);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "State updated", jsessionId);
		
		return task;
	}
	
	public Task updateTaskTeam(Team team, String jsessionId, Integer taskId){
		
		Task task = getTask(taskId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setTeam(team);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "State updated", jsessionId);
		
		return task;
	}
	
	public Task updateTask(Timestamp followupDate, Timestamp endDate, Integer followUpDurationInDays, String jsessionId, Integer taskId){
		
		Task task = getTask(taskId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		
		task.setFollowUpDurationInDays(followUpDurationInDays);
		task.setFollowupDate(followupDate);
		task.setEndDate(endDate);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "Followup date and endDate updated", jsessionId);
		
		return task;
	}
	
	public Task updateTaskTypeOfTask(Integer taskTypeId, Integer taskId, String jsessionId){
		
		TaskTypeServices taskTypeServices = new TaskTypeServices();
		TaskType taskType = taskTypeServices.getTaskType(taskTypeId);
		
		Task task = getTask(taskId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setTaskType(taskType);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "TaskType updated", jsessionId);
		
		return task;		
	}
	
	public Task updateTaskStartAndEndDate(Timestamp startDate, Timestamp endDate, String jsessionId, Integer taskId){
		
		Task task = getTask(taskId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "State updated", jsessionId);
		
		return task;
	}
	   
	/**
	 * This method update TaskActor and returns Task Object corresponding to Input parameter jsessionId  istarUserByActorId   taskId
	 * 
	 * @param jsessionId String value of TaskActor jsessionId
	 * @param istarUserByActorId  Integer value of TaskActor istarUserByActorId
	 * @param taskId  Integer value of TaskActor taskId
	 * @return  Task Object
	 */
	public Task updateTaskActor(String jsessionId, Integer istarUserByActorId, Integer taskId){
		
		Task task = getTask(taskId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUserByActor = istarUserServices.getIstarUser(istarUserByActorId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setIstarUserByActor(istarUserByActor);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "Actor updated", jsessionId);
		
		return task;
	}
	
	/**
	 * This method update TaskState And Actor and returns Task Object corresponding to Input parameter newStage  jsessionId
	 * istarUserByActorId  taskId
	 * 
	 * @param newStage  String value of TaskStateAndActor newStage
	 * @param jsessionId  String value of TaskStateAndActor jsessionId
	 * @param istarUserByActorId  Integer value of TaskStateAndActor istarUserByActorId
	 * @param taskId  Integer value of TaskStateAndActor taskId
	 * @return  Task Object
	 */
	public Task updateTaskStateAndActor(String newStage, String jsessionId, Integer istarUserByActorId, Integer taskId){
		
		Task task = getTask(taskId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUserByActor = istarUserServices.getIstarUser(istarUserByActorId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setState(newStage);
		task.setIstarUserByActor(istarUserByActor);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "State and Actor updated", jsessionId);
		
		return task;
	}
	
	public Task completeTask(String state, boolean isActive, Integer taskId, String jsessionId){
		
		Task task = getTask(taskId);
	
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setState(state);
		task.setIsActive(isActive);
		//task.setEndDate(current);
		task.setUpdatedAt(current);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "Task Completed", jsessionId);
		
		return task;
	}
	
   
	/**
	 * This method returns Task Object corresponding to Input parameter taskId
	 * 
	 * @param taskId  Integer value of Task taskId
	 * @return Task Object
	 */
	public Task getTask(Integer taskId){
		TaskDAO taskDAO = new TaskDAO();
		Task task;
		try{
		task = taskDAO.findById(taskId);
		}catch(IllegalArgumentException e){
			task = null;
		}
		return task;
	}
	
	/*public List<Task> getAllTaskFromItemIdAndItemType(Integer itemId, String itemType){

		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
		Root<Task> fromTask = criteria.from(Task.class);

		ArrayList<Predicate> allPredicates = new ArrayList<Predicate>();
		
		allPredicates.add(builder.equal(fromTask.get("itemType"), itemType));
		allPredicates.add(builder.equal(fromTask.get("itemId"), itemId));
		
		criteria.where(builder.and(allPredicates.toArray(new Predicate[] {})));
		
		List<Task> allTask = session.createQuery(criteria).getResultList();
		
		return allTask;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Task> getAllTaskFromItemIdAndItemType(Integer itemId, String itemType){

		String hql = "from Task task where item_type= :itemType and item_id= :itemId";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("itemType",itemType);
		query.setParameter("itemId",itemId);
		
		List<Task> allTask = query.list();
		
		return allTask;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Task> getAllTaskFromItemAndActor(Integer itemId, String itemType, Integer actorId){

		String hql = "from Task task where item_type= :itemType and item_id= :itemId and actor= :actorId";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("itemType",itemType);
		query.setParameter("itemId",itemId);
		query.setParameter("actorId",actorId);
		
		List<Task> allTask = query.list();
		
		return allTask;
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getAllTaskOfOwner(IstarUser istarUser){
		
		String hql = "from Task task where owner= :owner";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("owner", istarUser.getId());
		
		List<Task> allTask = query.list();
		
		return allTask;
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getAllTaskOfActor(IstarUser istarUser){
		
		String hql = "from Task task where actor= :actor";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		
		Query query = session.createQuery(hql);
		query.setParameter("actor", istarUser.getId());
		
		List<Task> allTask = query.list();
		//System.out.println("allTask" + allTask.size());
		return allTask;
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getAllTaskOfActorForToday(IstarUser istarUser){
		
		String hql = "from Task task where actor= :actor";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

/*		DateFormat day = new SimpleDateFormat("yyyyMMdd");
		Date startDate = new Date();
		
		try {
			startDate = day.parse(day.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date endDate = new Date(startDate.getTime()+24*60*60*1000);
		Timestamp dayStart = new Timestamp(startDate.getTime());
		Timestamp dayEnd = new Timestamp(endDate.getTime());

		//System.out.println("Day ends at->" + dayStart);
		//System.out.println("Day ends at->" + dayEnd);
		*/
		Query query = session.createQuery(hql);
		query.setParameter("actor", istarUser.getId());
		//query.setParameter("dayStart", dayStart);
		//query.setParameter("dayEnd", dayEnd);
		
		List<Task> allTask = query.list();
		//System.out.println("allTask" + allTask.size());
		return allTask;		
	}
	
	
	@SuppressWarnings("unchecked")
	public int getAllTaskOfActorForToday22Count(IstarUser istarUser){
		
		String hql = "from Task task where actor= :actor and startDate<= :dayEnd";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		DateFormat day = new SimpleDateFormat("yyyyMMdd");
		Date startDate = new Date();
		
		try {
			startDate = day.parse(day.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date endDate = new Date(startDate.getTime()+24*60*60*1000);
		Timestamp dayStart = new Timestamp(startDate.getTime());
		Timestamp dayEnd = new Timestamp(endDate.getTime());

		//System.out.println("Day ends at->" + dayStart);
		//System.out.println("Day ends at->" + dayEnd);
		
		Query query = session.createQuery(hql);
		query.setParameter("actor", istarUser.getId());
		//query.setParameter("dayStart", dayStart);
		query.setParameter("dayEnd", dayEnd);
		
		List<Task> allTask = query.list();
		//System.out.println("allTask" + allTask.size());
		return allTask.size();		
	}
	
/*	public List<Task> getAllTaskFromItemAndActor(Integer itemId, String itemType, Integer actorId){

		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
		Root<Task> fromTask = criteria.from(Task.class);

		ArrayList<Predicate> allPredicates = new ArrayList<Predicate>();
		
		allPredicates.add(builder.equal(fromTask.get("itemType"), itemType));
		allPredicates.add(builder.equal(fromTask.get("itemId"), itemId));
		allPredicates.add(builder.equal(fromTask.get("istarUserByActor"), actorId));
		
		criteria.where(builder.and(allPredicates.toArray(new Predicate[] {})));
		
		List<Task> allTask = session.createQuery(criteria).getResultList();
		
		return allTask;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Task> getAllSubTask(Task task){
		
		TaskDAO taskDAO = new TaskDAO();
		List<Task> allSubTask = (List<Task>) taskDAO.findByProperty("task", task);
		
		//System.out.println("SubTasks"+allSubTask.size());
		
		return allSubTask;
	}
	
	
	/**
	 * This method returns list of task object corresponding to the List of TaskID in input parameter
	 * 
	 * @param task  ArrayList<Integer> of TaskIds
	 * @return ArrayList<Task> Object
	 */
	public ArrayList<Task> getListOfTaskFromId(ArrayList<Integer> allTaskId){
		ArrayList<Task> listOfTask = new ArrayList<Task>();

		for(Integer taskId: allTaskId){
			listOfTask.add(getTask(taskId));
		}		
		return listOfTask;
	}
	
	/**
	 * This method returns task which is to be marked as Inactive
	 * 
	 * @param task  Task instance to be marked as Inactive
	 * @return Task Object
	 */
	public Task markTaskAsInactive(Integer taskId, String jsessionId){
		
		Task task = getTask(taskId);
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setUpdatedAt(current);
		task.setIsActive(false);
			
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "Task marked as Inactive", jsessionId);
		
		return task;
	}
	
	public Task setTaskAsRepeatable(Task task, boolean isRepeatable, String jsessionId){
		
		java.util.Date date= new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		task.setUpdatedAt(current);
		task.setIsRepeatative(isRepeatable);
		
		task = updateTaskToDAO(task);
		
		new TaskLogServices().generateTaskLog(task, "Set Task as IsRepeatable:" + isRepeatable, jsessionId);
		
		return task;
	}	
	
	public Task saveTaskToDAO(Task task) {

		TaskDAO taskDAO = new TaskDAO();

		Session taskSession = taskDAO.getSession();
		Transaction taskTransaction = null;
		try {
			taskTransaction = taskSession.beginTransaction();
			taskSession.update(task);
			taskTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskTransaction != null)
				taskTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskSession.close();
		}
		return task;
	}

	public Task updateTaskToDAO(Task task) {

		TaskDAO taskDAO = new TaskDAO();

		Session taskSession = taskDAO.getSession();
		Transaction taskTransaction = null;
		try {
			taskTransaction = taskSession.beginTransaction();
			taskSession.update(task);
			taskTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (taskTransaction != null)
				taskTransaction.rollback();
			e.printStackTrace();
		} finally {
			taskSession.close();
		}
		return task;
	}
	
	/**
	 * This method return ChildTaskForUser Object as results Object corresponding to Input parameter user  task
	 * 
	 * @param user  IstarUser instance of ChildTaskForUser user
	 * @param task  Task instance of ChildTaskForUser task
	 * @return results Object
	 */
	@SuppressWarnings("unchecked")
	public static List<Task> getChildTaskForUser(String user, Task task) {
		String hql ="from Task task where task.parentTask=:parent_task_id and task.actor=:actor_id";
		Query query = new TaskDAO().getSession().createQuery(hql);
		query.setInteger("actor_id", Integer.parseInt(user));
		query.setInteger("parent_task_id", task.getId());
		
		List<Task> results = query.list();
		return results;
	}
}