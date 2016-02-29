package es.uniovi.asw.dbupdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.voter.Voter;

/**
 * Inserts one or more voters into the database
 * @author UO238739
 *
 */
public class InsertVoters {

	private List<Voter> voters;
	
	private void insert(Voter voter) {
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

	public void insert(List<Voter> voters) {
		this.voters = voters;
		for (Voter voter : voters) {
						if(findByNif(voter.getNif())==null){ 
							insert(voter);
						}
		}
	}

	private Voter findByNif(String nif) {
		// TODO Auto-generated method stub
		return null;
	}

}
