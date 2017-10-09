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
 * EventSessionLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.EventSessionLog
 * @author MyEclipse Persistence Tools
 */
public class EventSessionLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(EventSessionLogDAO.class);
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

	public void save(EventSessionLog transientInstance) {
		log.debug("saving EventSessionLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EventSessionLog persistentInstance) {
		log.debug("deleting EventSessionLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventSessionLog findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting EventSessionLog instance with id: " + id);
		try {
			EventSessionLog instance = (EventSessionLog) ss
					.get("com.viksitpro.core.dao.entities.EventSessionLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<EventSessionLog> findByExample(EventSessionLog instance) {
		log.debug("finding EventSessionLog instance by example");
		try {
			List<EventSessionLog> results = (List<EventSessionLog>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.EventSessionLog").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding EventSessionLog instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from EventSessionLog as model where model." + propertyName + "= ?";
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

	public List<EventSessionLog> findByTrainerId(Object trainerId) {
		return findByProperty(TRAINER_ID, trainerId);
	}

	public List<EventSessionLog> findByEventId(Object eventId) {
		return findByProperty(EVENT_ID, eventId);
	}

	public List<EventSessionLog> findByCourseId(Object courseId) {
		return findByProperty(COURSE_ID, courseId);
	}

	public List<EventSessionLog> findByCmsessionId(Object cmsessionId) {
		return findByProperty(CMSESSION_ID, cmsessionId);
	}

	public List<EventSessionLog> findByLessonId(Object lessonId) {
		return findByProperty(LESSON_ID, lessonId);
	}

	public List<EventSessionLog> findByPptId(Object pptId) {
		return findByProperty(PPT_ID, pptId);
	}

	public List<EventSessionLog> findBySlideId(Object slideId) {
		return findByProperty(SLIDE_ID, slideId);
	}

	public List<EventSessionLog> findByBatchId(Object batchId) {
		return findByProperty(BATCH_ID, batchId);
	}

	public List<EventSessionLog> findByModuleId(Object moduleId) {
		return findByProperty(MODULE_ID, moduleId);
	}

	public List<EventSessionLog> findByAssessmentId(Object assessmentId) {
		return findByProperty(ASSESSMENT_ID, assessmentId);
	}

	public List<EventSessionLog> findByLessonType(Object lessonType) {
		return findByProperty(LESSON_TYPE, lessonType);
	}

	public List<EventSessionLog> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all EventSessionLog instances");
		try {
			String queryString = "from EventSessionLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EventSessionLog merge(EventSessionLog detachedInstance) {
		log.debug("merging EventSessionLog instance");
		try {
			EventSessionLog result = (EventSessionLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EventSessionLog instance) {
		log.debug("attaching dirty EventSessionLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EventSessionLog instance) {
		log.debug("attaching clean EventSessionLog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}