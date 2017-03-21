package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * OpsEventSessionLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.OpsEventSessionLog
 * @author MyEclipse Persistence Tools
 */
public class OpsEventSessionLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(OpsEventSessionLogDAO.class);
	// property constants
	public static final String TRAINER_ID = "trainerId";
	public static final String EVENT_ID = "eventId";
	public static final String COURSE_ID = "courseId";
	public static final String CMSESSION_ID = "cmsessionId";
	public static final String LESSON_ID = "lessonId";
	public static final String PPT_ID = "pptId";
	public static final String SLIDE_ID = "slideId";
	public static final String BATCH_ID = "batchId";
	public static final String MODULE_ID = "moduleId";
	public static final String ASSESSMENT_ID = "assessmentId";
	public static final String LESSON_TYPE = "lessonType";
	public static final String URL = "url";

	public void save(OpsEventSessionLog transientInstance) {
		log.debug("saving OpsEventSessionLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OpsEventSessionLog persistentInstance) {
		log.debug("deleting OpsEventSessionLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OpsEventSessionLog findById(java.lang.Integer id) {
		log.debug("getting OpsEventSessionLog instance with id: " + id);
		try {
			OpsEventSessionLog instance = (OpsEventSessionLog) getSession()
					.get("com.viksitpro.core.dao.entities.OpsEventSessionLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<OpsEventSessionLog> findByExample(OpsEventSessionLog instance) {
		log.debug("finding OpsEventSessionLog instance by example");
		try {
			List<OpsEventSessionLog> results = (List<OpsEventSessionLog>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.OpsEventSessionLog").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding OpsEventSessionLog instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from OpsEventSessionLog as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<OpsEventSessionLog> findByTrainerId(Object trainerId) {
		return findByProperty(TRAINER_ID, trainerId);
	}

	public List<OpsEventSessionLog> findByEventId(Object eventId) {
		return findByProperty(EVENT_ID, eventId);
	}

	public List<OpsEventSessionLog> findByCourseId(Object courseId) {
		return findByProperty(COURSE_ID, courseId);
	}

	public List<OpsEventSessionLog> findByCmsessionId(Object cmsessionId) {
		return findByProperty(CMSESSION_ID, cmsessionId);
	}

	public List<OpsEventSessionLog> findByLessonId(Object lessonId) {
		return findByProperty(LESSON_ID, lessonId);
	}

	public List<OpsEventSessionLog> findByPptId(Object pptId) {
		return findByProperty(PPT_ID, pptId);
	}

	public List<OpsEventSessionLog> findBySlideId(Object slideId) {
		return findByProperty(SLIDE_ID, slideId);
	}

	public List<OpsEventSessionLog> findByBatchId(Object batchId) {
		return findByProperty(BATCH_ID, batchId);
	}

	public List<OpsEventSessionLog> findByModuleId(Object moduleId) {
		return findByProperty(MODULE_ID, moduleId);
	}

	public List<OpsEventSessionLog> findByAssessmentId(Object assessmentId) {
		return findByProperty(ASSESSMENT_ID, assessmentId);
	}

	public List<OpsEventSessionLog> findByLessonType(Object lessonType) {
		return findByProperty(LESSON_TYPE, lessonType);
	}

	public List<OpsEventSessionLog> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all OpsEventSessionLog instances");
		try {
			String queryString = "from OpsEventSessionLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OpsEventSessionLog merge(OpsEventSessionLog detachedInstance) {
		log.debug("merging OpsEventSessionLog instance");
		try {
			OpsEventSessionLog result = (OpsEventSessionLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OpsEventSessionLog instance) {
		log.debug("attaching dirty OpsEventSessionLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OpsEventSessionLog instance) {
		log.debug("attaching clean OpsEventSessionLog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}