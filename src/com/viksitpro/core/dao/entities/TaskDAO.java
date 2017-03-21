package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Task
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Task
 * @author MyEclipse Persistence Tools
 */
public class TaskDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TaskDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PRIORITY = "priority";
	public static final String STATE = "state";
	public static final String DURATION_IN_HOURS = "durationInHours";
	public static final String IS_REPEATATIVE = "isRepeatative";
	public static final String IS_ACTIVE = "isActive";
	public static final String TAGS = "tags";
	public static final String ITEM_ID = "itemId";
	public static final String ITEM_TYPE = "itemType";
	public static final String IS_TIMED_TASK = "isTimedTask";
	public static final String FOLLOW_UP_DURATION_IN_DAYS = "followUpDurationInDays";

	public void save(Task transientInstance) {
		log.debug("saving Task instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Task persistentInstance) {
		log.debug("deleting Task instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Task findById(java.lang.Integer id) {
		log.debug("getting Task instance with id: " + id);
		try {
			Task instance = (Task) getSession().get("com.viksitpro.core.dao.entities.Task", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Task> findByExample(Task instance) {
		log.debug("finding Task instance by example");
		try {
			List<Task> results = (List<Task>) getSession().createCriteria("com.viksitpro.core.dao.entities.Task")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Task instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Task as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Task> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Task> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Task> findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	public List<Task> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Task> findByDurationInHours(Object durationInHours) {
		return findByProperty(DURATION_IN_HOURS, durationInHours);
	}

	public List<Task> findByIsRepeatative(Object isRepeatative) {
		return findByProperty(IS_REPEATATIVE, isRepeatative);
	}

	public List<Task> findByIsActive(Object isActive) {
		return findByProperty(IS_ACTIVE, isActive);
	}

	public List<Task> findByTags(Object tags) {
		return findByProperty(TAGS, tags);
	}

	public List<Task> findByItemId(Object itemId) {
		return findByProperty(ITEM_ID, itemId);
	}

	public List<Task> findByItemType(Object itemType) {
		return findByProperty(ITEM_TYPE, itemType);
	}

	public List<Task> findByIsTimedTask(Object isTimedTask) {
		return findByProperty(IS_TIMED_TASK, isTimedTask);
	}

	public List<Task> findByFollowUpDurationInDays(Object followUpDurationInDays) {
		return findByProperty(FOLLOW_UP_DURATION_IN_DAYS, followUpDurationInDays);
	}

	public List findAll() {
		log.debug("finding all Task instances");
		try {
			String queryString = "from Task";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Task merge(Task detachedInstance) {
		log.debug("merging Task instance");
		try {
			Task result = (Task) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Task instance) {
		log.debug("attaching dirty Task instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Task instance) {
		log.debug("attaching clean Task instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}