package es.uniovi.asw.parser;

import org.apache.commons.cli.*;

/**
 * Provisional way to read from CMD
 * @author UO238739
 *
 */
public class ArgumentsParser implements ReadCensus {

	private static final String FORMAT = ".xlsx";
	private static final String FILEOPTION = "file";
	private CommandLineParser parser = new DefaultParser();
	private Options options = new Options();
	private String[] args;
	private String file;
	private StringBuilder error = new StringBuilder();

	public ArgumentsParser(String[] args) {
		options.addOption(FILEOPTION, true, "File to be processed");
		this.args = args;
	}

	private void processArguments() {
		String file;
		CommandLine line;
		try {
			line = parser.parse(options, args);
			if( line.hasOption(FILEOPTION) ) {
				file = line.getOptionValue(FILEOPTION);
				if(!file.endsWith(FORMAT)){
					error.append("The format is not " + FORMAT + "\n");
				} else {
					this.file = file;
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

	@Override
	public void read() {
		processArguments();
		CensusParser reader = new ExcelParser();
		reader.read(file);
	}
}
