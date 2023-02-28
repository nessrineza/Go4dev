package esprit.tn.Service;

import esprit.tn.Entites.Sponsoring;
import esprit.tn.Repository.ISponsoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Sponsoring")
public class SponsoringServiceImpl implements ISponsoringService {
    private final ISponsoringRepository sponsoringRepository;

    @Autowired
    public SponsoringServiceImpl(ISponsoringRepository sponsoringRepository) {
        this.sponsoringRepository = sponsoringRepository;

    }

    @Override
    public Sponsoring addSponsoring(Sponsoring s) {
        return sponsoringRepository.save(s);
    }

    @Override
    public Sponsoring updateSponsoring(Sponsoring s, Integer id) {
        s.setId(id);
        return sponsoringRepository.save(s);
    }

    @Override
    public void removeSponsoring(Integer id) {
        sponsoringRepository.deleteById(id);
    }
    @Override
    public Sponsoring retrieveSponsoring(Integer id) {
        return sponsoringRepository.findById(id).orElse(null);
    }
    @Override
    public List<Sponsoring> retrieveAllSponsorings() {

        return sponsoringRepository.findAll();
    }
}
