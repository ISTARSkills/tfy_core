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
 * BgProgress entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.BgProgress
 * @author MyEclipse Persistence Tools
 */
public class BgProgressDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BgProgressDAO.class);
	// property constants
	public static final String COLLEGE_ID = "collegeId";
	public static final String COLLEGE_NAME = "collegeName";
	public static final String BATCH_GROUP_ID = "batchGroupId";
	public static final String BATCH_GROUP_NAME = "batchGroupName";
	public static final String AVG_SCORE = "avgScore";

	public void save(BgProgress transientInstance) {
		log.debug("saving BgProgress instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BgProgress persistentInstance) {
		log.debug("deleting BgProgress instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BgProgress findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting BgProgress instance with id: " + id);
		try {
			BgProgress instance = (BgProgress) ss.get("com.viksitpro.core.dao.entities.BgProgress", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<BgProgress> findByExample(BgProgress instance) {
		log.debug("finding BgProgress instance by example");
		try {
			List<BgProgress> results = (List<BgProgress>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.BgProgress").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BgProgress instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from BgProgress as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BgProgress> findByCollegeId(Object collegeId) {
		return findByProperty(COLLEGE_ID, collegeId);
	}

	public List<BgProgress> findByCollegeName(Object collegeName) {
		return findByProperty(COLLEGE_NAME, collegeName);
	}

	public List<BgProgress> findByBatchGroupId(Object batchGroupId) {
		return findByProperty(BATCH_GROUP_ID, batchGroupId);
	}

	public List<BgProgress> findByBatchGroupName(Object batchGroupName) {
		return findByProperty(BATCH_GROUP_NAME, batchGroupName);
	}

	public List<BgProgress> findByAvgScore(Object avgScore) {
		return findByProperty(AVG_SCORE, avgScore);
	}

	public List findAll() {
		log.debug("finding all BgProgress instances");
		try {
			String queryString = "from BgProgress";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BgProgress merge(BgProgress detachedInstance) {
		log.debug("merging BgProgress instance");
		try {
			BgProgress result = (BgProgress) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BgProgress instance) {
		log.debug("attaching dirty BgProgress instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BgProgress instance) {
		log.debug("attaching clean BgProgress instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}