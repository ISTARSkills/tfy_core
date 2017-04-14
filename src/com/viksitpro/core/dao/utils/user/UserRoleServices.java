package com.viksitpro.core.dao.utils.user;

import java.util.List;
import java.util.Set;

/*import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;*/

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Role;
import com.viksitpro.core.dao.entities.UserRole;
import com.viksitpro.core.dao.entities.UserRoleDAO;

public class UserRoleServices {
	
	public UserRole createUserRole(Integer istarUserId, Integer roleId, Integer priority){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		RoleServices roleServices = new RoleServices();
		Role role = roleServices.getRole(roleId);
		
		UserRole userRole = createUserRole(istarUser, role, priority);
		
		return userRole;
	}
	
	public UserRole createUserRole(IstarUser istarUser, Role role, Integer priority){
		
		UserRole userRole = new UserRole();
		
		userRole.setIstarUser(istarUser);
		userRole.setRole(role);
		userRole.setPriority(priority);	
		
		userRole = saveUserRoleToDAO(userRole);
		
		return userRole;
	}
	
	public void revokeAllRolesFromUser(Integer istarUserId)
	{
		IstarUserServices istarUserService = new IstarUserServices(); 
		IstarUser istarUser = istarUserService.getIstarUser(istarUserId);
		
		Set<UserRole> allUserRoles = istarUser.getUserRoles();
		
		for(UserRole userRole : allUserRoles){
			deleteUserRoleFromDAO(userRole);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRole> getAllUserRolesOfRole(Integer roleId){
		
		String hql = "from UserRole userRole where role= :role";

		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		Query query = session.createQuery(hql);
		query.setParameter("role", roleId);
		
		List<UserRole> allUserRoles = query.list();
		
		return allUserRoles;
	}
	
/*	public void revokeRoleFromUser(Integer istarUserId, Integer roleId)
	{
		IstarUserServices istarUserService = new IstarUserServices(); 
		IstarUser istarUser = istarUserService.getIstarUser(istarUserId);
		
		RoleServices roleServices = new RoleServices();
		Role role = roleServices.getRole(roleId);
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<UserRole> criteria = builder.createQuery(UserRole.class);
		Root<UserRole> fromUserRole = criteria.from(UserRole.class);

		ArrayList<Predicate> allPredicates = new ArrayList<Predicate>();
		
		allPredicates.add(builder.equal(fromUserRole.get("role"), role));
		allPredicates.add(builder.equal(fromUserRole.get("istarUser"), istarUser));
		
		criteria.where(builder.and(allPredicates.toArray(new Predicate[] {})));
		
		List<UserRole> allUserRoles = session.createQuery(criteria).getResultList();
		
		for(UserRole userRole : allUserRoles){
			deleteUserRoleFromDAO(userRole);
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public void revokeRoleFromUser(Integer istarUserId, Integer roleId)
	{		
		String sql = "from UserRole where role= :roleId and istar_user= :istarUserId";
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		Query query = session.createQuery(sql);
		query.setParameter("roleId",roleId);
		query.setParameter("istarUserId",istarUserId);
		
		List<UserRole> allUserRoles = query.list();
		
		for(UserRole userRole : allUserRoles){
			deleteUserRoleFromDAO(userRole);
		}
	}
	
	public UserRole getUserRole(int userRoleId){
		UserRoleDAO userRoleDAO = new UserRoleDAO();
		UserRole userRole = userRoleDAO.findById(userRoleId);
		
		return userRole;
	}
	
	public UserRole saveUserRoleToDAO(UserRole userRole) {

			UserRoleDAO userRoleDAO = new UserRoleDAO();

			Session userRoleSession = userRoleDAO.getSession();
			Transaction userRoleTransaction = null;
			try {
				userRoleTransaction = userRoleSession.beginTransaction();
				userRoleSession.save(userRole);
				userRoleTransaction.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (userRoleTransaction != null)
					userRoleTransaction.rollback();
			} finally {
				userRoleSession.close();
			}
		return userRole;
	}

	public UserRole updateUserRoleToDAO(UserRole userRole) {

		UserRoleDAO userRoleDAO = new UserRoleDAO();

		Session userRoleSession = userRoleDAO.getSession();
		Transaction userRoleTransaction = null;
		try {
			userRoleTransaction = userRoleSession.beginTransaction();
			userRoleSession.update(userRole);
			userRoleTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (userRoleTransaction != null)
				userRoleTransaction.rollback();
		} finally {
			userRoleSession.close();
		}
		return userRole;
	}
	
	public void deleteUserRoleFromDAO(UserRole userRole) {

		UserRoleDAO userRoleDAO = new UserRoleDAO();

		Session userRoleSession = userRoleDAO.getSession();
		Transaction userRoleTransaction = null;
		try {
			userRoleTransaction = userRoleSession.beginTransaction();
			userRoleSession.delete(userRole);
			userRoleTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (userRoleTransaction != null)
				userRoleTransaction.rollback();
		} finally {
			userRoleSession.close();
		}
	}
}
