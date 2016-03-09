package es.uniovi.asw.parser.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
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
		Row firstRow = sheet.getRow(FIRST_ROW);
		Cell header;
		Cell cell;
		for (Row row : sheet) {
			if (row.getRowNum() == FIRST_ROW) { 
				checkFirstRow(row);
			} else {
				for (int column = 0; column < row.getLastCellNum(); column++) {
					cell = row.getCell(column, Row.RETURN_BLANK_AS_NULL);
					
					//if cell is null then it is not checked
					if (cell != null) { 
						
						// get the type of column data (nif, name, ...)
						header = firstRow.getCell(column);
						if (header.getStringCellValue().equalsIgnoreCase(NAME)) {
							name = cell.getStringCellValue();
						} else if (header.getStringCellValue().equalsIgnoreCase(NIF)) {
							nif = cell.getStringCellValue();
						} else if (header.getStringCellValue().equalsIgnoreCase(EMAIL)) {
							email = cell.getStringCellValue();
						} else if (header.getStringCellValue().equalsIgnoreCase(STATION)) {
							station = (int) cell.getNumericCellValue();
						}
					}
				}
				voters.add(new Voter(name, nif, station, email));
			}
		}

		input.close(); // close resources before finishing
		
		//TODO check for cast errors
		
		return voters;
	}

	/**
	 * Checks if the format of the first row is correct
	 */
	private void checkFirstRow(Row row) {
		
		/*If some of the fields exists in the sheet it is marked as true
		 * Each field is assigned to a position in the array
		 */
		boolean[] fieldPresent= {false, false, false, false}; 
		for(Cell cell : row){
			if(cell.getStringCellValue().equalsIgnoreCase(NAME)){
				fieldPresent[0] = true;
			} else if (cell.getStringCellValue().equalsIgnoreCase(EMAIL)){
				fieldPresent[1] = true;
			} else if (cell.getStringCellValue().equalsIgnoreCase(NIF)){
				fieldPresent[2] = true;
			} else if (cell.getStringCellValue().equalsIgnoreCase(STATION)){
				fieldPresent[3] = true;
			}
		}
		if(!allElementsTrue(fieldPresent)){
			// TODO error
		}
		
	} 
	
	private boolean allElementsTrue(boolean[] array){
		for(boolean value: array){
			if(value == false){
				return false;
			}
		}
		return true;
		
	}
}
