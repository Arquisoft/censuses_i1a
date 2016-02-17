package es.uniovi.asw.reportwriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that reports the errors found
 * It writes them in a file called ErrorsReport
 * 
 * @author UO238754
 */
public class WriteReport {
	
	File file = new File("ErrorsReport.txt");

	public void write(String data){
		
		try {
			FileWriter fileW = new FileWriter(file);
			fileW.write(data + "\n");
			fileW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
