package esprit.tn.Entites;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class searchStock {
    String query;
    Date dateDebut;
    Date dateFin;
    int nbrProduct;
}
