package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Metadata entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Metadata
 * @author MyEclipse Persistence Tools
 */
public class MetadataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MetadataDAO.class);
	// property constants
	public static final String ENTITY_ID = "entityId";
	public static final String ENTITY_TYPE = "entityType";
	public static final String VALUE = "value";

	public void save(Metadata transientInstance) {
		log.debug("saving Metadata instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Metadata persistentInstance) {
		log.debug("deleting Metadata instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Metadata findById(java.lang.Integer id) {
		log.debug("getting Metadata instance with id: " + id);
		try {
			Metadata instance = (Metadata) getSession().get("com.viksitpro.core.dao.entities.Metadata", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Metadata> findByExample(Metadata instance) {
		log.debug("finding Metadata instance by example");
		try {
			List<Metadata> results = (List<Metadata>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Metadata").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Metadata instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Metadata as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Metadata> findByEntityId(Object entityId) {
		return findByProperty(ENTITY_ID, entityId);
	}

	public List<Metadata> findByEntityType(Object entityType) {
		return findByProperty(ENTITY_TYPE, entityType);
	}

	public List<Metadata> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all Metadata instances");
		try {
			String queryString = "from Metadata";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Metadata merge(Metadata detachedInstance) {
		log.debug("merging Metadata instance");
		try {
			Metadata result = (Metadata) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Metadata instance) {
		log.debug("attaching dirty Metadata instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Metadata instance) {
		log.debug("attaching clean Metadata instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}