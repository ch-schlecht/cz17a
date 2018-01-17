package cz17a.gamification.gameserver;

public class Answer {
	private int ID;
	private String content;
	private boolean type;
	
	public Answer() {}
	
	public Answer(String content, boolean type) {
		this.content = content;
		this.type = type;
	}

}
