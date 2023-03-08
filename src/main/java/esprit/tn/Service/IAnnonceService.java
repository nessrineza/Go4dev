package esprit.tn.Service;

import esprit.tn.Dto.AnnonceDto;
import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.TypeA;

import javax.transaction.Transactional;
import java.util.List;

public interface IAnnonceService {
    Announcement addAnnonce(Announcement a ,Long userId);

    Announcement updateAnnonce(Announcement d, Integer id);

    void removeAnnonce(Integer id);

    Announcement retrieveAnnouncement(Integer id);

    List<Announcement> retrieveAllAnnouncements();

   // Announcement asignSponsoring(Integer idSponsoring, Integer idAnnonce);

   // Announcement assignAnnonceToSponsoring(Integer idAnnonce, Integer IdSponsoring);


    Announcement assignAnnonceToSponsoring(Integer idAnnonce, Integer IdSponsoring, float disc);

    List<Announcement> getByCategory(Category category);
    List<Announcement> getByTypeA(TypeA typeA);
    List<Announcement> getByLocation(String location);
    List<Announcement> getByDescription(String description);
    List<Announcement> getByPrice(float price);

    float calculateDiscountedPrice(Integer id, float discount);
    void verifyAnnouncementById(Integer id);

    AnnonceDto add(AnnonceDto annonceDto);

    List<AnnonceDto> retrieveAll();
}
