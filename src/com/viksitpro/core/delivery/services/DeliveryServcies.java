package com.viksitpro.core.delivery.services;

import com.viksitpro.core.dao.entities.Assessment;
import com.viksitpro.core.dao.entities.AssessmentDAO;
import com.viksitpro.core.dao.entities.AssessmentQuestion;
import com.viksitpro.core.skill.pojo.DeliveryAssessmentTree;

public class DeliveryServcies
{
	public DeliveryAssessmentTree getQuestionTreeWithSkillForAssessmentInCourse(int assessmentId, int courseId)
	{
		DeliveryAssessmentTree tree= new DeliveryAssessmentTree();
		Assessment assessment = new AssessmentDAO().findById(assessmentId);
		if(assessment!=null && assessment.getAssessmentQuestions()!=null)
		{
			for(AssessmentQuestion assesQue : assessment.getAssessmentQuestions())
		}
		return tree;
	}

}