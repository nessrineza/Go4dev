package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
    private  float priceTotal;
    private Integer discount;
    @Enumerated(EnumType.STRING)
    private  Category category;
    private  String picture;
    @JsonIgnore
    @ManyToOne
    private User user;
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Sponsoring> sponsorings;


}


