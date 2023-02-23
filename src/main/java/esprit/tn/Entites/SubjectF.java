package esprit.tn.Entites;

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


public class SubjectF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private String title;
    private String description;
    private Date date ;
    @ManyToOne
    private Publication publication;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="subjectF")
    private List<Comment> comments;
}
