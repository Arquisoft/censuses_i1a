package es.uniovi.asw.dbupdate;

import java.util.List;

import es.uniovi.asw.voter.Voter;

/**
 * Class acting as the façade for the dbupdate component
 * @author UO238739
 *
 */
public class InsertServices implements Insert {
	
	
	@Override
	public void insert(List<Voter> voters) {
		InsertP.validateVoters(voters);
		new InsertVoters().insert(voters);
	}

}
