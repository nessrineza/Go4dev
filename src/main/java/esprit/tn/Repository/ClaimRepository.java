package esprit.tn.repository;
import esprit.tn.Entites.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer> {

    @Query(value="select mots from dictionnaire", nativeQuery=true)
    List<String> Dictionnaire();
}
