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
 * StudentAssessment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.StudentAssessment
 * @author MyEclipse Persistence Tools
 */
public class StudentAssessmentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StudentAssessmentDAO.class);
	// property constants
	public static final String CORRECT = "correct";
	public static final String OPTION1 = "option1";
	public static final String OPTION2 = "option2";
	public static final String OPTION3 = "option3";
	public static final String OPTION4 = "option4";
	public static final String OPTION5 = "option5";
	public static final String COUNTRY_ID = "countryId";
	public static final String ORGANIZATION_ID = "organizationId";
	public static final String BATCH_GROUP_ID = "batchGroupId";
	public static final String TIME_TAKEN = "timeTaken";

	public void save(StudentAssessment transientInstance) {
		log.debug("saving StudentAssessment instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StudentAssessment persistentInstance) {
		log.debug("deleting StudentAssessment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StudentAssessment findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting StudentAssessment instance with id: " + id);
		try {
			StudentAssessment instance = (StudentAssessment) ss
					.get("com.viksitpro.core.dao.entities.StudentAssessment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<StudentAssessment> findByExample(StudentAssessment instance) {
		log.debug("finding StudentAssessment instance by example");
		try {
			List<StudentAssessment> results = (List<StudentAssessment>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.StudentAssessment").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding StudentAssessment instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from StudentAssessment as model where model." + propertyName + "= ?";
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

	public List<StudentAssessment> findByCorrect(Object correct) {
		return findByProperty(CORRECT, correct);
	}

	public List<StudentAssessment> findByOption1(Object option1) {
		return findByProperty(OPTION1, option1);
	}

	public List<StudentAssessment> findByOption2(Object option2) {
		return findByProperty(OPTION2, option2);
	}

	public List<StudentAssessment> findByOption3(Object option3) {
		return findByProperty(OPTION3, option3);
	}

	public List<StudentAssessment> findByOption4(Object option4) {
		return findByProperty(OPTION4, option4);
	}

	public List<StudentAssessment> findByOption5(Object option5) {
		return findByProperty(OPTION5, option5);
	}

	public List<StudentAssessment> findByCountryId(Object countryId) {
		return findByProperty(COUNTRY_ID, countryId);
	}

	public List<StudentAssessment> findByOrganizationId(Object organizationId) {
		return findByProperty(ORGANIZATION_ID, organizationId);
	}

	public List<StudentAssessment> findByBatchGroupId(Object batchGroupId) {
		return findByProperty(BATCH_GROUP_ID, batchGroupId);
	}

	public List<StudentAssessment> findByTimeTaken(Object timeTaken) {
		return findByProperty(TIME_TAKEN, timeTaken);
	}

	public List findAll() {
		log.debug("finding all StudentAssessment instances");
		try {
			String queryString = "from StudentAssessment";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StudentAssessment merge(StudentAssessment detachedInstance) {
		log.debug("merging StudentAssessment instance");
		try {
			StudentAssessment result = (StudentAssessment) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StudentAssessment instance) {
		log.debug("attaching dirty StudentAssessment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StudentAssessment instance) {
		log.debug("attaching clean StudentAssessment instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}