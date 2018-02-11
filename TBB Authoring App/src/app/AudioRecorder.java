package app;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.FlowLayout;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;

public class AudioRecorder extends JPanel {

	private static final long serialVersionUID = 1L;

	// audio resources
	private String saveLoc = "AudioFiles/";
	private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	private TargetDataLine dataLine;

	// components
	private JButton btnRecord;
	private JTextField txtDuration, txtFileName;
	private Box verticalBox;
	private Box horizontalBox;
	private Box horizontalBox_1;
	private JLabel lblNewLabel;
	private JLabel lblRecordingDuration;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Box horizontalBox_2;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component verticalStrut_3;
	private Component verticalStrut_4;

	public AudioRecorder() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		verticalBox = Box.createVerticalBox();
		add(verticalBox);

		lblNewLabel = new JLabel("Recording Name:");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox.add(lblNewLabel);
		
		verticalStrut_4 = Box.createVerticalStrut(8);
		verticalBox.add(verticalStrut_4);

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		txtFileName = new JTextField();
		txtFileName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFileName.setText("Recording_01");
		horizontalBox_1.add(txtFileName);

		// deploy
		txtFileName.setColumns(16);
		
		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		lblRecordingDuration = new JLabel("Recording Duration(sec.):");
		lblRecordingDuration.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRecordingDuration.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox.add(lblRecordingDuration);
		
		verticalStrut_3 = Box.createVerticalStrut(8);
		verticalBox.add(verticalStrut_3);
		
		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		horizontalStrut = Box.createHorizontalStrut(40);
		horizontalBox_2.add(horizontalStrut);
		txtDuration = new JTextField();
		horizontalBox_2.add(txtDuration);
		txtDuration.setHorizontalAlignment(SwingConstants.CENTER);
		txtDuration.setColumns(3);
		txtDuration.setText("2");
		
		horizontalStrut_1 = Box.createHorizontalStrut(40);
		horizontalBox_2.add(horizontalStrut_1);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		// reference
		btnRecord = new JButton("Record");
		horizontalBox.add(btnRecord);

		// listeners
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					recordAndSave(Integer.parseInt(txtDuration.getText()), txtFileName.getText());
				} catch (NumberFormatException | IOException e) {
					System.out.print("Record failed, Exception: " + e.getMessage() + " due to " + e.getCause());
					e.printStackTrace();
				}
			}
		});
	}

	private void recordAndSave(int duration, String fileName) throws IOException {

		File audioFile = new File(saveLoc + fileName + ".wav");

		if (audioFile.exists() != true) {
			audioFile.createNewFile();
			System.out.println("Created file.");
		}

		Thread timer = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(duration * 1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				if (dataLine.isActive()) {
					System.out.println("Recording finished.");
					dataLine.stop();
					dataLine.close();
					dataLine.flush();
				}
			}
		});

		timer.start();

		try {
			AudioFormat format = new AudioFormat(16000, 16, 2, true, true);
			DataLine.Info lineInfo = new DataLine.Info(TargetDataLine.class, format);

			if (!AudioSystem.isLineSupported(lineInfo)) {
				System.out.print("Dataline not supported.");
				dataLine.stop();
				dataLine.close();
				dataLine.flush();
			}

			dataLine = (TargetDataLine) AudioSystem.getLine(lineInfo);
			dataLine.open(format);
			dataLine.start();

			AudioInputStream stream = new AudioInputStream(dataLine);
			AudioSystem.write(stream, fileType, audioFile);

		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
