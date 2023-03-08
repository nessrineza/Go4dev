package esprit.tn.services;

import esprit.tn.Entites.EmailDetails;
import esprit.tn.Entites.Publication;
import esprit.tn.Entites.User;
import esprit.tn.repository.PublicationRepository;
import esprit.tn.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;


    @Override

    public Publication addPublication(Publication p) {


        return publicationRepository.save(p);

    }

    @Override
    public Publication updatePublication(Publication p) {
        return publicationRepository.save(p);


    }

    @Override
    public void removePublicationById(Integer idPublication) {
        publicationRepository.deleteById(idPublication);
    }

    @Override
    @Transactional
    public List<Publication> assignUserToPub(Integer pubId, Long userId) {
        Publication p = publicationRepository.findById(pubId).orElse(null);
        User u = userRepository.findById(userId).orElse(null);

        u.getPublications().add(p);
        userRepository.save(u);

        publicationRepository.save(p);

        return u.getPublications();
    }


    @Override
    public Publication retrievePublication(Integer idPublication) {
        return publicationRepository.findById(idPublication).orElse(null);
    }


    @Override
    public List<Publication> retrieveAllPublications() {
        List<Publication> publications = new ArrayList<>();
        publicationRepository.findAll().forEach(publications::add);

        return publications;
    }


    public boolean containsBadWords(String input) {

        List<String> badWords = Arrays.asList("badword1", "badword2", "badword3");

        for (String word : badWords) {
            if (input.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void signalAction() {


        List<Publication> pubs = retrieveAllPublications();
        for (Publication pub : pubs) {
            if (pub.getReport() >= 3 && !pub.isVerif()) {/*send mail to admin and user
                pub.getUsers().get(1).getUsername();*/

                EmailDetails emailDetails = new EmailDetails(/*admin email*/"adminMail@esprit.tn",
                        pub.getUsers().get(0).getUsername() + "'s publication has been reported "
                                + pub.getReport() + "  times  ",
                        "Publication reported", "");
                emailService.sendSimpleMail(emailDetails);
                EmailDetails emailDetails2 = new EmailDetails
                        (pub.getUsers().get(0).getEmail(),

                                "Your publication has been reported " + pub.getReport() + "times,it might be deleted.Contact admins for more info.  ",
                                "Publication reported", "");
                emailService.sendSimpleMail(emailDetails2);
                pub.setVerif(true);
                updatePublication(pub);
                System.out.println("publication reported multiple times");
            } else if
            (pub.getReport() > 5
                            && ((pub.getReport() * 0.15) > (pub.getUsers().size())) ||
                            (pub.getReport() > 5 &&
                                    (pub.getReport() * 0.25) > (pub.getFavoris())))
                /*les Reportes supérieur à 15% de nombre des users => supprimer automatiquement le pub
                 * et envoyer mail à user et admin  */ {
                EmailDetails emailDetails = new EmailDetails(/*admin email*/"adminMail@esprit.tn",
                        pub.getUsers().get(0).getUsername()
                                + "'s publication has been deleted due to multiple reports ",
                        "Publication deleted", "");
                emailService.sendSimpleMail(emailDetails);
                EmailDetails emailDetails2 = new EmailDetails
                        (pub.getUsers().get(0).getEmail(),

                                "Your publication has been deleted  ",
                                "Publication deleted", "");
                emailService.sendSimpleMail(emailDetails2);
                removePublicationById(pub.getId());
                System.out.println("publication removed");
                System.out.println(pub.getUsers().get(1).getUsername());
            }
        }
    }

    public Boolean isFormal(String text) {
        int informalCount = 0;
        String[] tokens = text.split("\\s+");
        for (String token : tokens) {
            if (token.matches("(?i:i|me|my|you|your|he|him|his|she|her|hers|it|its|we|us|our|they|them|their|this|that|these|those)")) {
                informalCount++;
            }
            if (token.matches("(?i:gonna|wanna|gotta|shoulda|coulda|woulda|ain't|dunno|cuz|lol|omg|" +
                    "wtf|Howdy|Yo| Hey| Sup| What's up| Wazzup| Dude| Bro| Sis| Chillin'| Hangin'| " +
                    "Kickin' it| Coolio|  Hola| Howdy-do| Hiya| Aloha| Salutations| What's cracking|" +
                    " Wassup| How's it hanging| Hottie| Homie| BFF| Fam| Squad| Lit)")) {
                informalCount++;
            }
        }
        double informalRatio = (double) informalCount / tokens.length;
        if (informalRatio > 0.1) {
            return false;
        }else
        {return true;}}

    /*@Override
    public void buttonReport(Publication pub) {
        pub.setReport(pub.getReport()+1);
    }*/


}

