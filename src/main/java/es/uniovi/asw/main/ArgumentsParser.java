package es.uniovi.asw.main;

import org.apache.commons.cli.*;

/**
 * A test of how to read from CMD
 * @author UO238739
 *
 */
public class ArgumentsParser {

	private CommandLineParser parser = new DefaultParser();
	private Options options = new Options();
	private String[] args;
	private StringBuilder error = new StringBuilder();

	public ArgumentsParser(String[] args) {
		options.addOption("file", true, "File to be processed");
		this.args = args;
	}

	public void process() {
		String file;
		CommandLine line;
		try {
			line = parser.parse(options, args);
			if( line.hasOption( "file" ) ) {
				file = line.getOptionValue("file");
				if(!file.endsWith(".xlsx")){
					error.append("The format is not .\"xlsx\n");
				}
			} else {
				error.append("The argument is missing\n");
			}
		} catch (ParseException exp) {
			System.err.println( "Error: " + exp.getMessage() );
			error.append(exp.getMessage()); error.append("\n");
		}
	}

	public String getErrors(){
		if(error.length()==0){
			error.append("No errors (*≧▽≦)");
		}
		return error.toString();
	}
	
}
