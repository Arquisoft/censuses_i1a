package es.uniovi.asw.dbupdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import es.uniovi.asw.voter.Voter;

/**
 * Verifies input data and generates an error report if there is a lack of mandatory attributes
 * 
 * @author UO237212
 */
public class InsertP implements Insert {
	
	private List<Voter> voters;
	
	public InsertP() {
	}

	public boolean validateVoter(Voter voter) {
		if (!voter.getName().isEmpty() && !voter.getNif().isEmpty()
				&& !voter.getEmail().isEmpty() && !voter.getPassword().isEmpty()
				&& voter.getPollingStation() >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void insert(Voter voter) {
		
		if(!validateVoter(voter)){
			throw new IllegalArgumentException("This voter can not "
					+ "be logged into the system with missing attributes. Generating report...");
		}
		
		Connection c;
		try {
			c = ConnectionManager.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO CENSOS "
					+ "(NOMBRE, NIF, EMAIL, CODCOLEGIOELECTORAL, PASSWORD) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, voter.getName());
			ps.setString(2, voter.getNif());
			ps.setString(3, voter.getEmail());
			ps.setInt(4, voter.getPollingStation());
			ps.setString(5, voter.getPassword());
			ps.execute();
			
			System.out.println("Log in information for the voter " + voter.getName()
					+ " is:\n\t\t User: " + voter.getEmail() + " \n\t\t Password: "
					+ voter.getPassword());
			
			ps.close();
			c.close();
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			//report.setLog("Error introducing the voter " + voter.getName() + "; NIF: " + voter.getNif());
		}
		
	}
	
	public List<Voter> getVoters(){
 		return this.voters;
 	}

	@Override
	public void insert(List<Voter> voters) {
		for (Voter voter : voters) {
						if(voter.findByNif(voter.getNif())==null){ 
							insert(voter);
						}
		}

	}
}