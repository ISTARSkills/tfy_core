package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * LobjStudentAggregate entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.LobjStudentAggregate
 * @author MyEclipse Persistence Tools
 */
public class LobjStudentAggregateDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(LobjStudentAggregateDAO.class);
	// property constants
	public static final String LOBJ_ID = "lobjId";
	public static final String STUDENT_ID = "studentId";
	public static final String BATCH_RANK = "batchRank";
	public static final String COUNTRY_RANK = "countryRank";
	public static final String GLOBE_RANK = "globeRank";
	public static final String ORGANIZATION_RANK = "organizationRank";
	public static final String PERCENTILE_BATCH = "percentileBatch";
	public static final String PERCENTILE_ORGANIZATION = "percentileOrganization";
	public static final String PERCENTILE_COUNTRY = "percentileCountry";
	public static final String PERCENTILE_GLOBE = "percentileGlobe";
	public static final String TOTAL_POINTS = "totalPoints";
	public static final String MAX_POINTS = "maxPoints";
	public static final String PERCENTAGE = "percentage";

	public void save(LobjStudentAggregate transientInstance) {
		log.debug("saving LobjStudentAggregate instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LobjStudentAggregate persistentInstance) {
		log.debug("deleting LobjStudentAggregate instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LobjStudentAggregate findById(java.lang.Integer id) {
		log.debug("getting LobjStudentAggregate instance with id: " + id);
		try {
			LobjStudentAggregate instance = (LobjStudentAggregate) getSession()
					.get("com.viksitpro.core.dao.entities.LobjStudentAggregate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<LobjStudentAggregate> findByExample(LobjStudentAggregate instance) {
		log.debug("finding LobjStudentAggregate instance by example");
		try {
			List<LobjStudentAggregate> results = (List<LobjStudentAggregate>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.LobjStudentAggregate").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding LobjStudentAggregate instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from LobjStudentAggregate as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<LobjStudentAggregate> findByLobjId(Object lobjId) {
		return findByProperty(LOBJ_ID, lobjId);
	}

	public List<LobjStudentAggregate> findByStudentId(Object studentId) {
		return findByProperty(STUDENT_ID, studentId);
	}

	public List<LobjStudentAggregate> findByBatchRank(Object batchRank) {
		return findByProperty(BATCH_RANK, batchRank);
	}

	public List<LobjStudentAggregate> findByCountryRank(Object countryRank) {
		return findByProperty(COUNTRY_RANK, countryRank);
	}

	public List<LobjStudentAggregate> findByGlobeRank(Object globeRank) {
		return findByProperty(GLOBE_RANK, globeRank);
	}

	public List<LobjStudentAggregate> findByOrganizationRank(Object organizationRank) {
		return findByProperty(ORGANIZATION_RANK, organizationRank);
	}

	public List<LobjStudentAggregate> findByPercentileBatch(Object percentileBatch) {
		return findByProperty(PERCENTILE_BATCH, percentileBatch);
	}

	public List<LobjStudentAggregate> findByPercentileOrganization(Object percentileOrganization) {
		return findByProperty(PERCENTILE_ORGANIZATION, percentileOrganization);
	}

	public List<LobjStudentAggregate> findByPercentileCountry(Object percentileCountry) {
		return findByProperty(PERCENTILE_COUNTRY, percentileCountry);
	}

	public List<LobjStudentAggregate> findByPercentileGlobe(Object percentileGlobe) {
		return findByProperty(PERCENTILE_GLOBE, percentileGlobe);
	}

	public List<LobjStudentAggregate> findByTotalPoints(Object totalPoints) {
		return findByProperty(TOTAL_POINTS, totalPoints);
	}

	public List<LobjStudentAggregate> findByMaxPoints(Object maxPoints) {
		return findByProperty(MAX_POINTS, maxPoints);
	}

	public List<LobjStudentAggregate> findByPercentage(Object percentage) {
		return findByProperty(PERCENTAGE, percentage);
	}

	public List findAll() {
		log.debug("finding all LobjStudentAggregate instances");
		try {
			String queryString = "from LobjStudentAggregate";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LobjStudentAggregate merge(LobjStudentAggregate detachedInstance) {
		log.debug("merging LobjStudentAggregate instance");
		try {
			LobjStudentAggregate result = (LobjStudentAggregate) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LobjStudentAggregate instance) {
		log.debug("attaching dirty LobjStudentAggregate instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LobjStudentAggregate instance) {
		log.debug("attaching clean LobjStudentAggregate instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}