
package esprit.tn.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date ;
    private String topic;
    @PositiveOrZero
    @Column(name = "favoris"/*, columnDefinition = "INT DEFAULT '0'"*/)
    private Integer favoris=0;
    @PositiveOrZero
    @Column(name = "report"/*, columnDefinition = "INT DEFAULT '0'"*/)
    private Integer report=0;
    private boolean verif;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "rooms")
    @JsonIgnore

    private List<User> users;
}
