package esprit.tn.services;

import esprit.tn.Entites.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment c);
    // read operation
    List<Comment> retrieveAllComments();
    Comment retrieveComment(Integer idComment) ;
    List<Comment> retrieveCommentsByPub(Integer idPub);

    Comment updateComment(Comment p);


    // delete operation
    void removeCommentById(Integer idComment);
    public Comment assignCommentToPublication(Comment c,Integer publciationId) ;

    public void likeComment(Integer idCom);

}

