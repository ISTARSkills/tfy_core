package com.viksitpro.core.dao.entities.utils.organization;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Organization;
import com.viksitpro.core.dao.entities.UserOrgMapping;
import com.viksitpro.core.dao.entities.UserOrgMappingDAO;

public class UserOrgMappingServices {

	public UserOrgMapping createUserOrgMapping(IstarUser istarUser, Organization organization) {

		UserOrgMapping userOrgMapping = new UserOrgMapping();

		userOrgMapping.setIstarUser(istarUser);
		userOrgMapping.setOrganization(organization);

		userOrgMapping = saveUserOrgMappingToDAO(userOrgMapping);

		return userOrgMapping;
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganizationsOfIstarUser(IstarUser istarUser){
		
		List<Organization> allOrganization = new ArrayList<Organization>();
		
		
		UserOrgMappingDAO userOrgMappingDAO = new UserOrgMappingDAO();
		List<UserOrgMapping> allUserOrgMappings = (List<UserOrgMapping>) userOrgMappingDAO.findByProperty("istarUser", istarUser);
		
		for(UserOrgMapping userOrgMapping: allUserOrgMappings){
			allOrganization.add(userOrgMapping.getOrganization());
		}
		
		return allOrganization;
	}
	
	@SuppressWarnings("unchecked")
	public List<IstarUser> getAllIstarUsersOfOrganization(Organization organization){
		
		List<IstarUser> allIstarUsers = new ArrayList<IstarUser>();
		UserOrgMappingDAO userOrgMappingDAO = new UserOrgMappingDAO();
		List<UserOrgMapping> allUserOrgMappings = (List<UserOrgMapping>) userOrgMappingDAO.findByProperty("org", organization);
		
		for(UserOrgMapping userOrgMapping: allUserOrgMappings){
			allIstarUsers.add(userOrgMapping.getIstarUser());
		}
		
		return allIstarUsers;
	}

	public UserOrgMapping saveUserOrgMappingToDAO(UserOrgMapping userOrgMapping) {

		UserOrgMappingDAO userOrgMappingDAO = new UserOrgMappingDAO();

		Session userOrgMappingSession = userOrgMappingDAO.getSession();
		Transaction userOrgMappingTransaction = null;
		try {
			userOrgMappingTransaction = userOrgMappingSession.beginTransaction();
			userOrgMappingSession.save(userOrgMapping);
			userOrgMappingTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (userOrgMappingTransaction != null)
				userOrgMappingTransaction.rollback();
			e.printStackTrace();
		} finally {
			userOrgMappingSession.close();
		}
		return userOrgMapping;
	}
}
