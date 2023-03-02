package esprit.tn.services;

import esprit.tn.Entites.Publication;
import esprit.tn.Entites.User;
import esprit.tn.repository.PublicationRepository;
import esprit.tn.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserRepository userRepository;


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
    public Publication retrievePublication(Integer idPublication) {
        return publicationRepository.findById(idPublication).orElse(null);
    }



    @Override
    public List<Publication> retrieveAllPublications() {
        List<Publication> publications = new ArrayList<>();
        publicationRepository.findAll().forEach(publications::add);

        return publications;
    }
    @Override
    public void assignUserToPub(Integer idPub, Long idUser) {
        Publication p= publicationRepository.findById(idPub).orElse(null);
        User u=userRepository.findById(idUser).orElse(null);

            List<User> users=new ArrayList<>();
            users.add(u);
            p.setUsers( users);
        userRepository.save(u);

        publicationRepository.save(p);

    }
}
