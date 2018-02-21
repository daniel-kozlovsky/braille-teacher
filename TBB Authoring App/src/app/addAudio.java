package app;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;

import java.awt.Cursor;
import javax.swing.JList;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.TargetDataLine;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;

public class addAudio extends JFrame {
	private JFrame parent= new JFrame ();
	private JTextField textField;
	private JTextField textField_1;
	

	public addAudio ()
	
	{
		this.parent=new JFrame();
		this.setSize(400, 500);
		
		JLabel lblImportNewFile = new JLabel("Import a Audio file");
		lblImportNewFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportNewFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnSelectFile = new JButton("Select file");
		btnSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblRecourdNewAudio = new JLabel("Recourd new Audio file");
		lblRecourdNewAudio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecourdNewAudio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblFileName = new JLabel("file name");
		lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblFileDuration = new JLabel("file duration");
		lblFileDuration.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileDuration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button = new JButton("Record");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFileName, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
								.addComponent(lblFileDuration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
							.addGap(1206)))
					.addGap(1065))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(lblImportNewFile, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(2251, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(btnSelectFile, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(2276, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRecourdNewAudio, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(2193, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(2196, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblImportNewFile, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addGap(37)
					.addComponent(btnSelectFile, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRecourdNewAudio, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFileName, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFileDuration, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		getContentPane().setLayout(groupLayout);

		this.parent.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	public void initComponents()
	{
	
	}
}
	