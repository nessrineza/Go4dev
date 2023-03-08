package esprit.tn.Service;

import esprit.tn.Dto.AnnonceDto;
import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.Sponsoring;
import esprit.tn.Entites.TypeA;
import esprit.tn.Mappers.AnnonceMapper;
import esprit.tn.Repository.IAnnonceRepository;
import esprit.tn.Repository.ISponsoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("Annonce")

public class AnnonceServiceImpl implements IAnnonceService{
    private static final int DISCOUNT_THRESHOLD=5;
    private final IAnnonceRepository annonceRepository;
    private final ISponsoringRepository sponsoringRepository;
    @Autowired
    public AnnonceServiceImpl(IAnnonceRepository annonceRepository, ISponsoringRepository sponsoringRepository) {

        this.annonceRepository = annonceRepository;
        this.sponsoringRepository = sponsoringRepository;
    }
    @Override
    public Announcement addAnnonce(Announcement a,Long id)
    {
        a.setUsId(id);
        a.setVerified(true);
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
    public Announcement assignAnnonceToSponsoring(Integer idAnnonce, Integer IdSponsoring , float dis) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        List<Sponsoring> sponsorings = new ArrayList<>();
        Announcement announcement= annonceRepository.findById(idAnnonce).orElse(null);
        Sponsoring sponsoring=sponsoringRepository.findById(IdSponsoring).orElse(null);

            announcement.getSponsorings().add(sponsoring);
            annonceRepository.save(announcement);

            float priceTotalSpon = annonceRepository.sumPrice(idAnnonce);
            float priceTotal = announcement.getPriceA()+priceTotalSpon;

   System.out.println(   announcement.getDiscount());

        float priceTotalDiscount = calculateDiscountedPrice(idAnnonce, dis );


            announcement.setPriceTotalSpon(priceTotalSpon);
            announcement.setPriceTotal(priceTotal);
            announcement.setPriceTotalDiscount(priceTotalDiscount);


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
    public List<Announcement> getByPrice(float priceA) {
        List<Announcement> announcements = new ArrayList<>();
        annonceRepository.findByPriceA(priceA).forEach(announcements::add);
        return announcements;
    }
    @Override
     public float calculateDiscountedPrice(Integer id, float discount) {  //annonceRepository
        Announcement announcement= annonceRepository.findById(id).orElse(null);
            float discountAmount = announcement.getPriceTotal() * discount / 100;
            float discountedPrice = announcement.getPriceTotal() - discountAmount;
            announcement.setPriceTotalDiscount(discountedPrice);
            return discountedPrice;
        }



    @Override
    public void verifyAnnouncementById(Integer id) {
        Announcement a =annonceRepository.findById(id).orElse(null);
        a.setVerified(false);
        annonceRepository.save(a);
    }

    @Override
        public AnnonceDto add(AnnonceDto annonceDto){
            Announcement announcement = annonceRepository.save(AnnonceMapper.mapToEntity(annonceDto));
        return AnnonceMapper.mapToDto(announcement);
        }
        @Override
        public List<AnnonceDto> retrieveAll(){
        return annonceRepository.findAll()
                .stream()
                .map(announcement -> AnnonceMapper.mapToDto(announcement))
                .collect(Collectors.toList());
        }

}
