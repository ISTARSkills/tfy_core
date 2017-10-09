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
 * SkillPointer entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.SkillPointer
 * @author MyEclipse Persistence Tools
 */
public class SkillPointerDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SkillPointerDAO.class);
	// property constants
	public static final String SKILL_ID = "skillId";
	public static final String STUDENT_ID = "studentId";
	public static final String POINTS_EARNED = "pointsEarned";
	public static final String MAX_POINTS = "maxPoints";
	public static final String RANK = "rank";
	public static final String PERCENTILE_BATCH = "percentileBatch";
	public static final String PERCENTILE_ORGANIZATION = "percentileOrganization";
	public static final String PERCENTILE_GLOBE = "percentileGlobe";
	public static final String PERCENTILE_COUNTRY = "percentileCountry";
	public static final String RANK_COUNTRY = "rankCountry";
	public static final String RANK_GLOBE = "rankGlobe";
	public static final String RANK_BATCH = "rankBatch";
	public static final String RANK_ORGANIZATION = "rankOrganization";
	public static final String PERCENTAGE = "percentage";

	public void save(SkillPointer transientInstance) {
		log.debug("saving SkillPointer instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkillPointer persistentInstance) {
		log.debug("deleting SkillPointer instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkillPointer findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting SkillPointer instance with id: " + id);
		try {
			SkillPointer instance = (SkillPointer) ss.get("com.viksitpro.core.dao.entities.SkillPointer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<SkillPointer> findByExample(SkillPointer instance) {
		log.debug("finding SkillPointer instance by example");
		try {
			List<SkillPointer> results = (List<SkillPointer>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.SkillPointer").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding SkillPointer instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from SkillPointer as model where model." + propertyName + "= ?";
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

	public List<SkillPointer> findBySkillId(Object skillId) {
		return findByProperty(SKILL_ID, skillId);
	}

	public List<SkillPointer> findByStudentId(Object studentId) {
		return findByProperty(STUDENT_ID, studentId);
	}

	public List<SkillPointer> findByPointsEarned(Object pointsEarned) {
		return findByProperty(POINTS_EARNED, pointsEarned);
	}

	public List<SkillPointer> findByMaxPoints(Object maxPoints) {
		return findByProperty(MAX_POINTS, maxPoints);
	}

	public List<SkillPointer> findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}

	public List<SkillPointer> findByPercentileBatch(Object percentileBatch) {
		return findByProperty(PERCENTILE_BATCH, percentileBatch);
	}

	public List<SkillPointer> findByPercentileOrganization(Object percentileOrganization) {
		return findByProperty(PERCENTILE_ORGANIZATION, percentileOrganization);
	}

	public List<SkillPointer> findByPercentileGlobe(Object percentileGlobe) {
		return findByProperty(PERCENTILE_GLOBE, percentileGlobe);
	}

	public List<SkillPointer> findByPercentileCountry(Object percentileCountry) {
		return findByProperty(PERCENTILE_COUNTRY, percentileCountry);
	}

	public List<SkillPointer> findByRankCountry(Object rankCountry) {
		return findByProperty(RANK_COUNTRY, rankCountry);
	}

	public List<SkillPointer> findByRankGlobe(Object rankGlobe) {
		return findByProperty(RANK_GLOBE, rankGlobe);
	}

	public List<SkillPointer> findByRankBatch(Object rankBatch) {
		return findByProperty(RANK_BATCH, rankBatch);
	}

	public List<SkillPointer> findByRankOrganization(Object rankOrganization) {
		return findByProperty(RANK_ORGANIZATION, rankOrganization);
	}

	public List<SkillPointer> findByPercentage(Object percentage) {
		return findByProperty(PERCENTAGE, percentage);
	}

	public List findAll() {
		log.debug("finding all SkillPointer instances");
		try {
			String queryString = "from SkillPointer";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkillPointer merge(SkillPointer detachedInstance) {
		log.debug("merging SkillPointer instance");
		try {
			SkillPointer result = (SkillPointer) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkillPointer instance) {
		log.debug("attaching dirty SkillPointer instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkillPointer instance) {
		log.debug("attaching clean SkillPointer instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}