package esprit.tn.Entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private  String FirstName;
    private  String LastName;
    private String PhoneNumber;
    private  String Mail;
    private String Password;
    private  Role role;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="user" )
    List<Announcement> announcements;
    @ManyToOne
    private Forum forum;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="user" )
    List<Appointment> Appointments;
    @OneToMany(mappedBy = "users")
    List<Claim>claims;




}
