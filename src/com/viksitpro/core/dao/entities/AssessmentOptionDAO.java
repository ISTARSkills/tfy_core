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
 * AssessmentOption entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.AssessmentOption
 * @author MyEclipse Persistence Tools
 */
public class AssessmentOptionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AssessmentOptionDAO.class);
	// property constants
	public static final String TEXT = "text";
	public static final String MARKING_SCHEME = "markingScheme";

	public void save(AssessmentOption transientInstance) {
		log.debug("saving AssessmentOption instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssessmentOption persistentInstance) {
		log.debug("deleting AssessmentOption instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssessmentOption findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting AssessmentOption instance with id: " + id);
		try {
			AssessmentOption instance = (AssessmentOption) ss
					.get("com.viksitpro.core.dao.entities.AssessmentOption", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<AssessmentOption> findByExample(AssessmentOption instance) {
		log.debug("finding AssessmentOption instance by example");
		try {
			List<AssessmentOption> results = (List<AssessmentOption>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.AssessmentOption").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AssessmentOption instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from AssessmentOption as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AssessmentOption> findByText(Object text) {
		return findByProperty(TEXT, text);
	}

	public List<AssessmentOption> findByMarkingScheme(Object markingScheme) {
		return findByProperty(MARKING_SCHEME, markingScheme);
	}

	public List findAll() {
		log.debug("finding all AssessmentOption instances");
		try {
			String queryString = "from AssessmentOption";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssessmentOption merge(AssessmentOption detachedInstance) {
		log.debug("merging AssessmentOption instance");
		try {
			AssessmentOption result = (AssessmentOption) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssessmentOption instance) {
		log.debug("attaching dirty AssessmentOption instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssessmentOption instance) {
		log.debug("attaching clean AssessmentOption instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}