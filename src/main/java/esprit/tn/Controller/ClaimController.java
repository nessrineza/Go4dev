package esprit.tn.Controller;

import esprit.tn.Repository.ClaimRepository;
import esprit.tn.Service.IClaimService;
import esprit.tn.Entites.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/claim")
public class ClaimController {
    private final IClaimService iClaimService;
    @Autowired
    private ClaimRepository claimRepository;
   /* @Autowired
    JavaMailSender emailSender;*/

    @PostMapping("/add")
    void addClaim(@RequestBody Claim claim) {
        List<String> dic = claimRepository.Dictionnaire();
            for (int i = 1; i <= dic.size(); i++) {
                if (claim.getSubject().toString().contains(dic.get(i - 1))) {
                    break;
                } else {
                    if (i == dic.size()) {
                        iClaimService.addClaim(claim);
                        SimpleMailMessage message = new SimpleMailMessage();

                        message.setTo(claim.getUsers().getEmail());
                        message.setSubject("Your complaint has been successfully added");
                        message.setText("Hello, Your complaint has been successfully added. Thank you for your feedback!");

                        // Send Message!
                       /* this.emailSender.send(message);*/

                        System.out.println( "Email Sent by me!");

                    }
                }

            }
      //  return iClaimService.addClaim(claim);
    }

    @PutMapping("/update")
    Claim updateClaim(@RequestBody Claim claim) {
        return iClaimService.addOrUpdateClaim(claim);
    }


    @GetMapping("/get/{id}")
    Claim getClaim(@PathVariable("id") Integer idClaim) {
        return iClaimService.retrieveClaim(idClaim);
    }

    @GetMapping("/all")
    List<Claim> getAllClaims() {
        return iClaimService.retrieveAllClaims();
    }


    @DeleteMapping("/delete/{id}")
    void deleteClaim(@PathVariable("id") Integer idClaim) {
        iClaimService.removeClaim(idClaim);
    }
}

