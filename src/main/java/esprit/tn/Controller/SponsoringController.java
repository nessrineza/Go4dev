package esprit.tn.Controller;

import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Sponsoring;
import esprit.tn.Service.AnnonceServiceImpl;
import esprit.tn.Service.SponsoringServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/sponsoring")
public class SponsoringController {
    @Autowired
    SponsoringServiceImpl sponsoringServiceImpl;

    @PostMapping("/addSponsoring")
    public Sponsoring addSponsoring(@RequestBody Sponsoring s)
    {
        return sponsoringServiceImpl.addSponsoring(s);
    }
    @PutMapping("/updateAnnonce/{id}")
    Sponsoring updateSponsoring(@RequestBody Sponsoring s,@PathVariable("id") Integer id)
    {
        return sponsoringServiceImpl.updateSponsoring(s,id);
    }
    @DeleteMapping("/deleteSponsoring/{id}")
    void removeSponsoring(@PathVariable ("id") Integer id){
        sponsoringServiceImpl.removeSponsoring(id);
    }
    @GetMapping("/getSponsoring/{id}")
    Sponsoring retrieveSponsoring(@PathVariable("id") Integer id)

    {
        return sponsoringServiceImpl.retrieveSponsoring(id);
    }
    @GetMapping("/allSponsoring")
    List<Sponsoring> retrieveAllSponsorings()
    {
        return sponsoringServiceImpl.retrieveAllSponsorings();
    }


}
