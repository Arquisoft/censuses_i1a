package es.uniovi.asw.parser;

import org.apache.commons.cli.*;

import es.uniovi.asw.parser.reader.*;

/**
 * Class that reads from CMD.
 * It receives arguments to configure the file input and (in the future) output and their formats.
 * It also creates the Parser component and passes the input file to it.
 * @author UO238739
 *
 */
public class ArgumentsParser implements ReadCensus {

	public static final String EXCEL_FORMAT = ".xlsx";
	public static final String EXCEL_OPTION = "excel";
	private CommandLineParser parser = new DefaultParser();
	private Options options = new Options();
	private String file;
	private StringBuilder error = new StringBuilder(); //provisional
	private FileReader reader;

	public ArgumentsParser() {
		options.addOption(EXCEL_OPTION, true, "Excel file to be processed");
	}

	void processArguments(String[] args) {
		String file;
		CommandLine line;
		try {
			line = parser.parse(options, args);
			if( line.hasOption(EXCEL_OPTION) ) {
				file = line.getOptionValue(EXCEL_OPTION);
				if(file.endsWith(EXCEL_FORMAT)){
					this.file = file;
					this.reader = new ExcelReader();
				} else {
					error.append("The format is not " + EXCEL_FORMAT + "\n");
				}
			} else {
				error.append("The argument is missing\n");
			}
		} catch (ParseException exp) {
			System.err.println( "Error: " + exp.getMessage() );
			error.append(exp.getMessage()); error.append("\n");
		}
	}

	/**
	 * Provisional
	 */
	public String getErrors(){
		if(error.length()==0){
			error.append("No errors (*≧▽≦)");
		}
		return error.toString();
	}

	@Override
	public void read(String[] args) {
		processArguments(args);
		CensusParser parser = new CensusParser(reader);
		parser.process(file);
	}

	String getFile() {
		return file;
	}

	FileReader getReader() {
		return reader;
	}
}
