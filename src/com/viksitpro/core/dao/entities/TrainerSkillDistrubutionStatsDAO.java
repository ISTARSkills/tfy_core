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
 * TrainerSkillDistrubutionStats entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.viksitpro.core.dao.entities.TrainerSkillDistrubutionStats
 * @author MyEclipse Persistence Tools
 */
public class TrainerSkillDistrubutionStatsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TrainerSkillDistrubutionStatsDAO.class);
	// property constants

	public void save(TrainerSkillDistrubutionStats transientInstance) {
		log.debug("saving TrainerSkillDistrubutionStats instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TrainerSkillDistrubutionStats persistentInstance) {
		log.debug("deleting TrainerSkillDistrubutionStats instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TrainerSkillDistrubutionStats findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting TrainerSkillDistrubutionStats instance with id: " + id);
		try {
			TrainerSkillDistrubutionStats instance = (TrainerSkillDistrubutionStats) ss
					.get("com.viksitpro.core.dao.entities.TrainerSkillDistrubutionStats", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<TrainerSkillDistrubutionStats> findByExample(TrainerSkillDistrubutionStats instance) {
		log.debug("finding TrainerSkillDistrubutionStats instance by example");
		try {
			List<TrainerSkillDistrubutionStats> results = (List<TrainerSkillDistrubutionStats>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.TrainerSkillDistrubutionStats")
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
		log.debug(
				"finding TrainerSkillDistrubutionStats instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TrainerSkillDistrubutionStats as model where model." + propertyName + "= ?";
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
		log.debug("finding all TrainerSkillDistrubutionStats instances");
		try {
			String queryString = "from TrainerSkillDistrubutionStats";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TrainerSkillDistrubutionStats merge(TrainerSkillDistrubutionStats detachedInstance) {
		log.debug("merging TrainerSkillDistrubutionStats instance");
		try {
			TrainerSkillDistrubutionStats result = (TrainerSkillDistrubutionStats) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TrainerSkillDistrubutionStats instance) {
		log.debug("attaching dirty TrainerSkillDistrubutionStats instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TrainerSkillDistrubutionStats instance) {
		log.debug("attaching clean TrainerSkillDistrubutionStats instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}