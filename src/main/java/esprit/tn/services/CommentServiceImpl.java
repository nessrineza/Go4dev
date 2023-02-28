package esprit.tn.services;

import esprit.tn.Entites.Comment;
import esprit.tn.Entites.Publication;
import esprit.tn.repository.CommentRepository;
import esprit.tn.repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PublicationRepository publicationRepository;


    @Override
    public Comment addComment(Comment c) {


        return         commentRepository.save(c);
    }
    @Override
    public Comment assignCommentToPublication(Comment c,Integer publciationId) {
        Publication e =publicationRepository.findById(publciationId).orElse(null);

        c.setPublication(e);
        publicationRepository.save(e);

          return commentRepository.save(c);
    }
    @Override
    public Comment updateComment(Comment p) {
        return commentRepository.save(p);


    }

    @Override
    public void removeCommentById(Integer idComment) {
        commentRepository.deleteById(idComment);
    }



    @Override
    public Comment retrieveComment(Integer idComment) {
        return commentRepository.findById(idComment).orElse(null);
    }



    @Override
    public List<Comment> retrieveAllComments() {
        List<Comment> Comments =new ArrayList<>();
        commentRepository.findAll().forEach(Comments::add);

        return Comments;
    }

}
