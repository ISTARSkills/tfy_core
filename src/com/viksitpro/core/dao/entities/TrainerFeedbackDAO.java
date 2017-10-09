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
 * TrainerFeedback entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.TrainerFeedback
 * @author MyEclipse Persistence Tools
 */
public class TrainerFeedbackDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TrainerFeedbackDAO.class);
	// property constants
	public static final String EVENT_ID = "eventId";
	public static final String RATING = "rating";
	public static final String NOISE = "noise";
	public static final String ATTENDANCE = "attendance";
	public static final String SICK = "sick";
	public static final String CONTENT = "content";
	public static final String ASSIGNMENT = "assignment";
	public static final String INTERNALS = "internals";
	public static final String INTERNET = "internet";
	public static final String ELECTRICITY = "electricity";
	public static final String TIME = "time";
	public static final String COMMENTS = "comments";

	public void save(TrainerFeedback transientInstance) {
		log.debug("saving TrainerFeedback instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TrainerFeedback persistentInstance) {
		log.debug("deleting TrainerFeedback instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TrainerFeedback findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting TrainerFeedback instance with id: " + id);
		try {
			TrainerFeedback instance = (TrainerFeedback) ss
					.get("com.viksitpro.core.dao.entities.TrainerFeedback", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<TrainerFeedback> findByExample(TrainerFeedback instance) {
		log.debug("finding TrainerFeedback instance by example");
		try {
			List<TrainerFeedback> results = (List<TrainerFeedback>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.TrainerFeedback").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding TrainerFeedback instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TrainerFeedback as model where model." + propertyName + "= ?";
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

	public List<TrainerFeedback> findByEventId(Object eventId) {
		return findByProperty(EVENT_ID, eventId);
	}

	public List<TrainerFeedback> findByRating(Object rating) {
		return findByProperty(RATING, rating);
	}

	public List<TrainerFeedback> findByNoise(Object noise) {
		return findByProperty(NOISE, noise);
	}

	public List<TrainerFeedback> findByAttendance(Object attendance) {
		return findByProperty(ATTENDANCE, attendance);
	}

	public List<TrainerFeedback> findBySick(Object sick) {
		return findByProperty(SICK, sick);
	}

	public List<TrainerFeedback> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<TrainerFeedback> findByAssignment(Object assignment) {
		return findByProperty(ASSIGNMENT, assignment);
	}

	public List<TrainerFeedback> findByInternals(Object internals) {
		return findByProperty(INTERNALS, internals);
	}

	public List<TrainerFeedback> findByInternet(Object internet) {
		return findByProperty(INTERNET, internet);
	}

	public List<TrainerFeedback> findByElectricity(Object electricity) {
		return findByProperty(ELECTRICITY, electricity);
	}

	public List<TrainerFeedback> findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List<TrainerFeedback> findByComments(Object comments) {
		return findByProperty(COMMENTS, comments);
	}

	public List findAll() {
		log.debug("finding all TrainerFeedback instances");
		try {
			String queryString = "from TrainerFeedback";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TrainerFeedback merge(TrainerFeedback detachedInstance) {
		log.debug("merging TrainerFeedback instance");
		try {
			TrainerFeedback result = (TrainerFeedback) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TrainerFeedback instance) {
		log.debug("attaching dirty TrainerFeedback instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TrainerFeedback instance) {
		log.debug("attaching clean TrainerFeedback instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}