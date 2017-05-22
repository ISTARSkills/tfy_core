
package com.viksitpro.core.dao.utils.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;*/

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.SubUser;
import com.viksitpro.core.dao.entities.SubUserDAO;

public class SubUserServices {

	public SubUser createSubUser(IstarUser istarUser, IstarUser subIstarUser) {

		SubUser subUser = new SubUser();

		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());

		subUser.setIstarUserByIstarUser(istarUser);
		subUser.setIstarUserBySubIstarUser(subIstarUser);
		subUser.setCreatedAt(current);
		subUser.setUpdatedAt(current);

		subUser = saveSubUserToDAO(subUser);

		return subUser;
	}
	
	public Boolean isSubUserAlready(Integer istarUserId, Integer subIstarUserId){
		
		String sql = "select id from sub_user where istar_user="+istarUserId+" and sub_istar_user="+subIstarUserId;
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		SQLQuery query = session.createSQLQuery(sql);
	
		List<Integer> result = query.list();
		
		if(result.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
/*	public void deleteSubUser(int istarUserId, int subIstarUserId){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		IstarUser subIstarUser = istarUserServices.getIstarUser(subIstarUserId);
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<SubUser> criteria = builder.createQuery(SubUser.class);
		Root<SubUser> fromSubUser = criteria.from(SubUser.class);

		ArrayList<Predicate> allPredicates = new ArrayList<Predicate>();
		
		allPredicates.add(builder.equal(fromSubUser.get("istarUserByIstarUser"), istarUser));
		allPredicates.add(builder.equal(fromSubUser.get("istarUserBySubIstarUser"), subIstarUser));
		
		criteria.where(builder.and(allPredicates.toArray(new Predicate[] {})));
		
		List<SubUser> allSubUsers = session.createQuery(criteria).getResultList();
		
		for(SubUser subUser : allSubUsers){
			deleteSubUserFromDAO(subUser);
		}
		
	}*/
	
	@SuppressWarnings("unchecked")
	public void deleteSubUser(int istarUserId, int subIstarUserId){
		
		String sql = "from SubUser where istar_user= :istarUserByIstarUser and sub_istar_user= :istarUserBySubIstarUser";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		Query query = session.createQuery(sql);
		query.setParameter("istarUserByIstarUser",istarUserId);
		query.setParameter("istarUserBySubIstarUser",subIstarUserId);
		
		List<SubUser> allSubUsers = query.list();
		
		for(SubUser subUser : allSubUsers){
			deleteSubUserFromDAO(subUser);
		}		
	}

	public SubUser getSubUser(Integer subUserId) {
		SubUserDAO subUserDAO = new SubUserDAO();
		SubUser subUser = subUserDAO.findById(subUserId);

		return subUser;
	}
	
	@SuppressWarnings("unchecked")
	public Set<IstarUser> getSubIstarUsers(IstarUser istarUser){
				
		String sql = "select sub_istar_user from sub_user where istar_user="+istarUser.getId();
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		SQLQuery query = session.createSQLQuery(sql);
	
		List<Integer> allSubUserIds = query.list();
		
		IstarUserServices istarUserServices = new IstarUserServices();		
	
		Set<IstarUser> allSubIstarUsers = new HashSet<IstarUser>();
		
		for(Integer subUserId : allSubUserIds){
			IstarUser subIstarUser = istarUserServices.getIstarUser(subUserId);
			if(subIstarUser!=null){
				allSubIstarUsers.add(subIstarUser);
			}
		}		
		return allSubIstarUsers;
	}

	public SubUser saveSubUserToDAO(SubUser subUser) {

		SubUserDAO subUserDAO = new SubUserDAO();

		Session subUserSession = subUserDAO.getSession();
		Transaction subUserTransaction = null;
		try {
			subUserTransaction = subUserSession.beginTransaction();
			subUserSession.save(subUser);
			subUserTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (subUserTransaction != null)
				subUserTransaction.rollback();
			e.printStackTrace();
		} finally {
			subUserSession.close();
		}
		return subUser;
	}
	
	public SubUser deleteSubUserFromDAO(SubUser subUser) {

		SubUserDAO subUserDAO = new SubUserDAO();

		Session subUserSession = subUserDAO.getSession();
		Transaction subUserTransaction = null;
		try {
			subUserTransaction = subUserSession.beginTransaction();
			subUserSession.delete(subUser);
			subUserTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (subUserTransaction != null)
				subUserTransaction.rollback();
			e.printStackTrace();
		} finally {
			subUserSession.close();
		}
		return subUser;
	}
}
