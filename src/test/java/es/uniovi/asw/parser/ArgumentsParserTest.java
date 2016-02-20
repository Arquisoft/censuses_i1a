package es.uniovi.asw.parser;

import static org.junit.Assert.*;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;

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
	public void testMissingArgument() throws ParseException{
		String[] args = {"-"+ArgumentsParser.EXCEL_OPTION};
		argParser.processArguments(args);
	}

}
