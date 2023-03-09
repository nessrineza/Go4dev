package esprit.tn.repository;

import esprit.tn.Entites.Publication;
import esprit.tn.Entites.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room,Integer> {
    @Query(value ="SELECT * FROM room group by favoris desc", nativeQuery = true)
    List<Room> SortedByFavoris();
}
