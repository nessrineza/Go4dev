package esprit.tn.Entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private String publicationName;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="publication")
    private List<SubjectF> subjects;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy ="publications")
    private List<User> users;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="publication")
    private List<Space> spaces;
}
