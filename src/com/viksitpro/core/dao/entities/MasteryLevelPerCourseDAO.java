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
 * MasteryLevelPerCourse entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.MasteryLevelPerCourse
 * @author MyEclipse Persistence Tools
 */
public class MasteryLevelPerCourseDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MasteryLevelPerCourseDAO.class);
	// property constants
	public static final String COURSE_ID = "courseId";
	public static final String COLLEGE_ID = "collegeId";
	public static final String BATCH_GROUP_ID = "batchGroupId";
	public static final String SKILL_ID = "skillId";
	public static final String MASTER = "master";
	public static final String ROOKIE = "rookie";
	public static final String APPRENTICE = "apprentice";
	public static final String WIZARD = "wizard";

	public void save(MasteryLevelPerCourse transientInstance) {
		log.debug("saving MasteryLevelPerCourse instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MasteryLevelPerCourse persistentInstance) {
		log.debug("deleting MasteryLevelPerCourse instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MasteryLevelPerCourse findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting MasteryLevelPerCourse instance with id: " + id);
		try {
			MasteryLevelPerCourse instance = (MasteryLevelPerCourse) ss
					.get("com.viksitpro.core.dao.entities.MasteryLevelPerCourse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<MasteryLevelPerCourse> findByExample(MasteryLevelPerCourse instance) {
		log.debug("finding MasteryLevelPerCourse instance by example");
		try {
			List<MasteryLevelPerCourse> results = (List<MasteryLevelPerCourse>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.MasteryLevelPerCourse").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding MasteryLevelPerCourse instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from MasteryLevelPerCourse as model where model." + propertyName + "= ?";
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

	public List<MasteryLevelPerCourse> findByCourseId(Object courseId) {
		return findByProperty(COURSE_ID, courseId);
	}

	public List<MasteryLevelPerCourse> findByCollegeId(Object collegeId) {
		return findByProperty(COLLEGE_ID, collegeId);
	}

	public List<MasteryLevelPerCourse> findByBatchGroupId(Object batchGroupId) {
		return findByProperty(BATCH_GROUP_ID, batchGroupId);
	}

	public List<MasteryLevelPerCourse> findBySkillId(Object skillId) {
		return findByProperty(SKILL_ID, skillId);
	}

	public List<MasteryLevelPerCourse> findByMaster(Object master) {
		return findByProperty(MASTER, master);
	}

	public List<MasteryLevelPerCourse> findByRookie(Object rookie) {
		return findByProperty(ROOKIE, rookie);
	}

	public List<MasteryLevelPerCourse> findByApprentice(Object apprentice) {
		return findByProperty(APPRENTICE, apprentice);
	}

	public List<MasteryLevelPerCourse> findByWizard(Object wizard) {
		return findByProperty(WIZARD, wizard);
	}

	public List findAll() {
		log.debug("finding all MasteryLevelPerCourse instances");
		try {
			String queryString = "from MasteryLevelPerCourse";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MasteryLevelPerCourse merge(MasteryLevelPerCourse detachedInstance) {
		log.debug("merging MasteryLevelPerCourse instance");
		try {
			MasteryLevelPerCourse result = (MasteryLevelPerCourse) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MasteryLevelPerCourse instance) {
		log.debug("attaching dirty MasteryLevelPerCourse instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MasteryLevelPerCourse instance) {
		log.debug("attaching clean MasteryLevelPerCourse instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}