package multiAgentProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cartago.OPERATION;
import cartago.tools.GUIArtifact;

/**
 * @author Walid
 *
 */
public class Env extends GUIArtifact {
	private EnvGUI envGUI;
	private String user;
	private ArrayList<TwitterLikeCommunity> tlcs = new ArrayList<TwitterLikeCommunity>();
	
	public ArrayList<TwitterLikeCommunity> getTwitterLikeCommunity () {
		return tlcs;
	}
	
	public EnvGUI getEnvGUI () {
		return envGUI;
	}
	
	public String getUser () {
		return user;
	}
	
	public void init (String s) {
		envGUI = new EnvGUI();
		envGUI.setSize(606, 365);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		envGUI.setLocation(dim.width/2-envGUI.getSize().width/2, dim.height/2-envGUI.getSize().height/2);
		
		linkActionEventToOp(envGUI.getBtnCreateCommunity(), "createCommunity");
		linkActionEventToOp(envGUI.getBtnJoinCommunity(), "joinCommunity");
		linkActionEventToOp(envGUI.getBtnDeleteCommunity(), "deleteCommunity");
		linkActionEventToOp(envGUI.getBtnLeaveCommunity(), "leaveCommunity");
		
		envGUI.setTitle(s);
		user = s;
		
		Users.users.add(this);
		
		envGUI.setVisible(true);
		
		this.init();
	}
	
	@OPERATION 
	public void createCommunity (ActionEvent e) {
		int test = 0;

		if (!envGUI.getCommunityNameTextField().getText().equals("")) {
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(envGUI.getCommunityNameTextField().getText())) {
					test = 1;
					break;
				}
			}
			if (test == 0) {
				if (envGUI.getCommunityTypeComboBox().getSelectedItem().toString().equals("Twitter like Community")) {
					System.out.println("Creating twitter like community");

					TwitterLikeCommunity tlc = new TwitterLikeCommunity();
					tlc.setCommunityName(envGUI.getCommunityNameTextField().getText());
					tlc.setOwner(this);

					signal("createCommunity", envGUI.getCommunityNameTextField().getText(), "twitter_like_agent_walid"+user.replace("user_agent", ""));
					JOptionPane.showMessageDialog(null, "Community created", "Info Box", JOptionPane.INFORMATION_MESSAGE);

					envGUI.getCommunityNameTextField().setText("");

					tlc.setOwner(this);
					Community.communities.add(tlc);

					for (int i = 0; i < Users.users.size(); i++) {
						if (Users.users.get(i).getEnvGUI() == envGUI) {
							Users.users.get(i).getEnvGUI().getCommunityToDeleteComboBox().addItem(tlc.getCommunityName());
							Users.users.get(i).getEnvGUI().getCommunityToLeaveComboBox().addItem(tlc.getCommunityName());
						}
						else {
							Users.users.get(i).getEnvGUI().getCommunityToJoinComboBox().addItem(tlc.getCommunityName());
						}
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This community name already exists, please choose another one", "Info Box", JOptionPane.INFORMATION_MESSAGE);
				envGUI.getCommunityNameTextField().setText("");
			}
		}
	}
	
	@OPERATION 
	void joinCommunity (ActionEvent e) {
		if (envGUI.getCommunityToJoinComboBox().getSelectedItem() != null) {
			System.out.println("Joining Community");
			
			String agent = null;
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(envGUI.getCommunityToJoinComboBox().getSelectedItem().toString())) {
					Community.communities.get(i).getMembers().add(user);
					agent = Community.communities.get(i).getClass().toString();
					break;
				}
			}
			
