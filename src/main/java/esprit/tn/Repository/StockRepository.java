package esprit.tn.repository;


import esprit.tn.Entites.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query("SELECT s FROM Stock s WHERE s.quantityMin>= s.quantity")
    List<Stock> retrieveStock();
    @Query("SELECT s FROM Stock s WHERE (s.quantityMin>= s.quantity) AND (s.checked=FALSE)")
    List<Stock> retrieveStockEnRp();
    @Query("SELECT s FROM Stock s WHERE (:str is null or s.libelleStock LIKE %:str% ) and  ((:date1 is null or s.createdAt=:date1) "
            + "or ( s.createdAt BETWEEN :date1 and :date2)) and(:nbr is null or s.quantity>=:nbr)")
    List<Stock> rechercheStcokAvance(@Param("str") String str , @Param("date1") Date d1, @Param("date2") Date d2, @Param("nbr") int nbr);

   List<Stock> findAllByOrderByCreatedAtDesc();


    List<Stock> findByLibelleStock(String s);

    List<Stock>findAllByOrderByCreatedAtAsc();

    List<Stock> findAllByOrderByUpdatedAtAsc();
    List<Stock> findAllByOrderByUpdatedAtDesc();
    List<Stock> findAllByOrderByQuantityDesc();
    List<Stock> findAllByOrderByQuantityAsc();



}
