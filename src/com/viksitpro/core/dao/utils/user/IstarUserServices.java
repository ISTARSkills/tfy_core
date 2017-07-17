package com.viksitpro.core.dao.utils.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Address;
import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.IstarUserDAO;
import com.viksitpro.core.dao.entities.ProfessionalProfile;
import com.viksitpro.core.dao.entities.ProfessionalProfileDAO;
import com.viksitpro.core.dao.entities.Role;
import com.viksitpro.core.dao.entities.UserProfile;
import com.viksitpro.core.dao.entities.UserProfileDAO;
import com.viksitpro.core.dao.entities.UserRole;
import com.viksitpro.core.dao.entities.utils.address.AddressService;

public class IstarUserServices {

	public ProfessionalProfile createProfessionalProfile(Integer istarUserId, Integer yop10, Float marks10, Long yop12,
			Float marks12, Boolean hasUnderGraduation, String underGraduationSpecializationName,
			Float underGradutionMarks, Boolean hasPostGraduation, String postGraduationSpecializationName,
			Float postGradutionMarks, Boolean isStudyingFurtherAfterDegree, String jobSector, String preferredLocation,
			String companyName, String position, String duration, String description, String interestedInTypeOfCourse,
			String areaOfInterest, String marksheet10, String marksheet12, String underGraduateDegreeName,
			String pgDegreeName, String resumeUrl, Integer underGraduationYear, Integer postGraduationYear,
			String underGraduationCollege, String postGraduationCollege) {
		
		IstarUser istarUser = getIstarUser(istarUserId);
		ProfessionalProfile professionalProfile = new ProfessionalProfile();
		
			professionalProfile.setAreaOfInterest(areaOfInterest);
			professionalProfile.setCompanyName(companyName);
			professionalProfile.setDescription(description);
			professionalProfile.setDuration(duration);
			professionalProfile.setHasPostGraduation(hasPostGraduation);
			professionalProfile.setHasUnderGraduation(hasUnderGraduation);
			professionalProfile.setInterestedInTypeOfCourse(interestedInTypeOfCourse);
			professionalProfile.setIsStudyingFurtherAfterDegree(isStudyingFurtherAfterDegree);

			professionalProfile.setIstarUser(istarUser);
			professionalProfile.setJobSector(jobSector);
			professionalProfile.setMarks10(marks10);
			professionalProfile.setMarks12(marks12);
			professionalProfile.setMarksheet10(marksheet10);
			professionalProfile.setMarksheet12(marksheet12);
			professionalProfile.setPgDegreeName(pgDegreeName);
			professionalProfile.setPosition(position);
			professionalProfile.setPostGraduationSpecializationName(postGraduationSpecializationName);
			professionalProfile.setPostGradutionMarks(postGradutionMarks);
			professionalProfile.setPreferredLocation(preferredLocation);
			professionalProfile.setResumeUrl(resumeUrl);
			professionalProfile.setUnderGraduateDegreeName(underGraduateDegreeName);
			professionalProfile.setUnderGraduationSpecializationName(underGraduationSpecializationName);
			professionalProfile.setUnderGradutionMarks(underGradutionMarks);
			professionalProfile.setYop10(yop10);
			professionalProfile.setYop12(yop12);
			professionalProfile.setUnderGraduationCollege(underGraduationCollege);
			professionalProfile.setPostGraduationCollege(postGraduationCollege);
			professionalProfile.setUnderGraduationYear(underGraduationYear);
			professionalProfile.setPostGraduationYear(postGraduationYear);

			professionalProfile = saveProfessionalProfileToDAO(professionalProfile);

		return professionalProfile;
	}
	
