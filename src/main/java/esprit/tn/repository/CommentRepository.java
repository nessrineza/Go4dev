package esprit.tn.repository;

import esprit.tn.Entites.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Integer> {
@Query(value ="SELECT * FROM comment group by likes desc", nativeQuery = true)
    List<Comment>CommentSortedByLikes();
    @Query(value ="SELECT * FROM comment group by date desc", nativeQuery = true)
    List<Comment>CommentSortedByDate();
    public List<Comment> findCommentsByPublication_Id(Integer id);

    public Comment findCommentByIdIs(Integer idCom);
}
