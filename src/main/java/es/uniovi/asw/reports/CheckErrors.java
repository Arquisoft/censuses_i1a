package main.java.es.uniovi.asw.reports;

/**
 * Class that checks if the input file contains errors
 * 
 * @author UO238754
 */
public class CheckErrors {
	
	static WriteReport report = new WriteReport();
	
	public static boolean checkData(String name, String nif, int pollingStation, String email){
		
		if(name.isEmpty()){
			report.write("Name not found.");
			return false;
		}
		if(nif.isEmpty()){
			report.write("NIF not found.");
			return false;
		}
		if(pollingStation == 0){
			report.write("Polling station not found.");
			return false;
		}
		if(email.isEmpty()){
			report.write("Email not found.");
			return false;
		}
		if(nif.length()!=9){
			report.write("Incorrect NIF.");
			return false;
		}
		return true;
	}

}
