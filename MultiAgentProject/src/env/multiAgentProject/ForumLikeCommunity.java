package multiAgentProject;

import java.util.List;

public class ForumLikeCommunity {
	private String communityName;
	private List<String> message;
	
	public String getCommunityName () {
		return communityName;
	}
	
	public void setCommunityName (String communityName) {
		this.communityName = communityName;
	}
	
	public List<String> getMessage () {
		return message;
	}
	
	public void setMessage (List<String> message) {
		this.message = message;
	}
}
