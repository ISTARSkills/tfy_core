package com.viksitpro.core.dao.utils.user;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Address;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.UserProfile;
import com.viksitpro.core.dao.entities.UserProfileDAO;

public class UserProfileServices {

	
	/**
	 * This method create UserProfile and returns UserProfile Object corresponding to Input parameter istarUser
	 * address   firstName   lastName  gender  profileImage  dateOfBirth  aadharNumber
	 * 
	 * 
	 * @param istarUser
	 * @param address
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param profileImage
	 * @param dateOfBirth
	 * @param aadharNumber
	 * @return UserProfile Object
	 */
	public UserProfile createUserProfile(IstarUser istarUser, Address address, String firstName, String lastName, 
			String gender, String profileImage, Date dateOfBirth, Long aadharNumber){
	
		
		UserProfile userProfile = new UserProfile();
		
		userProfile.setIstarUser(istarUser);
		userProfile.setAddress(address);
		userProfile.setFirstName(firstName);
		userProfile.setLastName(lastName);
		userProfile.setGender(gender);
		userProfile.setProfileImage(profileImage);
		userProfile.setDob(dateOfBirth);
		userProfile.setAadharNo(aadharNumber);
		
		userProfile = saveUserProfileToDAO(userProfile);
		
		return userProfile;
	}

	/**
	 * This method returns UserProfile Object corresponding to Input parameter userProfileId
	 * 
	 * @param userProfileId
	 * @return UserProfile Object
	 */
	public UserProfile getUserProfile(Integer userProfileId) {
		UserProfileDAO userProfileDAO = new UserProfileDAO();
		UserProfile userProfile; 
		try{
		userProfile= userProfileDAO.findById(userProfileId);
		}catch(IllegalArgumentException e){
			userProfile = null;
		}
		return userProfile;
	}

	public UserProfile saveUserProfileToDAO(UserProfile userProfile) {

		UserProfileDAO userProfileDAO = new UserProfileDAO();

		Session userProfileSession = userProfileDAO.getSession();
		Transaction userProfileTransaction = null;
		try {
			userProfileTransaction = userProfileSession.beginTransaction();
			userProfileSession.save(userProfile);
			userProfileTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (userProfileTransaction != null)
				userProfileTransaction.rollback();
			e.printStackTrace();
		} finally {
			userProfileSession.close();
		}
		return userProfile;
	}

	public UserProfile updateUserProfileToDAO(UserProfile userProfile) {

		UserProfileDAO userProfileDAO = new UserProfileDAO();

		Session userProfileSession = userProfileDAO.getSession();
		Transaction userProfileTransaction = null;
		try {
			userProfileTransaction = userProfileSession.beginTransaction();
			userProfileSession.update(userProfile);
			userProfileTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (userProfileTransaction != null)
				userProfileTransaction.rollback();
			e.printStackTrace();
		} finally {
			userProfileSession.close();
		}
		return userProfile;
	}
	
}
