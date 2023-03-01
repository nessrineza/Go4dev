package esprit.tn.Service;

import esprit.tn.Entites.Partner;
import esprit.tn.Repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
@RequiredArgsConstructor
public class PartnerService implements IPartnerService {



    private final PartnerRepository partnerRepository;
    @Override
    public List<Partner> retrieveAllPartners() {
        return (List<Partner>) partnerRepository.findAll();
    }

    @Override
    public Partner addPartner(Partner u) {
        partnerRepository.save(u);
        return u;
    }

    @Override
    public void deletePartner (int id) {
        partnerRepository.deleteById(id);
    }

    @Override
    public Partner updatePartner(Partner u) {
        partnerRepository.save(u);
        return u;
    }

    @Override
    public Partner retrievePartner(int id) {
        Partner u = partnerRepository.findById(id).get();
        return u;
    }}
