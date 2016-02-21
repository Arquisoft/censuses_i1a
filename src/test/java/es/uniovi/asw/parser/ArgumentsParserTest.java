package es.uniovi.asw.parser;

import static org.junit.Assert.*;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.parser.letters.PDFLetter;
import es.uniovi.asw.parser.letters.TxtLetter;
import es.uniovi.asw.parser.reader.ExcelReader;

public class ArgumentsParserTest {
	
	private ArgumentsParser argParser;
	
	@Before
	public void setUp(){
		this.argParser = new ArgumentsParser();
	}

	@Test
	public void testExcelArguments() throws ParseException {
		String[] args = {"-"+ArgumentsParser.EXCEL_OPTION, "src/test/resources/test.xlsx"};
		argParser.processArguments(args);
		assertEquals(args[1], argParser.getFile());
		assertTrue(argParser.getReader() instanceof ExcelReader);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWrongFormat() throws ParseException{
		String[] args = {"-"+ArgumentsParser.EXCEL_OPTION, "src/test/resources/test.doc"};
		argParser.processArguments(args);
	}
	
	@Test(expected=MissingArgumentException.class)
	public void testMissingFileArgument() throws ParseException{
		String[] args = {"-"+ArgumentsParser.EXCEL_OPTION};
		argParser.processArguments(args);
	}
	
	@Test(expected=MissingOptionException.class)
	public void testMissingMandatoryOption() throws ParseException{
		String[] args = {"-"+ArgumentsParser.PDF_OPTION};
		argParser.processArguments(args);
	}
	
	@Test
	public void testPdfArguments() throws ParseException{
		String[] args = {"-"+ArgumentsParser.PDF_OPTION, "-"+ArgumentsParser.EXCEL_OPTION, 
				"src/test/resources/test.xlsx",};
		argParser.processArguments(args);
		assertTrue(argParser.getLetter() instanceof PDFLetter);
	}
	
	@Test
	public void testTxtArguments() throws ParseException{
		String[] args = {"-"+ArgumentsParser.TXT_OPTION, "-"+ArgumentsParser.EXCEL_OPTION, 
				"src/test/resources/test.xlsx",};
		argParser.processArguments(args);
		assertTrue(argParser.getLetter() instanceof TxtLetter);
	}
	
	@Test
	public void testTwoArguments() throws ParseException{
		String[] args = {"-"+ArgumentsParser.EXCEL_OPTION, 
				"src/test/resources/test.xlsx", "-"+ArgumentsParser.TXT_OPTION};
		argParser.processArguments(args);
		assertEquals(args[1], argParser.getFile());
		assertTrue(argParser.getReader() instanceof ExcelReader);
		assertTrue(argParser.getLetter() instanceof TxtLetter);
	}

}
