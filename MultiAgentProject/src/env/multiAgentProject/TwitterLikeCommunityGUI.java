package multiAgentProject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

/**
 * @author Walid
 *
 */
public class TwitterLikeCommunityGUI extends JFrame {
	private JTextPane textPane;
	private JButton btnPostMessage;

	public JTextPane getTextPane () {
		return textPane;
	}
	
	public JButton getBtnPostMessage() {
		return btnPostMessage;
	}
	
	public TwitterLikeCommunityGUI() {
		setTitle("Twitter Like Community");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMessageToPost = new JLabel("Message to post :");
		lblMessageToPost.setBounds(97, 118, 163, 14);
		panel.add(lblMessageToPost);
		
		textPane = new JTextPane();
		textPane.setBounds(220, 78, 286, 95);
		panel.add(textPane);
		
		btnPostMessage = new JButton("Post Message");
		btnPostMessage.setBounds(219, 225, 138, 23);
		panel.add(btnPostMessage);
	}
}
