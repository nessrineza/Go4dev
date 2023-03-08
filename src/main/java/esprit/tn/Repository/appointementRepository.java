package esprit.tn.repository;

import esprit.tn.Entites.Appointment;
import esprit.tn.Entites.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
@Repository
public interface appointementRepository extends JpaRepository<Appointment,Integer> {
   // List<Appointment> findAppointmentByCalendar(Date Calender);


 //   List<Appointment> findAppointmentsByCalendar(Date x);


    @Query("SELECT CASE WHEN COUNT(appt) > 0 THEN true ELSE false END FROM Appointment appt WHERE (((appt.appointmentStartTime >= :startime) AND (appt.appointmentStartTime < :endtime)) OR ((appt.appointmentEndTime > :startime) AND  (appt.appointmentEndTime <= :endtime) ) OR ((appt.appointmentStartTime <= :startime) AND (appt.appointmentEndTime >= :endtime) )  OR ((appt.appointmentStartTime >= :startime) AND (appt.appointmentEndTime <= :endtime)))")
    boolean isInBetweenTwoTime(@Param("startime") LocalTime startime, @Param("endtime") LocalTime endtime  );

    @Query("SELECT CASE WHEN COUNT(appt) > 0 THEN true ELSE false END FROM Appointment appt WHERE    ((appt.appointmentDate >= :date)   and (((appt.appointmentStartTime >= :startime) AND (appt.appointmentStartTime < :endtime)) OR ((appt.appointmentEndTime > :startime) AND  (appt.appointmentEndTime <= :endtime) ) OR ((appt.appointmentStartTime <= :startime) AND (appt.appointmentEndTime >= :endtime) )  OR ((appt.appointmentStartTime >= :startime) AND (appt.appointmentEndTime <= :endtime))))")
    boolean isInBetweenTwoTimeAndDate(@Param("date") LocalDate date, @Param("startime") LocalTime startime, @Param("endtime") LocalTime endtime  );

    List<Appointment> findAppointmentsByAppointmentDate(LocalDate date);
    @Query("select appt  from  Appointment appt WHERE (appt.appointmentStatus=esprit.tn.Entites.AppointmentStatus.Available)and (appt.appointmentDate=:date)")
    List<Appointment> findAppointmentsByavailbleAndDate(@Param("date")LocalDate date);
}
