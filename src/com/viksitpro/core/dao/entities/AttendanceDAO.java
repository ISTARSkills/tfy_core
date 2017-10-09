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
 * Attendance entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Attendance
 * @author MyEclipse Persistence Tools
 */
public class AttendanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AttendanceDAO.class);
	// property constants
	public static final String STATUS = "status";
	public static final String EVENT_ID = "eventId";

	public void save(Attendance transientInstance) {
		log.debug("saving Attendance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Attendance persistentInstance) {
		log.debug("deleting Attendance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Attendance findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Attendance instance with id: " + id);
		try {
			Attendance instance = (Attendance) ss.get("com.viksitpro.core.dao.entities.Attendance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Attendance> findByExample(Attendance instance) {
		log.debug("finding Attendance instance by example");
		try {
			List<Attendance> results = (List<Attendance>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Attendance").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Attendance instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Attendance as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Attendance> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Attendance> findByEventId(Object eventId) {
		return findByProperty(EVENT_ID, eventId);
	}

	public List findAll() {
		log.debug("finding all Attendance instances");
		try {
			String queryString = "from Attendance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Attendance merge(Attendance detachedInstance) {
		log.debug("merging Attendance instance");
		try {
			Attendance result = (Attendance) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attendance instance) {
		log.debug("attaching dirty Attendance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attendance instance) {
		log.debug("attaching clean Attendance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}