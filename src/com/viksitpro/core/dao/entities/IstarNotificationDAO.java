package com.viksitpro.core.dao.entities;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for IstarNotification entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.viksitpro.core.dao.entities.IstarNotification
  * @author MyEclipse Persistence Tools 
 */
public class IstarNotificationDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(IstarNotificationDAO.class);
		//property constants
	public static final String SENDER_ID = "senderId";
	public static final String RECEIVER_ID = "receiverId";
	public static final String TITLE = "title";
	public static final String DETAILS = "details";
	public static final String STATUS = "status";
	public static final String ACTION = "action";
	public static final String TYPE = "type";
	public static final String IS_EVENT_BASED = "isEventBased";
	public static final String TASK_ID = "taskId";
	public static final String GROUP_CODE = "groupCode";



    
    public void save(IstarNotification transientInstance) {
        log.debug("saving IstarNotification instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(IstarNotification persistentInstance) {
        log.debug("deleting IstarNotification instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public IstarNotification findById( java.lang.Integer id) {
		Session ss = getSession();
        log.debug("getting IstarNotification instance with id: " + id);
        try {
            IstarNotification instance = (IstarNotification) ss
                    .get("com.viksitpro.core.dao.entities.IstarNotification", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }finally {
			ss.close();
		}
    }
    
    
    public List<IstarNotification> findByExample(IstarNotification instance) {
        log.debug("finding IstarNotification instance by example");
        try {
            List<IstarNotification> results = (List<IstarNotification>) getSession()
                    .createCriteria("com.viksitpro.core.dao.entities.IstarNotification")
                    .add( create(instance) )
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
      log.debug("finding IstarNotification instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from IstarNotification as model where model." 
         						+ propertyName + "= ?";
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

	public List<IstarNotification> findBySenderId(Object senderId
	) {
		return findByProperty(SENDER_ID, senderId
		);
	}
	
	public List<IstarNotification> findByReceiverId(Object receiverId
	) {
		return findByProperty(RECEIVER_ID, receiverId
		);
	}
	
	public List<IstarNotification> findByTitle(Object title
	) {
		return findByProperty(TITLE, title
		);
	}
	
	public List<IstarNotification> findByDetails(Object details
	) {
		return findByProperty(DETAILS, details
		);
	}
	
	public List<IstarNotification> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List<IstarNotification> findByAction(Object action
	) {
		return findByProperty(ACTION, action
		);
	}
	
	public List<IstarNotification> findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List<IstarNotification> findByIsEventBased(Object isEventBased
	) {
		return findByProperty(IS_EVENT_BASED, isEventBased
		);
	}
	
	public List<IstarNotification> findByTaskId(Object taskId
	) {
		return findByProperty(TASK_ID, taskId
		);
	}
	
	public List<IstarNotification> findByGroupCode(Object groupCode
	) {
		return findByProperty(GROUP_CODE, groupCode
		);
	}
	

	public List findAll() {
		log.debug("finding all IstarNotification instances");
		try {
			String queryString = "from IstarNotification";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public IstarNotification merge(IstarNotification detachedInstance) {
        log.debug("merging IstarNotification instance");
        try {
            IstarNotification result = (IstarNotification) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IstarNotification instance) {
        log.debug("attaching dirty IstarNotification instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(IstarNotification instance) {
        log.debug("attaching clean IstarNotification instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}