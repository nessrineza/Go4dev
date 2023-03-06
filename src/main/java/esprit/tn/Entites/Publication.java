package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date ;
    private boolean verif;
    @PositiveOrZero
    @Column(name = "report"/*, columnDefinition = "INT DEFAULT '0'"*/)
    private Integer report=0;
    @PositiveOrZero
    @Column(name = "favoris"/*, columnDefinition = "INT DEFAULT '0'"*/)
    private Integer favoris=0;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="publication")
    @JsonIgnore
    private List<Comment> comments;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "publications",fetch = FetchType.EAGER)

    @JsonIgnore
    private List<User> users;

}

