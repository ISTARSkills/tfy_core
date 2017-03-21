package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CourseStats entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.CourseStats
 * @author MyEclipse Persistence Tools
 */
public class CourseStatsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CourseStatsDAO.class);
	// property constants
	public static final String COURSE_ID = "courseId";
	public static final String COURSE_NAME = "courseName";
	public static final String ATTENDANCE_PERC = "attendancePerc";
	public static final String AVG_FEEDBACK = "avgFeedback";
	public static final String STU_ENROLLED = "stuEnrolled";
	public static final String COMPLETION_PERC = "completionPerc";
	public static final String BATCH_GROUP_ID = "batchGroupId";
	public static final String COLLEGE_ID = "collegeId";
	public static final String COURSE_DESCRIPTION = "courseDescription";

	public void save(CourseStats transientInstance) {
		log.debug("saving CourseStats instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CourseStats persistentInstance) {
		log.debug("deleting CourseStats instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CourseStats findById(java.lang.Integer id) {
		log.debug("getting CourseStats instance with id: " + id);
		try {
			CourseStats instance = (CourseStats) getSession().get("com.viksitpro.core.dao.entities.CourseStats", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CourseStats> findByExample(CourseStats instance) {
		log.debug("finding CourseStats instance by example");
		try {
			List<CourseStats> results = (List<CourseStats>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.CourseStats").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CourseStats instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from CourseStats as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CourseStats> findByCourseId(Object courseId) {
		return findByProperty(COURSE_ID, courseId);
	}

	public List<CourseStats> findByCourseName(Object courseName) {
		return findByProperty(COURSE_NAME, courseName);
	}

	public List<CourseStats> findByAttendancePerc(Object attendancePerc) {
		return findByProperty(ATTENDANCE_PERC, attendancePerc);
	}

	public List<CourseStats> findByAvgFeedback(Object avgFeedback) {
		return findByProperty(AVG_FEEDBACK, avgFeedback);
	}

	public List<CourseStats> findByStuEnrolled(Object stuEnrolled) {
		return findByProperty(STU_ENROLLED, stuEnrolled);
	}

	public List<CourseStats> findByCompletionPerc(Object completionPerc) {
		return findByProperty(COMPLETION_PERC, completionPerc);
	}

	public List<CourseStats> findByBatchGroupId(Object batchGroupId) {
		return findByProperty(BATCH_GROUP_ID, batchGroupId);
	}

	public List<CourseStats> findByCollegeId(Object collegeId) {
		return findByProperty(COLLEGE_ID, collegeId);
	}

	public List<CourseStats> findByCourseDescription(Object courseDescription) {
		return findByProperty(COURSE_DESCRIPTION, courseDescription);
	}

	public List findAll() {
		log.debug("finding all CourseStats instances");
		try {
			String queryString = "from CourseStats";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CourseStats merge(CourseStats detachedInstance) {
		log.debug("merging CourseStats instance");
		try {
			CourseStats result = (CourseStats) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CourseStats instance) {
		log.debug("attaching dirty CourseStats instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CourseStats instance) {
		log.debug("attaching clean CourseStats instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}