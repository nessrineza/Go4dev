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
    private float price;
    private String picture;
    @JsonIgnore
   @ManyToMany(mappedBy ="sponsorings",cascade = {CascadeType.PERSIST})
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
}