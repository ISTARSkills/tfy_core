package com.viksitpro.core.dao.utils.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Project;
import com.viksitpro.core.dao.entities.Team;
import com.viksitpro.core.dao.entities.TeamDAO;
import com.viksitpro.core.dao.utils.task.ProjectServices;

public class TeamServices {
	
	
	/**
	 * @param teamId Integer value of Team Id and instance of Team to be deleted
	 */
	public void deleteTeam(Integer teamId)
	{
		Team team = getTeam(teamId);
		/*for(TeamMember teamMember : team.getTeamMembers())
		{
			TeamMemberServices teamMemberService = new TeamMemberServices();
			teamMemberService.deleteTeamMember(teamMember);
		}
		*/
		TeamDAO teamDAO = new TeamDAO();

		Session teamSession = teamDAO.getSession();
		Transaction teamTransaction = null;
		try {
			teamTransaction = teamSession.beginTransaction();
			teamSession.delete(team);
			teamTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (teamTransaction != null)
				teamTransaction.rollback();
			e.printStackTrace();
		} finally {
			teamSession.close();
		}
	}
	
	/**
	 * This method create Team and returns Team Object corresponding to Input parameter teamName  description  projectId  istarUserId
	 * 
	 * @param teamName
	 * @param description
	 * @param projectId
	 * @param istarUserId
	 * @return Team Object
	 */
	public Team createTeam(String teamName, String description, Integer projectId, Integer istarUserId){
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		ProjectServices projectServices = new ProjectServices();
		Project project  = projectServices.getProject(projectId); 
				
		Team team = new Team();
		
		team.setName(teamName);
		team.setDescription(description);
		team.setIstarUser(istarUser);
		team.setProject(project);
		
		team = saveTeamToDAO(team);
		
		return team;
	}
	
	public Team updateTeam(Integer teamId, String teamName, String description, Integer projectId, Integer istarUserId){
		
		Team team = getTeam(teamId);
		
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser istarUser = istarUserServices.getIstarUser(istarUserId);
		
		ProjectServices projectServices = new ProjectServices();
		Project project  = projectServices.getProject(projectId); 
		
		team.setName(teamName);
		team.setDescription(description);
		team.setIstarUser(istarUser);
		team.setProject(project);
		
		team = updateTeamToDAO(team);
		
		return team;
	}
	

	/**
	 * This method returns Team Object corresponding to Input parameter teamId
	 * 
	 * @param teamId
	 * @return Team Object
	 */
	public Team getTeam(Integer teamId){
		TeamDAO teamDAO = new TeamDAO();
		Team team;
		try{
		team= teamDAO.findById(teamId);
		}catch(IllegalArgumentException e){
		team = null;
		}
		return team;
	}
	
	/**
	 * This method  returns Team Object corresponding to Input parameter teamName
	 * 
	 * @param teamName
	 * @return Team Object
	 */
	public Team getTeamByName(String teamName){
		TeamDAO teamDAO = new TeamDAO();
		List<Team> allTeams = teamDAO.findByName(teamName);
		Team team = null;
		
		if(allTeams.size() > 0){
		team = allTeams.get(0);
		}		
		return team;
	}
	
	public Team saveTeamToDAO(Team team) {

		TeamDAO teamDAO = new TeamDAO();

		Session teamSession = teamDAO.getSession();
		Transaction teamTransaction = null;
		try {
			teamTransaction = teamSession.beginTransaction();
			teamSession.save(team);
			teamTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (teamTransaction != null)
				teamTransaction.rollback();
			e.printStackTrace();
		} finally {
			teamSession.close();
		}
		return team;
	}

	public Team updateTeamToDAO(Team team) {

		TeamDAO teamDAO = new TeamDAO();

		Session teamSession = teamDAO.getSession();
		Transaction teamTransaction = null;
		try {
			teamTransaction = teamSession.beginTransaction();
			teamSession.update(team);
			teamTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (teamTransaction != null)
				teamTransaction.rollback();
			e.printStackTrace();
		} finally {
			teamSession.close();
		}
		return team;
	}
}
