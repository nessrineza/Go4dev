package esprit.tn.repository;

import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Sponsoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISponsoringRepository extends JpaRepository<Sponsoring, Integer> {
    //List<Sponsoring> findByid(Integer id);


}
