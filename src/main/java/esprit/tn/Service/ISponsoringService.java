package esprit.tn.Service;

import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Sponsoring;

import java.util.List;

public interface ISponsoringService {
    Sponsoring addSponsoring(Sponsoring s);

    Sponsoring updateSponsoring(Sponsoring s, Integer id);

    void removeSponsoring(Integer id);

    Sponsoring retrieveSponsoring(Integer id);

    List<Sponsoring> retrieveAllSponsorings();
}
