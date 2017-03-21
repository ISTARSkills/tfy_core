package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BadgeSkill entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.BadgeSkill
 * @author MyEclipse Persistence Tools
 */
public class BadgeSkillDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BadgeSkillDAO.class);
	// property constants
	public static final String POINTS = "points";

	public void save(BadgeSkill transientInstance) {
		log.debug("saving BadgeSkill instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BadgeSkill persistentInstance) {
		log.debug("deleting BadgeSkill instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BadgeSkill findById(java.lang.Integer id) {
		log.debug("getting BadgeSkill instance with id: " + id);
		try {
			BadgeSkill instance = (BadgeSkill) getSession().get("com.viksitpro.core.dao.entities.BadgeSkill", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BadgeSkill> findByExample(BadgeSkill instance) {
		log.debug("finding BadgeSkill instance by example");
		try {
			List<BadgeSkill> results = (List<BadgeSkill>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.BadgeSkill").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BadgeSkill instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from BadgeSkill as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BadgeSkill> findByPoints(Object points) {
		return findByProperty(POINTS, points);
	}

	public List findAll() {
		log.debug("finding all BadgeSkill instances");
		try {
			String queryString = "from BadgeSkill";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BadgeSkill merge(BadgeSkill detachedInstance) {
		log.debug("merging BadgeSkill instance");
		try {
			BadgeSkill result = (BadgeSkill) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BadgeSkill instance) {
		log.debug("attaching dirty BadgeSkill instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BadgeSkill instance) {
		log.debug("attaching clean BadgeSkill instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}