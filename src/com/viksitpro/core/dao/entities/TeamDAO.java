package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Team
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Team
 * @author MyEclipse Persistence Tools
 */
public class TeamDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TeamDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	public void save(Team transientInstance) {
		log.debug("saving Team instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Team persistentInstance) {
		log.debug("deleting Team instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Team findById(java.lang.Integer id) {
		log.debug("getting Team instance with id: " + id);
		try {
			Team instance = (Team) getSession().get("com.viksitpro.core.dao.entities.Team", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Team> findByExample(Team instance) {
		log.debug("finding Team instance by example");
		try {
			List<Team> results = (List<Team>) getSession().createCriteria("com.viksitpro.core.dao.entities.Team")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Team instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Team as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Team> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Team> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all Team instances");
		try {
			String queryString = "from Team";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Team merge(Team detachedInstance) {
		log.debug("merging Team instance");
		try {
			Team result = (Team) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Team instance) {
		log.debug("attaching dirty Team instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Team instance) {
		log.debug("attaching clean Team instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}