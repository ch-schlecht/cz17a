package cz17a.gamification.restserver.resource.quiz;

import data.access.HibernateUtil;

public class QuizService {

	public QuizService() {
		
	}
	
	public static Quiz getAllDataHibernate(){
		return (Quiz) HibernateUtil.getSession().createCriteria(Quiz.class).list().get(0);
	}
	
	
}
