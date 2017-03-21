package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TrainerEventLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.TrainerEventLog
 * @author MyEclipse Persistence Tools
 */
public class TrainerEventLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TrainerEventLogDAO.class);
	// property constants
	public static final String TRAINER_ID = "trainerId";
	public static final String COURSE_ID = "courseId";
	public static final String CMSESSION_ID = "cmsessionId";
	public static final String LESSON_ID = "lessonId";
	public static final String PPT_ID = "pptId";
	public static final String SLIDE_ID = "slideId";
	public static final String BATCH_ID = "batchId";
	public static final String MODULE_ID = "moduleId";
	public static final String ASSESSMENT_ID = "assessmentId";
	public static final String LESSON_TYPE = "lessonType";
	public static final String EVENT_STATUS = "eventStatus";
	public static final String EVENT_ID = "eventId";
	public static final String URL = "url";

	public void save(TrainerEventLog transientInstance) {
		log.debug("saving TrainerEventLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TrainerEventLog persistentInstance) {
		log.debug("deleting TrainerEventLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TrainerEventLog findById(java.lang.Integer id) {
		log.debug("getting TrainerEventLog instance with id: " + id);
		try {
			TrainerEventLog instance = (TrainerEventLog) getSession()
					.get("com.viksitpro.core.dao.entities.TrainerEventLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TrainerEventLog> findByExample(TrainerEventLog instance) {
		log.debug("finding TrainerEventLog instance by example");
		try {
			List<TrainerEventLog> results = (List<TrainerEventLog>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.TrainerEventLog").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TrainerEventLog instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TrainerEventLog as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TrainerEventLog> findByTrainerId(Object trainerId) {
		return findByProperty(TRAINER_ID, trainerId);
	}

	public List<TrainerEventLog> findByCourseId(Object courseId) {
		return findByProperty(COURSE_ID, courseId);
	}

	public List<TrainerEventLog> findByCmsessionId(Object cmsessionId) {
		return findByProperty(CMSESSION_ID, cmsessionId);
	}

	public List<TrainerEventLog> findByLessonId(Object lessonId) {
		return findByProperty(LESSON_ID, lessonId);
	}

	public List<TrainerEventLog> findByPptId(Object pptId) {
		return findByProperty(PPT_ID, pptId);
	}

	public List<TrainerEventLog> findBySlideId(Object slideId) {
		return findByProperty(SLIDE_ID, slideId);
	}

	public List<TrainerEventLog> findByBatchId(Object batchId) {
		return findByProperty(BATCH_ID, batchId);
	}

	public List<TrainerEventLog> findByModuleId(Object moduleId) {
		return findByProperty(MODULE_ID, moduleId);
	}

	public List<TrainerEventLog> findByAssessmentId(Object assessmentId) {
		return findByProperty(ASSESSMENT_ID, assessmentId);
	}

	public List<TrainerEventLog> findByLessonType(Object lessonType) {
		return findByProperty(LESSON_TYPE, lessonType);
	}

	public List<TrainerEventLog> findByEventStatus(Object eventStatus) {
		return findByProperty(EVENT_STATUS, eventStatus);
	}

	public List<TrainerEventLog> findByEventId(Object eventId) {
		return findByProperty(EVENT_ID, eventId);
	}

	public List<TrainerEventLog> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all TrainerEventLog instances");
		try {
			String queryString = "from TrainerEventLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TrainerEventLog merge(TrainerEventLog detachedInstance) {
		log.debug("merging TrainerEventLog instance");
		try {
			TrainerEventLog result = (TrainerEventLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TrainerEventLog instance) {
		log.debug("attaching dirty TrainerEventLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TrainerEventLog instance) {
		log.debug("attaching clean TrainerEventLog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}