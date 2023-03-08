package esprit.tn.services;

import esprit.tn.Entites.Comment;
import esprit.tn.Entites.EmailDetails;
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
  @Autowired
  EmailService emailService;


    @Override
    public Comment addComment(Comment c) {

return         commentRepository.save(c);
    }
    @Override
    public Comment assignCommentToPublication(Comment c,Integer publciationId) {
        Publication e =publicationRepository.findById(publciationId).orElse(null);

        c.setPublication(e);
        EmailDetails emailDetails= new EmailDetails("becheikh.wassim@esprit.tn",
                "New comment has been added to your publication",
                "New comment","");

        emailService.sendSimpleMail(emailDetails);
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

    /*@Override
    public void signalAction() {


        List<Comment> pubs = retrieveAllComments();
        for (Comment pub : pubs) {
            if (pub.getReport() >= 3 && !pub.isVerif()) {/*send mail to admin and user
                pub.getUsers().get(1).getUsername();
                EmailDetails emailDetails = new EmailDetails(/*admin email"adminMail@esprit.tn",
                        pub.getUsers().get(0).getUsername() + "'s Comment has been reported "
                                + pub.getReport() + " times  ",
                        "Comment reported", "");
                emailService.sendSimpleMail(emailDetails);
                EmailDetails emailDetails2 = new EmailDetails
                        (pub.getUsers().get(0).getEmail(),

                                "Your Comment has been reported " + pub.getReport() + "times,it might be deleted.Contact admins for more info.  ",
                                "Comment reported", "");
                emailService.sendSimpleMail(emailDetails2);
                pub.setVerif(true);
                updateComment(pub);
                System.out.println("Comment reported multiple times");
            } else if
            (pub.getReport() > 5
                            && ((pub.getReport() * 0.15) > (pub.getLikes())) {
                {
                EmailDetails emailDetails = new EmailDetails(admmin email "adminMail@esprit.tn", pub.getUsers().get(0).getUsername()*/
                            /*    + "'s Comment has been deleted due to multiple reports ",
                        "Comment deleted", "");
                emailService.sendSimpleMail(emailDetails);
                EmailDetails emailDetails2 = new EmailDetails
                        (pub.getUsers().get(0).getEmail(),

                                "Your Comment has been deleted  ",
                                "Comment deleted", "");
                emailService.sendSimpleMail(emailDetails2);
                removeCommentById(pub.getId());
                System.out.println("Comment removed");
                System.out.println(pub.getUsers().get(1).getUsername());
            }
        }
    }*/
}
