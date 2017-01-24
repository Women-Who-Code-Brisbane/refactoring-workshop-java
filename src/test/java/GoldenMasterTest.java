import java.io.*;
import java.lang.*;
import java.util.*;
import org.apache.commons.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GoldenMasterTest {
	
 	@Test
 	public void goldenMasterTest() {
		int i = 1;
		for (Customer inputCustomer : GoldenMasterCreate.goldenMasterInputs()) {
 			String expectedStatement = readFromGoldenMasterFile("input-"+i+".txt");
 			assertThat(inputCustomer.statement(), is(expectedStatement));
			i++;
 		}
 	}
	
 	@Test
 	public void goldenMasterHtmlTest() {
		int i = 1;
		for (Customer inputCustomer : GoldenMasterCreate.goldenMasterInputs()) {
 			String expectedStatement = readFromGoldenMasterFile("input-"+i+".html");
 			assertThat(inputCustomer.htmlStatement(), is(expectedStatement));
			i++;
 		}
 	}

 	/* 
  * Private methods
  */

  private String readFromGoldenMasterFile(String fileName) {
		String content = "";
  	try {
  		File directory = new File(".");
			File file = new File(
				directory.getCanonicalPath() + File.separator + 
				"src" + File.separator + 
				"test" + File.separator + 
				"java" + File.separator + 
				"goldenmaster",
				fileName);
			if(file.exists()) { 
				try (FileInputStream inputStream = new FileInputStream(file.getCanonicalPath())) {   
    			content = IOUtils.toString(inputStream);
    		}
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		return content;
  }


}