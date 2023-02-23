package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    private boolean blocked;
    private boolean activated;
    private  String FirstName;
    private  String LastName;
    private String PhoneNumber;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="user" )
    @JsonIgnore
    List<Announcement> announcements;
    @ManyToMany
    @JsonIgnore
    private List<Publication> publications;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="user" )
    @JsonIgnore
    List<Appointment> Appointments;
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Claim>claims;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }



}
