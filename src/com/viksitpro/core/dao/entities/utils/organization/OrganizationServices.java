package com.viksitpro.core.dao.entities.utils.organization;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Address;
import com.viksitpro.core.dao.entities.Organization;
import com.viksitpro.core.dao.entities.OrganizationDAO;

public class OrganizationServices {

	public Organization createOrganization(String name, String organizationType, String industry, String profile, String image, 
			String employeeCount, String website, Integer founded, String contactName, String contactEmail, Long contactPhone, Address address){

		Organization organization = new Organization();
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		organization.setAddress(address);
		organization.setName(name);
		organization.setOrgType(organizationType);
		organization.setIndustry(industry);
		organization.setProfile(profile);
		organization.setImage(image);
		organization.setWebsite(website);
		organization.setEmployeeCount(employeeCount);
		organization.setContactEmail(contactEmail);
		organization.setContactName(contactName);
		organization.setContactPhone(contactPhone);
		organization.setFounded(founded);
		organization.setCreatedAt(current);
		organization.setUpdatedAt(current);
		
		organization = saveOrganizationToDAO(organization);
	
		return organization;
	}
	
	public Organization updateOrganization(Integer organizationId, String name, String organizationType, String industry, String profile, String image, 
			String employeeCount, String website, Integer founded, String contactName, String contactEmail, Long contactPhone, Address address){
						
		Organization organization = getOrganization(organizationId);
		
		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());
		
		organization.setAddress(address);
		organization.setName(name);
		organization.setOrgType(organizationType);
		organization.setIndustry(industry);
		organization.setProfile(profile);
		organization.setImage(image);
		organization.setWebsite(website);
		organization.setEmployeeCount(employeeCount);
		organization.setContactEmail(contactEmail);
		organization.setContactName(contactName);
		organization.setContactPhone(contactPhone);
		organization.setFounded(founded);
		organization.setUpdatedAt(current);
		
		organization = updateOrganizationToDAO(organization);
	
		return organization;
	}
	
	/**
	 * This method returns Organization Object corresponding to Input parameter organizationId
	 * 
	 * @param organizationId Integer value of Organization Id
	 * @return  Organization Object
	 */
		
	public Organization getOrganization(int organizationId) {
		OrganizationDAO organizationDAO = new OrganizationDAO();
		Organization organization;
		try {
			organization = organizationDAO.findById(organizationId);
		} catch (IllegalArgumentException e) {
			organization = null;
		}
		return organization;
	}
	
	/**
	 * This method returns Organization Object corresponding to Input parameter organizationName
	 * 
	 * @param organizationName Integer value of Organization Name
	 * @return  Organization Object
	 */
		
	public Organization getOrganizationByName(String organizationName) {
		OrganizationDAO organizationDAO = new OrganizationDAO();
		List<Organization> allOrganization = organizationDAO.findByName(organizationName);

		if(allOrganization.size()<=0){
		return null;
		}
		Organization organization = allOrganization.get(0);
		return organization;
	}
	
	/**
	 * This method returns all college Organization Objects
	 * 
	 * @return  List of College Organization Object
	 */
		
	public List<Organization> getAllCollege() {
		OrganizationDAO organizationDAO = new OrganizationDAO();
		List<Organization> allColleges = organizationDAO.findByOrgType("COLLEGE");

		return allColleges;
	}
	
	
	/**
	 * This method returns all Company Organization Objects
	 * 
	 * @return  List of Company Organization Object
	 */		
	public List<Organization> getAllCompany() {
		OrganizationDAO organizationDAO = new OrganizationDAO();
		List<Organization> allColleges = organizationDAO.findByOrgType("COMPANY");

		return allColleges;
	}

	public Organization saveOrganizationToDAO(Organization organization) {

		OrganizationDAO organizationDAO = new OrganizationDAO();

		Session organizationSession = organizationDAO.getSession();
		Transaction organizationTransaction = null;
		try {
			organizationTransaction = organizationSession.beginTransaction();
			organizationSession.save(organization);
			organizationTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (organizationTransaction != null)
				organizationTransaction.rollback();
			e.printStackTrace();
		} finally {
			organizationSession.close();
		}
		return organization;
	}

	public Organization updateOrganizationToDAO(Organization organization) {

		OrganizationDAO organizationDAO = new OrganizationDAO();

		Session organizationSession = organizationDAO.getSession();
		Transaction organizationTransaction = null;
		try {
			organizationTransaction = organizationSession.beginTransaction();
			organizationSession.update(organization);
			organizationTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (organizationTransaction != null)
				organizationTransaction.rollback();
			e.printStackTrace();
		} finally {
			organizationSession.close();
		}
		return organization;
	}
	
}
