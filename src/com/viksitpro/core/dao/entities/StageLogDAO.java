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
 * StageLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.StageLog
 * @author MyEclipse Persistence Tools
 */
public class StageLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StageLogDAO.class);
	// property constants
	public static final String STAGE_NAME = "stageName";
	public static final String STAGE_TYPE = "stageType";
	public static final String STATUS = "status";
	public static final String ACTION_ID = "actionId";
	public static final String ACTION_PASSWORD = "actionPassword";
	public static final String RESULT = "result";
	public static final String URL0 = "url0";
	public static final String URL1 = "url1";
	public static final String ACTION = "action";
	public static final String URL_CODE = "urlCode";

	public void save(StageLog transientInstance) {
		log.debug("saving StageLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StageLog persistentInstance) {
		log.debug("deleting StageLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StageLog findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting StageLog instance with id: " + id);
		try {
			StageLog instance = (StageLog) ss.get("com.viksitpro.core.dao.entities.StageLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<StageLog> findByExample(StageLog instance) {
		log.debug("finding StageLog instance by example");
		try {
			List<StageLog> results = (List<StageLog>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.StageLog").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding StageLog instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from StageLog as model where model." + propertyName + "= ?";
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

	public List<StageLog> findByStageName(Object stageName) {
		return findByProperty(STAGE_NAME, stageName);
	}

	public List<StageLog> findByStageType(Object stageType) {
		return findByProperty(STAGE_TYPE, stageType);
	}

	public List<StageLog> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<StageLog> findByActionId(Object actionId) {
		return findByProperty(ACTION_ID, actionId);
	}

	public List<StageLog> findByActionPassword(Object actionPassword) {
		return findByProperty(ACTION_PASSWORD, actionPassword);
	}

	public List<StageLog> findByResult(Object result) {
		return findByProperty(RESULT, result);
	}

	public List<StageLog> findByUrl0(Object url0) {
		return findByProperty(URL0, url0);
	}

	public List<StageLog> findByUrl1(Object url1) {
		return findByProperty(URL1, url1);
	}

	public List<StageLog> findByAction(Object action) {
		return findByProperty(ACTION, action);
	}

	public List<StageLog> findByUrlCode(Object urlCode) {
		return findByProperty(URL_CODE, urlCode);
	}

	public List findAll() {
		log.debug("finding all StageLog instances");
		try {
			String queryString = "from StageLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StageLog merge(StageLog detachedInstance) {
		log.debug("merging StageLog instance");
		try {
			StageLog result = (StageLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StageLog instance) {
		log.debug("attaching dirty StageLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StageLog instance) {
		log.debug("attaching clean StageLog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}