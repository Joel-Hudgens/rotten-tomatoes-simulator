// Assignment: 7
// Name: Joel Hudgens
// StudentID: 1224491216
// Lecture: T Th, 10: 30
// Description: 
//This class compares movies according to the following hierarchy so they can be sorted:
	//with number 1 being most important and number 4 being least important:
	//1. Stars
	//2. Movie Name
	//3. Director
	//4. Review

import java.util.Comparator;

public class ReviewRatingComparator implements Comparator <Movie>{

	public int compare(Movie firstMovie, Movie secondMovie) {
		
		
		if (firstMovie.getStars() == secondMovie.getStars()) {							//If the stars are the same
			
			if (!firstMovie.getMovieName().equals(secondMovie.getMovieName())){					//Check for the next condition in hierarchy
				return firstMovie.getMovieName().compareTo(secondMovie.getMovieName());			//Return that variable if they are not equivalent
			}
			
			else if  (!firstMovie.getDirector().equals(secondMovie.getDirector())) {
				return firstMovie.getDirector().compareTo(secondMovie.getDirector());
			}
			
			else if (!firstMovie.getReview().equals(secondMovie.getReview())) {
				return firstMovie.getReview().compareTo(secondMovie.getReview());
			}
		}

		return firstMovie.getStars() - secondMovie.getStars();
	}
}
