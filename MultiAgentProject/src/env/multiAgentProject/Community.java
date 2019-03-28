package multiAgentProject;

import java.util.ArrayList;
import java.util.List;

public class Community {
	private String communityName;
	public static ArrayList<Community> communities = new ArrayList<Community>();
	
	public String getCommunityName () {
		return communityName;
	}
	
	public void setCommunityName (String communityName) {
		this.communityName = communityName;
	}
}
