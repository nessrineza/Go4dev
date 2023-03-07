package esprit.tn.repository;

import esprit.tn.Entites.Comment;
import esprit.tn.Entites.Publication;
import org.springframework.data.repository.CrudRepository;

public interface PublicationRepository extends CrudRepository<Publication,Integer> {
    public Publication findPublicationsByComments(Comment comment);
}
