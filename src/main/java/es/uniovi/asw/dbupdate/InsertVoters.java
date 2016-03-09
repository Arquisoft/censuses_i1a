package es.uniovi.asw.dbupdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.voter.Voter;

/**
 * Inserts one or more voters into the database
 * 
 * @author UO238739
 *
 */
public class InsertVoters {

	private List<Voter> voters;

	private static void insert(Voter voter) {
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

			System.out.println("Log in information for the voter " + voter.getName() + " is:\n\t\t User: "
					+ voter.getEmail() + " \n\t\t Password: " + voter.getPassword());

			ps.close();
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Voter> getVoters() {
		return this.voters;
	}

	public static void insert(List<Voter> voters) {
		Connection c;
		try {
			c = ConnectionManager.getConnection();
			for (Voter voter : voters) {
				if (!findByNif(voter.getNif(), c)) {
					insert(voter);
				}
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * For testing purposes
	 */
	static boolean findByNif(String nif, Connection conn) throws SQLException {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM CENSOS WHERE NIF = ?");
			ps.setString(1, nif);
			ResultSet rs = ps.executeQuery();
			return rs != null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * For testing purposes
	 */
	static boolean delete(String nif, Connection conn) throws SQLException {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM CENSOS WHERE NIF = ?");
			ps.setString(1, nif);
			boolean succes = ps.executeUpdate() > 0;
			return succes;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * For testing purposes
	 */
	public static boolean deleteAllVoters(Connection conn) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM CENSOS");
			boolean succes = ps.executeUpdate() > 0;
			return succes;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
