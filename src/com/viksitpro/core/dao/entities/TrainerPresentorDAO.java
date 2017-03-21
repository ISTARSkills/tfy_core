package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TrainerPresentor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.TrainerPresentor
 * @author MyEclipse Persistence Tools
 */
public class TrainerPresentorDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TrainerPresentorDAO.class);
	// property constants

	public void save(TrainerPresentor transientInstance) {
		log.debug("saving TrainerPresentor instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TrainerPresentor persistentInstance) {
		log.debug("deleting TrainerPresentor instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TrainerPresentor findById(com.viksitpro.core.dao.entities.TrainerPresentorId id) {
		log.debug("getting TrainerPresentor instance with id: " + id);
		try {
			TrainerPresentor instance = (TrainerPresentor) getSession()
					.get("com.viksitpro.core.dao.entities.TrainerPresentor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TrainerPresentor> findByExample(TrainerPresentor instance) {
		log.debug("finding TrainerPresentor instance by example");
		try {
			List<TrainerPresentor> results = (List<TrainerPresentor>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.TrainerPresentor").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TrainerPresentor instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TrainerPresentor as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TrainerPresentor instances");
		try {
			String queryString = "from TrainerPresentor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TrainerPresentor merge(TrainerPresentor detachedInstance) {
		log.debug("merging TrainerPresentor instance");
		try {
			TrainerPresentor result = (TrainerPresentor) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TrainerPresentor instance) {
		log.debug("attaching dirty TrainerPresentor instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TrainerPresentor instance) {
		log.debug("attaching clean TrainerPresentor instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}