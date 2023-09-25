package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JLabel;

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
					
		final int MAX_LIST_SIZE = 50;
		//HashMap used to store Movie objects using their titles as keys
		//Initial max. capacity of 50 Movies
		HashMap<String, Movie> movieHash = new HashMap<String, Movie>(MAX_LIST_SIZE);
		
		//Each list contains Movie titles to be used as keys to access HashMap
		ArrayList<String> actionList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> horrorList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> comedyList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> documentaryList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> sciFiList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> fantasyList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> thrillerList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> dramaList = new ArrayList<String>(MAX_LIST_SIZE);
		ArrayList<String> otherList = new ArrayList<String>(MAX_LIST_SIZE);
				
		setResizable(false);
		setTitle("MainWindow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textAreaMovieTitle = new JTextArea();
		textAreaMovieTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		textAreaMovieTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		textAreaMovieTitle.setWrapStyleWord(true);
		textAreaMovieTitle.setLineWrap(true);
		textAreaMovieTitle.setToolTipText("Type title of movie here");
		textAreaMovieTitle.setBounds(160, 36, 221, 71);
		contentPane.add(textAreaMovieTitle);
		
		JTextPane textPaneTroubleshootingPrintout = new JTextPane();
		textPaneTroubleshootingPrintout.setBounds(10, 278, 522, 45);
		contentPane.add(textPaneTroubleshootingPrintout);

		//Creates a list model of genres to be displayed to user via JList
		DefaultListModel<String> listModGenre = new DefaultListModel<String>();
		listModGenre.addElement("Action");
		listModGenre.addElement("Horror");
		listModGenre.addElement("Comedy");
		listModGenre.addElement("Documentary");
		listModGenre.addElement("Sci-Fi");
		listModGenre.addElement("Fantasy");
		listModGenre.addElement("Thriller");
		listModGenre.addElement("Drama");
		listModGenre.addElement("Other");
		
		//List allows for one genre to be chosen by user
		//May be updated at a later time to allow for multiple genres
		JList<String> listGenre = new JList<String>(listModGenre);
		listGenre.setBounds(51, 148, 150, 106);
		listGenre.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listGenre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listGenre.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(listGenre);
		
		//Creates a list model of star ratings to be displayed to user via JList
		DefaultListModel<String> listModRating = new DefaultListModel<String>();
		listModRating.addElement("*");
		listModRating.addElement("**");
		listModRating.addElement("***");
		listModRating.addElement("****");
		listModRating.addElement("*****");
		
		JList<String> listRating = new JList<String>(listModRating);
		listRating.setBounds(341, 148, 150, 106);
		listRating.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listRating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listRating.setFont(new Font("Tahoma", Font.BOLD, 16));
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
		btnEntryReset.setFocusPainted(false);
		btnEntryReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Removes any in-progress selections from displayed categories
				textAreaMovieTitle.setText(null);
				listGenre.clearSelection();
				listRating.clearSelection();
			}
		});
		btnEntryReset.setToolTipText("Stop an in-progress movie addition and clear fields for next movie");
		btnEntryReset.setBounds(226, 188, 89, 23);
		contentPane.add(btnEntryReset);

		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.setToolTipText("Click after selecting all required movie information to add entry to database");
		btnAddMovie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddMovie.setFocusPainted(false);
		btnAddMovie.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddMovie.setBounds(211, 111, 120, 34);
		contentPane.add(btnAddMovie);
		
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If title, genre, and rating have been selected, construct new Movie and add to HashMap
				if(textAreaMovieTitle.getText() != null && !listGenre.isSelectionEmpty() 
				  && !listRating.isSelectionEmpty()) {
					//Adds new Movie to HashMap, uses index of star rating to determine int value
					movieHash.put(textAreaMovieTitle.getText(), new Movie(textAreaMovieTitle.getText(), 
					  listGenre.getSelectedValue(), listRating.getSelectedIndex() + 1));
					
					//TODO: add movie to category lists	
					//FIXME: TEST: used to verify that movie was added to HashMap,
					//searches Hashmap for title currently displayed in Movie Title box
					textPaneTroubleshootingPrintout.setText("Movie Added Successfully!\n" 
					  + movieHash.get(textAreaMovieTitle.getText()).toString());
				}
			}
		});
	}
}
