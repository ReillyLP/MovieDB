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
import java.io.PrintWriter;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, welcomePanel, entryPanel;
	final int MAX_LIST_SIZE = 50;
	//HashMap used to store Movie objects using their titles as keys
	//Initial max. capacity of 50 Movies
	private HashMap<String, Movie> movieHash = new HashMap<String, Movie>(MAX_LIST_SIZE);
	
	//Each list contains Movie titles to be used as keys to access HashMap
	private ArrayList<String> actionList, horrorList, comedyList, documentaryList, sciFiList;
	private ArrayList<String> fantasyList, thrillerList, dramaList, otherList, titleList;
	private ArrayList<String> oneStarList, twoStarList, threeStarList, fourStarList, fiveStarList;
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
		
		
		entryPanel = new JPanel();
		entryPanel.setVisible(false);
		entryPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		entryPanel.setBounds(0, 0, 542, 330);
		entryPanel.setLayout(null);
		entryPanel.setEnabled(false);
		
		contentPane.add(entryPanel);
		
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
		
		JTextArea textAreaMovieTitle = new JTextArea();
		textAreaMovieTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		textAreaMovieTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		textAreaMovieTitle.setWrapStyleWord(true);
		textAreaMovieTitle.setLineWrap(true);
		textAreaMovieTitle.setToolTipText("Type movie title here");
		textAreaMovieTitle.setBounds(159, 57, 221, 71);
		entryPanel.add(textAreaMovieTitle);

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
		
		//TODO: prompt the user to provide a description in a text area and add String to constructor
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
							//TODO: add user description prompt
							Movie movie = new Movie(movieTitle, listGenre.getSelectedValue(), 
									listRating.getSelectedIndex() + 1, getDescription());
							setCoverPhoto(movie);
							movieHash.put(movieTitle, movie);
							JOptionPane.showMessageDialog(contentPane, 
								"Movie already exists in database!\n Movie updated with newest data");
							addToCategoryLists(movie);
							
							//FIXME: testing
							displayMovieWithPhoto(movie);						}
						JOptionPane.showMessageDialog(contentPane, textAreaMovieTitle.getText() +
								" Added Successfully!");
					}
					else {
						JOptionPane.showMessageDialog(contentPane, 
								"ERROR: Cannot add movie:\n Database has reached maximum capacity"); 
					}		
				}
				else {
					JOptionPane.showMessageDialog(contentPane, 
							"Insufficient Data: Please Provide All Categories");
				}
				//FIXME: used for testing
				printCategoryLists();
				printDescriptions();
				
					}
				});
	
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
		
		//Creates Star Ratings for byRating Menu
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
		
		searchMenu.add(searchMenuItem);
		
		viewMenu.add(byTitle);
		viewMenu.add(byGenre);
		viewMenu.add(byRating);
	
		menuBar.setAlignmentX(LEFT_ALIGNMENT);
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		menuBar.add(searchMenu);
		
		entryPanel.add(menuBar);
		
		loadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If database contains movies, asks user if they would like to
				//overwrite the current database, or add movies from the file to the database
				if(!movieHash.isEmpty()) {
					String[] loadOptions = {"Overwrite", "Add", "Cancel"};
					int userChoice = JOptionPane.showOptionDialog(contentPane, "You currently have movies in the database!\n"
							+ "Would you like to overwrite your database with the saved file\n or add the file to your database?", 
							"Load File", 0, 3, null, loadOptions, loadOptions[0]);
					//Overwrite current database
					if(userChoice==0) {
						loadFile(true, true);
					}
					//Add file to database
					else if(userChoice==1){
						loadFile(false, true);
					}
				}
				//Default file load when database is already empty
				else {
					loadFile(false, true);
				}
				//FIXME: testing
				printCategoryLists();
				printDescriptions();
			}

		});
		
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File savedMovies = new File("SavedMovies.txt");

				if(savedMovies.exists()) {
					String[] saveOptions = {"Overwrite", "Add", "Cancel"};
					int userChoice = JOptionPane.showOptionDialog(contentPane, "A saved database already exists!\n"
							+ "Would you like to overwrite the previous database file\n or add your current movies to the file?", 
							"Save to File", 0, 3, null, saveOptions, saveOptions[0]);
					//Overwrites file
					if (userChoice == 0) {
						saveToFile(savedMovies, true);
					}
					//Adds database to file
					else if(userChoice == 1) {
						//Loads file to database, then saves combined database back to file
						loadFile(false, false);
						saveToFile(savedMovies, false);	
					}
				}
			}
		});
		
		clearMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userChoice = JOptionPane.showConfirmDialog(contentPane, "Are you sure?\n This will delete all database contents",
						"Clear Database", JOptionPane.YES_NO_OPTION);
				if (userChoice == JOptionPane.YES_OPTION) {
					clearDB();
				    JOptionPane.showMessageDialog(contentPane, "Database Cleared!");
				}
			}
		});		
	}//End of default constructor
	
	//Beginning of methods
	
	//Clears HashMap and all lists from current database
	private void clearDB() {
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
		
		oneStarList.clear();
		twoStarList.clear();
		threeStarList.clear();
		fourStarList.clear();
		fiveStarList.clear();
	}
	//Loads .txt file contents into HashMap
	private void loadFile(boolean overwriteRequested, boolean successMsgRequested) {		
		//Overwrites current database with contents in load file by first clearing database 
		if(overwriteRequested) {
			this.clearDB();
		}
		
		File savedMovies = new File("SavedMovies.txt");
		Scanner fileReader;
		try {
			fileReader = new Scanner(savedMovies);
			
			/*
			 * TODO: 
			 * Determine if user's saved file is in valid format.
			 * Try searching Genre text box for valid genre Strings before adding to HashMap
			 * Verify that rating is between 1-5.
			 * Enforce max. amount of char's in title?
			 */
			
			//Used to prevent multiple error messages from appearing
			boolean hasPrevDisplayedError = false;
			
			while(fileReader.hasNextLine()) {
				String movieData = fileReader.nextLine();
				//Splits line into individual movie variables
				//Tilde ( ~ ) used to split values to prevent splitting of titles containing commas
				//Elements are as follows: [0]==title, [1]==genre, [2]==starRating [3]==description [4]==coverPhotoPath
				String[] movieVars = movieData.split(" ~ ");
				String title = movieVars[0];
				Movie movie = new Movie(title, movieVars[1], Integer.parseInt(movieVars[2]), movieVars[3]);
				String photoPath = movieVars[4];
				if(photoPath.compareTo("null") != 0) {
					try {
						movie.setCoverPhotoPath(photoPath);
						BufferedImage bi = ImageIO.read(new File(movie.getCoverPhotoPath()));
						movie.setCoverPhoto(bi);
					} catch (Exception e) {
						//Only displays error message if it hasn't been displayed before
						//Prevents excessive message pop-ups
						if(!hasPrevDisplayedError) {
							JOptionPane.showMessageDialog(this, "ERROR: Cannot locate previous cover photo(s). "
									+ "Please verify that photo location has not changed since last load." );
							hasPrevDisplayedError = true;
						}
						
					}
				}
				
				//Adds each Movie to the HashMap
				this.movieHash.put(title, movie);
				//Adds each Movie to the titleList after verifying that it is not already present
				if(!this.titleList.contains(title)) {
					this.titleList.add(title);
				}
				/*
				 * NOTE: If title is already present in titleList, duplicate movie entries
				 * will overwrite the existing movie's genre and rating in the HashMap 
				 * with the user's newest input.
				 */
			}
			if(overwriteRequested) {
				JOptionPane.showMessageDialog(this, 
						"Database Overwrite Successful!");
			}
			else if(successMsgRequested) {
				JOptionPane.showMessageDialog(this, 
						"File Load Successful!");
			}
			//After file has successfully loaded, update lists with new movies
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
	
	private void saveToFile(File saveFile, boolean overwriteRequested) {
		try {
			PrintWriter saveToTxt = new PrintWriter(saveFile);
			//Print title, genre, and rating of each Movie on a separate line
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
	
	private void addToCategoryLists(Movie movie) {
		String genre = movie.getGenre();
		int rating = movie.getStarRating();
		String title = movie.getTitle();
		
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
		//TODO: verify input so default case isn't needed
		}
	}
	
	//Adds String to ArrayList if it is not already present
	private void addIfNew(ArrayList<String> arrLst, String str) {
		if(!arrLst.contains(str)) {
			arrLst.add(str);
		}
	}
	
	//Prompts user for custom description of movie
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
	
	private void removeFromCategoryLists(Movie movie) {
		String genre = movie.getGenre();
		int rating = movie.getStarRating();
		String title = movie.getTitle();
		
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
		//TODO: verify input so default case isn't needed
		}
	}
	
	//FIXME: used for testing
	private void printCategoryLists() {
		System.out.println("Title: " + titleList.toString());		
		System.out.println("Action: " + actionList.toString());
		System.out.println("Horror: " + horrorList.toString());
		System.out.println("Comedy: " + comedyList.toString());
		System.out.println("Documentary: " + documentaryList.toString());
		System.out.println("Sci-Fi: " + sciFiList.toString());
		System.out.println("Fantasy: " + fantasyList.toString());
		System.out.println("Thriller: " + thrillerList.toString());
		System.out.println("Drama: " + dramaList.toString());
		System.out.println("Other: " + otherList.toString());
		
		System.out.println("One: " + oneStarList.toString());
		System.out.println("Two: " + twoStarList.toString());
		System.out.println("Three: " + threeStarList.toString());
		System.out.println("Four: " + fourStarList.toString());
		System.out.println("Five: " + fiveStarList.toString());
	}
	
	//FIXME: used for testing
	private void printDescriptions() {
		for(String title : titleList) {
			System.out.println("\nTitle: " + title);
			System.out.println("Description: " + movieHash.get(title).getDescription());
			System.out.println("Cover Photo: " + movieHash.get(title).getCoverPhotoPath());
		}
	}
	
	private void displayMovieWithPhoto(Movie movie) {
		if(movie.getCoverPhoto() != null) {
			JPanel panel = new JPanel();
			//Cover photo scaled to a 9:16 size
			ImageIcon coverPhoto = new ImageIcon(movie.getCoverPhoto().getScaledInstance(360, 640, java.awt.Image.SCALE_SMOOTH));
			JLabel label = new JLabel();
			label.setIcon(coverPhoto);
			panel.add(label);
			JTextArea textArea = new JTextArea(movie.toString());
			textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel.add(textArea);
			
			JOptionPane.showMessageDialog(contentPane, panel, "Movie", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void displayTextArea(String displayText) {
		JTextArea textArea = new JTextArea();
        textArea.setRows(14);
        textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
        textArea.setText(displayText);
        //Moves scroll bar to top of text area
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(contentPane, scrollPane, "Display", JOptionPane.PLAIN_MESSAGE);
	}
	
	//Creates a small text area to display a single movie
	private void displayMovieText(Movie movie) {
		JTextArea textArea = new JTextArea();
        textArea.setRows(5);
        textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
        textArea.setText(movie.toString());
        //Moves scroll bar to top of text area
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(contentPane, scrollPane, "Display", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void setCoverPhoto(Movie movie) {
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(imageFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		int userChoice = JOptionPane.showConfirmDialog(contentPane, "Would you like to add a cover photo?",
				"Cover Photo", JOptionPane.YES_NO_OPTION);
		if (userChoice == JOptionPane.YES_OPTION) {
			if(fileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedImage coverPhoto = ImageIO.read(fileChooser.getSelectedFile());
					movie.setCoverPhoto(coverPhoto);
					movie.setCoverPhotoPath(fileChooser.getSelectedFile().getAbsolutePath());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		    JOptionPane.showMessageDialog(contentPane, "Cover photo added!");
		}
	}
		
	private void displayByCategory(ArrayList<String> categoryList) {
		//Sorts list in alphabetical order for display
		categoryList.sort(null); 
		
		StringBuilder databaseContent = new StringBuilder();
	        // Iterate through the categoryList and fetch details from the HashMap
	        for (String title : categoryList) {
	        	//Prevents duplicates by checking database for title
	        	//Does not append to databaseContent if title is already present
	        	if(!databaseContent.toString().contains(title)) {
	        		Movie movie = movieHash.get(title);
	        		databaseContent.append(movie.toString() + "\n\n");
	        	}
	            
	        }
	        
	        displayTextArea(databaseContent.toString());
	}
}
