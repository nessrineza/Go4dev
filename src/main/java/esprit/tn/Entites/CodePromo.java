package esprit.tn.Entites;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
=======
import lombok.*;
>>>>>>> cd9fc34a7bbd95e87e213de16642fd1ed8f823b5

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
<<<<<<< HEAD

@Entity
@Table(name = "jwt_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<<< HEAD:src/main/java/esprit/tn/Entites/CodePromo.java
=======
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> cd9fc34a7bbd95e87e213de16642fd1ed8f823b5
@Table(name = "CodePromo")
public class CodePromo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
<<<<<<< HEAD
    private  String nom;
    private  float pourcentage;
    @Temporal(TemporalType.DATE)
    private Date dateexpiration;
========
public class JwtToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "expiration_date")
    private Date expirationDate;
>>>>>>>> cd9fc34a7bbd95e87e213de16642fd1ed8f823b5:src/main/java/esprit/tn/Entites/JwtToken.java

=======
    private String nom;
    private float pourcentage;
    @Temporal(TemporalType.DATE)
    private Date dateexpiration;
>>>>>>> cd9fc34a7bbd95e87e213de16642fd1ed8f823b5
}
