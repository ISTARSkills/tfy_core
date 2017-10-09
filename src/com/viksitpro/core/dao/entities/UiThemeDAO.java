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
 * UiTheme entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.UiTheme
 * @author MyEclipse Persistence Tools
 */
public class UiThemeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UiThemeDAO.class);
	// property constants

	public void save(UiTheme transientInstance) {
		log.debug("saving UiTheme instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UiTheme persistentInstance) {
		log.debug("deleting UiTheme instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UiTheme findById(com.viksitpro.core.dao.entities.UiThemeId id) {
		Session ss = getSession();
		log.debug("getting UiTheme instance with id: " + id);
		try {
			UiTheme instance = (UiTheme) ss.get("com.viksitpro.core.dao.entities.UiTheme", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<UiTheme> findByExample(UiTheme instance) {
		log.debug("finding UiTheme instance by example");
		try {
			List<UiTheme> results = (List<UiTheme>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.UiTheme").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding UiTheme instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from UiTheme as model where model." + propertyName + "= ?";
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

	public List findAll() {
		log.debug("finding all UiTheme instances");
		try {
			String queryString = "from UiTheme";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UiTheme merge(UiTheme detachedInstance) {
		log.debug("merging UiTheme instance");
		try {
			UiTheme result = (UiTheme) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UiTheme instance) {
		log.debug("attaching dirty UiTheme instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UiTheme instance) {
		log.debug("attaching clean UiTheme instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}