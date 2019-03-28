package multiAgentProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import cartago.tools.GUIArtifact;

/**
 * @author Walid
 *
 */
public class TwitterLikeCommunityArtifact extends GUIArtifact {
	private TwitterLikeCommunityGUI t;
	
	public void init (String s) {
		t = new TwitterLikeCommunityGUI();
		t.setSize(606, 365);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		t.setLocation(dim.width/2-t.getSize().width/2, dim.height/2-t.getSize().height/2);
		t.setVisible(true);
		
		this.init();
	}
}