	public ProfessionalProfile updateProfessionalProfile(Integer istarUserId, Integer yop10, Float marks10, Long yop12,
			Float marks12, Boolean hasUnderGraduation, String underGraduationSpecializationName,
			Float underGradutionMarks, Boolean hasPostGraduation, String postGraduationSpecializationName,
			Float postGradutionMarks, Boolean isStudyingFurtherAfterDegree, String jobSector, String preferredLocation,
			String companyName, String position, String duration, String description, String interestedInTypeOfCourse,
			String areaOfInterest, String marksheet10, String marksheet12, String underGraduateDegreeName,
			String pgDegreeName, String resumeUrl, Integer underGraduationYear, Integer postGraduationYear,
			String underGraduationCollege, String postGraduationCollege) {

		IstarUser istarUser = getIstarUser(istarUserId);
		ProfessionalProfile professionalProfile = istarUser.getProfessionalProfile();

		professionalProfile.setAreaOfInterest(areaOfInterest);
		professionalProfile.setCompanyName(companyName);
		professionalProfile.setDescription(description);
		professionalProfile.setDuration(duration);
		professionalProfile.setHasPostGraduation(hasPostGraduation);
		professionalProfile.setHasUnderGraduation(hasUnderGraduation);
		professionalProfile.setInterestedInTypeOfCourse(interestedInTypeOfCourse);
		professionalProfile.setIsStudyingFurtherAfterDegree(isStudyingFurtherAfterDegree);

		professionalProfile.setIstarUser(istarUser);
		professionalProfile.setJobSector(jobSector);
		professionalProfile.setMarks10(marks10);
		professionalProfile.setMarks12(marks12);
		professionalProfile.setMarksheet10(marksheet10);
		professionalProfile.setMarksheet12(marksheet12);
		professionalProfile.setPgDegreeName(pgDegreeName);
		professionalProfile.setPosition(position);
		professionalProfile.setPostGraduationSpecializationName(postGraduationSpecializationName);
		professionalProfile.setPostGradutionMarks(postGradutionMarks);
		professionalProfile.setPreferredLocation(preferredLocation);
		professionalProfile.setResumeUrl(resumeUrl);
		professionalProfile.setUnderGraduateDegreeName(underGraduateDegreeName);
		professionalProfile.setUnderGraduationSpecializationName(underGraduationSpecializationName);
		professionalProfile.setUnderGradutionMarks(underGradutionMarks);
		professionalProfile.setYop10(yop10);
		professionalProfile.setYop12(yop12);
		professionalProfile.setUnderGraduationCollege(underGraduationCollege);
		professionalProfile.setPostGraduationCollege(postGraduationCollege);
		professionalProfile.setUnderGraduationYear(underGraduationYear);
		professionalProfile.setPostGraduationYear(postGraduationYear);

		professionalProfile = updateProfessionalProfileToDAO(professionalProfile);

		return professionalProfile;
	}

	private ProfessionalProfile saveProfessionalProfileToDAO(ProfessionalProfile professionalProfile) {
		
		ProfessionalProfileDAO professionalProfileDAO = new ProfessionalProfileDAO();
		
		Session professionalProfileSession = professionalProfileDAO.getSession();
		Transaction professionalProfileTransaction = null;
		try {
			professionalProfileTransaction = professionalProfileSession.beginTransaction();
			professionalProfileSession.save(professionalProfile);
			professionalProfileTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (professionalProfileTransaction != null)
				professionalProfileTransaction.rollback();
		} finally {
			professionalProfileSession.close();
		}
		return professionalProfile;
	}

	private ProfessionalProfile updateProfessionalProfileToDAO(ProfessionalProfile professionalProfile) {
		
		ProfessionalProfileDAO professionalProfileDAO = new ProfessionalProfileDAO();
		
		Session professionalProfileSession = professionalProfileDAO.getSession();
		Transaction professionalProfileTransaction = null;
		try {
			professionalProfileTransaction = professionalProfileSession.beginTransaction();
			professionalProfileSession.update(professionalProfile);
			professionalProfileTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (professionalProfileTransaction != null)
				professionalProfileTransaction.rollback();
		} finally {
			professionalProfileSession.close();
		}
		return professionalProfile;
	}
	
