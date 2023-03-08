package esprit.tn.repository;

import esprit.tn.Entites.Comment;
import esprit.tn.Entites.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface PublicationRepository extends CrudRepository<Publication,Integer> {
    public Publication findPublicationsByComments(Comment comment);
    public Publication findPublicationByDate(Date date);
}
