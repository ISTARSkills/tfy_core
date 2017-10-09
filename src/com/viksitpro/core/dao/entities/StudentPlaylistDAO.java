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
 * StudentPlaylist entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.StudentPlaylist
 * @author MyEclipse Persistence Tools
 */
public class StudentPlaylistDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StudentPlaylistDAO.class);
	// property constants
	public static final String STATUS = "status";

	public void save(StudentPlaylist transientInstance) {
		log.debug("saving StudentPlaylist instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StudentPlaylist persistentInstance) {
		log.debug("deleting StudentPlaylist instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StudentPlaylist findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting StudentPlaylist instance with id: " + id);
		try {
			StudentPlaylist instance = (StudentPlaylist) ss
					.get("com.viksitpro.core.dao.entities.StudentPlaylist", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<StudentPlaylist> findByExample(StudentPlaylist instance) {
		log.debug("finding StudentPlaylist instance by example");
		try {
			List<StudentPlaylist> results = (List<StudentPlaylist>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.StudentPlaylist").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding StudentPlaylist instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from StudentPlaylist as model where model." + propertyName + "= ?";
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

	public List<StudentPlaylist> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<StudentPlaylist> findAll() {
		log.debug("finding all StudentPlaylist instances");
		try {
			String queryString = "from StudentPlaylist";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StudentPlaylist merge(StudentPlaylist detachedInstance) {
		log.debug("merging StudentPlaylist instance");
		try {
			StudentPlaylist result = (StudentPlaylist) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StudentPlaylist instance) {
		log.debug("attaching dirty StudentPlaylist instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StudentPlaylist instance) {
		log.debug("attaching clean StudentPlaylist instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}