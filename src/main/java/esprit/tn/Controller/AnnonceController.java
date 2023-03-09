package esprit.tn.Controller;

import com.itextpdf.text.DocumentException;
import esprit.tn.Dto.AnnonceDto;
import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.TypeA;
import esprit.tn.Entites.User;
import esprit.tn.Service.AnnonceServiceImpl;
import esprit.tn.Service.MailingServiceImpl;
import esprit.tn.pdf.PDFGenerator;
import esprit.tn.services.IUserService;
import esprit.tn.services.UserService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
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


    UserService userService;
    AnnonceServiceImpl annonceServiceImpl;

    MailingServiceImpl mailingServiceImpl;
    esprit.tn.Repository.IAnnonceRepository annonceRepository;


    @PostMapping("/addAnnonce")
    public void addAnnonce(@RequestBody Announcement a )
    {

        User user= userService.findUserbyId(a.getUsId());
        List<Announcement> announcementsUser =annonceRepository.findAnnouncementByUsId(user.getId());

        int nombreannonce=0;
        for (Announcement announcement:announcementsUser
             ) {
            if(!announcement.isVerified()){
                nombreannonce++;
            }
        }

        if(nombreannonce%5 ==0&& user.getCodePromo()==null){
            String randomCode = RandomString.make(6);
            user.setCodePromo(randomCode);
            userService.updateUser(user);
            mailingServiceImpl.sendSimpleEmail(user.getEmail(),"Votre annonce est ajoutée avec succées! Felicitation vous gagnez un Code promo remise : "+randomCode,"Ajout d'une annonce");
            annonceServiceImpl.addAnnonce(a,a.getUsId());
        }else {
            annonceServiceImpl.addAnnonce(a,a.getUsId());
            mailingServiceImpl.sendSimpleEmail(user.getEmail(), "Votre annonce est ajoutée avec succées!", "Ajout d'une annonce");
        }
        System.out.println(nombreannonce +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");


    }
    @PutMapping("/updateAnnonce/{id}")
    Announcement updateAnnonce(@RequestBody Announcement a,@PathVariable("id") Integer id)
    {
        User user= userService.findUserbyId(a.getUsId());
        mailingServiceImpl.sendSimpleEmail(user.getEmail(),"Modification d'une annonce","Votre annonce est modifiée avec succées!");

        return annonceServiceImpl.updateAnnonce(a,id);
    }
    @DeleteMapping("/deleteAnnonce/{id}")
    void removeAnnonce(@PathVariable ("id") Integer id){
        Announcement a= annonceServiceImpl.retrieveAnnouncement(id);
        User user= userService.findUserbyId(a.getUsId());
        annonceServiceImpl.removeAnnonce(id);
        mailingServiceImpl.sendSimpleEmail(user.getEmail(),"Suppression d'une annonce","Votre annonce est suppriméé!");
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
        Announcement announcement= annonceRepository.findById(idAnnonce).orElse(null);
        return annonceServiceImpl.assignAnnonceToSponsoring(idAnnonce,IdSponsoring, 10);
     }
    @GetMapping("/{id}/{discount}")
    public float getDiscountedPrice(@PathVariable("id") Integer id,
                                     @PathVariable("discount") Integer discount)
    {
        float discountedPriceTotal = annonceServiceImpl.calculateDiscountedPrice(id, discount);
        return discountedPriceTotal;
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
        List<AnnonceDto> annonceDtos = annonceServiceImpl.retrieveAll();
        PDFGenerator generetorUser = new PDFGenerator();
        generetorUser.setAnnonceDtos(annonceDtos);
        generetorUser.generate(response);
    }
    @PostMapping("/verifyAnnouncement/{id}")
    void verifyAnnoncement(@PathVariable Integer id){
        annonceServiceImpl.verifyAnnouncementById(id);
    }

}





