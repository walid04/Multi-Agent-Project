package multiAgentProject;

import java.util.ArrayList;
import java.util.List;

public class TwitterLikeCommunity extends Community {
	private ArrayList<String> messages = new ArrayList<String>();
	
	public ArrayList<String> getMessages () {
		return messages;
	}
	
	public void setMessages (ArrayList<String> messages) {
		this.messages = messages;
	}
}
