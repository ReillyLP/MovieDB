package main;

//TODO: add movie image variable, and add image to toString
public class Movie {
	private String title, genre, description;
	private int starRating;
	
	public Movie(String title, String genre, int starRating, String description) {
		this.title = title;
		this.genre = genre;
		this.starRating = starRating;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\nGenre: " + genre + "\nRating: " + starRating + "\nDescription: " + description;
	}
	
	
	
}
