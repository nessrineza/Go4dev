package esprit.tn.services;

import esprit.tn.Entites.Publication;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.List;

public interface PublicationService {
    @Transactional

    Publication addPublication(Publication p);

    // read operation
    List<Publication> retrieveAllPublications();
    Publication retrievePublication(Integer idPublication) ;

    Publication updatePublication(Publication p);


    // delete operation
    void removePublicationById(Integer idPublication);

    @Transactional
    List<Publication> assignUserToPub(Integer pubId, Long userId);
     boolean containsBadWords(String input);
     void signalAction();
     Boolean isFormal(String text) ;
    }
