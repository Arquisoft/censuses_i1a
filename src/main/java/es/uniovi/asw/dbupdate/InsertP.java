package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.voter.Voter;

/**
 * Verifies input data and generates an error report if there is a lack of mandatory attributes
 * 
 * @author UO237212
 */
public class InsertP {

	public static void validateVoters(List<Voter> voters){
		for(Voter voter : voters){
			if(!validateVoter(voter)){
				throw new IllegalArgumentException("This voter can not "
						+ "be logged into the system with missing attributes");
			}
		}
	}
	
	private static boolean validateVoter(Voter voter) {
		if (!voter.getName().isEmpty() && !voter.getNif().isEmpty()
				&& !voter.getEmail().isEmpty() && !voter.getPassword().isEmpty()
				&& voter.getPollingStation() >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
}