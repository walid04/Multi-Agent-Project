package multiAgentProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cartago.OPERATION;
import cartago.tools.GUIArtifact;

/**
 * @author Walid
 *
 */
public class TwitterLikeCommunityArtifact extends GUIArtifact {
	private TwitterLikeCommunityGUI tlc;
	private String communityOwner;
	private String community;
	
	public String getCommunity () {
		return community;
	}
	
	public String getOwner () {
		return communityOwner;
	}
	
	public void init (String s) {
		tlc = new TwitterLikeCommunityGUI();
		tlc.setSize(606, 365);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		tlc.setLocation(dim.width/2-tlc.getSize().width/2, dim.height/2-tlc.getSize().height/2);
		
		linkActionEventToOp(tlc.getBtnPostMessage(), "postMessage");
		linkActionEventToOp(tlc.getBtnDeleteMessage(), "deleteMessage");
		
		tlc.setTitle(s);
		String[] separator = s.split("-");
		community = separator[0];
		communityOwner = separator[1];
		
		tlc.setVisible(true);
		
		this.init();
	}
	
	@OPERATION
	public void postMessage (ActionEvent e) {
		if (!tlc.getTextPane().getText().equals("")) {
			System.out.println("Posting message to twitter like community members");
			
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(community)) {
					for (int k = 0; k < Community.communities.get(i).getMembers().size(); k++) {
						String sender = "twitter_like_agent_walid" + Community.communities.get(i).getOwner().getUser().replace("user_agent", "");
						String receiver = "twitter_like_agent_walid" +  Community.communities.get(i).getMembers().get(k).replace("user_agent", "");

						if (sender.equals(communityOwner)) {
							signal("postMessage", tlc.getTextPane().getText(), sender, receiver);
							JOptionPane.showMessageDialog(null, "Message posted", "Info Box", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
			
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(community)) {
					Community.communities.get(i).getMessages().add(tlc.getTextPane().getText());
					break;
				}
			}
			
			for (int i = 0; i < tlc.getPostedMessagesComboBox().getItemCount(); i++) {
				tlc.getPostedMessagesComboBox().removeItemAt(i);
			}
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(community)) {
					if (Community.communities.get(i).getMessages() != null) {
						for (int j = 0; j < Community.communities.get(i).getMessages().size(); j++) {
							tlc.getPostedMessagesComboBox().addItem(Community.communities.get(i).getMessages().get(j));
						}
						break;
					}
				}
			}
			
			tlc.getTextPane().setText("");
		}
	}
	
	@OPERATION
	public void deleteMessage (ActionEvent e) {
		if (tlc.getPostedMessagesComboBox().getSelectedItem() != null) {
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(community)) {
					for (int j = 0; j < Community.communities.get(i).getMessages().size(); i++) {
						if (Community.communities.get(i).getMessages().get(j).equals(tlc.getPostedMessagesComboBox().getSelectedItem())) {
							Community.communities.get(i).getMessages().remove(j);
							break;
						}
					}
				}
			}
			
			System.out.println("Deleting message");
			tlc.getPostedMessagesComboBox().removeItem(tlc.getPostedMessagesComboBox().getSelectedItem());
			JOptionPane.showMessageDialog(null, "Message deleted", "Info Box", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