	public UserProfile createUserProfile(Integer istarUserId, Integer addressId, String firstName, String lastName,
			Date dob, String gender, String profileImage, Long aadhar) {

		System.out.println("Name is-> "+firstName);
		IstarUser istarUser = getIstarUser(istarUserId);

		UserProfile userProfile = new UserProfile();
		
			userProfile.setAadharNo(aadhar);
			Address address = new AddressService().getAddress(addressId);
			userProfile.setAddress(address);
			userProfile.setDob(dob);
			userProfile.setFirstName(firstName);
			userProfile.setGender(gender);
			userProfile.setIstarUser(istarUser);
			userProfile.setLastName(lastName);
			userProfile.setProfileImage(profileImage);
			
			userProfile = saveUserProfileToDAO(userProfile);

		return userProfile;
	}

	public UserProfile updateUserProfile(Integer istarUserId, Integer addressId, String firstName, String lastName,
			String dob, String gender, String profileImage, Long aadhar) {
		
		IstarUser user = getIstarUser(istarUserId);
		UserProfile userProfile = user.getUserProfile();

			userProfile.setAadharNo(aadhar);
			userProfile.setDob(dob);
			userProfile.setFirstName(firstName);
			userProfile.setGender(gender);

			userProfile.setIstarUser(user);
			userProfile.setLastName(lastName);
			userProfile.setProfileImage(profileImage);
			
			userProfile = updateUserProfileToDAO(userProfile);
		
		return userProfile;
	}

	
	public UserProfile updateProfileImage(IstarUser istarUser, String profileImage){
		IstarUserDAO uDAO = new IstarUserDAO();
		UserProfileDAO pDAO = new UserProfileDAO();
		UserProfile userProfile = istarUser.getUserProfile();
		
		if(userProfile!=null){
			userProfile.setProfileImage(profileImage);			
			Session userProfileSession = uDAO.getSession();
			Transaction userProfileTransaction = null;
			try {
				userProfileTransaction = userProfileSession.beginTransaction();
				pDAO.attachDirty(userProfile);
				//userProfileSession.update(userProfile);
				userProfileTransaction.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (userProfileTransaction != null)
					userProfileTransaction.rollback();
			} finally {
				userProfileSession.close();
				
			}
			istarUser.setUserProfile(userProfile);
			Session userSession = uDAO.getSession();
			Transaction userTransaction = null;
			try {
				userTransaction = userSession.beginTransaction();
				uDAO.attachDirty(istarUser);
				userTransaction.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (userTransaction != null)
					userTransaction.rollback();
			} finally {
				userSession.close();
				
			}
			
		}
		return userProfile;
	}
	
