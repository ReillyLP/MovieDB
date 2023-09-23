package main;

import main.Movie;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JLabel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//HashMap used to store Movie objects using their titles as keys
		//Initial max. capacity of 50 Movies
		HashMap<String, Movie> movieHashM = new HashMap<String, Movie>(50);
		
		//Creates a list of genre lists
		ArrayList<ArrayList<String>> genreList = new ArrayList<ArrayList<String>>(); 
		
		//Creates default genres to be provided to user
		//Each genre list contains Movie titles to be used as keys to access HashMap
		//Max. capacity for each genre is 50 titles
		ArrayList<String> actionList = new ArrayList<String>(50);
		ArrayList<String> horrorList = new ArrayList<String>(50);
		ArrayList<String> comedyList = new ArrayList<String>(50);
		ArrayList<String> documentaryList = new ArrayList<String>(50);
		ArrayList<String> sciFiList = new ArrayList<String>(50);
		ArrayList<String> fantasyList = new ArrayList<String>(50);
		ArrayList<String> thrillerList = new ArrayList<String>(50);
		ArrayList<String> dramaList = new ArrayList<String>(50);
		ArrayList<String> otherList = new ArrayList<String>(50);
		
		genreList.add(actionList);
		genreList.add(horrorList);
		genreList.add(comedyList);
		genreList.add(documentaryList);
		genreList.add(sciFiList);
		genreList.add(fantasyList);
		genreList.add(thrillerList);
		genreList.add(dramaList);
		genreList.add(otherList);
		
		
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
		textAreaMovieTitle.setToolTipText("Type title of movie here");
		textAreaMovieTitle.setBounds(115, 34, 311, 58);
		contentPane.add(textAreaMovieTitle);
		
		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.setToolTipText("Click after selecting all required movie information to add entry to database");
		btnAddMovie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddMovie.setFocusPainted(false);
		btnAddMovie.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddMovie.setBounds(203, 103, 135, 34);
		contentPane.add(btnAddMovie);
		
		JTextPane textPaneTroubleshootingPrintout = new JTextPane();
		textPaneTroubleshootingPrintout.setBounds(86, 289, 369, 34);
		contentPane.add(textPaneTroubleshootingPrintout);
		
		JList listGenre = new JList();
		listGenre.setBounds(58, 148, 135, 106);
		contentPane.add(listGenre);
		
		JList listRating = new JList();
		listRating.setBounds(348, 148, 135, 106);
		contentPane.add(listRating);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGenre.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenre.setBounds(93, 123, 64, 14);
		contentPane.add(lblGenre);
		
		JLabel lblTitle = new JLabel("Movie Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(203, 11, 135, 14);
		contentPane.add(lblTitle);
		
		JLabel lblRating = new JLabel("Star Rating");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRating.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRating.setHorizontalAlignment(SwingConstants.CENTER);
		lblRating.setBounds(383, 123, 64, 14);
		contentPane.add(lblRating);
		
		JButton btnEntryReset = new JButton("Reset");
		btnEntryReset.setToolTipText("Stop an in-progress movie addition and clear fields for next movie");
		btnEntryReset.setBounds(226, 145, 89, 23);
		contentPane.add(btnEntryReset);
		
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Disable text box on button click if title was entered
				//Prevents title alteration during Movie creation
				if(textAreaMovieTitle.getText() != null && ) {
					textAreaMovieTitle.setEnabled(false);
					//FIXME: Used for testing
					textPaneTroubleshootingPrintout.setText(textAreaMovieTitle.getText());
					
				}

				
			}
		});
		
		

	}
}
