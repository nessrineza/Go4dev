package esprit.tn.controllers;

import esprit.tn.Entites.Comment;
import esprit.tn.Entites.EmailDetails;
import esprit.tn.repository.CommentRepository;
import esprit.tn.repository.UserRepository;
import esprit.tn.services.BadWordFilter;
import esprit.tn.services.CommentService;
import esprit.tn.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/Comment")

public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    PublicationService publicationService;
   /* @Autowired
    EmailService emailService;*/
    @Autowired
    private CommentRepository commentRepository;
@Autowired
    private UserRepository userRepository;
BadWordFilter badWordFilter;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Annotation

    // Save operation
    @Transactional

    @PostMapping("/add/{id}/{id2}")
    public Comment saveComment(
            @RequestBody Comment comment,
            @PathVariable("id")Integer pubId,@PathVariable("id2")Long userId)
    {
comment.setPseudo(userRepository.getById(userId).getUsername());
publicationService.assignUserToPub(pubId,userId);

        comment.setContent( badWordFilter.getCensoredText(comment.getContent()));

        return commentService.assignCommentToPublication(comment,pubId);
    }

    // Read operation
    @GetMapping("/Comments")
    public List<Comment> retrieveAllComments()
    {

        return commentService.retrieveAllComments();
    }
    @GetMapping("/retrieve/{id}")
    public Comment getComment(@PathVariable("id") Integer commentId)
    {

        return commentService.retrieveComment(commentId);
    }


    // Update operation
    @PutMapping("/update")
    public Comment
    updateComment(@RequestBody Comment Comment)
    {

        return commentService.updateComment(Comment);
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    public String deleteCommentById(@PathVariable("id") Integer commentId)
    {

        commentService.removeCommentById(commentId);
        return "Deleted Successfully";
    }

    @GetMapping("/CommentsSorted")
    public List<Comment> retrieveCommentsSorted()
    {

        return commentRepository.CommentSortedByLikes();
    }

  /*  @PutMapping("/assignComment/{id1}/{id2}")
    public void assignCommentToPublication
            (@PathVariable("id1")Integer commentId,@PathVariable("id2")Integer publciationId)
    { commentService.assignCommentToPublication(commentId,publciationId);}*/

}
