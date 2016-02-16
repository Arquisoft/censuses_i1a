package main.java.es.uniovi.asw.letters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.java.es.uniovi.asw.passwords.PasswordGenerator;

/**
 * This class generates raw text letters
 * @author UO238754
 */
public class TxtLetter implements LetterGenerator{

	@Override
	public void generateLetter(String name, String email) {
		
		String password = PasswordGenerator.generateRandomPassword();
		
		File letter = new File("Letters/" + email + ".txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(letter));
			bw.write(name + ", you have been added to the Electoral Roll \nYour login name is: " +email +
					"\nYour password is: " + password);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
