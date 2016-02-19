package es.uniovi.asw.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArgumentsParserTest {
	
	private ArgumentsParser argParser;
	
	@Before
	public void setUp(){
		this.argParser = new ArgumentsParser();
	}

	@Test
	public void testExcelArguments() {
		String[] args = {ArgumentsParser.EXCEL_OPTION, "src/test/resources/test.xlsx"};
		argParser.processArguments(args);
		assertEquals(args[1], argParser.getFile());
		assertTrue(argParser.getReader() instanceof ExcelReaderTest);
	}

}
