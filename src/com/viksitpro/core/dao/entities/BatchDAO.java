package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Batch
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Batch
 * @author MyEclipse Persistence Tools
 */
public class BatchDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BatchDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ORDER_ID = "orderId";

	public void save(Batch transientInstance) {
		log.debug("saving Batch instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Batch persistentInstance) {
		log.debug("deleting Batch instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Batch findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Batch instance with id: " + id);
		try {
			Batch instance = (Batch) ss.get("com.viksitpro.core.dao.entities.Batch", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Batch> findByExample(Batch instance) {
		log.debug("finding Batch instance by example");
		try {
			List<Batch> results = (List<Batch>) getSession().createCriteria("com.viksitpro.core.dao.entities.Batch")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding Batch instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Batch as model where model." + propertyName + "= ?";
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

	public List<Batch> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Batch> findByOrderId(Object orderId) {
		return findByProperty(ORDER_ID, orderId);
	}

	public List findAll() {
		log.debug("finding all Batch instances");
		try {
			String queryString = "from Batch";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Batch merge(Batch detachedInstance) {
		log.debug("merging Batch instance");
		try {
			Batch result = (Batch) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Batch instance) {
		log.debug("attaching dirty Batch instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Batch instance) {
		log.debug("attaching clean Batch instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}