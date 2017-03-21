package com.viksitpro.core.dao.utils.user;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Role;
import com.viksitpro.core.dao.entities.RoleDAO;

public class RoleServices {

	public void deleteRole(Integer roleId) {
		Role role = getRole(roleId);
		if (role != null) {
			RoleDAO roleDAO = new RoleDAO();

			Session roleSession = roleDAO.getSession();
			Transaction roleTransaction = null;
			try {
				roleTransaction = roleSession.beginTransaction();
				roleSession.delete(role);
				roleTransaction.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (roleTransaction != null)
					roleTransaction.rollback();
			} finally {
				roleSession.close();
			}
		}
	}

	public Role createRole(String roleName) {

		Role role = new Role();

		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());

		role.setRoleName(roleName);
		role.setCreatedAt(current);

		role = saveRoleToDAO(role);

		return role;
	}

	public Role getRole(Integer roleId) {
		RoleDAO roleDAO = new RoleDAO();
		Role role = roleDAO.findById(roleId);

		return role;
	}

	public Role getRoleByName(String roleName) {
		RoleDAO roleDAO = new RoleDAO();
		List<Role> allRoles = roleDAO.findByRoleName(roleName);
		Role role = null;

		if (allRoles.size() > 0) {
			role = allRoles.get(0);
		}
		return role;
	}

	public Role saveRoleToDAO(Role role) {

		RoleDAO roleDAO = new RoleDAO();

		Session roleSession = roleDAO.getSession();
		Transaction roleTransaction = null;
		try {
			roleTransaction = roleSession.beginTransaction();
			roleSession.save(role);
			roleTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (roleTransaction != null)
				roleTransaction.rollback();
		} finally {
			roleSession.close();
		}
		return role;
	}

	public Role updateRoleToDAO(Role role) {

		RoleDAO roleDAO = new RoleDAO();

		Session roleSession = roleDAO.getSession();
		Transaction roleTransaction = null;
		try {
			roleTransaction = roleSession.beginTransaction();
			roleSession.update(role);
			roleTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (roleTransaction != null)
				roleTransaction.rollback();
		} finally {
			roleSession.close();
		}
		return role;
	}
}
