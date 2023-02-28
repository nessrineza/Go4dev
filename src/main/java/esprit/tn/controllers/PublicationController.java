package esprit.tn.controllers;

import esprit.tn.Entites.Publication;
import esprit.tn.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/publication")

public class PublicationController {
    @Autowired
    PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    // Annotation

    // Save operation
    @PostMapping("/add")
    public Publication savePublication(
            @RequestBody Publication publication)
    {

        return publicationService.addPublication(publication);
    }

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
}
