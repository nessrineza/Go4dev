package esprit.tn.Service;

import esprit.tn.Entites.User;
import esprit.tn.repository.ClaimRepository;
import esprit.tn.repository.UserRepository;
import esprit.tn.Entites.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService implements IClaimService {
    @Autowired
     ClaimRepository claimRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Claim> retrieveAllClaims() {
        return claimRepository.findAll();    }

    @Override
    public Claim addOrUpdateClaim(Claim c) {
        return  claimRepository.save(c);
    }

    @Override
    public Claim addClaimtoUser(Claim c , long idUser){
        User user = userRepository.findById(idUser).orElse(null);
        c.setUsers(user);
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
