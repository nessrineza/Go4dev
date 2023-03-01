package esprit.tn.Service;

import esprit.tn.Entites.Partner;

import java.util.List;

public interface IPartnerService {
    List<Partner> retrieveAllPartners();

    Partner addPartner(Partner p);

    void deletePartner(int id);

    Partner updatePartner(Partner p);

    Partner retrievePartner(int id);
}
