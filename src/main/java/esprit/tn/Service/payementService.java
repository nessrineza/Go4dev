package esprit.tn.Service;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.Payement;

import java.util.List;

public interface payementService {
    public Payement AjouterPayement(Payement payement);
    public Payement UpdatePayement( Payement payement, Integer id);
    public void removePayement(Integer idPayement);
    public List<Payement> retrieveAllPayements();
    void assignAppointementToPayement(Integer idAppointement, Integer idPayement);
}
