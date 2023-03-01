package esprit.tn.Service;

import esprit.tn.Repository.ClaimRepository;
import esprit.tn.Entites.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService implements IClaimService {
    private  final ClaimRepository claimRepository;

    @Override
    public List<Claim> retrieveAllClaims() {
        return claimRepository.findAll();    }

    @Override
    public Claim addOrUpdateClaim(Claim c) {
        return  claimRepository.save(c);
    }

    @Override
    public Claim addClaim(Claim c) {
        return  claimRepository.save(c);
    }

    @Override
    public Claim retrieveClaim(Integer idClaim) {
        return  claimRepository.findById(idClaim).orElse(null);
    }

    @Override
    public void removeClaim(Integer idClaim) {
        claimRepository.deleteById(idClaim);
    }
}
