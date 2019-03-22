package multiAgentProject;

import java.awt.Dimension;
import java.awt.Toolkit;

import cartago.OPERATION;
import cartago.tools.GUIArtifact;

/**
 * @author Walid
 *
 */
public class Env extends GUIArtifact {
	private EnvGUI envGUI;
	
	public void init () {
		envGUI = new EnvGUI();
		
		linkActionEventToOp(envGUI.getBtnCreateCommunity(), "createCommunity");
		
		envGUI.setSize(606, 365);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		envGUI.setLocation(dim.width/2-envGUI.getSize().width/2, dim.height/2-envGUI.getSize().height/2);
		envGUI.setVisible(true);
	}
	
	@OPERATION void createCommunity () {
		if (!envGUI.getCommunityNameTextField().getText().equals("")) {
			/* Community to create is Twitter Like Community */
			if (envGUI.getCommunityTypeComboBox().getSelectedItem().toString().equals("Twitter like Community")) {
				/* Send signal to assistant agent to create community (artifact) */
				signal("createCommunity", "TwitterLikeCommunity", envGUI.getCommunityNameTextField().getText());
				/* Send signal to twitter like assistant agent to focus on created artifact */
				signal("focusOnTwitterLikeCommunity", envGUI.getCommunityNameTextField().getText());
			}
			if (envGUI.getCommunityTypeComboBox().getSelectedItem().toString().equals("Forum Like Community")) {
				/* Send signal to assistant agent to create community (artifact) */
				signal("createCommunity", "ForumLikeCommunity", envGUI.getCommunityNameTextField().getText());
				/* Send signal to twitter like assistant agent to focus on created artifact */
				signal("focusOnForumLikeCommunity", envGUI.getCommunityNameTextField().getText());
			}
		}
	}
}
