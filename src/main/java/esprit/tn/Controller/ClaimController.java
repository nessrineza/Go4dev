package esprit.tn.Controller;

import esprit.tn.Service.IClaimService;
import esprit.tn.Entites.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/claim")
public class ClaimController {
    private final IClaimService iClaimService;

    @PostMapping("/add")
    Claim addClaim(@RequestBody Claim claim) {
        return iClaimService.addClaim(claim);
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

