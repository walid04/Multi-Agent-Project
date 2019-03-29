package multiAgentProject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 * @author Walid
 *
 */
public class TwitterLikeCommunityGUI extends JFrame {
	private JTextPane textPane;
	private JButton btnPostMessage;
	private JComboBox communityComboBox;
	private JComboBox postedMessagesComboBox;
	private JButton btnDeleteMessage;

	public JTextPane getTextPane () {
		return textPane;
	}
	
	public JButton getBtnPostMessage () {
		return btnPostMessage;
	}
	
	public JComboBox getCommunityComboBox () {
		return communityComboBox;
	}
	
	public JButton getBtnDeleteMessage () {
		return btnDeleteMessage;
	}
	
	public JComboBox getPostedMessagesComboBox () {
		return postedMessagesComboBox;
	}
	
	public TwitterLikeCommunityGUI() {
		setTitle("Twitter Like Community");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMessageToPost = new JLabel("Message to post :");
		lblMessageToPost.setBounds(114, 54, 163, 14);
		panel.add(lblMessageToPost);
		
		textPane = new JTextPane();
		textPane.setBounds(21, 79, 286, 95);
		panel.add(textPane);
		
		btnPostMessage = new JButton("Post Message");
		btnPostMessage.setBounds(91, 224, 138, 23);
		panel.add(btnPostMessage);
		
		communityComboBox = new JComboBox();
		communityComboBox.setBounds(291, 29, 28, 20);
		panel.add(communityComboBox);
		
		postedMessagesComboBox = new JComboBox();
		postedMessagesComboBox.setBounds(332, 79, 236, 95);
		panel.add(postedMessagesComboBox);
		
		JLabel lblPostedMessages = new JLabel("Posted Messages");
		lblPostedMessages.setBounds(405, 54, 156, 14);
		panel.add(lblPostedMessages);
		
		btnDeleteMessage = new JButton("Delete Message");
		btnDeleteMessage.setBounds(382, 224, 152, 23);
		panel.add(btnDeleteMessage);
		
		communityComboBox.setVisible(false);
	}
}
