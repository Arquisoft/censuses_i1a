package es.uniovi.asw.dbupdate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.voter.Voter;

public class DatabaseTest {
	
	private List<Voter> voters;
	private Voter v1;
	private Voter v2;
	private Voter v3;
	private static final String URL = "jdbc:hsqldb:file:src/test/resources/db/testdb";
	
	@Before
	public void setUp() throws SQLException {
		voters = new ArrayList<Voter>();
		v1 = new Voter("Ana Martinez", "123456789A", 1, "anamar@gmail.com", "ersdht");
		v2 = new Voter("Olga Garcia", "456789123B", 2, "olgagar@gmail.com", "qgdt");
		v3 = new Voter("Tomas Fernandez", "789123456C", 1, "tomasfer@gmail.com", "sthc");
		voters.add(v1);
		voters.add(v2);
		voters.add(v3);
		emptyDatabase(); //delete voters from previously failed tests
	}
	
	@Test
	public void testInsert() throws SQLException {
		InsertVoters.insert(voters);
		for(Voter voter : voters){
			assertTrue(InsertVoters.findByNif(voter.getNif(), ConnectionManager.getConnection(URL)));
		}
	}

	@After
	public void emptyDatabase() throws SQLException{
		for(Voter voter : voters){
			InsertVoters.delete(voter.getNif(), ConnectionManager.getConnection());
		}
	}
}
