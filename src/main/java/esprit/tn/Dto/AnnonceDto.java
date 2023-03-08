package esprit.tn.Dto;

import esprit.tn.Entites.Category;
import esprit.tn.Entites.Sponsoring;
import esprit.tn.Entites.TypeA;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class AnnonceDto {
    private TypeA typeA;
    private float priceA;
    private Integer discount;
    private Category category;
    private String type;
    private float priceS;
    private String quantite;

    private List<Sponsoring> sponsorings;

    public TypeA getTypeA() {
        return typeA;
    }

    public float getPriceA() {
        return priceA;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Category getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public float getPriceS() {
        return priceS;
    }

    public String getQuantite() {
        return quantite;
    }

    public List<Sponsoring> getSponsorings() {
        return sponsorings;
    }
}
