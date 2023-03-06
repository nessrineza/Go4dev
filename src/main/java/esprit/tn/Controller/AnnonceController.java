package esprit.tn.Controller;

import com.itextpdf.text.DocumentException;
import esprit.tn.Dto.AnnonceDto;
import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.TypeA;
import esprit.tn.Service.AnnonceServiceImpl;
import esprit.tn.Service.MailingServiceImpl;
import esprit.tn.pdf.PDFGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/annonce")
public class AnnonceController {

    AnnonceServiceImpl annonceServiceImpl;

    MailingServiceImpl mailingServiceImpl;


    @PostMapping("/addAnnonce")
    public void addAnnonce(@RequestBody Announcement a)
    {
        annonceServiceImpl.addAnnonce(a);
        mailingServiceImpl.sendSimpleEmail("broumaima@yahoo.com","Ajout d'une annonce","Votre annonce est ajoutée avec succées!");



    }
    @PutMapping("/updateAnnonce/{id}")
    Announcement updateAnnonce(@RequestBody Announcement a,@PathVariable("id") Integer id)
    {

        mailingServiceImpl.sendSimpleEmail("broumaima@yahoo.com","Modification d'une annonce","Votre annonce est modifiée avec succées!");

        return annonceServiceImpl.updateAnnonce(a,id);
    }
    @DeleteMapping("/deleteAnnonce/{id}")
    void removeAnnonce(@PathVariable ("id") Integer id){
        annonceServiceImpl.removeAnnonce(id);
        mailingServiceImpl.sendSimpleEmail("broumaima@yahoo.com","Suppression d'une annonce","Votre annonce est suppriméé!");
    }
    @GetMapping("/getAnnouncement/{id}")
    Announcement retrieveAnnouncement(@PathVariable("id") Integer id)

    {
        return annonceServiceImpl.retrieveAnnouncement(id);
    }

    @GetMapping("/allAnnouncement")
    List<Announcement> retrieveAllAnnouncements()
    {
        return annonceServiceImpl.retrieveAllAnnouncements();
    }
   /* @PutMapping("affecterSponsoring/{idAnnonce}/{idSponsoring}")
    Announcement affecterSponsoring(@PathVariable("idAnnonce")Integer idAnnonce,@PathVariable("idSponsoring") Integer idSponsoring)
    {
        return annonceServiceImpl.assignAnnonceToSponsoring(idAnnonce, idSponsoring);
    }*/
    @GetMapping("/allCategory/{category}")
    List<Announcement> getAnnouncementByCategory(@PathVariable Category category)
    {
        return annonceServiceImpl.getByCategory(category);
    }
    @GetMapping("/allTypeA/{typeA}")
    List<Announcement> getAnnouncementByTypeA(@PathVariable TypeA typeA)
    {
        return annonceServiceImpl.getByTypeA(typeA);
    }
    @GetMapping("/allPrice/{price}")
    List<Announcement> getAnnouncementByPrice(@PathVariable float price)
    {
        return annonceServiceImpl.getByPrice(price);
    }
    @GetMapping("/allLocation/{location}")
    List<Announcement> getAnnouncementByLocation(@PathVariable String location)
    {
        return annonceServiceImpl.getByLocation(location);
    }
    @GetMapping("/allDescription/{description}")
    List<Announcement> getAnnouncementByDescription(@PathVariable String description)
    {
        return annonceServiceImpl.getByDescription(description);
    }

     @PutMapping("assignAnnonceToSpon/{idAn}/{idSpo}")
     public Announcement assignAnnonceToSponsoring(@PathVariable("idAn") Integer idAnnonce,@PathVariable("idSpo") Integer IdSponsoring){
        return annonceServiceImpl.assignAnnonceToSponsoring(idAnnonce,IdSponsoring);
     }
    @GetMapping("/{id}/{discount}")
    public float getDiscountedPrice(@PathVariable("id") Integer id,
                                     @PathVariable("discount") Integer discount)
    {
        float discountedPriceA = annonceServiceImpl.calculateDiscountedPrice(id, discount);
        return discountedPriceA;
    }
    @PostMapping("/addDto")
        public AnnonceDto add(@RequestBody AnnonceDto entity) {
        return annonceServiceImpl.add(entity);
        }
    @GetMapping("/retrieveAllDto")
   public List<AnnonceDto> retrieveAll(){
        return annonceServiceImpl.retrieveAll();
    }

    @GetMapping("/pdf/annonce")
    public void generator(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setHeader(headerkey, headervalue);
        List<Announcement> announcements = annonceServiceImpl.retrieveAllAnnouncements();
        PDFGenerator generetorUser = new PDFGenerator();
        generetorUser.setAnnouncements(announcements);
        generetorUser.generate(response);
    }
}





