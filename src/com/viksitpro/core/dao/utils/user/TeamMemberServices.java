package com.viksitpro.core.dao.utils.user;

import java.util.ArrayList;
import java.util.List;

/*import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;*/

/*import org.hibernate.HibernateException;*/
import org.hibernate.SQLQuery;
import org.hibernate.Session;
/*import org.hibernate.Transaction;*/

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Team;
/*import com.viksitpro.core.dao.entities.TeamMember;
import com.viksitpro.core.dao.entities.TeamMemberDAO;*/

public class TeamMemberServices {

/*	public void deleteTeamMember(Integer teamId, Integer istarUserId) {
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
*/
	
	
/*	public void deleteTeamMembersByEmail(Integer teamId, List<String> allTeamMemberEmails){
		
		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);
			t
		IstarUserServices istarUserServices = new IstarUserServices();
		
		for(String email : allTeamMemberEmails){
			IstarUser istarUser = istarUserServices.getIstarUserByEmail(email);
			deleteTeamMember(team, istarUser);
		}
	}*/
	
	
/*	public void deleteTeamMember(Team team, IstarUser istarUser){
		
		TeamMember teamMember = getTeamMember(team.getId(), istarUser.getId());	
		deleteTeamMemberFromDAO(teamMember);
	}*/
	
/*	public TeamMember createTeamMember(int teamId, int istarUserId) {

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
	}*/
	
	@SuppressWarnings("unchecked")
	public List<IstarUser> getIstarUsersOfTeam(int teamId){
		
		String sql = "select user_id from team_member where team_id="+teamId;
		
		//ViksitLogger.logMSG(this.getClass().getName(),sql);
		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		SQLQuery query = session.createSQLQuery(sql);

		List<Integer> result = query.list();
		
		List<IstarUser> allIstarUserOfTeam = new ArrayList<IstarUser>();
		IstarUserServices istarUserServices = new IstarUserServices();
		for(Integer userId: result){
			allIstarUserOfTeam.add(istarUserServices.getIstarUser(userId));
		}
		
		return allIstarUserOfTeam;
	} 

	public void setIstarUsersOfTeam(int teamId, List<IstarUser> allTeamMembers) {

		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		try {
			for (IstarUser user : allTeamMembers) {
				String sql = "INSERT INTO team_member (team_id, user_id) VALUES (" + teamId + ", " + user.getId() + ")";
				//ViksitLogger.logMSG(this.getClass().getName(),sql);

				SQLQuery query = session.createSQLQuery(sql);
				query.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<IstarUser> createTeamMember(int teamId, List<Integer> allIstarUserId) {

		TeamServices teamServices = new TeamServices();
		Team team = teamServices.getTeam(teamId);

		IstarUserServices istarUserServices = new IstarUserServices();

		List<IstarUser> allTeamMembersSet = getIstarUsersOfTeam(teamId);
				for (Integer istarUserId : allIstarUserId) {
			if (!checkIfTeamMemberAlreadyExists(teamId, istarUserId)) {
				IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
				
				allTeamMembersSet.add(istarUser);
			} else {
				//ViksitLogger.logMSG(this.getClass().getName(),"TeamMember already exists");
			}
			team = teamServices.updateTeamToDAO(team);
		}
		
		setIstarUsersOfTeam(team.getId(), allTeamMembersSet);
		List<IstarUser> allTeamMembers = new ArrayList<IstarUser>( getIstarUsersOfTeam(teamId));
		return allTeamMembers;
	}

	public List<IstarUser> createTeamMemberByEmail(int teamId, List<String> allEmails) {
		IstarUserServices istarUserServices = new IstarUserServices();
		List<Integer> allIstarUserIds = new ArrayList<Integer>();

		for (String email : allEmails) {
			IstarUser istarUser = istarUserServices.getIstarUserByEmail(email);

			if (istarUser == null) {
				istarUser = istarUserServices.createIstarUser(email, "test123", null);
				//RecruiterEmailUtility recruiterEmailUtility = new RecruiterEmailUtility();
				//recruiterEmailUtility.sendEmailToHiringTeamMembers(istarUser);
			} 	
			allIstarUserIds.add(istarUser.getId());
		}

		List<IstarUser> allIstarUserOfTeam = createTeamMember(teamId, allIstarUserIds);
		return allIstarUserOfTeam;
	}

/*	public TeamMember getTeamMember(int teamMemberId) {
		TeamMemberDAO teamMemberDAO = new TeamMemberDAO();
		TeamMember teamMember = teamMemberDAO.findById(teamMemberId);

		return teamMember;
	}*/

/*	public TeamMember getTeamMember(int teamId, int istarUserId) {

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

		//ViksitLogger.logMSG(this.getClass().getName(),allTeamMember.size());
		if (allTeamMember.size() > 0) {
			teamMember = allTeamMember.get(0);
		}
		return teamMember;
	}*/

	@SuppressWarnings("unchecked")
	public boolean checkIfTeamMemberAlreadyExists(int teamId, int istarUserId) {

		boolean isOld = true;

		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();

		String sql = "select user_id from team_member where team_id= :teamId and user_id= :istarUserId";
		
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("teamId", teamId);
		query.setParameter("istarUserId", istarUserId);
		
		List<Integer> allTeamMember = query.list();

		//ViksitLogger.logMSG(this.getClass().getName(),allTeamMember.size());
		if (allTeamMember.size() <= 0) {
			isOld = false;
		}		
		return isOld;
	}
	
	public void removeAllExistingTeamMembers(int teamId){		
		BaseHibernateDAO baseHibernateDAO = new BaseHibernateDAO();
		Session session = baseHibernateDAO.getSession();
		try {
			String sql = "delete from team_member where team_id=" + teamId;
			//ViksitLogger.logMSG(this.getClass().getName(),(sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

/*	public TeamMember saveTeamMemberToDAO(TeamMember teamMember) {

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
	}*/

/*	public TeamMember updateTeamMemberToDAO(TeamMember teamMember) {

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
	}*/

/*	public void deleteTeamMemberFromDAO(TeamMember teamMember) {

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

	}*/
}
