package esprit.tn.Service;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.Payement;
import esprit.tn.Repository.appointementRepository;
import esprit.tn.Repository.payementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
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
      List<Appointment> appointmentsWithSameDate = appointementRepository.findAppointmentsByCalendar(appointment.getCalendar());
        if (!appointmentsWithSameDate.isEmpty() ) {
            throw new RuntimeException("There is already an appointment scheduled on this date.");
        }
        /* if (this.verify (appointment.getCalendar()) ) {
            throw new RuntimeException("This is a week-end");
        }*/

        // Save the new appointment
        Appointment A = this.appointementRepository.save(appointment);


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
    public boolean isWeekend(final Date d)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
    }
    public boolean verify (final Date date )
    {
        boolean state = true ;
        if (this.isWeekend(date)){state = false;}
        return state;
    }
}
