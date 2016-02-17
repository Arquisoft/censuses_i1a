package es.uniovi.asw.updateDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.uniovi.asw.ReportWriter.WriteReport;
import es.uniovi.asw.voter.Voter;

/**
 * Verifies input data and generates an error report if there is a lack of mandatory attributes
 * 
 * @author UO237212
 */
public class InsertP implements Insert {
	
	WriteReport report;
	

	public InsertP(WriteReport report) {
		this.report = report;
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
			c = Jdbc.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO CENSOS "
					+ "(NOMBRE, NIF, EMAIL, CODCOLEGIOELECTORAL, PASSWORD) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, voter.getName());
			ps.setString(2, voter.getNif());
			ps.setString(3, voter.getEmail());
			ps.setInt(4, voter.getPollingStation());
			ps.setString(5, voter.getPassword());
			ps.execute();
			
			System.out.println("Log in information for the voter " + v.getName()
					+ " is:\n\t\t User: " + v.getEmail() + " \n\t\t Password: "
					+ v.getPassword());
			
			ps.close();
			c.close();
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			report.setLog("Error introducing the voter " + voter.getName() + "; NIF: " + voter.getNif());
		}
		
	}

}