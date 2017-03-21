package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BatchGroup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.BatchGroup
 * @author MyEclipse Persistence Tools
 */
public class BatchGroupDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BatchGroupDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String BATCH_CODE = "batchCode";
	public static final String ASSESSMENT_ID = "assessmentId";
	public static final String BG_DESC = "bgDesc";

	public void save(BatchGroup transientInstance) {
		log.debug("saving BatchGroup instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BatchGroup persistentInstance) {
		log.debug("deleting BatchGroup instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BatchGroup findById(java.lang.Integer id) {
		log.debug("getting BatchGroup instance with id: " + id);
		try {
			BatchGroup instance = (BatchGroup) getSession().get("com.viksitpro.core.dao.entities.BatchGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BatchGroup> findByExample(BatchGroup instance) {
		log.debug("finding BatchGroup instance by example");
		try {
			List<BatchGroup> results = (List<BatchGroup>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.BatchGroup").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BatchGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from BatchGroup as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BatchGroup> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<BatchGroup> findByBatchCode(Object batchCode) {
		return findByProperty(BATCH_CODE, batchCode);
	}

	public List<BatchGroup> findByAssessmentId(Object assessmentId) {
		return findByProperty(ASSESSMENT_ID, assessmentId);
	}

	public List<BatchGroup> findByBgDesc(Object bgDesc) {
		return findByProperty(BG_DESC, bgDesc);
	}

	public List findAll() {
		log.debug("finding all BatchGroup instances");
		try {
			String queryString = "from BatchGroup";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BatchGroup merge(BatchGroup detachedInstance) {
		log.debug("merging BatchGroup instance");
		try {
			BatchGroup result = (BatchGroup) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BatchGroup instance) {
		log.debug("attaching dirty BatchGroup instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BatchGroup instance) {
		log.debug("attaching clean BatchGroup instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}