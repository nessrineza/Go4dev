package esprit.tn.Service;

import esprit.tn.Entites.Claim;

import java.util.List;

public interface IClaimService {
    public Claim addOrUpdateClaim(Claim c);
    public Claim addClaim(Claim c);
    public Claim retrieveClaim(Integer idClaim);
    public List<Claim> retrieveAllClaims();
    public void removeClaim(Integer idClaim);
}
