package esprit.tn.Repository;

import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Category;
import esprit.tn.Entites.TypeA;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAnnonceRepository extends JpaRepository<Announcement, Integer> {
    //List<Announcement> findByid(Integer id);
    List<Announcement> findByCategory(Category category);
    List<Announcement> findByDescription(String description);
    List<Announcement> findByLocation(String location);
    List<Announcement> findByPrice(float price);
    List<Announcement> findByTypeA(TypeA typeA);

    @Query("select sum (sp.price) from Announcement ac join ac.sponsorings sp where ac.id = :id")
    float sumPrice(Integer id);
}
