package multiAgentProject;

import java.util.ArrayList;
import java.util.List;

public class Community {
	private String communityName;
	private Env owner;
	private ArrayList<String> members = new ArrayList<String>();
	public static ArrayList<Community> communities = new ArrayList<Community>();
	
	public String getCommunityName () {
		return communityName;
	}
	
	public void setCommunityName (String communityName) {
		this.communityName = communityName;
	}
	
	public Env getOwner () {
		return owner;
	}
	
	public void setOwner (Env owner) {
		this.owner = owner;
	}
	
	public ArrayList<String> getMembers () {
		return members;
	}
}
