import java.io.*;
import java.lang.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GoldenMasterCreate {
	
 	@Test
 	public void goldenMasterCreate() {
		int i = 1;
		for (Customer inputCustomer : goldenMasterInputs()) {
			writeToGoldenMasterFile("input-"+i+".txt", inputCustomer.statement());
			i++;
		}
 	}

 	/* 
  * Public methods
  */

	public static Vector<Customer> goldenMasterInputs() {

		Customer[] testCustomers = testCustomers();
		Vector<Movie> testMovies = testMovies();

		Vector<Customer> goldenMasterInputs = new Vector<Customer>();
		for(int i=0; i<testCustomers.length; ++i) {
			Customer customer = testCustomers[i];
			for (int k=0; k<3*(i+1); ++k) {
				customer.addRental(new Rental(testMovies.get(k), k%7));
			}
			goldenMasterInputs.add(customer);
		}
		return goldenMasterInputs;
	}

 	/* 
  * Private methods
  */

	private static Vector<Movie> testMovies() {
		Vector<Movie> movies = new Vector<Movie>();
		Integer[] movieTypes = new Integer[] { Movie.REGULAR, Movie.CHILDRENS, Movie.NEW_RELEASE };
		for (Integer movieType : movieTypes) {
 			for (int i=0; i<10; ++i) {
 				movies.add(new Movie("A " + movieTypeToString(movieType) +  " Movie " + i, movieType));
 			}
		}
		return movies;
	}

	private static Customer[] testCustomers() {
		return new Customer[] {
			new Customer("Daenerys Targaryan"),
			new Customer("Jon Snow"),
			new Customer("Gregor Clegane"),
			new Customer("Arya Stark"),
			new Customer("Sansa Stark"),
			new Customer("Censei Lannister"),
			new Customer("Tyrion Lannister"),
			new Customer("Ramsay Bolton"),
			new Customer("Petyr Baelish"),
			new Customer("Khal Drogo")
		};
	}

  private void writeToGoldenMasterFile(String fileName, String content) {
  	File goldenMasterFile = null;
  	try {
			File goldenMasterDir = new File(
				new File(".").getCanonicalPath() + File.separator + 
				"src" + File.separator + 
				"test" + File.separator + 
				"java" + File.separator + 
				"goldenmaster");
			goldenMasterDir.mkdirs();
			goldenMasterFile = new File(goldenMasterDir, fileName);
			if(goldenMasterFile.createNewFile()) { 
    		try (BufferedWriter bw = new BufferedWriter(new FileWriter(goldenMasterFile.getAbsolutePath()))) {
					bw.write(content);
				}
			}
		} 
		catch (IOException e) {
			throw new RuntimeException(
				"Got the error '" + e.getMessage() + 
				"' when trying to write to '" + fileName + "'", 
				e);
		}
	}

	private static String movieTypeToString(int movieType) {
		switch(movieType) {
			case Movie.REGULAR: return "Regular";
			case Movie.CHILDRENS: return "Childrens";
			case Movie.NEW_RELEASE: return "New Release";
			default: return "Unknown";
		}
	}



}