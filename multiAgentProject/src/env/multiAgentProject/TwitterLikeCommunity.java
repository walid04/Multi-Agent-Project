package multiAgentProject;

import java.util.List;

public class TwitterLikeCommunity extends Community {
	private List<String> messages;
	
	public List<String> getMessages () {
		return messages;
	}
	
	public void setMessages (List<String> messages) {
		this.messages = messages;
	}
}
