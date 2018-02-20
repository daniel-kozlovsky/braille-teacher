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
import java.awt.Font;
import java.awt.Color;

/*Audio recording creator class
 * Open this as a new panel for the user to record
 */

public class AudioRecorder extends JPanel {

	private static final long serialVersionUID = 1L;

	// audio resources
	private String saveLoc = "AudioFiles/";
	private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	private TargetDataLine dataLine;
	AudioFormat format;
	boolean stopCapture = false;
	File audioFile;

	// components
	private JButton btnRecord, btnStop;
	private JTextField txtFileName;
	private Box verticalBox;
	private Box horizontalBox;
	private Box horizontalBox_1;
	private JLabel lblNewLabel;
	private Component verticalStrut;
	private Box horizontalBox_2;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component verticalStrut_4;
	private Component horizontalStrut_2;
	private JLabel lblRecording;

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

		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		horizontalStrut = Box.createHorizontalStrut(40);
		horizontalBox_2.add(horizontalStrut);

		horizontalStrut_1 = Box.createHorizontalStrut(40);
		horizontalBox_2.add(horizontalStrut_1);

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		btnStop = new JButton("Stop");
		horizontalBox.add(btnStop);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		// reference
		btnRecord = new JButton("Record");
		horizontalBox.add(btnRecord);

		lblRecording = new JLabel("Recording...");
		lblRecording.setForeground(Color.RED);
		lblRecording.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRecording.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRecording.setVisible(false);
		verticalBox.add(lblRecording);

		// listeners
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					recordAndSave(txtFileName.getText());
				} catch (NumberFormatException | IOException e) {
					System.out.print("Record failed, Exception: " + e.getMessage() + " due to " + e.getCause());
					e.printStackTrace();
				}
			}
		});

		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Stopped.");
				lblRecording.setVisible(false);
				dataLine.stop();
				dataLine.close();
				dataLine.flush();
			}
		});
	}

	public void recordAndSave(String fileName) throws IOException {

		lblRecording.setVisible(true);

		audioFile = new File(saveLoc + fileName + ".wav");

		if (audioFile.exists() != true) {
			audioFile.createNewFile();
			System.out.println("Created the file.");
		}
		new timer().start();
	}

	class timer extends Thread {
		public void run() {

			try {
				format = new AudioFormat(16000, 16, 2, true, true);
				DataLine.Info lineInfo = new DataLine.Info(TargetDataLine.class, format);
				dataLine = (TargetDataLine) AudioSystem.getLine(lineInfo);
				dataLine.open(format);
				dataLine.start();
				AudioInputStream stream = new AudioInputStream(dataLine);
				AudioSystem.write(stream, fileType, audioFile);

			} catch (LineUnavailableException | IOException e) {
				if(e instanceof LineUnavailableException) {
					System.out.println("Line input not accessible.");
				}
				else if(e instanceof IOException) {
					System.out.println("Unable to access audio file.");
				}
				e.printStackTrace();
			}
		}
	}

}
