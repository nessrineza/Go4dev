package esprit.tn.Entites;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Dictionnaire")
public class DictionnaireDesMotsInterdits implements Serializable {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy=  GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column
    private String mots;


}
