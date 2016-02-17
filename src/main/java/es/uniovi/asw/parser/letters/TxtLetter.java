package es.uniovi.asw.parser.letters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import es.uniovi.asw.voter.Voter;

/**
 * This class generates raw text letters
 * @author UO238754
 */
public class TxtLetter implements LetterGenerator{

	@Override
	public void generateLetter(Voter voter) {
		File letter = new File("Letters/" + voter.getEmail() + ".txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(letter));
			bw.write(voter.getName() + ", you have been added to the Electoral Roll \nYour login name is: " + voter.getEmail() +
					"\nYour password is: " + voter.getPassword());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
