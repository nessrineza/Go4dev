package esprit.tn.Service;

import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.TypeA;

import java.util.List;

public interface IAnnonceService {
    Announcement addAnnonce(Announcement a);

    Announcement updateAnnonce(Announcement d, Integer id);

    void removeAnnonce(Integer id);

    Announcement retrieveAnnouncement(Integer id);

    List<Announcement> retrieveAllAnnouncements();

   // Announcement asignSponsoring(Integer idSponsoring, Integer idAnnonce);

    Announcement assignAnnonceToSponsoring(Integer idAnnonce, Integer IdSponsoring);

    List<Announcement> getByCategory(Category category);
    List<Announcement> getByTypeA(TypeA typeA);
    List<Announcement> getByLocation(String location);
    List<Announcement> getByDescription(String description);
    List<Announcement> getByPrice(float price);

    float calculateDiscountedPrice(Integer id, Integer discount);
}
