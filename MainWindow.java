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
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
	//TODO: can "this" statement be removed?
	public MainWindow() {
		this.movieHash = new HashMap<String, Movie>(MAX_LIST_SIZE);
		this.actionList = new ArrayList<String>(MAX_LIST_SIZE);
		this.horrorList = new ArrayList<String>(MAX_LIST_SIZE);
		this.comedyList = new ArrayList<String>(MAX_LIST_SIZE);
		this. documentaryList = new ArrayList<String>(MAX_LIST_SIZE);
		this.sciFiList = new ArrayList<String>(MAX_LIST_SIZE);
		this.fantasyList = new ArrayList<String>(MAX_LIST_SIZE);
		this.thrillerList = new ArrayList<String>(MAX_LIST_SIZE);
		this.dramaList = new ArrayList<String>(MAX_LIST_SIZE);
		this.otherList = new ArrayList<String>(MAX_LIST_SIZE);
		this.titleList = new ArrayList<String>(MAX_LIST_SIZE);
		
		this.oneStarList = new ArrayList<String>(MAX_LIST_SIZE);
		this.twoStarList = new ArrayList<String>(MAX_LIST_SIZE);
		this.threeStarList = new ArrayList<String>(MAX_LIST_SIZE);
		this.fourStarList = new ArrayList<String>(MAX_LIST_SIZE);
		this.fiveStarList = new ArrayList<String>(MAX_LIST_SIZE);
				
				
		setResizable(false);
		setTitle("MainWindow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textAreaMovieTitle = new JTextArea();
		textAreaMovieTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		textAreaMovieTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		textAreaMovieTitle.setWrapStyleWord(true);
		textAreaMovieTitle.setLineWrap(true);
		textAreaMovieTitle.setToolTipText("Type movie title here");
		textAreaMovieTitle.setBounds(160, 36, 221, 71);
		contentPane.add(textAreaMovieTitle);

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
		btnEntryReset.setToolTipText("Stop an in-progress movie addition and clear fields");
		btnEntryReset.setBounds(226, 172, 89, 23);
		contentPane.add(btnEntryReset);

		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.setToolTipText("Click after selecting all required movie categoriese to add movie to database");
		btnAddMovie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddMovie.setFocusPainted(false);
		btnAddMovie.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddMovie.setBounds(211, 118, 120, 33);
		
		contentPane.add(btnAddMovie);
		//TODO: Add movies to their respective category lists
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
							movieHash.put(movieTitle, new Movie(movieTitle, listGenre.getSelectedValue(), 
								listRating.getSelectedIndex() + 1));
							titleList.add(movieTitle);
							 addToCategoryLists(movieHash.get(movieTitle));
						}
						else {
							//Removes previous movie data from lists while creating new movie
							removeFromCategoryLists(movieHash.get(movieTitle));
							movieHash.put(movieTitle, new Movie(movieTitle, listGenre.getSelectedValue(), 
									listRating.getSelectedIndex() + 1));
							//TODO: prompt user asking if they would like to change current movie data
							JOptionPane.showMessageDialog(contentPane, 
								"Movie already exists in database!\n Movie updated with newest data");
							Movie movie = movieHash.get(movieTitle);
							addToCategoryLists(movie);
						}
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
				
					}
				});
		
		//Saves data from each Movie in a .txt file
		//Iterates through titleList for movieHash keys
		//tilde(~) used to split values to prevent splitting of titles with commas
		JButton btnSave = new JButton("Save to File");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File savedMovies = new File("SavedMovies.txt");

				if(savedMovies.exists()) {
					String[] saveOptions = {"Overwrite", "Add", "Cancel"};
					int userChoice = JOptionPane.showOptionDialog(contentPane, "A saved database already exists!\n"
							+ "Would you like to overwrite the previous database file\n or add your current movies to the file?", 
							btnSave.getText(), 0, 3, null, saveOptions, saveOptions[0]);
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
		
		btnSave.setToolTipText("Click to save movies to file");
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setFocusPainted(false);
		btnSave.setBounds(51, 265, 120, 33);
		contentPane.add(btnSave);
		
		//TODO: Create a "Clear Database" button with an "Are you sure?" confirmation window
		
		JButton btnLoad = new JButton("Load File");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If database contains movies, asks user if they would like to
				//overwrite the current database, or add movies from the file to the database
				if(!movieHash.isEmpty()) {
					String[] loadOptions = {"Overwrite", "Add", "Cancel"};
					int userChoice = JOptionPane.showOptionDialog(contentPane, "You currently have movies in the database!\n"
							+ "Would you like to overwrite your database with the saved file\n or add the file to your database?", 
							btnLoad.getText(), 0, 3, null, loadOptions, loadOptions[0]);
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
			}
			
		});
		
		btnLoad.setToolTipText("Click to load movies from existing file");
		btnLoad.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoad.setFocusPainted(false);
		btnLoad.setBounds(371, 265, 120, 33);
		contentPane.add(btnLoad);
		
		JButton btnClearDB = new JButton("Clear Database");
		btnClearDB.setToolTipText("Clears all movies from the database");
		btnClearDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userChoice = JOptionPane.showConfirmDialog(contentPane, "Are you sure?\n This will delete all database contents",
						btnClearDB.getText(), JOptionPane.YES_NO_OPTION);
				if (userChoice == JOptionPane.YES_OPTION) {
					clearDB();
				    JOptionPane.showMessageDialog(contentPane, "Database Cleared!");
				}
			}
		});
		btnClearDB.setBounds(203, 206, 135, 23);
		contentPane.add(btnClearDB);
		
		JButton btnViewDB = new JButton("View Database");
		btnViewDB.setToolTipText("Click to view the contents of the database");
		btnViewDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewDB.setFocusPainted(false);
		btnViewDB.setHorizontalTextPosition(SwingConstants.CENTER);
		btnViewDB.setBounds(203, 265, 135, 33);
		contentPane.add(btnViewDB);
		
		btnViewDB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        StringBuilder databaseContent = new StringBuilder();
		        // Iterate through the titleList and fetch details from the HashMap
		        for (String title : titleList) {
		        	//Prevents duplicates by checking database for title
		        	//Does not append to databaseContent if title is already present
		        	if(!databaseContent.toString().contains(title)) {
		        		Movie movie = movieHash.get(title);
		        		databaseContent.append("Title: ").append(movie.getTitle()).append("\n");
		        		databaseContent.append("Genre: ").append(movie.getGenre()).append("\n");
		        		databaseContent.append("Rating: ").append(movie.getStarRating()).append("\n\n");
		        	}
		            
		        }
		        
		        JTextArea textArea = new JTextArea(databaseContent.toString());
		        textArea.setEditable(false);
		        
		        JScrollPane scrollPane = new JScrollPane(textArea);
		        
		        JOptionPane.showMessageDialog(contentPane, scrollPane, "Database Content", JOptionPane.PLAIN_MESSAGE);
		    }
	    
		});
		
		
	}	//End of default constructor
	//Beginning of methods
	
	//Clears HashMap and all lists from current database
	private void clearDB() {
		this.movieHash.clear();
		this.actionList.clear();
		this.horrorList.clear();
		this.comedyList.clear();
		this.documentaryList.clear();
		this.sciFiList.clear();
		this.fantasyList.clear();
		this.thrillerList.clear();
		this.dramaList.clear();
		this.otherList.clear();
		this.titleList.clear();
		
		this.oneStarList.clear();
		this.twoStarList.clear();
		this.threeStarList.clear();
		this.fourStarList.clear();
		this.fiveStarList.clear();
	}
	//Loads .txt file contents into HashMap
	private void loadFile(boolean overwriteRequested, boolean successMsgRequested) {
		//Overwrites current database with contents in load file by first clearing database 
		//TODO: Requires testing
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
			
			while(fileReader.hasNextLine()) {
				String movieData = fileReader.nextLine();
				//Splits line into individual movie variables
				//Tilde ( ~ ) used to split values to prevent splitting of titles containing commas
				//Elements are as follows: [0]==title, [1]==genre, [2]==starRating
				String[] movieVars = movieData.split(" ~ ");
				String title = movieVars[0];
				//Adds each Movie to the HashMap
				this.movieHash.put(title, new Movie(title, movieVars[1], Integer.parseInt(movieVars[2])));
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
				JOptionPane.showMessageDialog(contentPane, 
						"Database Overwrite Successful!");
			}
			else if(successMsgRequested) {
				JOptionPane.showMessageDialog(contentPane, 
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
			JOptionPane.showMessageDialog(contentPane, 
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
					+ tempMovie.getStarRating());
			}
			saveToTxt.close();
			if(overwriteRequested) {
				JOptionPane.showMessageDialog(contentPane, 
						"File Overwrite Successful!");
			}
			else {
				JOptionPane.showMessageDialog(contentPane, 
					"Movies Saved to File!");
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(contentPane, 
					"ERROR: Unable to Save Movies to File");
		}
	}
	
	private void addToCategoryLists(Movie movie) {
		/*
		 * TODO: assign to lists for following genres:
		 * "Action" "Horror" "Comedy" "Documentary" "Sci-Fi" "Fantasy" "Thriller" "Drama" "Other"
		 * Add ratings
		 */
		String genre = movie.getGenre();
		int rating = movie.getStarRating();
		String title = movie.getTitle();
		
		switch(genre) {
			case "Action":
				actionList.add(title);
				break;
			case "Horror":
				horrorList.add(title);
				break;
			case "Comedy":
				comedyList.add(title);
				break;
			case "Documentary":
				documentaryList.add(title);
				break;
			case "Sci-Fi":
				sciFiList.add(title);
				break;
			case "Fantasy":
				fantasyList.add(title);
				break;
			case "Thriller":
				thrillerList.add(title);
				break;
			case "Drama":
				dramaList.add(title);
				break;
			case "Other":
				otherList.add(title);
				break;
			default:
				otherList.add(title);				
		}
		
		switch(rating) {
		case 1:
			oneStarList.add(title);
			break;
		case 2:
			twoStarList.add(title);
			break;
		case 3: 
			threeStarList.add(title);
			break;
		case 4:
			fourStarList.add(title);
			break;
		case 5:
			fiveStarList.add(title);
		//TODO: verify input so default case isn't needed
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
}
