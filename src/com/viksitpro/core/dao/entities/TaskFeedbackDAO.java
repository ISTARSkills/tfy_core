package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskFeedback entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.TaskFeedback
 * @author MyEclipse Persistence Tools
 */
public class TaskFeedbackDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TaskFeedbackDAO.class);
	// property constants
	public static final String STATUS = "status";
	public static final String FEEDBACK = "feedback";
	public static final String STAGE = "stage";
	public static final String RATING = "rating";

	public void save(TaskFeedback transientInstance) {
		log.debug("saving TaskFeedback instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskFeedback persistentInstance) {
		log.debug("deleting TaskFeedback instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskFeedback findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting TaskFeedback instance with id: " + id);
		try {
			TaskFeedback instance = (TaskFeedback) ss.get("com.viksitpro.core.dao.entities.TaskFeedback", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<TaskFeedback> findByExample(TaskFeedback instance) {
		log.debug("finding TaskFeedback instance by example");
		try {
			List<TaskFeedback> results = (List<TaskFeedback>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.TaskFeedback").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding TaskFeedback instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TaskFeedback as model where model." + propertyName + "= ?";
			Query queryObject = ss.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<TaskFeedback> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<TaskFeedback> findByFeedback(Object feedback) {
		return findByProperty(FEEDBACK, feedback);
	}

	public List<TaskFeedback> findByStage(Object stage) {
		return findByProperty(STAGE, stage);
	}

	public List<TaskFeedback> findByRating(Object rating) {
		return findByProperty(RATING, rating);
	}

	public List findAll() {
		log.debug("finding all TaskFeedback instances");
		try {
			String queryString = "from TaskFeedback";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskFeedback merge(TaskFeedback detachedInstance) {
		log.debug("merging TaskFeedback instance");
		try {
			TaskFeedback result = (TaskFeedback) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskFeedback instance) {
		log.debug("attaching dirty TaskFeedback instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskFeedback instance) {
		log.debug("attaching clean TaskFeedback instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}