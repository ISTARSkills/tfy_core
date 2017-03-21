package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.TaskType
 * @author MyEclipse Persistence Tools
 */
public class TaskTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TaskTypeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CATEGORY = "category";
	public static final String WORKFLOW_FILE = "workflowFile";

	public void save(TaskType transientInstance) {
		log.debug("saving TaskType instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskType persistentInstance) {
		log.debug("deleting TaskType instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskType findById(java.lang.Integer id) {
		log.debug("getting TaskType instance with id: " + id);
		try {
			TaskType instance = (TaskType) getSession().get("com.viksitpro.core.dao.entities.TaskType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TaskType> findByExample(TaskType instance) {
		log.debug("finding TaskType instance by example");
		try {
			List<TaskType> results = (List<TaskType>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.TaskType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TaskType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TaskType as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TaskType> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<TaskType> findByCategory(Object category) {
		return findByProperty(CATEGORY, category);
	}

	public List<TaskType> findByWorkflowFile(Object workflowFile) {
		return findByProperty(WORKFLOW_FILE, workflowFile);
	}

	public List findAll() {
		log.debug("finding all TaskType instances");
		try {
			String queryString = "from TaskType";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskType merge(TaskType detachedInstance) {
		log.debug("merging TaskType instance");
		try {
			TaskType result = (TaskType) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskType instance) {
		log.debug("attaching dirty TaskType instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskType instance) {
		log.debug("attaching clean TaskType instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}