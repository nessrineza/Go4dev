package esprit.tn.Controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import esprit.tn.Entites.Stock;
import esprit.tn.Entites.User;
import esprit.tn.repository.ClaimRepository;
import esprit.tn.repository.UserRepository;
import esprit.tn.Service.IClaimService;
import esprit.tn.Entites.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/claim")
public class ClaimController {
    @Autowired
    private  IClaimService iClaimService;
    @Autowired
    private ClaimRepository claimRepository;
@Autowired
private UserRepository userRepository;


@Autowired
    private JavaMailSender javaMailSender;


    @PostMapping("/add/{idUser}")
    public Claim  addClaim(@RequestBody Claim c , @PathVariable("idUser") long idUser) throws MessagingException {

        iClaimService.addClaimtoUser(c,idUser);

        User user = userRepository.findById( idUser).orElse(null);
        System.out.printf(user.getEmail());
            if (iClaimService.addClaimtoUser(c,idUser) != null) {


                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);
                helper.setFrom("piproject840@gmail.com");
                helper.setTo(user.getEmail());
                helper.setText("You Have a new Claim");
                javaMailSender.send(message);

            }

        return claimRepository.save(c);
}

    @PutMapping("/update")
    Claim updateClaim(@RequestBody Claim claim) {
        return iClaimService.addOrUpdateClaim(claim);
    }


    @GetMapping("/get/{id}")
    Claim getClaim(@PathVariable("id") Integer idClaim) {
        return iClaimService.retrieveClaim(idClaim);
    }

    @GetMapping("/all")
    List<Claim> getAllClaims() {
        return iClaimService.retrieveAllClaims();
    }


    @DeleteMapping("/delete/{id}")
    void deleteClaim(@PathVariable("id") Integer idClaim) {
        iClaimService.removeClaim(idClaim);
    }



    @GetMapping("/claims/pdf")
    public ResponseEntity<byte[]> downloadClaimsPdf() throws IOException, DocumentException {
        List<Claim> claimsList = claimRepository.findAll(); // récupération des données de réclamations depuis la base de données à l'aide de JPA

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // création d'un flux de sortie pour stocker le PDF généré
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        PdfPTable table = new PdfPTable(4); // création d'une table avec 4 colonnes

        // ajout des en-têtes de colonne à la table
        Stream.of("ID", "Utilisateur", "Date", "Sujet")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

        // ajout des données de réclamations à la table
        for (Claim claim : claimsList) {
            table.addCell(String.valueOf(claim.getId()));
            table.addCell(claim.getUsers().getUsername()); // remplacez "getUsername()" par la méthode appropriée pour récupérer le nom d'utilisateur de l'utilisateur associé à la réclamation
            table.addCell(claim.getDate().toString()); // remplacez "getDate()" par la méthode appropriée pour récupérer la date de la réclamation
            table.addCell(claim.getSubject().toString());
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "claims.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK); // retourne le document PDF en tant que tableau de bytes dans la réponse HTTP
    }

}

