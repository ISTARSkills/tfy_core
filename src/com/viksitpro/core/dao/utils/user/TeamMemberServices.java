package com.viksitpro.core.dao.utils.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Team;
import com.viksitpro.core.dao.entities.TeamMember;
import com.viksitpro.core.dao.entities.TeamMemberDAO;

public class TeamMemberServices {

	/**
	 * The instance of TeamMember to be deleted corresponding to Input parameter
	 * teamId istarUserId
	 * 
	 * @param teamId
	 *            Integer value of Team Id
	 * @param istarUserId
	 *            Integer value of IstarUser Id
	 */
	public void deleteTeamMember(Integer teamId, Integer istarUserId) {
		IstarUserServices userService = new IstarUserServices();
		IstarUser user = userService.getIstarUser(istarUserId);
		Team team = new TeamServices().getTeam(teamId);

		Set<TeamMember> teamMebersToBeDeleted = new HashSet<>();
		Set<TeamMember> updatedTeamMember = new HashSet<>();
		for (TeamMember teamMember : team.getTeamMembers()) {
			if (teamMember.getIstarUser().getId() != istarUserId) {
				updatedTeamMember.add(teamMember);
			} else {
				teamMebersToBeDeleted.add(teamMember);
			}

		}
		userService.updateIstarUserToDAO(user);
		for (TeamMember member : teamMebersToBeDeleted) {
			deleteTeamMemberFromDAO(member);
		}
	}

	
	
	public void deleteTeamMembersByEmail(Integer teamId, List<String> allTeamMemberEmails){
		
		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		
		for(String email : allTeamMemberEmails){
			IstarUser istarUser = istarUserServices.getIstarUserByEmail(email);
			deleteTeamMember(team, istarUser);
		}
	}
	
	
	public void deleteTeamMember(Team team, IstarUser istarUser){
		
		TeamMember teamMember = getTeamMember(team.getId(), istarUser.getId());	
		deleteTeamMemberFromDAO(teamMember);
	}
	
	/**
	 * This method create TeamMember and returns TeamMember Object corresponding
	 * to Input parameter teamId istarUserId
	 * 
	 * @param teamId
	 * @param istarUserId
	 * @return TeamMember Object
	 */
	public TeamMember createTeamMember(int teamId, int istarUserId) {

		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);

		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		//java.util.Date date = new java.util.Date();
		//Timestamp current = new Timestamp(date.getTime());

		TeamMember teamMember = new TeamMember();

		teamMember.setIstarUser(istarUser);
		teamMember.setTeam(team);

		teamMember = saveTeamMemberToDAO(teamMember);

