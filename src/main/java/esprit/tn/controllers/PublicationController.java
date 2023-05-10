package esprit.tn.controllers;

import esprit.tn.Entites.Comment;
import esprit.tn.Entites.EmailDetails;
import esprit.tn.Entites.Publication;
import esprit.tn.Entites.User;
import esprit.tn.repository.PublicationRepository;
import esprit.tn.services.BadWordFilter;
import esprit.tn.services.EmailService;
import esprit.tn.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/publication")

public class PublicationController {
    @Autowired
    PublicationService publicationService;
    @Autowired
    EmailService emailService;
  @Autowired
    PublicationRepository publicationRepository;
    BadWordFilter badWordFilter;


    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    // Annotation

    // Save operation
    @PostMapping("/add/{id}")
    public Publication savePublication(
            @RequestBody Publication publication, @PathVariable("id")Long id)
    {
       publicationService.addPublication(publication);
    Publication pub= publicationRepository.findPublicationByDate(publication.getDate());
    pub.setDescription( badWordFilter.getCensoredText(pub.getDescription()));

    publicationService.assignUserToPub(pub.getId(), id);
return publicationRepository.save(pub);}


    // Read operation
    @GetMapping("/publications")
    public List<Publication> retrieveAllPublications()
    {

        return publicationService.retrieveAllPublications();
    }
    @GetMapping("/retrieve/{id}")
    public Publication getPublication(@PathVariable("id") Integer publicationId)
    {

        return publicationService.retrievePublication(publicationId);
    }


    // Update operation
    @PutMapping("/update")
    public ResponseEntity<String>
    updatePublication(@RequestBody Publication publication)
    {

        if(publicationService.isFormal(publication.getSubject())){
        publicationService.updatePublication(publication);



        return ResponseEntity.ok("Publication updated successfully.");}
else{
        return ResponseEntity.ok("Your publication will not be updated because its subject context wasn't formal") ;}}



    // Delete operation
    @DeleteMapping("/delete/{id}")
    public String deletePublicationById(@PathVariable("id") Integer publicationId)
    {

        publicationService.removePublicationById(publicationId);
        return "Deleted Successfully";
    }
    @Transactional

    @PutMapping("/assignUser/{id1}/{id2}")
    public List<Publication> assignUserToPublication
            (@PathVariable("id1")Integer pubId,@PathVariable("id2")Long userId)
    {return publicationService.assignUserToPub(pubId,userId);
    }
    @Scheduled(fixedRate = 60000)
    @PostMapping("/signalAction")public void signalAction(){publicationService.signalAction();}

    @GetMapping("/PublicationsSortedFav")
    public List<Publication> retrievePublicationsSortedDate()
    {

        return publicationRepository.publicationSortedByFavoris();
    }



   @PutMapping("/likes/{id}")
    public void upLikes(@PathVariable("id") Integer id)
    {Publication c=publicationRepository.findPublicationById(id);
        c.setFavoris(c.getFavoris()+1);


        publicationService.updatePublication(c);
    }
    @PutMapping("/report/{id}")
    public void upReport(@PathVariable("id") Integer id)
    {Publication c=publicationRepository.findPublicationById(id);
        c.setReport(c.getReport()+1);


        publicationService.updatePublication(c);
    }






}
