// Assignment: 7
// Name: Joel Hudgens
// StudentID: 1224491216
// Lecture: T Th, 10: 30
// Description:
//This class compares movies according to the following hierarchy so they can be sorted:
	//with number 1 being most important and number 4 being least important:
	//1. Movie Genre
	//2. Total Collection
	//3. Movie Name
	//4. Director


import java.util.Comparator;

public class ReviewMovieGenreComparator implements Comparator<Movie> {

	public int compare(Movie firstMovie, Movie secondMovie) {
		
		if (firstMovie.getMovieGenre().getGenre().equals(secondMovie.getMovieGenre().getGenre())) { //If the genres are the same
			
			if (firstMovie.getTotalCollection() != (secondMovie.getTotalCollection())){				//Check for the next condition in hierarchy
				return firstMovie.getTotalCollection() - (secondMovie.getTotalCollection());		//Return that variable if they are not equivalent
			}
			
			else if  (!firstMovie.getMovieName().equals(secondMovie.getMovieName())) {
				return firstMovie.getMovieName().compareTo(secondMovie.getMovieName());
			}
			
			else if (!firstMovie.getDirector().equals(secondMovie.getDirector())) {
				return firstMovie.getDirector().compareTo(secondMovie.getDirector());
			}
			return 0; //they are the same
		}

		return firstMovie.getMovieGenre().getGenre().compareTo(secondMovie.getMovieGenre().getGenre());

	}
}
