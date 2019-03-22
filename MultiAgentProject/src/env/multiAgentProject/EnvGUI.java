package multiAgentProject;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * @author Walid
 *
 */
public class EnvGUI extends JFrame {
	private JTextField communityNameTextField;
	private JComboBox communityTypeComboBox;
	private JComboBox communityToJoinComboBox;
	private JComboBox communityToDeleteComboBox;
	private JComboBox communityToLeaveComboBox;
	private JButton btnCreateCommunity;
	private JButton btnJoinCommunity;
	private JButton btnDeleteCommunity;
	private JButton btnLeaveCommunity;
	
	public JTextField getCommunityNameTextField () {
		return communityNameTextField;
	}
	
	public JComboBox getCommunityTypeComboBox () {
		return communityTypeComboBox;
	}
	
	public JComboBox getCommunityToJoinComboBox () {
		return communityToJoinComboBox;
	}
	
	public JComboBox getCommunityToDeleteComboBox () {
		return communityToDeleteComboBox;
	}
	
	public JComboBox getCommunityToLeaveComboBox () {
		return communityToLeaveComboBox;
	}
	
	public JButton getBtnCreateCommunity () {
		return btnCreateCommunity;
	}
	
	public JButton getBtnDeleteCOmmunity () {
		return btnDeleteCommunity;
	}
	
	public JButton getBtnLeaveCommunity () {
		return btnLeaveCommunity;
	}
	
	public EnvGUI() {
		setTitle("User Community Handling Interface");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelCreateCommunity = new JPanel();
		tabbedPane.addTab("Create Community", null, panelCreateCommunity, null);
		tabbedPane.setEnabledAt(0, true);
		panelCreateCommunity.setLayout(null);
		
		JLabel lblCommunityName = new JLabel("Community name :");
		lblCommunityName.setBounds(133, 68, 107, 14);
		panelCreateCommunity.add(lblCommunityName);
		
		communityNameTextField = new JTextField();
		communityNameTextField.setBounds(274, 65, 155, 20);
		panelCreateCommunity.add(communityNameTextField);
		communityNameTextField.setColumns(10);
		
		JLabel lblType = new JLabel("Community type :");
		lblType.setBounds(133, 119, 107, 14);
		panelCreateCommunity.add(lblType);
		
		communityTypeComboBox = new JComboBox();
		communityTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Twitter like Community","Forum Like Community"}));
		communityTypeComboBox.setBounds(274, 116, 155, 20);
		panelCreateCommunity.add(communityTypeComboBox);
		
		btnCreateCommunity = new JButton("Create Community");
		btnCreateCommunity.setBounds(208, 180, 145, 23);
		panelCreateCommunity.add(btnCreateCommunity);
		
		JPanel panelJoinCommunity = new JPanel();
		tabbedPane.addTab("Join Community", null, panelJoinCommunity, null);
		panelJoinCommunity.setLayout(null);
		
		JLabel lblCommunityToJoin = new JLabel("Community to join :");
		lblCommunityToJoin.setBounds(141, 80, 117, 14);
		panelJoinCommunity.add(lblCommunityToJoin);
		
		communityToJoinComboBox = new JComboBox();
		communityToJoinComboBox.setBounds(309, 77, 145, 20);
		panelJoinCommunity.add(communityToJoinComboBox);
		
		btnJoinCommunity = new JButton("Join Community");
		btnJoinCommunity.setBounds(220, 157, 130, 23);
		panelJoinCommunity.add(btnJoinCommunity);
		
		JPanel panelDeleteCommunity = new JPanel();
		tabbedPane.addTab("Delete Community", null, panelDeleteCommunity, null);
		panelDeleteCommunity.setLayout(null);
		
		JLabel lblMyCommunities = new JLabel("Community to delete :");
		lblMyCommunities.setBounds(132, 92, 130, 14);
		panelDeleteCommunity.add(lblMyCommunities);
		
		communityToDeleteComboBox = new JComboBox();
		communityToDeleteComboBox.setBounds(293, 89, 143, 20);
		panelDeleteCommunity.add(communityToDeleteComboBox);
		
		btnDeleteCommunity = new JButton("Delete Community");
		btnDeleteCommunity.setBounds(210, 165, 162, 23);
		panelDeleteCommunity.add(btnDeleteCommunity);
		
		JPanel panelLeaveCommunity = new JPanel();
		tabbedPane.addTab("Leave Community", null, panelLeaveCommunity, null);
		panelLeaveCommunity.setLayout(null);
		
		JLabel lblCommunitiesIAm = new JLabel("Community to leave :");
		lblCommunitiesIAm.setBounds(135, 88, 157, 14);
		panelLeaveCommunity.add(lblCommunitiesIAm);
		
		communityToLeaveComboBox = new JComboBox();
		communityToLeaveComboBox.setBounds(301, 85, 144, 20);
		panelLeaveCommunity.add(communityToLeaveComboBox);
		
		btnLeaveCommunity = new JButton("Leave Community");
		btnLeaveCommunity.setBounds(214, 152, 148, 23);
		panelLeaveCommunity.add(btnLeaveCommunity);
	}
}
