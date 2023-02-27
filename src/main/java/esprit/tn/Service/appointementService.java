package esprit.tn.Service;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.Payement;

import java.time.ZonedDateTime;
import java.util.List;

public interface appointementService {
    public void AjouterAppointement(Appointment appointment);
    public Appointment UpdateAppointement( Appointment appointment, Integer id);
    public void removeAppointement(Integer idApp);
    public List<Appointment> retrieveAllAppointement();


}
