package esprit.tn.Repository;

import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.TypeA;
import esprit.tn.Entites.User;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnnonceRepository extends JpaRepository<Announcement, Integer> {
    //List<Announcement> findByid(Integer id);
    List<Announcement> findByCategory(Category category);
    List<Announcement> findByDescription(String description);
    List<Announcement> findByLocation(String location);
    List<Announcement> findByPriceA(float priceA);
    List<Announcement> findByTypeA(TypeA typeA);

    int countAnnouncementByUser(User user);
    List<Announcement> findAnnouncementByUsId(Long id);

    @Query("select sum (sp.priceS) from Announcement ac join ac.sponsorings sp where ac.id = :id")
    float sumPrice(Integer id);
}
