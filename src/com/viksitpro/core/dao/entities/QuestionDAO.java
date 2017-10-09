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
 * Question entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Question
 * @author MyEclipse Persistence Tools
 */
public class QuestionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(QuestionDAO.class);
	// property constants
	public static final String QUESTION_TEXT = "questionText";
	public static final String QUESTION_TYPE = "questionType";
	public static final String DIFFICULTY_LEVEL = "difficultyLevel";
	public static final String SPECIFIER = "specifier";
	public static final String DURATION_IN_SEC = "durationInSec";
	public static final String EXPLANATION = "explanation";
	public static final String COMPREHENSIVE_PASSAGE_TEXT = "comprehensivePassageText";
	public static final String POINTS = "points";

	public void save(Question transientInstance) {
		log.debug("saving Question instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Question persistentInstance) {
		log.debug("deleting Question instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Question findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Question instance with id: " + id);
		try {
			Question instance = (Question) ss.get("com.viksitpro.core.dao.entities.Question", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Question> findByExample(Question instance) {
		log.debug("finding Question instance by example");
		try {
			List<Question> results = (List<Question>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Question").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding Question instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Question as model where model." + propertyName + "= ?";
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

	public List<Question> findByQuestionText(Object questionText) {
		return findByProperty(QUESTION_TEXT, questionText);
	}

	public List<Question> findByQuestionType(Object questionType) {
		return findByProperty(QUESTION_TYPE, questionType);
	}

	public List<Question> findByDifficultyLevel(Object difficultyLevel) {
		return findByProperty(DIFFICULTY_LEVEL, difficultyLevel);
	}

	public List<Question> findBySpecifier(Object specifier) {
		return findByProperty(SPECIFIER, specifier);
	}

	public List<Question> findByDurationInSec(Object durationInSec) {
		return findByProperty(DURATION_IN_SEC, durationInSec);
	}

	public List<Question> findByExplanation(Object explanation) {
		return findByProperty(EXPLANATION, explanation);
	}

	public List<Question> findByComprehensivePassageText(Object comprehensivePassageText) {
		return findByProperty(COMPREHENSIVE_PASSAGE_TEXT, comprehensivePassageText);
	}

	public List<Question> findByPoints(Object points) {
		return findByProperty(POINTS, points);
	}

	public List findAll() {
		log.debug("finding all Question instances");
		try {
			String queryString = "from Question";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Question merge(Question detachedInstance) {
		log.debug("merging Question instance");
		try {
			Question result = (Question) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Question instance) {
		log.debug("attaching dirty Question instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Question instance) {
		log.debug("attaching clean Question instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}