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
 * SkillPrecentile entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.SkillPrecentile
 * @author MyEclipse Persistence Tools
 */
public class SkillPrecentileDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SkillPrecentileDAO.class);
	// property constants
	public static final String STUDENT_ID = "studentId";
	public static final String SKILL_ID = "skillId";
	public static final String PERCENTILE_COUNTRY = "percentileCountry";
	public static final String PERCENTILE_GLOBE = "percentileGlobe";
	public static final String PERCENTILE_BATCH = "percentileBatch";
	public static final String PERCENTILE_ORGANIZATION = "percentileOrganization";
	public static final String ACCURACY = "accuracy";
	public static final String INDUSTRY_BENCHMARK = "industryBenchmark";
	public static final String POINTS_EARNED = "pointsEarned";
	public static final String MAX_POINTS = "maxPoints";
	public static final String RANK_COUNTRY = "rankCountry";
	public static final String RANK_GLOBE = "rankGlobe";
	public static final String RANK_BATCH = "rankBatch";
	public static final String RANK_ORGANIZATION = "rankOrganization";
	public static final String PERCENTAGE = "percentage";

	public void save(SkillPrecentile transientInstance) {
		log.debug("saving SkillPrecentile instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkillPrecentile persistentInstance) {
		log.debug("deleting SkillPrecentile instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkillPrecentile findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting SkillPrecentile instance with id: " + id);
		try {
			SkillPrecentile instance = (SkillPrecentile) ss
					.get("com.viksitpro.core.dao.entities.SkillPrecentile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<SkillPrecentile> findByExample(SkillPrecentile instance) {
		log.debug("finding SkillPrecentile instance by example");
		try {
			List<SkillPrecentile> results = (List<SkillPrecentile>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.SkillPrecentile").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding SkillPrecentile instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from SkillPrecentile as model where model." + propertyName + "= ?";
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

	public List<SkillPrecentile> findByStudentId(Object studentId) {
		return findByProperty(STUDENT_ID, studentId);
	}

	public List<SkillPrecentile> findBySkillId(Object skillId) {
		return findByProperty(SKILL_ID, skillId);
	}

	public List<SkillPrecentile> findByPercentileCountry(Object percentileCountry) {
		return findByProperty(PERCENTILE_COUNTRY, percentileCountry);
	}

	public List<SkillPrecentile> findByPercentileGlobe(Object percentileGlobe) {
		return findByProperty(PERCENTILE_GLOBE, percentileGlobe);
	}

	public List<SkillPrecentile> findByPercentileBatch(Object percentileBatch) {
		return findByProperty(PERCENTILE_BATCH, percentileBatch);
	}

	public List<SkillPrecentile> findByPercentileOrganization(Object percentileOrganization) {
		return findByProperty(PERCENTILE_ORGANIZATION, percentileOrganization);
	}

	public List<SkillPrecentile> findByAccuracy(Object accuracy) {
		return findByProperty(ACCURACY, accuracy);
	}

	public List<SkillPrecentile> findByIndustryBenchmark(Object industryBenchmark) {
		return findByProperty(INDUSTRY_BENCHMARK, industryBenchmark);
	}

	public List<SkillPrecentile> findByPointsEarned(Object pointsEarned) {
		return findByProperty(POINTS_EARNED, pointsEarned);
	}

	public List<SkillPrecentile> findByMaxPoints(Object maxPoints) {
		return findByProperty(MAX_POINTS, maxPoints);
	}

	public List<SkillPrecentile> findByRankCountry(Object rankCountry) {
		return findByProperty(RANK_COUNTRY, rankCountry);
	}

	public List<SkillPrecentile> findByRankGlobe(Object rankGlobe) {
		return findByProperty(RANK_GLOBE, rankGlobe);
	}

	public List<SkillPrecentile> findByRankBatch(Object rankBatch) {
		return findByProperty(RANK_BATCH, rankBatch);
	}

	public List<SkillPrecentile> findByRankOrganization(Object rankOrganization) {
		return findByProperty(RANK_ORGANIZATION, rankOrganization);
	}

	public List<SkillPrecentile> findByPercentage(Object percentage) {
		return findByProperty(PERCENTAGE, percentage);
	}

	public List findAll() {
		log.debug("finding all SkillPrecentile instances");
		try {
			String queryString = "from SkillPrecentile";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkillPrecentile merge(SkillPrecentile detachedInstance) {
		log.debug("merging SkillPrecentile instance");
		try {
			SkillPrecentile result = (SkillPrecentile) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkillPrecentile instance) {
		log.debug("attaching dirty SkillPrecentile instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkillPrecentile instance) {
		log.debug("attaching clean SkillPrecentile instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}