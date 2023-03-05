package esprit.tn.controllers;

import esprit.tn.Entites.EmailDetails;
import esprit.tn.Entites.Publication;
import esprit.tn.Entites.User;
import esprit.tn.services.EmailService;
import esprit.tn.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
@RestController
@RequestMapping("/publication")

public class PublicationController {
    @Autowired
    PublicationService publicationService;
    @Autowired
    EmailService emailService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    // Annotation

    // Save operation
    @PostMapping("/add")
    public ResponseEntity<String> savePublication(
            @RequestBody Publication publication)
    {

       publicationService.addPublication(publication);

        return ResponseEntity.ok("Input processed successfully.");}


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
    public Publication
    updatePublication(@RequestBody Publication publication)
    {

        return publicationService.updatePublication(publication);
    }

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
    @PostMapping("/signalAction")public void signalAction(){publicationService.signalAction();}
}
