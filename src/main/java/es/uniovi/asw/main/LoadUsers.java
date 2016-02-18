package es.uniovi.asw.main;

import es.uniovi.asw.parser.ArgumentsParser;
import es.uniovi.asw.parser.ReadCensus;

public class LoadUsers {

	public static void main(String... args) {
		ReadCensus census = new ArgumentsParser(args);
		census.read();
		System.err.println(((ArgumentsParser) census).getErrors()); //to be removed 
	}
	
}
