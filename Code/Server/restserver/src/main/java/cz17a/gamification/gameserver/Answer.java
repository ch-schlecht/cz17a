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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

}
