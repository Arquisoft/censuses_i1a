package es.uniovi.asw.parser.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class ExcelReader implements FileReader {

	private final static String NAME = "name";
	private final static String NIF = "nif";
	private final static String EMAIL = "email";
	private final static String STATION = "polling station code";
	private final static int FIRST_ROW = 0;

	/**
	 * Parses the input file to extract voters's info and creates object
	 * containers (Voter) with that info.
	 * 
	 * @throws IOException
	 */
	@Override
	public List<Voter> read(String file) throws IOException {
		List<Voter> voters = new ArrayList<Voter>();
		String name = null;
		String nif = null;
		String email = null;
		Integer station = null;
		
		FileInputStream input = new FileInputStream(new File(file));

		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(input);
		Sheet sheet = workbook.getSheetAt(0);
		CellReference header;
		for (Row row : sheet) {
			if (row.getRowNum() != FIRST_ROW) {
				for (Cell cell : row) {

					// get the type of column data (nif, name, ...)
					header = new CellReference(FIRST_ROW, cell.getColumnIndex());
					if (header.formatAsString().equalsIgnoreCase(NAME)) {
						name = cell.getStringCellValue();
					} else if (header.formatAsString().equalsIgnoreCase(NIF)) {
						nif = cell.getStringCellValue();
					} else if (header.formatAsString().equalsIgnoreCase(EMAIL)) {
						email = cell.getStringCellValue();
					} else if (header.formatAsString().equalsIgnoreCase(STATION)) {
						station = (int) cell.getNumericCellValue();
					}
				}
				voters.add(new Voter(name, nif, station, email));
			}
		}

		input.close(); // close resources before finishing
		return voters;
	}

}
