package es.uniovi.asw.voter;

/**
 * Class made to serve as object container to transport data from the parser to the database
 * @author UO238739
 *
 */

public class Voter {

	private String name;
	private String nif;
	private int pollingStation;
	private String email;
	private String password;
	
	public Voter(String name, String nif, int pollingStation, String email) {
		super();
		this.name = name;
		this.nif = nif;
		this.pollingStation = pollingStation;
		this.email = email;
	}
	
	public Voter(String name, String nif, int pollingStation, String email, String password) {
		this(name, nif, pollingStation, email);
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public String getNif() {
		return nif;
	}
	public int getPollingStation() {
		return pollingStation;
	}
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", nif=" + nif + ", pollingStation=" + pollingStation + ", email=" + email + "]";
	}
	
}
