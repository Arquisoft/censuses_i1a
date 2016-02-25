package es.uniovi.asw.main;

import es.uniovi.asw.dbupdate.VoterCheck;
import es.uniovi.asw.parser.reader.ExcelReader;
import es.uniovi.asw.voter.Voter;

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
						
				ExcelReader reader = new ExcelReader();
				reader.read("test.xlsx"); 
				
				log.info("Voters info: ");
				for (Voter voter : voters.findAll()) {
					log.info(voter.toString());
				}
		}
}