		return teamMember;
	}

	/**
	 * This method create List of TeamMembers and returns List of IstarUser
	 * Objects of the team corresponding to Input parameter teamId List of
	 * istarUserId
	 * 
	 * @param teamId
	 * @param List
	 *            of istarUserId
	 * @return List of IstarUser Object
	 */
	public List<IstarUser> createTeamMember(int teamId, List<Integer> allIstarUserId) {

		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);

		List<IstarUser> allTeamMembers = new ArrayList<IstarUser>();

		IstarUserServices istarUserServices = new IstarUserServices();

		for (Integer istarUserId : allIstarUserId) {
			TeamMember teamMember = null;
			if (checkIfTeamMemberAlreadyExists(teamId, istarUserId)) {
				System.out.println("Creating New Team Member ");
				teamMember = new TeamMember();
				IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);

				teamMember.setIstarUser(istarUser);
				teamMember.setTeam(team);

				teamMember = saveTeamMemberToDAO(teamMember);
			} else {
				System.out.println("old team Member");
				teamMember = getTeamMember(teamId, istarUserId);
			}
			if(teamMember!=null){
			allTeamMembers.add(teamMember.getIstarUser());
			}
		}
		return allTeamMembers;
	}

	/**
	 * This method create List of TeamMembers and returns List of TeamMember
	 * Objects corresponding to Input parameter teamId List of istarUserId
	 * 
	 * @param teamId
	 * @param List
	 *            of istarUserId
	 * @return List of IstarUser Object
	 */
	public List<IstarUser> createTeamMemberByEmail(int teamId, List<String> allEmails) {

		IstarUserServices istarUserServices = new IstarUserServices();
		List<Integer> allIstarUserIds = new ArrayList<Integer>();

		for (String email : allEmails) {
			IstarUser istarUser = istarUserServices.getIstarUserByEmail(email);

			if (istarUser == null) {
				istarUser = istarUserServices.createIstarUser(email, "test123", 0L);
				//RecruiterEmailUtility recruiterEmailUtility = new RecruiterEmailUtility();
				//recruiterEmailUtility.sendEmailToHiringTeamMembers(istarUser);
			} 	
			allIstarUserIds.add(istarUser.getId());
		}

		List<IstarUser> allIstarUserOfTeam = createTeamMember(teamId, allIstarUserIds);
		return allIstarUserOfTeam;
	}

	/**
	 * This method returns TeamMember Object corresponding to Input parameter
	 * teamMemberId
	 * 
	 * @param teamMemberId
	 * @return TeamMember Object
	 */
	public TeamMember getTeamMember(int teamMemberId) {
		TeamMemberDAO teamMemberDAO = new TeamMemberDAO();
		TeamMember teamMember = teamMemberDAO.findById(teamMemberId);

		return teamMember;
	}

	public TeamMember getTeamMember(int teamId, int istarUserId) {

		TeamMember teamMember = null;

		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);

		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);

		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<TeamMember> criteria = builder.createQuery(TeamMember.class);
		Root<TeamMember> fromTeamMember = criteria.from(TeamMember.class);

		ArrayList<Predicate> allPredicates = new ArrayList<Predicate>();

		allPredicates.add(builder.equal(fromTeamMember.get("team"), team));
		allPredicates.add(builder.equal(fromTeamMember.get("istarUser"), istarUser));

		criteria.where(builder.and(allPredicates.toArray(new Predicate[] {})));

		List<TeamMember> allTeamMember = session.createQuery(criteria).getResultList();

		System.out.println(allTeamMember.size());
		if (allTeamMember.size() > 0) {
			teamMember = allTeamMember.get(0);
		}
		return teamMember;
	}

	public boolean checkIfTeamMemberAlreadyExists(int teamId, int istarUserId) {

		boolean isNew = false;

		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);

		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);

		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<TeamMember> criteria = builder.createQuery(TeamMember.class);
		Root<TeamMember> fromTeamMember = criteria.from(TeamMember.class);

		ArrayList<Predicate> allPredicates = new ArrayList<Predicate>();

		allPredicates.add(builder.equal(fromTeamMember.get("team"), team));
		allPredicates.add(builder.equal(fromTeamMember.get("istarUser"), istarUser));

		criteria.where(builder.and(allPredicates.toArray(new Predicate[] {})));

		List<TeamMember> allTeamMember = session.createQuery(criteria).getResultList();

		System.out.println(allTeamMember.size());
		if (allTeamMember.size() <= 0) {
			isNew = true;
		}
		
		System.out.println("checkIfTeamMemberAlreadyExists:" + isNew);
		return isNew;
	}
	
	public void removeAllExistingTeamMembers(int teamId){
		
		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);
		
		Set<TeamMember> allTeamMembers = team.getTeamMembers();
		
		for(TeamMember teamMember : allTeamMembers){
			deleteTeamMemberFromDAO(teamMember);
		}		
	}

	/**
	 * 
	 * @param teamMember
	 * @return
	 */
	public TeamMember saveTeamMemberToDAO(TeamMember teamMember) {

		TeamMemberDAO teamMemberDAO = new TeamMemberDAO();

		Session teamMemberSession = teamMemberDAO.getSession();
		Transaction teamMemberTransaction = null;
		try {
			teamMemberTransaction = teamMemberSession.beginTransaction();
			teamMemberSession.save(teamMember);
			teamMemberTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (teamMemberTransaction != null)
				teamMemberTransaction.rollback();
			e.printStackTrace();
		} finally {
			teamMemberSession.close();
		}
		return teamMember;
	}

	/**
	 * 
	 * @param teamMember
	 * @return
	 */
	public TeamMember updateTeamMemberToDAO(TeamMember teamMember) {

		TeamMemberDAO teamMemberDAO = new TeamMemberDAO();

		Session teamMemberSession = teamMemberDAO.getSession();
		Transaction teamMemberTransaction = null;
		try {
			teamMemberTransaction = teamMemberSession.beginTransaction();
			teamMemberSession.update(teamMember);
			teamMemberTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (teamMemberTransaction != null)
				teamMemberTransaction.rollback();
			e.printStackTrace();
		} finally {
			teamMemberSession.close();
		}
		return teamMember;
	}

	/**
	 * 
	 * @param teamMember
	 *            instance of TeamMember to be deleted
	 */
	public void deleteTeamMemberFromDAO(TeamMember teamMember) {

		TeamMemberDAO teamMemberDAO = new TeamMemberDAO();

		Session teamMemberSession = teamMemberDAO.getSession();
		Transaction teamMemberTransaction = null;
		try {
			teamMemberTransaction = teamMemberSession.beginTransaction();
			teamMemberSession.delete(teamMember);
			teamMemberTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (teamMemberTransaction != null)
				teamMemberTransaction.rollback();
			e.printStackTrace();
		} finally {
			teamMemberSession.close();
		}

	}
}
