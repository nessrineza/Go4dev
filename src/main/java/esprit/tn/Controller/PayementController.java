package esprit.tn.Controller;

import esprit.tn.Entites.Payement;
import esprit.tn.Service.payementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Payement")
public class PayementController {
    @Autowired
    payementServiceImp payementServiceImp;
    @PostMapping("/Add")
    public Payement addPayement(@RequestBody Payement payement) {

        return   this.payementServiceImp.AjouterPayement(payement);
    }
    @PutMapping("/update/{id}")
    public Payement updatePayment(@RequestBody Payement p,@PathVariable("id") Integer id)
    {
        return payementServiceImp.UpdatePayement(p,id);

    }
    @DeleteMapping("/delete/{id}")
    void deletePayement(@PathVariable("id") Integer id){
        payementServiceImp.removePayement(id);
    }
    @GetMapping("/all")
    List<Payement> getAllPayements(){
        return payementServiceImp.retrieveAllPayements();
    }
    @PutMapping("/Affectation/{idApp}/{idPay}")
    void affecter(@PathVariable("idApp")Integer idApp,@PathVariable("idPay")Integer idPay ){
         payementServiceImp.assignAppointementToPayement(idApp,idPay);

    }

}
