package esprit.tn.Service;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.AppointmentStatus;
import esprit.tn.Entites.Payement;
import esprit.tn.repository.appointementRepository;
import esprit.tn.repository.payementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class appointementServiceImp implements appointementService{
    @Autowired
    appointementRepository appointementRepository;
    @Autowired
    payementRepository payementRepository;
    @Autowired
    EmailSender EmailSender;
    @Override
    public void AjouterAppointement(Appointment appointment) {
        // Check if there is an existing appointment with the same date



        // Save the new appointment

        if ( appointementRepository.isInBetweenTwoTimeAndDate(appointment.getAppointmentDate()  ,  appointment.getAppointmentStartTime(),appointment.getAppointmentEndTime())  ) {
            appointment.setAppointmentStatus(AppointmentStatus.Booked);
            sendMailSuggestionForUser(appointment.getAppointmentDate());
            System.out.println( "There is already an appointment scheduled on this date.");

        }
        else if ( this.isWeekend(appointment.getAppointmentDate())) {
            appointment.setAppointmentStatus(AppointmentStatus.Booked);
            System.out.println( "this is weekend");
        }
        else {   appointment.setAppointmentStatus(AppointmentStatus.Available);      this.appointementRepository.save(appointment);

            try {
                EmailSender.sendEmailWithAttachment("nesrine.zamni@esprit.tn","this is your appointement","add an appointement",
                        "C:\\photo.pdf");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }

        // Save the new appointment

        this.appointementRepository.save(appointment);


    }






    @Override
    public Appointment UpdateAppointement(Appointment appointment, Integer id) {
        appointment.setId(id);
        return appointementRepository.save(appointment);
    }

    @Override
    public void removeAppointement(Integer idApp) {
        appointementRepository.deleteById(idApp);

    }

    @Override
    public List<Appointment> retrieveAllAppointement() {

        List<Appointment> appointments = new ArrayList<>();
        appointementRepository.findAll().forEach(appointments::add);
        return appointments;
    }
    public  boolean isWeekend(LocalDate localDate) {
        return (localDate.get(ChronoField.DAY_OF_WEEK) == 6)
                || (localDate.get(ChronoField.DAY_OF_WEEK) == 7);
    }
    public  boolean sendMailSuggestionForUser(LocalDate localDate) {
        LocalDate localDateNow = LocalDate.now().plusDays(1);
        LocalTime startWorkTime = LocalTime.of(8,0);
        LocalTime endWorkTime = LocalTime.of(16,0);
        boolean NotReturn = true;
        while (localDateNow.isBefore(localDate) && NotReturn )
        {
            LocalTime startSearchTime = startWorkTime;
            LocalTime endSearchTime = startWorkTime.plusHours(1);
            while   (!startWorkTime.isAfter(endWorkTime) && NotReturn)
            {

                if (
                        !    appointementRepository. isInBetweenTwoTimeAndDate(localDateNow ,
                                startSearchTime,
                                endSearchTime )
                )
                {
                    System.out.println("We founded  !!!!!!!!!!!!!!!!!");
                    System.out.println(localDateNow.toString() +" "+  startSearchTime.toString()  +" "+  endSearchTime.toString() );
                    try{

                        String content = "Dear [[name]]"
                                +"AnotherAppointementRequest  "
                                +localDateNow.toString() + " "
                                +startSearchTime.toString()  +" "
                                +endSearchTime.toString()
                                + "  Please click ok to confirm your Appointement: "
                                + " href=\"[[URL]]\"/"
                                + "Thank you"
                                + "echeriniEkrini.";
                        content = content.replace("[[name]]", "Nesrine");
                        String verifyURL = "http://localhost:8089/SpringMVC/Appointement/Add" ;
                        content = content.replace("[[URL]]", verifyURL);
                        EmailSender.sendSimpleEmail("nesrine.zamni@esprit.tn",content
                                ,"We founded ");}
                    catch (Exception e){
                        System.out.println( "mail failed");

                    }







                    NotReturn= false;
                }

                startSearchTime = endSearchTime;
                endSearchTime = endSearchTime.plusHours(1);
                startWorkTime.plusHours(1);

            }
            localDateNow.plusDays(1);
        }
        return true;
    }
}
