package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BatchStats entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.BatchStats
 * @author MyEclipse Persistence Tools
 */
public class BatchStatsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BatchStatsDAO.class);
	// property constants
	public static final String BATCH_ID = "batchId";
	public static final String BATCH_NAME = "batchName";
	public static final String ATTENDANCE_PERC = "attendancePerc";
	public static final String AVG_FEEDBACK = "avgFeedback";
	public static final String STU_ENROLLED = "stuEnrolled";
	public static final String COMPLETION_PERC = "completionPerc";
	public static final String COLLEGE_ID = "collegeId";
	public static final String BATCH_GROUP_ID = "batchGroupId";

	public void save(BatchStats transientInstance) {
		log.debug("saving BatchStats instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BatchStats persistentInstance) {
		log.debug("deleting BatchStats instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BatchStats findById(java.lang.Integer id) {
		log.debug("getting BatchStats instance with id: " + id);
		try {
			BatchStats instance = (BatchStats) getSession().get("com.viksitpro.core.dao.entities.BatchStats", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BatchStats> findByExample(BatchStats instance) {
		log.debug("finding BatchStats instance by example");
		try {
			List<BatchStats> results = (List<BatchStats>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.BatchStats").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BatchStats instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from BatchStats as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BatchStats> findByBatchId(Object batchId) {
		return findByProperty(BATCH_ID, batchId);
	}

	public List<BatchStats> findByBatchName(Object batchName) {
		return findByProperty(BATCH_NAME, batchName);
	}

	public List<BatchStats> findByAttendancePerc(Object attendancePerc) {
		return findByProperty(ATTENDANCE_PERC, attendancePerc);
	}

	public List<BatchStats> findByAvgFeedback(Object avgFeedback) {
		return findByProperty(AVG_FEEDBACK, avgFeedback);
	}

	public List<BatchStats> findByStuEnrolled(Object stuEnrolled) {
		return findByProperty(STU_ENROLLED, stuEnrolled);
	}

	public List<BatchStats> findByCompletionPerc(Object completionPerc) {
		return findByProperty(COMPLETION_PERC, completionPerc);
	}

	public List<BatchStats> findByCollegeId(Object collegeId) {
		return findByProperty(COLLEGE_ID, collegeId);
	}

	public List<BatchStats> findByBatchGroupId(Object batchGroupId) {
		return findByProperty(BATCH_GROUP_ID, batchGroupId);
	}

	public List findAll() {
		log.debug("finding all BatchStats instances");
		try {
			String queryString = "from BatchStats";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BatchStats merge(BatchStats detachedInstance) {
		log.debug("merging BatchStats instance");
		try {
			BatchStats result = (BatchStats) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BatchStats instance) {
		log.debug("attaching dirty BatchStats instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BatchStats instance) {
		log.debug("attaching clean BatchStats instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}