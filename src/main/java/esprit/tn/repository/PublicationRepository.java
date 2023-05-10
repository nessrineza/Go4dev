package esprit.tn.repository;

import esprit.tn.Entites.Comment;
import esprit.tn.Entites.Publication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PublicationRepository extends CrudRepository<Publication,Integer> {
    public Publication findPublicationByDate(Date date);
    @Query(value ="SELECT * FROM publication group by favoris desc", nativeQuery = true)
    List<Publication> publicationSortedByFavoris();
    void deletePublicationById(Integer id );
    public Publication findPublicationById(Integer id);

}
