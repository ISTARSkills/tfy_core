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
 * FolderItems entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.FolderItems
 * @author MyEclipse Persistence Tools
 */
public class FolderItemsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(FolderItemsDAO.class);
	// property constants
	public static final String ITEM_ID = "itemId";
	public static final String ITEM_TYPE = "itemType";

	public void save(FolderItems transientInstance) {
		log.debug("saving FolderItems instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FolderItems persistentInstance) {
		log.debug("deleting FolderItems instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FolderItems findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting FolderItems instance with id: " + id);
		try {
			FolderItems instance = (FolderItems) ss.get("com.viksitpro.core.dao.entities.FolderItems", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<FolderItems> findByExample(FolderItems instance) {
		log.debug("finding FolderItems instance by example");
		try {
			List<FolderItems> results = (List<FolderItems>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.FolderItems").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding FolderItems instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from FolderItems as model where model." + propertyName + "= ?";
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

	public List<FolderItems> findByItemId(Object itemId) {
		return findByProperty(ITEM_ID, itemId);
	}

	public List<FolderItems> findByItemType(Object itemType) {
		return findByProperty(ITEM_TYPE, itemType);
	}

	public List findAll() {
		log.debug("finding all FolderItems instances");
		try {
			String queryString = "from FolderItems";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FolderItems merge(FolderItems detachedInstance) {
		log.debug("merging FolderItems instance");
		try {
			FolderItems result = (FolderItems) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FolderItems instance) {
		log.debug("attaching dirty FolderItems instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FolderItems instance) {
		log.debug("attaching clean FolderItems instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}