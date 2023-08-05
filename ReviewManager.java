// Assignment: 7
// Name: Joel Hudgens
// StudentID: 1224491216
// Lecture: T Th, 10: 30
// Description: This class contains an ArrayList of Movie objects called "reviewList"
                //This class implements methods which manipulate/serve the reviewList
				//All of these methods are called in the main method upon user action

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewManager implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L;

    ArrayList<Movie> reviewList; //Contains all of the Movie & Movie attributes

    public ReviewManager() {
        reviewList = new ArrayList<>();
    }
    
    //Returns -1 if the movie is not found in reviewList
    //Returns index of movie in the list if it is found
    public int movieExists(String movieName, String director) {
    	int indexOfMovie = -1;
    	
    	for (Movie movie: reviewList) {
    		if (movie.getMovieName().equals(movieName) && movie.getDirector().equals(director)) {
    			indexOfMovie = reviewList.indexOf(movie);
    		}
    	}
    	return indexOfMovie;
    }
    
    //Returns an empty arrayList if the movie genre is not found
    //Returns an arrayList of the indexes of the movies with the specified genre if the genre is found
    public ArrayList<Integer> movieGenreExists(String movieGenre){
    	ArrayList<Integer> positionsOfGenres = new ArrayList<>();
    	int indexOfMovie;
    	
    	for (Movie movie: reviewList) {
    		if (movie.getMovieGenre().getGenre().equals(movieGenre)) {
    			indexOfMovie = reviewList.indexOf(movie);
    			positionsOfGenres.add(indexOfMovie);
    		}
    	}
    	return positionsOfGenres;
    }
    
    public Movie getMovie(int index) {
    	return reviewList.get(index);
    }
    

    /**
     * Add a Movie object to the reviewList and return true if such an object
     * is added successfully. Otherwise, return false. Two Movies are
     * considered duplicated if they have exactly the same movie name and genre.
     * 
     * @param  movieName          the name of the movie
     * @param  stars              the number of stars the movie recieved
     * @param  review             the movie review
     * @param  totalCollection    the integer total collection earned by the movie
     * @param  genre              the movie's genre
     * @param  director           the movie's director
     * @param  prodictionCompany  production comapny of the movie
     * @return                    true if the operation is successful; false otherwise
     */
    
    //Adds a movie review to the reviewList
    public boolean addReview(String movieName, int stars, String review, String totalCollection, String genre, String director, String productionCompany) {
        if (movieExists(movieName, director) == -1) {
            int collection = totalCollection.length();
            MovieGenre newGenre = new MovieGenre(genre, productionCompany);
            Movie newMovie = new Movie(movieName, stars, review, collection, director, newGenre);
            reviewList.add(newMovie);
            return true;
        }
        return false;
    }
    
    //Returns true if a movie is successfully removed from reviewList
    //Returns false if no movie is removed from reviewList
    public boolean removeReview(String movieName, String director) {
    
    	boolean isRemoved = false;
    	
    	for (Movie movie: reviewList) {
    		if (movieExists(movieName, director) != -1) { 
    			reviewList.remove(movie);
    			isRemoved = true;
    		}
    	}
    	return isRemoved;
    }
    
    //Uses insertion sort in Sorts class
    //Sorts reviewList using the hierarchy defined in ReviewMovieGenreComparator
    public void sortByRating(){
    	Sorts.sort(reviewList, new ReviewRatingComparator());
    }
    
    
    //Uses insertion sort in Sorts class
    //Sorts reviewList using the hierarchy defined in ReviewRatingComparator
    public void sortByMovieGenre() {
    	Sorts.sort(reviewList, new ReviewMovieGenreComparator());
    }
    
    //Returns all the reviews in reviewList
    public String listReviews() {
    	String output = "";
    	
    	for (Movie movie: reviewList) {
    		output += movie.toString();
    	}
    	
    	return output;
    }
    
    //Clears reviewList
    public void closeReviewManager() {
    	reviewList.clear();
    }  
}

