package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * SkillObjective entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.SkillObjective
 * @author MyEclipse Persistence Tools
 */
public class SkillObjectiveDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SkillObjectiveDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String NAME = "name";
	public static final String PARENT_SKILL = "parentSkill";
	public static final String SKILL_LEVEL_TYPE = "skillLevelType";

	public void save(SkillObjective transientInstance) {
		log.debug("saving SkillObjective instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkillObjective persistentInstance) {
		log.debug("deleting SkillObjective instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkillObjective findById(java.lang.Integer id) {
		log.debug("getting SkillObjective instance with id: " + id);
		try {
			SkillObjective instance = (SkillObjective) getSession()
					.get("com.viksitpro.core.dao.entities.SkillObjective", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkillObjective> findByExample(SkillObjective instance) {
		log.debug("finding SkillObjective instance by example");
		try {
			List<SkillObjective> results = (List<SkillObjective>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.SkillObjective").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SkillObjective instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from SkillObjective as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SkillObjective> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<SkillObjective> findByName(Object name) {
		return findByProperty(NAME, name);
	}
	
	public List<SkillObjective> findByParentSkill(Object parentSkill) {
		return findByProperty(PARENT_SKILL, parentSkill);
	}
	
	public List<SkillObjective> findBySkillLevelType(Object skillLevelType) {
		return findByProperty(SKILL_LEVEL_TYPE, skillLevelType);
	}

	public List<SkillObjective> findAll() {
		log.debug("finding all SkillObjective instances");
		try {
			String queryString = "from SkillObjective";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkillObjective merge(SkillObjective detachedInstance) {
		log.debug("merging SkillObjective instance");
		try {
			SkillObjective result = (SkillObjective) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkillObjective instance) {
		log.debug("attaching dirty SkillObjective instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkillObjective instance) {
		log.debug("attaching clean SkillObjective instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}