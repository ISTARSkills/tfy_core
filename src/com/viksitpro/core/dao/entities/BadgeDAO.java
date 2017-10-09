package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Badge
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Badge
 * @author MyEclipse Persistence Tools
 */
public class BadgeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BadgeDAO.class);
	// property constants
	public static final String BADGE_TITLE = "badgeTitle";
	public static final String BADGE_IMAGE = "badgeImage";

	public void save(Badge transientInstance) {
		log.debug("saving Badge instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Badge persistentInstance) {
		log.debug("deleting Badge instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Badge findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Badge instance with id: " + id);
		try {
			Badge instance = (Badge) ss.get("com.viksitpro.core.dao.entities.Badge", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Badge> findByExample(Badge instance) {
		log.debug("finding Badge instance by example");
		try {
			List<Badge> results = (List<Badge>) getSession().createCriteria("com.viksitpro.core.dao.entities.Badge")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding Badge instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Badge as model where model." + propertyName + "= ?";
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

	public List<Badge> findByBadgeTitle(Object badgeTitle) {
		return findByProperty(BADGE_TITLE, badgeTitle);
	}

	public List<Badge> findByBadgeImage(Object badgeImage) {
		return findByProperty(BADGE_IMAGE, badgeImage);
	}

	public List findAll() {
		log.debug("finding all Badge instances");
		try {
			String queryString = "from Badge";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Badge merge(Badge detachedInstance) {
		log.debug("merging Badge instance");
		try {
			Badge result = (Badge) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Badge instance) {
		log.debug("attaching dirty Badge instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Badge instance) {
		log.debug("attaching clean Badge instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}