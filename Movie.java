package main;

public class Movie {
	private String title, genre;
	private int starRating;
	
	public Movie(String title, String genre, int starRating) {
		this.title = title;
		this.genre = genre;
		this.starRating = starRating;
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
		return "Movie [title=" + title + ", genre=" + genre + ", starRating=" + starRating + "]";
	}
}
