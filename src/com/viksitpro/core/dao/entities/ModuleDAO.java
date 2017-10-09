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
 * Module entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Module
 * @author MyEclipse Persistence Tools
 */
public class ModuleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ModuleDAO.class);
	// property constants
	public static final String MODULE_NAME = "moduleName";
	public static final String ORDER_ID = "orderId";
	public static final String IS_DELETED = "isDeleted";

	public void save(Module transientInstance) {
		log.debug("saving Module instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Module persistentInstance) {
		log.debug("deleting Module instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Module findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Module instance with id: " + id);
		try {
			Module instance = (Module) ss.get("com.viksitpro.core.dao.entities.Module", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Module> findByExample(Module instance) {
		log.debug("finding Module instance by example");
		try {
			List<Module> results = (List<Module>) getSession().createCriteria("com.viksitpro.core.dao.entities.Module")
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
		log.debug("finding Module instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Module as model where model." + propertyName + "= ?";
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

	public List<Module> findByModuleName(Object moduleName) {
		return findByProperty(MODULE_NAME, moduleName);
	}

	public List<Module> findByOrderId(Object orderId) {
		return findByProperty(ORDER_ID, orderId);
	}

	public List<Module> findByIsDeleted(Object isDeleted) {
		return findByProperty(IS_DELETED, isDeleted);
	}

	public List findAll() {
		log.debug("finding all Module instances");
		try {
			String queryString = "from Module";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Module merge(Module detachedInstance) {
		log.debug("merging Module instance");
		try {
			Module result = (Module) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Module instance) {
		log.debug("attaching dirty Module instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Module instance) {
		log.debug("attaching clean Module instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}