package es.uniovi.asw.parser.reader;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.voter.Voter;

/**
 * Interface that every class processing an input file for the census info must implement
 * @author UO238739
 *
 */
public interface FileReader {

	public List<Voter> read(String file) throws IOException;

}
