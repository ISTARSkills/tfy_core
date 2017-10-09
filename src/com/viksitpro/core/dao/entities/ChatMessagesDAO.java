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
 * ChatMessages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.viksitpro.core.dao.entities.ChatMessages
 * @author MyEclipse Persistence Tools
 */
public class ChatMessagesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ChatMessagesDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String MESSAGE = "message";
	public static final String CHAT_GROUP_ID = "chatGroupId";
	public static final String RECEIVER_ID = "receiverId";
	public static final String TYPE = "type";
	public static final String SENT = "sent";

	public void save(ChatMessages transientInstance) {
		log.debug("saving ChatMessages instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ChatMessages persistentInstance) {
		log.debug("deleting ChatMessages instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ChatMessages findById(java.lang.Integer id) {
		Session ss = getSession();
		log.debug("getting ChatMessages instance with id: " + id);
		try {
			ChatMessages instance = (ChatMessages) ss.get("com.viksitpro.core.dao.entities.ChatMessages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public List<ChatMessages> findByExample(ChatMessages instance) {
		log.debug("finding ChatMessages instance by example");
		try {
			List<ChatMessages> results = (List<ChatMessages>) getSession()
					.createCriteria("com.viksitpro.core.dao.entities.ChatMessages").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session ss = getSession();
		log.debug("finding ChatMessages instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ChatMessages as model where model." + propertyName + "= ?";
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

	public List<ChatMessages> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ChatMessages> findByMessage(Object message) {
		return findByProperty(MESSAGE, message);
	}

	public List<ChatMessages> findByChatGroupId(Object chatGroupId) {
		return findByProperty(CHAT_GROUP_ID, chatGroupId);
	}

	public List<ChatMessages> findByReceiverId(Object receiverId) {
		return findByProperty(RECEIVER_ID, receiverId);
	}

	public List<ChatMessages> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<ChatMessages> findBySent(Object sent) {
		return findByProperty(SENT, sent);
	}

	public List findAll() {
		log.debug("finding all ChatMessages instances");
		try {
			String queryString = "from ChatMessages";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ChatMessages merge(ChatMessages detachedInstance) {
		log.debug("merging ChatMessages instance");
		try {
			ChatMessages result = (ChatMessages) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ChatMessages instance) {
		log.debug("attaching dirty ChatMessages instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ChatMessages instance) {
		log.debug("attaching clean ChatMessages instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}