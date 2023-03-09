package esprit.tn.repository;

import esprit.tn.Entites.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DeliveryRepository extends CrudRepository<Delivery,Integer> {
}
