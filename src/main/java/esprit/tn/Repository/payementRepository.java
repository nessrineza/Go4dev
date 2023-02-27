package esprit.tn.Repository;

import esprit.tn.Entites.Payement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface payementRepository extends JpaRepository<Payement,Integer> {
}
