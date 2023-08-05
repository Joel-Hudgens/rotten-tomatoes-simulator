// Assignment: 7
// Name: Joel Hudgens
// StudentID: 1224491216
// Lecture: T Th, 10: 30
// Description: //This class is an Object
 				//Each Movie has a Movie Genre

import java.io.Serializable;

public class MovieGenre implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L; 
    private String genre;
    private String productionCompany;

    public MovieGenre(String genre, String productionCompany) {
        this.genre = genre;
        this.productionCompany = productionCompany;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return genre + " Movie\n" +
                "Production Company:\t" + productionCompany + '\n';
    }
}

