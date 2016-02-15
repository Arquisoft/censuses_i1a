package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.voter.Voter;

/**
 * Class that reads from an excel file to pass the data to a database
 * 
 * @author UO238739
 *
 */
public class ExcelParser implements CensusParser {

	private Voter voter;
	private final static String NAME = "name";
	private final static String NIF = "nif";
	private final static String EMAIL = "email";
	private final static String STATION = "polling station code";

	/**
	 * Parses the input file to extract voters's info and creates object containers (Voter) with that info
	 */
	@Override
	public void read(String file) {
		String name = null;
		String nif = null;
		String email = null;
		Integer station = null;

		try {
			FileInputStream input = new FileInputStream(new File("C:\\test.xlsx"));

			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			CellReference header;
			for (Row row : sheet) {
				for (Cell cell : row) {
					header = new CellReference(0, cell.getColumnIndex()); //get the type of column data (nif, name, ...)
					if(header.formatAsString().equalsIgnoreCase(NAME)){
						name = cell.getStringCellValue();
					} else if(header.formatAsString().equalsIgnoreCase(NIF)){
						nif = cell.getStringCellValue();
					} else if(header.formatAsString().equalsIgnoreCase(EMAIL)){
						email = cell.getStringCellValue();
					} else if(header.formatAsString().equalsIgnoreCase(STATION)){
						station = (int) cell.getNumericCellValue();
					}
				}
			}
			voter = new Voter(name, nif, station, email);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
