package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    private String subject;
    private String description;
    private Date date ;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="publication")
    @JsonIgnore
    private List<Comment> comments;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "publications")
    @JsonIgnore
    private List<User> users;

}
