// Assignment: 7
// Name: Joel Hudgens
// StudentID: 1224491216
// Lecture: T Th, 10: 30
// Description: //This Class Uses Insertion Sort to Switch the positions of the movies in the reviewList
				//The manner in which they are sorted depends on which comparator is passed into the sort() method
				//This class is used in ReviewManager.java

import java.util.ArrayList;
import java.util.Comparator;

public class Sorts {

	public static void sort(ArrayList<Movie> reviewList, Comparator<Movie> xComparator) {
		
		int min;
		
		for (int index = 0; index < reviewList.size(); index++) {   
			min = index; 
			
			for (int scan = index + 1; scan < reviewList.size(); scan++) { 
				
				if (xComparator.compare(reviewList.get(scan), reviewList.get(min)) < 0) { //If the value at the current 
					min = scan;															  //index is less than current min,
				}																		  //current index is new min.
			}
			swap(reviewList,min,index);
		}
		
	}
	
	//Helper method for sort()
	//Swaps the position of two index values within the reviewList
	private static void swap (ArrayList<Movie> reviewList, int index1, int index2) {
	       Movie temp = reviewList.get(index1);
	       
	       reviewList.set(index1, reviewList.get(index2));
	       reviewList.set(index2, temp);
	   }
}
