package main.java.es.uniovi.asw.letters;

import main.java.es.uniovi.asw.passwords.PasswordGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * This class generates PDF letters
 * @author UO238754
 */
public class PDFLetter implements LetterGenerator{

	@Override
	public void generateLetter(String name, String email) throws FileNotFoundException, DocumentException {
		
		String password = PasswordGenerator.generateRandomPassword();
		
		FileOutputStream letter = new FileOutputStream("Letters/" + email + ".pdf");
		Document pdfDoc = new Document();
		PdfWriter.getInstance(pdfDoc, letter);
		pdfDoc.open();
		pdfDoc.add(new Paragraph(name +",you have been added to the Electoral Roll."));
		pdfDoc.add(new Paragraph("Your login name is: " + email));
		pdfDoc.add(new Paragraph("Your password is: " + password));
		pdfDoc.close();
	}
}