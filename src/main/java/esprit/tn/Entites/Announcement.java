package esprit.tn.Entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Announcement")
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TypeA TypeA;
    private  String Location;
    private  String Description;
    private float Price;
    @Enumerated(EnumType.STRING)
    private  Category Category;
    private  String Picture;
    @ManyToOne
    private User user;
    @ManyToMany()
    private List<Sponsoring> sponsorings;


}


