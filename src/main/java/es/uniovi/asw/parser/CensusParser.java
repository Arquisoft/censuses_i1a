package es.uniovi.asw.parser;

import java.util.List;
import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.dbupdate.InsertServices;
import es.uniovi.asw.dbupdate.InsertVoters;
import es.uniovi.asw.parser.letters.LetterGenerator;
import es.uniovi.asw.parser.letters.TxtLetter;
import es.uniovi.asw.parser.reader.ExcelReader;
import es.uniovi.asw.parser.reader.FileReader;
import es.uniovi.asw.voter.Voter;

/**
 * This is the core class of the Parser component. It receives the file from the
 * ArgumentsParser class, reads it to obtain all the voters, creates a password
 * for each one of them. Then uploads the voters and the passwords to the
 * database and generates a letter for them with the respective password and
 * email.
 * 
 * @author UO238739
 *
 */
public class CensusParser {
	private FileReader reader;
	private LetterGenerator letterGenerator;

	public CensusParser(FileReader reader, LetterGenerator letterGenerator) {
		this.reader = reader;
		this.letterGenerator = letterGenerator;
	}

	public CensusParser() {
		this(new ExcelReader(), new TxtLetter());
	}

	public CensusParser(FileReader reader) {
		this(reader, new TxtLetter());
	}

	public void process(String file) {
		List<Voter> voters;

		try {
			// obtain voters from file
			voters = reader.read(file);

			// create passwords for voters
			for (Voter voter : voters) {
				voter.setPassword(PasswordGenerator.generateRandomPassword());
			}

			// upload voters to the DB
			Insert updateDb = new InsertServices();
			updateDb.insert(voters);

			// create letters
			for (Voter voter : voters) {
				letterGenerator.generateLetter(voter);
			}
		} catch (Exception e) {
			//TODO exception handling
			System.err.println("Exception handling not yet implemented");
		}
	}
	
}
