package esprit.tn.Mappers;

import esprit.tn.Dto.AnnonceDto;
import esprit.tn.Entites.Announcement;
import esprit.tn.Entites.Sponsoring;

import java.util.ArrayList;

public class AnnonceMapper {
    public static AnnonceDto mapToDto(Announcement announcement) {

         String type = "";
         float priceS = 0;
         String quantite = "";
        if ( announcement.getSponsorings().size() !=0 ) {
            type   = announcement.getSponsorings().get(0).getType();
            priceS  =announcement.getSponsorings().get(0).getPriceS();
            quantite =  announcement.getSponsorings().get(0).getQuantite();
        }

        AnnonceDto annonceDto = AnnonceDto.builder()
                .typeA(announcement.getTypeA())
                .priceA(announcement.getPriceA())
                .discount(announcement.getDiscount())
                .category(announcement.getCategory())
              .type(type)
              .priceS(priceS)
               .quantite(quantite)
                .build();
        return annonceDto;
    }
        public static Announcement mapToEntity(AnnonceDto annonceDto){
           Sponsoring sponsoring = new Sponsoring(annonceDto.getType(), annonceDto.getPriceS()  ,  annonceDto.getQuantite());
            Announcement announcement = Announcement.builder()
                    .typeA(annonceDto.getTypeA())
                    .priceA(annonceDto.getPriceA())
                    .discount(annonceDto.getDiscount())
                    .category(annonceDto.getCategory())
                    .sponsorings( new ArrayList<Sponsoring>(){{
                        add(sponsoring);
                    } })
                    .build();
            return announcement;

}}
