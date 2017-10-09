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
 * ChatGroup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.ChatGroup
 * @author MyEclipse Persistence Tools
 */
public class ChatGroupDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ChatGroupDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CREATED_BY = "createdBy";

	public void save(ChatGroup transientInstance) {
		log.debug("saving ChatGroup instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ChatGroup persistentInstance) {
		log.debug("deleting ChatGroup instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ChatGroup findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting ChatGroup instance with id: " + id);
		try {
			ChatGroup instance = (ChatGroup) ss.get("com.viksitpro.core.dao.entities.ChatGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<ChatGroup> findByExample(ChatGroup instance) {
		log.debug("finding ChatGroup instance by example");
		try {
			List<ChatGroup> results = (List<ChatGroup>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.ChatGroup").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding ChatGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ChatGroup as model where model." + propertyName + "= ?";
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

	public List<ChatGroup> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ChatGroup> findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all ChatGroup instances");
		try {
			String queryString = "from ChatGroup";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ChatGroup merge(ChatGroup detachedInstance) {
		log.debug("merging ChatGroup instance");
		try {
			ChatGroup result = (ChatGroup) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ChatGroup instance) {
		log.debug("attaching dirty ChatGroup instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ChatGroup instance) {
		log.debug("attaching clean ChatGroup instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}