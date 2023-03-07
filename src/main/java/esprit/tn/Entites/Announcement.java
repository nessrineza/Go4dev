package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Announcement")
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TypeA typeA;
    private  String location;
    private  String description;
    private float priceA;
    private float priceTotalSpon;
<<<<<<< HEAD
    private float priceTotal;
=======
    private  float priceTotal;
>>>>>>> cd9fc34a7bbd95e87e213de16642fd1ed8f823b5
    private Integer discount;
    @Enumerated(EnumType.STRING)
    private  Category category;
    private  String picture;
    @JsonIgnore
    @ManyToOne
    private User user;
<<<<<<< HEAD
    @ManyToMany ( cascade= CascadeType.ALL , fetch = FetchType.EAGER)
=======
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
>>>>>>> cd9fc34a7bbd95e87e213de16642fd1ed8f823b5
    private List<Sponsoring> sponsorings;
   // @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)// (fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})//(fetch = FetchType.EAGER)
   // @JoinTable(name = "announcement_sponsorings",joinColumns = { @JoinColumn(name = "id") },inverseJoinColumns = { @JoinColumn(name = "id") })
   // List<Sponsoring> sponsorings = new ArrayList<>();// @JsonIgnore

}


