// Assignment: 7
// Name: Joel Hudgens
// StudentID: 1224491216
// Lecture: T Th, 10: 30
// Description: //This class is an Object
 				//It is a movie with many variables
				//The reviewList is an arrayList of this object type

import java.io.Serializable; //Will be serializing movie objects into files

public class Movie implements Serializable { 
	
	private static final long serialVersionUID = 205L;
	private String movieName, review, director;
	private int stars, totalCollection;
	private MovieGenre movieGenre;
	
	//Constructor
	public Movie(String movieName, int stars, String review, int totalCollection, 
			     String director, MovieGenre movieGenre) {
		this.movieName = movieName;
		this.stars =stars;
		this.review = review;
		this.totalCollection = totalCollection;
		this.director = director;
		this.movieGenre = movieGenre;
	}
	
	//getters for private movie variables
	public String getMovieName() {
		return movieName;
	}
	
	public int getStars() {
		return stars;
	}
	
	public int getTotalCollection() {
		return totalCollection;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getReview() {
		return review;
	}
	
	public MovieGenre getMovieGenre() {
		return movieGenre;
	}
	
	
	//Returns a movie in the specified String format
	@Override
	public String toString() {
		String output = "";
		
		String starsString = "";
		for(int i = 1; i <= getStars(); i++) {
			starsString += "*";
		}
		
		String totalCollectionString = "";
		for(int i = 1; i <= getTotalCollection(); i++) {
			totalCollectionString += "$";
		}
		
		output += movieName + " Movie\n" + starsString + "\n" + "Total Collection earned: ";
		output += totalCollectionString + "\n" +  movieGenre.toString() + "Director: " + director + 
				"\n" + "Review:\t" + review + "\n\n";	
		
		return output;
	}
}
