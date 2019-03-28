package multiAgentProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import cartago.OPERATION;
import cartago.tools.GUIArtifact;

/**
 * @author Walid
 *
 */
public class TwitterLikeCommunityArtifact extends GUIArtifact {
	private TwitterLikeCommunityGUI tlc;
	
	public void init (String s) {
		tlc = new TwitterLikeCommunityGUI();
		tlc.setSize(606, 365);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		tlc.setLocation(dim.width/2-tlc.getSize().width/2, dim.height/2-tlc.getSize().height/2);
		
		linkActionEventToOp(tlc.getBtnPostMessage(), "postMessage");
		linkActionEventToOp(tlc.getBtnDeleteMessage(), "deleteMessage");
		
		tlc.setVisible(true);
		
		this.init();
	}
	
	@OPERATION
	public void postMessage (ActionEvent e) {
		if (!tlc.getTextPane().getText().equals("")) {
			System.out.println("Posting message to community members");
			signal("postMessage", tlc.getTextPane().getText());
			
			tlc.getPostedMessagesComboBox().addItem(tlc.getTextPane().getText());
		}
	}
	
	@OPERATION
	public void deleteMessage (ActionEvent e) {
		
	}
}
