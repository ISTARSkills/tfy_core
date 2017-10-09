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
 * AttendanceStats entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.AttendanceStats
 * @author MyEclipse Persistence Tools
 */
public class AttendanceStatsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AttendanceStatsDAO.class);
	// property constants
	public static final String COURSE_ID = "courseId";
	public static final String BATCH_GROUP_ID = "batchGroupId";
	public static final String PERCENTAGE_ATTENDANCE = "percentageAttendance";

	public void save(AttendanceStats transientInstance) {
		log.debug("saving AttendanceStats instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AttendanceStats persistentInstance) {
		log.debug("deleting AttendanceStats instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AttendanceStats findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting AttendanceStats instance with id: " + id);
		try {
			AttendanceStats instance = (AttendanceStats) ss
					.get("com.viksitpro.core.dao.entities.AttendanceStats", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<AttendanceStats> findByExample(AttendanceStats instance) {
		log.debug("finding AttendanceStats instance by example");
		try {
			List<AttendanceStats> results = (List<AttendanceStats>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.AttendanceStats").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding AttendanceStats instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from AttendanceStats as model where model." + propertyName + "= ?";
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

	public List<AttendanceStats> findByCourseId(Object courseId) {
		return findByProperty(COURSE_ID, courseId);
	}

	public List<AttendanceStats> findByBatchGroupId(Object batchGroupId) {
		return findByProperty(BATCH_GROUP_ID, batchGroupId);
	}

	public List<AttendanceStats> findByPercentageAttendance(Object percentageAttendance) {
		return findByProperty(PERCENTAGE_ATTENDANCE, percentageAttendance);
	}

	public List findAll() {
		log.debug("finding all AttendanceStats instances");
		try {
			String queryString = "from AttendanceStats";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AttendanceStats merge(AttendanceStats detachedInstance) {
		log.debug("merging AttendanceStats instance");
		try {
			AttendanceStats result = (AttendanceStats) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AttendanceStats instance) {
		log.debug("attaching dirty AttendanceStats instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AttendanceStats instance) {
		log.debug("attaching clean AttendanceStats instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}