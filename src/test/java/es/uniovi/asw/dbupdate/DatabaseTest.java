package es.uniovi.asw.dbupdate;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hsqldb.Database;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.voter.Voter;
import es.uniovi.asw.dbupdate.*;

public class DBTest {
	
	List<Voter> voters = new ArrayList<Voter>();
	ConnectionManager c = new ConnectionManager();
	
	@Before
	public void setUp() throws Exception {
		voters = null;
		voters.add(new Voter("Ana Martinez",  "123456789A", 1, "anamar@gmail.com", "ersdht"));
		voters.add(new Voter("Olga Garcia", "456789123B", 2, "olgagar@gmail.com", "qgdt"));
		voters.add(new Voter("Tomas Fernandez", "789123456C", 1, "tomasfer@gmail.com", "sthc"));
		InsertVoters.insert(voters);
	}
	
	@Test
	public void testFindByNif() {
		try {
			assertEquals(true, InsertVoters.findByNif("123456789A", c.getConnection()));
			assertEquals(true, InsertVoters.findByNif("456789123B", c.getConnection()));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
