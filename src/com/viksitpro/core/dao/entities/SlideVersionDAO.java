package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * SlideVersion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.SlideVersion
 * @author MyEclipse Persistence Tools
 */
public class SlideVersionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SlideVersionDAO.class);
	// property constants
	public static final String SLIDE_TEXT = "slideText";
	public static final String TEMPLATE = "template";
	public static final String TEACHER_NOTES = "teacherNotes";
	public static final String STUDENT_NOTES = "studentNotes";

	public void save(SlideVersion transientInstance) {
		log.debug("saving SlideVersion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SlideVersion persistentInstance) {
		log.debug("deleting SlideVersion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SlideVersion findById(java.lang.Integer id) {
		log.debug("getting SlideVersion instance with id: " + id);
		try {
			SlideVersion instance = (SlideVersion) getSession().get("com.viksitpro.core.dao.entities.SlideVersion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SlideVersion> findByExample(SlideVersion instance) {
		log.debug("finding SlideVersion instance by example");
		try {
			List<SlideVersion> results = (List<SlideVersion>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.SlideVersion").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SlideVersion instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from SlideVersion as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SlideVersion> findBySlideText(Object slideText) {
		return findByProperty(SLIDE_TEXT, slideText);
	}

	public List<SlideVersion> findByTemplate(Object template) {
		return findByProperty(TEMPLATE, template);
	}

	public List<SlideVersion> findByTeacherNotes(Object teacherNotes) {
		return findByProperty(TEACHER_NOTES, teacherNotes);
	}

	public List<SlideVersion> findByStudentNotes(Object studentNotes) {
		return findByProperty(STUDENT_NOTES, studentNotes);
	}

	public List findAll() {
		log.debug("finding all SlideVersion instances");
		try {
			String queryString = "from SlideVersion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SlideVersion merge(SlideVersion detachedInstance) {
		log.debug("merging SlideVersion instance");
		try {
			SlideVersion result = (SlideVersion) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SlideVersion instance) {
		log.debug("attaching dirty SlideVersion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SlideVersion instance) {
		log.debug("attaching clean SlideVersion instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}