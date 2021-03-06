package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Lesson entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Lesson
 * @author MyEclipse Persistence Tools
 */
public class LessonDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(LessonDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String DURATION = "duration";
	public static final String TAGS = "tags";
	public static final String TITLE = "title";
	public static final String SUBJECT = "subject";
	public static final String ORDER_ID = "orderId";

	public void save(Lesson transientInstance) {
		log.debug("saving Lesson instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Lesson persistentInstance) {
		log.debug("deleting Lesson instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Lesson findById(java.lang.Integer id) {
		log.debug("getting Lesson instance with id: " + id);
		try {
			Lesson instance = (Lesson) getSession().get("com.viksitpro.core.dao.entities.Lesson", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Lesson> findByExample(Lesson instance) {
		log.debug("finding Lesson instance by example");
		try {
			List<Lesson> results = (List<Lesson>) getSession().createCriteria("com.viksitpro.core.dao.entities.Lesson")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Lesson instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Lesson as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Lesson> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Lesson> findByDuration(Object duration) {
		return findByProperty(DURATION, duration);
	}

	public List<Lesson> findByTags(Object tags) {
		return findByProperty(TAGS, tags);
	}

	public List<Lesson> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Lesson> findBySubject(Object subject) {
		return findByProperty(SUBJECT, subject);
	}

	public List<Lesson> findByOrderId(Object orderId) {
		return findByProperty(ORDER_ID, orderId);
	}

	public List findAll() {
		log.debug("finding all Lesson instances");
		try {
			String queryString = "from Lesson";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Lesson merge(Lesson detachedInstance) {
		log.debug("merging Lesson instance");
		try {
			Lesson result = (Lesson) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Lesson instance) {
		log.debug("attaching dirty Lesson instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Lesson instance) {
		log.debug("attaching clean Lesson instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}