package esprit.tn.Repository;

import esprit.tn.Entites.Claim;
import esprit.tn.Entites.Partner;
import esprit.tn.Entites.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query("SELECT s FROM Stock s WHERE s.quantityMin>= s.quantity")
    List<Stock> retrieveStock();
    @Query("SELECT s FROM Stock s WHERE (s.quantityMin >= s.quantity) AND (s.checked = FALSE)")
    List<Stock> retrieveStockEnRp();




    //Stock findByLibelleStock(String s);

    //List<Stock> searchStcokWithLibelle(String str);
   // List<Stock> findByLibelleStock(String str);
    //List<Stock> findAllByOrderByCreatedAtAsc();
    //List<Stock> findAllByOrderByCreatedAtDesc();
    //List<Stock> findAllByOrderByUpdatedAtAsc();
    //List<Stock> findAllByOrderByUpdatedAtDesc();
    //List<Stock> findAllByOrderByQteDesc();
    //List<Stock> findAllByOrderByQteAsc();


}
