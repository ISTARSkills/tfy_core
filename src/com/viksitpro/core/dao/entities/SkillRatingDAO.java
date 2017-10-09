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
 * SkillRating entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.SkillRating
 * @author MyEclipse Persistence Tools
 */
public class SkillRatingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SkillRatingDAO.class);
	// property constants
	public static final String RATING = "rating";
	public static final String COMMENT = "comment";

	public void save(SkillRating transientInstance) {
		log.debug("saving SkillRating instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkillRating persistentInstance) {
		log.debug("deleting SkillRating instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkillRating findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting SkillRating instance with id: " + id);
		try {
			SkillRating instance = (SkillRating) ss.get("com.viksitpro.core.dao.entities.SkillRating", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<SkillRating> findByExample(SkillRating instance) {
		log.debug("finding SkillRating instance by example");
		try {
			List<SkillRating> results = (List<SkillRating>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.SkillRating").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding SkillRating instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from SkillRating as model where model." + propertyName + "= ?";
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

	public List<SkillRating> findByRating(Object rating) {
		return findByProperty(RATING, rating);
	}

	public List<SkillRating> findByComment(Object comment) {
		return findByProperty(COMMENT, comment);
	}

	public List findAll() {
		log.debug("finding all SkillRating instances");
		try {
			String queryString = "from SkillRating";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkillRating merge(SkillRating detachedInstance) {
		log.debug("merging SkillRating instance");
		try {
			SkillRating result = (SkillRating) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkillRating instance) {
		log.debug("attaching dirty SkillRating instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkillRating instance) {
		log.debug("attaching clean SkillRating instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}