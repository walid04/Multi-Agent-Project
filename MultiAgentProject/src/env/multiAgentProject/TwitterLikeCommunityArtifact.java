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
	private TwitterLikeCommunity tlc2;
	private String community;
	
	public String getCommunity () {
		return community;
	}
	
	public void init (String s) {
		tlc = new TwitterLikeCommunityGUI();
		tlc.setSize(606, 365);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		tlc.setLocation(dim.width/2-tlc.getSize().width/2, dim.height/2-tlc.getSize().height/2);
		
		linkActionEventToOp(tlc.getBtnPostMessage(), "postMessage");
		linkActionEventToOp(tlc.getBtnDeleteMessage(), "deleteMessage");
		
		tlc.setTitle(s);
		community = s;
		
		tlc.setVisible(true);
		
		this.init();
	}
	
	@OPERATION
	public void postMessage (ActionEvent e) {
		if (!tlc.getTextPane().getText().equals("")) {
			System.out.println("Posting message to community members");
			
			for (int i = 0; i < Community.communities.size(); i++) {
				if (Community.communities.get(i).getCommunityName().equals(community)) {
					for (int k = 0; k < Community.communities.get(i).getMembers().size(); k++) {
						System.out.println("Members of community : " + Community.communities.get(i).getMembers().get(k));
						System.out.println("Owner of community : " + Community.communities.get(i).getOwner().getUser());
						signal("postMessage", tlc.getTextPane().getText(), Community.communities.get(i).getOwner().getUser(), Community.communities.get(i).getMembers().get(k));
						JOptionPane.showMessageDialog(null, "Message posted", "Info Box", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
//			signal("postMessage", tlc.getTextPane().getText());
			
//			tlc2.getMessages().add(tlc.getTextPane().getText());
			tlc.getPostedMessagesComboBox().addItem(tlc.getTextPane().getText());
			tlc.getTextPane().setText("");
		}
	}
	
	@OPERATION
	public void deleteMessage (ActionEvent e) {
		if (tlc.getPostedMessagesComboBox().getSelectedItem() != null) {
			System.out.println("Deleting message");
//			for (int i = 0; i < tlc2.getMessages().size(); i++) {
//				if (tlc2.getMessages().get(i).equals(tlc.getPostedMessagesComboBox().getSelectedItem())) {
//					tlc2.getMessages().remove(i);
//					break;
//				}
//			}
			tlc.getPostedMessagesComboBox().removeItem(tlc.getPostedMessagesComboBox().getSelectedItem());
			JOptionPane.showMessageDialog(null, "Message deleted", "Info Box", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
