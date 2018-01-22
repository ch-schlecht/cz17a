package cz17a.gamification.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import data.model.Answer;
import data.model.Participation;
import data.model.PlayedQuestion;
import data.model.Player;
import data.model.Question;
import data.model.Quiz;
import data.model.Round;

public class GameTest { //tests most getter methods with dummy elements to guarantee data flow is correct
	

	@org.junit.Test
	public void notNullTest() {
		Quiz quiz = new Quiz("Test-Topic", 10, 1, 5);
		Player player1 = new Player("mail@example.com", "max mustermann", "password");
		Player player2 = new Player("mail2@example.com", "moritz mustermann", "otherPassword");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		//Game game = new Game(quiz,players);
		Question question = new Question(10, "Welche Zahl ist die groe�te", 5, 5, "Test-Topic");
		question.setQuiz(quiz);
		Answer answer = new Answer("0", true);
		Answer answer2 = new Answer("1", false);
		ArrayList<Answer> answers = new ArrayList<Answer>();
		answers.add(answer);
		answers.add(answer2);
		question.setAnswers(answers);
		answer.setQuestion(question);
		answer2.setQuestion(question);
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		
		Round round = new Round(questions, players);
		
		Participation part = new Participation(player1, round);
		part.setScore(100);
		
		PlayedQuestion pQuestion = new PlayedQuestion(question, round, player1);
		pQuestion.setIs_correct(true);
		pQuestion.setScore(part.getScore());
		
		assertNotNull(quiz.getId());
		assertNotNull(quiz.getLength());
		assertNotNull(quiz.getMax_participants());
		assertNotNull(quiz.getMin_participants());
		assertNotNull(quiz.getQuestions());
		assertNotNull(quiz.getTitle());
		
		assertNotNull(player1.getId());
		assertNull(player1.getLast_login()); //not yet set
		assertNotNull(player1.getMail());
		assertNotNull(player1.getNickname());
		assertNotNull(player1.getPassword());
		assertNotNull(player1.getPlayedQuestion());
		assertNotNull(player1.getPlaytime_in_minutes());
		assertNull(player1.getRegistration());
		
		assertNotNull(question.getDynamic_difficulty());
		assertNotNull(question.getId());
		assertNotNull(question.getQuestioning());
		assertNotNull(question.getResponse_time());
		assertNotNull(question.getStatic_difficulty());
		assertNotNull(question.getTopic());
		assertNotNull(question.getQuiz());
		assertNotNull(question.getAnswers());
		
		assertNotNull(answer.getContent());
		assertNotNull(answer.getID());
		assertNotNull(answer.getQuestion());
		assertTrue(answer.isType());
		assertFalse(answer2.isType());
		
		
		assertNotNull(round.getId());
		assertNotNull(round.getMax_score());
		assertNotNull(round.getQuestions());
		assertNull(round.getStart()); //not yet set
		assertNull(round.getEnd()); //not yet set
		
		assertNotNull(part.getPlayer());
		assertNotNull(part.getRound());
		assertNotNull(part.getScore());
		assertEquals(part.getScore(), 100);
		
		assertNotNull(pQuestion.getPlayer());
		assertNotNull(pQuestion.getQuestion());
		assertNotNull(pQuestion.getRound());
		assertNotNull(pQuestion.getScore());
		assertNotNull(pQuestion.getSpeed_in_seconds());
		assertNotNull(pQuestion.isIs_correct());
		assertTrue(pQuestion.isIs_correct());
		assertEquals(pQuestion.getScore(), 100);
	}

}
