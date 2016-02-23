package es.uniovi.asw.main;

import es.uniovi.asw.parser.ArgumentsParser;
import es.uniovi.asw.parser.ReadCensus;
import es.uniovi.asw.voter.Voter;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LoadUsers {

	private static final Logger log = LoggerFactory.getLogger(LoadUsers.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LoadUsers.class);
	}
	
	@Bean
		public CommandLineRunner demo(final VoterCheck voters) {
			return (args) -> {
				
				ExcelReader reader = (ExcelReader)ParserFactory.getParserXlsx(repository);
				reader.read("test.xlsx"); //lee el fichero en formato .xlsx
				leerxlsx.process();			
				
				log.info("Voters info: ");
				for (Voter voter : voters.findAll()) {
					log.info(voter.toString());
				}
			};
			
		}
}
