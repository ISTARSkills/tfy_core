package com.viksitpro.core.dao.entities;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Organization entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Organization
 * @author MyEclipse Persistence Tools
 */
public class OrganizationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(OrganizationDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ORG_TYPE = "orgType";
	public static final String INDUSTRY = "industry";
	public static final String PROFILE = "profile";
	public static final String IMAGE = "image";
	public static final String WEBSITE = "website";
	public static final String FOUNDED = "founded";
	public static final String EMPLOYEE_COUNT = "employeeCount";
	public static final String CONTACT_NAME = "contactName";
	public static final String CONTACT_EMAIL = "contactEmail";
	public static final String CONTACT_PHONE = "contactPhone";

	public void save(Organization transientInstance) {
		log.debug("saving Organization instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Organization persistentInstance) {
		log.debug("deleting Organization instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Organization findById(java.lang.Integer id) {
		log.debug("getting Organization instance with id: " + id);
		try {
			Organization instance = (Organization) getSession().get("com.viksitpro.core.dao.entities.Organization", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Organization> findByExample(Organization instance) {
		log.debug("finding Organization instance by example");
		try {
			List<Organization> results = (List<Organization>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Organization").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Organization instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Organization as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Organization> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Organization> findByOrgType(Object orgType) {
		return findByProperty(ORG_TYPE, orgType);
	}

	public List<Organization> findByIndustry(Object industry) {
		return findByProperty(INDUSTRY, industry);
	}

	public List<Organization> findByProfile(Object profile) {
		return findByProperty(PROFILE, profile);
	}

	public List<Organization> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Organization> findByWebsite(Object website) {
		return findByProperty(WEBSITE, website);
	}

	public List<Organization> findByFounded(Object founded) {
		return findByProperty(FOUNDED, founded);
	}

	public List<Organization> findByEmployeeCount(Object employeeCount) {
		return findByProperty(EMPLOYEE_COUNT, employeeCount);
	}

	public List<Organization> findByContactName(Object contactName) {
		return findByProperty(CONTACT_NAME, contactName);
	}

	public List<Organization> findByContactEmail(Object contactEmail) {
		return findByProperty(CONTACT_EMAIL, contactEmail);
	}

	public List<Organization> findByContactPhone(Object contactPhone) {
		return findByProperty(CONTACT_PHONE, contactPhone);
	}

	public List findAll() {
		log.debug("finding all Organization instances");
		try {
			String queryString = "from Organization";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Organization merge(Organization detachedInstance) {
		log.debug("merging Organization instance");
		try {
			Organization result = (Organization) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Organization instance) {
		log.debug("attaching dirty Organization instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Organization instance) {
		log.debug("attaching clean Organization instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}