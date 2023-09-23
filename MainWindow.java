package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Point;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setResizable(false);
		setTitle("MainWindow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textAreaMovieTitle = new JTextArea();
		textAreaMovieTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		textAreaMovieTitle.setWrapStyleWord(true);
		textAreaMovieTitle.setLineWrap(true);
		textAreaMovieTitle.setToolTipText("Type title of movie here, then click \"Add Movie\"");
		textAreaMovieTitle.setBounds(115, 11, 311, 58);
		contentPane.add(textAreaMovieTitle);
		
		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.setFocusPainted(false);
		btnAddMovie.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddMovie.setBounds(203, 80, 135, 34);
		contentPane.add(btnAddMovie);
		
		JTextPane textPaneTroubleshootingPrintout = new JTextPane();
		textPaneTroubleshootingPrintout.setBounds(86, 169, 369, 34);
		contentPane.add(textPaneTroubleshootingPrintout);
		
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Disable text box on button click if title was entered
				//Prevents title alteration during Movie creation
				if(textAreaMovieTitle.getText() != null) {
					textAreaMovieTitle.setEnabled(false);
					//FIXME: Used for testing
					textPaneTroubleshootingPrintout.setText(textAreaMovieTitle.getText());
					
				}

				
			}
		});
		
		

	}
}
