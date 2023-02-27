package esprit.tn.Repository;

import esprit.tn.Entites.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
@Repository
public interface appointementRepository extends JpaRepository<Appointment,Integer> {
   // List<Appointment> findAppointmentByCalendar(Date Calender);


 //   List<Appointment> findAppointmentsByCalendar(Date x);

@Query("select App from Appointment App where (App.Calendar = :calendar)")
    List<Appointment> findAppointmentsByCalendar(@Param("calendar") Date calendar);
}
