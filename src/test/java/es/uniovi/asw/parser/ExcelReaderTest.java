package es.uniovi.asw.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.uniovi.asw.parser.reader.ExcelReader;
import es.uniovi.asw.parser.reader.FileReader;
import es.uniovi.asw.voter.Voter;

import static org.junit.Assert.*;

public class ExcelReaderTest {

	@Test
	public void testCorrectFile() throws IOException {
		List<Voter> votersFromFile;
		FileReader reader = new ExcelReader();
		List<Voter> expectedVoters = getVotersList();
		
		votersFromFile = reader.read("src/test/resources/test.xlsx");
		for(Voter expectedVoter : expectedVoters){
			for(Voter voterFromFile : votersFromFile){
			assertTrue(checkSameVoter(voterFromFile, expectedVoter));
			}
		}
	}
	
	private boolean checkSameVoter(Voter voterFromFile, Voter expectedVoter) {
		return voterFromFile.getEmail().equals(expectedVoter.getEmail()) &&
				voterFromFile.getName().equals(expectedVoter.getName()) &&
				voterFromFile.getNif().equals(expectedVoter.getNif()) &&
				voterFromFile.getPollingStation().equals(expectedVoter.getPollingStation());
	}

	private List<Voter> getVotersList(){
		List<Voter> voters = new ArrayList<Voter>();
		voters.add(new Voter("Juan Torres Pardo", "90500084Y", 1, "juan@gmail.com"));
		voters.add(new Voter("Luis López Fernando", "19160962F", 2, "fdezlpez@hotmail.com"));
		voters.add(new Voter("Ana Torres Pardo", "09940449X", 2, "anita87@gmail.com"));
		return voters;
	}

}
