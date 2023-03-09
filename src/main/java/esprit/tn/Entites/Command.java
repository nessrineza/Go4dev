package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Command")
public class Command implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private Integer total;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,mappedBy ="commands")
    private List<Sponsoring> sponsorings;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy ="commands")
    private Delivery deliveries;


}
