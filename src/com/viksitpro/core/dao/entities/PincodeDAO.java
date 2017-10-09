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
 * Pincode entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.Pincode
 * @author MyEclipse Persistence Tools
 */
public class PincodeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PincodeDAO.class);
	// property constants
	public static final String CITY = "city";
	public static final String COUNTRY = "country";
	public static final String PIN = "pin";
	public static final String STATE = "state";
	public static final String LATTIUDE = "lattiude";
	public static final String LONGITUDE = "longitude";
	public static final String STATE_CODE = "stateCode";

	public void save(Pincode transientInstance) {
		log.debug("saving Pincode instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Pincode persistentInstance) {
		log.debug("deleting Pincode instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pincode findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting Pincode instance with id: " + id);
		try {
			Pincode instance = (Pincode) ss.get("com.viksitpro.core.dao.entities.Pincode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<Pincode> findByExample(Pincode instance) {
		log.debug("finding Pincode instance by example");
		try {
			List<Pincode> results = (List<Pincode>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.Pincode").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Pincode> findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding Pincode instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Pincode as model where model." + propertyName + "= ?";
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

	public List<Pincode> findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List<Pincode> findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List<Pincode> findByPin(Object pin) {
		return findByProperty(PIN, pin);
	}

	public List<Pincode> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Pincode> findByLattiude(Object lattiude) {
		return findByProperty(LATTIUDE, lattiude);
	}

	public List<Pincode> findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List<Pincode> findByStateCode(Object stateCode) {
		return findByProperty(STATE_CODE, stateCode);
	}

	public List<Pincode> findAll() {
		log.debug("finding all Pincode instances");
		try {
			String queryString = "from Pincode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Pincode merge(Pincode detachedInstance) {
		log.debug("merging Pincode instance");
		try {
			Pincode result = (Pincode) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Pincode instance) {
		log.debug("attaching dirty Pincode instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pincode instance) {
		log.debug("attaching clean Pincode instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}