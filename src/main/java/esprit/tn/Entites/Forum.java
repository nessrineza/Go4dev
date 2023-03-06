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
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private String forumName;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="forum")
    private List<SubjectF> subjects;
   /* @OneToMany(cascade = CascadeType.ALL,mappedBy ="forum")
    private List<User> users;*/
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="forum")
    private List<Space> spaces;
}