			if (agent.replaceAll("class multiAgentProject.", "").equals("TwitterLikeCommunity")) {
				agent = "twitter_like_agent_walid" + user.replace("user_agent", "");
				
				System.out.println("Twitter Like Community to join : " + envGUI.getCommunityToJoinComboBox().getSelectedItem().toString());
				signal("joinCommunity", envGUI.getCommunityToJoinComboBox().getSelectedItem().toString(), agent);
				envGUI.getCommunityToLeaveComboBox().addItem(envGUI.getCommunityToJoinComboBox().getSelectedItem());
				envGUI.getCommunityToJoinComboBox().removeItem(envGUI.getCommunityToJoinComboBox().getSelectedItem());
				JOptionPane.showMessageDialog(null, "Twitter Like Community joined", "Info Box", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	@OPERATION
	public void deleteCommunity (ActionEvent e) {
		if (envGUI.getCommunityToDeleteComboBox().getSelectedItem() != null) {
			System.out.println("Deleting Community");
			
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(envGUI.getCommunityToDeleteComboBox().getSelectedItem().toString())) {
					for (int j = 0; j < Community.communities.get(i).getMembers().size(); j++) {
						signal("informUsersOfDeletedCommunity", envGUI.getCommunityToDeleteComboBox().getSelectedItem().toString(), "twitter_like_agent_walid"+Community.communities.get(i).getMembers().get(j).replace("user_agent", ""));
					}
				}
			}

			String agent = null;
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(envGUI.getCommunityToDeleteComboBox().getSelectedItem().toString())) {
					agent = Community.communities.get(i).getClass().toString();
					Community.communities.remove(i);
					break;
				}
			}
			
			if (agent.replaceAll("class multiAgentProject.", "").equals("TwitterLikeCommunity")) {
				agent = "twitter_like_agent_walid" + user.replace("user_agent", "");
				
				JOptionPane.showMessageDialog(null, "Twitter Like Community deleted", "Info Box", JOptionPane.INFORMATION_MESSAGE);
				
				Object o = envGUI.getCommunityToDeleteComboBox().getSelectedItem();
				for (int i = 0; i < Users.users.size(); i++) {
					for (int j = 0; j < Users.users.get(i).getEnvGUI().getCommunityToLeaveComboBox().getItemCount(); j++) {
						if (Users.users.get(i).getEnvGUI().getCommunityToLeaveComboBox().getItemAt(j) == o) {
							Users.users.get(i).getEnvGUI().getCommunityToLeaveComboBox().removeItemAt(j);
							break;
						}
					}
					for (int j = 0; j < Users.users.get(i).getEnvGUI().getCommunityToJoinComboBox().getItemCount(); j++) {
						if (Users.users.get(i).getEnvGUI().getCommunityToJoinComboBox().getItemAt(j) == o) {
							Users.users.get(i).getEnvGUI().getCommunityToJoinComboBox().removeItemAt(j);
							break;
						}
					}
					for (int j = 0; j < Users.users.get(i).getEnvGUI().getCommunityToDeleteComboBox().getItemCount(); j++) {
						if (Users.users.get(i).getEnvGUI().getCommunityToDeleteComboBox().getItemAt(j) == o) {
							Users.users.get(i).getEnvGUI().getCommunityToDeleteComboBox().removeItemAt(j);;
							break;
						}
					}
				}
			}
		}
	}
	
	@OPERATION
	public void leaveCommunity (ActionEvent e) {
		if (envGUI.getCommunityToLeaveComboBox().getSelectedItem() != null) {
			System.out.println("Leaving Community");

			String agent = null;
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(envGUI.getCommunityToLeaveComboBox().getSelectedItem().toString())) {
					agent = Community.communities.get(i).getClass().toString();
					break;
				}
			}
			
			if (agent.replaceAll("class multiAgentProject.", "").equals("TwitterLikeCommunity")) {
				agent = "twitter_like_agent_walid" + user.replace("user_agent", "");
				signal("leaveCommunity", envGUI.getCommunityToLeaveComboBox().getSelectedItem().toString(), agent);
				JOptionPane.showMessageDialog(null, "Twitter like Community left", "Info Box", JOptionPane.INFORMATION_MESSAGE);

				envGUI.getCommunityToJoinComboBox().addItem(envGUI.getCommunityToLeaveComboBox().getSelectedItem());
				envGUI.getCommunityToLeaveComboBox().removeItem(envGUI.getCommunityToLeaveComboBox().getSelectedItem());
			}
		}
	}
}
