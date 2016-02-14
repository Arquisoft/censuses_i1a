package es.uniovi.asw.main;

public class Main {

	public static void main(String... args) {
		ArgumentsParser parser = new ArgumentsParser(args);
		parser.process();
		System.err.println(parser.getErrors());
	}
	
}
