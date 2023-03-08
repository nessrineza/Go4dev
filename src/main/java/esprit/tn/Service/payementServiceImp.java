package esprit.tn.Service;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.Payement;
import esprit.tn.repository.appointementRepository;
import esprit.tn.repository.payementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class payementServiceImp implements payementService{
    @Autowired

    payementRepository payementRepository;
    @Autowired
    appointementRepository appointementRepository;
    @Override
    public Payement AjouterPayement(Payement payement) {
        Payement P = this.payementRepository.save(payement);
        return P;
    }

    @Override
    public Payement UpdatePayement(Payement payement, Integer id) {
        payement.setId(id);
        return payementRepository.save(payement);

    }
    @Override
    public void removePayement(Integer idPayement) {
        payementRepository.deleteById(idPayement);
    }
    @Override
    public List<Payement> retrieveAllPayements() {

        List<Payement> payements =new ArrayList<>();
        payementRepository.findAll().forEach(payements::add);
        return payements;
    }

    @Override
    public void assignAppointementToPayement(Integer idAppointement, Integer idPayement) {
        Payement P = payementRepository.findById(idPayement).orElse(null);
        Appointment A = appointementRepository.findById(idAppointement).orElse(null);
        P.setAppointment(A);
        payementRepository.save(P);
    }
}
