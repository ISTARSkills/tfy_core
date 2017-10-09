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
 * ClassroomDetails entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.ClassroomDetails
 * @author MyEclipse Persistence Tools
 */
public class ClassroomDetailsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ClassroomDetailsDAO.class);
	// property constants
	public static final String CLASSROOM_IDENTIFIER = "classroomIdentifier";
	public static final String MAX_STUDENTS = "maxStudents";
	public static final String IP_ADDRESS = "ipAddress";

	public void save(ClassroomDetails transientInstance) {
		log.debug("saving ClassroomDetails instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClassroomDetails persistentInstance) {
		log.debug("deleting ClassroomDetails instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClassroomDetails findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting ClassroomDetails instance with id: " + id);
		try {
			ClassroomDetails instance = (ClassroomDetails) ss
					.get("com.viksitpro.core.dao.entities.ClassroomDetails", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<ClassroomDetails> findByExample(ClassroomDetails instance) {
		log.debug("finding ClassroomDetails instance by example");
		try {
			List<ClassroomDetails> results = (List<ClassroomDetails>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.ClassroomDetails").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding ClassroomDetails instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ClassroomDetails as model where model." + propertyName + "= ?";
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

	public List<ClassroomDetails> findByClassroomIdentifier(Object classroomIdentifier) {
		return findByProperty(CLASSROOM_IDENTIFIER, classroomIdentifier);
	}

	public List<ClassroomDetails> findByMaxStudents(Object maxStudents) {
		return findByProperty(MAX_STUDENTS, maxStudents);
	}

	public List<ClassroomDetails> findByIpAddress(Object ipAddress) {
		return findByProperty(IP_ADDRESS, ipAddress);
	}

	public List findAll() {
		log.debug("finding all ClassroomDetails instances");
		try {
			String queryString = "from ClassroomDetails";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ClassroomDetails merge(ClassroomDetails detachedInstance) {
		log.debug("merging ClassroomDetails instance");
		try {
			ClassroomDetails result = (ClassroomDetails) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClassroomDetails instance) {
		log.debug("attaching dirty ClassroomDetails instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClassroomDetails instance) {
		log.debug("attaching clean ClassroomDetails instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}