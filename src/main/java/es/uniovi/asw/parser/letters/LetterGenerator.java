package es.uniovi.asw.parser.letters;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import es.uniovi.asw.voter.Voter;


/**
 * @author UO238754
 */
public interface LetterGenerator {

	/**
	 * Generates the letters to communicate the user that he has been added to the Electoral Roll
	 * The letter contain his login name and password.
	 * The login name will be the email.
	 * It receives the data from the user
	 * @param name
	 * @param email (login name)
	 * @throws FileNotFoundException 
	 * @throws DocumentException 
	 */
	public void generateLetter(Voter voter) throws FileNotFoundException, DocumentException;
	
}
