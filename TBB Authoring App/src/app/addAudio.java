package app;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
public class addAudio extends JFrame {
    private JFrame parent= new JFrame ();
    private JTextField textField;
    private JTextField textField_1;
    
    public addAudio (JFrame parent)
    
    {
        this.parent=parent;
        
        JLabel lblImportNewFile = new JLabel("Import a Audio file");
        lblImportNewFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JButton btnSelectFile = new JButton("select file");
        btnSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel lblRecourdNewAudio = new JLabel("Recourd  new Audio file");
        lblRecourdNewAudio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JLabel lblFileName = new JLabel("file name");
        lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JLabel lblFileDuration = new JLabel("file duration");
        lblFileDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        JButton btnNewButton = new JButton("Add");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(131)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblRecourdNewAudio, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                .addComponent(btnSelectFile, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                            .addGap(66))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblFileDuration, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(157))
                                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                    .addComponent(lblFileName, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(textField, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))))
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                            .addGap(25)
                            .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addGap(104)
                            .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
                    .addGap(35))
                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                    .addGap(154)
                    .addComponent(lblImportNewFile, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGap(111))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(22)
                    .addComponent(lblImportNewFile, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addGap(46)
                    .addComponent(btnSelectFile, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addGap(69)
                    .addComponent(lblRecourdNewAudio, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblFileName, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(9)
                            .addComponent(textField)))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblFileDuration, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(39)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .addGap(22))
        );
        getContentPane().setLayout(groupLayout);
        this.parent.setSize(500,500);
        this.parent.setTitle("Add Audio");
        this.parent.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }
    public void initComponents()
    {
    
    }
}