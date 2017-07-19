/**
 * 
 */
package com.viksitpro.core.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.utils.HibernateSessionFactory;

/**
 * @author vaibhav
 *
 */
public class DBUTILS {
	public void executeUpdate(String sql) {
		Session session_hibernate = HibernateSessionFactory.getSessionFactory().openSession();
		session_hibernate.clear();
		Transaction tx = null;
		try {
			tx = session_hibernate.beginTransaction();
			SQLQuery query = session_hibernate.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session_hibernate.close();
		}
	}

	public int executeUpdateReturn(String sql) {
		Session session_hibernate = HibernateSessionFactory.getSessionFactory().openSession();
		session_hibernate.clear();
	Transaction tx = null;
		List<HashMap<String, Object>> ret = new ArrayList<HashMap<String, Object>>();
		try {
			tx = session_hibernate.beginTransaction();
			SQLQuery query = session_hibernate.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			ret = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session_hibernate.close();
		}
		return Integer.parseInt(ret.get(0).get("id").toString());
	}

	public List<HashMap<String, Object>> executeQuery(String sql) {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		Session session_hibernate = HibernateSessionFactory.getSessionFactory().openSession();
		session_hibernate.clear();
	Transaction tx = null;
		try {
			tx = session_hibernate.beginTransaction();
			SQLQuery query = session_hibernate.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			data = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session_hibernate.close();
		}
		return data;
	}

	public List<HashMap<String, Object>> executeFromQueryObject(SQLQuery query) {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		Session session_hibernate = HibernateSessionFactory.getSessionFactory().openSession();
		session_hibernate.clear();
Transaction tx = null;
		try {
			tx = session_hibernate.beginTransaction();
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			data = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session_hibernate.close();
		}
		return data;
	}

	public void syncPkeySeq(String table_name, String pkey_seq) {
		String syncSeq = "SELECT setval('" + pkey_seq + "', COALESCE((SELECT MAX(id)+1 FROM " + table_name + "), 1), false) as id;";
		try {
			long seq_val = executeUpdateReturn(syncSeq);
			//System.out.println("DONE: DBUTILS::syncPkeySeq -> syncSeq --> " + syncSeq + pkey_seq + " ---> " + seq_val);
		} catch (Exception e) {
			//System.err.println("FAIL: DBUTILS::syncPkeySeq -> syncSeq --> " + syncSeq);
			e.printStackTrace();
		}
		return;
	}
	
	public List<Object> executeHQL(String hql) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();		session.clear();

		Query query = session.createQuery(hql);
		List<Object> results = query.list();
		return results;
	}
}
