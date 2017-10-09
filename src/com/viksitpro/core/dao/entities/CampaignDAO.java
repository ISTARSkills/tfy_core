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
 * Campaign entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Campaign
 * @author MyEclipse Persistence Tools
 */
public class CampaignDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CampaignDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String IS_ACTIVE = "isActive";

	public void save(Campaign transientInstance) {
		log.debug("saving Campaign instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Campaign persistentInstance) {
		log.debug("deleting Campaign instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Campaign findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Campaign instance with id: " + id);
		try {
			Campaign instance = (Campaign) ss.get("com.viksitpro.core.dao.entities.Campaign", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Campaign> findByExample(Campaign instance) {
		log.debug("finding Campaign instance by example");
		try {
			List<Campaign> results = (List<Campaign>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Campaign").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding Campaign instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Campaign as model where model." + propertyName + "= ?";
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

	public List<Campaign> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Campaign> findByIsActive(Object isActive) {
		return findByProperty(IS_ACTIVE, isActive);
	}

	public List findAll() {
		log.debug("finding all Campaign instances");
		try {
			String queryString = "from Campaign";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Campaign merge(Campaign detachedInstance) {
		log.debug("merging Campaign instance");
		try {
			Campaign result = (Campaign) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Campaign instance) {
		log.debug("attaching dirty Campaign instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Campaign instance) {
		log.debug("attaching clean Campaign instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}