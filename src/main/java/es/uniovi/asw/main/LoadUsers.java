package es.uniovi.asw.main;

import es.uniovi.asw.parser.reader.ExcelReader;

import java.io.IOException;


public class LoadUsers {
	
	public static void main(String[] args) throws IOException {
		ExcelReader reader = new ExcelReader();
		reader.read("test.xlsx"); 
	}

}
