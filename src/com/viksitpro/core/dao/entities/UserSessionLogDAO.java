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
 * UserSessionLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.UserSessionLog
 * @author MyEclipse Persistence Tools
 */
public class UserSessionLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserSessionLogDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String COURSE_ID = "courseId";
	public static final String CMSESSION_ID = "cmsessionId";
	public static final String LESSON_ID = "lessonId";
	public static final String PPT_ID = "pptId";
	public static final String SLIDE_ID = "slideId";
	public static final String MODULE_ID = "moduleId";
	public static final String ASSESSMENT_ID = "assessmentId";
	public static final String LESSON_TYPE = "lessonType";
	public static final String URL = "url";

	public void save(UserSessionLog transientInstance) {
		log.debug("saving UserSessionLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserSessionLog persistentInstance) {
		log.debug("deleting UserSessionLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserSessionLog findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting UserSessionLog instance with id: " + id);
		try {
			UserSessionLog instance = (UserSessionLog) ss
					.get("com.viksitpro.core.dao.entities.UserSessionLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<UserSessionLog> findByExample(UserSessionLog instance) {
		log.debug("finding UserSessionLog instance by example");
		try {
			List<UserSessionLog> results = (List<UserSessionLog>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.UserSessionLog").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding UserSessionLog instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from UserSessionLog as model where model." + propertyName + "= ?";
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

	public List<UserSessionLog> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<UserSessionLog> findByCourseId(Object courseId) {
		return findByProperty(COURSE_ID, courseId);
	}

	public List<UserSessionLog> findByCmsessionId(Object cmsessionId) {
		return findByProperty(CMSESSION_ID, cmsessionId);
	}

	public List<UserSessionLog> findByLessonId(Object lessonId) {
		return findByProperty(LESSON_ID, lessonId);
	}

	public List<UserSessionLog> findByPptId(Object pptId) {
		return findByProperty(PPT_ID, pptId);
	}

	public List<UserSessionLog> findBySlideId(Object slideId) {
		return findByProperty(SLIDE_ID, slideId);
	}

	public List<UserSessionLog> findByModuleId(Object moduleId) {
		return findByProperty(MODULE_ID, moduleId);
	}

	public List<UserSessionLog> findByAssessmentId(Object assessmentId) {
		return findByProperty(ASSESSMENT_ID, assessmentId);
	}

	public List<UserSessionLog> findByLessonType(Object lessonType) {
		return findByProperty(LESSON_TYPE, lessonType);
	}

	public List<UserSessionLog> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all UserSessionLog instances");
		try {
			String queryString = "from UserSessionLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserSessionLog merge(UserSessionLog detachedInstance) {
		log.debug("merging UserSessionLog instance");
		try {
			UserSessionLog result = (UserSessionLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserSessionLog instance) {
		log.debug("attaching dirty UserSessionLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserSessionLog instance) {
		log.debug("attaching clean UserSessionLog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}