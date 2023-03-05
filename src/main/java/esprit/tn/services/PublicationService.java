package esprit.tn.services;

import esprit.tn.Entites.Publication;

import javax.transaction.Transactional;
import java.util.List;

public interface PublicationService {
    Publication addPublication(Publication p);

    // read operation
    List<Publication> retrieveAllPublications();
    Publication retrievePublication(Integer idPublication) ;

    Publication updatePublication(Publication p);


    // delete operation
    void removePublicationById(Integer idPublication);

    @Transactional
    List<Publication> assignUserToPub(Integer pubId, Long userId);
    public boolean containsBadWords(String input);
    public void signalAction();
}
