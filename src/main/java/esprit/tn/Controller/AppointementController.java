package esprit.tn.Controller;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.Payement;
import esprit.tn.Service.EmailSender;
import esprit.tn.Service.appointementServiceImp;
import esprit.tn.Service.payementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Appointement")
public class AppointementController {
    @Autowired
   appointementServiceImp appointementServiceImp;
    @Autowired
    EmailSender EmailSender;
    @PostMapping("/Add")
    public void addAppointement(@RequestBody Appointment appointment) throws MessagingException {

           this.appointementServiceImp.AjouterAppointement(appointment);
         EmailSender.sendEmailWithAttachment("nesrine.zamni@esprit.tn","this is your appointement","add an appointement",
                 "C:\\photo.pdf");

    }
    @PutMapping("/update/{id}")
    public Appointment updateAppointement(@RequestBody Appointment A,@PathVariable("id") Integer id)
    {
        return appointementServiceImp.UpdateAppointement(A,id);

    }
    @DeleteMapping("/delete/{id}")
    void deleteAppointement(@PathVariable("id") Integer id){
        appointementServiceImp.removeAppointement(id);
    }
    @GetMapping("/all")
    List<Appointment> getAllAppointements()
    {
        return appointementServiceImp.retrieveAllAppointement();
    }

}
