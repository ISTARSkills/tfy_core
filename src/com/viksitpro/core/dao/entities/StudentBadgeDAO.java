package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * StudentBadge entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.StudentBadge
 * @author MyEclipse Persistence Tools
 */
public class StudentBadgeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StudentBadgeDAO.class);
	// property constants
	public static final String STUDENT_ID = "studentId";
	public static final String BADGE_ID = "badgeId";

	public void save(StudentBadge transientInstance) {
		log.debug("saving StudentBadge instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StudentBadge persistentInstance) {
		log.debug("deleting StudentBadge instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StudentBadge findById(java.lang.Integer id) {
		log.debug("getting StudentBadge instance with id: " + id);
		try {
			StudentBadge instance = (StudentBadge) getSession().get("com.viksitpro.core.dao.entities.StudentBadge", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<StudentBadge> findByExample(StudentBadge instance) {
		log.debug("finding StudentBadge instance by example");
		try {
			List<StudentBadge> results = (List<StudentBadge>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.StudentBadge").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding StudentBadge instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from StudentBadge as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<StudentBadge> findByStudentId(Object studentId) {
		return findByProperty(STUDENT_ID, studentId);
	}

	public List<StudentBadge> findByBadgeId(Object badgeId) {
		return findByProperty(BADGE_ID, badgeId);
	}

	public List findAll() {
		log.debug("finding all StudentBadge instances");
		try {
			String queryString = "from StudentBadge";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StudentBadge merge(StudentBadge detachedInstance) {
		log.debug("merging StudentBadge instance");
		try {
			StudentBadge result = (StudentBadge) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StudentBadge instance) {
		log.debug("attaching dirty StudentBadge instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StudentBadge instance) {
		log.debug("attaching clean StudentBadge instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}