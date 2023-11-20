package main;

import java.awt.image.BufferedImage;

public class Movie {
	private String title, genre, description, coverPhotoPath;
	private int starRating;
	private BufferedImage coverPhoto;

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
	
	public BufferedImage getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(BufferedImage coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public String getCoverPhotoPath() {
		return coverPhotoPath;
	}

	public void setCoverPhotoPath(String coverPhotoPath) {
		this.coverPhotoPath = coverPhotoPath;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\nGenre: " + genre + "\nStar Rating: " + starRating + "\nDescription: " + description;
	}
}
