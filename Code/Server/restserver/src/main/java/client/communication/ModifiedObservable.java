package client.communication;

import java.util.Observable;

public class ModifiedObservable extends Observable {
	public String message = "";
	
	public void setChanged() {
		super.setChanged();
	}
}
