package multiAgentProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.tools.GUIArtifact;

/**
 * @author Walid
 *
 */
public class Env extends GUIArtifact {
	private EnvGUI envGUI;
	private String user;
	
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
		if (!envGUI.getCommunityNameTextField().getText().equals("")) {
			if (envGUI.getCommunityTypeComboBox().getSelectedItem().toString().equals("Twitter like Community")) {
				System.out.println("Creating twitter like community");
				
//				System.out.println(this.getCurrentOpAgentId().getAgentName());
//				System.out.println(Users.users);
				
				TwitterLikeCommunity tlc = new TwitterLikeCommunity();
				tlc.setCommunityName(envGUI.getCommunityNameTextField().getText());
				tlc.setOwner(this);
				
				signal("createCommunity", "TwitterLikeCommunity", envGUI.getCommunityNameTextField().getText());
				signal("focusOnTwitterLikeCommunity", envGUI.getCommunityNameTextField().getText(), user);
				JOptionPane.showMessageDialog(null, "Community created", "Info Box", JOptionPane.INFORMATION_MESSAGE);
				
				envGUI.getCommunityNameTextField().setText("");
				
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
			else if (envGUI.getCommunityTypeComboBox().getSelectedItem().toString().equals("Forum like Community")) {
				System.out.println("Creating forum like community");
				ForumLikeCommunity flc = new ForumLikeCommunity();
				flc.setCommunityName(envGUI.getCommunityNameTextField().getText());
				
				signal("createCommunity3", "ForumLikeCommunity", envGUI.getCommunityNameTextField().getText());
				signal("focusOnForumLikeCommunity", envGUI.getCommunityNameTextField().getText());
				
				Community.communities.add(flc);
				envGUI.getCommunityToJoinComboBox().addItem(flc.getCommunityName());
			}
		}
	}
	
	@OPERATION 
	void joinCommunity (ActionEvent e) {
		if (envGUI.getCommunityToJoinComboBox().getSelectedItem() != null) {
			System.out.println("Joining Community");
			
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(envGUI.getCommunityToJoinComboBox().getSelectedItem().toString())) {
					Community.communities.get(i).getMembers().add(user);
				}
			}
			
			System.out.println("Community to join : " + envGUI.getCommunityToJoinComboBox().getSelectedItem().toString());
			signal("joinCommunity", envGUI.getCommunityToJoinComboBox().getSelectedItem().toString());
			JOptionPane.showMessageDialog(null, "Community joined", "Info Box", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@OPERATION
	public void deleteCommunity (ActionEvent e) {
		if (envGUI.getCommunityToDeleteComboBox().getSelectedItem() != null) {
			System.out.println("Deleting Community");
			signal("deleteCommunity", envGUI.getCommunityToDeleteComboBox().getSelectedItem().toString());
			
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(envGUI.getCommunityToDeleteComboBox().getSelectedItem().toString())) {
					Community.communities.remove(i);
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "Community deleted", "Info Box", JOptionPane.INFORMATION_MESSAGE);

			envGUI.getCommunityToJoinComboBox().removeItem(envGUI.getCommunityToDeleteComboBox().getSelectedItem());
			envGUI.getCommunityToLeaveComboBox().removeItem(envGUI.getCommunityToDeleteComboBox().getSelectedItem());
			envGUI.getCommunityToDeleteComboBox().removeItem(envGUI.getCommunityToDeleteComboBox().getSelectedItem());
		}
	}
	
	@OPERATION
	public void leaveCommunity (ActionEvent e) {
		if (envGUI.getCommunityToLeaveComboBox().getSelectedItem() != null) {
			System.out.println("Leaving Community");
			signal("leaveCommunity", envGUI.getCommunityToLeaveComboBox().getSelectedItem().toString());
			JOptionPane.showMessageDialog(null, "Community left", "Info Box", JOptionPane.INFORMATION_MESSAGE);
			
			envGUI.getCommunityToLeaveComboBox().removeItem(envGUI.getCommunityToLeaveComboBox().getSelectedItem());
		}
	}
}
