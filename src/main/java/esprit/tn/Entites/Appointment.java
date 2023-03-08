package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "appointment_date", columnDefinition = "DATE")
    private LocalDate appointmentDate;

    @Column(name = "appointment_start_time", columnDefinition = "TIME")
    private LocalTime appointmentStartTime;


    @Column(name = "appointment_end_time", columnDefinition = "TIME")
    private LocalTime appointmentEndTime;
    @Column(name = "appointment_status" )
    @Enumerated(EnumType.STRING)
    AppointmentStatus appointmentStatus;
    @ManyToOne
    User user;
    @OneToMany()
    private Set<Announcement> Announcements;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="Appointment")

     Set<Payement> listPayement;


}
