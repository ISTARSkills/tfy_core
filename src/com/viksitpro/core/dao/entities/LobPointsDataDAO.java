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
 * LobPointsData entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.LobPointsData
 * @author MyEclipse Persistence Tools
 */
public class LobPointsDataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(LobPointsDataDAO.class);
	// property constants
	public static final String LOBJ_ID = "lobjId";
	public static final String STUDENT_ID = "studentId";
	public static final String POINTS_EARNED = "pointsEarned";
	public static final String MAX_POINTS = "maxPoints";

	public void save(LobPointsData transientInstance) {
		log.debug("saving LobPointsData instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LobPointsData persistentInstance) {
		log.debug("deleting LobPointsData instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LobPointsData findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting LobPointsData instance with id: " + id);
		try {
			LobPointsData instance = (LobPointsData) ss.get("com.viksitpro.core.dao.entities.LobPointsData",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<LobPointsData> findByExample(LobPointsData instance) {
		log.debug("finding LobPointsData instance by example");
		try {
			List<LobPointsData> results = (List<LobPointsData>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.LobPointsData").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding LobPointsData instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from LobPointsData as model where model." + propertyName + "= ?";
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

	public List<LobPointsData> findByLobjId(Object lobjId) {
		return findByProperty(LOBJ_ID, lobjId);
	}

	public List<LobPointsData> findByStudentId(Object studentId) {
		return findByProperty(STUDENT_ID, studentId);
	}

	public List<LobPointsData> findByPointsEarned(Object pointsEarned) {
		return findByProperty(POINTS_EARNED, pointsEarned);
	}

	public List<LobPointsData> findByMaxPoints(Object maxPoints) {
		return findByProperty(MAX_POINTS, maxPoints);
	}

	public List findAll() {
		log.debug("finding all LobPointsData instances");
		try {
			String queryString = "from LobPointsData";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LobPointsData merge(LobPointsData detachedInstance) {
		log.debug("merging LobPointsData instance");
		try {
			LobPointsData result = (LobPointsData) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LobPointsData instance) {
		log.debug("attaching dirty LobPointsData instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LobPointsData instance) {
		log.debug("attaching clean LobPointsData instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}