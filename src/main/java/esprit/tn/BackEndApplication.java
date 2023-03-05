package esprit.tn;

import esprit.tn.Entites.Publication;
import esprit.tn.services.BadWordFilter;
import esprit.tn.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);

    }

}
