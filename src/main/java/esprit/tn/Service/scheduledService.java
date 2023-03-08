package esprit.tn.Service;

import esprit.tn.Entites.Appointment;
import esprit.tn.repository.appointementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class scheduledService {

    @Autowired
   appointementRepository appointementRepository;
    @Autowired
    EmailSender emailSender   ;

    @Scheduled(cron = "* 0 14 * * ?")
    public void  Reminder(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Appointment> appointments = appointementRepository.findAppointmentsByAppointmentDate(tomorrow);
        for (Appointment appointment: appointments){
            emailSender.sendSimpleEmail("nesrine.zamni@esprit.tn", "You have an appointement Tomorrow",  "Reminder Tomorrow");
        }
    }
}
