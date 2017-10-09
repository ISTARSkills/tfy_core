package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Job
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Job
 * @author MyEclipse Persistence Tools
 */
public class JobDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(JobDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String MAXIMUM_EXPERIENCE = "maximumExperience";
	public static final String MINIMUM_EXPERIENCE = "minimumExperience";
	public static final String AVAILABLE_POSITIONS = "availablePositions";
	public static final String TOTAL_POSITIONS = "totalPositions";
	public static final String MAXIMUM_SALARY = "maximumSalary";
	public static final String MINIMUM_SALARY = "minimumSalary";
	public static final String SOCIAL_CODE = "socialCode";
	public static final String STATE = "state";
	public static final String TAGS = "tags";
	public static final String LOCATION = "location";

	public void save(Job transientInstance) {
		log.debug("saving Job instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Job persistentInstance) {
		log.debug("deleting Job instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Job findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Job instance with id: " + id);
		try {
			Job instance = (Job) ss.get("com.viksitpro.core.dao.entities.Job", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Job> findByExample(Job instance) {
		log.debug("finding Job instance by example");
		try {
			List<Job> results = (List<Job>) getSession().createCriteria("com.viksitpro.core.dao.entities.Job")
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
		log.debug("finding Job instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Job as model where model." + propertyName + "= ?";
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

	public List<Job> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Job> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Job> findByMaximumExperience(Object maximumExperience) {
		return findByProperty(MAXIMUM_EXPERIENCE, maximumExperience);
	}

	public List<Job> findByMinimumExperience(Object minimumExperience) {
		return findByProperty(MINIMUM_EXPERIENCE, minimumExperience);
	}

	public List<Job> findByAvailablePositions(Object availablePositions) {
		return findByProperty(AVAILABLE_POSITIONS, availablePositions);
	}

	public List<Job> findByTotalPositions(Object totalPositions) {
		return findByProperty(TOTAL_POSITIONS, totalPositions);
	}

	public List<Job> findByMaximumSalary(Object maximumSalary) {
		return findByProperty(MAXIMUM_SALARY, maximumSalary);
	}

	public List<Job> findByMinimumSalary(Object minimumSalary) {
		return findByProperty(MINIMUM_SALARY, minimumSalary);
	}

	public List<Job> findBySocialCode(Object socialCode) {
		return findByProperty(SOCIAL_CODE, socialCode);
	}

	public List<Job> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Job> findByTags(Object tags) {
		return findByProperty(TAGS, tags);
	}

	public List<Job> findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findAll() {
		log.debug("finding all Job instances");
		try {
			String queryString = "from Job";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Job merge(Job detachedInstance) {
		log.debug("merging Job instance");
		try {
			Job result = (Job) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Job instance) {
		log.debug("attaching dirty Job instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Job instance) {
		log.debug("attaching clean Job instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}