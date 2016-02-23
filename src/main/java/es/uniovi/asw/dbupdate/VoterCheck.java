package es.uniovi.asw.dbupdate;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.voter.Voter;

public interface VoterRepository extends CrudRepository<Voter, Long>{
	
	/**
	 * Find X voter by NIF
	 * @param nif
	 * @return
	 */
	Voter findByNif(String nif);
	
	/**
	 * Return all voters
	 */
	List<Voter> findAll();


}