package esprit.tn.Service;

import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.Sponsoring;
import esprit.tn.Entites.TypeA;
import esprit.tn.Repository.IAnnonceRepository;
import esprit.tn.Repository.ISponsoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("Annonce")

public class AnnonceServiceImpl implements IAnnonceService{
    private static final int DISCOUNT_THRESHOLD=5;
    private static final int DISCOUNT_PERCENTAGE=10;

    private final IAnnonceRepository annonceRepository;
    private final ISponsoringRepository sponsoringRepository;
    @Autowired
    public AnnonceServiceImpl(IAnnonceRepository annonceRepository, ISponsoringRepository sponsoringRepository) {

        this.annonceRepository = annonceRepository;
        this.sponsoringRepository = sponsoringRepository;
    }
    @Override
    public Announcement addAnnonce(Announcement a)
    {
        return annonceRepository.save(a);
    }
    @Override
    public Announcement updateAnnonce(Announcement d,Integer id)
    {
        d.setId(id);
        return annonceRepository.save(d);
    }
    @Override
    public void removeAnnonce(Integer id) {
        annonceRepository.deleteById(id);

    }
    @Override
    public Announcement retrieveAnnouncement(Integer id) {
        return annonceRepository.findById(id).orElse(null);
    }
    @Override
    public List<Announcement> retrieveAllAnnouncements() {
        // List<Announcement> announcements = new ArrayList<>();
        // annonceRepository.findAll().forEach(announcements::add);
        // return announcements;
        // List<Announcement> announcements = new ArrayList<>();
        // annonceRepository.findAll().forEach(announcements::add);
        return annonceRepository.findAll();
    }

    @Override
    @Transactional
    public Announcement assignAnnonceToSponsoring(Integer idAnnonce, Integer IdSponsoring) {
        List<Sponsoring> sponsorings = new ArrayList<>();
        Announcement announcement= annonceRepository.findById(idAnnonce).orElse(null);
        Sponsoring sponsoring=sponsoringRepository.findById(IdSponsoring).orElse(null);

            announcement.getSponsorings().add(sponsoring);
            annonceRepository.save(announcement);


        return announcement;
    }
    /*@Override
    public Announcement asignSponsoring(Integer idSponsoring, Integer idAnnonce)
    {
        Announcement a= annonceRepository.findById(idAnnonce).orElse(null);
        Sponsoring s= sponsoringRepository.findById(idSponsoring).orElse(null) ;
        if (a.getSponsorings() == null){
            List<Sponsoring> sponsorings = new ArrayList<Sponsoring>();
            sponsorings.add(s);
            a.setSponsorings(sponsorings);
        }
        else {
            a.getSponsorings().add(s);
        }
        return annonceRepository.save(a);
    }*/


   /* @Override
    public Announcement assignAnnonceToSponsoring(Integer idAnnonce, Integer IdSponsoring)
    {
        Announcement annonce = annonceRepository.findById(idAnnonce).orElse(null);
        Sponsoring sponsoring = sponsoringRepository.findById(IdSponsoring).orElse(null);

        if (annonce.getSponsorings() == null){ //creation de collection
            //System.out.println("Null");
            List<Sponsoring> sponsorings = new ArrayList<Sponsoring>();
            sponsorings.add(sponsoring);
            annonce.setSponsorings(sponsorings);
        }
        else {
            annonce.getSponsorings().add(sponsoring);
        }
        //etudiant.setEquipes(etudiant.getEquipes());
        return annonceRepository.save(annonce);
    }
*/
    @Override
    public List<Announcement> getByCategory(Category category) {
        List<Announcement> announcements = new ArrayList<>();
        annonceRepository.findByCategory(category).forEach(announcements::add);
        return announcements;
    }

    @Override
    public List<Announcement> getByTypeA(TypeA typeA) {
        List<Announcement> announcements = new ArrayList<>();
        annonceRepository.findByTypeA(typeA).forEach(announcements::add);
        return announcements;
    }

    @Override
    public List<Announcement> getByLocation(String location) {
        List<Announcement> announcements = new ArrayList<>();
        annonceRepository.findByLocation(location).forEach(announcements::add);
        return announcements;
    }

    @Override
    public List<Announcement> getByDescription(String description) {
        List<Announcement> announcements = new ArrayList<>();
        annonceRepository.findByDescription(description).forEach(announcements::add);
        return announcements;
    }

    @Override
    public List<Announcement> getByPrice(float price) {
        List<Announcement> announcements = new ArrayList<>();
        annonceRepository.findByPrice(price).forEach(announcements::add);
        return announcements;
    }
    @Override
     public float calculateDiscountedPrice(Integer id, Integer discount) {
        Announcement announcement= annonceRepository.findById(id).orElse(null);
            float discountAmount = announcement.getPrice() * discount / 100;
            float discountedPrice = announcement.getPrice() - discountAmount;
            return discountedPrice;
        }

}