	private UserProfile saveUserProfileToDAO(UserProfile userProfile) {
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
		} finally {
			userProfileSession.close();
		}
		return userProfile;
	}
	
	private UserProfile updateUserProfileToDAO(UserProfile userProfile) {
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
		} finally {
			userProfileSession.close();
		}
		return userProfile;
	}

	public IstarUser createIstarUser(String email, String password, Long mobile) {
		
		IstarUser istarUser;
		IstarUser istarUserByMobile = null;
		
		istarUser = getIstarUserByEmail(email);
		
		if(mobile!=null){
			istarUserByMobile = getIstarUserByMobile(mobile); 
		}
		
		if(istarUser==null && istarUserByMobile==null){
			istarUser = new IstarUser();

		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());

		istarUser.setEmail(email);
		istarUser.setPassword(password);
		istarUser.setMobile(mobile);
		istarUser.setIsVerified(false);
		istarUser.setCreatedAt(current);

		istarUser = saveIstarUserToDAO(istarUser);
		}else{
			System.out.println("User alread exists!");
		}
		return istarUser;
	}
	
	public IstarUser createIstarUser(String email, String password, Long mobile, String authenticationToken) {
		
		IstarUser istarUser;
		IstarUser istarUserByMobile = null;
		
		istarUser = getIstarUserByEmail(email);
		
		if(mobile!=null){
			istarUserByMobile = getIstarUserByMobile(mobile); 
		}
		
		if(istarUser==null && istarUserByMobile==null){
			istarUser = new IstarUser();

		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());

		istarUser.setEmail(email);
		istarUser.setPassword(password);
		istarUser.setMobile(mobile);
		istarUser.setAuthToken(authenticationToken);
		istarUser.setIsVerified(false);
		istarUser.setCreatedAt(current);

		istarUser = saveIstarUserToDAO(istarUser);
		}else{
			System.out.println("User Email/Mobile alread exists!");
		}
		return istarUser;
	}
	
	public IstarUser createIstarUser(String email, String password, Long mobile, String authenticationToken, String loginType) {
		
		IstarUser istarUser;
		IstarUser istarUserByMobile = null;
		
		istarUser = getIstarUserByEmail(email);
		
		if(mobile!=null){
			istarUserByMobile = getIstarUserByMobile(mobile); 
		}
		
		if(istarUser==null && istarUserByMobile==null){
			istarUser = new IstarUser();

		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());

		istarUser.setEmail(email);
		istarUser.setPassword(password);
		istarUser.setMobile(mobile);
		istarUser.setAuthToken(authenticationToken);
		istarUser.setLoginType(loginType);
		istarUser.setIsVerified(false);
		istarUser.setCreatedAt(current);

		istarUser = saveIstarUserToDAO(istarUser);
		}else{
			System.out.println("User Email/Mobile alread exists!");
		}
		return istarUser;
	}

	public IstarUser updateIstarUser(Integer istarUserId, String email, String password, Long mobile) {
		
		IstarUser istarUser = getIstarUser(istarUserId);

		java.util.Date date = new java.util.Date();
		Timestamp current = new Timestamp(date.getTime());

		istarUser.setEmail(email);
		istarUser.setPassword(password);
		istarUser.setMobile(mobile);
		istarUser.setCreatedAt(current);

		istarUser = updateIstarUserToDAO(istarUser);

		return istarUser;
	}

	public IstarUser updateAuthenticationTokenForIstarUser(IstarUser istarUser, String authenticationToken) {
		istarUser.setAuthToken(authenticationToken);
		istarUser = updateIstarUserToDAO(istarUser);
		
		return istarUser;
	}

	public IstarUser updateAuthenticationTokenForIstarUser(Integer istarUserId, String authenticationToken) {

		IstarUser istarUser = getIstarUser(istarUserId);

		return updateAuthenticationTokenForIstarUser(istarUser, authenticationToken);
	}

	public IstarUser updateMobile(Integer istarUserId, Long mobile){
		
		IstarUser istarUser = getIstarUser(istarUserId);
		
		istarUser.setMobile(mobile);
		istarUser = updateIstarUserToDAO(istarUser);

		return istarUser;		
	}
	
	public IstarUser updateIsVerified(Integer istarUserId, Boolean isVerified){
		IstarUser istarUser = getIstarUser(istarUserId);
		
		istarUser.setIsVerified(isVerified);
		istarUser = updateIstarUserToDAO(istarUser);

		return istarUser;		
	}
	
	
	public IstarUser saveIstarUserToDAO(IstarUser istarUser) {

		IstarUserDAO istarUserDAO = new IstarUserDAO();

		Session istarUserSession = istarUserDAO.getSession();
		Transaction istarUserTransaction = null;
		try {
			istarUserTransaction = istarUserSession.beginTransaction();
			istarUserSession.save(istarUser);
			istarUserTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (istarUserTransaction != null)
				istarUserTransaction.rollback();
			e.printStackTrace();
		} finally {
			istarUserSession.close();
		}
		return istarUser;
	}

	public IstarUser updateIstarUserToDAO(IstarUser istarUser) {

		IstarUserDAO istarUserDAO = new IstarUserDAO();

		Session istarUserSession = istarUserDAO.getSession();
		Transaction istarUserTransaction = null;
		try {
			istarUserTransaction = istarUserSession.beginTransaction();
			istarUserSession.update(istarUser);
			istarUserTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (istarUserTransaction != null)
				istarUserTransaction.rollback();
			e.printStackTrace();
		} finally {
			istarUserSession.close();
		}
		return istarUser;
	}
	
	public boolean deleteIstarUserFromDAO(IstarUser istarUser) {

		boolean isDeleted = false;
		
		IstarUserDAO istarUserDAO = new IstarUserDAO();

		Session istarUserSession = istarUserDAO.getSession();
		Transaction istarUserTransaction = null;
		try {
			istarUserTransaction = istarUserSession.beginTransaction();
			istarUserSession.delete(istarUser);
			istarUserTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (istarUserTransaction != null)
				istarUserTransaction.rollback();
			e.printStackTrace();
		} finally {
			istarUserSession.close();
		}
		return isDeleted;
	}
	
	public IstarUser getIstarUser(Integer istarUserId) {
		IstarUser istarUser;
		IstarUserDAO istarUserDAO = new IstarUserDAO();
		istarUserDAO.getSession().clear();
		try {
			istarUser = istarUserDAO.findById(istarUserId);
		} catch (IllegalArgumentException e) {
			istarUser = null;
		}
		return istarUser;
	}
	
	public void deleteIstarUser(Integer istarUserId){
		IstarUser istarUser = getIstarUser(istarUserId);
		deleteIstarUserFromDAO(istarUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<IstarUser> getAllIstarUsers() {
		List<IstarUser> allIstarUsers;
		IstarUserDAO istarUserDAO = new IstarUserDAO();
		try {
			allIstarUsers = (List<IstarUser>) istarUserDAO.findAll();
		} catch (IllegalArgumentException e) {
			allIstarUsers = new ArrayList<IstarUser>();
		}
		return allIstarUsers;
	}

	public IstarUser getIstarUserByEmail(String email) {

		List<IstarUser> allIstarUsersByEmail;
		IstarUser istarUser = null;
		IstarUserDAO istarUserDAO = new IstarUserDAO();
		try {
			allIstarUsersByEmail = istarUserDAO.findByEmail(email);

			System.out.println("Size is " + allIstarUsersByEmail.size());
			
			if (allIstarUsersByEmail.size() > 0) {
				istarUser = allIstarUsersByEmail.get(0);
			} else {
				return null;
			}
		} catch (IllegalArgumentException e) {
			istarUser = null;
		}
		return istarUser;
	}

	public IstarUser getIstarUserByMobile(Long mobile) {

		List<IstarUser> allIstarUsersByMobile;
		IstarUser istarUser = null;
		IstarUserDAO istarUserDAO = new IstarUserDAO();
		try {
			allIstarUsersByMobile = istarUserDAO.findByMobile(mobile);

			System.out.println("Size is " + allIstarUsersByMobile.size());
			
			if (allIstarUsersByMobile.size() > 0) {
				istarUser = allIstarUsersByMobile.get(0);
			} else {
				return null;
			}
		} catch (IllegalArgumentException e) {
			istarUser = null;
		}
		return istarUser;
	}

	public List<IstarUser> getUserWithEmail(String email) {
		List<IstarUser> users = new ArrayList<IstarUser>();
		IstarUserDAO dao = new IstarUserDAO();
		users = dao.findByEmail(email);
		return users;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Role> getRolesOfUser(IstarUser user) {
		ArrayList<Role> roles = new ArrayList<Role>();

		Set<Role> rolesset = new HashSet<Role>();
		for (UserRole userRole : user.getUserRoles()) {
			rolesset.add(userRole.getRole());
		}
		System.out.println("roleset size>>>>>" + rolesset.size());
		roles = (ArrayList<Role>) BaseHibernateDAO.GetOrderedListFromSet(rolesset, "priority");
		System.out.println("roles size>>>>>>>" + roles.size());
		return roles;
	}

	public List<IstarUser> getUserByMobile(Integer mobile) {
		IstarUserDAO userDao = new IstarUserDAO();
		List<IstarUser> users = new ArrayList<IstarUser>();
		users = userDao.findByMobile(mobile);
		return users;
	}

	public void testProfile(int userId, String fileName) {
		System.err.println("-------------->"+(new IstarUserDAO()).findById(userId).getUserProfile().getImage());
		System.err.println("fileName--->"+fileName);
		
	}

}
