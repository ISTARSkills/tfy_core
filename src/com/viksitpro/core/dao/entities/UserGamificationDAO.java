package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserGamification entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.UserGamification
 * @author MyEclipse Persistence Tools
 */
public class UserGamificationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserGamificationDAO.class);
	// property constants
	public static final String POINTS = "points";
	public static final String COINS = "coins";

	public void save(UserGamification transientInstance) {
		log.debug("saving UserGamification instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserGamification persistentInstance) {
		log.debug("deleting UserGamification instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserGamification findById(java.lang.Integer id) {
		log.debug("getting UserGamification instance with id: " + id);
		try {
			UserGamification instance = (UserGamification) getSession()
					.get("com.viksitpro.core.dao.entities.UserGamification", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UserGamification> findByExample(UserGamification instance) {
		log.debug("finding UserGamification instance by example");
		try {
			List<UserGamification> results = (List<UserGamification>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.UserGamification").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserGamification instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from UserGamification as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UserGamification> findByPoints(Object points) {
		return findByProperty(POINTS, points);
	}

	public List<UserGamification> findByCoins(Object coins) {
		return findByProperty(COINS, coins);
	}

	public List findAll() {
		log.debug("finding all UserGamification instances");
		try {
			String queryString = "from UserGamification";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserGamification merge(UserGamification detachedInstance) {
		log.debug("merging UserGamification instance");
		try {
			UserGamification result = (UserGamification) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserGamification instance) {
		log.debug("attaching dirty UserGamification instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserGamification instance) {
		log.debug("attaching clean UserGamification instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}