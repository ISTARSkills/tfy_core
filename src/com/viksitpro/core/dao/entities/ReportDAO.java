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
 * Report entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Report
 * @author MyEclipse Persistence Tools
 */
public class ReportDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ReportDAO.class);
	// property constants
	public static final String PROGRESS = "progress";
	public static final String SCORE = "score";
	public static final String TIME_TAKEN = "timeTaken";
	public static final String POINTS_EARNED = "pointsEarned";
	public static final String TOTAL_POINTS = "totalPoints";
	public static final String RANK = "rank";
	public static final String PERCENTAGE = "percentage";
	public static final String RANK_COUNTRY = "rankCountry";
	public static final String RANK_GLOBE = "rankGlobe";
	public static final String RANK_BATCH = "rankBatch";
	public static final String RANK_ORGANIZATION = "rankOrganization";
	public static final String PERCENTILE_BATCH = "percentileBatch";
	public static final String PERCENTILE_ORGANIZATION = "percentileOrganization";
	public static final String PERCENTILE_GLOBE = "percentileGlobe";
	public static final String PERCENTILE_COUNTRY = "percentileCountry";

	public void save(Report transientInstance) {
		log.debug("saving Report instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Report persistentInstance) {
		log.debug("deleting Report instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Report findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Report instance with id: " + id);
		try {
			Report instance = (Report) ss.get("com.viksitpro.core.dao.entities.Report", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Report> findByExample(Report instance) {
		log.debug("finding Report instance by example");
		try {
			List<Report> results = (List<Report>) getSession().createCriteria("com.viksitpro.core.dao.entities.Report")
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
		log.debug("finding Report instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Report as model where model." + propertyName + "= ?";
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

	public List<Report> findByProgress(Object progress) {
		return findByProperty(PROGRESS, progress);
	}

	public List<Report> findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List<Report> findByTimeTaken(Object timeTaken) {
		return findByProperty(TIME_TAKEN, timeTaken);
	}

	public List<Report> findByPointsEarned(Object pointsEarned) {
		return findByProperty(POINTS_EARNED, pointsEarned);
	}

	public List<Report> findByTotalPoints(Object totalPoints) {
		return findByProperty(TOTAL_POINTS, totalPoints);
	}

	public List<Report> findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}

	public List<Report> findByPercentage(Object percentage) {
		return findByProperty(PERCENTAGE, percentage);
	}

	public List<Report> findByRankCountry(Object rankCountry) {
		return findByProperty(RANK_COUNTRY, rankCountry);
	}

	public List<Report> findByRankGlobe(Object rankGlobe) {
		return findByProperty(RANK_GLOBE, rankGlobe);
	}

	public List<Report> findByRankBatch(Object rankBatch) {
		return findByProperty(RANK_BATCH, rankBatch);
	}

	public List<Report> findByRankOrganization(Object rankOrganization) {
		return findByProperty(RANK_ORGANIZATION, rankOrganization);
	}

	public List<Report> findByPercentileBatch(Object percentileBatch) {
		return findByProperty(PERCENTILE_BATCH, percentileBatch);
	}

	public List<Report> findByPercentileOrganization(Object percentileOrganization) {
		return findByProperty(PERCENTILE_ORGANIZATION, percentileOrganization);
	}

	public List<Report> findByPercentileGlobe(Object percentileGlobe) {
		return findByProperty(PERCENTILE_GLOBE, percentileGlobe);
	}

	public List<Report> findByPercentileCountry(Object percentileCountry) {
		return findByProperty(PERCENTILE_COUNTRY, percentileCountry);
	}

	public List findAll() {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Report merge(Report detachedInstance) {
		log.debug("merging Report instance");
		try {
			Report result = (Report) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Report instance) {
		log.debug("attaching dirty Report instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Report instance) {
		log.debug("attaching clean Report instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}