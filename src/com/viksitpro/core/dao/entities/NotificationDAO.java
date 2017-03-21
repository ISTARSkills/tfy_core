package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Notification entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Notification
 * @author MyEclipse Persistence Tools
 */
public class NotificationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(NotificationDAO.class);
	// property constants
	public static final String NOTIFICATION_MESSAGE = "notificationMessage";
	public static final String TYPE = "type";
	public static final String SENT = "sent";

	public void save(Notification transientInstance) {
		log.debug("saving Notification instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Notification persistentInstance) {
		log.debug("deleting Notification instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Notification findById(java.lang.Integer id) {
		log.debug("getting Notification instance with id: " + id);
		try {
			Notification instance = (Notification) getSession().get("com.viksitpro.core.dao.entities.Notification", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Notification> findByExample(Notification instance) {
		log.debug("finding Notification instance by example");
		try {
			List<Notification> results = (List<Notification>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Notification").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Notification instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Notification as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Notification> findByNotificationMessage(Object notificationMessage) {
		return findByProperty(NOTIFICATION_MESSAGE, notificationMessage);
	}

	public List<Notification> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Notification> findBySent(Object sent) {
		return findByProperty(SENT, sent);
	}

	public List findAll() {
		log.debug("finding all Notification instances");
		try {
			String queryString = "from Notification";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Notification merge(Notification detachedInstance) {
		log.debug("merging Notification instance");
		try {
			Notification result = (Notification) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Notification instance) {
		log.debug("attaching dirty Notification instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Notification instance) {
		log.debug("attaching clean Notification instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}