package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProfessionalProfile entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.ProfessionalProfile
 * @author MyEclipse Persistence Tools
 */
public class ProfessionalProfileDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ProfessionalProfileDAO.class);
	// property constants
	public static final String YOP10 = "yop10";
	public static final String MARKS10 = "marks10";
	public static final String YOP12 = "yop12";
	public static final String MARKS12 = "marks12";
	public static final String HAS_UNDER_GRADUATION = "hasUnderGraduation";
	public static final String UNDER_GRADUATION_SPECIALIZATION_NAME = "underGraduationSpecializationName";
	public static final String UNDER_GRADUTION_MARKS = "underGradutionMarks";
	public static final String HAS_POST_GRADUATION = "hasPostGraduation";
	public static final String POST_GRADUATION_SPECIALIZATION_NAME = "postGraduationSpecializationName";
	public static final String POST_GRADUTION_MARKS = "postGradutionMarks";
	public static final String IS_STUDYING_FURTHER_AFTER_DEGREE = "isStudyingFurtherAfterDegree";
	public static final String JOB_SECTOR = "jobSector";
	public static final String PREFERRED_LOCATION = "preferredLocation";
	public static final String COMPANY_NAME = "companyName";
	public static final String POSITION = "position";
	public static final String DURATION = "duration";
	public static final String DESCRIPTION = "description";
	public static final String INTERESTED_IN_TYPE_OF_COURSE = "interestedInTypeOfCourse";
	public static final String AREA_OF_INTEREST = "areaOfInterest";
	public static final String MARKSHEET10 = "marksheet10";
	public static final String MARKSHEET12 = "marksheet12";
	public static final String UNDER_GRADUATE_DEGREE_NAME = "underGraduateDegreeName";
	public static final String PG_DEGREE_NAME = "pgDegreeName";
	public static final String RESUME_URL = "resumeUrl";
	public static final String UNDER_GRADUATION_YEAR = "underGraduationYear";
	public static final String POST_GRADUATION_YEAR = "postGraduationYear";
	public static final String UNDER_GRADUATION_COLLEGE = "underGraduationCollege";
	public static final String POST_GRADUATION_COLLEGE = "postGraduationCollege";

	public void save(ProfessionalProfile transientInstance) {
		log.debug("saving ProfessionalProfile instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProfessionalProfile persistentInstance) {
		log.debug("deleting ProfessionalProfile instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProfessionalProfile findById(java.lang.Integer id) {
		log.debug("getting ProfessionalProfile instance with id: " + id);
		try {
			ProfessionalProfile instance = (ProfessionalProfile) getSession()
					.get("com.viksitpro.core.dao.entities.ProfessionalProfile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ProfessionalProfile> findByExample(ProfessionalProfile instance) {
		log.debug("finding ProfessionalProfile instance by example");
		try {
			List<ProfessionalProfile> results = (List<ProfessionalProfile>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.ProfessionalProfile").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ProfessionalProfile instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ProfessionalProfile as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ProfessionalProfile> findByYop10(Object yop10) {
		return findByProperty(YOP10, yop10);
	}

	public List<ProfessionalProfile> findByMarks10(Object marks10) {
		return findByProperty(MARKS10, marks10);
	}

	public List<ProfessionalProfile> findByYop12(Object yop12) {
		return findByProperty(YOP12, yop12);
	}

	public List<ProfessionalProfile> findByMarks12(Object marks12) {
		return findByProperty(MARKS12, marks12);
	}

	public List<ProfessionalProfile> findByHasUnderGraduation(Object hasUnderGraduation) {
		return findByProperty(HAS_UNDER_GRADUATION, hasUnderGraduation);
	}

	public List<ProfessionalProfile> findByUnderGraduationSpecializationName(Object underGraduationSpecializationName) {
		return findByProperty(UNDER_GRADUATION_SPECIALIZATION_NAME, underGraduationSpecializationName);
	}

	public List<ProfessionalProfile> findByUnderGradutionMarks(Object underGradutionMarks) {
		return findByProperty(UNDER_GRADUTION_MARKS, underGradutionMarks);
	}

	public List<ProfessionalProfile> findByHasPostGraduation(Object hasPostGraduation) {
		return findByProperty(HAS_POST_GRADUATION, hasPostGraduation);
	}

	public List<ProfessionalProfile> findByPostGraduationSpecializationName(Object postGraduationSpecializationName) {
		return findByProperty(POST_GRADUATION_SPECIALIZATION_NAME, postGraduationSpecializationName);
	}

	public List<ProfessionalProfile> findByPostGradutionMarks(Object postGradutionMarks) {
		return findByProperty(POST_GRADUTION_MARKS, postGradutionMarks);
	}

	public List<ProfessionalProfile> findByIsStudyingFurtherAfterDegree(Object isStudyingFurtherAfterDegree) {
		return findByProperty(IS_STUDYING_FURTHER_AFTER_DEGREE, isStudyingFurtherAfterDegree);
	}

	public List<ProfessionalProfile> findByJobSector(Object jobSector) {
		return findByProperty(JOB_SECTOR, jobSector);
	}

	public List<ProfessionalProfile> findByPreferredLocation(Object preferredLocation) {
		return findByProperty(PREFERRED_LOCATION, preferredLocation);
	}

	public List<ProfessionalProfile> findByCompanyName(Object companyName) {
		return findByProperty(COMPANY_NAME, companyName);
	}

	public List<ProfessionalProfile> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List<ProfessionalProfile> findByDuration(Object duration) {
		return findByProperty(DURATION, duration);
	}

	public List<ProfessionalProfile> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<ProfessionalProfile> findByInterestedInTypeOfCourse(Object interestedInTypeOfCourse) {
		return findByProperty(INTERESTED_IN_TYPE_OF_COURSE, interestedInTypeOfCourse);
	}

	public List<ProfessionalProfile> findByAreaOfInterest(Object areaOfInterest) {
		return findByProperty(AREA_OF_INTEREST, areaOfInterest);
	}

	public List<ProfessionalProfile> findByMarksheet10(Object marksheet10) {
		return findByProperty(MARKSHEET10, marksheet10);
	}

	public List<ProfessionalProfile> findByMarksheet12(Object marksheet12) {
		return findByProperty(MARKSHEET12, marksheet12);
	}

	public List<ProfessionalProfile> findByUnderGraduateDegreeName(Object underGraduateDegreeName) {
		return findByProperty(UNDER_GRADUATE_DEGREE_NAME, underGraduateDegreeName);
	}

	public List<ProfessionalProfile> findByPgDegreeName(Object pgDegreeName) {
		return findByProperty(PG_DEGREE_NAME, pgDegreeName);
	}

	public List<ProfessionalProfile> findByResumeUrl(Object resumeUrl) {
		return findByProperty(RESUME_URL, resumeUrl);
	}

	public List<ProfessionalProfile> findByUnderGraduationYear(Object underGraduationYear) {
		return findByProperty(UNDER_GRADUATION_YEAR, underGraduationYear);
	}

	public List<ProfessionalProfile> findByPostGraduationYear(Object postGraduationYear) {
		return findByProperty(POST_GRADUATION_YEAR, postGraduationYear);
	}

	public List<ProfessionalProfile> findByUnderGraduationCollege(Object underGraduationCollege) {
		return findByProperty(UNDER_GRADUATION_COLLEGE, underGraduationCollege);
	}

	public List<ProfessionalProfile> findByPostGraduationCollege(Object postGraduationCollege) {
		return findByProperty(POST_GRADUATION_COLLEGE, postGraduationCollege);
	}

	public List findAll() {
		log.debug("finding all ProfessionalProfile instances");
		try {
			String queryString = "from ProfessionalProfile";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProfessionalProfile merge(ProfessionalProfile detachedInstance) {
		log.debug("merging ProfessionalProfile instance");
		try {
			ProfessionalProfile result = (ProfessionalProfile) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProfessionalProfile instance) {
		log.debug("attaching dirty ProfessionalProfile instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProfessionalProfile instance) {
		log.debug("attaching clean ProfessionalProfile instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}