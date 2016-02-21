package es.uniovi.asw.parser;

import org.apache.commons.cli.*;

import es.uniovi.asw.parser.letters.LetterGenerator;
import es.uniovi.asw.parser.letters.PDFLetter;
import es.uniovi.asw.parser.letters.TxtLetter;
import es.uniovi.asw.parser.reader.*;

/**
 * Class that reads from CMD.
 * It receives arguments to configure the file input and (in the future) output and their formats.
 * It also creates the Parser component and passes the input file to it.
 * @author UO238739
 *
 */
public class ArgumentsParser implements ReadCensus {

	public static final String TXT_OPTION = "txt";
	public static final String PDF_OPTION = "pdf";
	public static final String EXCEL_OPTION = "excel";
	public static final String EXCEL_FORMAT = ".xlsx";
	private CommandLineParser parser = new DefaultParser();
	private Options options = new Options();
	private String file;
	private FileReader reader;
	private LetterGenerator letter;

	public ArgumentsParser() {
		OptionGroup outputOptions = new OptionGroup();
		outputOptions.addOption(new Option(PDF_OPTION, false, "Sets the letter output to PDF"));
		outputOptions.addOption(new Option(TXT_OPTION, false, "Sets the letter output to TXT"));
		
		Option excelOption = new Option(EXCEL_OPTION, true, "Excel file to be processed");
		excelOption.setRequired(true);
		options.addOption(excelOption);
		options.addOptionGroup(outputOptions);
		
	}

	void processArguments(String[] args) throws ParseException {
		String file;
		CommandLine line;
			line = parser.parse(options, args);
			if( line.hasOption(EXCEL_OPTION) ) {
				file = line.getOptionValue(EXCEL_OPTION);
				if(file.endsWith(EXCEL_FORMAT)){
					this.file = file;
					this.reader = new ExcelReader();
				} else {
					throw new IllegalArgumentException("The format is not " + EXCEL_FORMAT + "\n");
				}
			} if( line.hasOption(PDF_OPTION) ) {
				this.letter = new PDFLetter();
				
			} if ( line.hasOption(TXT_OPTION) ) {
				this.letter = new TxtLetter();
			}
	}

	@Override
	public void read(String[] args) {
		try {
			processArguments(args);
			CensusParser parser = new CensusParser(reader, letter);
			parser.process(file);
		} catch (ParseException | IllegalArgumentException exp) {
			System.err.println( "ERROR: " + exp.getMessage() );
		}
	}

	String getFile() {
		return file;
	}

	FileReader getReader() {
		return reader;
	}

	LetterGenerator getLetter() {
		return letter;
	}
}
