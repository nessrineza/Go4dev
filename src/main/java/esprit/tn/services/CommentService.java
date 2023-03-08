package esprit.tn.services;

import esprit.tn.Entites.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment c);
    // read operation
    List<Comment> retrieveAllComments();
    Comment retrieveComment(Integer idComment) ;

    Comment updateComment(Comment p);


    // delete operation
    void removeCommentById(Integer idComment);
    public Comment assignCommentToPublication(Comment c,Integer publciationId) ;

}

