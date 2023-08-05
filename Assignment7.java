// Assignment: 7
// Name: Joel Hudgens
// StudentID: 1224491216
// Lecture: T Th, 10: 30
// Description: This Class is the main Driver method of the whole program.
                //It enables the user to interact with the menu for specific
				//movie review functions

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Assignment7 {
	
	public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Movie and Movie Genre information
        String movieName, movieGenre;
        String review = null, director, productionCompany, totalCollection;

        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg;
        // Movie manager
        ReviewManager reviewManager = new ReviewManager();   
        
        
        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new Movie Review Based off user Input
                        System.out.print("Please enter the movie information:\n");
                        System.out.print("Enter the movie name:\n");
                        movieName = stdin.readLine().trim(); 
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the total collection:\n");
                        totalCollection = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the movie genre:\n");
                        movieGenre = stdin.readLine().trim();
                        System.out.print("Enter the movie's Director:\n");
                        director = stdin.readLine().trim();
                        System.out.print("Enter the movie's production company\n");
                        productionCompany = stdin.readLine().trim();
                        
                        if (reviewManager.addReview(movieName, rating, review,
                        		totalCollection, movieGenre, director, productionCompany) == true) {
                        	System.out.print("Movie added to the database!\n");
                        }
                        else {
                        	System.out.println("Movie NOT added!\n");
                        }
                        
                        break;
                        
                     
                    // Search for a movie
                    case 'D': 
                        System.out.print("Please enter the Movie name to search:\n");
                        movieName = stdin.readLine().trim();
                        System.out.print("Please enter the movies's director':\n");
                        director = stdin.readLine().trim();
                        
                        //If movie does not exist, give error 
                        if (reviewManager.movieExists(movieName, director) == -1 ) {
                        	System.out.print("Movie not found. Please try again\n");
                        }
                        //If it does exist, display it
                        else {																					
                        	int index = reviewManager.movieExists(movieName, director);
                        	System.out.println("Movie found. Here's the review:");										
                        	System.out.println(reviewManager.reviewList.get(index).getReview().toString());			
                        }
                        
                        break;
                        
                        
                    case 'E': // Search for a Movie Genre
                        System.out.print("Please enter the movie genre to search:\n");
                        movieGenre = stdin.readLine().trim();
                        
                        //If genre does not exist, give error
                        if (reviewManager.movieGenreExists(movieGenre).isEmpty()) {
                        	System.out.println("Movie Genre: " + movieGenre + " was NOT found\n");
                        }
                        
                        //If genre does exist, print the movies that contain that genre
                        else {
                        	int numReviews = reviewManager.movieGenreExists(movieGenre).size();
                        	ArrayList<Integer> genreMatches = reviewManager.movieGenreExists(movieGenre);				//genreMatches contain the indexes where 
                        	System.out.print(numReviews + " Movies matching " + movieGenre +  " type were found:\n");	//matching genres are found in reviewManager
                        	
                        	for (int i = 0; i < genreMatches.size(); i++) {  //for each of the movies with the given genre
                        		int index = genreMatches.get(i);

                        		System.out.print(reviewManager.getMovie(index).toString());  //
                        	}
                        }
                        
                        break;
                        
   
                    case 'L': // List movie's reviews
                    	
                        if (reviewManager.reviewList.isEmpty()) {
                        	System.out.print("\nNo Reviews available");
                        }
                    	
                    	System.out.print("\n" + reviewManager.listReviews() + "\n");
                        
                        
                        break;
                                            
                        
                    case 'N': // List movie's reviews sorted by rating
                    	reviewManager.sortByRating();
                        System.out.print("sorted by rating\n");
                        break;
                        
                        
                    case 'P': // List movie's reviews sorted by genre
                    	reviewManager.sortByMovieGenre();
                        System.out.print("sorted by genre\n");
                        break;
                        
                        
                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a review
                        System.out.print("Please enter the name of the movie for which you want the review removed:\n");
                        movieName = stdin.readLine().trim();
                        System.out.print("Please enter the name of the movie's director:\n");
                        director = stdin.readLine().trim();
                        
                        
                        if (reviewManager.movieExists(movieName, director) == -1) {
                        	System.out.println(movieName + ", " + director + " was NOT removed");
                        } else {
                        	int index = reviewManager.movieExists(movieName, director);
                        	reviewManager.reviewList.remove(index);
                        	System.out.println(movieName + ", " + director + " was removed");
                        }
                        
                        break;
                        
        
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("The movie management system was reset!\n");
                        break;
                        

                    case 'U': // Write movies' names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the movie:\n");
                        movieName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = movieName + "\n" + review + "\n";
                        
                        
                        try {
                        	PrintWriter outFile = new PrintWriter(outFilename);
                        
                        	outFile.println(outMsg); //write to file
                        	
                        	System.out.println(outFilename + " is written\n");
                        	
                        	
                        	outFile.close();
                        	
                        } catch (IOException e){
                        	System.out.print("Write string inside the file error\n");
                        } catch(Exception e){
                        	System.out.println("Something went wrong\n");
                        }
                        
                        break;
                        
    
                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        
                        try {
                        	FileReader fr = new FileReader(inFilename);
                        	BufferedReader inFile = new BufferedReader(fr); 
                        	
                        	System.out.println(inFilename + " was read\n");
                        	System.out.println("The contents of the file are:\n");
                        	
                        	String line = inFile.readLine();
                        	
                        	while(line != null) { 				//Reads the next line until there is no text
                        		System.out.println(line);	
                        		line = inFile.readLine();
                        	}
                        	
                        	inFile.close();
                        	
                        } catch (FileNotFoundException fnfe ) {
                        	System.out.print(inFilename + " was not found\n");
                        } catch (IOException ioe){
                        	System.out.print("Read string from file error\n");
                        } 
                        
                        break;
                        
                        
                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();
                        
                        try {
                        	FileOutputStream fr = new FileOutputStream(outFilename);
                        	ObjectOutputStream objOutput = new ObjectOutputStream(fr);
                        	
                        	objOutput.writeObject(reviewManager);  //write reviewManager object into specified file
                        	objOutput.close();
                        	
                        } catch(NotSerializableException cereal) {
                        	System.out.println("Not serializable exception\n");
                        } catch(IOException ioe) {
                        	System.out.println("Data file written exception\n");
                        }
                        	
                        break;
                        
                        	
                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                         
                        try {
                        	FileInputStream fis = new FileInputStream(inFilename);
                        	ObjectInputStream objInput = new ObjectInputStream(fis);
                        	
                        	reviewManager = (ReviewManager) objInput.readObject();  //cast the input to type ReviewManager for object serialization
                        	objInput.close();
                        	
                        	System.out.println(inFilename + " was read");
	
                        } catch (ClassNotFoundException cnfe) {
                        	System.out.print("Class not found exception\n");
                        } catch (NotSerializableException cereal) {
                        	System.out.print("Not serializable exception\n");
                        } catch (IOException ioe) {
                        	ioe.printStackTrace();
                        	System.out.print("Data file read exception\n");
                        }

                        break;

                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }
    
	//Helper class. Prints the main menu
    public static void printMenu() {
        System.out.println("Welcome to YoMovies! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) movies.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a movie\n" + "E\t\tSearch for a genre\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by genre\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
   
}
