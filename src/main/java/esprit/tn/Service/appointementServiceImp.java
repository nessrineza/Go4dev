package esprit.tn.Service;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.Payement;
import esprit.tn.Repository.appointementRepository;
import esprit.tn.Repository.payementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        if ( appointementRepository.isInBetweenTwoTimeAndDate(appointment.getAppointmentStartTime(),appointment.getAppointmentEndTime())  ) {
            System.out.println( "There is already an appointment scheduled on this date.");
            return;
        }
        if ( this.isWeekend(appointment.getAppointmentDate())) {
            System.out.println( "this is weekend");
            return;
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
}
