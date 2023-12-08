/** 
This class represents the main window of the application, providing a graphical user interface
for users to interact with the movie database. It includes features for adding, searching, and
managing movie entries. The GUI is developed using Java Swing components.
Traceability to Requirements:
- Req. 3.1.1, 3.1.2
- Req 3.2.1, 3.2.2, 3.2.3
- Req. 3.3.1, 3.3.2
- Req. 3.4.2
- Req. 3.5.1, 3.5.2
- Req. 3.6.1
*/


package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** 
The MainWindow class extends JFrame and serves as the main window of the application. 
It includes various Swing compeonents for user interaction and manages overall functionality
of the User-Generated Movie Database.
*/

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, welcomePanel, entryPanel;
	final int MAX_LIST_SIZE = 50;
	// HashMap used to store Movie objects using their titles as keys
	// Initial max. capacity of 50 Movies
	private HashMap<String, Movie> movieHash = new HashMap<String, Movie>(MAX_LIST_SIZE);
	
	// Each list contains Movie titles to be used as keys to access HashMap
	private ArrayList<String> actionList, horrorList, comedyList, documentaryList, sciFiList;
	private ArrayList<String> fantasyList, thrillerList, dramaList, otherList, titleList;
	private ArrayList<String> oneStarList, twoStarList, threeStarList, fourStarList, fiveStarList;
	
	/**
	The main method serves as the entry point for the User-Generated Movie Database Application.
	It initializes the main window by creating an instance of the MainWindow class and making it visible.
 	The method uses EventQueue.invokeLater to ensure the GUI is constructed and modified on the 
	Event Dispatch Thread to avoid potential thread-safety issues.
	Traceability to Requirements:
 	- Req. 2.1, 2.2, 2.3
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Creating an instance of the MainWindow class
					MainWindow frame = new MainWindow();
					
					// Making main window visible
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	The constructor initializes the main components of the User-Generated Movie Database Application.
  	It sets up data structures, creates the main window, and initializes the welcome and entry panels.
	Traceability to Requirements:
	Req 3.1, 3.2, 3.3
	 */

	public MainWindow() {
		// Initialization of data structures
		movieHash = new HashMap<String, Movie>(MAX_LIST_SIZE);
		actionList = new ArrayList<String>(MAX_LIST_SIZE);
		horrorList = new ArrayList<String>(MAX_LIST_SIZE);
		comedyList = new ArrayList<String>(MAX_LIST_SIZE);
		documentaryList = new ArrayList<String>(MAX_LIST_SIZE);
		sciFiList = new ArrayList<String>(MAX_LIST_SIZE);
		fantasyList = new ArrayList<String>(MAX_LIST_SIZE);
		thrillerList = new ArrayList<String>(MAX_LIST_SIZE);
		dramaList = new ArrayList<String>(MAX_LIST_SIZE);
		otherList = new ArrayList<String>(MAX_LIST_SIZE);
		titleList = new ArrayList<String>(MAX_LIST_SIZE);
		
		oneStarList = new ArrayList<String>(MAX_LIST_SIZE);
		twoStarList = new ArrayList<String>(MAX_LIST_SIZE);
		threeStarList = new ArrayList<String>(MAX_LIST_SIZE);
		fourStarList = new ArrayList<String>(MAX_LIST_SIZE);
		fiveStarList = new ArrayList<String>(MAX_LIST_SIZE);
		
		// Setting up main window properties		
		setResizable(false);
		setTitle("MainWindow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 369);
		
		//Main container for the application
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//First panel user sees. Asks if new or previous database should be used.
		welcomePanel = new JPanel();
		welcomePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		welcomePanel.setBounds(0, 0, 542, 330);
		welcomePanel.setLayout(null);
		
		contentPane.add(welcomePanel);
		welcomePanel.setEnabled(true);
		welcomePanel.setVisible(true);
		
		// Entry panel setup
		entryPanel = new JPanel();
		entryPanel.setVisible(false);
		entryPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		entryPanel.setBounds(0, 0, 542, 330);
		entryPanel.setLayout(null);
		entryPanel.setEnabled(false);
		
		contentPane.add(entryPanel);
		
		// Labels and buttons for the welcome panel
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblWelcome.setBounds(208, 39, 125, 33);
		welcomePanel.add(lblWelcome);
		
		JLabel lblWelcomeLn1 = new JLabel("Would you like to create a new database,");
		lblWelcomeLn1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcomeLn1.setBounds(118, 106, 305, 26);
		welcomePanel.add(lblWelcomeLn1);
		
		JLabel lblWelcomeLn2 = new JLabel("or load a previous database file?");
		lblWelcomeLn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcomeLn2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLn2.setBounds(123, 132, 295, 26);
		welcomePanel.add(lblWelcomeLn2);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(welcomePanel, 
						"Starting new database\n You may now add your movies!");
				//Removes ability to see or interact with welcome panel
				welcomePanel.setVisible(false);
				welcomePanel.setEnabled(false);
				entryPanel.setVisible(true);
				entryPanel.setEnabled(true);
				int userChoice = JOptionPane.showConfirmDialog(contentPane, "Would you like to choose a custom background color?"
						+ "\n(background can also be changed at a later time using the \"Window\" menu)",
						"Custom Color", JOptionPane.YES_NO_OPTION);
				if (userChoice == JOptionPane.YES_OPTION) {
					entryPanel.setBackground(JColorChooser.showDialog(contentPane, "Choose Color", entryPanel.getBackground()));
				}
			}
		});
		
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNew.setBounds(118, 209, 104, 38);
		welcomePanel.add(btnNew);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLoad.setBounds(319, 209, 104, 38);
		welcomePanel.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(welcomePanel, 
						"Attempting to load database...");
				//Removes ability to see or interact with welcome panel
				welcomePanel.setVisible(false);
				welcomePanel.setEnabled(false);
				entryPanel.setVisible(true);
				entryPanel.setEnabled(true);
				loadFile(false, true);
			}
		});

		// Text area, lists, labels for entry panel
		JTextArea textAreaMovieTitle = new JTextArea();
		textAreaMovieTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		textAreaMovieTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		textAreaMovieTitle.setWrapStyleWord(true);
		textAreaMovieTitle.setLineWrap(true);
		textAreaMovieTitle.setToolTipText("Type movie title here");
		textAreaMovieTitle.setBounds(159, 57, 221, 71);
		entryPanel.add(textAreaMovieTitle);

		// Creates a list model of genres to be displayed to user via JList
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
		
		// List allows for one genre to be chosen by user
		// May be updated at a later time to allow for multiple genres
		JList<String> listGenre = new JList<String>(listModGenre);
		listGenre.setBounds(50, 169, 150, 106);
		listGenre.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listGenre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listGenre.setFont(new Font("Tahoma", Font.BOLD, 10));
		entryPanel.add(listGenre);
		
		//Creates a list model of star ratings to be displayed to user via JList
		DefaultListModel<String> listModRating = new DefaultListModel<String>();
		listModRating.addElement("*");
		listModRating.addElement("**");
		listModRating.addElement("***");
		listModRating.addElement("****");
		listModRating.addElement("*****");
		
		JList<String> listRating = new JList<String>(listModRating);
		listRating.setBounds(340, 169, 150, 106);
		listRating.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listRating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listRating.setFont(new Font("Tahoma", Font.BOLD, 16));
		entryPanel.add(listRating);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenre.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenre.setBounds(93, 135, 64, 37);
		entryPanel.add(lblGenre);
		
		JLabel lblTitle = new JLabel("Movie Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(202, 32, 135, 23);
		entryPanel.add(lblTitle);
		
		JLabel lblRating = new JLabel("Star Rating");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRating.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRating.setHorizontalAlignment(SwingConstants.CENTER);
		lblRating.setBounds(340, 135, 150, 33);
		entryPanel.add(lblRating);
		
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
		
		btnEntryReset.setToolTipText("Stop an in-progress movie addition and clear fields");
		btnEntryReset.setBounds(225, 193, 89, 23);
		entryPanel.add(btnEntryReset);

		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.setToolTipText("Click after selecting all required movie categoriese to add movie to database");
		btnAddMovie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddMovie.setFocusPainted(false);
		btnAddMovie.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddMovie.setBounds(210, 139, 120, 33);
		
		entryPanel.add(btnAddMovie);
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//If title, genre, and rating have been selected, construct new Movie and add to HashMap
				if(!textAreaMovieTitle.getText().isBlank() && !listGenre.isSelectionEmpty() 
					  && !listRating.isSelectionEmpty()) {
					String movieTitle = textAreaMovieTitle.getText();
					//Adds title if titleList is below max. capacity and title is not already in list
					if(titleList.size() < MAX_LIST_SIZE) {
						if(!titleList.contains(movieTitle)) {
							//Adds new Movie to HashMap and titleList
							//Uses index of star rating to determine int value
							Movie movie = new Movie(movieTitle, listGenre.getSelectedValue(), 
									listRating.getSelectedIndex() + 1, getDescription());
							setCoverPhoto(movie);
							movieHash.put(movieTitle, movie);
							
							titleList.add(movieTitle);
							addToCategoryLists(movie);
							displayMovieWithPhoto(movie);
						}
						else {
							//Removes previous movie data from lists while creating new movie
							removeFromCategoryLists(movieHash.get(movieTitle));
							Movie movie = new Movie(movieTitle, listGenre.getSelectedValue(), 
									listRating.getSelectedIndex() + 1, getDescription());
							setCoverPhoto(movie);
							movieHash.put(movieTitle, movie);
							JOptionPane.showMessageDialog(contentPane, 
								"Movie already exists in database!\n Movie updated with newest data");
							addToCategoryLists(movie);
							//Displays photo so user can verify that movie info is correct
							displayMovieWithPhoto(movie);						}
							JOptionPane.showMessageDialog(contentPane, textAreaMovieTitle.getText() +
								" Added Successfully!");
					}
					else {
						JOptionPane.showMessageDialog(contentPane, 
								"ERROR: Cannot add movie!\n Database has reached maximum capacity"); 
					}		
				}
				else {
					JOptionPane.showMessageDialog(contentPane, 
							"Insufficient Data: Please Provide All Categories");
				}
					}
				});
		
		// Menu bar setup
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 542, 22);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setToolTipText("Basic read and write functions for the database");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		JMenuItem clearMenuItem = new JMenuItem("Clear");
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		fileMenu.add(clearMenuItem);

		JMenu viewMenu = new JMenu("View");
		viewMenu.setToolTipText("Text-only display of database contents (if you would like to view a cover photo, use the Search menu)");
		
		JMenuItem byTitle = new JMenuItem("All Titles");
		byTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 displayByCategory(titleList);
				
			}
		});
		
		JMenu byGenre = new JMenu("By Genre");
		JMenu byRating = new JMenu("By Rating");
		
		//Creates genres for byGenre Menu
		JMenuItem horrorMenuItem = new JMenuItem("Horror");
		horrorMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(horrorList);				
			}
		});
		
		JMenuItem actionMenuItem = new JMenuItem("Action");
		actionMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(actionList);				
			}
		});
		
		JMenuItem comedyMenuItem = new JMenuItem("Comedy");
		comedyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(comedyList);				
			}
		});
		
		JMenuItem documentaryMenuItem = new JMenuItem("Documentary");
		documentaryMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(documentaryList);				
			}
		});
		JMenuItem sciFiMenuItem = new JMenuItem("Sci-Fi");
		sciFiMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(sciFiList);				
			}
		});
		JMenuItem fantasyMenuItem = new JMenuItem("Fantasy");
		fantasyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(fantasyList);				
			}
		});
		JMenuItem thrillerMenuItem = new JMenuItem("Thriller");
		thrillerMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(thrillerList);				
			}
		});
		JMenuItem dramaMenuItem = new JMenuItem("Drama");
		dramaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(dramaList);				
			}
		});
		JMenuItem otherMenuItem = new JMenuItem("Other");
		otherMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(otherList);				
			}
		});
		
		// Creates Star Ratings for byRating Menu
		JMenuItem oneStarMenuItem = new JMenuItem("*");
		oneStarMenuItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		oneStarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(oneStarList);				
			}
		});
		
		JMenuItem twoStarMenuItem = new JMenuItem("**");
		twoStarMenuItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		twoStarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(twoStarList);				
			}
		});
		
		JMenuItem threeStarMenuItem = new JMenuItem("***");
		threeStarMenuItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		threeStarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(threeStarList);				
			}
		});
		
		JMenuItem fourStarMenuItem = new JMenuItem("****");
		fourStarMenuItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		fourStarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(fourStarList);
			}
		});
		
		JMenuItem fiveStarMenuItem = new JMenuItem("*****");
		fiveStarMenuItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		fiveStarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayByCategory(fiveStarList);
			}
		});
		
		byGenre.add(actionMenuItem);
		byGenre.add(horrorMenuItem);
		byGenre.add(comedyMenuItem);
		byGenre.add(documentaryMenuItem);
		byGenre.add(sciFiMenuItem);
		byGenre.add(fantasyMenuItem);
		byGenre.add(thrillerMenuItem);
		byGenre.add(dramaMenuItem);
		byGenre.add(otherMenuItem);
		
		byRating.add(oneStarMenuItem);
		byRating.add(twoStarMenuItem);
		byRating.add(threeStarMenuItem);
		byRating.add(fourStarMenuItem);
		byRating.add(fiveStarMenuItem);
		
		JMenu searchMenu = new JMenu("Search");
		searchMenu.setToolTipText("Finds a movie and displays its categories. Displays a cover photo if one has been added." );
		JMenuItem searchMenuItem = new JMenuItem("By Title");
		searchMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userInput;
				
				userInput = JOptionPane.showInputDialog(contentPane, "Please enter title of movie (search is case-sensitive): ");
				
				//Ends process if no movie title was given
				//userInput is null if Cancel button was clicked, and blank if OK button was clicked with blank field
				if(userInput == null || userInput.isBlank()) {
					return;
				}
 
				if(movieHash.containsKey(userInput)) {
					Movie movie = movieHash.get(userInput);
					//If movie has a cover photo, displays categories and photo
					if(movie.getCoverPhoto() != null) {
						displayMovieWithPhoto(movie);
					}
					else {
						//Displays categories of movie without cover photo
						displayMovieText(movie);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Could not find movie in database! (If you believe this to be an error, "
							+ "\nplease verify that the title has been entered correctly and try again)");
				}
			}
		});
		
		JMenu windowMenu = new JMenu("Window");
		windowMenu.setToolTipText("Customize application windows");
		
		JMenuItem appearanceMenuItem = new JMenuItem("Appearance");
		appearanceMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entryPanel.setBackground(JColorChooser.showDialog(contentPane, "Choose Color", entryPanel.getBackground()));
			}	
		});
		
		
		
		searchMenu.add(searchMenuItem);
		
		viewMenu.add(byTitle);
		viewMenu.add(byGenre);
		viewMenu.add(byRating);
		
		windowMenu.add(appearanceMenuItem);
	
		menuBar.setAlignmentX(LEFT_ALIGNMENT);
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		menuBar.add(searchMenu);
		menuBar.add(windowMenu);

		// Adding components to the entry panel
		entryPanel.add(menuBar);

		// ActionListener for the Load menu item
		loadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If database contains movies, asks user if they would like to
				// overwrite the current database, or add movies from the file to the database
				if(!movieHash.isEmpty()) {
					String[] loadOptions = {"Overwrite", "Add", "Cancel"};
					int userChoice = JOptionPane.showOptionDialog(contentPane, "You currently have movies in the database!\n"
							+ "Would you like to overwrite your database with the saved file\n or add the file to your database?", 
							"Load File", 0, 3, null, loadOptions, loadOptions[0]);
					// Overwrite current database
					if(userChoice==0) {
						loadFile(true, true);
					}
					// Add file to database
					else if(userChoice==1){
						loadFile(false, true);
					}
				}
				//Default file load when database is already empty
				else {
					loadFile(false, true);
				}
			}

		});

		// ActionListener for the Save menu item
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File savedMovies = new File("SavedMovies.txt");
				// If destination file exists but is empty, overwrites file (prevents "File is empty" message from loadFile() in below "Overwrite" option)
				if(savedMovies.exists()) {
					if(savedMovies.length() == 0) {
						saveToFile(savedMovies, true);
						return;
					}
					
					String[] saveOptions = {"Overwrite", "Add", "Cancel"};
					int userChoice = JOptionPane.showOptionDialog(contentPane, "A saved database already exists!\n"
							+ "Would you like to overwrite the previous database file\n or add your current movies to the file?", 
							"Save to File", 0, 3, null, saveOptions, saveOptions[0]);
					// Overwrites file
					if (userChoice == 0) {
						saveToFile(savedMovies, true);
					}
					// Adds database to file
					else if(userChoice == 1) {
						//Loads file to database, then saves combined database back to file
						loadFile(false, false);
						saveToFile(savedMovies, false);	
					}
				}
			}
		});

		// ActionListener for the Clean menu item 
		clearMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userChoice = JOptionPane.showConfirmDialog(contentPane, "Are you sure?\n This will delete all current database content."
						+ "\n(Only deletes application database. Does not affect previously saved file.)",
						"Clear Database", JOptionPane.YES_NO_OPTION);
				if (userChoice == JOptionPane.YES_OPTION) {
					clearDB();
				    JOptionPane.showMessageDialog(contentPane, "Database Cleared!");
				}
			}
		});		
	} //End of default constructor
	
	//Beginning of methods
	
	/** 
 	This method clears all data structures used in the User-Generated Movie Datanase,
  	including the main HashMap storing Movie objects and various lists categorized byb genre,
   	star rating, and movie title.
	Traceability to Requirements:
 	- Req. 3.3
 	*/
	
	private void clearDB() {
		// Clearing the main HashMap and category lists
		movieHash.clear();
		actionList.clear();
		horrorList.clear();
		comedyList.clear();
		documentaryList.clear();
		sciFiList.clear();
		fantasyList.clear();
		thrillerList.clear();
		dramaList.clear();
		otherList.clear();
		titleList.clear();

		// Clearing sar rating lists
		oneStarList.clear();
		twoStarList.clear();
		threeStarList.clear();
		fourStarList.clear();
		fiveStarList.clear();
	}
	/**
	This method reads movie data from a file, validates the format, and adds the movie to the 
 	User-Generated Movie Database. It also handles cases where the database is overwritten or 
	additional movies are added.
	Traceability to Requirements:
 	- Req. 3.1
  	*/
	
	private void loadFile(boolean overwriteRequested, boolean successMsgRequested) {		
		// Overwrites current database with contents in load file by first clearing database 
		if(overwriteRequested) {
			this.clearDB();
		}

		else if(movieHash.size() >= MAX_LIST_SIZE) {
			JOptionPane.showMessageDialog(contentPane, "ERROR: Database is currently at maximum capacity.");
			return;
		}
		
		File savedMovies = new File("SavedMovies.txt");
		if(!savedMovies.exists()) {
			JOptionPane.showMessageDialog(contentPane, "ERROR: File not found! \nCreating new file...");
			try {
				savedMovies.createNewFile();
				JOptionPane.showMessageDialog(contentPane, "New file successfully created!");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(contentPane, "ERROR: Failed to create database file");
			}
			return;
		}
		Scanner fileReader;
		boolean hasFormatError = false;
		
		try {
			fileReader = new Scanner(savedMovies);
			// Ends load process if file is blank
			if(!fileReader.hasNextLine()) {
				fileReader.close();
				// If attempting to load file to empty application database, warns user that file is empty
				if(movieHash.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "ERROR: No data found in file!");
					hasFormatError = true;
					return;
				}
			}
			
			// Used when photo could not be found
			boolean hasPhotoError = false;
			
			while(fileReader.hasNextLine()) {
				if(movieHash.size() == MAX_LIST_SIZE) {
					JOptionPane.showMessageDialog(contentPane, "ERROR: Database has reached maximum capacity!"
							+ "\nUnable to load remaining movies.");
					fileReader.close();
					return;
				}
				String movieData = fileReader.nextLine();

				
				// Splits line into individual movie variables
				// Tilde ( ~ ) used to split values to prevent splitting of titles containing commas
				// Elements are as follows: [0]==title, [1]==genre, [2]==starRating [3]==description [4]==coverPhotoPath
				String[] movieVars = movieData.split(" ~ ");
				
				// Checks whether valid amount of variables has been given
				if(movieVars.length != 5) {
					fileReader.close();
					hasFormatError = true;
					JOptionPane.showMessageDialog(contentPane, "ERROR: One or more movies have an invalid number of variables.");
					break;
				}
				
				int starRating = Integer.parseInt(movieVars[2]);
				// Ends load process if starRating is not valid (between 1-5)
				if(starRating > 5 || starRating < 1) {
					JOptionPane.showMessageDialog(contentPane, "ERROR: Unable to load file. "
							+ "Star Rating for one or more movies is outside of valid range.");
					hasFormatError = true;
					fileReader.close();
					break;
				}
				
				String title = movieVars[0];
				Movie movie = new Movie(title, movieVars[1], starRating, movieVars[3]);
				String photoPath = movieVars[4];
				if(photoPath.compareTo("null") != 0) {
					try {
						movie.setCoverPhotoPath(photoPath);
						BufferedImage bi = ImageIO.read(new File(movie.getCoverPhotoPath()));
						movie.setCoverPhoto(bi);
					} catch (Exception e) {
						// Only displays error message if it hasn't been displayed before
						// Prevents excessive message pop-ups
						if(!hasPhotoError) {
							JOptionPane.showMessageDialog(this, "ERROR: Cannot locate one or more previous cover photos! "
									+ "\nPlease verify that photo location has not changed since database file was created.");
							hasPhotoError = true;
						}
						
					}
				}
				
				// Adds each Movie to the HashMap
				this.movieHash.put(title, movie);
				// Adds each Movie to the titleList after verifying that it is not already present
				if(!this.titleList.contains(title)) {
					this.titleList.add(title);
				}
				
				/*
				 * NOTE: If title is already present in titleList, duplicate movie entries
				 * will overwrite the existing movie's genre and rating in the HashMap 
				 * with the user's newest input.
				 */
			}
			
			// If invalid format was given, load process is ended, and error message is displayed
			if(hasFormatError) {
				fileReader.close();
				JOptionPane.showMessageDialog(contentPane, "File load failed!"
						+ "\nPlease verify that your file was either generated by this program"
						+ "\n or written in the format used by this program.");
				return;
			}
			
			// End of file read
			if(overwriteRequested) {
				JOptionPane.showMessageDialog(this, "Database Overwrite Successful!");
			}
			else if(successMsgRequested) {
				if(hasPhotoError) {
					JOptionPane.showMessageDialog(this, 
							"File load complete! \nAny previous photos moved/deleted since file creation have been omitted.");
				}
				else {
					JOptionPane.showMessageDialog(this, "File Load Successful!");	
				}
				
			}
			// After file has successfully loaded, update lists with new movies
			for(String title : titleList) {
				try {
					Movie movie = movieHash.get(title);
					addToCategoryLists(movie);
				} catch (Exception e) {
					System.out.println("Failed to populate lists after loading file");
				}
			}
			
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, 
					"ERROR: File Not Found");
		}
	}

	/** 
	This method saves the movie data from the User-Generated Movie Database to a specific file.
	It includes an option to overwrite the existing file or create a new one.
	Traceability to Requirments:
 	- Req. 3.1
	*/		
	private void saveToFile(File saveFile, boolean overwriteRequested) {
		if(!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(contentPane, "ERROR: Unable to create database file");
			}
		}
		
		try {
			PrintWriter saveToTxt = new PrintWriter(saveFile);
			// Print title, genre, and rating of each Movie on a separate line
			Movie tempMovie;
			for(String title : titleList) {
				tempMovie = movieHash.get(title);
				saveToTxt.println(tempMovie.getTitle() + " ~ " + tempMovie.getGenre() + " ~ " 
					+ tempMovie.getStarRating() + " ~ " + tempMovie.getDescription() + " ~ " + tempMovie.getCoverPhotoPath());
			}
			saveToTxt.close();
			if(overwriteRequested) {
				JOptionPane.showMessageDialog(this, 
						"File Overwrite Successful!");
			}
			else {
				JOptionPane.showMessageDialog(this, 
					"Movies Saved to File!");
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, 
					"ERROR: Unable to Save Movies to File");
		}
	}

	/**
	This method categorizes a Movie and adds its title to the corresponding genre and rating lists.
	If the title is already present in a list, it won't be added again.
 	Traceability to Requirements:
  	- Req. 2.1
	*/
	
	private void addToCategoryLists(Movie movie) {
		String genre = movie.getGenre();
		int rating = movie.getStarRating();
		String title = movie.getTitle();

		// Categorize by genre
		switch(genre) {
			case "Action":
				addIfNew(actionList, title);
				break;
			case "Horror":
				addIfNew(horrorList, title);
				break;
			case "Comedy":
				addIfNew(comedyList, title);
				break;
			case "Documentary":
				addIfNew(documentaryList, title);
				break;
			case "Sci-Fi":
				addIfNew(sciFiList, title);
				break;
			case "Fantasy":
				addIfNew(fantasyList, title);
				break;
			case "Thriller":
				addIfNew(thrillerList, title);
				break;
			case "Drama":
				addIfNew(dramaList, title);
				break;
			case "Other":
				addIfNew(otherList, title);
				break;
			default:
				addIfNew(otherList, title);				
		}

		// Categorize by rating
		switch(rating) {
		case 1:
			addIfNew(oneStarList,title);
			break;
		case 2:
			addIfNew(twoStarList, title);
			break;
		case 3: 
			addIfNew(threeStarList, title);
			break;
		case 4:
			addIfNew(fourStarList, title);
			break;
		case 5:
			addIfNew(fiveStarList, title);
		}
	}
	
	/**
	This method checks if the specified item is already present in the given ArrayList.
	If the item is not present, it is added to the ArrayList.
	Traceability to Requirements:
 	- Req. 2.1
	*/
	private void addIfNew(ArrayList<String> arrLst, String str) {
		if(!arrLst.contains(str)) {
			arrLst.add(str);
		}
	}
	
	/** 
	This method displays a confirmation dialog to the user, asking if they want to
 	enter a custom description for the movie. If the user chooses 'Yes', it prompts
  	for input, allowing them to enter a brief custom description. If the user chooses 
   	'No' or cancels the operation, it returns a default value "N/A".
    	Traceability to Requirements:
     	- Req. 2.2
	*/
	private String getDescription() {
		int userChoice = JOptionPane.showConfirmDialog(contentPane, "Would you like to enter"
				+ " a custom description of this movie?", "Custom Description",
				JOptionPane.YES_NO_OPTION);
		if (userChoice == JOptionPane.YES_OPTION) {	    
			return JOptionPane.showInputDialog(contentPane, "Please enter a brief custom description: ");
		}
		else {
			return "N/A";
		}
	}

	/**
	This method takes a Movie object as a paramete and removes its title from
	category-specific lists, such as genre-based lists (e.g., Horror, Comedy) and
	star rating-based lists (e.g., five stars, one star). Itt ensures that the movie is 
 	appropriately removed from the corresponding lists to maintain data consistency.
	Traceability to Requirements:
 	- Req. 3.1
	*/ 
	private void removeFromCategoryLists(Movie movie) {
		String genre = movie.getGenre();
		int rating = movie.getStarRating();
		String title = movie.getTitle();
		
		// Remove the title from the appropriate genre-based list
		switch(genre) {
			case "Action":
				actionList.remove(title);
				break;
			case "Horror":
				horrorList.remove(title);
				break;
			case "Comedy":
				comedyList.remove(title);
				break;
			case "Documentary":
				documentaryList.remove(title);
				break;
			case "Sci-Fi":
				sciFiList.remove(title);
				break;
			case "Fantasy":
				fantasyList.remove(title);
				break;
			case "Thriller":
				thrillerList.remove(title);
				break;
			case "Drama":
				dramaList.remove(title);
				break;
			case "Other":
				otherList.remove(title);
				break;
			default:
				otherList.remove(title);
		}

		// Remove the title from the appropriate star rating-based list
		switch(rating) {
		case 1:
			oneStarList.remove(title);
			break;
		case 2:
			twoStarList.remove(title);
			break;
		case 3: 
			threeStarList.remove(title);
			break;
		case 4:
			fourStarList.remove(title);
			break;
		case 5:
			fiveStarList.remove(title);
		}
	}

	/**
	This method takes a Movie object as a parameter and checks if it has a cover photo.
 	If a cover photo is present, it creates a custom dialog to display the cover photo 
  	along with the movie's details, including title, genre, star rating, and description
	*/
	private void displayMovieWithPhoto(Movie movie) {
		if(movie.getCoverPhoto() != null) {
			JPanel panel = new JPanel();
			panel.setBackground(entryPanel.getBackground());
			// Cover photo scaled to a 9:16 size
			ImageIcon coverPhoto = new ImageIcon(movie.getCoverPhoto().getScaledInstance(360, 640, java.awt.Image.SCALE_SMOOTH));
			JLabel label = new JLabel();
			label.setIcon(coverPhoto);
			panel.add(label);

			// Display movie details using a JTextArea with a bold font
			JTextArea textArea = new JTextArea(movie.toString());
			textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel.add(textArea);

			// Show the custom dialog with the cover photo and movie details
			JOptionPane.showMessageDialog(contentPane, panel, "Movie", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	This method takes a string of text content as a paramete and creates a custom dialog
 	to display the text in a JTextArea. The JTextArea is configured with a bold font, and
  	a JScrollPane is added to enable scrolling if the content exceeds the visible area.
   	The method ensures that the scroll bar is positioned at the top of the text area.
    	Traceability to Requirements:
     	- Req. 4.3
	*/
	private void displayTextArea(String displayText) {
		JTextArea textArea = new JTextArea();
        textArea.setRows(14);
        textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
        textArea.setText(displayText);
		
        // Moves scroll bar to top of text area
        textArea.setCaretPosition(0);
        textArea.setEditable(false);

	// Create a JScrollPane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);

	// Show the custom dialog with the scrollable JTextArea
        JOptionPane.showMessageDialog(contentPane, scrollPane, "Display", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	This method takes a Movie object as a parameter and extracts textual information about
 	the movie using its toString() method. The extracted text is then displayed in a JTextArea
	within a custom dialog. The JTextArea is configured with a bold font, and a JScrollPane is
	added to enable scrolling if the contexts exceeds the visible area. The method ensures that
	the scroll bar is positioned at the top of the text area.
 	Traceability to Requirements:
  	- Req. 4.1
	*/
	private void displayMovieText(Movie movie) {
		JTextArea textArea = new JTextArea();
        textArea.setRows(5);
        textArea.setFont(new Font("Tahoma", Font.BOLD, 14));

	// Extract text information about the movie using toString() method
        textArea.setText(movie.toString());
		
        //Moves scroll bar to top of text area
        textArea.setCaretPosition(0);
        textArea.setEditable(false);

	// Create a JScrollPane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);

	// Show the custom dialog with the scrollable JTextArea
        JOptionPane.showMessageDialog(contentPane, scrollPane, "Display", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	This method takes a Movie object as a parameter and prompts the user with a confirmation dialog
 	to inquire whether they would like to add a cover photo. If the user chooses to do so, a file chooser
  	dialog is displayed to select an image file (with supported extensions: jpg, jpeg, png) as the cover photo.
   	Once an image is selected, it is read into a BufferedImage, and both the cover photo and its file path
    	are set in the Movie object. If any errors occur during the process, an error message is displayed.
     	Traceability to Requirements:
      	- Req. 5.1
	*/
	private void setCoverPhoto(Movie movie) {
		// Create a file filter fo image files with supported extensions
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");

		// Create a file chooser and add the image filter
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(imageFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);

		// Prompt the user with a confirmation dialog to add a cover photo
		int userChoice = JOptionPane.showConfirmDialog(contentPane, "Would you like to add a cover photo?",
				"Cover Photo", JOptionPane.YES_NO_OPTION);
		
		// If the user chooses to add a cover photo, proceed with the file chooser
		if (userChoice == JOptionPane.YES_OPTION) {
			// Show file chooser dialog and check if the user selects a file
			if(fileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
				try {
					// Read selected image file into BufferedImage
					BufferedImage coverPhoto = ImageIO.read(fileChooser.getSelectedFile());

					// Set cover photo and its file path in the Movie object
					movie.setCoverPhoto(coverPhoto);
					movie.setCoverPhotoPath(fileChooser.getSelectedFile().getAbsolutePath());

					// Display an error message if unableto add the cover photo
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "ERROR: Unable to add cover photo.");
				}
			}
			// Display a success message
		    JOptionPane.showMessageDialog(contentPane, "Cover photo added!");
		}
	}

	/**
	This method take an ArrayList of movie titles representing a specific category as a parameter. 
 	It first sorts the list in alphabetical order. Then, it iterates through the sorted list, fetches
  	the corresponding Movie objects fromthe HashMap, and appends their detail to a StringBuilder. To
   	avoid duplicates, the methodchecks whether a title is already present in the StringBuilder before
    	appending. Once the StringBuilder is populated, it invokes the displayTextArea method to show the
     	movie details to the user in a scrollable dialog box.
      	Traceability to Requirements:
       	- Req. 4.2
	*/
	private void displayByCategory(ArrayList<String> categoryList) {
		// Sorts list in alphabetical order for display
		categoryList.sort(null); 

		// Initialize a StringBuilder to store movie details for display
		StringBuilder databaseContent = new StringBuilder();
		
	        // Iterate through the categoryList and fetch details from the HashMap
	        for (String title : categoryList) {
			
	        	// Prevents duplicates by checking database for title
	        	// Does not append to databaseContent if title is already present
	        	if(!databaseContent.toString().contains(title)) {
	        		Movie movie = movieHash.get(title);
	        		databaseContent.append(movie.toString() + "\n\n");
	        	}
	            
	        }

		// Display the movie details in a scrollable dialog box.
	        displayTextArea(databaseContent.toString());
	}
}
