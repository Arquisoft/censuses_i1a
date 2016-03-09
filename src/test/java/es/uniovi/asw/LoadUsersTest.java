package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.dbupdate.ConnectionManager;
import es.uniovi.asw.dbupdate.InsertVoters;
import es.uniovi.asw.main.LoadUsers;
import es.uniovi.asw.parser.ArgumentsParser;
import es.uniovi.asw.voter.Voter;

public class LoadUsersTest {

	private static final String URL = "jdbc:hsqldb:file:src/test/resources/db/testdb";
	private Voter v1 = new Voter("Juan Torres Pardo", "90500084Y", 1, "juan@gmail.com");
	private Voter v2 = new Voter("Luis López Fernando", "19160962F", 2, "fdezlpez@hotmail.com");
	private Voter v3 = new Voter("Ana Torres Pardo","09940449X", 2, "anita87@gmail.com");

	@Before
	public void setUp() throws SQLException{
		ConnectionManager.setURL(URL);
		emptyDatabase();
	}
	
	@Test
	public void test() throws IOException {
		String[] args = {"-"+ArgumentsParser.EXCEL_OPTION, "src/test/resources/test.xlsx"};
		LoadUsers.main(args);
		assertTrue(new File("Letters/" + v1.getEmail() + ".txt").exists());
		assertTrue(new File("Letters/" + v2.getEmail() + ".txt").exists());
		assertTrue(new File("Letters/" + v3.getEmail() + ".txt").exists());
	}

	@After
	public void emptyDatabase() throws SQLException{
		InsertVoters.deleteAllVoters(ConnectionManager.getConnection(URL));
	}
}
