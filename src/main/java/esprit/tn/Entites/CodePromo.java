package esprit.tn.Entites;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;




@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "CodePromo")
public class CodePromo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Integer id;
    private String nom;
    private float pourcentage;
    @Temporal(TemporalType.DATE)
    private Date dateexpiration;
}

