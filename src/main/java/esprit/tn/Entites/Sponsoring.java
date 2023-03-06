package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sponsoring")
public class Sponsoring implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String type;
    private String description;
    private float priceS;
    private String picture;
    private String quantite;
    @JsonIgnore
   @ManyToMany(mappedBy ="sponsorings")
    private List<Announcement> announcements;
    @JsonIgnore
    @ManyToOne
    private Stock stocks;
    @JsonIgnore
    @ManyToMany
    private List<Command>commands;
    //@ManyToMany(mappedBy = "sponsoring",cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER) //(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "equipes")//(fetch = FetchType.EAGER, mappedBy = "equipes")
    //@JsonIgnore
   // List<Announcement> announcements = new ArrayList<>();


    public Sponsoring(String type, float priceS, String quantite) {
        this.type = type;
        this.priceS = priceS;
        this.quantite = quantite;
    }
}