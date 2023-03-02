package esprit.tn.services;

import esprit.tn.Entites.Publication;

import java.util.List;

public interface PublicationService {
    Publication addPublication(Publication p);

    // read operation
    List<Publication> retrieveAllPublications();
    Publication retrievePublication(Integer idPublication) ;

    Publication updatePublication(Publication p);


    // delete operation
    void removePublicationById(Integer idPublication);

    void assignUserToPub(Integer pubId, Long userId);
}
