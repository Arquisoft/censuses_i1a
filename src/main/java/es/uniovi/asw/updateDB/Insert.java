package es.uniovi.asw.updateDB;

import es.uniovi.asw.voter.Voter;
/**
 * Receives and object with the information to insert in the database
 * 
 * @author UO237212
 */
public interface Insert {
	
	public void insert(Voter voter);
	
}