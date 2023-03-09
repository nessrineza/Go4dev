package esprit.tn.repository;

import esprit.tn.Entites.Command;
import esprit.tn.stat.CAEvolution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandRepository extends CrudRepository<Command,Integer> {
    @Query(value = "select t1.date as date , SUM(t2.total) as montant from command t1 inner join command t2 on t1.id >= t2.id group by t1.id, t1.total, CAST(t1.date as date) order by t1.date ASC", nativeQuery = true)
    List<CAEvolution> getCAEvolution();
}
