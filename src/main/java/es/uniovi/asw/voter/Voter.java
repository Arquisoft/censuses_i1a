package es.uniovi.asw.voter;
/**
 * Class made to serve as object container to transport data from the parser to the database
 * @author UO238739
 *
 */
public class Voter  {

	private String nif;
	private String name;
	private Integer pollingStation;
	private String email;
	private String password;
	
	protected Voter(){}
	
	public Voter(String nif, String email){
		this.nif = nif;
		this.email = email;
	}
	
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
	public Integer getPollingStation() {
		return pollingStation;
	}
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", nif=" + nif + ", pollingStation=" + pollingStation + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return true;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPollingStation(Integer pollingStation) {
		this.pollingStation = pollingStation;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
