package es.uniovi.asw.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import es.uniovi.asw.parser.letters.LetterGenerator;
import es.uniovi.asw.parser.letters.PDFLetter;
import es.uniovi.asw.parser.letters.TxtLetter;
import es.uniovi.asw.voter.Voter;

public class LettersTest {

	private LetterGenerator generator;
	private Voter voter;
	private File newLetter;
	
	@Before
	public void setUp(){
		voter = new Voter("Testín Téstez", "00000000T", 1, "test@email.com");
	}
	
	@Test
	public void testTxtLetter() throws FileNotFoundException, DocumentException {
		generator = new TxtLetter();
		generator.generateLetter(voter);
		newLetter = new File("Letters/" + voter.getEmail() + ".txt");
		assertTrue(newLetter.exists());
	}
	
	@Test
	public void testPdfLetter() throws FileNotFoundException, DocumentException {
		generator = new PDFLetter();
		generator.generateLetter(voter);
		newLetter = new File("Letters/" + voter.getEmail() + ".pdf");
		assertTrue(newLetter.exists());
	}

}
