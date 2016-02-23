package es.uniovi.asw.dbupdate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbupdate.VoterCheck;
import es.uniovi.asw.main.LoadUsers;
import es.uniovi.asw.voter.Voter;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoadUsers.class)
public class DatabaseTest {

	@Autowired
	VoterCheck ch;
	List<Voter> voters = new ArrayList<Voter>();
	
	@Before
	public void setUp() throws Exception {
		voters = ch.findAll(); 
		ch.deleteAll(); 
		
		ch.save(new Voter("Ana Martinez",  "123456789A", 1, "anamar@gmail.com", "ersdht"));
		ch.save(new Voter("Olga Garcia", "456789123B", 2, "olgagar@gmail.com", "qgdt"));
		ch.save(new Voter("Tomas Fernandez", "789123456C", 1, "tomasfer@gmail.com", "sthc"));
		
	}
	
	@Test
	public void testFindByNif() {

		assertEquals("Ana Martinez", ch.findByNif("123456789A").getName());
		assertEquals("olgagar@gmail.com", ch.findByNif("456789123B").getEmail());
		assertEquals(1 , ch.findByNif("789123456C").getPollingStation());
	
	
	}
	
	/**
	 * Delete test
	 */
	@Test
	public void testDelete(){
		
		ch.delete(ch.findByNif("789123456C"));
		assertEquals(null, ch.findByNif("789123456C"));
		assertEquals(2, ch.count());
	}
	
	@Test
	public void testAddNewVoters(){
		
		ch.save(new Voter("Antonio Alvarez", "147258369A", 1, "antal@gmail.com", "fgh"));
		ch.save(new Voter("Maria Martinez", "258369147B", 2, "marmar@gmail.com", "sdgy"));
		ch.save(new Voter("Pablo Perez", "369147258C", 3, "pape@gmail.com", "sdgtj"));
		ch.save(new Voter("Ursula Garcia", "321654987D", 2, "urgar@gmail.com", "ngft"));
		ch.save(new Voter("Adrian Suarez", "987654321E", 1, "adris@gmail.com", "srty"));
	
		assertEquals(8, ch.count());

		assertEquals("Antonio Alvarez", ch.findByNif("147258369A").getName());
		assertEquals("marmar@gmail.com", ch.findByNif("258369147B").getEmail());
		assertEquals(3, ch.findByNif("369147258C").getPollingStation());
		assertEquals("Ursula Garcia", ch.findByNif("321654987D").getName());
		assertEquals("adris@gmail.com", ch.findByNif("987654321E").getEmail());
		
	}

}