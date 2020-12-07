package com.cvservice;

import com.cvservice.models.Candidate;
import com.cvservice.parser.Parser;
import com.cvservice.repositories.db.CVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;

@SpringBootApplication
 public class CVMicroserviceApplication {

	@Autowired
	private CVRepository CVRepository;


	public static void main(String[] args) {
		SpringApplication.run(CVMicroserviceApplication.class, args);

  	}

	@Bean
	public CommandLineRunner demo(CVRepository repository) {
		return (args) -> {
			repository.deleteAll(); // J'ai pu remarquer que des fois, a lancer le serveur en boucle, la bd ne se vide pas.
			// En metant le chemin d'un fichier de pdf de CV ici, vous pouvez tester sans passer par l'upload
			Candidate cyann = Parser.parser(new File("/home/sslye/Workspace/DAAR/tourist-Unesco/src/main/java/com/example/touristmicroservice/parsing/CV_Cyann_DONNOT.pdf"));
 			Candidate claire = Parser.parser(new File("/home/sslye/Workspace/DAAR/tourist-Unesco/src/main/java/com/example/touristmicroservice/parsing/CV_Claire_BARDOUX.pdf"));
			Candidate pov2 = Parser.parser(new File("cvtheque/VELLY_Pierre-octave.docx"));

 			repository.save(cyann); repository.save(claire); repository.save(pov2);

 			System.out.println(repository.findByLastName("Donnot").size());
 			System.out.println(repository.findBySkillsName(List.of("anglais","java")).size());
 			System.out.println(repository.findBySkillsName(List.of("anglais", "espagnole")).size());
			System.out.println(repository.findBySkillsName(List.of("daar")).size());
			System.out.println(repository.findByPhone("0651751245").size());

			System.out.println("----END----");


		};
	}

}
